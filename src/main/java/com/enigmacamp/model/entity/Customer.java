package com.enigmacamp.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mst_customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE mst_customer SET status = false WHERE customer_id = ?")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Date dateOfBirth;
    private String phone;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = Boolean.TRUE;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties("customer")
    @OneToOne
    @JoinColumn(name = "profile_picture_id")
    private ProfilePicture profilePicture;

    @JsonIgnoreProperties("customer")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoanTransaction> loanTransactions;
}
