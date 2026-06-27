import java.util.ArrayList;
import java.util.Date;

class Ticket{
    public String customerName;
    public String contact;
    public String category;
    public Date   creationDate;
    public String issueDescription;

    public Ticket(String customerName, String contact, String category, String issueDescription) {
        this.customerName = customerName;
        this.contact = contact;
        this.category = category;
        this.creationDate = new Date(); // Automatically sets current date and time
        this.issueDescription = issueDescription;
    }

//    public Ticket createTicket(String customerName, String contact, String category, String issueDescription){
//        if((customerName == null || customerName.isEmpty()) && (contact == null || contact.isEmpty())){
//            throw new IllegalArgumentException("Customer name or contact should not be empty");
//        }
//        return new Ticket(customerName, contact,category,issueDescription);
//    }

    //Getters
    public String getCustomerName(){
        return customerName;
    }
    public String getContact(){
        return contact;
    }
    public String getCategory(){
        return category;
    }
    public Date getCreationDate(){
        return creationDate;
    }
    public String getIssueDescription(){
        return issueDescription;
    }


    //Method to fetch all the ticket details
    public String getTicketDetails(){
        return "\n-----Ticket Details-----\n"+
                "CustomerName:            "+ this.customerName +"\n"+
                "Contact:                 "+ this.contact      +"\n"+
                "Category:                "+ this.category     +"\n"+
                "CreationDate:            "+ this.creationDate +"\n"+
                "issueDescription:        "+ this.issueDescription+"\n"+
                "--------------------------";
    }

}

//A callAgent to coordinate the ticket activities
class CallAgent{

    //An ArrayList to store the tickets
    ArrayList<Ticket> ticketsStorage = new ArrayList<>();

    //CallAgent Creates Ticket and saves ticket to the tickets storage
    public Ticket createTicket(String customerName, String contact, String category, String issueDescription){
        Ticket freshTicket = new Ticket(customerName,contact,category,issueDescription);
        ticketsStorage.add(freshTicket);
        return freshTicket;
    }

    //CallAgent Reads a ticket by customer name
    public Ticket getTicketByCustomerName(String customerName){
        for(Ticket currentTicket: ticketsStorage){
            if(currentTicket.getCustomerName().equalsIgnoreCase(customerName)){
                return currentTicket; //Ticket Found
            }
        }
        return null; //Ticket not found
    }
}


//AgentCentre......Ticket Runner(Main Class)
public class AgentCentre {
    public static void main(String[] args){
        CallAgent ca = new CallAgent();

        //Call Agent creating the tickets
        ca.createTicket("Alice", "alice@email.com", "Network", "Wi-Fi dropping frequently");
        ca.createTicket("Bob", "bob@email.com", "Billing", "Overcharged on monthly statement");

        //Searching for ticket by customer Name
        Ticket ticketfound = ca.getTicketByCustomerName("Alice");
        if(ticketfound != null){
            System.out.println(ticketfound.getTicketDetails());
        }
    }
}
