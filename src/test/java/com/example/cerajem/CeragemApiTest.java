package com.example.cerajem;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

/**
 * <p>
 * Project : Rchemist Commerce Platform
 * <p>
 * Created by kunner
 **/
@ExtendWith(SpringExtension.class)
public class CeragemApiTest {

  private static final String HOST_URL = "https://cerahr.ceragem.com:9950";
  private static final String TOKEN_URL_POSTFIX = "/restful/getToken";

  @Test
  public void apiTest(){

    String tokenUrl = getTokenUrl();


    HttpResponse<JsonNode> request = Unirest.post(tokenUrl)
        .header("Content-Type", "application/json;charset=UTF-8")
        .asJson();

    String token = null;
    if(request.isSuccess()){
      token = request.getBody().getObject().getString("Token");
    }

    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("Token is : " + token);
    System.out.println();
    System.out.println();
    System.out.println();

    Assert.notNull(token, "Token 을 받아 오지 못했습니다.");

  }

  private String getTokenUrl(){
    return HOST_URL + TOKEN_URL_POSTFIX;
  }

}
