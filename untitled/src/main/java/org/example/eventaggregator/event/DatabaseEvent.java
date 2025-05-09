package org.example.eventaggregator.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.eventaggregator.event.entity.EventType;

@Data
@AllArgsConstructor
public class DatabaseEvent {

    private EventType eventType;
    private Object entity;

}

