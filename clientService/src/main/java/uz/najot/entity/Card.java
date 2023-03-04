package uz.najot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cardNumber", "appUserId"})})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String expiredDate;
    @ManyToOne
    private AppUser appUser;

    public Card(String cardNumber, Double balance, String phoneNumber, String expiredDate, AppUser appUser) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.expiredDate = expiredDate;
        this.appUser = appUser;
    }
}
