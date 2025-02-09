package org.example.qr.service;

import org.example.qr.entity.Attendee;
import org.example.qr.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;

    public String verifyTicket(String ticketId) {
        Optional<Attendee> attendee = attendeeRepository.findByTicketId(ticketId);
        if (attendee.isPresent()) {
            if (attendee.get().isCheckedIn()) {
                return "Ticket already used.";
            } else {
                attendee.get().setCheckedIn(true);
                attendeeRepository.save(attendee.get());
                return "Welcome, " + attendee.get().getName() + "!";
            }
        } else {
            return "Invalid ticket.";
        }
    }
}
