package com.iheart.selenium.webCommons;

import java.util.HashMap;
import java.util.Map;

import com.iheart.selenium.utils.WaitUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public abstract class Login  extends Page{
	
	@FindBy(css="button.facebook:nth-child(1)") public WebElement facebookLogin;
	@FindBy(css="button.facebook:nth-child(2)") public WebElement googleLogin;
	
	@FindBy(css="#dialog > div > div > div > div > div > section > ul > li:nth-child(1) > button > span")
	public WebElement faceBook;
	
	@FindBy(id="email") public WebElement faceEmail;
	@FindBy(id="pass") public WebElement facePass;
	@FindBy(id="u_0_2") public WebElement faceLogin;
	
		
	
	//Put different country use different account for testing
	   public static Map<String, String> userAccountByCountry, faceBookByCountry, zipByCountry;
	  // public final String userAccount_US = "iheartRocks999@gmail.com"; //facebook blocked this account
	   public final String userAccount_US = "iheartRadio.tribecca@gmail.com"; 
	   public final String userAccount_AU = "iheartRocks999@gmail.com";
	   public final String userAccount_NZ = "iheartRocks999@gmail.com";
	   public final String facebook_AU = "iheart.au999@gmail.com"; //"iheart.aus@gmail.com";
	   public final String facebook_NZ = "iheart.nz@gmail.com";

    public WebElement loginButton, userName, password, login;
	
	public Login()
	{
		this(driver);
		userAccountByCountry = new HashMap<String, String>();
		userAccountByCountry.put("US", userAccount_US);
		userAccountByCountry.put("AU", userAccount_AU);
		userAccountByCountry.put("NZ", userAccount_NZ);
	   
		faceBookByCountry = new HashMap<String, String>();
		faceBookByCountry.put("US", userAccount_US);
		faceBookByCountry.put("AU", facebook_AU);
		faceBookByCountry.put("NZ", facebook_NZ);
		
		
		//Maybe will move this to Page.java   
		   zipByCountry = new HashMap<String, String>();
		   zipByCountry.put("US", "10013");
		   zipByCountry.put("AU", "2011");
		   zipByCountry.put("NZ", "2016");
	}
	
	public Login(WebDriver driver)
	{
		super(driver);
	}
	
	
	public void login()
	{ 
		login(USER_NAME, PASSWORD);
		
	}
	
	public void login(String _userName, String _password)
	{   
		try{
		   loginButton.getText();
		}catch(Exception e)
		{
			System.out.println("Already loggged in. Do nothing.");
			return;
		}
		
		int count = 0;    //Use retry to handle home-page-flick issue
		boolean isDone = false;
		do{
			
			try{
			   loginButton.click();
			   isDone = true;
			}catch(Exception e)
			{
				System.out.println("LOGIN button is not clicked. try again.");
			}
			
			count++;
		}while (count< 6 && !isDone);
		
    	userName.sendKeys(_userName);
	    password.sendKeys(_password);
	
		login.click();
		
	}
	
	/*
	 * login with facebook account
	 */
	public void facebookSignUp()
	{
		waitForSignUp();
		faceBookSignUp_direct();
	}
	
	
	
	public void faceBookSignUp_direct()
	{   
		try{
		   waitForElement(faceBook, 18000).click();
		}catch(Exception e)
		{
		    driver.findElement(By.cssSelector("#dialog > div > div > div > div > section > ul > li:nth-child(1) > button > span")).click();
  
		}
       
		String winHandleBefore = switchWindow();
		
		faceEmail.sendKeys(getFacebookAccount());
		
		facePass.sendKeys("iheart001");
		faceLogin.click();
	    
	    driver.switchTo().window(winHandleBefore);
	}
	
	
	public void waitForSignUp()
	{
		int count = 0;
		while (count < 6)
		{	
			if (!isSignUpShown()) 
			{	
				WaitUtility.sleep(6*1000);
				count++;
				System.out.println("Waited for signup:" + count + " time(s)");
			}else 
				break;
		}	
	}
	
	private boolean isSignUpShown()
	{
		try{
			//Check for Sign Up button
			String signUp = driver.findElement(By.cssSelector("#dialog > div > div > div > div > div > form > button")).getText();
			//driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2")).getText();
		    if (signUp.equalsIgnoreCase("Sign Up"))
			   return true;
		    else return false;
		}catch(Exception e)
		{   
			return false;
		}
	}
	
	 public static String getUserAccount()
	   {
		   return (String)userAccountByCountry.get(getCountry());
	   }
	   
	   public static String getFacebookAccount()
	   {
		   return (String)faceBookByCountry.get(getCountry());
	   }
	   
	   
	   
	
	
}
