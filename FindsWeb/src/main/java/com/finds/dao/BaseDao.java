package com.finds.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.finds.entity.BaseEntity;
import com.finds.entity.Taken;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;


public interface BaseDao {
	 /**
     * +[增加] 1对象
     */
     <T extends BaseEntity> void save(T obj);

    /**
     * +[增加] n对象
     */
     <T extends BaseEntity> void save(List<T> objs);

    /**
     * U[更新] 1对象
     */
     <T extends BaseEntity> void update(T obj);

    /**
     * U[更新] n对象
     */
     <T extends BaseEntity> void update(List<T> objs);

    /**
     * U[更新] 1对象,不存在则添加
     */
     <T extends BaseEntity> void saveOrUpdate(T obj);

    /**
     * U[更新] n对象,不存在则添加
     */
     <T extends BaseEntity> void saveOrUpdate(List<T> objs);

    /**
     * -[删除] 1对象
     */
     <T extends BaseEntity> void delete(T obj);

    /**
     * -[删除] n对象
     */
     <T extends BaseEntity> void delete(List<T> listObjs);

    /**
     * -[删除] 1对象(按主键）
     */
     <T extends BaseEntity, PK extends Serializable> void delete(Class<T> type, PK id);

    /**
     * -[删除] n对象(按主键）
     */
     <T extends BaseEntity, PK extends Serializable> void delete(Class<T> type, List<PK> ids);

    /**
     * S[查询] 1对象
     */
     <T extends BaseEntity, PK extends Serializable> T get(Class<T> type, PK id);

    /**
     * S[查询] n对象
     */
     <T extends BaseEntity> List<T> getAll(Class<T> type);

    /**
     * S[查询] n对象（条件）
     */
     <T extends BaseEntity> List<T> getAll(Class<T> type, List<Criterion> criterions);

    /**
     * S[查询] n对象（条件、排序）
     */
     <T extends BaseEntity> List<T> getAll(Class<T> type, List<Criterion> criterions, List<Order> orders);

    /**
     * S[分页查询] n对象
     */
     <T extends BaseEntity> List<T> getPage(Class<T> type, int start, int limit);

    /**
     * S[分页查询] n对象（条件）
     */
     <T extends BaseEntity> List<T> getPage(Class<T> type, int start, int limit, List<Criterion> criterions);

    /**
     * S[分页查询] n对象（条件，排序）
     */
     <T extends BaseEntity> List<T> getPage(Class<T> type, int start, int limit, List<Criterion> criterions, List<Order> orders);

    /**
     * S[查询] 总记录数
     */
     long getTotalCount(Class type);

    /**
     * S[查询] 总记录数 (条件）
     */
     long getTotalCount(Class type, List<Criterion> criterions);

    /**
     * 查询分组数据
     */

    <T extends BaseEntity> Object getProjection(Class<T> type, List<Criterion> criterions, ProjectionList projectionList);
    /**
      通过sql语句查询
       */
    public List getBySql(String sql,Map<String,?> values);

    /**
    通过SQL分页查询
     */
    public List getBySQL(String sql,Integer start,Integer limit,final Map<String, ?> values);
    /**
    通过SQL查询总数
     */
    public long getTotalCountBySql(String sql,final Map<String, ?> values);
    public <T extends BaseEntity> Map pagerByCriterion(Class<T> type, Integer pageNo, Integer pageSize,
                                                       List<Criterion> criterions, List<Order> orders);
    
    public List findbysqllist(String sql, int pagenum, int pagesize,
                              final Map<String, ?> values);

    public String findUser_id(String taken);
   
}
