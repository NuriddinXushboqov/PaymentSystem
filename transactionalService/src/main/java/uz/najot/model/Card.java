package uz.najot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cardNumber", "appUserId"})})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cardNumber;
    private Double balance;
    private String phoneNumber;
    private String expiredDate;
}
