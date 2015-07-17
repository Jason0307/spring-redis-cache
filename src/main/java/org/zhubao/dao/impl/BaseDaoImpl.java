package org.zhubao.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhubao.dao.BaseDao;

/**
 * hibernate BaseDao Spring4 Hibernate4 is not support HibernateDaoSupport and
 * HibernateTemplate,use native API
 * 
 * @author Jason.Zhu
 * @date 2013-7-1 3:00:01
 * @email jasonzhu@augmentum.com.cn
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    @SuppressWarnings("rawtypes")
    private Class entityClass;
    private String pkname;
    private final String HQL_LIST_ALL;
    private final String HQL_COUNT_ALL;

    @SuppressWarnings("rawtypes")
    public Class getEntityClass() {
        return entityClass;
    }

    @SuppressWarnings("rawtypes")
    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("rawtypes")
    public BaseDaoImpl() {
        Type type = this.getClass().getGenericSuperclass();
        if (type.toString().indexOf("BaseDao") != -1) {
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] types = type1.getActualTypeArguments();
            setEntityClass((Class) types[0]);
        } else {
            type = ((Class) type).getGenericSuperclass();
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] types = type1.getActualTypeArguments();
            setEntityClass((Class) types[0]);
        }
        getPkname();
        HQL_LIST_ALL = "FROM " + this.entityClass.getSimpleName() + " ORDER BY " + pkname + " DESC";
        HQL_COUNT_ALL = "SELECT COUNT(*) FROM " + this.entityClass.getSimpleName();
    }

    /**
     * @return
     */
    public String getPkname() {
        Field[] fields = this.entityClass.getDeclaredFields();
        for (Field field : fields) {
            field.isAnnotationPresent(Id.class);
            this.pkname = field.getName();
            break;
        }
        return pkname;
    }

    /**
     * 
     * @param t
     * @throws HibernateException
     */
    public void save(T t) throws HibernateException {
        Session session = getSession();
        session.saveOrUpdate(t);
    }

    /**
     * 
     * @param t
     * @throws HibernateException
     */
    public void update(T t) throws HibernateException {
        Session session = getSession();
        session.update(t);
    }

    /**
     * 
     * @param t
     * @throws HibernateException
     */
    public void delete(T t) throws HibernateException {
        Session session = getSession();
        session.delete(t);
    }

    /**
     * 
     * @param id
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public T findById(Serializable id) {
        Session session = getSession();
        T t = null;
        t = (T) session.get(getEntityClass(), id);
        return t;
    }

    /**
     * 
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Session session = getSession();
        Query query = session.createQuery(HQL_LIST_ALL);
        list = query.list();
        return list;
    }

    /**
     * 
     * @throws HibernateException
     */
    public int findAllCount() {
        int count = 0;
        Session session = getSession();
        Query query = session.createQuery(HQL_COUNT_ALL);
        List<?> list = query.list();
        if (list != null && !list.isEmpty()) {
            count = ((Integer) list.get(0)).intValue();
        }
        return count;
    }

    /**
     * 
     * @param criteria
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criteria criteria) {
        List<T> list = new ArrayList<T>();
        Session session = getSession();
        Criteria criteria1 = session.createCriteria(getEntityClass());
        criteria1 = criteria;
        list = criteria1.list();
        return list;
    }

    /**
     * 
     * @param t
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T t) {
        List<T> list = new ArrayList<T>();
        Session session = getSession();
        Example example = Example.create(t);
        Criteria criteria = session.createCriteria(getEntityClass());
        criteria.add(example);
        list = criteria.list();
        return list;
    }

    /**
     * 
     * @param hql
     * @param objects
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> findByHql(String hql, final Object... objects) {
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = getSession();
        Query query = session.createQuery(hql);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        list = query.list();
        return list;
    }

    /**
     * SQL
     * 
     * @param hql
     * @param objects
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> findBySql(String sql, final Object... objects) {
        List<Object[]> list = new ArrayList<Object[]>();
        Session session = getSession();
        Query query = session.createSQLQuery(sql);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        list = query.list();
        return list;
    }
}
