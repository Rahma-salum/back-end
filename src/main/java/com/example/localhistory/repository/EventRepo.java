package com.example.localhistory.repository;

import com.example.localhistory.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo  extends JpaRepository<Event,Integer> {
}
