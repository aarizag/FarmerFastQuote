package utils;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pages.Page;
import pages.SingleInput;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class FileInputOperations {
	
	private String fileReportLocation = "C:\\Users\\ADRYEL.ARIZAGA\\eclipse-workspace\\SeleniumProject\\Report2.txt";

	public File openChooser() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			return selectedFile;
		}
		return null;
	}

	public void printContents(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));

		String line = br.readLine();
		ArrayList<String[]> lines = new ArrayList<>();
		while (line != null) {
			lines.add(line.split("	"));
			line = br.readLine();
		}
		br.close();

		for (String[] s : lines) {
			for (String data : s) {
				System.out.print(data.trim() + ",");
			}
			System.out.println();
		}
	}

	public ArrayList<String[]> returnContents(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));

		String line = br.readLine();
		ArrayList<String[]> lines = new ArrayList<>();
		while (line != null) {
			lines.add(line.split("	"));
			line = br.readLine();
		}
		br.close();

		return lines;
	}
	
	public HashMap<String, ArrayList<SingleInput>> retrieveInput() {
		File f = new File("C:\\Users\\ADRYEL.ARIZAGA\\Documents\\Product Models\\Input Data.txt");// openChooser();

		ArrayList<String[]> inputLines = null;
		System.out.println(f.getAbsolutePath() + "\n");
		try {
			inputLines = returnContents(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SingleInput placeholder;
		ArrayList<SingleInput> temp;
		HashMap<String, ArrayList<SingleInput>> mappedInputs = new HashMap<>();

		for (String[] arr : inputLines) {
			placeholder = new SingleInput(arr);
			if (placeholder.id == Integer.MIN_VALUE)
				continue;
			else if (!mappedInputs.containsKey(placeholder.fullName())) {
				temp = new ArrayList<SingleInput>();
				temp.add(placeholder);
				mappedInputs.put(placeholder.fullName(), temp);
			} else {
				mappedInputs.get(placeholder.fullName()).add(placeholder);
			}
		}
		System.out.println("Total unique names in the list = " + mappedInputs.size());

		return mappedInputs;
	}
	
	public void addReport(Exception e, String personName, Page currentPage) {
		try (FileWriter fw = new FileWriter(fileReportLocation, true); BufferedWriter writer = new BufferedWriter(fw)) {
			writer.append("\n\nTesting for " + personName + " failed on : " + currentPage.currentAction());
        	writer.append("\n*************************************************************************");
        	writer.append("\n\nException Message : \n" + e.getMessage());
        	writer.append("\n\nException Cause : \n" + e.getCause());
        	writer.append("\n\nException Stack Trace : \n");
        	for (StackTraceElement ste : e.getStackTrace()) 
        		writer.append(ste.toString() + "\n");
        	writer.append("*************************************************************************\n\n");
		}
		catch (Exception ex) {
			System.out.println("FAILED TO WRITE TO FILE.......");
			System.out.println("\n\nTesting for " + personName + " failed on : " + currentPage.currentAction());
        	System.out.println("*************************************************************************");
        	System.out.println("Exception Message : \n" + e.getMessage());
        	System.out.println("Exception Cause : \n" + e.getCause());
        	System.out.println("Exception Stack Trace : ");
        	e.printStackTrace();
        	System.out.println("*************************************************************************\n\n");
		}
	}
	public void takeScreenShot(WebDriver driver, int errorNum) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File("C:\\Users\\ADRYEL.ARIZAGA\\eclipse-workspace\\SeleniumProject\\ScreenShots\\2screenshot"+errorNum+".png"));
		} catch (IOException e) {
			System.out.println("Could not take screen shot for error #: " + errorNum);
		}
	}
}
