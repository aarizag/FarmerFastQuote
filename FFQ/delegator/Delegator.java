package delegator;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.seminar.UniversalDriver;

/*
 * Class to delegate various explicit wait functions.
 */
public class Delegator {
	
	public enum CheckType {
		ENABLED_AND_DISPLAYED,
		LIST_EXISTS,
		CLICKABLE,
		INVALID_ENTRY;
	}
	
	private static HashMap<CheckType, Function> toFunction = null;  // Singleton HashMap : instantiated on first use of Delegator
	
	public Delegator() {
		if (toFunction == null) {
			toFunction = new HashMap<>();
			toFunction.put(CheckType.ENABLED_AND_DISPLAYED, new EnablednDisplayed());
			toFunction.put(CheckType.LIST_EXISTS, new ListExists());
			toFunction.put(CheckType.INVALID_ENTRY, new InvalidEntry());
			toFunction.put(CheckType.CLICKABLE, new Clickable());
		}
	}
	public void delegateWait(WebDriver d, CheckType arg, String xpath) {
		delegateWait(d,arg,xpath,10);
	}
	public void delegateWait(CheckType arg, String xpath) {
		WebDriver d = UniversalDriver.getInstance();
		delegateWait(d, arg,xpath,10);
	}
	public void delegateWait(WebDriver d, CheckType arg, String xpath, int time) {
		Function foo = toFunction.get(arg);
		foo.setXpath(xpath);
		wait(foo, d, time);
	}
	
	private void wait(Function f, WebDriver d, int time) {
		new WebDriverWait(d, time).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return f.exec(d);
			}
		});
	}
	
}
