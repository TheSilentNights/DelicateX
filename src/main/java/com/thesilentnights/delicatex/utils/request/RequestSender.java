package com.thesilentnights.delicatex.utils.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestSender{
    private String ip;
    private String param;
    private String route; //存在多级route时用/分割 -> json/api 反映在请求上为: https://xxx.xxx.xxx/json/api/param
    private RequestLimit limit;

    public RequestSender(String ip, String route,String param,RequestLimit limit) {
        this.ip = ip;
        this.route = route;
        this.param = param;
        this.limit = limit;
    }


    public String sendGet(){
        if (!this.limit.ifCan()){
            return null;
        }
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("http://"+ip+"/"+route+"/"+param);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp;

            while ((temp = bufferedReader.readLine())!= null){
                result.append(temp);
            }

            connection.disconnect();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
