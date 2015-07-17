package org.zhubao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zhubao.dao.ArticleDao;
import org.zhubao.model.Article;
import org.zhubao.service.ArticleService;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    private ArticleDao articleDao;

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        super.setBaseDao(articleDao);
        this.articleDao = articleDao;
    }

    @Override
    @Cacheable(value = "articleCache", key = "#userId + 'articles'")
    public List<Article> findArticlesByUserId(int userId) {
        System.out.println("No cache go here!" + " ====== findArticlesByUserId");
        return articleDao.findByUserId(userId);
    }

    @Override
    @CacheEvict(value = "articleCache", key = "#article.user.userId + 'articles'")
    public Article addArticle(Article article) {
        articleDao.save(article);
        return article;
    }

}
