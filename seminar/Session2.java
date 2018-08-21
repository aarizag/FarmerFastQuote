package selenium.seminar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

//https://csstr.farmers.com/REGquote/ffq/landingpage.jsf
public class Session2 {
	
	static WebDriver driver = UniversalDriver.getInstance();
	static int count = 0;
	
	public static void main(String[] args) {		
		driver.get("https://gmail.com");
		ArrayList<String[]> sampleInputs = null;
		try {
			sampleInputs = getInfoFromFile();
		} catch(Exception e) {}
		
		fillLogin();
		for(String[] s : sampleInputs) 
			fillInbox(s);
			
		driver.navigate().refresh();
	}
	
	private static void fillInbox(String[] strings) {
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return driver.findElement(By.xpath("//div[@style='user-select: none;']")).isEnabled();
			}
		});
		WebElement compose = driver.findElement(By.xpath("//div[@class='z0']/div[@style='user-select: none;' and @role='button']"));
		compose.click();
		
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return driver.findElement(By.name("to")).isDisplayed();
			}
		});
		WebElement toField = driver.findElement(By.name("to"));
		toField.sendKeys("adryel.arizaga.seleniumtest@gmail.com");
		
		WebElement subjectField = driver.findElement(By.name("subjectbox"));
		subjectField.sendKeys("This is Test Automated Email #" + count + " for " + strings[2] + " " + strings[3]);
		count++;
		
		WebElement messageBody = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
		messageBody.sendKeys(strings[0]);
		System.out.println(strings[0]);
		
		// Click on send button
		driver.findElement(By.xpath("//div[contains(text(),'Send')]")).click();
		count++;
		try {
			Thread.sleep(1000);
		} catch (Exception e) {} 
	}

	public static void fillInbox() {
		fillInbox(new String[] {"NO SAMPLE DATA", "IGNORED",  "FIRSTNAME", "LASTNAME"});
	}
	public static void fillLogin() {
		
		WebElement id = driver.findElement(By.id("identifierId"));
		
		id.sendKeys("adryel.arizaga.seleniumtest");
		id.sendKeys(Keys.RETURN);

		
		(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return driver.findElement(By.name("password")).isDisplayed();
			}
		});
		WebElement password = driver.findElement(By.name("password"));
		
		password.sendKeys("SeleniumTest");
		password.sendKeys(Keys.RETURN);
	}
	
	private static ArrayList<String[]> getInfoFromFile() throws IOException {
		File f = new File("C:\\Users\\ADRYEL.ARIZAGA\\Documents\\SampleData.csv");// openChooser();
		System.out.println("File Path: \n" + f.getAbsolutePath() + "\n");
		
		BufferedReader br = new BufferedReader(new FileReader(f));

		String line = br.readLine();
		ArrayList<String[]> lines = new ArrayList<>();
		while (line != null) {
			lines.add(line.split(","));
			line = br.readLine();
		}
		br.close();	

		System.out.println(lines.size() + " sample lines... ");
		return lines;
	}
}
