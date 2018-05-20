package com.linktrust.fuyao;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class httpClientTest {
	
	public static void main(String[] args) throws HttpException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.sojson.com/open/api/weather/json.shtml?city=洛阳");
		HttpResponse response = httpClient.execute(httpGet);
		System.out.println(EntityUtils.toString(response.getEntity()));
	}
}
