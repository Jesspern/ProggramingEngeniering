package org.example.eventaggregator.event;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.example.eventaggregator.event.entity.EventType;
import org.example.eventaggregator.event.mediator.EventMediator;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AuditListener {
    private static EventMediator mediator;

    @Autowired
    public void setMediator(EventMediator mediator) {
        AuditListener.mediator = mediator;
    }

    @PrePersist
    public void onCreate(Object entity) {
        mediator.notifyObservers(new DatabaseEvent(EventType.CREATE, entity));
    }

    @PreUpdate
    public void onUpdate(Object entity) {
        mediator.notifyObservers(new DatabaseEvent(EventType.UPDATE, entity));
    }

    @PreRemove
    public void onDelete(Object entity) {
        mediator.notifyObservers(new DatabaseEvent(EventType.DELETE, entity));
    }
}
