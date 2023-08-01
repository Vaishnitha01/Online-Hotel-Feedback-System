package Jdbc;

import java.sql.*;

public class GuestDAO extends AbstractDAO<Guest>{
	@Override
    public void save(Guest guest) throws SQLException {
    	// Code to insert the guest data into the 'Guest' table
    	try (Connection conn = DatabaseConnection.getConnection()) {
    		String query = "INSERT INTO Guest (name, email, phone) VALUES (?, ?, ?)";
    		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    		stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int guestId = generatedKeys.getInt(1);
                guest.setId(guestId); // Set the auto-generated guest_id back to the guest object
            }
    	}
    }
    
    public int getGuestIdByName(String guestName) throws SQLException {
        int guestId = -1; // Default value if guest not found

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id FROM guest WHERE name = ?")) {
            stmt.setString(1, guestName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    guestId = rs.getInt("id");
                }
            }
        }

        return guestId;
    }
    
    public Guest getGuestById(int guestId) throws SQLException {
        Guest guest = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM guest WHERE id = ?")) {
            stmt.setInt(1, guestId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    guest = new Guest(guestId, name, email, phone);
                }
            }
        }

        return guest;
    }
    
    public void updateGuest(Guest guest) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE guest SET name = ?, email = ?, phone = ? WHERE id = ?")) {
            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.setInt(4, guest.getId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteGuest(int guestId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM guest WHERE id = ?")) {
            stmt.setInt(1, guestId);
            stmt.executeUpdate();
        }
    }
    
    
}