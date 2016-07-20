package com.iheart.selenium.utils;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.iheart.selenium.webCommons.Page;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class Utils {
	
	final static Logger logger = Logger.getLogger(Utils.class);

	public static WebDriver  createWebDriver() 
	
	{
	
	return createWebDriver("firefox");
	
	}
	
	
	public static WebDriver  createWebDriver(String browser) 
	
	{   WebDriver driver;
	
	    if (browser.equalsIgnoreCase("firefox"))
	
	        driver = new FirefoxDriver();
	
	    else if (browser.equalsIgnoreCase("chrome"))
	    {   
	
	       ChromeDriverManager.getInstance().setup();	
	  
	      ChromeOptions options = new ChromeOptions();
	      options.addArguments("test-type");
	      options.addArguments("--start-maximized");
	     
	      driver = new ChromeDriver(options);
	
	      }else if (browser.equalsIgnoreCase("ie"))
	      {    
	    	  InternetExplorerDriverManager.getInstance().setup();
	    	  driver = new InternetExplorerDriver();
	      }else 
	      {
		      logger.info("Unknown browser.");
		      return null;
	      }
	
	      driver.manage().window().maximize();
	
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	      return driver;
	
	  }
	
	
	public static void waitForPageToLoad(WebDriver driver) 
	{
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	
	        public Boolean apply(WebDriver driver) {
	
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	
	        }
	
	      };
	
	    Wait<WebDriver> wait = new WebDriverWait(driver,1000);
	
	      try {
	    	  wait.until(expectation);
	
	      } catch(Throwable error) {
	
	              logger.info("Timeout waiting for Page Load Request to complete.");
	
	      }
	
	} 
	
	
	public static WebDriver launchBrowser(String url, String browser)
	{       
			WebDriver driver = createWebDriver(browser);
			
			driver.get(url);
			//Wait for page to load
	        WaitUtility.sleep(5000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			return driver;
	
	}
	
	
	public static int getRandomInt()
	{
		Random randomGenerator = new Random();
	  
	    int randomInt = randomGenerator.nextInt(999999);
	     
	    return randomInt;
	    
	}
	
	
	public static String getCountry()
	{   
		Map<String, String> geoInfo = new HashMap<String, String>();
		WebDriver driver = Utils.createWebDriver("chrome");
		 driver.navigate().to("http://www.iplocation.net");
		 
		 String country = driver.findElement(By.cssSelector("#wrapper > section > div > div > div.col.col_8_of_12 > div:nth-child(9) > div > table > tbody:nth-child(2) > tr > td:nth-child(2)")).getText();
		 logger.info("What country am I in:" + country);
		 
		 driver.quit();
		 
		 if (country.equalsIgnoreCase("United States"))
			 return "US";
		 else if (country.equalsIgnoreCase("Australia"))
			 return "AU";
		 else if  (country.equalsIgnoreCase("New Zealand"))
		     return "NZ";
		 else 
			 return "unknown";
	}
	
	public static Map<String, String> getLocationByIp(WebDriver driver)
	{    Map<String, String> geoInfo = new HashMap<String, String>();
		 driver.navigate().to("http://www.iplocation.net");
		 String country = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		 String state = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(3)")).getText();
		 String city = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(4)")).getText();
		 
		 geoInfo.put("country", country);
		 geoInfo.put("state", state);
		 geoInfo.put("city", city);
		 
		 
		 
		 return geoInfo;
	}
	

	 public static Map getSystemEnv() 
	 {
	        Map<String, String> env = System.getenv();
	        for (String envName : env.keySet()) {
	            System.out.format("%s=%s%n",
	                              envName,
	                              env.get(envName));
	        }
	        
	        return env;
	    }
	
	
	public static void takeScreenshot(WebDriver driver, String testMethod) throws Exception 
   {      
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
   			Date date = new Date();
   			//logger.info(dateFormat.format(date)); //2014/08/06 15:59:48
	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
	       logger.info("See screenshotName:" + screenshotName);
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
           FileUtils.copyFile(scrFile, new File(screenshotName));
           logger.info("Screenshot is taken.");
   }
   
	   
	 
}
