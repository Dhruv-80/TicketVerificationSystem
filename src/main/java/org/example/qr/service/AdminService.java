package org.example.qr.service;

import org.example.qr.dto.AnalyticsDTO;
import org.example.qr.dto.StatsDTO;
import org.example.qr.entity.Attendee;
import org.example.qr.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    /**
     * Get ticket statistics (total, checked-in, pending).
     *
     * @return StatsDTO containing ticket statistics.
     */
    public StatsDTO getTicketStats() {
        long totalTickets = attendeeRepository.count();
        long checkedInTickets = attendeeRepository.countByCheckedIn(true);
        long pendingTickets = totalTickets - checkedInTickets;

        StatsDTO stats = new StatsDTO();
        stats.setTotalTickets(totalTickets);
        stats.setCheckedInTickets(checkedInTickets);
        stats.setPendingTickets(pendingTickets);
        return stats;
    }
    /**
     * Get a paginated list of attendees.
     *
     * @param page Page number (starting from 0).
     * @param size Number of items per page.
     * @return Page of Attendee objects.
     */
    public Page<Attendee> getAttendees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return attendeeRepository.findAll(pageable);
    }
    public List<AnalyticsDTO> getAnalytics() {
        List<Map<String, Object>> analyticsData = attendeeRepository.findCheckInAnalytics();
        return analyticsData.stream()
                .map(data -> new AnalyticsDTO(
                        data.get("date").toString(),
                        Long.parseLong(data.get("count").toString())
                ))
                .collect(Collectors.toList());
    }
}