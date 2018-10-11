package com.example.aceahmer.quakequake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class makeNetworkRequest {
    private static HttpURLConnection httpURLConnection;
    private static InputStream inputStream;
    private static String jsonResponse="";
    public static String makeHttpRequest(URL url)  {
        try {
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse=readInputStream(inputStream);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
        }



        return jsonResponse;
    }

    private static String readInputStream(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder=new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                String line=bufferedReader.readLine();
                while (line!=null){
                    stringBuilder.append(line);
                    line=bufferedReader.readLine();
                }


        } return stringBuilder.toString();
    }


}
