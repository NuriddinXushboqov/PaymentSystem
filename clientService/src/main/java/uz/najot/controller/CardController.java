package uz.najot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.najot.model.ReqCard;
import uz.najot.model.ResponseModel;
import uz.najot.service.CardManageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cardManage")
@RequiredArgsConstructor
public class CardController {
    private final CardManageService cardManageService;


    @PostMapping("/add")
    public ResponseEntity<?> addCard(@Valid @RequestBody ReqCard reqCard){
        ResponseModel responseModel = cardManageService.addCard(reqCard);
        return ResponseEntity.ok(responseModel);
    }


}
