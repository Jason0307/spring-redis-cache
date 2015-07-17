/**
 * 
 */
package org.zhubao.service.impl;

import java.io.Serializable;
import java.util.List;

import org.zhubao.dao.BaseDao;
import org.zhubao.service.BaseService;

/**
 * 
 * @author jason.zhu
 *
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    protected BaseDao<T> baseDao;

    public void save(T t) {
        baseDao.save(t);
    }

    public void update(T t) {
        baseDao.update(t);
    }

    public void delete(T t) {
        baseDao.delete(t);
    }

    public T findById(Serializable id) {
        return baseDao.findById(id);
    }
    
    public List<T> findAll() {
        return baseDao.findAll();
    }

    public List<T> findByExample(T t) {
        return baseDao.findByExample(t);
    }

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

}
