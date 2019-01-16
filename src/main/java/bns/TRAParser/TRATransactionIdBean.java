package bns.TRAParser;

public class TRATransactionIdBean {
	
	private String transactionId;
	public TRATransactionIdBean() {
		
	}
	public TRATransactionIdBean(final String transactionId) {
		this.transactionId=transactionId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Override
	public String toString() {
		return "\""+transactionId+"\"";
	}
	
}
