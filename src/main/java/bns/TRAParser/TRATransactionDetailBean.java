package bns.TRAParser;


public class TRATransactionDetailBean extends TRATransactionIdBean{
	private double amount ;	
	private String currency;
	private String SVC;
	private String disposition;
	private String dateTime ;
	private double matchPercentage ;
	private double passPercentage;
	private double failPercentage ;
	private String user ;
	private String location ;
	private String pType ;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSVC() {
		return SVC;
	}

	public void setSVC(String sVC) {
		SVC = sVC;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getMatchPercentage() {
		return matchPercentage;
	}

	public void setMatchPercentage(double matchPercentage) {
		this.matchPercentage = matchPercentage;
	}

	public double getPassPercentage() {
		return passPercentage;
	}

	public void setPassPercentage(double passPercentage) {
		this.passPercentage = passPercentage;
	}

	public double getFailPercentage() {
		return failPercentage;
	}

	public void setFailPercentage(double failPercentage) {
		this.failPercentage = failPercentage;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	public TRATransactionDetailBean(){
		
	}
	public TRATransactionDetailBean(final String id, double amount, String currency, String service, String disposition, String date, double percent, double passThreshold, double failThreshold, String user, String location,String pairType ) {
		super(id);
		this.amount =amount;	
		this.currency =currency;
		this.SVC = service;
		this.disposition = disposition;
		this.dateTime =date; 
		this.matchPercentage = percent;
		this.passPercentage = passThreshold;
		this.failPercentage = failThreshold;
		this.user = user;
		this.location = location;
		this.pType = pairType;
		
	}
	@Override
	public String toString() {
		return super.toString() +",\""+ amount +"\",\""+ currency +"\",\""+ SVC +"\",\""+ disposition 
				+"\",\""+ dateTime +"\",\""+ matchPercentage +"\",\""+ passPercentage +"\",\""+ failPercentage 
				+"\",\""+ user +"\",\""+location +"\",\""+ pType+"\"";
	}
	public String getDisposition() {
		return disposition;
	}

}
