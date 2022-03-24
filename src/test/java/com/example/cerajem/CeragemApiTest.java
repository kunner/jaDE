package com.example.cerajem;

import com.example.cerajem.data.RequestJsonData;
import com.example.cerajem.data.StaffData;
import com.example.cerajem.data.StaffResult;
import com.google.gson.Gson;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.collections4.CollectionUtils;
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
  private static final String REST_URL_POSTFIX = "/restful/";

  private static final String CORP_KEY = "REFwWALk0KnxM8S55r5wAA==";
  private static final String ORG_DATA_KEY = "ZjH2yGNkSXC63TMeuSX5Hw==";
  private static final String STAFF_DATA_KEY = "438MVz40Ca2tE2C+CR1RDQ==";

  private Gson gson = new Gson();

  private String getTokenUrl(){
    return HOST_URL + TOKEN_URL_POSTFIX;
  }

  @Test
  public void TOKEN_TEST(){

    String token = getToken();

    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("Token is : " + token);
    System.out.println();
    System.out.println();
    System.out.println();

    Assert.notNull(token, "Token 을 받아 오지 못했습니다.");

  }

  @Test
  public void ASSOCIATION_TEST(){

    String token = getToken();

    RequestJsonData jsonData = new RequestJsonData(CORP_KEY, ORG_DATA_KEY, token)
        .addParameter("YMD", "20211015")
        .addParameter("C_CD", "2011040")
        .addParameter("CP_CD", "2011041");

    String jsonParameter = "jsonData=" + gson.toJson(jsonData);

    HttpResponse<JsonNode> response = Unirest.post(HOST_URL + REST_URL_POSTFIX)
        .body(jsonParameter)
        .header("Content-Type", "application/json;charset=UTF-8")
        .asJson();

    System.out.println("");


  }


  @Test
  public void STAFF_TEST(){
    String token = getToken();

    RequestJsonData jsonData = new RequestJsonData(CORP_KEY, STAFF_DATA_KEY, token)
        .addParameter("YMD", "20211015")
        .addParameter("C_CD", "2011040")
        .addParameter("CP_CD", "2011041");

    String jsonParameter = "jsonData=" + gson.toJson(jsonData);

    HttpResponse<JsonNode> response = Unirest.post(HOST_URL + REST_URL_POSTFIX)
        .body(jsonParameter)
        .header("Content-Type", "application/json;charset=UTF-8")
        .asJson();

    StaffResult result = gson.fromJson(response.getBody().toString(), StaffResult.class);

    System.out.println(result.getErrorMessage());
    System.out.println(result.isSuccess());

    List<StaffData> dataList = result.getData();
    for(StaffData data : CollectionUtils.emptyIfNull(dataList)){
      /*

        Data 를 front-mono 프로젝트에 맞게 변환해 호출합니다.
        - SimpleLogin 프로세스를 사용한다면 비밀번호는 임의로 입력합니다.

        - 권한은 CustomerRole 로 등록한 정보의 Role Alias 를 입력합니다.

        - 직원번호, 소속, 재직여부 등 기존 Customer 에 없는 필드는 Attribute Map 으로 넣으면 됩니다.
          이때 소속, 재직여부에 대한 커스텀필드, 커스텀옵션을 먼저 생성해야 합니다.
          Key 는 관리자도구에서 CustomField 로 생성한 소속 필드의 CustomOption Key 입니다.
          Value 는 관리자도구에서 소속 필드에 매핑한 CustomOption 의 Value 입니다.


        #회원 정보 생성
        CustomerAuthenticationService#requestRegister(RegisterRequest request);

        #회원 정보 변경
        CustomerManageService#updateCustomer(CustomerUpdateForm form);

        #회원 탈퇴 처리
        CustomerManageService#secedeCustomer(CustomerSecedeForm form);

         */

    }

  }

  @Test
  public void JSON_TEST(){

    String json = "{\n"
        + "        \"C_CD\": \"2011040\",\n"
        + "        \"TEL_NO\": \"\",\n"
        + "        \"ORG_ID\": \"\",\n"
        + "        \"JOB_ID\": \"J00xxxxxx\",\n"
        + "        \"BIRTH_YMD\": \"19221111\",\n"
        + "        \"CP_NM\": \"주식회사 XXXX앤에스\",\n"
        + "        \"MAIL_ADDR\": \"xxxxxxxx@ceragem.com\",\n"
        + "        \"EMP_NM\": \"장지\",\n"
        + "        \"DUTY_CD\": \"\",\n"
        + "        \"STAT_CD\": \"\",\n"
        + "        \"JOB_NM\": \"\",\n"
        + "        \"EMP_GRADE_CD\": \"\",\n"
        + "        \"C_NM\": \"(주)XXX\",\n"
        + "        \"RETIRE_YMD\": \"\",\n"
        + "        \"CP_CD\": \"2011041\",\n"
        + "        \"EMP_TYPE\": \"\",\n"
        + "        \"GRP_YMD\": \"20220407\",\n"
        + "        \"EMP_TYPE_NM\": \"\",\n"
        + "        \"ENTER_YMD\": \"20220407\",\n"
        + "        \"ORG_NM\": \"\",\n"
        + "        \"MOBILE_NO\": \"01011112222\",\n"
        + "        \"EMP_ID\": \"1234567\",\n"
        + "        \"DUTY_NM\": \"\",\n"
        + "        \"STAT_NM\": \"\",\n"
        + "        \"EMP_GRADE_NM\": \"\"\n"
        + "    }";

    StaffData data = gson.fromJson(json, StaffData.class);

    System.out.println(gson.toJson(data));

  }


  private String getToken(){
    String tokenUrl = getTokenUrl();

    HttpResponse<JsonNode> request = Unirest.post(tokenUrl)
        .header("Content-Type", "application/json;charset=UTF-8")
        .asJson();

    String token = null;
    if(request.isSuccess()){
      token = request.getBody().getObject().getString("Token");
    }

    return token;
  }

}
