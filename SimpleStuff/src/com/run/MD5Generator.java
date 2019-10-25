package com.run;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class MD5Generator {
    //Get the signature
//    private static String getSign(ApiRequestDO apiRequestDo, String secretKey) {
//        TreeMap<String, String> params = paramsBuild(apiRequestDo);
//        String signString = signAssembly(params, secretKey);
//        return signString;
//    }

// https://a1.tuyacn.com/api.json?a=tuya.cloud.user.list&time=1531925660&lang=en&v=1.0&os=Windows&clientid=uewwmf5vkytrrt9ddcak&sign=9aa12fb3dde7fb89146aba0bb3abfb97&postData={“countryCode”:“CN”}
	// https://a1.tuyacn.com/api.json?a=tuya.cloud.user.list&time=1531925660&lang=zh-Hans&v=1.0&os=Windows&clientid=uewwmf5vkytrrt9ddcak&sign=be2139afc26238b571bf6fa59dfa8e7e&postData={"strartTime":"1514786400","endTime":"1531925660","offset":0,"limit":1}
    // vvupv4hukrrrvrhf7x83mxhasnrs9pw4a=tuya.cloud.user.list|clientId=uewwmf5vkytrrt9ddcak|lang=en|os=Windows|postData={"strartTime":"1514786400","endTime":"1531925660","offset":0,"limit":1}|time=1531925660|v=1.0
  //MD5
	//22572f891e8ae2fdb70c6294413cb84f
	//be2139afc26238b571bf6fa59dfa8e7e
	
// 
	//https://a1.tuyaus.com/api.json?a=tuya.cloud.user.list&time=1490004310&lang=zh-Hans&v=1.0&os=Windows&clientid=uewwmf5vkytrrt9ddcak&sign=be2139afc26238b571bf6fa59dfa8e7e&postData={"strartTime":"1514786400","endTime":"1531925660","offset":0,"limit":1}	
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
//https://a1.tuyaus.com/api.json?a=tuya.cloud.user.list&time=1531925660&lang=zh-Hans&v=1.0&os=Windows&cliendId=uewwmf5vkytrrt9ddcak&sign=be2139afc26238b571bf6fa59dfa8e7e
//https://a1.tuyacn.com/api.json?a=tuya.p.weather.city.info.list&time=1490004310&lang=zh-Hans&v=1.0&os=Linux&clientid=accessKey&sign=9aa12fb3dde7fb89146aba0bb3abfb97&postData={“countryCode”:“CN”}
		
//a=tuya.cloud.user.list&time=1531925660&lang=zh-Hans&v=1.0&os=Windows&clientid=uewwmf5vkytrrt9ddcak&sign=be2139afc26238b571bf6fa59dfa8e7e&postData={"strartTime":"1514786400","endTime":"1531925660","offset":0,"limit":1}

    public static void main(String[] args) throws UnsupportedEncodingException {
//        ApiRequestDO apiRequestDo = new ApiRequestDO();
//
//        apiRequestDo.setApi("tuya.cloud.user.list");
//        ApiContextDO apiContextDO = new ApiContextDO();
//        apiContextDO.setApiVersion("1.0");
//        apiContextDO.setOs("Windows");
//        apiContextDO.setLang("en");
//
//        AppInfoDO appInfoDO = new AppInfoDO();
//        appInfoDO.setClientId("uewwmf5vkytrrt9ddcak");
//        apiRequestDo.setAppInfoDo(appInfoDO);
//
//        apiRequestDo.setT("1531925660");
//        apiRequestDo.setData("{ \"strartTime\": \"1514786400\",   \"endTime\": \"1531925660\",   \"offset\": 0   \"limit\": 1 }");
//
//        apiRequestDo.setApiContextDo(apiContextDO);
//
//        String s = getSign(apiRequestDo, "accessKey");
    	final String ACCESS_ID = "uewwmf5vkytrrt9ddcak"; // TODO: Contact Tuya Staff to get
//
    	final String ACCESS_KEY = "vvupv4hukrrrvrhf7x83mxhasnrs9pw4"; 
        //String s="vvupv4hukrrrvrhf7x83mxhasnrs9pw4a=tuya.cloud.user.list|clientid=uewwmf5vkytrrt9ddcak|lang=zh-Hans|os=Windows|postData={\"strartTime\":\"1514786400\",\"endTime\":\"1531925660\",\"offset\":0,\"limit\":1}|time=1531925660|v=1.0";
        long tlong = System.currentTimeMillis();
        System.out.println(tlong);
        String tt = "1544127874591";
        System.out.println("t :" + tt);
        String s = (ACCESS_ID + ACCESS_KEY + tt);
        //s = URLEncoder.encode(s, "UTF-8"); //encode(s);
        String sign = getMD5(s.getBytes()).toUpperCase();
        System.out.println("BEFORE:"+sign);
        // 3947df81bf06684972194390e58be131
        // 3947df81bf06684972194390e58be131
//        1532675867695
          //1542731845758
//        95860764995000
        String tok = ACCESS_ID + "cb43ced321147e17f10f8fbcd8bd64f2" + ACCESS_KEY + tt;
        sign = getMD5(tok.getBytes()).toUpperCase();
        System.out.println("AFTER:" + sign);
    }
}
