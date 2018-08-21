package delegator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;;

/*
 * Function that checks if a list of WebElements exists and has elements
 */
public class ListExists extends Function{
	
	public ListExists() {}
	public ListExists(String s) {
		this.xpath = s;
	}

	@Override
	public boolean exec(WebDriver d) {
		try {
			List<WebElement> e = d.findElements(By.xpath(xpath));
			return e.size() > 0;
		}
		catch (Exception e) {
			return false;
		}
	}
	
}
