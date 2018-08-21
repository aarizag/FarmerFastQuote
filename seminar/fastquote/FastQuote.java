package selenium.seminar.fastquote;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import selenium.seminar.UniversalDriver;

//https://csstr.farmers.com/REGquote/ffq/landingpage.jsf
public class FastQuote {
	public static void main(String[] args) {
		WebDriver driver = UniversalDriver.getInstance();
		driver.get("https://csstr.farmers.com/REGquote/ffq/landingpage.jsf");
		Page current = null;
		
		Class[] pages = {
			LandingPage.class,
			AutoYourInfo.class,
			AutoVehiclePages.class,
			DriverInformationPages.class,
			DiscountsPages.class
		};
		
		for (Class page : pages) {
			current = (Page) PageFactory.initElements(driver, page);
			System.out.println("current page : " + page.getName());
			current.fillOut();
		}
	}	
}
