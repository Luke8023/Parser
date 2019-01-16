package bns.TRAParser;


public class TRATransactionMoreDetailBean extends TRATransactionDetailBean{
	private char format ;	
	private String request;
	private String criteria;
	private String transactionLocation;
	private String ruleBasedScanning ;
	private String messagePriority ;
	private String freeForm;
	private int numberofMatches;
	
	public TRATransactionMoreDetailBean(String id, double amount, String currency, String service, 
			String disposition, String date, double percent, double passThreshold, double failThreshold, 
			String user, String location,String pairType, char formatType, String requestType, String criteriaSet, 
			String transactionLocation, String ruleBasedScanning, String messagePriority, String freeForm, int numberOfMatches) {
		super(id, amount, currency, service, disposition , date, percent, passThreshold, failThreshold, user, location, pairType);
		format = formatType;
		request = requestType;
		criteria = criteriaSet;
		this.transactionLocation =transactionLocation; 
		this.ruleBasedScanning = ruleBasedScanning;
		this.messagePriority = messagePriority;
		this.freeForm =freeForm;
		this.numberofMatches=numberOfMatches;
	}
	public TRATransactionMoreDetailBean(TRATransactionDetailBean bean, char formatType, String requestType, String criteriaSet, 
			String transactionLocation, String ruleBasedScanning, String messagePriority, String freeForm, int numberOfMatches) {
		super(bean.getTransactionId(),bean.getAmount(),bean.getCurrency(),bean.getSVC(),bean.getDisposition(),bean.getDateTime(),bean.getMatchPercentage(),bean.getPassPercentage(),bean.getFailPercentage(),bean.getUser(),bean.getLocation(),bean.getpType());
		format = formatType;
		request = requestType;
		criteria = criteriaSet;
		this.transactionLocation =transactionLocation; 
		this.ruleBasedScanning = ruleBasedScanning;
		this.messagePriority = messagePriority;
		this.freeForm =freeForm;
		this.numberofMatches=numberOfMatches;
	}
	public TRATransactionMoreDetailBean(TRATransactionDetailBean bean) {
		super(bean.getTransactionId(),bean.getAmount(),bean.getCurrency(),bean.getSVC(),bean.getDisposition(),bean.getDateTime(),bean.getMatchPercentage(),bean.getPassPercentage(),bean.getFailPercentage(),bean.getUser(),bean.getLocation(),bean.getpType());
	}
	@Override
	public String toString() {
		return super.toString() + ",\"" + format +"\",\""+ request +"\",\""+ criteria +"\",\""
						+ ruleBasedScanning +"\",\""+ messagePriority +"\",\""+ freeForm+"\",\""+ numberofMatches+"\"";
	}

	public char getFormat() {
		return format;
	}

	public void setFormat(char format) {
		this.format = format;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getTransactionLocation() {
		return transactionLocation;
	}

	public void setTransactionLocation(String transactionLocation) {
		this.transactionLocation = transactionLocation;
	}

	public String getRuleBasedScanning() {
		return ruleBasedScanning;
	}

	public void setRuleBasedScanning(String ruleBasedScanning) {
		this.ruleBasedScanning = ruleBasedScanning;
	}

	public String getMessagePriority() {
		return messagePriority;
	}

	public void setMessagePriority(String messagePriority) {
		this.messagePriority = messagePriority;
	}

	public String getFreeForm() {
		return freeForm;
	}

	public void setFreeForm(String freeForm) {
		this.freeForm = freeForm;
	}

	public int getNumberofMatches() {
		return numberofMatches;
	}
	public void setNumberofMatches(int numberofMatches) {
		this.numberofMatches = numberofMatches;
	}
}
