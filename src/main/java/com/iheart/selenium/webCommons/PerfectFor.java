package com.iheart.selenium.webCommons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import com.iheart.selenium.utils.WaitUtility;

import java.util.List;
import java.util.ArrayList;


public  class PerfectFor extends Page{
	/*
	public WebElement activityList;  //all kinds of catogories
	public WebElement chosenActivity; 
	public By findActivityBy;
	
	//Second Layer
	public WebElement playList;
	public By findPlayListBy;
	
	 public PerfectFor()
    {
    	this(driver);
    }
    
    public PerfectFor(WebDriver driver)
    {
    	super(driver);
    }

    
    public void chooseActivityByIndex(int index)
    {
    	List<WebElement>  options = activityList.findElements(findActivityBy);
        options.get(index).click();
    }
    
    public void chooseActivityByName(String activity)
    {
    	List<WebElement>  options = activityList.findElements(findActivityBy);
    	for (WebElement option: options)
    	{	
           if (option.getText().contains(activity))
           {
        	   option.click();
        	   break;
           }
    	}
    }
    
    
    public void choooseStationByIndex(int index)
    {
    	//Some times POPUP comes up 'Glad you like it...' simply click on top bar to get rid of it
    	
    	
        List<WebElement>  lists = new ArrayList<WebElement>() ;
        try{
           lists = playList.findElements(findPlayListBy);
        }catch(Exception e)
        {
            //dismissPopup();
        	Page.dismissAllPopups();
            lists = playList.findElements(findPlayListBy);
        }
        lists.get(index).click();
        
        if (!Page.mediaType.equals("web"))
        {	   
 	       try{
 	         Page.dismissAllPopups();
 	       }catch(Exception e)
 	       {
 	    	   
 	       }
        }
        
       waitForPreroll();
        
    }
    
    
  
    public void playFirst( )
    {
        chooseActivityByIndex(0);
        
        choooseStationByIndex(0);
        
    	waitForPreroll();
    }
    
    
    
    public void playStationFor(String activity)
    {
    	chooseActivityByName(activity);
        
        choooseStationByIndex(0);
        
    	waitForPreroll();
    }
    
    
    public void playStationByIndex(int activityIndex)
    {
    	playStationByIndex(activityIndex, 0);
    }
	
    public void playStationByIndex(int activityIndex, int stationIndex)
    {
    	chooseActivityByIndex(activityIndex);
        WaitUtility.sleep(2000);
        choooseStationByIndex(stationIndex);
        
    	waitForPreroll();
    }
    
   
	 */

}
