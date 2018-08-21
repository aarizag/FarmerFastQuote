package selenium.seminar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session1 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADRYEL.ARIZAGA\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        for (int i = 0 ; i < 20 ; i++) {
	        driver.navigate().to("https://google.com");
	        WebElement e = driver.findElement(By.name("q"));
	        
	        e.sendKeys("Do a barrel roll");
	        e.submit();
	        
	        Thread.sleep(5000);
        }
        driver.close();
	}

}
