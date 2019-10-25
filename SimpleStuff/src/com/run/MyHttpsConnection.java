package com.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import com.google.gson.Gson;

public class MyHttpsConnection {
	final static String ACCESS_ID = "uewwmf5vkytrrt9ddcak"; //"{myAccessId}";
	final static String ACCESS_KEY = "vvupv4hukrrrvrhf7x83mxhasnrs9pw4"; //"{myAccessKey}"; 
	static String URL = "https://openapi.tuyaus.com/v1.0/token?grant_type=1";
	// GET, first %d must be replaced with {page_no}. and second %d must be replaced with {page_size}
	static String USER_URL = "https://openapi.tuyaus.com/v1.0/apps/nuwave/users?page_no=%d&page_size=%d";	
	//GET, %s must be replaced with {userId}
	static String DEVICE_URL = "https://openapi.tuyaus.com/v1.0/users/%s/devices";	
	//GET, %s must be replaced with {device_id}
	static String DEVICE_DETAIL_URL = "https://openapi.tuyaus.com/v1.0/devices/%s";		
	boolean BeforeAccessToken = true;
	
	/**
	 * create default HttpsURLConnection
	 * @param url	the url to connect with to get data.
	 * @return HttpsURLConnection
	 */
	static HttpsURLConnection createDefault(String url) throws MalformedURLException, IOException {
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		connection.addRequestProperty("Accept", "*/*");
		connection.addRequestProperty("Accept-Encoding", "gzip, deflate, br");
		connection.addRequestProperty("Accept-Language", "en-US,en;q=0.9");
		connection.addRequestProperty("Cache-Control", "no-cache");
		connection.addRequestProperty("Connection", "keep-alive");
		connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
		return connection;
	}
	
	/**	
	 * to get accessToken from Tuya. You must get accessToken before getting data from Tuya 
	 * @param	ll	time in milliseconds
	 * @return accessToken 
	 **/
	static String getAccessToken(long ll) {
		ReturnMessage msg = null;
		try {
			HttpsURLConnection connection = createDefault(URL);
		    String s = (ACCESS_ID + ACCESS_KEY + ll);
		    s = URLEncoder.encode(s, "UTF-8"); //encode(s);
		    String sign = getMD5(s.getBytes()).toUpperCase();
		    System.out.println("time in millis:"+ll + ", sign:"+sign);
			connection.setRequestMethod("GET");	
			connection.addRequestProperty("client_id", "uewwmf5vkytrrt9ddcak");
			connection.addRequestProperty("sign", sign);
			connection.addRequestProperty("t", String.valueOf(ll));
			
		    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
		    BufferedReader rdr = new BufferedReader(inputStream);
		    Gson gson = new Gson();
		    String result;
		    while((result=rdr.readLine())!=null) {
		    	System.out.println(result);
		    	msg = gson.fromJson(result, ReturnMessage.class);
		    }
		    rdr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	    return msg.result.access_token;
	}
	
	/**
	 * @param token token got from @see #getAccessToken(long)
	 * @param ll	time in milliseconds, it must be the same with one that you used as a parameter to get accessToken
	 */
	static void getUsers(String token, long ll, int p, int psize) {
		ReturnMessage msg = null;
		try {
			HttpsURLConnection connection = createDefault(String.format(USER_URL, p, psize));
		    String s = (ACCESS_ID + token + ACCESS_KEY + ll);
		    s = URLEncoder.encode(s, "UTF-8"); //encode(s);
		    String sign = getMD5(s.getBytes()).toUpperCase();
		    System.out.println("time in millis:"+ll + ", sign:"+sign);
			connection.setRequestMethod("GET");	
			connection.addRequestProperty("client_id", "uewwmf5vkytrrt9ddcak");
			connection.addRequestProperty("sign", sign);
			connection.addRequestProperty("access_token", token);
			connection.addRequestProperty("t", String.valueOf(ll));
		    
		    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
		    BufferedReader rdr = new BufferedReader(inputStream);
		    Gson gson = new Gson();
		    String result;
		    while((result=rdr.readLine())!=null) {
		    	System.out.println(result);
		    }
		    rdr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void getDevices(String token, long ll, String uId) {
		ReturnMessage msg = null;
		try {
			HttpsURLConnection connection = createDefault(String.format(DEVICE_URL, uId));
		    String s = (ACCESS_ID + token + ACCESS_KEY + ll);
		    s = URLEncoder.encode(s, "UTF-8"); //encode(s);
		    String sign = getMD5(s.getBytes()).toUpperCase();
		    System.out.println("time in millis:"+ll + ", sign:"+sign);
			connection.setRequestMethod("GET");	
			connection.addRequestProperty("client_id", "uewwmf5vkytrrt9ddcak");
			connection.addRequestProperty("sign", sign);
			connection.addRequestProperty("access_token", token);
			connection.addRequestProperty("t", String.valueOf(ll));
		    
		    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
		    BufferedReader rdr = new BufferedReader(inputStream);
		    Gson gson = new Gson();
		    String result;
		    while((result=rdr.readLine())!=null) {
		    	System.out.println(result);
		    }
		    rdr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	static void getDeviceDetail(String token, long ll, String deviceId) {
		ReturnMessage msg = null;
		try {
			HttpsURLConnection connection = createDefault(String.format(DEVICE_DETAIL_URL, deviceId));
		    String s = (ACCESS_ID + token + ACCESS_KEY + ll);
		    s = URLEncoder.encode(s, "UTF-8"); //encode(s);
		    String sign = getMD5(s.getBytes()).toUpperCase();
		    System.out.println("time in millis:"+ll + ", sign:"+sign);
			connection.setRequestMethod("GET");	
			connection.addRequestProperty("client_id", "uewwmf5vkytrrt9ddcak");
			connection.addRequestProperty("sign", sign);
			connection.addRequestProperty("access_token", token);
			connection.addRequestProperty("t", String.valueOf(ll));
		    
		    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
		    BufferedReader rdr = new BufferedReader(inputStream);
		    Gson gson = new Gson();
		    String result;
		    while((result=rdr.readLine())!=null) {
		    	System.out.println(result);
		    }
		    rdr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		long ll = System.currentTimeMillis();
		String accessToken = getAccessToken(ll);
		getUsers(accessToken, ll, 1, 50);
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

	/**
	 * to get MD5 hashing value
	 * @param source	data to be hashed
	 * @return	value hashed from source
	 */
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
