package uz.najot.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqCard {
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String expiredDate;
}
