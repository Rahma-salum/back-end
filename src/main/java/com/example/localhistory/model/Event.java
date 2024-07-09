package com.example.localhistory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
        private String eventName;
        private LocalDateTime eventStart;
        private LocalDateTime eventEnd;

        // Getters and Setters

        public int getEventId() {
            return eventId;
        }

        public void setEventId(int eventId) {
            this.eventId = eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public LocalDateTime getEventStart() {
            return eventStart;
        }

        public void setEventStart(LocalDateTime eventStart) {
            this.eventStart = eventStart;
        }

        public LocalDateTime getEventEnd() {
            return eventEnd;
        }

        public void setEventEnd(LocalDateTime eventEnd) {
            this.eventEnd = eventEnd;
        }

    public void setEvenName() {
    }

    public void getEvenName() {
    }
}

