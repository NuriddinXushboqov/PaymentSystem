package uz.najot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.najot.entity.Card;
import uz.najot.util.PathUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RestTemplateService{
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public JsonNode postMethod(Object object, String path){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("token","f5a71cee-9b25-439e-abc9-bb4aa921dcb3");
        HttpEntity<?> request = new HttpEntity<>(object,headers);
        ResponseEntity<String> response = restTemplate.exchange(PathUtil.HOST + path, HttpMethod.POST, request, String.class);
        String body = response.getBody();
        try {
            return objectMapper.readTree(body);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    public ResponseEntity<Card> postCard(Card card){
        Card card1 = restTemplate.postForObject("http://localhost:8085/api/card/check", card, Card.class);

        return ResponseEntity.ok(card1);
    }
}
