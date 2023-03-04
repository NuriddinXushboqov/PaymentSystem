package uz.najot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najot.model.Card;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {
    Optional<Card> findByCardNumberAndExpiredDateAndPhoneNumber(String cardNumber,
                                                          String expiredDate,
                                                          String phoneNumber);
}
