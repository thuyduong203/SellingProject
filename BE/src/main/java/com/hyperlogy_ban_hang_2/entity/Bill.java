package com.hyperlogy_ban_hang_2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "bill",
        uniqueConstraints = {@UniqueConstraint(name = "Bill_Code", columnNames = {"code"})
        })
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idBill;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "code")
    private String code;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "payment_time")
    private Timestamp paymentTime;

    @Column(name = "receiver_name")
    private String receicverName;

    @Column(name = "receiver_date")
    private Timestamp receiverDate;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private int status;

}
