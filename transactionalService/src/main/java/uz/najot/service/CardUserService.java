package uz.najot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najot.model.Card;
import uz.najot.repository.CardRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardUserService {

    @Autowired
    private final CardRepo cardRepo;
    public ResponseEntity<Card> checkCard(Card card) {
        Optional<Card> cardOptional = cardRepo.findByCardNumberAndExpiredDateAndPhoneNumber(card.getCardNumber(),
                card.getExpiredDate(), card.getPhoneNumber());

        if(!cardOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Card card1 = cardOptional.get();

        return ResponseEntity.ok(card1);
    }
}
