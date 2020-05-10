package com.gantang.common.wxsmallpost;

import java.util.List;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):
 * @Package(包名称) com.gantang.util.postMsg.WxsmallTemplate
 * @ClassName(类名称):WxsmallTemplate
 * @Title(标题):     
 * @see(与该类相关联的类):  
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/3/13 9:10   
 * @version(版本): V1.0 
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    小程序微信推送模版model
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
public class WxsmallTemplate {

    // 消息接收方
    private String toUser;
    // 模板id
    private String templateId;
    // 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转
    private String page;

    // 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
    private String form_id;

    // 模板内容字体的颜色，不填默认黑色 【废弃】
    private String color;
    // 模板需要放大的关键词，不填则默认无放大
    private String emphasis_keyword;

    // 参数列表
    private List<WxsmallTemplateParam> templateParamList;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    public List<WxsmallTemplateParam> getTemplateParamList() {
        return templateParamList;
    }

    public void setTemplateParamList(List<WxsmallTemplateParam> templateParamList) {
        this.templateParamList = templateParamList;
    }

    public String toJSON() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
        buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
        buffer.append(String.format("\"page\":\"%s\"", this.page)).append(",");
        buffer.append(String.format("\"form_id\":\"%s\"", this.form_id)).append(",");

        buffer.append("\"data\":{");
        WxsmallTemplateParam param = null;
        for (int i = 0; i < this.templateParamList.size(); i++) {
            param = templateParamList.get(i);
            // 判断是否追加逗号
            if (i < this.templateParamList.size() - 1) {

                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(),
                        param.getValue(), param.getColor()));
            } else {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(),
                        param.getValue(), param.getColor()));
            }

        }
        buffer.append("},");
        buffer.append(String.format("\"emphasis_keyword\":\"%s\"", this.emphasis_keyword));
        buffer.append("}");
        return buffer.toString();
    }
}
