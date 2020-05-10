package com.gantang.entity.management;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user")
public class SysUser extends Model<SysUser>  {
	private static final long serialVersionUID = 1L;
	
	    //
    @TableId(value="id",type=IdType.AUTO)
    private Integer id;
	
	    //
    @TableField("name")
    private String name;
	
	    //
    @TableField("car_id")
    private String carId;
	
	    //修改人
    @TableField("modifier")
    private Integer modifier;
	
	    //修改时间
    @TableField("modifier_time")
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