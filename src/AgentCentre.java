import java.util.ArrayList;
import java.util.Date;

class Ticket{
    public String customerName;
    public String contact;
    public String category;
    public Date   creationDate;
    public String issueDescription;
    public String status;
    public String priorityLevel;
    public String additionalComments;

    public Ticket(String customerName, String contact, String category, String issueDescription, String status, String priorityLevel, String additionalComments) {
        this.customerName = customerName;
        this.contact = contact;
        this.category = category;
        this.creationDate = new Date(); // Automatically sets current date and time
        this.issueDescription = issueDescription;
        this.status = status;
        this.priorityLevel = priorityLevel;
        this.additionalComments = additionalComments;
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
    public String getStatus(){
        return status;
    }
    public String getPriorityLevel(){
        return priorityLevel;
    }
    public String getAdditionalComments(){
        return additionalComments;
    }

    //Setters so that these attributes can be updated
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }




    //Method to fetch all the ticket details
    public String getTicketDetails(){
        return "\n-----Ticket Details-----\n"+
                "CustomerName:            "+ this.customerName +"\n"+
                "Contact:                 "+ this.contact      +"\n"+
                "Category:                "+ this.category     +"\n"+
                "CreationDate:            "+ this.creationDate +"\n"+
                "IssueDescription:        "+ this.issueDescription+"\n"+
                "Status:                  "+ this.status       +"\n"+
                "Priority_Level:          "+ this.priorityLevel +"\n"+
                "Additional_Comments:     "+ this.additionalComments +"\n"+
                "--------------------------";
    }

}

//A callAgent to coordinate the ticket activities
class CallAgent{

    //An ArrayList to store the tickets
    ArrayList<Ticket> ticketsStorage = new ArrayList<>();

    //CallAgent Creates Ticket and saves ticket to the tickets storage
    public Ticket createTicket(String customerName, String contact, String category, String issueDescription, String status, String priorityLevel, String additionalComments){
        Ticket freshTicket = new Ticket(customerName,contact,category,issueDescription,status,priorityLevel,additionalComments);
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

    //CallAgent Deletes a ticket by customerName
    public boolean deleteTicketByCustomer(String customerName){
        for(int i = 0; i <= ticketsStorage.size(); i++){
            if(ticketsStorage.get(i).getCustomerName().equalsIgnoreCase(customerName)){
                ticketsStorage.remove(i);
                return true;
            }
        }
        return false;
    }

    //CallAgent updates a ticket
    public boolean updateTicket(String customerName, String newStatus, String newPriorityLevel, String newAdditionalComments){
        //Loop through List to find a particular ticket
        for(int i = 0; i <= ticketsStorage.size(); i++){
            if(ticketsStorage.get(i).getCustomerName().equalsIgnoreCase(customerName)){
                //Target that particular ticket matching the customer name
                Ticket particularTicket = ticketsStorage.get(i);

                //Apply the updates to that particular ticket
                particularTicket.setStatus(newStatus);
                particularTicket.setPriorityLevel(newPriorityLevel);
                particularTicket.setAdditionalComments(newAdditionalComments);

                return true; //Updates complete
            }
        }
        return false; // Updates failed
    }
}


//AgentCentre......Ticket Runner(Main Class)
public class AgentCentre {
    public static void main(String[] args){
        CallAgent ca = new CallAgent();

        //Call Agent creating the tickets
        ca.createTicket("Alice", "alice@email.com", "Network", "Wi-Fi dropping frequently","Open","Medium","No Comments yet");
        ca.createTicket("Bob", "bob@email.com", "Billing", "Overcharged on monthly statement","Open","Medium","No Comments yet");

        //Call Agent Searching for ticket by customer Name
        Ticket ticketfound = ca.getTicketByCustomerName("Alice");
        if(ticketfound != null){
            System.out.println(ticketfound.getTicketDetails());
        }else {
            System.out.println("Error: Ticket not found.");
        }

        //Deleting a ticket
//        boolean isDeleted = ca.deleteTicketByCustomer("Alice");
//        if(isDeleted){
//            System.out.println("Ticket Found and Deleted");
//        }else{
//            System.out.println("Error: Ticket doesn't exist");
//        }

        //Updating ticket
        boolean isUpdated = ca.updateTicket("Alice","Open","Low","Assigned to a particular developer");
        if(isUpdated){
            System.out.println("Ticket updated Successfully");
        }else {
            System.out.println("System failed to update Ticket");
        }
    }
}
