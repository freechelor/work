package com.tuya.smart.internal;

import com.tuya.smart.model.RequestMessage;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

public class AtopThirdCloudMobileSignUtil {
	private static TreeMap<String, String> paramsBuild(RequestMessage request) {
		TreeMap<String, String> params = new TreeMap();
		params.put("a", request.getApi());
		params.put("v", request.getApiVersion());
		params.put("lang", request.getLang());
		params.put("deviceId", request.getDeviceid());
		params.put("os", request.getOs());
		params.put("clientId", request.getClientId());
		params.put("time", String.valueOf(request.getTime()));
		if (StringUtils.isNotBlank(request.getSession())) {
			params.put("sid", request.getSession());
		}
		if (StringUtils.isNotBlank(request.getPostData())) {
			params.put("postData", request.getPostData());
		}
		return params;
	}

	private static String signAssembly(TreeMap<String, String> params, String secretKey) {
		StringBuilder str = new StringBuilder();
		str.append(secretKey);
		Set<String> keySet = params.keySet();
		Iterator<String> iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (!StringUtils.isBlank((String) params.get(key))) {
				str.append(key);
				str.append("=");
				str.append((String) params.get(key));
				str.append("|");
			}
		}
		String strValue = str.toString();
		strValue = strValue.substring(0, strValue.length() - 1);
		return strValue;
	}

	public static String getSign(RequestMessage request, String secretKey) {
		TreeMap<String, String> params = paramsBuild(request);
		String signString = signAssembly(params, secretKey);
		String sign = MD5Util.getMD5(signString.getBytes());
		return sign;
	}
}
