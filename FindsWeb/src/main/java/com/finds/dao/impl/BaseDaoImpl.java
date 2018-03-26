package com.finds.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.finds.dao.BaseDao;
import com.finds.entity.BaseEntity;
import com.finds.entity.Taken;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("baseDao")
@Transactional
public class BaseDaoImpl implements BaseDao {
	
	@Autowired
	SessionFactory sessionFactory;
	Session session;

	/**
	 * +[增加] 1对象
	 */
	public <T extends BaseEntity> void save(T obj) {
		getSession().save(obj);
	}

	/**
	 * +[增加] n对象
	 */
	public <T extends BaseEntity> void save(List<T> objs) {
		for (T obj : objs)
			getSession().save(obj);
	}

	/**
	 * U[更新] 1对象
	 */
	public <T extends BaseEntity> void update(T obj) {
		getSession().update(obj);
	}

	/**
	 * U[更新] n对象
	 */
	public <T extends BaseEntity> void update(List<T> objs) {
		for (T obj : objs)
			getSession().update(obj);
	}

	/**
	 * U[更新] 1对象,不存在则添加
	 */
	public <T extends BaseEntity> void saveOrUpdate(T obj) {
		getSession().saveOrUpdate(obj);
	}

	/**
	 * U[更新] n对象,不存在则添加
	 */
	public <T extends BaseEntity> void saveOrUpdate(List<T> objs) {
		for (T obj : objs)
			getSession().saveOrUpdate(obj);
	}

	/**
	 * -[删除] 1对象
	 */
	public <T extends BaseEntity> void delete(T obj) {
		getSession().delete(obj);
	}

	/**
	 * -[删除] n对象
	 */
	public <T extends BaseEntity> void delete(List<T> listObjs) {
		for (T obj : listObjs)
			getSession().delete(obj);
	}

	/**
	 * -[删除] 1对象(按主键）
	 */
	public <T extends BaseEntity, PK extends Serializable> void delete(Class<T> type, PK id) {
		getSession().delete(get(type, id));
	}

	/**
	 * -[删除] n对象(按主键）
	 */
	public <T extends BaseEntity, PK extends Serializable> void delete(Class<T> type, List<PK> ids) {
		for (PK id : ids)
			getSession().delete(get(type, id));
	}

