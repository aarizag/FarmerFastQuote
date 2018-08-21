package selenium.seminar.fastquote;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import delegator.Delegator;
import delegator.Delegator.CheckType;
import selenium.seminar.UniversalDriver;

public class DriverInformationPages implements Page {
	private static Delegator delegator = new Delegator();
	private static Random r = new Random();
	WebDriver driver = UniversalDriver.getInstance();
	boolean spouse;
	
	private final String SAMPLE_EMAIL = "adryel.arizaga.seleniumtest@gmail.com";
	private final String SAMPLE_PHONE = "5555555555";
	
	private final String MARITAL_STATUS = "//div[@id='FFQAuto_Driver_maritalStatusWrapper']//label";
	private final String OCCUPATION = "//div[@id='FFQAuto_Driver_occupationWrapper']//label";	
	private final String DRIVER_SAFETY_COURSE = "//div[@id='FFQAuto_Driver_safetyCourseWrapper']//label";
	private final String RECENT_ACCIDENT = "//div[@id='FFQAuto_Driver_safetyCourseQuesWrapper']//label";
	
	private final String ACCIDENT_TYPE = "//select[@id='FFQAuto_Driver_Acctype']/option";
	private final String ACCIDENT_DESC = "//select[@id='FFQAuto_Driver_description']/option";
	private final String ACCIDENT_DATE = "//select[@ng-model='selectedDate']";
	
	private final String SUBMIT_XPATH = "//button[@type='submit']";
	
	@FindBy(xpath=MARITAL_STATUS)
	List<WebElement> maritalStatus;
	
	@FindBy(xpath=OCCUPATION)
	List<WebElement> occupation;

	@FindBy(xpath="//input[@name='age']")
	WebElement age;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	@FindBy(xpath="//input[@name='phoneNumber']")
	WebElement phone;
	
	@FindBy(xpath=DRIVER_SAFETY_COURSE)
	List<WebElement> driverSafetyCourse;
	
	@FindBy(xpath=RECENT_ACCIDENT)
	List<WebElement> recentAccident;
	
	@FindBy(xpath=SUBMIT_XPATH)
	WebElement submit;

	@Override
	public void fillOut() {
		marriage();
		job();
		firstLiscensed();
		emailAndNum();
		driverSafety();
		recentAccidents();
		if(spouse)
			enterSpouseInfo();
		noOtherDriver();
		if(spouse)
			vehicleAssignment();

	}

	private void vehicleAssignment() {
		try {
			AutoVehiclePages s = new AutoVehiclePages();
			delegator.delegateWait(CheckType.CLICKABLE, "//label[@for='check+0']");
			WebElement cont;
			for(int i = 0 ; i < 3 ; i ++) {
				cont = driver.findElement(By.id("FFQAuto_Assignment_assignVehicleContinue"));
				cont.sendKeys(Keys.RETURN);
				s.sleep(2);
			}
		} catch (Exception e) {}
	}

	private void noOtherDriver() {
		delegator.delegateWait(CheckType.CLICKABLE, "//label[@for='FFQAuto_Driver_moredriver_sc2']");
	}

	private void enterSpouseInfo() {
		final String SPOUSE_FIRST = "//input[@id='FFQAuto_Driver_firstName']";
		final String SPOUSE_SECOND = "//input[@id='FFQAuto_Driver_lastName']";
		final String SPOUSE_DOB = "//input[@id='FFQAuto_Driver_DOB']";
		
		delegator.delegateWait(CheckType.CLICKABLE, SPOUSE_FIRST);

		WebElement firstName = driver.findElement(By.xpath(SPOUSE_FIRST));
		WebElement lastName = driver.findElement(By.xpath(SPOUSE_SECOND));
		WebElement spouseDob = driver.findElement(By.xpath(SPOUSE_DOB));
		
		firstName.sendKeys("Jiminia");
		lastName.sendKeys("Cricket");
		spouseDob.sendKeys("10/10/1945");
		
		submit.sendKeys(Keys.RETURN);
		
		final String IS_LISCENSED = "//select[@id='FFQAuto_Driver_licensedQues']//option";
		delegator.delegateWait(CheckType.ENABLED_AND_DISPLAYED,IS_LISCENSED);
		
		int choice = r.nextInt(3);
		driver.findElements(By.xpath(IS_LISCENSED)).get(choice).click();
		driver.findElement(By.xpath("//label[@for='FFQAuto_Driver_question1']")).click();
		submit.sendKeys(Keys.RETURN);
		
		if(choice == 0) {
			firstLiscensed();
			driverSafety();
			recentAccidents();
		}
	}
	
	private void enterAccidentInformation() {
		delegator.delegateWait(CheckType.LIST_EXISTS, ACCIDENT_TYPE);

		List<WebElement> type = driver.findElements(By.xpath(ACCIDENT_TYPE));
		List<WebElement> desc = driver.findElements(By.xpath(ACCIDENT_DESC));
		WebElement date = driver.findElement(By.xpath(ACCIDENT_DATE));
		
		type.get(r.nextInt(type.size())).click();
		
		delegator.delegateWait(CheckType.CLICKABLE, ACCIDENT_DESC);
		desc.get(r.nextInt(desc.size())).click();
		
		date.click();
		submit.click();
	}

	private void recentAccidents() {
		delegator.delegateWait(CheckType.LIST_EXISTS, RECENT_ACCIDENT);
		int choice = 1;// r.nextInt(2);
		recentAccident.get(choice).click();
		if(choice==0)
			enterAccidentInformation();
		new AutoVehiclePages().sleep(1);
		submit.sendKeys(Keys.RETURN);
	}

	private void driverSafety() {
		new AutoVehiclePages().sleep(2.5);
		delegator.delegateWait(CheckType.LIST_EXISTS, DRIVER_SAFETY_COURSE);
		int choice = r.nextInt(2);
		driverSafetyCourse.get(choice).click();
//		System.out.println("CLICKED DRIVER SAFETY COURSE OPTION " + choice);
	}

	private void emailAndNum() {
		delegator.delegateWait(CheckType.ENABLED_AND_DISPLAYED, "//*[@name='email']");
		email.sendKeys(SAMPLE_EMAIL);
		phone.sendKeys(SAMPLE_PHONE);
		phone.sendKeys(Keys.RETURN);
	}

	private void firstLiscensed() {
		delegator.delegateWait(CheckType.ENABLED_AND_DISPLAYED, "//input[@name='age']");
		age.sendKeys("19");
		age.sendKeys(Keys.RETURN);
	}

	private void job() {
		delegator.delegateWait(CheckType.LIST_EXISTS, OCCUPATION);
		int choice = r.nextInt(occupation.size());
		occupation.get(choice).click();
		if(choice == 10) {
			delegator.delegateWait(CheckType.ENABLED_AND_DISPLAYED, "//*[@id='FFQAuto_Driver_otherOccupation']");
			driver.findElement(By.id("FFQAuto_Driver_otherOccupation")).sendKeys("Sound Cloud Rapper");
			driver.findElement(By.id("FFQAuto_Driver_continuebutton")).sendKeys(Keys.RETURN);
		}
			
	}

	private void marriage() {
		delegator.delegateWait(CheckType.LIST_EXISTS, MARITAL_STATUS);
		int choice = r.nextInt(3) + 1;
		maritalStatus.get(choice).click();
		spouse = choice == 0;
	}

	@Override
	public void fillOut(SampleData sample) {}
	
	
}
