package jade;

import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.sql.Timestamp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Context {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
    	
    	Timestamp tmstp = new Timestamp(System.currentTimeMillis());
        String timestamp        = tmstp.toString();
        
        String host = "{세라젬HR API주소}";
        String token = "";
        
        {
            URL obj = new URL(host+"/restful/getToken");
        
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
        
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            String jsonStr = response.toString();
            System.out.println("reponse:"+jsonStr);
        
            JSONParser parser = new JSONParser();
            JSONObject jObj = (JSONObject) parser.parse(jsonStr);
            token = (String) jObj.get("Token");
            
            System.out.println("token:"+token);
        }
        
        {
            JSONObject top = new JSONObject();
            top.put("p1",URLEncoder.encode("{p1토큰}","UTF-8")); // Customer Key
            
            top.put("p2",URLEncoder.encode("{p2토큰}","UTF-8")); // Request Service Key
            
            top.put("p3",URLEncoder.encode(token,"UTF-8")); // Access Token

            JSONObject param = new JSONObject();
            JSONArray jArray = new JSONArray();
            
            
            param.put("C_CD","2011040");
            param.put("YMD","20211015");
            param.put("CP_CD","2011041");
            
            
            top.put("PARAM",param);
        
            String jsonStr = "jsonData="+top.toJSONString();
            
                        
            byte[] postDataBytes = jsonStr.getBytes("UTF-8");
            
            URL obj = new URL(host+"/restful");

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            con.setDoOutput(true);
            con.getOutputStream().write(postDataBytes);
        
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
        
            String json = response.toString();
            System.out.println("REQUEST:\r\n" + jsonStr);
            //System.out.println("RESPONSE:"+json);
        }
    }
}
