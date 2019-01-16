package bns.TRAParser;

public class TRAUserMatchCommentBean  extends TRAUserActionBean{
	private String matchNumber;
	private String matchType;
	private String matchPairType;
	private String matchHighlightText;
	private String matchSDNName;
	private String matchCode;
	private String matchComment;
	public TRAUserMatchCommentBean(String tranId,String matchNumber, String matchType, String matchPairType,String matchHighlightText, String matchSDNName, String matchCode, String matchComment, TRAUserActionBean traUserAction) {
		super(tranId, traUserAction.getUser(), traUserAction.getLocation(), traUserAction.getDisposition(), traUserAction.getDate(), traUserAction.getComment());
		
		this.matchNumber=matchNumber;
		this.matchType=matchType;
		this.matchPairType=matchPairType;
		this.matchHighlightText=matchHighlightText.replace("\"", "\"\"");;
		this.matchSDNName=matchSDNName.replace("\"", "\"\"");;
		this.matchCode=matchCode;
		this.matchComment=matchComment.replace("\"", "\"\"");
		
	}
	public TRAUserMatchCommentBean(TRAUserActionBean traUserAction) {
		super(traUserAction.getTransactionId(), traUserAction.getUser(), traUserAction.getLocation(), traUserAction.getDisposition(), traUserAction.getDate(), traUserAction.getComment());

	}
	@Override
	public String toString() {
		String toWrite = super.toString();
//		if (hasMatchDetails)
//			toWrite +=",\""+ matchNumber +"\",\""+ matchType+"\",\""+ matchPairType +"\",\""+ matchHighlightText +"\",\""+ matchSDNName +"\",\""+matchCode +"\",\""+ matchComment+"\"";
		return toWrite;	
	}
	public String getMatchNumber() {
		return matchNumber;
	}
	public void setMatchNumber(String matchNumber) {
		this.matchNumber = matchNumber;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public String getMatchPairType() {
		return matchPairType;
	}
	public void setMatchPairType(String matchPairType) {
		this.matchPairType = matchPairType;
	}
	public String getMatchHighlightText() {
		return matchHighlightText;
	}
	public void setMatchHighlightText(String matchHighlightText) {
		this.matchHighlightText = matchHighlightText;
	}
	public String getMatchSDNName() {
		return matchSDNName;
	}
	public void setMatchSDNName(String matchSDNName) {
		this.matchSDNName = matchSDNName;
	}
	public String getMatchCode() {
		return matchCode;
	}
	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}
	public String getMatchComment() {
		return matchComment;
	}
	public void setMatchComment(String matchComment) {
		this.matchComment = matchComment;
	}
	
}
