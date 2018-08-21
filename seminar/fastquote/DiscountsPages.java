package selenium.seminar.fastquote;

import org.openqa.selenium.WebDriver;

import delegator.Delegator;
import delegator.Delegator.CheckType;
import selenium.seminar.UniversalDriver;

public class DiscountsPages implements Page {
	private static Delegator delegator = new Delegator();
	WebDriver driver = UniversalDriver.getInstance();
	
	private final String SUBMIT_XPATH = "//button[@type='submit']";

	@Override
	public void fillOut() {
		delegator.delegateWait(CheckType.CLICKABLE, SUBMIT_XPATH);
		new AutoVehiclePages().sleep(2);
		delegator.delegateWait(CheckType.CLICKABLE, SUBMIT_XPATH);
	}

	@Override
	public void fillOut(SampleData sample) {}
	
	
}
