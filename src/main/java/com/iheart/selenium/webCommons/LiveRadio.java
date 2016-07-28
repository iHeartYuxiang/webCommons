package com.iheart.selenium.webCommons;

//import com.iheart.selenium.utils.WaitUtility;


import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public  class LiveRadio extends Page{
	
	
	//Filter station
	@FindBy(css=".country-filter > div:nth-child(1) > select:nth-child(2)") private WebElement country;
	@FindBy(css=".market-filter > div:nth-child(1) > select:nth-child(2)") private WebElement city;
	@FindBy(css="div.form-group:nth-child(3) > div:nth-child(1) > select:nth-child(2)") private WebElement genres_dropDown;
	@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)")  private WebElement genres;
	
    @FindBy(css=".station-tiles")  private WebElement stationTiles;
	
  
    
    public LiveRadio()
    {
    	this(driver);
    }
    
    public LiveRadio(WebDriver driver)
    {
    	super(driver);
    }
    
   public void playRandomStation()
   {
	   //Generate random number, and play that station
   }
   
	
	public void filterLiveStation()
	{   
		filterStation();
	    
		String chosenStation = playFirstStation();
		
		System.out.println("chosen station :" + chosenStation);
		
	}
	
	
	private void filterStation()
	{   
	    new Select(country).selectByIndex(1);
		
		new Select(driver.findElement(By.name("city"))).selectByIndex(1);
		  
	}	
	
	
	public String playFirstStation()
	{
	    return playStationByIndex(0);
	}
	
	/*
	 * @Return playing-station name
	 */
	public String playStationByIndex(int index)
	{
	   List<WebElement>  stations = stationTiles.findElements(By.className("station-thumb"));//By.className("icon-play"));
       System.out.println("See  STATION count:" + stations.size());
       String chosenStationName = stations.get(index).getAttribute("alt");
       stations.get(index).click();
      
        waitForPreroll();
        
       return  chosenStationName;
       
	}
	
	
}
