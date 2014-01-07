import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class SupportBee {
	private String Base_Url = "https://%s.supportbee.com";
	private String account_name, auth_token, url;
	public SupportBee(String account_name, String auth_token){
		this.account_name = account_name;
		this.auth_token = auth_token;
		this.url = String.format(this.Base_Url, this.account_name);
	}
	
	private String create_json(String subject, String requester_name, String requester_email, String copied_emails, String notify_requester, String content){
		JSONArray cc = new JSONArray();
		cc.add(requester_email);
		
		JSONObject cont = new JSONObject();
		cont.put("text", content);
		cont.put("html", "");
		
		JSONObject data = new JSONObject();
		data.put("subject", subject);
		data.put("requester_name", requester_name);
		data.put("requester_email", requester_email);
		data.put("cc", cc);
		data.put("content", cont);
		
		JSONObject ticket = new JSONObject();
		ticket.put("ticket", data);
		StringWriter out = new StringWriter();
		try {
			ticket.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonText = out.toString();
		return jsonText;
	}
	
	private HttpURLConnection set_headers(String url) throws MalformedURLException{
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
	
	private String request(String url, String... params){
		String per_page = params.length > 0? params[0]: "none";
		String page = params.length > 1? params[1]: "none";
		String label = params.length > 2?params[2]: "none";
		url = this.url + "/tickets.json?auth_token=" + this.auth_token + "&per_page=" + per_page + "&page=" + page + "&label=" + label; 
		return GET_Request(url);
		
	}
	
	private String request_search_tickets(String url, String query, String per_page, String page, String spam, String trash){
		url = this.url + "/tickets/search.json?auth_token=" + this.auth_token + "&query=" + query +  "&per_page=" + per_page + "&page=" + page + "&spam=" + spam + "&trash=" + trash;
		return GET_Request(url);
	}
	
	private String request_create_ticket(String url, String subject, String requester_name, String requester_email, String copied_emails, String notify_requester, String content){
		//String url_params =  "subject=" + subject + "&requester_name=" + requester_name + "&requester_email=" + requester_email + "&copied_emails=" + copied_emails + "&notify_requester=" + notify_requester + "&content=" + content;
		String url_params = create_json(subject, requester_name, requester_email, copied_emails, notify_requester, content);
		url = this.url + "/tickets.json";
		System.out.println(url);
		return POST_Request(url, url_params);
	}
	
	private String request_show_tickets(String id){
		url = this.url  + "/tickets/" + id + ".json" + "?auth_token=" + this.auth_token;
		System.out.println(url);
		return GET_Request(url);
	}
	
	private String request_archive_ticket(String id){
		url = this.url  + "/tickets/" + id + "/archive.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return POST_Request(url, url_params);
	}
	
	private String request_unarchive_ticket(String id){
		url = this.url  + "/tickets/" + id + "/archive.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return DELETE_Request(url, url_params);
	}
	
	private String request_star_ticket(String id){
		url = this.url  + "/tickets/" + id + "/star.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return POST_Request(url, url_params);
	}
	
	
	private String request_unstar_ticket(String id){
		url = this.url  + "/tickets/" + id + "/star.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return DELETE_Request(url, url_params);
	}
	
	
	private String request_spam_ticket(String id){
		url = this.url  + "/tickets/" + id + "/spam.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return POST_Request(url, url_params);
	}
	
	
	private String request_unspam_ticket(String id){
		url = this.url  + "/tickets/" + id + "/spam.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return DELETE_Request(url, url_params);
	}
	
	
	private String request_trash_ticket(String id){
		url = this.url  + "/tickets/" + id + "/trash.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return POST_Request(url, url_params);
	}
	
	
	private String request_untrash_ticket(String id){
		url = this.url  + "/tickets/" + id + "/trash.json" + "?auth_token=" + this.auth_token;
		String url_params = id;
		return DELETE_Request(url, url_params);
	}
	
	
	private String request_fetch_reply_ticket(String id){
		url = this.url  + "/tickets/" + id + "/replies.json" + "?auth_token=" + this.auth_token;
		//String url_params = id;
		return GET_Request(url);
	}
	
	
	private String request_show_reply_ticket(String ticket_id, String id){
		url = this.url  + "/tickets/" + ticket_id + "/replies/" + id + ".json" + "?auth_token=" + this.auth_token;
		//String url_params = id;
		return GET_Request(url);
	}
	
	
	private String request_fetch_comment_ticket(String id){
		url = this.url  + "/tickets/" + id + "/comments.json" + "?auth_token=" + this.auth_token;
		//String url_params = id;
		return GET_Request(url);
	}
	
	
	private String request_fetch_agents(boolean with_invited){
		url = this.url + "/users.json" + "?auth_token=" + this.auth_token + "?with_invited=" + with_invited;
		return GET_Request(url);
	}
	
	
	private String request_show_users(String id){
		url = this.url + "/users/" + id + ".json" + "?auth_token=" + this.auth_token;
		return GET_Request(url);
	}
	
	
	private String request_fetch_labels(){
		url = this.url + "/labels.json" + "?auth_token=" + this.auth_token;
		return GET_Request(url);
	}
	
	
	private String request_add_labels(String ticket_id, String label){
		url = this.url + "/tickets" + ticket_id + "/labels/" + label + ".json" + "?auth_token=" + this.auth_token;
		String url_params = "ticket_id=" + ticket_id + "&label=" + label;
		return POST_Request(url, url_params);
	}
	
	
	private String request_remove_labels(String ticket_id, String label){
		url = this.url + "/tickets" + ticket_id + "/labels/" + label + ".json" + "?auth_token=" + this.auth_token;
		String url_params = ticket_id + "&" + label;
		return DELETE_Request(url, url_params);
	}
	
	private String GET_Request(String url){
		StringBuffer response;
		String json_data = "";
		
		try {
			HttpURLConnection con = set_headers(url);
			System.out.println(con.getResponseCode());
			if (con.getResponseCode() == 200){
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
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
		}catch (IOException e){
			
		}
		return json_data;
	}
	
	private String POST_Request(String url, String url_params){
		StringBuffer response;
		String json_data = "";
		HttpsURLConnection con = null;
		try{
			con = (HttpsURLConnection) set_headers(url);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
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
			if (con.getResponseCode() == 201){
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
		 
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				json_data = response.toString();
			}
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			
		}catch (Exception e){
			con.getErrorStream();
		}
		return json_data;
	}
	
	
	
	private String DELETE_Request(String url, String url_params){
		StringBuffer response;
		String json_data = "";
		HttpsURLConnection con = null;
		try{
			//url = "https://selfsolve.apple.com/wcResults.do";
			//url_params = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
			
			//URL obj = new URL(url);
			//con =  (HttpsURLConnection) obj.openConnection();
			
			con = (HttpsURLConnection) set_headers(url);
			con.setDoOutput(true);
			con.setRequestMethod("DELETE");
			con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(url_params);
			wr.flush();
			wr.close();
			System.out.println(url_params);
			System.out.println(con.getResponseCode());
			if (con.getResponseCode() == 500)
				System.out.println(con.getErrorStream());
			if (con.getResponseCode() == 201){
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
		 
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				json_data = response.toString();
			}
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			
		}catch (Exception e){
			con.getErrorStream();
		}
		return json_data;
	}
	
	
	
	public String fetch_ticket(String per_page, String page, String label ){
		//HttpURLConnection con = set_headers(this.url + "");
		String data = request(this.url, per_page, page, label);
		return data;
		
	}
	
	public String fetch_ticket(){
		String data = request(this.url, "15", "1", "none");
		return data;
	}
	
	
	public String search_tickets(String query, String per_page, String page, String spam, String trash ){
		String data = request_search_tickets(this.url, query, per_page, page, spam, trash);
		return data;
	}
	
	public String search_tickets(String query){
		String data = request_search_tickets(this.url, query, "15", "1", "false", "false");
		return data;
	}
	
	public String create_ticket(String subject, String requester_name, String requester_email, String copied_emails, String notify_requester, String content){
		String data = request_create_ticket(this.url, subject, requester_name, requester_email, copied_emails, notify_requester, content);
		return data;
	}
	
	public String create_ticket(String subject, String requester_email, String content){
		String data = request_create_ticket(this.url, subject, "none", requester_email, "none", "false", content);
		return data;
	}
	
	public String show_tickets(String id){
		String data = request_show_tickets(id);
		return data;
	}
	
	public String archive_ticket(String id){
		String data = request_archive_ticket(id);
		return data;
	}
	
	public String unarchive_ticket(String id){
		String data = request_unarchive_ticket(id);
		return data;
	}
	
	public String star_ticket(String id){
		String data = request_star_ticket(id);
		return data;
	}
	
	public String unstar_ticket(String id){
		String data = request_unstar_ticket(id);
		return data;
	}
	
	public String spam_ticket(String id){
		String data = request_spam_ticket(id);
		return data;
	}
	
	public String unspam_ticket(String id){
		String data = request_unspam_ticket(id);
		return data;
	}
	
	public String trash_ticket(String id){
		String data = request_trash_ticket(id);
		return data;
	}
	
	public String untrash_ticket(String id){
		String data = request_untrash_ticket(id);
		return data;
	}
	
	public String fetch_reply_ticket(String id){
		String data = request_fetch_reply_ticket(id);
		return data;
	}
	
	public String show_reply_ticket(String ticket_id, String id){
		String data = request_show_reply_ticket(ticket_id, id);
		return data;
	}
	
	public String fetch_comment_ticket(String id){
		String data = request_fetch_comment_ticket(id);
		return data;
	}
	
	public String fetch_agents(boolean with_invited){
		String data = request_fetch_agents(with_invited);
		return data;
	}
	
	
	public String fetch_agents(){
		String data = request_fetch_agents(false);
		return data;
	}
	
	public String show_users(String id){
		String data = request_show_users(id);
		return data;
	}
	
	public String fetch_labels(){
		String data = request_fetch_labels();
		return data;
	}
	
	/*public String add_labels(String ticket_id, String label){
		String data = request_add_labels(ticket_id, label);
		return data;
	}
	
	public String remove_labels(String ticket_id, String label){
		String data = request_remove_labels(ticket_id, label);
		return data;
	}*/
	
}
