package com.gantang.common.wxsmallpost;

import com.gantang.common.redis.RedisUtil;
import com.gantang.common.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):
 * @Package(包名称) .  com.gantang.util.PostMsgUtil
 * @ClassName(类名称):PostMsgUtil
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/3/13 9:17
 * @version(版本): V1.0
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    小程序方法推送
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
public class WxPostUtil {
    @Autowired
    static RedisUtil redisUtil;
    @Value("${weChat.appId}")
    private String appId;
    @Value("${weChat.appSecret}")
    private String appSecret;
    /**
     * @throws @author: KingYiFan
     * @Title: main别问我为什么这么LOW 还用main方法，我只想告诉你main方法是最牛逼的方法没有之一
     * @Description: 测试推送
     * @param:  args
     * @return: void
     */
    public static void main(String[] args) {
        //封装了推送实体类，别问我为什么一直封装，java三特性 继承 封装 多态
        WxsmallTemplate tem = new WxsmallTemplate();
        //模板id 刚刚获取废了很成时间 如果还不会，我觉得你应该放弃java了
        tem.setTemplateId("Gcl3MdDqwWYYqMNo-2SQ1GkCLUErFKg7IJbTb0PmFt4");
        //推送给哪位神仙。 这个是openId 不是UnionID 如果是unionId肯定推送不过去。
        tem.setToUser("oHwTc4jHuMceYYmBAb8sNJyxQP24");
        //fromId 这个炒鸡重要，没有他百分百推送不成功，fromId+openId 才能推送
        tem.setForm_id("Gcl3MdDqwWYYqMNo-2SQ1GkCLUErFKg7IJbTb0PmFt4");
        //用户点击之后调到小程序哪个页面 找你们前段工程师提供即可
//        tem.setPage("pages/welcome/welcome");
        //有封装了一个实体类 哈哈哈 这个是模板消息参数
        List<WxsmallTemplateParam> paras = new ArrayList<WxsmallTemplateParam>();
        //这个是满参构造 keyword1代表的第一个提示  红包已到账这是提示 #DC143C这是色值不过废弃了
//        WxsmallTemplateParam templateParam = new WxsmallTemplateParam(
//                "keyword1", "红包已到账", "#DC143C");
//        //装进小参数结合中
//        paras.add(templateParam);
        //我就不嘚瑟了 省点劲直接扔进去算了哈哈哈哈哈~~~~
        paras.add(new WxsmallTemplateParam("keyword1", "采购订单审批-张三-20180920", ""));
        paras.add(new WxsmallTemplateParam("keyword2", "付款单审批流程", "#DC143C"));
        paras.add(new WxsmallTemplateParam("keyword3", "张三", ""));
        paras.add(new WxsmallTemplateParam("keyword4", "销售部", ""));
        paras.add(new WxsmallTemplateParam("keyword5", "2018-09-20 18:00:00", ""));

        //这里写的挺恶心的，不想抽取工具了。直接转换时间戳把
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date temp = new Date();
//        String str = "";
//        str = sdf.format(temp);
//        paras.add(new WxsmallTemplateParam("keyword8", str, ""));
        //然后把所有参数扔到大的模板中
        tem.setTemplateParamList(paras);
        //模板需要放大的关键词，不填则默认无放大
        tem.setEmphasis_keyword("keyword1.DATA");
        //获取token凭证。
        Token token = getToken();
        String accessToken = "19_YNuvbGwoMgc1rRwEP2Qz6f3ckIeMXTfVqFASS2vv79ENd8ZAL4p0dklHWaovUjZLAqVP-sSsXqSsb8cmmY23OZ7RVfk9CdiiqzrMAbel4eFfmpYzfoIREiJjcKY2P3Zb3rPoLKReWyO9ADSaELAcAGAKRG";
        //最后一步请求接口哈哈哈  推送成功
        boolean result1 = sendTemplateMsg(accessToken, tem);
        if (result1) {
            System.err.println("推送成功");
        } else {
            System.err.println("推送失败");
        }
    }
    /**
    　　* @Description: 获取token
    　　* @param tags　　
       ∗ @return{return_type}
    　　* @throws
    　　* @author sl.qiu　　
       ∗  @date 2019/3/13 10:58
    */
    public static Token getToken() {
        Token token = null;
//        try {
//            // 小程序唯一标识 (在微信小程序管理后台获取)
//            String wxspAppid = appId;
//            // 小程序的 app secret (在微信小程序管理后台获取)
//            String wxspSecret = appSecret;
//            //这里直接写死就可以，不用改，用法可以去看api
//            String grant_type = "client_credential";
//            //封装请求数据
//            String params = "grant_type=" + grant_type + "&secret=" + wxspSecret + "&appid=" + wxspAppid;
////            String accessToken = (String) redisUtil.get("access_token");
//            //发送GET请求
//            String sendGet = HttpRequestUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
//            // 解析相应内容（转换成json对象）
//            JSONObject json = new JSONObject(sendGet);
//            System.out.println("json:" + json);
//            //拿到accesstoken
//            String accesstoken = (String) json.get("access_token");
//            redisUtil.set("access_token", accesstoken, 7200L);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            //插入日志表
//            SystemLog systemLog = new SystemLog("1", "/api/ScheduledTasks/ScheduledTasks", "127.0.0.1", "执行失败-获取小程序accessToken", e.getMessage().substring(800));
//            userMapper.saveLog(systemLog);
//        }
        return token;
    }
    public static boolean sendTemplateMsg(String token, WxsmallTemplate template) {

        boolean flag = false;
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);

        String jsonResult = HttpRequestUtil.sendPost(requestUrl,template.toJSON());
        System.err.println(template.toJSON());
        // 解析相应内容（转换成json对象）
//        JSONObject json = new JSONObject(jsonResult);
//        System.out.println("json:" + json);
 
        if (jsonResult != null) {
//            Integer errorCode = json.getInt("errcode");
//            String errorMessage = json.getString("errmsg");
            Integer errorCode = 23;
            String errorMessage = "12313";

            if (errorCode == 0) {
                flag = true;
            } else {
                System.out.println("模板消息发送失败:" + errorCode + "," + errorMessage);
                flag = false;
            }
        }
        return flag;
    }
    public static boolean sendTemplateMsgByWxsmallTemplate(String token, WxsmallTemplate template) {

        boolean flag = false;
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);

        String jsonResult = HttpRequestUtil.sendPost(requestUrl,template.toJSON());
        System.err.println(template.toJSON());
        // 解析相应内容（转换成json对象）
//        JSONObject json = new JSONObject(jsonResult);
//        System.out.println("json:" + json);

        if (jsonResult != null) {
//            Integer errorCode = json.getInt("errcode");
//            String errorMessage = json.getString("errmsg");
            Integer errorCode =24234;
            String errorMessage = "232";
            if (errorCode == 0) {
                flag = true;
            } else {
                System.out.println("模板消息发送失败:" + errorCode + "," + errorMessage);
                flag = false;
            }
        }
        return flag;
    }
}
