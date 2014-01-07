SupportBee
==========

Java wrapper for SupportBee API.

QuickStart
==========
<code>
SupportBee sb = new SupportBee("Enter your user name", "Enter your API token");


sb.show_tickets("Enter ticket number");   // To show tickets.



sb.create_ticket("subject", "requester_email", "content");    // To create ticket.   


sb.archive_ticket("Enter ticket id");    // To archive tickets.


sb.unarchive_ticket("Enter ticket id");    // To unarchive ticket.


sb.star_ticket("Enter ticket id");    // To star ticket


sb.unstar_ticket("Enter ticket id");    // To unstar ticket.


sb.spam_ticket("Enter ticket id");    // To mark ticket as spam.


sb.unspam_ticket("Enter ticket id");    // To unspam ticket marked as spam.


sb.trash_ticket("Enter ticket id");    // To mark ticket as trash.


sb.untrash_ticket("Enter ticket id");    // To untrash a ticket.


sb.fetch_reply_ticket("Enter ticket id");    // To fetch reply for given ticket.


sb.show_reply_ticket("enter ticket id", "id");    // To show reply for ticket.


sb.fetch_comment_ticket("Enter ticket id");    // To fetch comment for a ticket.


sb.fetch_agents(true);    // To fetch agents.


sb.fetch_labels();    // To fetch labels.
</code>









