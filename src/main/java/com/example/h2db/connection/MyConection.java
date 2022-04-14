package com.example.h2db.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyConection {
    public static HttpURLConnection connection;

    String line;
    BufferedReader reader;
    StringBuffer responseContent = new StringBuffer();

    String que="https://api.spaceflightnewsapi.net/v3/articles?_limit=100&_start=1";

    public MyConection(int limit,int start)
    {
        que="https://api.spaceflightnewsapi.net/v3/articles?_limit="+limit + "&_start="+start;
    }

    public String getJsonString(String query)
    {
        try{

            URL url = new URL(que);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();


            if(status >299)
            {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine())!=null)
                {
                    responseContent.append(line);
                }
                reader.close();
            }else
            {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine())!=null)
                {
                    responseContent.append(line);
                }
                reader.close();
            }
            //System.out.println(responseContent.toString());
            //  Articles articles = new Articles();
            // articles = parse2(responseContent.toString());
            //System.out.println(articles.toString());
            // parse(responseContent.toString());

            //com.example.connection.MyParser parser1=new com.example.connection.MyParser();
            //Articles articles= parser1.parse(responseContent.toString());
            //System.out.println(articles);


        }catch(
                MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if(connection!=null)
            {
                connection.disconnect();

            }
        }
        return responseContent.toString();
    }


}
