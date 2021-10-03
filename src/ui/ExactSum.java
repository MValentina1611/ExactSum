package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;



public class ExactSum {

	private Scanner reader;
	
	public ExactSum()
	{
		reader = new Scanner(System.in);
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException
	{
		ExactSum exactSum = new ExactSum();
		System.out.println("The App is Initializing...");
		 int menuOp = 0;
		 
			do
			{
				menuOp = exactSum.showMenu();
				exactSum.executeOptions(menuOp);

			}while( menuOp != 0 );
		
	}
	
	public int showMenu() throws IOException 
	{
		int menuOp = 0;

		String menu = 
			"===== B O O K  F I N D E R=====\n" +
			"Pick an option \n" +
			"(1) Use default list of books\n" +
			"(2) Type a list of books\n"+								
			"(0) Exit";
		
		System.out.println(menu);
		menuOp = reader.nextInt();
		reader.nextLine();
		
		return menuOp;

	}//Method ends
	
	public void executeOptions( int option ) throws IOException, ClassNotFoundException
	{
		switch( option ) 
		{

			case 0:
				System.out.println ("Bye");
				break;
			case 1:
				System.out.println("default list of book");
				break;
	
			case 2:
				System.out.println("list of book entered");
				break;
		
			default:
				System.out.println("Error, invalid option");
				
		}//Switch ends
		
	}//Method ends
	
}