package com.iheart.selenium.webCommons;

import com.iheart.selenium.utils.WaitUtility;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class SignUp extends Page {
	

	@FindBy(css=".dialog-close > div:nth-child(1) > button:nth-child(1)") public WebElement icon_close;
	@FindBy(css="#dialog > div > div > div > header > div.type-secondary > span > a")  public WebElement signUpLink;
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2") public WebElement signupHeader;
	@FindBy(css=".dialog-title")   public WebElement signupHint; //Have an account? Log In
	          
	@FindBy(css="[name='userName'][type='text']")  public WebElement email;
	@FindBy(css="[name='password'][type='password']")  public WebElement password;
	@FindBy(name="zipCode")
	    public WebElement zipCode;
	
	@FindBy(name="birthYear") public WebElement birthYear; 
	
	@FindBy(css="[name='gender'][value='Female']")	
	    public WebElement gender_female;		
	
	@FindBy(name="termsAcceptanceDate") public WebElement termsAcceptanceDate;
	
	@FindBy(css="#dialog > div > div > div > div > div > form > button")
	   public WebElement signUp;
	
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(1) > button:nth-child(1)") public WebElement signedAccount;

	
	
	public void signUp()
	{   
		waitForSignUp();
		
		String  randomEmail_firstPart = getCurrentDateInMilli();
		String _email = randomEmail_firstPart + "@mailinator.com";
		System.out.println("See randomEmail:" + _email);
		
	    waitForElement(email, 10000).sendKeys(_email);
	    password.sendKeys("iheart001");

	    zipCode.sendKeys(getZip());
	    new Select(birthYear).selectByIndex(20);
	    gender_female.click();
	    signUp.click();
	    
	    System.out.println("see signed account:" + signedAccount.getText() );
	    signedAccount.click();
	    
	    if (!signedAccount.getText().contains(randomEmail_firstPart))
	    	getErrors().append("Signup failed.");
	    
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

	
	public String getCurrentDateInMilli()
	{
		Date date = new Date();
		return date.getTime() + "";
	}
}
