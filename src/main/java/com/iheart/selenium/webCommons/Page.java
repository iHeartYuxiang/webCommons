package com.iheart.selenium.webCommons;

import com.iheart.selenium.utils.WaitUtility;
import com.iheart.selenium.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.apache.log4j.Logger;

public abstract class Page {
	
	/* Generic page structure
	 *  1. Page header
	 *  2. Page View Container: filter, station tiles, stationLogo, stationName, artistName
	 *  3. Footer 
	 *  4. miniPlayer (web: id="bottom-fxed"; Android: com.clearchannel.iheartradio.controller:id/miniplayer_layout;IOS:      
	 * 
	 * 
	 * 
	 */
	
	final static Logger logger = Logger.getLogger(Page.class);
	
	public WebElement header;
	public WebElement subMenu; //Usually contains filter, tiles
	   public WebElement filter; //or subMenu
	   
	   public WebElement stationTiles;
	   public By chooseStationBy;
	   public By choosePlayIconBy;//for web only
	   public By chooseStationLogoBy ;
	   public By chooseStationNameBy;
	   public By chooseStationDescriptionBy;
		  
	   
	  
	public WebElement footer;
	public WebElement miniPlayer;
	
 	
	public static WebDriver driver;
	
	static Player player;
	
	static String browser ="";
	static final String USER_NAME ="iheartrocks888@gmail.com";
	static final String PASSWORD ="iheart001";
	static final String FACEBOOK_USER_NAME = USER_NAME;
	static final String GOOGLE_USER_NAME = USER_NAME;
	
	//for ubiquitous Search function
	private static WebElement searchField;
	private static String searchTerm;
	private static WebElement searchButton;
	private static WebElement firstSearchItem;
	
	private static String country ="US";  //Default to US
	
	private static Map<String, String>  zipByCountry;
	private static final String screenshot_folder="Screenshots";
	
	private static StringBuffer errors = new StringBuffer(); 
	
	

	
	public Page()
	{  
		PageFactory.initElements(driver, this);
		
	}
	
	public Page(WebDriver _driver)
	{   
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		
		
	   zipByCountry = new HashMap<String, String>();
	   zipByCountry.put("US", "10013");
	   zipByCountry.put("AU", "2011");
	   zipByCountry.put("NZ", "2016");
	  
	}
	
	
	
	   
   public static String getZip()
   {
	   return (String)zipByCountry.get(getCountry());
   }

   public static void setCountry(String _country)
   {
	   country = _country;
   }
   
   public static String getCountry()
   {
	   return country;
   }
	  
	   
	public static void search(String searchTerm)
	{     searchField = driver.findElement(By.cssSelector("#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > form > div.form-group.ui-inline-block.search-input > input"));
		   searchField.clear();
		   searchField.sendKeys(searchTerm);
		   WaitUtility.sleep(1000);
		   
		    WebElement firstStationTitle = driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > section:nth-child(1) > ul > li > div > p.title > a"));
				   //resultList.findElements(By.className("title")).get(0);
		   firstStationTitle.click();
		   WaitUtility.sleep(1000);
		   try{
		     driver.findElement(By.className("hero-content")).findElement(By.className("icon-play")).click();
		   }catch(Exception e)
		   {
			   
		   }
		    
		   waitForPreroll();
		
	}
	
	public boolean  isElementPresent(WebElement element)
	{
		 try{
			  System.out.println("see element:" +  element.getText());
			   return true;
		   }catch(Exception e)
		   {  // e.printStackTrace();
			   return false;
		   }
	}
	
	public static void setBrowser(String _browser)
	{
		browser = _browser;
	}
	
	
	public static void setDriver(WebDriver _driver)
	{
		driver = _driver;
	}
	
	public static void setPlayer(Player _player)
	{
		player = _player;
	}
	
	public static Player  getPlayer()
	{
		return player ;
	}
	
	
	public static void waitForPreroll()
	{
		WaitUtility.sleep(38000);
	}
	
	public static StringBuffer getErrors()
	{
		return errors;
	}
	
	public void handleError(String msg, String methodName) 
	{
		errors.append(msg);
		try{
		   Utils.takeScreenshot( driver,  methodName);
		}catch(Exception e)
		{
			System.out.println("Exception is thrown taking screenshot.");
		}
	}
	
	
	public String  switchWindow()
	{
		//Switch to new tab where the sign-up is
		String winHandleBefore = driver.getWindowHandle();
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}	
		return winHandleBefore;
	}
	
	
	
	public static void clearErrors()
	{
		errors.setLength(0);
	}
	
	public static String getUserName()
	{
		return USER_NAME;
	}
	
	public static String getPassword()
	{
		return PASSWORD;
	}
	
	
    public static void takeScreenshot(WebDriver driver, String testMethod) throws Exception 
    {      
 	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    			Date date = new Date();
    			//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
 	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
 	       System.out.println("See screenshotName:" + screenshotName);
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File(screenshotName));
            System.out.println("Screenshot is taken.");
    }
    
    
    public static WebElement waitForElement( WebElement element, long timeOutInMilliSecond)
	{
    	return waitForElement(driver, element, timeOutInMilliSecond);
	}
    
	//.isDisplayed() doesn't work with iheart elements, 
	public static WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInMilliSecond)
	{  
		long times = timeOutInMilliSecond / 500 + 1;    
		long count = 0;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		do{
			try{
			   System.out.println(element.getAttribute("outerHTML"));
			  if (element.isEnabled())
			      break;
			}catch(Exception e)
			{  System.out.println("Not there. try again.");
			  // e.printStackTrace();
			   WaitUtility.sleep(500);
			}
			
			count++;
		}while (count< times);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return element;
	}

    
}
