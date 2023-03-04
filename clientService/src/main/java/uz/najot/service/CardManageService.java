package uz.najot.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.najot.entity.AppUser;
import uz.najot.entity.Card;
import uz.najot.model.ReqCard;
import uz.najot.model.ResponseModel;
import uz.najot.repository.CardRepository;
import uz.najot.security.AppSecurityConfig;
import uz.najot.util.PathUtil;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardManageService {

    private final AppSecurityConfig appSecurityConfig;

    private final RestTemplateService restTemplateService;
    private final CardRepository cardRepository;
    public ResponseModel addCard(ReqCard reqCard){
        AppUser appUser = appSecurityConfig.currentUser();
        Optional<Card> optionalCard = cardRepository.findByCardNumberAndAppUserId(reqCard.getCardNumber(), appUser.getId());
        if (optionalCard.isEmpty()){
            Map<String, Object> data = new HashMap<>();
            data.put("cardNumber",reqCard.getCardNumber());
            data.put("expiredDate",reqCard.getExpiredDate());
            data.put("phoneNumber",appUser.getPhoneNumber());
            JsonNode jsonNode = restTemplateService.postMethod(data, PathUtil.CHECK);
            int statusCode = jsonNode.get("statusCode").asInt();
            if (statusCode==200){
                double balance = jsonNode.get("object").get("balance").asDouble();
                Card card = new Card(reqCard.getCardNumber(), balance, appUser.getPhoneNumber(), reqCard.getExpiredDate(), appUser);
                cardRepository.save(card);
                return ResponseModel.getSuccess(null);
            }else {
                return new ResponseModel(statusCode,jsonNode.get("message").asText(),null);
            }
        }else {
            ResponseEntity<Card> cardResponseEntity = restTemplateService.postCard(optionalCard.get());

            return new ResponseModel(101,"Bu kart mavjud", cardResponseEntity);
        }


    }


}
