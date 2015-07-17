package org.zhubao.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhubao.model.Article;
import org.zhubao.model.User;
import org.zhubao.service.ArticleService;
import org.zhubao.service.UserService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user/{userId}/article/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getUserArticles(@PathVariable ("userId") int userId) {
        return articleService.findArticlesByUserId(userId);
    }
    
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public Article findUserByName(@PathVariable ("articleId") int articleId) {
       // return articleService.findByArticleId(articleId);
        return null;
    }
    
    @RequestMapping(value = "/user/{userId}/article/add", method = RequestMethod.GET)
    @ResponseBody
    public Article addUser(@PathVariable ("userId") int userId) {
        Article article = new Article();
        article.setTitle("五月天");
        article.setContent("你不是真正的快乐");
        
        User user = userService.findById(userId);
        article.setUser(user);
        article.setDateCreated(new Date());
        return articleService.addArticle(article);
    }
    
    
}
