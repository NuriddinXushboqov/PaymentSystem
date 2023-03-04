package uz.najot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.najot.entity.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card> findByCardNumberAndAppUserId(String cardNumber, Integer userId);
}
