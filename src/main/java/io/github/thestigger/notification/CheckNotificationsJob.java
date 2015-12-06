package io.github.thestigger.notification;

import io.github.thestigger.SpringMongoConfiguration;
import io.github.thestigger.entity.Event;
import io.github.thestigger.service.EventService;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.faces.application.FacesMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Job to check current state and notify about new events.
 */
public class CheckNotificationsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringMongoConfiguration.class);
        EventService service = applicationContext.getBean(EventService.class);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 8);

        List<Event> events = service.findByStartDateBetween(new Date(), calendar.getTime());


        System.out.println(events.get(0));
        System.out.println(new Date().getTime());
        System.out.println(events.get(0).getStartDate().getTime());
        for (Event event : events) {
            EventBus eventBus = EventBusFactory.getDefault().eventBus();
            eventBus.publish("/notification", new FacesMessage(events.get(0).getTitle()));
        }
    }
}
