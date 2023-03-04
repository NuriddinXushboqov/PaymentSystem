package uz.najot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
    private int statusCode;
    private String message;
    private Object object;
    public static ResponseModel getSuccess(Object data){
        return new ResponseModel(0,"ok",data);
    }
}
