package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class WordSearcher {
	public void search(final String searchTerm) {
		final String path = "C:\\Users\\ADRYEL.ARIZAGA\\git\\Automation_Selenium\\src\\test\\java\\com\\farmers\\application\\testscripts";
		File folder =  new  File(path);
		File[] listOfFiles = folder.listFiles();

		ArrayList<String[]> matches = new ArrayList<>();
		
		for (File testName : listOfFiles) {
//			System.out.println(testName.getName());

			try (FileReader fw = new FileReader(testName); BufferedReader reader = new BufferedReader(fw)) {
				String line;
				int lineNum = 0;
				
				do{
					lineNum++;
					line = reader.readLine();
					if(line.contains("void")) {
						String[] split = line.split(" ");
						for(String s : split) {
							if(s.toLowerCase().contains(searchTerm))
								matches.add(new String[] {s, Integer.toString(lineNum), testName.getName()} );
						}
					}
					
				}while(line != null) ;
				
			}
			catch(Exception e) {}
		}
		
		System.out.println("\n\n");
		if(matches.isEmpty()) 
			System.out.println("No method matches");
		
		for(String[] s : matches) {
			System.out.print(s[0] + "  ");
			for(int i = 60 - s[0].length() ; i  > 0 ; i--) 
				System.out.print(" ");
			System.out.println("|  found in Line Number " + s[1] + " in file : " + s[2]);
		}
	}
	
	public static void main(String[] args) {
		WordSearcher ws = new WordSearcher();
		Scanner sc = new Scanner(System.in);
		String term;
		while (true) {
			System.out.print("\n\nPlease enter search term. \n(Leave Blank and hit 'Enter' to Terminate) : ");
			term = sc.nextLine();
			if(term == null || term.length() == 0)
				break;
			ws.search(term);
		} 
		sc.close();
		System.out.println("Search terminated...");
	}
}
