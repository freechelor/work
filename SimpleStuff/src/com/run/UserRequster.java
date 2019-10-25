package com.run;

import java.util.HashMap;
import java.util.Map;

import com.tuya.smart.TuyaCloudClient;
import com.tuya.smart.model.RequestMessage;
import com.tuya.smart.model.ResponseMessage;

public class UserRequster {

	private final String END_URI = "https://a1.tuyaus.com/api.json";// Call China's API (you can replace it with
																	// other available areas)

	private final String ACCESS_ID = "uewwmf5vkytrrt9ddcak"; // TODO: Contact Tuya Staff to get

	private final String ACCESS_KEY = "vvupv4hukrrrvrhf7x83mxhasnrs9pw4"; // TODO: Contact Tuya Staff to get

	TuyaCloudClient client = new TuyaCloudClient(ACCESS_ID, ACCESS_KEY, END_URI);

	// private int offset = 0;
	// private int limit = 30;

	// public void setLimit(int l) {
	// limit = l;
	// }

	public ResponseMessage getUsersList(long st, long et, int off, int lmt) {
		/*
		 * Create client，accessId&accessKey tuya provides accessId as clientId accessKey
		 * for signature
		 */

		// Constitute HTTPS request
		RequestMessage request = new RequestMessage();
		request.setApi("tuya.cloud.user.list"); // An API registered with a mobile number

		request.setApiVersion("2.0");
		request.setOs("Windows");
		request.setLang("en");

		// Package request parameters (interface needs specific parameters, please refer
		// to the API manual)
		Map<String, String> params = new HashMap<String, String>();
		// params.put("countryCode", "1");
		// params.put("mobile", "8476879067");
		// params.put("startTime", "1404786400");
		// params.put("endTime", "1531925660");
		params.put("schema", "nuwave");
		params.put("startTime", st + "");
		params.put("endTime", et + "");
		params.put("offset", off + "");
		params.put("limit", lmt + "");
		// off += lmt;
		/*
		 * Note: In addition to a few interfactes for registration and obtaining
		 * statistics, most of the interface needs sessionId.
		 * 
		 * Refer to the API manual for sessionId.
		 * 
		 * You can get the result from the registration and login interface and return
		 * the result field as sid
		 * 
		 */

		// The request parameters are added to the HTTP request

		request.setParams(params);

		/*
		 * Initiate a request and get a response
		 * 
		 * If the request succeeds, the result in response will be the result of a JSON
		 * object encapsulation. If the request fails, look at errorMsg and errorCode
		 * and proceed accordingly.
		 */
		ResponseMessage response = client.sendRequest(request);
		System.out.println("sign : " + request.getSign());
		System.out.println(response.toString());

		return response;
	}

	public ResponseMessage fetchUsers(long st, long et, int off, int lmt) {
		/*
		 * Create client，accessId&accessKey tuya provides accessId as clientId accessKey
		 * for signature
		 */

		// Constitute HTTPS request
		RequestMessage request = new RequestMessage();
		request.setApi("tuya.cloud.user.info.fetch"); // An API registered with a mobile number

		request.setApiVersion("2.0");
		request.setOs("Windows");
		request.setLang("en");

		// Package request parameters (interface needs specific parameters, please refer
		// to the API manual)
		Map<String, String> params = new HashMap<String, String>();
		// params.put("countryCode", "1");
		// params.put("mobile", "8476879067");
		// params.put("startTime", "1404786400");
		// params.put("endTime", "1531925660");
		params.put("startTime", st + "");
		params.put("endTime", et + "");
		params.put("offset", off + "");
		// off += lmt;
		/*
		 * Note: In addition to a few interfactes for registration and obtaining
		 * statistics, most of the interface needs sessionId.
		 * 
		 * Refer to the API manual for sessionId.
		 * 
		 * You can get the result from the registration and login interface and return
		 * the result field as sid
		 * 
		 */

		// The request parameters are added to the HTTP request

		request.setParams(params);

		/*
		 * Initiate a request and get a response
		 * 
		 * If the request succeeds, the result in response will be the result of a JSON
		 * object encapsulation. If the request fails, look at errorMsg and errorCode
		 * and proceed accordingly.
		 */
		ResponseMessage response = client.sendRequest(request);
		System.out.println("sign : " + request.getSign());
		System.out.println(response.toString());

		return response;
	}

	public ResponseMessage getDevices(String id) {
		/*
		 * Create client，accessId&accessKey tuya provides accessId as clientId accessKey
		 * for signature
		 */
		// TuyaCloudClient client = new TuyaCloudClient(ACCESS_ID, ACCESS_KEY, END_URI);

		// Constitute HTTPS request
		RequestMessage request = new RequestMessage();
		request.setApi("tuya.cloud.user.device.list"); // An API registered with a mobile number

		request.setApiVersion("1.0");
		request.setOs("Windows");
		request.setLang("en");

		// Package request parameters (interface needs specific parameters, please refer
		// to the API manual)
		Map<String, String> params = new HashMap<String, String>();
		params.put("schema", "nuwave");
		params.put("uid", id);

		// The request parameters are added to the HTTP request

		request.setParams(params);

		/*
		 * Initiate a request and get a response
		 * 
		 * If the request succeeds, the result in response will be the result of a JSON
		 * object encapsulation. If the request fails, look at errorMsg and errorCode
		 * and proceed accordingly.
		 */
		ResponseMessage response = client.sendRequest(request);
		System.out.println("sign : " + request.getSign());
		System.out.println(response.toString());

		return response;
	}
}