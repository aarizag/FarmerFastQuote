package delegator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * Function that verifies an invalid input. Based on the css classes associated with it.
 */
public class InvalidEntry extends Function {

	public InvalidEntry() {}
	public InvalidEntry(String s) {
		this.xpath = s;
	}
	
	@Override
	public boolean exec(WebDriver d) {
		try {
			WebElement e = d.findElement(By.xpath(xpath));
			String classes = e.getAttribute("class");
			return classes.contains("invalid") || classes.contains("ng-invalid");
		}
		catch (Exception e) {
			return false;
		}
	}

}
