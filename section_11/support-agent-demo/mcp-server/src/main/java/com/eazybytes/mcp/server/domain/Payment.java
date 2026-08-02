package com.eazybytes.mcp.server.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.eazybytes.mcp.server.domain.Enums.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private CustomerOrder order;

    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private String transactionRef;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime chargedAt;

    public Long getId() {
        return id;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getChargedAt() {
        return chargedAt;
    }
}
