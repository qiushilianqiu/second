package com.gantang.entity.organization;

import lombok.Data;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.employee
 * @ClassName(类名称):Organization
 * @Title(标题):  Organization.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司 sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年8月29日 上午9:53:30   
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
@Data
public class Organization {
    private Integer orgId;

    private String code;

    private String name;

    private Integer parentId;

    private String tel;

    private String fax;

    private String address;

    private String printcipal;

    private String remark;

    private String flag;

    private String sortindex;

    private String companyflag;

    private String haschild;

    private String numberMax;

    private String orgType;

    private String orgIds;

    private String orgNames;
    private String costCenter;

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPrintcipal() {
        return printcipal;
    }

    public void setPrintcipal(String printcipal) {
        this.printcipal = printcipal == null ? null : printcipal.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getSortindex() {
        return sortindex;
    }

    public void setSortindex(String sortindex) {
        this.sortindex = sortindex == null ? null : sortindex.trim();
    }

    public String getCompanyflag() {
        return companyflag;
    }

    public void setCompanyflag(String companyflag) {
        this.companyflag = companyflag == null ? null : companyflag.trim();
    }

    public String getHaschild() {
        return haschild;
    }

    public void setHaschild(String haschild) {
        this.haschild = haschild == null ? null : haschild.trim();
    }

    public String getNumberMax() {
        return numberMax;
    }

    public void setNumberMax(String numberMax) {
        this.numberMax = numberMax == null ? null : numberMax.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds == null ? null : orgIds.trim();
    }

    public String getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(String orgNames) {
        this.orgNames = orgNames == null ? null : orgNames.trim();
    }
}