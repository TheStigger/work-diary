package io.github.thestigger;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by maxim on 11/22/15.
 */
@Configuration
@ComponentScan(basePackages = "io.github.thestigger")
@EnableMongoRepositories
public class SpringMongoConfiguration extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "WorkDiary";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1");
    }
}
