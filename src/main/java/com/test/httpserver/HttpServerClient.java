package com.test.httpserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServerClient {

	public static void main(String[] args) {

		String url = "http://localhost:8080/welcome";

		try {
			URL obj = new URL(url);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			System.out.println("\nSending 'POST' request to URL " + url);

			// add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java8");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			// Send post request
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());

			BufferedReader in = null;
			wr.writeBytes("REQUEST_MESSAGE=Hi, who are you?");
			wr.flush();

			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			String inputLine;
			int i = 0;

			if (in == null)
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
