package org.example.qr.repository;

import org.example.qr.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Optional<Attendee> findByTicketId(String ticketId);

    @Query("SELECT COUNT(a) FROM Attendee a WHERE a.checkedIn = ?1")
    long countByCheckedIn(boolean checkedIn);

    @Query("SELECT DATE(a.updatedAt) as date, COUNT(a) as count " +
            "FROM Attendee a " +
            "WHERE a.checkedIn = true " +
            "GROUP BY DATE(a.updatedAt)")
    List<Map<String, Object>> findCheckInAnalytics();
}