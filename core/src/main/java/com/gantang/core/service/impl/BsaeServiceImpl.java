package com.gantang.core.service.impl;


import com.gantang.common.redis.RedisUtil;
import com.gantang.common.util.CountUtil;
import com.gantang.common.util.F;
import com.gantang.common.util.MyBeanUtil;
import com.gantang.common.util.StrUtils;
import com.gantang.core.mapper.BaseCommonMapper;
import com.gantang.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):Baseservice
 * @Package(包名称) Baseservice.
 * @ClassName(类名称):BaseService
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/6/14 10:59
 * @version(版本): V1.0
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    (用一句话描述该文件做什么)
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
@Service
public class BsaeServiceImpl implements BaseService {
    @Autowired
    BaseCommonMapper baseCommonMapper;
    @Autowired
    RedisUtil redisUtil;

    /**拼接查询条件
     * <p>Title: load</p>
     * <p>Description: </p>
     * @param tableName
     * @param fs
     * @return
     */
    @Override
    public List<Map<String, Object>> load(String tableName, F[] fs) {
        Map<String, String> map=new HashMap<String, String>(100);
        List<Map<String, Object>> modelList=new ArrayList<Map<String, Object>>(100);
        for(F f:fs) {
            map.put(f.getField(), f.getParam().toString());
        }
        modelList=baseCommonMapper.select(CountUtil.mapToList(map),tableName);
        return modelList;
    }
    /**根据条件查出单条记录
     * <p>Title: load</p>
     * <p>Description: </p>
     * @param tableName
     * @param fieldParam
     * @return
     */
    @Override
    public Map<String, String> loadMap(String tableName, F[] fieldParam) {
        Map<String, String> map=new  HashMap<String, String>(100);
        Map<String, String> mapResult=new HashMap<String, String>();
        for(F f:fieldParam) {
            map.put(f.getField(), f.getParam().toString());
        }
        List<Object> objects = CountUtil.mapToList(map);
        MyBeanUtil.mapObjectToMapString(baseCommonMapper.selectByWhere(objects, tableName), mapResult);
        return mapResult;
    }
    /**根据id查询单条记录
     * <p>Title: loadMap</p>
     * <p>Description: </p>
     * @param tableName
     * @param fs
     * @return
     */
    @Override
    public Map<String, String> loadMap(String tableName, F fs) {
        Map<String, String> mapResult=new HashMap<String, String>();
        MyBeanUtil.mapObjectToMapString(baseCommonMapper.selectById(tableName, Integer.parseInt( fs.getParam().toString())), mapResult);
        return mapResult;
    }

    /**插入
     * <p>Title: insert</p>
     * <p>Description: </p>
     * @param tableName
     * @param fieldParam
     * @return
     */
    @Override
    public int insert(String tableName, F... fieldParam) {
        Map<String, String> map=new  HashMap<String, String>(100);
        for(F f:fieldParam) {
            if(StrUtils.isNotNullStr(f.getParam().toString())){
                map.put(f.getField(), f.getParam().toString());
            }
        }
        int num =baseCommonMapper.save(map, tableName);
        return num;
    }



    /** 更新所有表数据
     * <p>Title: updateByFieldParam</p>
     * <p>Description: </p>
     * @param tableName
     * @param fieldParam
     * @return
     *
     */
    @Override
    public int updateByFieldParam(String tableName, F... fieldParam) {
        Map<String, String> map=new  HashMap<String, String>(100);
        for(F f:fieldParam) {
            map.put(f.getField(), f.getParam().toString());
        }
        int num =baseCommonMapper.updateAll(tableName, CountUtil.mapToList(map));
        return num;
    }

    /**条件更新
     * <p>Title: updateByFieldParam</p>
     * <p>Description: </p>
     * @param tableName
     * @param fieldParamUpdate
     * @param fieldParamWhere
     * @return
     */
    @Override
    public int updateByFieldParam(String tableName, F[] fieldParamUpdate, F[] fieldParamWhere) {
        Map<String, String> mapParamUpdate=new  HashMap<String, String>(100);
        for(F f:fieldParamUpdate) {
            mapParamUpdate.put(f.getField(), f.getParam().toString());
        }
        Map<String, String> mapParamWhere=new  HashMap<String, String>(100);
        for(F f:fieldParamWhere) {
            mapParamWhere.put(f.getField(), f.getParam().toString());
        }
        int num =baseCommonMapper.updateByWhere(tableName,CountUtil.mapToList(mapParamUpdate),CountUtil.mapToList(mapParamWhere));
        return num;
    }

    /**删除根据id
     * <p>Title: delete</p>
     * <p>Description: </p>
     * @param tableName
     * @param fs
     * @return
     */
    @Override
    public int delete(String tableName, F fs) {
        int num=baseCommonMapper.deleteById(tableName, Integer.parseInt( fs.getParam().toString()));
        return num;
    }
    /**删除根据id
     * <p>Title: delete</p>
     * <p>Description: </p>
     * @param tableName
     * @param fs
     * @return
     */
    @Override
    public int delete(String tableName, F[] fs) {
        Map<String, String> map=new  HashMap<String, String>(100);
        for(F f:fs) {
            map.put(f.getField(), f.getParam().toString());
        }
        int num =baseCommonMapper.deleteByWhere( tableName,CountUtil.mapToList(map));
        return num;
    }
    /*	*//**
     * <p>Title: queryMap</p>
     * <p>Description: </p>
     * @param sql
     * @param values
     * @return
     * @see com.gantang.service.base.BaseService#queryMap(String, Object[])
     *//*
	@Override
	public List<Map<String, Object>> queryMap(String sql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	*//**
     * <p>Title: queryMap</p>
     * <p>Description: </p>
     * @param tableName
     * @param fieldParam
     * @return
     * @see com.gantang.service.base.BaseService#queryMap(String, com.gantang.util.F[])
     *//*
	@Override
	public List<Map<String, Object>> queryMap(String tableName, F... fieldParam) {
		// TODO Auto-generated method stub
		return null;
	}

	*//**
     * <p>Title: executeCall</p>
     * <p>Description: </p>
     *//*
	@Override
	public void executeCall(String sql) {
		// TODO Auto-generated method stub

	}*/



}
