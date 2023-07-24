/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libtool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Naufal Azmi Ginting
 *
 */
public class HTTPConnect {
    public String useragent = "Mozilla/5.0 (Windows; U; Windows NT 6.1;en-GB;rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)";
    public String get(String url){
        String result = "";
        String chunk;
        try {
            HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", useragent);
            huc.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            while((chunk = br.readLine()) != null){
                result += chunk+"\n";
                chunk = null;
            }
            huc.disconnect();
        }
        catch(IOException e){
            System.out.println("Error : "+e.getMessage());
        }
        
        return result;
    }
    public String post(String url, String data){
        String result = "";
        try{
            HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
            huc.setDoInput(true);
            huc.setDoOutput(true);
            huc.setRequestMethod("POST");
            huc.setRequestProperty("User-Agent", useragent);
            
            OutputStreamWriter osw = new OutputStreamWriter(huc.getOutputStream());
            osw.write(data);
            osw.flush();
            osw.close();
            
            huc.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String chunk;
            while((chunk = br.readLine()) != null){
                System.out.println(chunk);
                result += chunk + "\n";
                chunk = null;
            }
        }
        catch(IOException e){
            System.out.println("Error : "+e.getMessage());
        }
        return result;
    }
    
    public InputStream getInputStream(String url){
        InputStream is = null;
        try{
            HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", useragent);
            huc.connect();
            
            is = huc.getInputStream();
        }
        catch(IOException e){
            System.out.println("Error : "+ e.getMessage());
        }
        
        return is;
    }
    
    public InputStream postInputStream(String url, String data){
        InputStream is = null;
        try{
            HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
            huc.setDoInput(true);
            huc.setDoOutput(true);
            huc.setRequestMethod("POST");
            huc.setRequestProperty("User-Agent", useragent);
            
            OutputStreamWriter osw = new OutputStreamWriter(huc.getOutputStream());
            osw.write(data);
            osw.flush();
            osw.close();
            
            huc.connect();
            
            is = huc.getInputStream();
        }
        catch(IOException e){
            System.out.println("Error : "+ e.getMessage());
        }
        
        return is;
    }
}
