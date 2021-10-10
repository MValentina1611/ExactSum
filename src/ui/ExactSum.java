package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;





public class ExactSum {

	//private Scanner reader;
	//private List <Integer> info; 
	private BufferedReader br; 
	
	public ExactSum()
	{
		//reader = new Scanner(System.in);
		//info = new ArrayList<Integer>();
		br = new BufferedReader( new InputStreamReader(System.in));
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
		menuOp = Integer.parseInt( br.readLine()); 
			
		return menuOp;

	}//Method ends
	
	public void executeOptions( int option ) throws IOException, ClassNotFoundException
	{
		switch( option ) 
		{

			case 0:
				System.out.println ("Bye");
				br.close();
				break;
			case 1:
				System.out.println("default list of book");
				break;
	
			case 2:
				askForInfo();
				break;
		
			case 3:
				//useSequentialSearch();
			default:
				System.out.println("Error, invalid option");
				
		}//Switch ends
		
	}//Method ends

	
	public void testScenario()
	{
		
	}
	
	
	public void askForInfo() throws IOException
	{
		
		System.out.println("Type the amount of available books, the price of each one, and how much money do you have");
				
		String line =  br.readLine(); 
		String inputInfo = "";
		int lineCounter = 0;
		
		while( line != null && line.length() > 0)
		{
			
			inputInfo += line+" ";
			lineCounter++;
			
			if(lineCounter == 3)
			{
				inputInfo += "\n\n";
				lineCounter = 0;
				line = br.readLine();
			}
			
			if(lineCounter > 0 && lineCounter < 3)
			{
				line = br.readLine();
			}
				
		}
		
		separateInArrays(inputInfo);
		
	
	}
	
	public void separateInArrays(String inputInfo )
	{
		String [] separateInfo = inputInfo.split("\n\n");
		
		for( int i = 0; i < separateInfo.length; i++ )
		{
			  String [] testCase = separateInfo[i].split(" ");
			  
			  int [] booksInfo = new int [testCase.length];
			  
			  for(int j = 0; j < testCase.length; j++)
			  {
				 try { 
				  int toAdd = Integer.parseInt(testCase[j]);
				
				  booksInfo[j] = toAdd;
				  
				 }catch(NumberFormatException e) {}
			  }
			  
			  buildPrices(booksInfo);
			  
		}
	}
	
	
	public void buildPrices(int [] booksInfo)
	{ 
		int n = 0;
		int m = 0;
		try {
				n = booksInfo[0];
				m = booksInfo[booksInfo.length-1];
				
			}catch(NumberFormatException e) {}
		
		
		int [] prices = new int [booksInfo.length-2];
		
		if( (n < 2)  ||(n>10000) )
		{
			System.out.println("The number of books available is not enough or too large to process");
		}
		else
		{
			//buildPrices
			for( int i = 0; i < prices.length; i++ )
			{
				prices[i] = booksInfo[i+1];
			}
			
			searchBooks(prices, n, m);
			sequentialSearch(prices, n, m);
		}
	}
	
	//BinarySearch
	public void searchBooks(int [] prices, int n, int m)
	{
		int lowLimit, upperLimit, middle, price1, price2;
		boolean find = false;
		List <Integer> chosenPrices = new ArrayList<Integer>();
		Arrays.sort(prices);
		
		for(int i = 0; i < prices.length; i++)
		{
			lowLimit = 0;
			upperLimit = prices.length - 1;
			
			
			while(( lowLimit <= upperLimit )&& (find == false))
			{
				middle = (int)((lowLimit + upperLimit)/2); //position
				
				
				int booksPrices = prices[i] + prices[middle];
						
				if(booksPrices == m)
				{
						find = true;
						price1 = prices[i];
						price2 = prices[middle];
						chosenPrices.add(price1);
						chosenPrices.add(price2);
			
				}
				else if( booksPrices < m )
				{
					lowLimit = middle + 1;
				}
				else
				{
					upperLimit = middle - 1;
					
				}
			}
		
		}
			
		System.out.println("Solving by Binary Search...");
		printArrayList(chosenPrices);	
		chooseBestOption( chosenPrices );
	}
	
	public void chooseBestOption(List<Integer> chosenPrices)
	{
		int min = Math.abs(chosenPrices.get(0)-chosenPrices.get(1));
		int chosenPrice1 = chosenPrices.get(0);
		int chosenPrice2 = chosenPrices.get(1);
		int toCompare = 0;
		
		for(int i = 0; i < chosenPrices.size()-1; i++)
		{
			toCompare = Math.abs(chosenPrices.get(i)-chosenPrices.get(i+1));
			
			if( toCompare < min )
			{
				System.out.println();
				chosenPrice1 = chosenPrices.get(i);
				chosenPrice2 = chosenPrices.get(i+1);
			}
		}
		
		 System.out.println("Peter should buy books whose prices are " +chosenPrice1 + " and "+ chosenPrice2+"\n");
		
		
	}
		
	//SequentialSearch
	public void sequentialSearch( int [] prices, int n, int m )
	{
		List <Integer> chosenPrices = new ArrayList<Integer>();
		int booksPrices;
		
		for(int i = 0; i < prices.length; i++ )
		{
			for(int j = i+1; j < prices.length;j++ )
			{
				booksPrices = prices[i] + prices[j];
				
				
				
				if(booksPrices == m)
				{	
					chosenPrices.add(prices[i]);
					chosenPrices.add(prices[j]);
				}
			}

		}
		System.out.println("Solving by Sequential Search...");
		chooseBestOption(chosenPrices);
	}
	
	public void binarySearchFirstPrice(int m, int [] prices)
	{
		int lowLimit, upperLimit, middle, booksPrices;
		List <Integer> chosenPrices = new ArrayList<Integer>();
		boolean find = false;
		Arrays.sort(prices);
	
		lowLimit = 0;
		upperLimit = prices.length;
		
		for(int i = 0; i < prices.length; i ++)
		{
			while( lowLimit <= upperLimit)
			{
				middle = (int) ( ( lowLimit + upperLimit ) / 2);
				booksPrices = prices[i] + prices[middle];
				
				if( booksPrices == m)
				{
					chosenPrices.add(prices[i]);
					chosenPrices.add(prices[middle]);
					lowLimit++;
				}
				else if(booksPrices < m)
				{
					lowLimit = m+1;
				}
				else
				{
					upperLimit = m-1;
				}
			}
		}
	}
	
	
	
	
	
	
	//print
	public void printArray(String [] array)
	{
		for(int i =0; i < array.length; i++)
		{
			System.out.println(array[i]);
		}
	}
	
	public void printArrayList( List<Integer> chosenPrices)
	{
		for(int i = 0; i< chosenPrices.size();i++)
		{
			System.out.println(chosenPrices.get(i));
		}
	}
	
}