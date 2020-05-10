package com.gantang.entity.management;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@TableName("order")
public class Order extends Model<Order> {
    private static final long serialVersionUID = 1L;

    //订单ID
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //订单编号
    @TableField("code")
    private String code;

    //订单类型
    @TableField("type")
    private String type;

    //修改人
    @TableField("modifier")
    private Integer modifier;

    //修改时间
    @TableField("modify_time")
    private String modifyTime;

    //创建人
    @TableField("creater")
    private Integer creater;

    //创建时间
    @TableField("create_time")
    private String createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}