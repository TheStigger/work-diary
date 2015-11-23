package io.github.thestigger;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by maxim on 11/19/15.
 */
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // create the root Spring application context
        AnnotationConfigWebApplicationContext mongoContext = new AnnotationConfigWebApplicationContext();
        mongoContext.register(SpringMongoConfiguration.class);

        // Manager the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(mongoContext));
    }
}
