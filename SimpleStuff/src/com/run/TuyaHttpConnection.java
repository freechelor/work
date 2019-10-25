package com.run;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class TuyaHttpConnection {
	final static String ACCESS_ID = "uewwmf5vkytrrt9ddcak"; // TODO: Contact Tuya Staff to get
//
	final static String ACCESS_KEY = "vvupv4hukrrrvrhf7x83mxhasnrs9pw4"; 

	public static void main(String args[]) {
		String URL = "https://openapi.tuyaus.com/v1.0/token";
		//String URL = "https://www.google.com/maps";
		String URL2 = "https://www.google.com/maps";
		//String URL2 = "https://openapi.tuyaus.com/v1.0/users/az1542053703946Cv8o6/devices";
		try {
			HttpsURLConnection connection = (HttpsURLConnection) new URL(URL).openConnection();
			String data = "grant_type=1";
			//String data2 = "uid=az1530885767175VsiCz";
			connection.setRequestMethod("GET");
			// TODO : Let's try SSLFactory!!! or SSL control directly skip or something like that.
//			connection.addRequestProperty("Accept", "*/*");
//			connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
//			connection.addRequestProperty("Accept-Language", "en-US,en;q=0.9");
//			connection.addRequestProperty("Cache-Control", "no-cache");
//			connection.addRequestProperty("Connection", "keep-alive");
//			connection.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
			
			long ll = System.currentTimeMillis();
		    String s = (ACCESS_ID + ACCESS_KEY + ll);
		    //s = URLEncoder.encode(s, "UTF-8"); //encode(s);
		    String sign = getMD5(s.getBytes()).toUpperCase();
		    //System.out.println("time in millis:"+ll + ", sign:"+sign);
		    
			connection.addRequestProperty("client_id", "uewwmf5vkytrrt9ddcak");
			connection.addRequestProperty("sign", sign);
			connection.addRequestProperty("t", String.valueOf(ll));
//			connection.addRequestProperty("access_token", "cb43ced321147e17f10f8fbcd8bd64f2");
			//connection.addRequestProperty("schema", "nuwave");
			connection.setDoOutput(true);
		    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		    outputStream.write(data.getBytes(StandardCharsets.UTF_8));
		    //outputStream.write();//data.getBytes());
		    //outputStream.flush();
		    

		    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
		    BufferedReader rdr = new BufferedReader(inputStream);
		    String result = null;
		    while((result=rdr.readLine())!=null) {
		    	System.out.println(result);
		    }
		    rdr.close();
		    connection.disconnect();
		    //connection.getInputStream().close(); // We don't care about the response - Just send our data :)
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	static private void print_https_cert(HttpsURLConnection con){
	  
	 if(con!=null){
				
	   try {
					
		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Cipher Suite : " + con.getCipherSuite());
		System.out.println("\n");
					
		Certificate[] certs = con.getServerCertificates();
		for(Certificate cert : certs){
		   System.out.println("Cert Type : " + cert.getType());
		   System.out.println("Cert Hash Code : " + cert.hashCode());
		   System.out.println("Cert Public Key Algorithm : " 
	                                 + cert.getPublicKey().getAlgorithm());
		   System.out.println("Cert Public Key Format : " 
	                                 + cert.getPublicKey().getFormat());
		   System.out.println("\n");
		}
					
		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	
	  }
		
	}
		
	static private void print_content(HttpsURLConnection con){
		if(con!=null){
				
		try {
			
		   System.out.println("****** Content of the URL ********");			
		   BufferedReader br = 
			new BufferedReader(
				new InputStreamReader(con.getInputStream()));
					
		   String input;
					
		   while ((input = br.readLine()) != null){
		      System.out.println(input);
		   }
		   //br.close();
					
		} catch (IOException e) {
		   e.printStackTrace();
		}
				
	    }
			
	}

    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); //  MD5 results in a 128-bit long integer，
            //Byte is 16 bytes
            char str[] = new char[16 * 2]; // Each byte in hexadecimal, then the use of two characters,
            //Therefore, 32 hexadecimal characters are required
            int k = 0; //Indicates the corresponding character position in the conversion result
            for (int i = 0; i < 16; i++) { //Starting from the first byte, for every byte of MD5
                //Convert to hexadecimal characters
                byte byte0 = tmp[i]; //Take the i-th byte
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; //Take the high 4-bit digital conversion,
                // >>> For the logical right shift, move the sign bit to the right
                str[k++] = hexDigits[byte0 & 0xf]; // The lower 4 digits of the byte are converted
            }
            s = new String(str); //The result is converted to a string

        } catch (Exception e) {
            System.out.println("MD5 encryption exception! : " + e.toString());
        }
        return s;
    }
}
