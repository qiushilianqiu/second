/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.core
 * @ClassName(类名称):ResultImport
 * @Title(标题):  ResultImport.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午11:14:36   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    TODO(用一句话描述该文件做什么)  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业目的
 *————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人: 
 *    修改原因：
 *              
 *——————————————————————————————————————
 */  
package com.gantang.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gantang.common.sysimport.ResponseModel;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.core
 * @ClassName(类名称):ResultImport
 * @Title(标题):  ResultImport.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午11:14:36   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  导入导出错误消息JSON信息返回的页面 
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
public class ResultImport {
    //JSON信息返回的页面
   
    public  final static  String LOGIN="index";
    public  final static  String PDF_REPORT="pdfView";

   

    /**
     * @Title: json   
     * @Description: TODO Map数据转成JSON
     * @param map Map Map对象   
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(Map map){
         return JsonUtil.toJSONString(map);
    }
    /**
     * @Title: json   
     * @Description: TODO JSON转成JSON
     * @param jsonStr String 字符串  
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(String jsonStr){
        return jsonStr;
    }
   
//    /**
//     * @Title: json   
//     * @Description: TODO List转成JSON
//     * @param list List 数据集合  
//     * @return: String      
//     * @throws
//     * @author: sl.qiu
//     */
//    @SuppressWarnings("unchecked")
//    public static String json(List list){
//        if(null ==list){
//            list =new ArrayList();
//        }
//        String jsonStr = JsonUtil.Pagination2GridJson(list);
//        list.clear();
//        list =null;
//        return jsonStr;
//    }
    /**
     * @Title: json   
     * @Description: TODO Object转成JSON
     * @param obj Object 对象   
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    @SuppressWarnings("unchecked")
    public static String json(Object obj){
    	return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue);
      
    }
   
    /**
     * @Title: json   
     * @Description: TODO boolean转成JSON
     * @param success boolean 是否成功
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(boolean success){
        return JsonUtil.toJSONString(new ResponseModel(success));
    }
    /**
     * @Title: json   
     * @Description: TODO responseModel转成JSON
     * @param responseModel ResponseModel 响应对象
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(ResponseModel responseModel){
        return JsonUtil.toJSONString(responseModel);
    }
    
    /**
     * @Title: json   
     * @Description: TODO 更新变动条数转成responseModel的JSON
     * @param updateRow int 更新变动条数
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(int updateRow){
        return JsonUtil.toJSONString(new ResponseModel(updateRow));
    }
    /**
     * @Title: json   
     * @Description: TODO 更新变动条数转成responseModel的JSON
     * @param updateRow int 更新变动条数
     * @param msg String 消息信息
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(int updateRow,String msg){
        return JsonUtil.toJSONString(new ResponseModel(updateRow,msg));
    }
 
    /**
     * @Title: json   
     * @Description: TODO 更新变动条数转成responseModel的JSON
     * @param updateRow int 更新变动条数
     * @param id String 更新后的ID
     * @param msg String 消息信息
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(int updateRow,String id,String msg){
        ResponseModel responseModel=   new ResponseModel(updateRow,msg);
        responseModel.setId(id);
        return JsonUtil.toJSONString(responseModel);
    }
    /**
     * @Title: json   
     * @Description: TODO 更新变动条数转成responseModel的JSON
     * @param updateRow int 更新变动条数
     * @param ids Map<String,Object> 更新后的IDS
     * @param msg String 消息信息
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(int updateRow,Map<String,Object> ids,String msg){
        ResponseModel responseModel=   new ResponseModel(updateRow,msg);
        responseModel.setIds(ids);
        return JsonUtil.toJSONString(responseModel);
    }
    
    /**
     * @Title: json   
     * @Description: TODO responseModel的JSON
     * @param success boolean 是否成功
     * @param msg String 消息信息
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(boolean success,String msg){
        return JsonUtil.toJSONString(new ResponseModel(success, msg));
    }
    /**
     * @Title: json   
     * @Description: TODO responseModel的JSON
     * @param success boolean 是否成功
     * @param msg String 消息信息
     *  @param url String 要跳转的url页面
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(boolean success,String msg,String url){
        return JsonUtil.toJSONString(new ResponseModel(success, msg, url));
    }
    /**
     * @Title: json   
     * @Description: TODO responseModel的JSON 将下载文件的文件路径和文件名字
     * @param fileName String 文明名称
     * @param filePath String 文件路径
     * @param success boolean 是否成功
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String json(String fileName, String filePath,boolean success){
      
        return JsonUtil.toJSONString(new ResponseModel(fileName, filePath, success));
    }
    /**
     * @Title: error   
     * @Description: TODO responseModel的JSON 错误消息结果
     * @param errorList List<ObjectError> 错误消息集合
     * @return: String      
     * @throws
     * @author: sl.qiu
     */
    public static String error(List<ObjectError> errorList){

        Map<String, List<String>> validate = new HashMap<String, List<String>>();
        if(CollectionUtil.isNotNullList(errorList)){
            for(ObjectError item:errorList){
                List<String> listMsg = new ArrayList<String>();
                listMsg.add(item.getDefaultMessage());
                validate.put(item.getObjectName(), listMsg);
            }
        }

        ResponseModel res = new ResponseModel();
        res.setSuccess(false);
        res.setMsg("validate");
        res.setValidate(validate);
        String json = JsonUtil.toJSONString(res);
       
        return json;
    }

   
}