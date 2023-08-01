package Jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OnlineFeedbackManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GuestDAO guestDAO = new GuestDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        ResponseDAO responseDAO = new ResponseDAO();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Register as Guest");
            System.out.println("2. Give Feedback");
            System.out.println("3. Respond to Feedback");
            System.out.println("4. Update guest details");
            System.out.println("5. Delete guest details");
            System.out.println("6. View feedbacks of a guest");
            System.out.println("7. View response of a guest");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Register as Guest - Collect guest details and save to 'Guest' table
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your phone number: ");
                    String phone = scanner.nextLine();
                    
                    Guest guest = new Guest();
                    guest.setName(name);
                    guest.setEmail(email);
                    guest.setPhone(phone);

                    try {
                        guestDAO.save(guest);
                        System.out.println("Guest registered successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to register guest. Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Give Feedback - Collect feedback and save to 'Feedback' table
                	System.out.print("Enter your registered name: ");
                    String guestName = scanner.nextLine();

                    Feedback feedback = new Feedback();
                    feedback.setGuestName(guestName);

                    System.out.print("Enter your feedback: ");
                    String guestFeedback = scanner.nextLine();
                    feedback.setFeedback(guestFeedback);

                    try {
                        feedbackDAO.save(feedback);
                        System.out.println("Feedback submitted successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to submit feedback. Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Respond to Feedback - Collect response and save to 'Response' table
                    System.out.print("Enter the registered guest name: ");
                    String guestname = scanner.nextLine();
                    System.out.print("Enter your response: ");
                    String managementResponse = scanner.nextLine();
                    Response response = new Response();
                    response.setGuestname(guestname);
                    response.setResponse(managementResponse);

                    try {
                        responseDAO.save(response);
                        System.out.println("Response saved successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to save response. Error: " + e.getMessage());
                    }
                    break;
                case 4: // Update Guest
                    System.out.print("Enter the guest ID to update details: ");
                    int guestIdToUpdate = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        // First, retrieve the existing guest details to display before updating
                        Guest existingGuest = guestDAO.getGuestById(guestIdToUpdate);
                        if (existingGuest != null) {
                            System.out.println("Current Guest Details:");
                            System.out.println("Name: " + existingGuest.getName());
                            System.out.println("Email: " + existingGuest.getEmail());
                            System.out.println("Phone: " + existingGuest.getPhone());

                            // Collect new details from the user to update
                            System.out.print("Enter updated name: ");
                            String updatedName = scanner.nextLine();
                            System.out.print("Enter updated email: ");
                            String updatedEmail = scanner.nextLine();
                            System.out.print("Enter updated phone number: ");
                            String updatedPhone = scanner.nextLine();

                            // Create a new Guest object with updated details
                            Guest updatedGuest = new Guest();
                            updatedGuest.setId(guestIdToUpdate);
                            updatedGuest.setName(updatedName);
                            updatedGuest.setEmail(updatedEmail);
                            updatedGuest.setPhone(updatedPhone);

                            // Save the updated guest details
                            guestDAO.updateGuest(updatedGuest);
                            System.out.println("Guest details updated successfully!");
                        } else {
                            System.out.println("Guest with ID " + guestIdToUpdate + " not found.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to update guest details. Error: " + e.getMessage());
                    }
                    break;

                case 5: // Delete Guest
                    System.out.print("Enter the guest ID to delete: ");
                    int guestIdToDelete = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        guestDAO.deleteGuest(guestIdToDelete);
                        System.out.println("Guest with ID " + guestIdToDelete + " deleted successfully!");
                    } catch (SQLException e) {
                        System.out.println("Failed to delete guest. Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    // View all feedbacks for a particular guest
                    System.out.print("Enter the guest ID: ");
                    int guestId = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        List<Feedback> feedbacks = feedbackDAO.getFeedbacksByGuestId(guestId);
                        if (feedbacks.isEmpty()) {
                            System.out.println("No feedbacks found for the guest ID: " + guestId);
                        } else {
                            System.out.println("Feedbacks for Guest ID " + guestId + ":");
                            for (Feedback feedbackk : feedbacks) {
                                System.out.println("Feedback ID: " + feedbackk.getFeedbackid());
                                System.out.println("Feedback: " + feedbackk.getFeedback());
                                System.out.println("===================================");
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to retrieve feedbacks. Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    // View all responses for a particular guest
                    System.out.print("Enter the guest ID: ");
                    int guestIdForResponse = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        List<Response> responses = responseDAO.getResponsesByGuestId(guestIdForResponse);
                        if (responses.isEmpty()) {
                            System.out.println("No responses found for the guest ID: " + guestIdForResponse);
                        } else {
                            System.out.println("Responses for Guest ID " + guestIdForResponse + ":");
                            for (Response responsee : responses) {
                                System.out.println("Response ID: " + responsee.getResponseid());
                                System.out.println("Response: " + responsee.getResponse());
                                System.out.println("===================================");
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Failed to retrieve responses. Error: " + e.getMessage());
                    }
                    break;
                case 8:
                    // Exit the application
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

