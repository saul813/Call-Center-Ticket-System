import java.util.*;
import java.util.Date;

//TICKET WIH ITS ATTRIBUTES
class Ticket {
    public String customerName;
    public String contact;
    public String category;
    public Date creationDate;
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
    public String getCustomerName() {
        return customerName;
    }

    public String getContact() {
        return contact;
    }

    public String getCategory() {
        return category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public String getAdditionalComments() {
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
//    public String getTicketDetails(){
//        return "\n-----Ticket Details-----\n"+
//                "CustomerName:            "+ this.customerName +"\n"+
//                "Contact:                 "+ this.contact      +"\n"+
//                "Category:                "+ this.category     +"\n"+
//                "CreationDate:            "+ this.creationDate +"\n"+
//                "IssueDescription:        "+ this.issueDescription+"\n"+
//                "Status:                  "+ this.status       +"\n"+
//                "Priority_Level:          "+ this.priorityLevel +"\n"+
//                "Additional_Comments:     "+ this.additionalComments +"\n"+
//                "--------------------------";
//    }
//
//}


    // VIEW DETAILED INFORMATION: Expands all properties into a clean report format
    public void displayDetailedReport() {
        System.out.println("\n========================================");
        System.out.println("             TICKET REPORT              ");
        System.out.println("========================================");
        System.out.println(" Customer Name : " + this.getCustomerName());
        System.out.println(" Contact Info  : " + this.getContact());
        System.out.println(" Category      : " + this.getCategory());
        System.out.println(" Timestamp     : " + this.getCreationDate());
        System.out.println(" Status        : [" + this.getStatus() + "]");
        System.out.println(" Priority Tier : [" + this.getPriorityLevel() + "]");
        System.out.println("----------------------------------------");
        System.out.println(" ISSUE DESCRIPTION:");
        System.out.println(" " + this.getIssueDescription());
        System.out.println("----------------------------------------");
        System.out.println(" PROGRESS COMMENTS:");
        System.out.println(" " + this.getAdditionalComments());
        System.out.println("========================================\n");
    }
}







// CALL AGENT TO HANDLE CALL CENTRE ACTIVITIES
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

    //NAVIGATION.....Through all the Tickets
    public ArrayList<Ticket> getAllTickets(){
        return this.ticketsStorage;
    }

    //VIEW.....Dashboard to View all the tickets
    public void printSummaryDashboard(){
        if(ticketsStorage.isEmpty()){
            System.out.println("No tickets Found in the System.");
            return;
        }

        System.out.println("\n=========== ACTIVE TICKETS DASHBOARD ==========");
        System.out.printf("%-5s | %-12s | %-12s | %-10s\n", "Index", "Customer", "Category", "Status");
        System.out.println("-------------------------------------------------------------------------");

        for (int i = 0; i < ticketsStorage.size(); i++){
            Ticket t = ticketsStorage.get(i);
            //Print a clean single-line summary for easy scrolling navigation
            System.out.printf("#%-4d | %-12s | %-12s | %-10s\n",
                    i, t.getCustomerName(), t.getCategory(), t.getStatus());
        }
        System.out.println("======================================================\n");
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


//AGENT CENTRE.................RUNNER CLASS

public class AgentCentre {
    public static void main(String[] args) {
        CallAgent ca = new CallAgent();
        Scanner scanner = new Scanner(System.in);

        // Pre-populating some mock data so the app isn't blank on startup
        ca.createTicket("Alice", "alice@email.com", "Network", "Wi-Fi dropping frequently", "Open", "Medium", "No Comments yet");
        ca.createTicket("Bob", "bob@email.com", "Billing", "Overcharged on monthly statement", "Open", "Medium", "No Comments yet");

        System.out.println("==============================================");
        System.out.println("    WELCOME TO THE CALL CENTRE AGENT SYSTEM   ");
        System.out.println("==============================================");

        while (true) {
            // 1. Display Menu Options
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Active Tickets Dashboard (Navigate)");
            System.out.println("2. View Detailed Ticket Information");
            System.out.println("3. Create a New Ticket");
            System.out.println("4. Update an Existing Ticket");
            System.out.println("5. Delete a Ticket");
            System.out.println("6. Exit System");
            System.out.print("Select an option (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Dashboard View
                    ca.printSummaryDashboard();
                    break;

                case "2":
                    // View Deep Details
                    System.out.print("Enter Customer Name to look up details: ");
                    String detailName = scanner.nextLine().trim();
                    Ticket foundTicket = ca.getTicketByCustomerName(detailName);
                    if (foundTicket != null) {
                        foundTicket.displayDetailedReport();
                    } else {
                        System.out.println("Error: No ticket found for customer: " + detailName);
                    }
                    break;

                case "3":
                    // Form to Create Ticket
                    System.out.println("\n--- CREATE NEW TICKET FORM ---");
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Contact (Email/Phone): ");
                    String contact = scanner.nextLine().trim();
                    System.out.print("Enter Category (e.g., Network, Hardware): ");
                    String category = scanner.nextLine().trim();
                    System.out.print("Enter Issue Description: ");
                    String desc = scanner.nextLine().trim();

                    // Default values for initial creation
                    String status = "Open";
                    String priority = "Medium";
                    String comment = "Initial entry logged.";

                    ca.createTicket(name, contact, category, desc, status, priority, comment);
                    System.out.println("Success: New ticket generated for " + name);
                    break;

                case "4":
                    // Form to Update Attributes
                    System.out.println("\n--- UPDATE TICKET FORM ---");
                    System.out.print("Enter Customer Name to update: ");
                    String updateName = scanner.nextLine().trim();

                    // Verify ticket exists before asking for details
                    if (ca.getTicketByCustomerName(updateName) == null) {
                        System.out.println("Error: Ticket does not exist.");
                        break;
                    }

                    System.out.print("Enter New Status (Open/In Progress/Resolved): ");
                    String newStatus = scanner.nextLine().trim();
                    System.out.print("Enter New Priority Level (Low/Medium/High): ");
                    String newPriority = scanner.nextLine().trim();
                    System.out.print("Enter Additional Progress Comments: ");
                    String newComment = scanner.nextLine().trim();

                    boolean updateSuccess = ca.updateTicket(updateName, newStatus, newPriority, newComment);
                    if (updateSuccess) {
                        System.out.println("Success: Ticket attributes updated cleanly.");
                    } else {
                        System.out.println("Error: Update failed unexpectedly.");
                    }
                    break;

                case "5":
                    // Form to Delete Ticket
                    System.out.print("Enter Customer Name to DELETE ticket: ");
                    String deleteName = scanner.nextLine().trim();
                    boolean deleteSuccess = ca.deleteTicketByCustomer(deleteName);
                    if (deleteSuccess) {
                        System.out.println("Success: Ticket removed completely from data stream.");
                    } else {
                        System.out.println("Error: No ticket matches that name.");
                    }
                    break;

                case "6":
                    // System Termination
                    System.out.println("Shutting down terminal connection. Goodbye!");
                    scanner.close(); // Clean up system resources
                    System.exit(0);  // Stops the Java Runtime
                    break;

                default:
                    System.out.println("⚠Invalid entry. Please choose a number between 1 and 6.");
                    break;
            }
        }
    }
}
