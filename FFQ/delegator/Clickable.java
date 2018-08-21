package delegator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Function that will check if a specific element is clickable.
 * If it is, it will attempt to click said element.
 */
public class Clickable extends Function {


	public Clickable() {}
	public Clickable(String s) {
		this.xpath = s;
	}
	
	@Override
	public boolean exec(WebDriver d) {
		try {
		WebElement e = new WebDriverWait(d, 1)
				.until(ExpectedConditions.elementToBeClickable
						(By.xpath(xpath)));
		e.click();
		return e != null;
		}
		catch(Exception e) { return false; }
	}

}
