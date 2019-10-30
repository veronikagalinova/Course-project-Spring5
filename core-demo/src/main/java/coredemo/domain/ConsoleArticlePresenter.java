package coredemo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("presenter")
public class ConsoleArticlePresenter implements ArticlePresenter {

    /**
     *  field based injection
     */
    private ArticleProvider provider;

    /**
     * constructor based injection
     */
    @Autowired
    public ConsoleArticlePresenter(ArticleProvider provider) {
        this.provider = provider;
    }

    @Override
    public void present() {
        provider.getArticles().forEach(System.out::println);
    }

    public static ArticlePresenter createPresenter(ArticleProvider provider) {
        return new ConsoleArticlePresenter(provider);
    }

    public void setProvider(ArticleProvider pr) {
        this.provider = pr;
    }

    public ArticleProvider getProvider() {
        return provider;
    }
}
