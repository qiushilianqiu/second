package com.gantang.common.wxsmallpost;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):
 * @Package(包名称) .  com.gantang.util.postMsg.WxsmallTemplateParam
 * @ClassName(类名称):WxsmallTemplateParam
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/3/13 9:14
 * @version(版本): V1.0
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    微信推送模版model
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
public class WxsmallTemplateParam {
    // 参数名称
    private String name;
    // 参数值
    private String value;
    // 颜色  废弃了哎。。。。。。。。。。。。。。。。。。。
    private String color;

    public WxsmallTemplateParam(String name, String value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public WxsmallTemplateParam(String name, String value) {
        this.name = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}