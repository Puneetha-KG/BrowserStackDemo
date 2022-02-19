package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class TestClass1 implements Runnable {
	public void run() {
		Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
		capsHashtable.put("device", "iPhone 12 Pro");
		capsHashtable.put("real_mobile", "true");
		capsHashtable.put("os_version", "14");
		capsHashtable.put("build", "browserstack-crossBrowser-build-5");
		capsHashtable.put("name", "Thread 1");
		BrowserStackCrossBrowserTesting r1 = new BrowserStackCrossBrowserTesting();
		r1.executeTest(capsHashtable);
	}
}

class TestClass2 implements Runnable {
	public void run() {
		Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
		capsHashtable.put("device", "Google Pixel 5");
		capsHashtable.put("real_mobile", "true");
		capsHashtable.put("os_version", "11.0");
		capsHashtable.put("build", "browserstack-crossBrowser-build-5");
		capsHashtable.put("name", "Thread 2");
		BrowserStackCrossBrowserTesting r2 = new BrowserStackCrossBrowserTesting();
		r2.executeTest(capsHashtable);
	}
}

class TestClass3 implements Runnable {
	public void run() {
		Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
		capsHashtable.put("browser", "safari");
		capsHashtable.put("browser_version", "14");
		capsHashtable.put("os", "OS X");
		capsHashtable.put("os_version", "Big Sur");
		capsHashtable.put("build", "browserstack-crossBrowser-build-5");
		capsHashtable.put("name", "Thread 3");
		BrowserStackCrossBrowserTesting r3 = new BrowserStackCrossBrowserTesting();
		r3.executeTest(capsHashtable);
	}
}

public class BrowserStackCrossBrowserTesting {
	public static final String USERNAME = "puneethakg_eOm0VZ";
	public static final String AUTOMATE_KEY = "PkR3JyjqGGgpKDE97Cb8";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) throws Exception {
		Thread object1 = new Thread(new TestClass1());
		object1.start();
		Thread object2 = new Thread(new TestClass2());
		object2.start();
		Thread object3 = new Thread(new TestClass3());
		object3.start();
	}

	public void executeTest(Hashtable<String, String> capsHashtable) {
		String key;
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserstack.safari.allowAllCookies", "false");
		// Iterate over the hashtable and set the capabilities
		Set<String> keys = capsHashtable.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			key = itr.next();
			caps.setCapability(key, capsHashtable.get(key));
		}
		WebDriver driver;
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
			
			driver.manage().deleteCookieNamed ("CONSENT");
			driver.manage().addCookie(new Cookie("CONSENT","YES+shp.gws-"+LocalDate.now().toString().replace("-","")+"-0-RC2.en+FX+374"));
			driver.navigate().refresh();
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			driver.manage().deleteCookieNamed ("CONSENT");
			//driver.manage().addCookie(new Cookie("CONSENT","YES+shp.gws-"+LocalDate.now().toString().replace("-","")+"-0-RC2.en+FX+374"));
			//driver.navigate().refresh();
			
			// Searching for 'BrowserStack' on google.com
			driver.get("https://www.google.com");
			WebElement element = driver.findElement(By.name("q"));
			element.sendKeys("BrowserStack");
			element.submit();
			// Setting the status of test as 'passed' or 'failed' based on the condition; if
			// title of the web page contains 'BrowserStack'
			WebDriverWait wait = new WebDriverWait(driver, 5);
			try {
				wait.until(ExpectedConditions.titleContains("BrowserStack"));
				jse.executeScript(
						"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
			} catch (Exception e) {
				jse.executeScript(
						"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
			}
			System.out.println(driver.getTitle());
			driver.quit();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
