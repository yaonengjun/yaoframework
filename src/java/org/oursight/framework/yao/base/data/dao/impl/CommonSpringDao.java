package org.oursight.framework.yao.base.data.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.util.StringHelper;
import org.oursight.framework.yao.base.data.dao.ICommonDao;
import org.oursight.framework.yao.base.exception.CommonException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonSpringDao extends HibernateDaoSupport implements ICommonDao {

	private static final Logger LOG = Logger.getLogger(CommonSpringDao.class);

	public CommonSpringDao() {
	}

	public void insert(Object obj) throws CommonException {
		getHibernateTemplate().save(obj);
	}

	public void delete(Object obj) throws CommonException {
		getHibernateTemplate().delete(obj);
	}

	public Object find(Class class1, Serializable serializable) throws CommonException {
		return getHibernateTemplate().load(class1, serializable);
	}

	public List find(Class class1) throws CommonException {
		return getHibernateTemplate().find("from " + class1.getName() + " obj");
	}

	public void load(Object obj, Serializable serializable) throws CommonException {
		getHibernateTemplate().load(obj, serializable);
	}

	public void update(Object obj) throws CommonException {
		getHibernateTemplate().update(obj);
	}

	public int count(Class class1) throws CommonException {
		List list = getHibernateTemplate().find("from " + class1.getName() + " obj");
		return list.size();
	}

	public int queryCount(Object object, String[] orderField, boolean[] isAsc) throws HibernateException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List list = queryList(object, orderField, isAsc);
		return list.size();
	}

	public List queryList(Object object, String[] orderField, boolean[] isAsc) throws HibernateException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		return queryList(object, 0, -1, orderField, isAsc);
	}

	public List queryList(Object object, int firstResult, int maxResults, String[] orderField, boolean[] isAsc)
			throws HibernateException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List list = null;
		Class c = object.getClass();
		Method method[] = c.getMethods();
		Session session = null;
		try {
			// SessionFactory sessionFactory = this.getSessionFactory();
			// session = sessionFactory.openSession();
			// session = sessionFactory.getCurrentSession();
			session = this.getSession();
			Criteria criteria = session.createCriteria(object.getClass());
			for (int i = 0; i < method.length; i++) {
				String name = method[i].getName();

				if (name.equals("getPageNumber"))
					continue;

				if (name.indexOf("getMin") == 0) { // ����
					String fieldName = name.substring(6, 7).toLowerCase() + name.substring(7);
					Object retObj = method[i].invoke(object, null);
					if (retObj != null && !retObj.equals(""))
						criteria.add(Expression.ge(fieldName, retObj));
					continue;
				}
				if (name.indexOf("getMax") == 0) {// С��
					String fieldName = name.substring(6, 7).toLowerCase() + name.substring(7);
					Object retObj = method[i].invoke(object, null);
					if (retObj != null && !retObj.equals(""))
						criteria.add(Expression.le(fieldName, retObj));
					continue;
				}
				if (name.indexOf("get") != 0 || name.indexOf("getClass") == 0) // �������Ҫ�ķ��������
					continue;
				String fieldName = name.substring(3, 4).toLowerCase() + name.substring(4);
				String returnType = method[i].getReturnType().toString();
				Object retObj = method[i].invoke(object, null);

				if (returnType.equals("int") && (Integer) retObj == 0)
					continue;

				if (returnType.equals("long") && (Long) retObj == 0)
					continue;

				if (fieldName.equals("id") && retObj.equals(0))
					continue;
				// if (fieldName.equals("id") && !retObj.equals(0))
				// criteria.add(Expression.eq(fieldName, retObj));

				if (retObj != null) { // ���Ϊnull��û�и�ֵ�����
					if (returnType.indexOf("String") != -1) {
						if (retObj.equals("")) // ���Ϊ""��String�ֶΣ����
							continue;
						criteria.add(Expression.like(fieldName, "%" + retObj + "%")); // ��String���ֶΣ�ʹ��likeģ���ѯ
					} else {
						criteria.add(Expression.eq(fieldName, retObj));
					}

				}

			}
			if (orderField != null) {
				for (int i = 0; i < orderField.length; i++) {
					if (isAsc[i])
						criteria.addOrder(Order.asc(orderField[i])); // ����
					else
						criteria.addOrder(Order.desc(orderField[i])); // ����
				}
			}
			criteria.setFirstResult(firstResult);
			if (maxResults >= 0)
				criteria.setMaxResults(maxResults);
			list = criteria.list();
			LOG.info("criteria: " + criteria.toString());
		} finally {
			// session.close();
		}
		return list;
	}

	public Object findByHQL(String hql) {
		List list = null;

		if (StringHelper.isEmpty(hql)) {
			return null;
		}
		Session session = this.getSession(true);
		try {
			Query query = session.createQuery(hql);
			list = query.list();
			return (Object) list.get(0);

		} catch (HibernateException e) {
			LOG.error("hql=" + hql, e);
			return null;
		} finally {
			// Hb3Util.closeSession(session);
		}

	}

	public Object findObject(Object object, String[] orderField, boolean[] isAsc) throws CommonException {
		List list = null;

		try {
			list = queryList(object, 0, -1, orderField, isAsc);
		} catch (Exception e) {
			LOG.error("query object error", e);
			e.printStackTrace();
		}

		if (list == null || list.size() == 0)
			return null;

		return (Object) list.get(0);
	}

	public Object findObject(Object object) throws CommonException {
		return findObject(object, new String[] { "id" }, new boolean[] { false });
	}

	/**
	 * ��ϲ�ѯ
	 * 
	 * @param object
	 *            ���ѯ����Ķ���
	 * @param firstResult
	 *            ��һ��ص�λ��(��0��ʼ)
	 * @param maxResults
	 *            ��󷵻���
	 * @param orderField
	 *            ������ֶ�
	 * @param isAbs
	 *            �Ƿ���������
	 * @return
	 * @throws HibernateException
	 */
	public List queryListEq(Object object, int firstResult, int maxResults, String[] orderField, boolean[] isAsc)
			throws HibernateException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List list = null;
		Class c = object.getClass();
		Method method[] = c.getMethods();
		Session session = null;
		try {
			// SessionFactory sessionFactory = this.getSessionFactory();
			// session = sessionFactory.openSession();
			// session = sessionFactory.getCurrentSession();
			session = this.getSession();
			Criteria criteria = session.createCriteria(object.getClass());
			for (int i = 0; i < method.length; i++) {
				String name = method[i].getName();

				if (name.equals("getPageNumber"))
					continue;

				if (name.indexOf("getMin") == 0) { // ����
					String fieldName = name.substring(6, 7).toLowerCase() + name.substring(7);
					Object retObj = method[i].invoke(object, null);
					if (retObj != null && !retObj.equals(""))
						criteria.add(Expression.ge(fieldName, retObj));
					continue;
				}
				if (name.indexOf("getMax") == 0) {// С��
					String fieldName = name.substring(6, 7).toLowerCase() + name.substring(7);
					Object retObj = method[i].invoke(object, null);
					if (retObj != null && !retObj.equals(""))
						criteria.add(Expression.le(fieldName, retObj));
					continue;
				}
				if (name.indexOf("get") != 0 || name.indexOf("getClass") == 0) // �������Ҫ�ķ��������
					continue;
				String fieldName = name.substring(3, 4).toLowerCase() + name.substring(4);
				String returnType = method[i].getReturnType().toString();
				Object retObj = method[i].invoke(object, null);

				if (retObj != null) { // ���Ϊnull��û�и�ֵ�����
					if (returnType.indexOf("String") != -1) {
						if (retObj.equals("")) // ���Ϊ""��String�ֶΣ����
							continue;
						criteria.add(Expression.eq(fieldName, retObj)); // ��String���ֶΣ�ʹ��likeģ���ѯ
					} else {
						criteria.add(Expression.eq(fieldName, retObj));
					}

				}

			}
			if (orderField != null) {
				for (int i = 0; i < orderField.length; i++) {
					if (isAsc[i])
						criteria.addOrder(Order.asc(orderField[i]));
					else
						criteria.addOrder(Order.desc(orderField[i]));
				}
			}
			criteria.setFirstResult(firstResult);
			if (maxResults >= 0)
				criteria.setMaxResults(maxResults);
			list = criteria.list();
			LOG.info("criteria: " + criteria.toString());
		} finally {
			// session.close();
		}
		return list;
	}

	public List quertList(String hql) throws CommonException {
		List list = null;

		if (StringHelper.isEmpty(hql)) {
			return null;
		}
		Session session = this.getSession(true);
		try {
			Query query = session.createQuery(hql);
			list = query.list();
			return list;

		} catch (HibernateException e) {
			LOG.error("hql=" + hql, e);
			return null;
		} finally {
			Hb3Util.closeSession(session);
		}
	}

	public Object findById(Object object) throws CommonException {
		return findObject(object, new String[] { "id" }, new boolean[] { false });
	}
}