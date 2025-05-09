package org.example.eventaggregator.event.observer.inter;

import org.example.eventaggregator.event.DatabaseEvent;

public interface EventObserver {
    void handleEvent(DatabaseEvent event);
}
