package org.zhubao.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zhubao.model.Article;

@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test") 
public class TestArticleDao {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void testFindArticlesByUserId() {
        List<Article> articles = articleDao.findByUserId(1);
        for(Article article : articles) {
            System.out.println(article);
        }
    }
}
