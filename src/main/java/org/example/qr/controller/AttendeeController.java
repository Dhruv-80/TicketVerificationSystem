package org.example.qr.controller;

import org.example.qr.entity.Attendee;
import org.example.qr.repository.AttendeeRepository;
import org.example.qr.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")



public class AttendeeController {
    @Autowired
    private AttendeeService attendeeService;
    @Autowired
    private AttendeeRepository attendeeRepository;

    @PostMapping("/verify-ticket")
    public ResponseEntity<String> verifyTicket(@RequestBody Map<String, String> request) {
        String ticketId = request.get("ticketId");
        String result = attendeeService.verifyTicket(ticketId);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/approve-ticket")
    public ResponseEntity<Map<String, String>> approveTicket(@RequestBody Map<String, String> request) {
        String ticketId = request.get("ticketId");
        Optional<Attendee> attendee = attendeeRepository.findByTicketId(ticketId);
        if (attendee.isPresent()) {
            attendee.get().setCheckedIn(true);
            attendeeRepository.save(attendee.get());
            return ResponseEntity.ok(Map.of("message", "Ticket approved. Welcome!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Ticket not found."));
        }
    }
    @PostMapping("/decline-ticket")
    public ResponseEntity<Map<String, String>> declineTicket(@RequestBody Map<String, String> request) {
        String ticketId = request.get("ticketId");
        Optional<Attendee> attendee = attendeeRepository.findByTicketId(ticketId);
        if (attendee.isPresent()) {
            attendeeRepository.delete(attendee.get());
            return ResponseEntity.ok(Map.of("message", "Ticket declined."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Ticket not found."));
        }
    }
    @GetMapping("/attendee-details")
    public ResponseEntity<Attendee> getAttendeeDetails(@RequestParam String ticketId) {
        Optional<Attendee> attendee = attendeeRepository.findByTicketId(ticketId);
        return attendee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}