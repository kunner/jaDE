package com.example.cerajem.data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Project : Rchemist Commerce Platform
 * <p>
 * Created by kunner
 **/
public class RequestJsonData implements Serializable {
  public RequestJsonData(String p1, String p2, String token){
    this.p1 = encode(p1);
    this.p2 = encode(p2);
    this.p3 = encode(token);
  }
  private String p1;
  private String p2;
  private String p3;
  private Map<String, String> PARAM = new HashMap<>();
  public RequestJsonData addParameter(String key, String value){
    this.PARAM.put(key, value);
    return this;
  }

  private static String encode(String str){
    try {
      return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return str;
    }
  }

}

