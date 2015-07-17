package org.zhubao.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.zhubao.dao.ArticleDao;
import org.zhubao.model.Article;

@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findByUserId(int userId) {
        List<Article> articles = new ArrayList<>();
        Session session = getSession();
        Query query = session
                .createQuery("FROM Article a INNER JOIN FETCH a.user WHERE a.user.userId = ?")
                .setInteger(0, userId);
        articles = query.list();
        return articles;
    }

}
