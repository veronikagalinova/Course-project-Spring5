package coredemo.domain;

public class ConsoleArticlePresenter implements ArticlePresenter {

    private ArticleProvider provider;

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
