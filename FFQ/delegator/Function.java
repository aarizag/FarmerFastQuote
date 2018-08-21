package delegator;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Function{
	String xpath;
	public abstract boolean exec(WebDriver d);
	
	public String toString() {
		return xpath;
	}
	
	public void setXpath(String s) {
		this.xpath = s;
	}
}