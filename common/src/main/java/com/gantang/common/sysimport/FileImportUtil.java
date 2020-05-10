
package com.gantang.common.sysimport;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util
 * @ClassName(类名称):aaa
 * @Title(标题):  aaa.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月24日 下午5:14:58   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):   
 * TODO(这里描述这个文件做什么 – 可选)  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 *—————————————————————————————————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人: 
 *    修改原因：
 *              
 *—————————————————————————————————————————————————————————————————
 */

/**
 * 
 * @author keas 仇石廉 2007-03-19
 *         <p>
 *         文件操作类
 *         <p>
 */

public class FileImportUtil {
	private static Logger log = Logger.getLogger(FileImportUtil.class);

	/**
	 * 移动文件
	 * 
	 * @param srcFile
	 *            eg: c:\windows\abc.txt
	 * @param destPath
	 *            eg: c:\temp
	 * @return
	 */
	public static boolean move(String srcFile, String destPath) {
		// File (or directory) to be moved
		File file = new File(srcFile);
		// Destination directory
		File dir = new File(destPath);
		// Move file to new directory
		boolean success = file.renameTo(new File(dir, file.getName()));

		return success;
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath)  {
		try {

			File myFilePath = new File(folderPath.trim());
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			log.error("新建目录操作出错," + folderPath + "创建失败");
			throw new RuntimeException("新建目录操作出错," + folderPath + "创建失败");
		}

	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) {
		FileWriter resultFile =null;
		PrintWriter myFile = null;
		try {
			File myFilePath = new File(filePathAndName.trim());
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			
			}
			 resultFile = new FileWriter(myFilePath);
			 myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.flush();

		} catch (IOException e) {
			log.error("新建目录操作出错");
			throw new RuntimeException("新建目录文件操作出错," + filePathAndName
					+ "创建失败:" + e.getMessage());
		}
		finally{
			try {
				resultFile.flush();
				resultFile.close();
				myFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static void newFile(String filePath) throws RuntimeException {
		try {
        
			File myFilePath = new File(filePath.trim());
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
		} catch (IOException e) {
			log.error("新建目录操作出错");
			throw new RuntimeException("新建目录文件操作出错,"
					+ "创建失败:" + e.getMessage());
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/test.xml
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			File myDelFile = new File(filePathAndName.trim());
			if(myDelFile.exists()){
		  	  myDelFile.delete();
			}
		} catch (Exception e) {
			log.error("删除文件操作出错");
			e.printStackTrace();
			throw new RuntimeException("删除文件操作出错," + filePathAndName + "删除失败:"
					+ e.getMessage());
		}

	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            String 文件夹路径及名称 如c:/test
	 * @return boolean
	 */
	public static void delFolder(String folderPath)  {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			File myFilePath = new File(folderPath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			log.error("删除文件夹操作出错");
			e.printStackTrace();
			throw new RuntimeException("删除文件操作出错," + folderPath + "删除失败:"
					+ e.getMessage());

		}

	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	/*public boolean copyFile(String oldPath, String newPath)
			throws RuntimeException {
		try {

			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream is = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fos = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				int n = 0;
				while ((n = is.read(buffer)) > 0) {
					fos.write(buffer, 0, n);
				}
				fos.flush();
				fos.close();
				is.close();
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			log.error("复制单个文件" + oldPath + "操作出错");
			e.printStackTrace();
			throw new RuntimeException("复制单个文件" + oldPath + "操作出错:"
					+ e.getMessage());
         
		}

	}
  */
	public static boolean copyFile(String oldPath, String newPath) {
		InputStream is = null;
		FileOutputStream fos = null;
	

			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				try {
				is = new FileInputStream(oldPath); // 读入原文件
				fos = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				int n = 0;
				while ((n = is.read(buffer)) > 0) {
					fos.write(buffer, 0, n);
				}
				fos.flush();

				return true;
				} catch (Exception e) {
					log.error("复制单个文件" + oldPath + "操作出错");
					e.printStackTrace();
					throw new RuntimeException("复制单个文件" + oldPath + "操作出错:"
							+ e.getMessage());

				} finally {
					try {
						fos.close();
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			} else {
				return false;
			}

		
	}

	
	
	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdir(); // 如果文件夹不存在 则建立新文件夹
			File file = new File(oldPath.trim());
			File[] file_list = file.listFiles();
			for (int i = 0; i < file_list.length; i++) {
				if (file_list[i].isFile()) {
					copyFile(file_list[i].getPath(), newPath + File.separator
							+ file_list[i].getName());
				} else if (file_list[i].isDirectory()) {
					copyFolder(file_list[i].getPath(), newPath + File.separator
							+ file_list[i].getName());
				}
			}
		} catch (Exception e) {
			log.error("复制整个文件夹" + oldPath + "到" + newPath + "出错");
			e.printStackTrace();
			throw new RuntimeException("复制整个文件夹" + oldPath + "到" + newPath
					+ "出错:" + e.getMessage());
		}
	}
	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	/**
	 * 删除文件夹下所有的文件（包括子文件夹）
	 * 
	 * @param folderName_Path
	 */

	public  static void delAllFile(String folderName_Path) {
		try {
			File file = new File(folderName_Path.trim());
			if(!file.isDirectory()){
				file.delete();
			}else{
				File[] file_list = file.listFiles();
				for (int i = 0; i < file_list.length; i++) {
					if (file_list[i].isFile()) {
						file_list[i].delete();
					} else if (file_list[i].isDirectory()) {
						delAllFile(file_list[i].getPath());
						file_list[i].delete();
					}
				}
			}
		} catch (Exception e) {
			log.error("delAllFile " + folderName_Path + " Error！");
			e.printStackTrace();
			throw new RuntimeException("delAllFile" + folderName_Path + "Error:"
					+ e.getMessage());
		}

	}

	public static String inputStreamTOString(InputStream inStream) throws Exception {

		if (inStream == null) {
			return null;
		}

		ByteArrayOutputStream outStream = null;
		String result = "";
		try {
			outStream = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			int count = -1;
			while ((count = inStream.read(data, 0, 1024)) != -1) {
				outStream.write(data, 0, count);
			}

			data = null;

			result = new String(outStream.toByteArray(), "utf-8");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (null != inStream) {
					inStream.close();
				}

				if (null != outStream) {
					outStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	 
	 public static void log(String path, String fileName, String logContent) {
		 if(StringUtils.isBlank(path) || StringUtils.isBlank(fileName) || StringUtils.isBlank(logContent)){
			 return ;
		 }
		String filepath = path + File.separatorChar + fileName;
		FileImportUtil.newFolder(path);
		FileImportUtil.newFile(filepath);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filepath, true));
			out.write(logContent);
			out.newLine();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
                if(null != out) {
                    out.flush();
                    out.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


	public static void uploadImg(String imgsrc, String imgdist, String suffix, int widthdist, int heightdist) {
		uploadImg(imgsrc, imgdist, suffix, widthdist, heightdist, null);
	}

	public static void uploadImg(String imgsrc, String imgdist, String suffix, int widthdist, int heightdist, Float rate) {
		File srcfile = new File(imgsrc);
		// 检查文件是否存在
		if (!srcfile.exists()) {
			return;
		}
		if (widthdist == 0 && heightdist == 0) {
			Long size = srcfile.length();
			if (size > 10 * 1024 * 1024) {
				//大于10M
				rate = 0.5f;
			} else if (size > 8 * 1024 * 1024) {
				//大于10M
				rate = 0.6f;
			} else if (size > 6 * 1024 * 1024) {
				//大于10M
				rate = 0.7f;
			} else if (size > 4 * 1024 * 1024) {
				//大于10M
				rate = 0.8f;
			} else if (size > 2 * 1024 * 1024) {
				//大于10M
				rate = 0.9f;
			} else {
				//大于10M
				rate = 1f;
			}
		}
		reduceImg(imgsrc, imgdist, suffix, widthdist, heightdist, rate);
	}

	/**
	 * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
	 * @param imgsrc 源图片地址
	 * @param imgdist 目标图片地址
	 * @param widthdist 压缩后图片宽度（当rate==null时，必传）
	 * @param heightdist 压缩后图片高度（当rate==null时，必传）
	 * @param rate 压缩比例
	 */
	public static void reduceImg(String imgsrc, String imgdist, String suffix, int widthdist, int heightdist, Float rate) {
		try {
			File srcfile = new File(imgsrc);
			// 检查文件是否存在
			if (!srcfile.exists()) {
				return;
			}
			// 如果rate不为空说明是按比例压缩
			if (rate != null && rate > 0) {
				// 获取文件高度和宽度
				int[] results = getImgWidthHeight(srcfile);
				if (results == null || results[0] == 0 || results[1] == 0) {
					return;
				} else {
					widthdist = (int) (results[0] * rate);
					heightdist = (int) (results[1] * rate);
				}
			}
			// 开始读取文件并进行压缩
			Image src = ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

//			FileOutputStream out = new FileOutputStream(imgdist);
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(tag);
//			out.close();
			BufferedImage bufferedImage = ImageIO.read(new File(imgsrc));
			//String formatName = imgdist.substring(imgdist.lastIndexOf(".") + 1);
			ImageIO.write(bufferedImage, suffix, new File(imgdist));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void reduceImg(String imgsrc, String imgdist, String suffix, int widthdist, int heightdist) {
		reduceImg(imgsrc, imgdist, suffix, widthdist, heightdist, null);
	}
	/**
	 * 获取图片宽度
	 *
	 * @param file 图片文件
	 * @return 宽度[0]  高度[1]
	 */
	public static int[] getImgWidthHeight(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int result[] = {0, 0};
		try {
			is = new FileInputStream(file);
			src = ImageIO.read(is);
			result[0] = src.getWidth(null); // 得到源图宽
			result[1] = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
