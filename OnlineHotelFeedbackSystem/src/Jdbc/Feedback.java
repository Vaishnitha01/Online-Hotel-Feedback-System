package Jdbc;

public class Feedback {
	private int feedbackid;
	private int guestid;
	private String feedback;
	// Add guestName as a temporary variable for input purposes
    private String guestName;
	
	public Feedback(int feedbackid, int guestid, String feedback) {
		super();
		this.feedbackid = feedbackid;
		this.guestid = guestid;
		this.feedback = feedback;
	}

	// Constructor with the guest name as parameter
	public Feedback(String guestName) {
        this.guestName = guestName;
    }

	public Feedback(){
		
	}
	public int getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(int feedbackid) {
		this.feedbackid = feedbackid;
	}
	public int getGuestid() {
		return guestid;
	}
	public void setGuestid(int guestid) {
		this.guestid = guestid;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	// Getter and Setter for guestName
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
