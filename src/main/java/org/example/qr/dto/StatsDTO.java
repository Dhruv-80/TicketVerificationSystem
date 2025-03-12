package org.example.qr.dto;

import lombok.Data;

@Data
public class StatsDTO {
    private long totalTickets;
    private long checkedInTickets;
    private long pendingTickets;
}