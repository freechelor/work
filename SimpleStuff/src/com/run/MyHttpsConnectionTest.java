package com.run;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Before;
import org.junit.Test;

public class MyHttpsConnectionTest {

	MyHttpsConnection myConn;
	String accessToken;
	@Before
	public void setUp() throws Exception {
		myConn = new MyHttpsConnection();
	}

	
	static String URL = "https://openapi.tuyaus.com/v1.0/token?grant_type=1";
	static long time = 0L;
	@Test
	public void testCreateDefault() throws MalformedURLException, IOException {
		HttpsURLConnection conn = myConn.createDefault(URL);
		
	}

	@Test
	public void testGetAccessToken() {
		System.out.println("=================testGetAccessToken=================");
		System.out.println();
		time = System.currentTimeMillis();
		accessToken = myConn.getAccessToken(time);
		assertNotNull(accessToken);
		System.out.println();
		//fail("Not yet implemented");
	}

	@Test
	public void testGetUsers() {
		System.out.println("=================testGetUsers=================");
		System.out.println();
		//when condition goes here
		time = System.currentTimeMillis();
		assertNotNull(accessToken=myConn.getAccessToken(time));
		System.out.println(accessToken);
		myConn.getUsers(accessToken, time, 1, 50);
		System.out.println();
	}

	@Test
	public void testGetDevices() {
		System.out.println("=================testGetDevices=================");
		System.out.println();
		time = System.currentTimeMillis();
		assertNotNull(accessToken=myConn.getAccessToken(time));
		System.out.println(accessToken);
		myConn.getDevices(accessToken, time, "az1532576459372PNqxh");
		System.out.println();
	}
	
	@Test
	public void testGetDeviceDetail() {
		System.out.println("=================testGetDevicedDetail=================");
		System.out.println();
		time = System.currentTimeMillis();
		assertNotNull(accessToken=myConn.getAccessToken(time));
		System.out.println(accessToken);
		myConn.getDeviceDetail(accessToken, time, "vdevo153257647122958");
		System.out.println();
	}
}
