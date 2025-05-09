package org.example.eventaggregator.event.mediator;

import org.example.eventaggregator.event.DatabaseEvent;
import org.example.eventaggregator.event.observer.inter.EventObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMediator {

    private final List<EventObserver> observers = new ArrayList<>();

    public void registerObserver(EventObserver obs) {
        observers.add(obs);
    }

    public void notifyObservers(DatabaseEvent event) {
        observers.forEach(observer -> observer.handleEvent(event));
    }

}
