package bns.TRAParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;



public class App {
	public static void main(String[] args) {
		Parser parse;
		try {
			Scanner reader = new Scanner(System.in);  					// Reading from System.in
			System.out.println("Enter a BESS TRA file to parse: ");
			String userFile = reader.nextLine(); 						// Scans the next token of the input as an int.
			//once finished											
			reader.close();												
//			parse =new Parse("C:\\Users\\s5649915\\eclipse-workspace\\TRAParser\\src\\BESSTRA_20180626.TXT");
			parse = new Parser(userFile);
			Date date = parse.getDate();
			System.out.println(date.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}


	