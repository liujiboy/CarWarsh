package cn.edu.cqu.carwarsh.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;


public class BaseService {
	private HibernateTemplate ht;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		ht = new HibernateTemplate(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> find(Class<T> entityClass,String queryString, Object... objs)
	{
		
		List<T> list=(List<T>) ht.find(queryString,objs);

		if(list==null)
			return new ArrayList<T>();
		else
			return list;
	}
	
	
	protected <T> T get(Class<T> entityClass,Serializable id)
	{
		
		return ht.get(entityClass, id);
	}
	protected<T> void update(T obj)
	{
		 ht.update(obj);
	}
	protected<T> void delete(T obj)
	{
		 ht.delete(obj);
	}
	protected<T> void save(T obj)
	{
		ht.save(obj);
	}
	protected <T> T getFirst(Class<T> entityClass,String queryString, Object... objs)
	{
		List<T> list=find(entityClass,queryString,objs);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	protected <T> List<T> getAll(Class<T> entityClass,String queryString, Object... objs)
	{
		List<T> list=find(entityClass,queryString,objs);
			return list;
	}
	protected Long getCount(String queryString, Object... objs)
	{
		Long count=getFirst(Long.class,queryString,objs);
		return count==null?0:count;
	}

}
