package bns.TRAParser;


public class TRAUserActionBean extends TRATransactionIdBean{
	private String user ;
	private String location;
	private String disposition;
	private String date;
	private String comment;

	
	public TRAUserActionBean(String tranId, String user, String location, String disposition, String date, String comment) {
		super(tranId);
		this.user =user;
		this.location = location;
		this.disposition=disposition;
		this.date = date;
		this.comment =comment.replace("\"", "\"\"");
		
	}
	
	public String toString() {
		String toWrite = "\""+ getTransactionId() +"\",\""+ user +"\",\""+ location +"\",\""+ disposition +"\",\""+ date +"\",\""+ comment +"\"";
		return toWrite;	
	}
	public String getDisposition() {
		return disposition;
	}
	public String getUser() {
		return user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

}
