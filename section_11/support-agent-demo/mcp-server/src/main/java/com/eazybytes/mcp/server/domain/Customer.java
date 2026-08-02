package com.eazybytes.mcp.server.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import com.eazybytes.mcp.server.domain.Enums.*;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String preferredLanguage;

    @Enumerated(EnumType.STRING)
    private LoyaltyTier loyaltyTier;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public LoyaltyTier getLoyaltyTier() {
        return loyaltyTier;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
