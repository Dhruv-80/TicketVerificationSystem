package org.example.qr.controller;

import org.example.qr.dto.AnalyticsDTO;
import org.example.qr.dto.StatsDTO;
import org.example.qr.entity.Attendee;
import org.example.qr.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/stats")
    public ResponseEntity<StatsDTO> getStats() {
        StatsDTO stats = adminService.getTicketStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/analytics")
    public ResponseEntity<List<AnalyticsDTO>> getAnalytics() {
        List<AnalyticsDTO> analytics = adminService.getAnalytics();
        return ResponseEntity.ok(analytics);
    }

    @GetMapping("/attendees")
    public ResponseEntity<Page<Attendee>> getAttendees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Attendee> attendees = adminService.getAttendees(page, size);
        return ResponseEntity.ok(attendees);
    }
}