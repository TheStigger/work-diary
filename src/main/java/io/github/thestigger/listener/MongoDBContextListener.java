package io.github.thestigger.listener;

import com.mongodb.MongoClient;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.UnknownHostException;

/**
 * Created by maxim on 10/14/15.
 */
@WebListener
public class MongoDBContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        MongoClient mongo = (MongoClient) servletContextEvent.getServletContext()
                .getAttribute("MONGO_CLIENT");
        mongo.close();
        System.out.println("MongoClient closed successfully");
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
            ServletContext context = servletContextEvent.getServletContext();
            MongoClient mongo = new MongoClient(
                    context.getInitParameter("MONGODB_HOST"),
                    Integer.parseInt(context.getInitParameter("MONGODB_PORT")));

            System.out.println("MongoClient initialized successfully");
            servletContextEvent.getServletContext().setAttribute("MONGO_CLIENT", mongo);
    }
}
