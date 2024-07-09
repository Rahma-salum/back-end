package com.example.localhistory.controller;

import com.example.localhistory.model.Event;
import com.example.localhistory.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class EventAPI {

    @Autowired
    private EventRepo eventRepo;

    @PostMapping("add/event")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        try {
            Event savedEvent = eventRepo.save(event);
            return new ResponseEntity<>(savedEvent, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong while adding the event.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/event/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
        try {
            eventRepo.deleteById(eventId);
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong while deleting the event.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/event/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable int eventId, @RequestBody Event updateEvent) {
        Optional<Event> eventOptional = eventRepo.findById(eventId);
        if (eventOptional.isPresent()) {
            Event existingEvent = eventOptional.get();
            existingEvent.setEvenName();updateEvent.getEvenName(); // Assumes getter/setter names are corrected
            existingEvent.setEventStart(updateEvent.getEventStart());
            existingEvent.setEventEnd(updateEvent.getEventEnd());
            Event updatedEvent = eventRepo.save(existingEvent);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found.", HttpStatus.NOT_FOUND);
        }
    }
}
