package app.andriod.em.andriode;


import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import app.andriod.em.andriode.pojo.RequestPackage;

public class HttpManager {

    public static String getData(RequestPackage requestPackage){
        BufferedReader reader = null;
        String uri = requestPackage.getUrl();

        if(requestPackage.getMethod().equals("GET")){
            uri += "?" + requestPackage.getEncodeParams();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while( (line = reader.readLine()) != null ){
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }





    public static String getData(String uri, String username, String password){
        BufferedReader reader = null;
        byte[] loginBytes = (username + ":" + password).getBytes();
        StringBuilder loginBuilder = new StringBuilder().append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));

        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", loginBuilder.toString());
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while( (line = reader.readLine()) != null ){
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

}
