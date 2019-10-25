package com.tuya.smart.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;

import com.alibaba.fastjson.JSON;
import com.tuya.smart.model.ResponseMessage;

public class CloudResponseHandler implements ResponseHandler<ResponseMessage> {
	public ResponseMessage handleResponse(HttpResponse response) throws IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		if (entity == null) {
			throw new ClientProtocolException("Response contains no content");
		}
		ContentType contentType = ContentType.getOrDefault(entity);
		Charset charset = contentType.getCharset();
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		StringBuilder sb = new StringBuilder();
		for (;;) {
			String tmp = reader.readLine();
			if (tmp == null) {
				break;
			}
			sb.append(tmp).append("\n");
		}
		try {
			Map<String, Object> resultMap = (Map) JSON.parseObject(sb.toString(), HashMap.class);
			ResponseMessage msg = new ResponseMessage();
			Boolean success = (Boolean) resultMap.get("success");
			if (success.booleanValue()) {
				msg.setSuccess(true);
				msg.setResult(resultMap.get("result"));
			} else {
				msg.setSuccess(false);
				msg.setErrorMsg((String) resultMap.get("errorMsg"));
				msg.setErrorCode((String) resultMap.get("errorCode"));
			}
			return msg;
		} catch (Exception e) {
			System.out.println("??http???json???:");
			e.printStackTrace();
		}
		return null;
	}
}
