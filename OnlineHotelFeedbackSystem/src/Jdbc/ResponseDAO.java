package Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponseDAO extends AbstractDAO<Response>{
	
	private GuestDAO guestDAO; // Create an instance of GuestDAO
	private FeedbackDAO feedbackDAO; // Create an instance of FeedbackDAO
	
    public ResponseDAO() {
        guestDAO = new GuestDAO(); // Initialize the instance of GuestDAO
        feedbackDAO = new FeedbackDAO(); // Initialize the instance of FeedbackDAO
    }
	
    @Override
    public void save(Response response) throws SQLException {
    	// Code to insert the response data into the 'Response' table
    	try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Response (feedbackid, guestid, response) VALUES (?, ?, ?)")) {
    		int guestId = guestDAO.getGuestIdByName(response.getGuestname()); // Fetch the corresponding guestid
            int feedbackId = feedbackDAO.getFeedbackIdByGuestId(guestId); // Fetch the corresponding feedbackid

            stmt.setInt(1, feedbackId);
            stmt.setInt(2, guestId);
            stmt.setString(3, response.getResponse());
            stmt.executeUpdate();
           }
    }
    
    public List<Response> getResponsesByGuestId(int guestId) throws SQLException {
        List<Response> responses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT r.*, (SELECT feedback FROM feedback f WHERE f.feedbackid = r.feedbackid) AS feedback, " +
                             "(SELECT name FROM guest g WHERE g.id = r.guestid) AS guestName " +
                             "FROM response r WHERE r.guestid = ?")) {
            stmt.setInt(1, guestId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int responseId = rs.getInt("responseid");
                    int feedbackId = rs.getInt("feedbackid");
                    String responseText = rs.getString("response");
                    String guestName = rs.getString("guestName");
                    String feedbackText = rs.getString("feedback");

                    Response response = new Response(responseId, feedbackId, guestId, responseText);
                    response.setGuestname(guestName);
                    response.setFeedback(feedbackText);
                    responses.add(response);
                }
            }
        }

        return responses;
    }

}
