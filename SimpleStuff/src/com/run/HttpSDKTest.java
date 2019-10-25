package com.run;
import java.util.HashMap;
import java.util.Map;

import com.tuya.smart.TuyaCloudClient;
import com.tuya.smart.model.RequestMessage;
import com.tuya.smart.model.ResponseMessage;

public class HttpSDKTest {

            private static final String END_URI = "https://a1.tuyaus.com/api.json";// Call China's API (you can replace it with other available areas)


            private static final String ACCESS_ID = "uewwmf5vkytrrt9ddcak"; // TODO: Contact Tuya Staff to get
            
            private static final String ACCESS_KEY = "vvupv4hukrrrvrhf7x83mxhasnrs9pw4"; // TODO: Contact Tuya Staff to get

    public static void main(String[] args) {
        /*
         Create client，accessId&accessKey tuya provides
         accessId as clientId
         accessKey for signature
        */
        TuyaCloudClient client = new TuyaCloudClient(ACCESS_ID, ACCESS_KEY, END_URI);
        

        // Constitute HTTPS request 
        RequestMessage request = new RequestMessage();
        request.setApi("tuya.cloud.user.list"); //An API registered with a mobile number

        request.setApiVersion("1.0");
        request.setOs("Windows");
        request.setLang("zh");

        // Package request parameters (interface needs specific parameters, please refer to the API manual)
        Map<String, String> params = new HashMap<String, String>();
//        params.put("countryCode", "1");
//        params.put("mobile", "8476879067");
        params.put("startTime", "1504786400");
        params.put("endTime", "1531925660");
        params.put("offset", "0");
        params.put("limit", "10");
        /*
         Note:
            In addition to a few interfactes for  registration and obtaining statistics, most of the interface needs sessionId.

           Refer to the API manual for sessionId.

            You can get the result from the registration and login interface and return the result field as sid

        */

        // The request parameters are added to the HTTP request

        request.setParams(params);
        

        /*
         Initiate a request and get a response

         If the request succeeds, the result in response will be the result of a JSON object encapsulation.
         If the request fails, look at errorMsg and errorCode and proceed accordingly.
        */
        ResponseMessage response = client.sendRequest(request);
        System.out.println("sign : " + request.getSign());
        System.out.println(response.toString());
    }
}