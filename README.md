SupportBee
==========

Java wrapper for SupportBee API.

QuickStart
==========

SupportBee sb = new SupportBee("Enter your user name", "Enter your API token");

// To show tickets.

sb.show_tickets("Enter ticket number");

// To create ticket.

sb.create_ticket("subject", "requester_email", "content");

// To archive tickets.

sb.archive_ticket("Enter ticket id");

// To unarchive ticket.

sb.unarchive_ticket("Enter ticket id");

// To star ticket

sb.star_ticket("Enter ticket id");

// To unstar ticket.

sb.unstar_ticket("Enter ticket id");

// To mark ticket as spam.

sb.spam_ticket("Enter ticket id");

// To unspam ticket marked as spam.

sb.unspam_ticket("Enter ticket id");

// To mark ticket as trash.

sb.trash_ticket("Enter ticket id");

// To untrash a ticket.

sb.untrash_ticket("Enter ticket id");

// To fetch reply for given ticket.

sb.fetch_reply_ticket("Enter ticket id");

// To show reply for ticket.

sb.show_reply_ticket("enter ticket id", "id");

// To fetch comment for a ticket.

sb.fetch_comment_ticket("Enter ticket id");

// To fetch agents.

sb.fetch_agents(true);

// To fetch labels.

sb.fetch_labels();










