package org.example.eventaggregator.event.observer.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.eventaggregator.event.DatabaseEvent;
import org.example.eventaggregator.event.observer.inter.EventObserver;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingObserver implements EventObserver {

    @Override
    public void handleEvent(DatabaseEvent event) {
        log.info("[EVENT] Type: {}, Entity: {}", event.getEventType(), event.getEntity());
    }
}
