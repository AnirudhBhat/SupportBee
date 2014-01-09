import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class HTTPRequests {
	
	public String url, auth_token;
	
	public HTTPRequests(String url, String token, String id){
		this.url = url;
		this.auth_token = token;
	}
	

	public String request_star_ticket(String Url, String token, String id) {
		String url = this.url + "/tickets/" + id + "/star.json" + "?auth_token="
				+ auth_token;
		System.out.println(url);
		String url_params = id;
		return POST_Request(url, url_params);
	}

	
	
	private HttpURLConnection set_headers(String url)
			throws MalformedURLException {
		URL obj = new URL(url);
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public String GET_Request(String url) {
		StringBuffer response;
		String json_data = "";

		try {
			HttpURLConnection con = set_headers(url);
			System.out.println(con.getResponseCode());
			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				json_data = response.toString();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		}
		return json_data;
	}

	public String POST_Request(String url, String url_params) {
		StringBuffer response;
		String json_data = "";
		HttpsURLConnection con = null;
		try {
			con = (HttpsURLConnection) set_headers(url);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(url_params);
			wr.flush();
			wr.close();
			System.out.println(url_params);
			System.out.println(con.getResponseCode());
			if (con.getResponseCode() == 500)
				System.out.println(con.getErrorStream());
			if (con.getResponseCode() == 201) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				json_data = response.toString();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		} catch (Exception e) {
			con.getErrorStream();
		}
		return json_data;
	}

	public String DELETE_Request(String url, String url_params) {
		StringBuffer response;
		String json_data = "";
		HttpsURLConnection con = null;
		try {
			// url = "https://selfsolve.apple.com/wcResults.do";
			// url_params = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// URL obj = new URL(url);
			// con = (HttpsURLConnection) obj.openConnection();

			con = (HttpsURLConnection) set_headers(url);
			con.setDoOutput(true);
			con.setRequestMethod("DELETE");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(url_params);
			wr.flush();
			wr.close();
			System.out.println(url_params);
			System.out.println(con.getResponseCode());
			if (con.getResponseCode() == 500)
				System.out.println(con.getErrorStream());
			if (con.getResponseCode() == 201) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				json_data = response.toString();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		} catch (Exception e) {
			con.getErrorStream();
		}
		return json_data;
	}
}
