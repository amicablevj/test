package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory { 
	
	WebDriver driver;
	
	public WebDriver fireFoxBrowser(String url){
		
		//Set property for FireFoxDriver
			System.setProperty("webdriver.gecko.driver", "C:\\HPM Client\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
	}
	
	public WebDriver chromeBrowser(String url){
	
			//Set property for ChromeDriver
			System.setProperty("webdriver.chrome.driver", "C:\\HPM Client\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
	}
		
	public WebDriver ieBrowser(String url){
		
			//Set property for InternetExplorerDriver
			System.setProperty("webdriver.ie.driver", "C:\\HPM Client\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(url);
			return driver;
			
	}
		
}
