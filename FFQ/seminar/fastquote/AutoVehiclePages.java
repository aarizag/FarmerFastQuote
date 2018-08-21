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

public class AutoVehiclePages implements Page{
	private static Delegator delegator = new Delegator();
	private static Random r = new Random();
	WebDriver driver = UniversalDriver.getInstance();
			
	private final String FINANCE_TYPE = "//div[@id='FFQAuto_Vehicle_vehicleOnWrap']//label";
	private final String VEHICLE_USE = "//div[@id='FFQAuto_Vehicle_vehicleUsedForWrap']//label";
	private final String RIDE_SHARE = "//div[@id='FFQAuto_Vehicle_rideShareWrap']//label";
	
	private final String NO_OTHER_VEHICLE = "//label[@for='FFQAuto_Vehicle_VehicleAnother_2']";
	
	private final String COMMUTE_SELECT_DROPDOWN = "//select[@name='selectnoOfCommutes']//*";
	private final String ADD_VEHICLE_XPATH = "//button[@id='FFQAuto_Vehicle_vehicleFeaturesbtn']";
	
	private final String SUBMIT_XPATH = "//button[@type='submit']";


	@FindBy(xpath=SUBMIT_XPATH)
	WebElement submit;
	@FindBy(xpath=ADD_VEHICLE_XPATH)
	WebElement addVehicle;
	@FindBy(xpath=RIDE_SHARE)
	List<WebElement> rideShareOptions;
	@FindBy(xpath=FINANCE_TYPE)
	List<WebElement> financeTypeOptions;
	@FindBy(xpath=VEHICLE_USE)
	List<WebElement> vehicleUseOptions;

	@FindBy(xpath=NO_OTHER_VEHICLE)
	WebElement noOtherVehicle;
	@FindBy(xpath=COMMUTE_SELECT_DROPDOWN)
	List<WebElement> commuteDropdown;
	
		
	
	public void fillOut() {		
		vehicleInfoFill();
		purchaseType();
		calendar();
		usage();
		rideSharing();
		addSafetyFeatures();
		noOtherVehicles();
	}
	
	private void noOtherVehicles() {
		delegator.delegateWait(CheckType.CLICKABLE, NO_OTHER_VEHICLE);
	}

	private void addSafetyFeatures() {
		delegator.delegateWait(driver, CheckType.ENABLED_AND_DISPLAYED, ADD_VEHICLE_XPATH);
		addVehicle.click();
	}
	
	private void rideSharing() {
		sleep(1);
		delegator.delegateWait(driver, CheckType.LIST_EXISTS, RIDE_SHARE);
		rideShareOptions.get(1).click();
	}

	private void usageOther() {
		final String ANNUAL_MILES_INPUT = "//input[@name='annualMiles']";

		delegator.delegateWait(CheckType.ENABLED_AND_DISPLAYED, ANNUAL_MILES_INPUT);
		WebElement ele = driver.findElement(By.xpath(ANNUAL_MILES_INPUT));
		ele.clear();
		ele.sendKeys(Integer.toString(r.nextInt(200) + 10000));
		
		submit.sendKeys(Keys.RETURN);
	}

	private void usageCommute() {
		final String ONEWAY_MILES_INPUT = "//input[@name='milesOneWay']";
		
		delegator.delegateWait(driver, CheckType.ENABLED_AND_DISPLAYED, ONEWAY_MILES_INPUT);
		WebElement ele = driver.findElement(By.xpath(ONEWAY_MILES_INPUT));
		ele.sendKeys(Integer.toString(r.nextInt(10) + 20));
		delegator.delegateWait(driver, CheckType.LIST_EXISTS, COMMUTE_SELECT_DROPDOWN);
		commuteDropdown.get(r.nextInt(commuteDropdown.size())).click();
		
		submit.sendKeys(Keys.RETURN);
	}

	private void usage() {
		sleep(1);
		delegator.delegateWait(CheckType.LIST_EXISTS, VEHICLE_USE);
		int choice = r.nextInt(vehicleUseOptions.size());
		vehicleUseOptions.get(choice).click();
		if(choice == 0) 
			usageCommute();
		else
			usageOther();
	}

	private void calendar() {
		delegator.delegateWait(driver, CheckType.ENABLED_AND_DISPLAYED, SUBMIT_XPATH);
		submit.sendKeys((Keys.RETURN));
	}

	private void purchaseType() {
		try {	
			delegator.delegateWait(driver, CheckType.LIST_EXISTS, FINANCE_TYPE, 2);  
			int choice = r.nextInt(financeTypeOptions.size());
			financeTypeOptions.get(choice).click();
		}
		catch (Exception e) { selectVehicleType(); }
	}

	private void selectVehicleType() {
		final String TYPE = "//select[@name='selectType']//*";
		delegator.delegateWait(driver, CheckType.LIST_EXISTS, TYPE);
		List<WebElement> opt = driver.findElements(By.xpath(TYPE));
		opt.get(r.nextInt(opt.size())).click();
		
		submit.sendKeys(Keys.RETURN);
		purchaseType();
	}

	private void vehicleInfoFill() {
		final String _START = "//select[@name='";
		final String _END = "']//*";
		final String[] menuVariable = {"selectYear", "selectMake", "selectModel"};
		String path = "";
		int choice = 0;
		List<WebElement> options = null;
		
		for (String menu : menuVariable) {
			path = _START + menu + _END;
			delegator.delegateWait(CheckType.LIST_EXISTS, path);
			
			options = driver.findElements(By.xpath(path));
			choice = r.nextInt(options.size());
			options.get(choice).click();
		}
		submit.sendKeys(Keys.RETURN);
	}
	
	public void sleep(double time) {
		try {
			Thread.sleep((int)(time * 1000));
		} catch (InterruptedException e) {}
	}

	@Override
	public void fillOut(SampleData sample) {}
}
