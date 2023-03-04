package uz.najot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.najot.model.Card;
import uz.najot.service.CardUserService;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardCheckController {

    @Autowired
    private final CardUserService cardUserService;


    @PostMapping("/check")
    public ResponseEntity<Card> check(@RequestBody Card card){
        return cardUserService.checkCard(card);
    }
}
