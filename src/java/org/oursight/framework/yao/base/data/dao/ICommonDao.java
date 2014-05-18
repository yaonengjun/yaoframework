package org.oursight.framework.yao.base.data.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.HibernateException;
import org.oursight.framework.yao.base.exception.CommonException;

public interface ICommonDao {
	public abstract void insert(Object obj) throws CommonException;

	public abstract void delete(Object obj) throws CommonException;

	public abstract Object find(Class class1, Serializable serializable) throws CommonException;

	/**
	 * 根据ID来查找对象。 要求传入的对象必须有名为id的字段并且已经赋值s
	 * 
	 * @return
	 * @throws CommonException
	 */
	public abstract Object findById(Object object) throws CommonException;

	public abstract Object findByHQL(String hql) throws CommonException;

	public abstract Object findObject(Object object) throws CommonException;

	public abstract Object findObject(Object object, String[] orderField, boolean[] isAsc) throws CommonException;

	public abstract void load(Object obj, Serializable serializable) throws CommonException;

	public abstract List find(Class class1) throws CommonException;

	public abstract void update(Object obj) throws CommonException;

	public abstract int count(Class class1) throws CommonException;

	public abstract int queryCount(Object object, String[] orderField, boolean[] isAsc) throws HibernateException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	public abstract List queryList(Object object, String[] orderField, boolean[] isAsc) throws HibernateException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	public abstract List queryList(Object object, int firstResult, int maxResults, String[] orderField, boolean[] isAsc)
			throws HibernateException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	public abstract List quertList(String HQL) throws CommonException;
}
