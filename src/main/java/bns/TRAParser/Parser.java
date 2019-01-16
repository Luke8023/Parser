package bns.TRAParser;


import java.beans.Beans;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class Parser {

	private Queue<TRATransactionDetailBean> transactions = new LinkedList<>();
	private Queue<TRAUserActionBean> userActions = new LinkedList<>();
	private ArrayList<TRATransactionMoreDetailBean> transactions2 = new ArrayList<TRATransactionMoreDetailBean>();
	private ArrayList<String[]> transactions3 = new ArrayList<String[]>();
	private ArrayList<String[]> transactions4 = new ArrayList<String[]>();
	private File traReport;
	private String traReportFileName;
	private Date traDate;
	private BufferedReader traReportBufferedReader;
	private BufferedWriter traReportBufferedWriter;
	private FileWriter transactionDetailFileWriter;
	private FileWriter userActionFileWriter;
	private CsvBeanWriter traTransactionDetailWriter;
	private CsvBeanWriter traUserActionWriter;
	private CsvBeanWriter traMatchDetailWriter;
	private FileWriter matchDetailFileWriter;
	private String[] matchDetailHeader;
	private String[] matchDetailVariableHeader;
	private String[] transactionDetailHeader;
	private String[] transactionDetailVariableHeader;
	private String[] userActionHeader;
	private String[] userActionVariableHeader;
	
	public Parser(String fileName) throws FileNotFoundException {
		//if txt file to be parsed is stored in the same folder run as jar?
		traReportFileName=fileName;
		String fileNoExt=fileName.substring(0, fileName.lastIndexOf('.'));
		//traReportFileName="C:\\Users\\s5649915\\eclipse-workspace\\TRAParser\\src\\BESSTRA_20180626.TXT";
		traReport=new File(traReportFileName);
		FileReader traReportFileReader= new FileReader(traReport);
		traReportBufferedReader=new BufferedReader(traReportFileReader);
		traDate=readDate();
		try {
			transactionDetailFileWriter = new FileWriter(fileNoExt+"_Transaction_Details.csv");
			userActionFileWriter = new FileWriter(fileNoExt+"_User_Actions.csv");
			matchDetailFileWriter = new FileWriter(fileNoExt + "_Match_Details.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		traTransactionDetailWriter = new CsvBeanWriter(transactionDetailFileWriter,CsvPreference.STANDARD_PREFERENCE);
		traUserActionWriter = new CsvBeanWriter(userActionFileWriter, CsvPreference.STANDARD_PREFERENCE);
		traMatchDetailWriter = new CsvBeanWriter(matchDetailFileWriter,
                CsvPreference.STANDARD_PREFERENCE);
		initTransactionDetailHeader();
		initTransactionDetailVariableHeader();
		initMatchDetailHeader();
		initMatchDetailVariableHeader();
		initUserActionHeader();
		initUserActionVariableHeader();
		try {
			traTransactionDetailWriter.writeHeader(transactionDetailHeader);
			traUserActionWriter.writeHeader(userActionHeader);
			traMatchDetailWriter.writeHeader(matchDetailHeader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		parseFile();
		
		try {
			traTransactionDetailWriter.close();
			traMatchDetailWriter.close();
			traUserActionWriter.close();
			transactionDetailFileWriter.close();
			traUserActionWriter.close();
			traMatchDetailWriter.close();
			traReportBufferedReader.close();
			traReportFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Precondition: Parse is initialized and the File has at least one line in TRA format.
	//Postcondition: return date of Parsed TRA report in Date format
	//				 if error in parsing returns null
	private Date readDate() {
		String dateString;
		try {
			dateString = traReportBufferedReader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyHH:mm");
		Date date;
		try {
			date = format.parse ( ""+dateString.substring(6, 15)+dateString.substring(24, 30) );
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	//Postcondition: return date of Parsed TRA report in Date format
	//				 if error in parsing returns null
	public Date getDate() {
		return traDate;
	}
	private void parseFile() {
		try {
			String curLine ="";
			int matchNum = 0;
			int matchNum2 = 0;
			int matchCounter = 0;
			boolean lastTransIsAutoHold = false;
			while ((curLine = traReportBufferedReader.readLine())!=null)
			{
				//split the spaces
				String[] curLineArray=curLine.split("\\s+");
				//Regex check the first line add to queue if auto hold
//				if (curLine.length()>23&& curLine.substring(0,23).equals("Total Transaction Count"))
//					if(lastTransIsAutoHold){
//						printWriter.write(",\""+matchNum+"\"\n");
//						lastTransIsAutoHold=false;
//					}

				if (curLineArray.length>=3&&curLineArray[0].matches("^CA.*")&&curLineArray[1].matches("[\\d,\\.]*\\.\\d\\d\\d")&&curLineArray[2].matches("[A-Z][A-Z][A-Z]"))  {
//					if (lastTransIsAutoHold) {
//						printWriter.write(",\""+matchNum+"\"\n");
//						lastTransIsAutoHold=false;
//					}
					addTransaction(curLine, curLineArray);
					
					matchNum=0;
					matchNum2=0;
				}
				//regex check tran details pop queue
				else if(curLine.length()>44&&curLine.substring(23, 45).equals("- Transaction Details:")){
					userActions.poll();
					String tranId= curLineArray[0];
					char format            = 'U';
					String request         = "S";
					String criteria        = "";
					String transLoc        = "";
					String ruleBased       = "";
					String messagePriority = "";
					boolean inFreeForm     = false;
					String freeForm ="";
					while ((curLine = traReportBufferedReader.readLine())!=null) {
						if(!inFreeForm) {
							if (curLine.matches("^Format type.*")&&curLine.charAt(32)==':') {
								format = curLine.charAt(35);
								continue;
							}
							else if (curLine.matches("^Request type.*")&&curLine.charAt(32)==':') {
								request = curLine.substring(35).trim();
								continue;
							}
							//CHECK BUS LOGIC
							else if (curLine.matches("^Criteria Set.*")&&curLine.charAt(32)==':') {
								criteria = curLine.substring(35,96).trim();
								continue;
							}
							else if (curLine.matches("^Transaction Location.*")&&curLine.charAt(32)==':') {
								transLoc = curLine.substring(35,96).trim();
								continue;
							}
							else if (curLine.matches("^Rule Based Scanning.*")&&curLine.charAt(32)==':') {
								ruleBased = curLine.substring(35,96).trim();
								continue;
							}
							else if (curLine.matches("^Message Priority.*")&&curLine.charAt(32)==':') {
								messagePriority = curLine.substring(35,115).trim();
								continue;
							}
							else if (curLine.matches("^  Free Form:.*")){
								inFreeForm=true;
								continue;
							}
						}
						else {
							if(curLine.trim().length()==0)
								break;
							freeForm+=curLine.trim()+" \n";
						}
					}
					if(transactions.isEmpty())
						continue;
					TRATransactionDetailBean toLook=transactions.peek();
					if (toLook.getTransactionId().equals(tranId)){
						TRATransactionMoreDetailBean toWrite= new TRATransactionMoreDetailBean(toLook, format, request, criteria, 
								transLoc, ruleBased, messagePriority, freeForm, matchCounter);
						transactions.poll();
						//WRITE BUFFER USING SUPERCSV
						traTransactionDetailWriter.write(toWrite,transactionDetailVariableHeader,getTransactionDetailProcessors());
						lastTransIsAutoHold=true;
					}
				}
				// Match Details
				else if (curLine.matches(".*- Match.*\\[.*\\]\\s$")) {
					String matchArray[] = new String[33];
//					for (String initial: matchArray) {
//						initial = "";
//					}
					int index = 2;
					matchNum++;
					matchArray[0] =  curLineArray[0];
					matchArray[1] =  curLineArray[3];
					
					matchArray[2] =  curLine.substring(curLine.indexOf("[")+1,curLine.indexOf("]"));
					boolean matchDetialOver=false;
					while ((curLine = traReportBufferedReader.readLine())!=null) {
						if (curLine.trim().matches("--------+")) {
							if(!matchDetialOver)
								matchDetialOver = true;
							else {
								break;
							}
						}
						else if (curLine.length()>32&&curLine.charAt(32)==':') {
							index++;
							if(index >= 12 && index < 32) {
								matchArray[index + 1]=curLine.substring(0, 32).trim();
								matchArray[index]=curLine.substring(33).trim();
								index++;
							} else {	
								matchArray[index]=curLine.substring(33).trim();
							}
						}
						else {
							matchArray[index]+="\n"+curLine.trim();
						}
					}
					traMatchDetailWriter.write(new TRAMatchDetailBean(
							matchArray[0], Integer.parseInt(matchArray[1]),
							matchArray[2], matchArray[3], matchArray[4], matchArray[5], matchArray[6],
							matchArray[7], matchArray[8], matchArray[9], matchArray[10], matchArray[11],
							matchArray[12], matchArray[13], matchArray[14], matchArray[15], matchArray[16],
							matchArray[17], matchArray[18], matchArray[19], matchArray[20], matchArray[21],
							matchArray[22], matchArray[23], matchArray[24], matchArray[25], matchArray[26],
							matchArray[27], matchArray[28], matchArray[29], matchArray[30], matchArray[31],
							matchArray[32]), 
							matchDetailVariableHeader,
							getMatchDetailProcessors());
					
				}
				//User Actions
				else if (curLineArray.length>=3&&curLineArray[0].matches("^CA.*")&&curLine.substring(24).equals("- User Actions and Comments:")){
					boolean userActionsHit=false;
					boolean isINVQ=false;
					String tranId = curLineArray[0];
					while ((curLine = traReportBufferedReader.readLine())!=null) {
						if (curLine.trim().matches("--------+")) {
							userActionsHit =true;
							continue;
						}
						else if (userActionsHit) {
							String[] userAction = curLine.split(("\\s+"));
							if(curLine.equals("  ")) {
								TRAUserActionBean bean;
								while((bean =userActions.poll())!=null) {
									traUserActionWriter.write(new TRAUserMatchCommentBean(bean),userActionVariableHeader,getUserActionProcessors());
								}
								matchCounter = 0;
								break;
							}
							else if (curLine.trim().equals(""))
								break;
							else if (userAction.length >7) {
								String user = userAction[0];
								String location = userAction[1];
								String disposition = userAction[2]+" "+ userAction[3];
								String date = userAction[4]+" "+ userAction[5]+" "+ userAction[6] +" "+ userAction[7];
								String comment = "";
								for(int userActionIndex = 8; userActionIndex<userAction.length;userActionIndex++) {
									comment += userAction[userActionIndex]+ " ";
								}
								comment=comment.trim();
								TRAUserActionBean curTRAUserAction=new TRAUserActionBean(tranId, user, location, disposition, date, comment);
								if(isINVQ) {
									isINVQ=false;
									traUserActionWriter.write(new TRAUserMatchCommentBean(curTRAUserAction),userActionVariableHeader,getUserActionProcessors());
								}
								else {
									userActions.add(curTRAUserAction);
								}
								if(disposition.equals("MANUAL INVQ"))
									isINVQ=true;
								
								
									
							}
						}
						
					}
				}			
				else if (curLine.matches(".*- Match.*\\[.*\\].*")) {
					String matchArray[] = new String[9];
					int index = 3;
					matchArray[0] =  curLineArray[0];
					matchArray[1] =  curLineArray[3];
					matchCounter = Integer.parseInt(curLineArray[3]);
					matchArray[2] =  curLine.substring(curLine.indexOf("[")+1,curLine.indexOf("]"));
					matchArray[3] = curLineArray[curLineArray.length-1];

					while ((curLine = traReportBufferedReader.readLine())!=null) {
						if (curLine.trim().matches("--------+")) {
							continue;
						}
						if(curLine.trim().matches("====+")) {
							break;
						}

						else if (curLine.length()>17&&curLine.charAt(17)==':') {
							index++;
							matchArray[index]=curLine.substring(18).trim();
							continue;
						}

						else {
							matchArray[index]+="\n"+curLine.trim();
						}

					}
//					transactions4.add(matchArray);
					while(true) {
						TRAUserActionBean userAction = userActions.peek();
						if (userAction.getTransactionId().equals(matchArray[0])) {
							if(userAction.getUser().equals(matchArray[8])) {
								traUserActionWriter.write((new TRAUserMatchCommentBean(matchArray[0],matchArray[1]
										,matchArray[2],matchArray[3],matchArray[4],matchArray[5],matchArray[6]
										,matchArray[7],userAction)),userActionVariableHeader,getUserActionProcessors());
								
								break;
							}
							else {
								userActions.poll();
							}
						}
						else {
							break;
						}
					}
					
				}


			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public Queue<TRATransactionDetailBean> getTransactions(){
		return transactions;
	}
	public ArrayList<TRATransactionMoreDetailBean> getTransactions2(){
		return transactions2;
	}
	public ArrayList<String []> getTransactions3(){
		return transactions3;
	}
	public ArrayList<String []> getTransactions4(){
		return transactions4;
	}
	private void addTransaction(String curLine, String[] curLineArray) {
		String tranId= curLineArray[0];
		double amount = Double.parseDouble(curLineArray[1].replace(",", ""));	
		String cur = curLine.substring(54, 58).replace(" ", "");
		String SVC = curLine.substring(58, 65).replace(" ", "");
		String disposition = curLine.substring(65, 74);
		if (!disposition.substring(0,4).equals("AUTO"))
			disposition.replace(" ", "");
		String dateTime = curLine.substring(77, 95);
		double matchPercentage = Double.parseDouble(curLine.substring(96, 101).replace(" ", ""));
		double passPercentage = Double.parseDouble(curLine.substring(102, 106).replace(" ", ""));
		double failPercentage = Double.parseDouble(curLine.substring(108, 113).replace(" ", ""));
		String user = curLine.substring(114, 126).replace(" ", "");
		String location = curLine.substring(126, 130).replace(" ", "");
		String priority = curLine.substring(130, 143).replace(" ", "");
		TRATransactionDetailBean traDetail = new TRATransactionDetailBean(tranId, amount, cur, SVC, disposition, dateTime,matchPercentage, passPercentage, failPercentage, user, location, priority );

		if (disposition.equals("AUTO HOLD"))
			transactions.add(traDetail);
		else
			try {
				traTransactionDetailWriter.write(new TRATransactionMoreDetailBean(traDetail),transactionDetailVariableHeader,getTransactionDetailProcessors());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	private void initMatchDetailHeader() {
		matchDetailHeader = new String[] { "Transaction ID", "Match Number", "Match Type", "Name","Address","City/State","Country",
						"Origin","Risk Entity Comments","Title","Remarks", "Risk Entity Info",
						"Free Form Match Text",
						"Additional Information 1","Actual Field Name 1",
						"Additional Information 2",	"Actual Field Name 2",
						"Additional Information 3" , "Actual Field Name 3",
						"Additional Information 4" , "Actual Field Name 4",
						"Additional Information 5", "Actual Field Name 5" ,
						"Additional Information 6" , "Actual Field Name 6",
						"Additional Information 7" , "Actual Field Name 7",
						"Additional Information 8" , "Actual Field Name 8",
						"Additional Information 9" , "Actual Field Name 9",
						"Additional Information 10" , "Actual Field Name 10"
		};
	}
	private void initMatchDetailVariableHeader() {
		matchDetailVariableHeader = new String[] { "transactionId", "matchNumber", "matchType", "name","address","cityState","country",
						"origin","riskEntityComments","title","remarks", "riskEntityInfo", 
						"freeFormMatchText",
						"additionalInfo1","actualField1", "additionalInfo2",
						"actualField2","additionalInfo3" , "actualField3" 
						, "additionalInfo4" , "actualField4" , "additionalInfo5" 
						, "actualField5" , "additionalInfo6" , "actualField6" 
						, "additionalInfo7" , "actualField7" , "additionalInfo8" 
						, "actualField8" , "additionalInfo9" , "actualField9" 
						, "additionalInfo10" , "actualField10"
		};
	}
	private void initTransactionDetailHeader() {
		transactionDetailHeader = new String[] {
				"Transaction ID", "Amount","Currency","SVC","Disposition","Date/Time",
				"%","PT","FT","User","Location","Pair Type","Format Type","Request Type","Criteria Set",
				"Rule Based Scanning","Message Priority","Free Form","Number of Matches"
		};
	}
	private void initTransactionDetailVariableHeader() {
		transactionDetailVariableHeader = new String[] { 
					"transactionId", "amount", "currency", "SVC","disposition","dateTime","matchPercentage",
					"passPercentage","failPercentage","user","location", "pType", 
					"format", "request","criteria", "ruleBasedScanning",
					"messagePriority","freeForm" , "numberofMatches" 
		};
	}
	private void initUserActionHeader() {
		userActionHeader = new String[] {
				"Transaction ID", "User","Location","Disposition","Date/Time","Comments"
				,"Match Number","Match Type","Pair Type","Highlight Text","SDNName","Code","Comments"
		};	
	}
	private void initUserActionVariableHeader() {
		userActionVariableHeader = new String[] {
				"transactionId", "user","location","disposition","date","comment"
				,"matchNumber","matchType","matchPairType","matchHighlightText","matchSDNName","matchCode","matchComment"
		};
	}
	private static CellProcessor[] getMatchDetailProcessors() {
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	                new NotNull(), // transNo 
	                new Optional(), // matchNo
	                new Optional(), // matchType
	                new Optional(), // name
	                new Optional(), // address
	                new Optional(), // city/state
	                new Optional(), // Country
	                new Optional(), // origin
	                new Optional(), // riskEntityComments
	                new Optional(), // title
	                new Optional(), // remarks
	                new Optional(), // riskEntityInfo
	                new Optional(), // AD1
	                new Optional(), // Actual1
	                new Optional(), // AD2
	                new Optional(), // Actual2
	                new Optional(), // AD3
	                new Optional(), // Actual3
	                new Optional(), // AD4
	                new Optional(), // Actual4
	                new Optional(), // AD5
	                new Optional(), // Actual5
	                new Optional(), // AD6
	                new Optional(), // Actual6
	                new Optional(), // AD7
	                new Optional(), // Actual7
	                new Optional(), // AD8
	                new Optional(), // Actual8
	                new Optional(), // AD9
	                new Optional(), // Actual9
	                new Optional(), // AD10
	                new Optional(), // Actual10
	                new Optional() // Free Form Match Text
	        };
	        
	        return processors;
	}
	private static CellProcessor[] getTransactionDetailProcessors() {
	    
	    final CellProcessor[] processors = new CellProcessor[] { 
	            new NotNull(), // transNo 
	            new Optional(), // amount
	            new Optional(), // currency
	            new Optional(), // svc
	            new Optional(), // disposition
	            new Optional(), // dateTime
	            new Optional(), // matchPercentage
	            new Optional(), // passPercentage
	            new Optional(), // failPercentage
	            new Optional(), // user
	            new Optional(), // location
	            new Optional(), // pairtype
	            new Optional(), // format
	            new Optional(), // request
	            new Optional(), // criteria
	            new Optional(), // ruleBasedScanning
	            new Optional(), // messagePriority
	            new Optional(), // freeForm
	            new Optional() // numberofMatches
	    };
	    
	    return processors;
	}
private static CellProcessor[] getUserActionProcessors() {
	    
	    final CellProcessor[] processors = new CellProcessor[] { 
	            new NotNull(), // transactionId 
	            new Optional(), // user
	            new Optional(), // location
	            new Optional(), // disposition
	            new Optional(), // date
	            new Optional(), // comment
	            new Optional(), // matchNumber
	            new Optional(), // matchType
	            new Optional(), // matchPairType
	            new Optional(), // matchHighlightText
	            new Optional(), // matchSDNName
	            new Optional(), // matchCode
	            new Optional() // matchComment
	    };
	    
	    return processors;
	}
}
