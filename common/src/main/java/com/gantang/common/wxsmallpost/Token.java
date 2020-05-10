package com.gantang.common.wxsmallpost;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):
 * @Package(包名称) com.gantang.util.postMsg.Token
 * @ClassName(类名称):Token
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/3/13 9:16
 * @version(版本): V1.0
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  小程序推送token
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
public class Token {
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
