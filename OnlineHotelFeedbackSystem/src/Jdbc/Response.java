package Jdbc;
// The fields are all private - Encapsulation
public class Response {
	private int responseid;
	private int feedbackid;
	private int guestid;
	private String response;
	
	// Add guestname as a temporary variable for input purposes
    private String guestname;
    
    private String feedback;
    
	public Response(int responseid, int feedbackid, int guestid, String response) {
		super();
		this.responseid = responseid;
		this.feedbackid = feedbackid;
		this.guestid = guestid;
		this.response = response;
	}
	
	// Constructor with the guest name as parameter
		public Response(String guestname,String feedback) {
	        this.guestname = guestname;
	        this.feedback = feedback;
	    }
	
	public Response() {
		
	}
	public int getResponseid() {
		return responseid;
	}
	public void setResponseid(int responseid) {
		this.responseid = responseid;
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
	// Getter and Setter for guestname
    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }
    
    public String getFeedback() {
    	return feedback;
    }
    
    public void setFeedback(String feedback) {
    	this.feedback = feedback;
    }
}
