SupportBee
==========

Java wrapper for SupportBee API.

QuickStart
==========
```java

SupportBee sb = new SupportBee("Enter your user name", "Enter your API token");


CreateTicket ct = new CreateTicket();
ct.subject = "subject";
ct.content = "content";
ct.requester_email = "requester_email";

Ticket ticket = sb.create_ticket(ct);

ticket.star();









