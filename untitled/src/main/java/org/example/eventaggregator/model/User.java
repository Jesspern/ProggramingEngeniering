package org.example.eventaggregator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventaggregator.event.AuditListener;

import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
