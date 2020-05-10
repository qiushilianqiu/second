package com.gantang.common.util;

import com.alibaba.fastjson.JSONObject;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件上传下载
 *
 * @author zqs
 */
@RestController
public class FileUtil {
    @Autowired
    HttpServletRequest request;

    @Value("${web.upload-path}")
    private String rootPath;

    /**
     * 普通上传
     *
     * @param file
     * @param uploadPath 如果根目录为D:/test/,要上传路径为D:/test/upload则uploadPath为upload,多层路径用“,”分隔
     * @return
     */
    @RequestMapping("/api/upload/{uploadPath}")
    public synchronized Result upload(@RequestParam("file") MultipartFile file,
                                      @PathVariable("uploadPath") String uploadPath) {
        String fileurl = "";
        String returnPath = "";
        if (null != uploadPath && !"".equals(uploadPath)) {
            fileurl = rootPath;
            String[] paths = uploadPath.split(",");
            String newPath = "";
            for (String s : paths) {
                if (!"".equals(s)) {
                    newPath = newPath + s + "/";
                    returnPath = returnPath + s + "/";
                }
            }
            fileurl += newPath;
        }
        JSONObject msg = new JSONObject();
        if (file.isEmpty()) {
            return ResultGenerator.genSuccessResult("上传文件为空!");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 文件上传后的路径
        String filePath = fileurl + "/";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        String newName = FileUtil.getMillisecond() + "." + FileUtil.getSuffixName(fileName);
        returnPath = returnPath + newName;
        File dest = new File(filePath + newName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            msg.put("msg", "上传成功");
            msg.put("newname", newName);
            msg.put("returnPath", returnPath);
            msg.put("oldname", fileName);
            msg.put("size", file.getSize());
            return ResultGenerator.genSuccessResult(msg);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("上传失败!");
    }

    /**
     * base64上传
     *
     * @param map
     * @param uploadPath
     * @return
     */
    @PostMapping("/api/upload/base64/{uploadPath}")
    public Result uploadByBase64(@RequestBody Map<String, String> map, @PathVariable("uploadPath") String uploadPath) {
        String base64Str = map.get("file");
        String newName = FileUtil.getMillisecond() + "." + FileUtil.getSuffixName(map.get("fileName"));
        String returnPath = "/" + uploadPath + "/" + newName;
        String filePath = rootPath + uploadPath + "/" + newName;
        File dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        boolean flag = generateImage(base64Str.split(",")[1], filePath);
        if (flag) {
            JSONObject returnJson = new JSONObject();
            returnJson.put("returnPath", returnPath);
            return ResultGenerator.genSuccessResult(returnJson);
        } else {
            return ResultGenerator.genFailResult("上传失败!");
        }
    }

    /**
     * 上传文件不改变原始文件名
     *
     * @param file
     * @param uploadPath
     * @return
     */
    @RequestMapping("/api/uploadByOriginal/{uploadPath}")
    @ResponseBody
    public String uploadByOriginal(@RequestParam MultipartFile file, @PathVariable("uploadPath") String uploadPath) {
        String fileurl = "";
        String returnPath = "";
        if (null != uploadPath && !"".equals(uploadPath)) {
            fileurl = rootPath;
            String[] paths = uploadPath.split(",");
            String newPath = "";
            for (String s : paths) {
                if (!"".equals(s)) {
                    newPath = newPath + s + "/";
                    returnPath = returnPath + s + "/";
                }
            }
            fileurl += newPath;
        }
        JSONObject msg = new JSONObject();
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 文件上传后的路径
        String filePath = fileurl + "/";
        returnPath = returnPath + fileName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            msg.put("msg", "上传成功");
            msg.put("returnPath", returnPath);
            msg.put("filename", fileName);
            msg.put("size", file.getSize());
            return msg.toJSONString();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg.put("msg", "上传失败");
        return msg.toJSONString();
    }

    /**
     * 文件下载
     * <p>
     * 文件资源路径
     * 文件输出名
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/api/downLoad/file", method = RequestMethod.GET)
    public void getDownload(String fullPath, String fileName, HttpServletResponse response) {
        fullPath = rootPath + fullPath;
        File downloadFile = new File(fullPath);
        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String name = (null == fileName || "".equals(fileName)) ? downloadFile.getName() : fileName;
        try {
            // 解决中文文件名不能显示的问题
            name = new String(name.getBytes(), "ISO-8859-1");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String headerValue = String.format("attachment; filename=\"%s\"", name);
        response.setHeader(headerKey, headerValue);

        // Copy the stream to the response's output stream.
        try {
            InputStream myStream = new FileInputStream(fullPath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return
     */
    public static Long getMillisecond() {
        return System.currentTimeMillis();
    }

    /**
     * 获取文件的后缀名
     *
     * @param fileName
     * @return
     */
    public static String getSuffixName(String fileName) {
        if (fileName.indexOf(".") != -1) {
            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
            return prefix;
        } else {
            return "";
        }
    }

    /**
     * base64字符串转化成图片
     *
     * @param imgStr
     * @param path
     * @return
     */
    public static boolean generateImage(String imgStr, String path) { // 对字节数组字符串进行Base64解码并生成图片
        // 图像数据为空
        if (imgStr == null) {
            return false;
        }
        try {
            // Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param zipFile 压缩包文件对象
     * @param listKey 压缩的图片物理地址
     * @return
     */
    public static boolean packageZip(File zipFile, List<String> listKey) {
        //图片打包操作
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        try {
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));// 用这个构造最终压缩包的输出流
//            zipSource = null;// 将源头文件格式化为输入流

            for (String picKey : listKey) {
                File file = new File(picKey);
                zipSource = new FileInputStream(file);
                byte[] bufferArea = new byte[1024 * 10];// 读写缓冲区
                // 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipStream.putNextEntry(zipEntry);// 定位到该压缩条目位置，开始写入文件到压缩包中

                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);// 输入缓冲流
                int read = 0;

                // 在任何情况下，b[0] 到 b[off] 的元素以及 b[off+len] 到 b[b.length-1]
                // 的元素都不会受到影响。这个是官方API给出的read方法说明，经典！
                while ((read = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
                    zipStream.write(bufferArea, 0, read);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        } finally {
            // 关闭流
            try {
                if (null != bufferStream) {
                    bufferStream.close();
                }
                if (null != zipStream) {
                    zipStream.close();
                }
                if (null != zipSource) {
                    zipSource.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
