package bns.TRAParser;


public class TRAMatchDetailBean extends TRATransactionIdBean{
	private int matchNumber;
	private String matchType;
	private String name;
	private String address;
	private String cityState;
	private String country;
	private String origin;
	private String riskEntityComments;
	private String title;
	private String remarks;
	private String riskEntityInfo;
	private String additionalInfo1;
	private String additionalInfo2;
	private String additionalInfo3;
	private String additionalInfo4;
	private String additionalInfo5;
	private String additionalInfo6;
	private String additionalInfo7;
	private String additionalInfo8;
	private String additionalInfo9;
	private String additionalInfo10;
	private String actualField1;
	private String actualField2;
	private String actualField3;
	private String actualField4;
	private String actualField5;
	private String actualField6;
	private String actualField7;
	private String actualField8;
	private String actualField9;
	private String actualField10;
	private String freeFormMatchText;

	public TRAMatchDetailBean() {

	}

	public TRAMatchDetailBean(final String transId, int matchNumber,
			String matchType, String name, String address, String cityState,
			String county, String origin, String riskEntityComments,
			String title, String remarks, String riskEntityInfo, 
			String additionalInfo1, String actualField1,
			String additionalInfo2, String actualField2,
			String additionalInfo3, String actualField3,
			String additionalInfo4, String actualField4,
			String additionalInfo5, String actualField5,
			String additionalInfo6, String actualField6,
			String additionalInfo7, String actualField7,
			String additionalInfo8, String actualField8,
			String additionalInfo9, String actualField9,
			String additionalInfo10,String actualField10,
			String freeFormMatchText) {
		super(transId);
		this.matchNumber = matchNumber;
		this.matchType = matchType;
		this.name = name;
		this.address = address;
		this.cityState = cityState;
		this.country = county;
		this.origin = origin;
		this.riskEntityComments = riskEntityComments;
		this.title = title;
		this.remarks = remarks;
		this.riskEntityInfo = riskEntityInfo;
		this.additionalInfo1 = additionalInfo1;
		this.additionalInfo2 = additionalInfo2;
		this.additionalInfo3 = additionalInfo3;
		this.additionalInfo4 = additionalInfo4;
		this.additionalInfo5 = additionalInfo5;
		this.additionalInfo6 = additionalInfo6;
		this.additionalInfo7 = additionalInfo7;
		this.additionalInfo8 = additionalInfo8;
		this.additionalInfo9 = additionalInfo9;
		this.additionalInfo10 = additionalInfo10;
		this.actualField1 = actualField1;
		this.actualField2 = actualField2;
		this.actualField3 = actualField3;
		this.actualField4 = actualField4;
		this.actualField5 = actualField5;
		this.actualField6 = actualField6;
		this.actualField7 = actualField7;
		this.actualField8 = actualField8;
		this.actualField9 = actualField9;
		this.actualField10 = actualField10;
		this.freeFormMatchText = freeFormMatchText;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityState() {
		return cityState;
	}

	public void setCityState(String cityState) {
		this.cityState = cityState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getRiskEntityComments() {
		return riskEntityComments;
	}

	public void setRiskEntityComments(String riskEntityComments) {
		this.riskEntityComments = riskEntityComments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRiskEntityInfo() {
		return riskEntityInfo;
	}

	public void setRiskEntityInfo(String riskEntityInfo) {
		this.riskEntityInfo = riskEntityInfo;
	}

	public String getAdditionalInfo1() {
		return additionalInfo1;
	}

	public void setAdditionalInfo1(String additionalInfo1) {
		this.additionalInfo1 = additionalInfo1;
	}

	public String getAdditionalInfo2() {
		return additionalInfo2;
	}

	public void setAdditionalInfo2(String additionalInfo2) {
		this.additionalInfo2 = additionalInfo2;
	}

	public String getAdditionalInfo3() {
		return additionalInfo3;
	}

	public void setAdditionalInfo3(String additionalInfo3) {
		this.additionalInfo3 = additionalInfo3;
	}

	public String getAdditionalInfo4() {
		return additionalInfo4;
	}

	public void setAdditionalInfo4(String additionalInfo4) {
		this.additionalInfo4 = additionalInfo4;
	}

	public String getAdditionalInfo5() {
		return additionalInfo5;
	}

	public void setAdditionalInfo5(String additionalInfo5) {
		this.additionalInfo5 = additionalInfo5;
	}

	public String getAdditionalInfo6() {
		return additionalInfo6;
	}

	public void setAdditionalInfo6(String additionalInfo6) {
		this.additionalInfo6 = additionalInfo6;
	}

	public String getAdditionalInfo7() {
		return additionalInfo7;
	}

	public void setAdditionalInfo7(String additionalInfo7) {
		this.additionalInfo7 = additionalInfo7;
	}

	public String getAdditionalInfo8() {
		return additionalInfo8;
	}

	public void setAdditionalInfo8(String additionalInfo8) {
		this.additionalInfo8 = additionalInfo8;
	}

	public String getAdditionalInfo9() {
		return additionalInfo9;
	}

	public void setAdditionalInfo9(String additionalInfo9) {
		this.additionalInfo9 = additionalInfo9;
	}

	public String getAdditionalInfo10() {
		return additionalInfo10;
	}

	public void setAdditionalInfo10(String additionalInfo10) {
		this.additionalInfo10 = additionalInfo10;
	}

	public String getActualField1() {
		return actualField1;
	}

	public void setActualField1(String actualField1) {
		this.actualField1 = actualField1;
	}

	public String getActualField2() {
		return actualField2;
	}

	public void setActualField2(String actualField2) {
		this.actualField2 = actualField2;
	}

	public String getActualField3() {
		return actualField3;
	}

	public void setActualField3(String actualField3) {
		this.actualField3 = actualField3;
	}

	public String getActualField4() {
		return actualField4;
	}

	public void setActualField4(String actualField4) {
		this.actualField4 = actualField4;
	}

	public String getActualField5() {
		return actualField5;
	}

	public void setActualField5(String actualField5) {
		this.actualField5 = actualField5;
	}

	public String getActualField6() {
		return actualField6;
	}

	public void setActualField6(String actualField6) {
		this.actualField6 = actualField6;
	}

	public String getActualField7() {
		return actualField7;
	}

	public void setActualField7(String actualField7) {
		this.actualField7 = actualField7;
	}

	public String getActualField8() {
		return actualField8;
	}

	public void setActualField8(String actualField8) {
		this.actualField8 = actualField8;
	}

	public String getActualField9() {
		return actualField9;
	}

	public void setActualField9(String actualField9) {
		this.actualField9 = actualField9;
	}

	public String getActualField10() {
		return actualField10;
	}

	public void setActualField10(String actualField10) {
		this.actualField10 = actualField10;
	}

	public String getFreeFormMatchText() {
		return freeFormMatchText;
	}

	public void setFreeFormMatchText(String freeFormMatchText) {
		this.freeFormMatchText = freeFormMatchText;
	}



}
