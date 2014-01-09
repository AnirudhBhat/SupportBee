import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Ticket {
	Long id;
	public Ticket(JSONObject jsonobj){
		//System.out.println(jsonobj.get("data"));
		String jsonText = jsonobj.get("data").toString();
		System.out.println("JSON STRING: " + jsonText);
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(jsonText);
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject tickett = (JSONObject) jsonObject.get("ticket");
			id =  (Long) tickett.get("id");
			//System.out.println(id);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String star(){
		SupportBee sb = new SupportBee();
		String url = String.format(sb.Base_Url, sb.name);;
		System.out.println(url);
		String token = sb.token;
		String ID = id.toString();
		HTTPRequests post = new HTTPRequests(url, token, ID);
		return post.request_star_ticket(url, token, ID);
	}
}
