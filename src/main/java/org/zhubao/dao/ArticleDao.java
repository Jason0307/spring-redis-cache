package org.zhubao.dao;

import java.util.List;

import org.zhubao.model.Article;

/**
 * Article interface
 * @author jason.zhu
 *
 */
public interface ArticleDao extends BaseDao<Article>{

    /**
     * get user articles
     * @param userId
     * @return
     */
    public List<Article> findByUserId(int userId);
}
