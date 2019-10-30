package coredemo.domain;

import coredemo.model.Article;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MockArticleProvider implements ArticleProvider {

    @Override
    public List<Article> getArticles() {
        return Arrays.asList(
                new Article("Welcome to Spring 5", "Spring 5 course 2019"),
                new Article("Welcome to Spring 5", "Spring 5 course 2018"),
                new Article("Welcome to Spring 5", "Spring 5 course 2017")
        );
    }

}
