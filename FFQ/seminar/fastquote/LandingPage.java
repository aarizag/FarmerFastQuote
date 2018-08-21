package selenium.seminar.fastquote;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import selenium.seminar.UniversalDriver;
import delegator.Delegator;
import delegator.Delegator.CheckType;

public class LandingPage implements Page {
	WebDriver d = UniversalDriver.getInstance();
	Delegator del = new Delegator();
	
	final String QUOTE_TYPE_PATH = "//select[@id='landing:quoteType']";
	final String QUOTE_OPTIONS_PATH = QUOTE_TYPE_PATH + "/option";
	final String ZIP_PATH = "//input[@id='landing:zipCode']";
	final String SUBMIT_PATH = "//div[@id='startbutton']/input";

	@FindBy(xpath=QUOTE_TYPE_PATH)
	WebElement quoteType;
	@FindBy(xpath=QUOTE_OPTIONS_PATH)
	List<WebElement> quoteOptions;
	@FindBy(xpath=ZIP_PATH)
	WebElement zip;
	@FindBy(xpath=SUBMIT_PATH)
	WebElement submit;
	
	@Override
	public void fillOut() {
		del.delegateWait(CheckType.ENABLED_AND_DISPLAYED , QUOTE_TYPE_PATH);
		quoteType.click();
		quoteOptions.get(1).click();
		zip.sendKeys("91367");
		submit.sendKeys(Keys.RETURN);
	}

	@Override
	public void fillOut(SampleData sample) {}
	
}
