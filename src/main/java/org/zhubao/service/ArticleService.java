package org.zhubao.service;

import java.util.List;

import org.zhubao.model.Article;

public interface ArticleService extends BaseService<Article>{

    /**
     * get user articles
     * @param userId
     * @return
     */
    public List<Article> findArticlesByUserId(int userId);
    
    /**
     * add article for user
     * @param article
     * @return
     */
    public Article addArticle(Article article);
}
