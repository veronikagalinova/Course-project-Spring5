package coredemo.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringDI {


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        ArticlePresenter  presenter= ctx.getBean("presenter", ArticlePresenter.class);

        presenter.present();
    }




}
