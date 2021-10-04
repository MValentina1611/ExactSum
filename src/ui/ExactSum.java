package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Country;



public class ExactSum {

	private Scanner reader;
	private List <Integer> info; 
	
	public ExactSum()
	{
		reader = new Scanner(System.in);
		info = new ArrayList<Integer>();	
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

	
	public void testScenario()
	{
		
	}
	
	
	public void askForInfo() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Type the amount of available books, the price of each one, and how much money do you have");
				
		String line = br.readLine();
		
			while(line != null )
			{	
				line += "\n";
				try {
						int infoToAdd = Integer.parseInt(line);
						info.add(infoToAdd);
					}catch(NumberFormatException e){}
			}
		
		
	}
	
	public void searchTheBooks()
	{
		int n = info.get(0);
		int m = info.get(info.size()-1);
		int lowLimit,upperLimit,middle;
		boolean find = false;
		List <Integer> chosenPrices = new ArrayList<Integer>();
		
		if( (n < 2) || (n>10000))
		{
			System.out.println("The number of books available is not enough or too large to process");
		}
		else
		{
			buildPrices();
			
			for(int i = 0; i< buildPrices().length; i++)
			{
				lowLimit = 0;
				upperLimit = buildPrices().length-1;
				
				while(( lowLimit <= upperLimit )&& (find == false))
				{
					middle = (int)((lowLimit + upperLimit)/2); //position
					
					int booksPrices = buildPrices()[i] + buildPrices()[middle];
					
					if(booksPrices == m)
					{
						find = true;
						
						chosenPrices.add(buildPrices()[i]);
						chosenPrices.add(buildPrices()[middle]);
						
					}
					else if( booksPrices < m )
					{
						upperLimit = m-1;
					}
					else
					{
						lowLimit = m+1;
					}
					
				}
			}	
			
			for(int i = 0; i < chosenPrices.size();i++)
			{
				int tempPrices = chosenPrices.get(i)  + chosenPrices.get(i+1);
				
			}
		}
			
		
		
		
			
		
	}
	
	public int[] buildPrices()
	{
		int [] prices = new int [info.size()-2];
		int j = 1;
		for(int i = 0; i<prices.length; i++)
		{
			prices[i] = info.get(j);
			j++;
		}
		
		Arrays.sort(prices);
		return prices;
	}
	
	
	
}