	/**
	 * S[查询] 1对象
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity, PK extends Serializable> T get(Class<T> type, PK id) {
		return (T) getSession().get(type, id);
	}

	/**
	 * S[查询] n对象
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity> List<T> getAll(Class<T> type) {
		return getSession().createCriteria(type).list();
	}

	/**
	 * S[查询] n对象（条件）
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity> List<T> getAll(Class<T> type, List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(type);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria.list();
	}

	/**
	 * S[查询] n对象（条件、排序）
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity> List<T> getAll(Class<T> type, List<Criterion> criterions, List<Order> orders) {
		Criteria criteria = getSession().createCriteria(type);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}

		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	/**
	 * S[分页查询] n对象
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity> List<T> getPage(Class<T> type, int start, int limit) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}

	/**
	 * S[分页查询] n对象（条件）
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity> List<T> getPage(Class<T> type, int start, int limit, List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria.list();
	}

	/**
	 * S[分页查询] n对象（条件，排序）
	 */
	@Transactional(readOnly = true)
	public <T extends BaseEntity> List<T> getPage(Class<T> type, int start, int limit, List<Criterion> criterions,
			List<Order> orders) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);

		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}

		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	/**
	 * S[查询] 总记录数
	 */
	@Transactional(readOnly = true)
	public long getTotalCount(Class type) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setProjection(Projections.rowCount());
		return (Long) (criteria.list().get(0));
	}

	/**
	 * S[查询] 总记录数 (条件）
	 */
	@Transactional(readOnly = true)
	public long getTotalCount(Class type, List<Criterion> criterions) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setProjection(Projections.rowCount());
		for (Criterion criterion : criterions)
			criteria.add(criterion);

		return (Long) (criteria.uniqueResult());
	}

	/**
	 * 查询单个分组数据（分组列表)
	 *
	 * @param type
	 *            model的class
	 * @param projection
	 *            分组
	 */
	public <T extends BaseEntity> Object getProjection(Class<T> type, ProjectionList projection) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setProjection(Projections.projectionList().add(projection));
		Object res = criteria.uniqueResult();
		return res == null ? 0 : res;
	}

	/**
	 * 查询单个分组数据（条件，分组列表)
	 *
	 * @param type
	 *            model的class
	 * @param criterions
	 *            条件
	 * @param projection
	 *            分组
	 */
	public <T extends BaseEntity> Object getProjection(Class<T> type, List<Criterion> criterions,
			ProjectionList projection) {
		Criteria criteria = getSession().createCriteria(type);
		criteria.setProjection(Projections.projectionList().add(projection));
		for (Criterion criterion : criterions)
			criteria.add(criterion);

		Object res = criteria.uniqueResult();
		return res == null ? 0 : res;
	}
	
	
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends BaseEntity> Map pagerByCriterion(Class<T> type, Integer pageNo, Integer pageSize, 
			List<Criterion> criterions,List<Order> orders) {
		Map map = new HashMap();
		
		Criteria criteria = getSession().createCriteria(type);
		if (pageNo!=null && pageSize!=null && pageNo != 0 && pageSize != 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		
		Long totalcount;
		if(criterions!=null && criterions.size()>0){
			totalcount = this.getTotalCount(type, criterions);
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
		}else{
			totalcount = this.getTotalCount(type);
		}

		if(orders!=null && orders.size()>0){
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		
		List<T> list = criteria.list();
		
		map.put("root", list);
		
		long page = 1;
		if(totalcount%pageSize==0){
			page = (totalcount/pageSize);
		}else{
			page = (totalcount/pageSize)+1;
		}
		
		long start = (pageNo-1)*pageSize+1;
		long end = pageNo>=page? totalcount:pageNo*pageSize;
		
		List<Integer> pagelist = new ArrayList<Integer>();
		int showp = 4;
		for(int i=showp; i>=0; i--){
			if((pageNo-i)>0)
				pagelist.add(pageNo-i);
		}
		
		for(int j=1; j<showp+1; j++){
			if((pageNo+j)<=page)
				pagelist.add(pageNo+j);
		}
		
		map.put("totalcount", totalcount);
		map.put("page", page);
		map.put("pageNo", pageNo);
		map.put("start", start);
		map.put("end", end);
		map.put("pagelist", pagelist);
		
		return map;
	}
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public List findbysqllist(String sql, int pagenum, int pagesize,
			final Map<String, ?> values) {
		List list = new ArrayList();

		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			query.setProperties(values);
		}
		if (pagenum != 0 && pagesize != 0) {
			query.setFirstResult((pagenum - 1) * pagesize);
			query.setMaxResults(pagesize);
		}
		list = query.list();

		return list; 
	}

	public String findUser_id(String taken) {
		try {
			return ((Taken)getSession().get(Taken.class,taken)).getUser_id();
		}
		catch (Exception e){
			return null;
		}

	}

	public List getBySQL(String sql,Integer start,Integer limit,final Map<String, ?> values){

		Query query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			query.setProperties(values);
		}
		if(start!=null && limit!=null && start>=0 && limit>0){
			query.setFirstResult(start);
			query.setMaxResults(limit);
		}

		return query.list();

	}
	public List getBySql(String sql,Map<String,?> values){
		Query query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			query.setProperties(values);
		}
		return query.list();
	}

	public long getTotalCountBySql(String sql,final Map<String, ?> values){
		Query query = getSession().createSQLQuery(sql);
		if (values != null) {
			query.setProperties(values);
		}
		return Long.valueOf(query.uniqueResult()+"");
	}


	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
