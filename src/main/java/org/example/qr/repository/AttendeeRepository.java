package org.example.qr.repository;

import org.example.qr.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Optional<Attendee> findByTicketId(String ticketId);
}
