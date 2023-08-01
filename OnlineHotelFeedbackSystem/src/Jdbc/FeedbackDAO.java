package Jdbc;

//import java.sql.*;
//
//public class FeedbackDAO {
//	private GuestDAO guestDAO;
//	public FeedbackDAO() {
//        guestDAO = new GuestDAO(); // Initialize the instance of GuestDAO
//    }
//
//	public void saveFeedback(Feedback feedback) throws SQLException {
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(
//                     "INSERT INTO feedback (guestid, feedback) VALUES (?, ?)")) {
//            int guestId = GuestDAO.getGuestIdByName(feedback.getGuestName());
//            stmt.setInt(1, guestId);
//            stmt.setString(2, feedback.getFeedback());
//            stmt.executeUpdate();
//        }
//    }
//	
//}

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class FeedbackDAO extends AbstractDAO<Feedback> {
    private GuestDAO guestDAO; // Create an instance of GuestDAO

    public FeedbackDAO() {
        guestDAO = new GuestDAO(); // Initialize the instance of GuestDAO
    }

    @Override
    public void save(Feedback feedback) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO feedback (guestid, feedback) VALUES (?, ?)")) {
            int guestId = guestDAO.getGuestIdByName(feedback.getGuestName());
            stmt.setInt(1, guestId);
            stmt.setString(2, feedback.getFeedback());
            stmt.executeUpdate();
        }
    }
    
    public int getFeedbackIdByGuestId(int guestId) throws SQLException {
        int feedbackId = -1; // Default value if feedback not found

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT feedbackid FROM feedback WHERE guestid = ?")) {
            stmt.setInt(1, guestId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    feedbackId = rs.getInt("feedbackid");
                }
            }
        }

        return feedbackId;
    }
    
    public List<Feedback> getFeedbacksByGuestId(int guestId) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
         try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT f.*, g.name AS guestName FROM feedback f JOIN guest g ON f.guestid = g.id WHERE f.guestid = ?")) {
            stmt.setInt(1, guestId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int feedbackId = rs.getInt("feedbackid");
                    String guestName = rs.getString("guestName");
                    String feedbackText = rs.getString("feedback");
                    Feedback feedback = new Feedback(feedbackId, guestId, feedbackText);
                    feedback.setGuestName(guestName);
                    feedbacks.add(feedback);
                }
            }
        }

        return feedbacks;

    }

    

}
