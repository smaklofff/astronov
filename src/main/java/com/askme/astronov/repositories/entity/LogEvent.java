package com.askme.astronov.repositories.entity;

import com.askme.astronov.utils.Enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "log_event")
public class LogEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "event_timestamp")
    private LocalDateTime timestamp;

    @Column(nullable = false, name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType event;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    public LogEvent(LocalDateTime timestamp, EventType event, String message) {
        this.timestamp = timestamp;
        this.event = event;
        this.message = message;
    }
}
