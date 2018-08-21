package delegator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * Function to verify that an element on the page is both enabled and displayed to the user.
 */
public class EnablednDisplayed extends Function{

	public EnablednDisplayed() {}
	public EnablednDisplayed(String s) {
		this.xpath = s;
	}
	
	@Override
	public boolean exec(WebDriver d) {
		try {
			WebElement e = d.findElement(By.xpath(xpath));
			return e.isEnabled() && e.isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}
}
