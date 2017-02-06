package com.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class StockRead {

	public static void main(String[] args) {
		  try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
				"http://finance.yahoo.com/webservice/v1/symbols/COALINDIA.NS/quote?format=json");
			getRequest.addHeader("Accept-Ranges", "application/json");
			/*getRequest.addHeader("Accept-Ranges", "none");
			getRequest.addHeader("Cache-Control", "no-cache, no-store, must-revalidate, proxy-revalidate");
			getRequest.addHeader("Connection", "Keep-Alive");
			getRequest.addHeader("Content-Type", "text/html; charset=UTF-8");
			getRequest.addHeader("Date", "Mon, 27 Jun 2016 10:56:10 GMT");
			getRequest.addHeader("Expires", "Fri, 01 Jan 1990 00:00:00 GMT");
			getRequest.addHeader("Pragma", "no-cache");
			getRequest.addHeader("Proxy-Connection", "Keep-Alive");
			getRequest.addHeader("Server", "GSE");
			getRequest.addHeader("Transfer-Encoding", "chunked");
			getRequest.addHeader("Vary", "Accept-Encoding");
			getRequest.addHeader("X-Content-Type-Options", "nosniff");
			getRequest.addHeader("X-Frame-Options", "SAMEORIGIN");
			getRequest.addHeader("X-UA-Compatible", "IE=EmulateIE7");
			getRequest.addHeader("X-XSS-Protection", "1; mode=block");*/

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (ClientProtocolException e) {
		
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }

		}
	

}

