package com.gantang.entity.test;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.entity.test
 * @ClassName(类名称): test
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/24 17:13
 * @version(版本): V1.0
 * @Copyright(版权): www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):该类的具体作用 注意：本内容仅限于甘棠公司内部传阅，禁止外泄以及用于其他的商业目的的
 * ————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * ——————————————————————————————————————
 */
@TableName("sys_user")
@Data
public class Test extends Model<Test> {
    //
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    //座位编号
    @TableField("name")
    private String name;

    //座位地址
    @TableField("car_id")
    private String carId;
    @Override
    protected Serializable pkVal() {
        return null;
    }
}
