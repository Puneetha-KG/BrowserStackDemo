package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackDemo {
	 public static final String AUTOMATE_USERNAME = "puneethakg_eOm0VZ";
	  public static final String AUTOMATE_ACCESS_KEY = "PkR3JyjqGGgpKDE97Cb8";
	  public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	  
	  
	  public static void main(String[] args) throws Exception {
		  
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("os", "Windows");
		  caps.setCapability("os_version", "10");
		  caps.setCapability("browser", "Chrome");
		  caps.setCapability("browser_version", "latest");
		  caps.setCapability("project", "BrowserStackDemo");
		  caps.setCapability("build", "Build3");
		  caps.setCapability("name", "Build3");
		  caps.setCapability("browserstack.local", "false");
		  caps.setCapability("browserstack.networkLogs", "true");
		  caps.setCapability("browserstack.selenium_version", "3.14.0");
	    
	    
	    
	    final WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);

	
		// invoke the browser 
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers");

	   // maximize the browser window
		
		driver.manage().window().maximize();
		
		//locators
		
		// ID
		
		// entering username text in the text feild
		
		WebElement username = driver.findElement(By.id("txtUsername"));
		
		Thread.sleep(1000);
		
		username.sendKeys("Admin");
		
		// entering password  text in the text feild
		
		WebElement password  = driver.findElement(By.id("txtPassword"));
		
		Thread.sleep(1000);
				
		password.sendKeys("admin123");
		
		// click on login button
		
		WebElement login   = driver.findElement(By.xpath("//input[@id='btnLogin']"));
		
		login.click();
		
		
		
		driver.quit();
		
	  }
	     
	  // This method accepts the status, reason and WebDriver instance and marks the test on BrowserStack
	  public static void markTestStatus(String status, String reason, WebDriver driver) {
	    final JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+ status + "\", \"reason\": \"" + reason + "\"}}");
	  }

}
