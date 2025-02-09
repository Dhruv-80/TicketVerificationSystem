package org.example.qr.entity;

import jakarta.persistence.*;
@lombok.Data

@Entity
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ticketId;

    private String name;
    private String email;
    private boolean checkedIn;

    // Getters and Setters
}