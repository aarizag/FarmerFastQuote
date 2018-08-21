package selenium.seminar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Singleton Implementation of the driver to use globally
public class UniversalDriver {
	
	private static volatile WebDriver driver = null;

	public static WebDriver getInstance() {
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADRYEL.ARIZAGA\\Desktop\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}
}
