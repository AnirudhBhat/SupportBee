public class supportbeedemo {
	public static void main(String[] args){
		SupportBee sb = new SupportBee("ENTER YOU USER NAME", "ENTER YOUR API TOKEN");
		System.out.println(sb.create_ticket("This is a Test subject", "test@gmail.com", "Yet another Demo Content"));
		//System.out.println(sb.show_tickets("1882744"));
		//System.out.println(sb.archive_ticket("1882744"));
		//System.out.println(sb.unarchive_ticket("1882744"));
		//System.out.println(sb.star_ticket("1882744"));
		//System.out.println(sb.unstar_ticket("1882744"));
		//System.out.println(sb.spam_ticket("1882744"));
		//System.out.println(sb.unspam_ticket("1882744"));
		//System.out.println(sb.untrash_ticket("1882744"));
		//System.out.println(sb.fetch_reply_ticket("1882744"));
		//System.out.println(sb.show_reply_ticket("1882744", "1"));
		//System.out.println(sb.fetch_comment_ticket("1882744"));
		//System.out.println(sb.fetch_agents(true));
		//System.out.println(sb.fetch_labels());
		//System.out.println(sb.add_labels("1882744", "testlabelname"));
		//System.out.println(sb.remove_labels("1882744", "important"));
	}
}
