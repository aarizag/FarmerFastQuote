package selenium.seminar.fastquote;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import selenium.seminar.UniversalDriver;
import delegator.Delegator;
import delegator.Delegator.CheckType;

public class AutoYourInfo implements Page{
	WebDriver d = UniversalDriver.getInstance();
	Delegator del = new Delegator();
	
	final String FIRST_NAME_PATH = "//input[@id='FFQAuto_Yourinfo_firstName']";
	final String LAST_NAME_PATH = "//input[@id='FFQAuto_Yourinfo_lastName']";
	final String DOB_PATH = "//input[@id='FFQAuto_Yourinfo_Dob']";
	final String ADDRESS_PATH = "//input[@id='FFQAuto_Yourinfo_ResedentialAddress']";
	final String CITY_PATH = "//input[@id='FFQAuto_Yourinfo_City']";
	
	final String GENDER_PATH = "//div[@model-value='yourinfo.yourInfo.gender']/div[@class='radio']";
	final String PRIOR_INSURANCE_PATH = "//label[@for='FFQAuto_Yourinfo_question']";
	
	final String SUBMIT = "//button[@type='submit']";
	
	@FindBy(xpath=FIRST_NAME_PATH)
	WebElement first;
	@FindBy(xpath=LAST_NAME_PATH)
	WebElement last;
	@FindBy(xpath=DOB_PATH)
	WebElement dob;
	@FindBy(xpath=ADDRESS_PATH)
	WebElement address;
	@FindBy(xpath=CITY_PATH)
	WebElement city;
	@FindBy(xpath=PRIOR_INSURANCE_PATH)
	WebElement prior;
	@FindBy(xpath=SUBMIT)
	WebElement submit;
	
	@FindBy(xpath=GENDER_PATH)
	List<WebElement> gender;

	@Override
	public void fillOut() {
		del.delegateWait(CheckType.ENABLED_AND_DISPLAYED, FIRST_NAME_PATH);
		first.sendKeys("Jiminy");
		last.sendKeys("Cricket");
		
		dob.sendKeys("2/7/1940");
		address.sendKeys("6301 Owensmouth Avenue");
		city.sendKeys("Woodland Hills");
		
		prior.click();
		gender.get(0).click();
		
		submit.click();
	}

	@Override
	public void fillOut(SampleData sample) {}
	

}
