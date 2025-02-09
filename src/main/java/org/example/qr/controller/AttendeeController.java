package org.example.qr.controller;

import org.example.qr.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
@RequestMapping("/api")



public class AttendeeController {
    @Autowired
    private AttendeeService attendeeService;

    @PostMapping("/verify-ticket")
    public ResponseEntity<String> verifyTicket(@RequestBody Map<String, String> request) {
        String ticketId = request.get("ticketId");
        String result = attendeeService.verifyTicket(ticketId);
        return ResponseEntity.ok(result);
    }
}