package io.github.thestigger.notification;

import io.github.thestigger.entity.Event;
import io.github.thestigger.service.EventService;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class with Scheduled tasks.
 * <p>
 * Used to check Events and push notifications if necessary.
 */
@Component
public class ScheduledTask {

    @Autowired
    private EventService service;

    @Scheduled(fixedDelay = 7000)
    public void checkEvents() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 8);

        List<Event> events = service.findByStartDateBetween(new Date(), calendar.getTime());


        System.out.println(events.get(0));
        System.out.println(new Date().getTime());
        System.out.println(events.get(0).getStartDate().getTime());
        for (Event event : events) {
            EventBus eventBus = EventBusFactory.getDefault().eventBus();
            eventBus.publish("/notification", new FacesMessage(event.getTitle()));
        }
    }

    public EventService getService() {
        return service;
    }

    public void setService(EventService service) {
        this.service = service;
    }
}
