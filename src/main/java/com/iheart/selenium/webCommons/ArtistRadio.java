package com.iheart.selenium.webCommons;

import com.iheart.selenium.utils.WaitUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public  class ArtistRadio extends Page {
   
	
  	@FindBy(css="#main > div.directory-custom > div > div > div > select") private WebElement genreSelect;
  	
    @FindBy(css=".station-tiles")  private WebElement stationTiles;
	
	@FindBy(css="#main > div:nth-child(2) > div > div.header-player > div > div > div.station-info > button > i") 
		private WebElement stationPlay;
	    
    public ArtistRadio()
    {
    	this(driver);
    }
    
    public ArtistRadio(WebDriver driver)
    {
    	super(driver);
    }

    
    
    public void createAstation()
    {
         Page.getPlayer().getPlayerSong().click();
         WaitUtility.sleep(1000);
         stationPlay.click();
         waitForPreroll();
         
    }
    
   public void chooseGenreByIndex(int index)
   {
	   new Select(genreSelect).selectByIndex(index);
   }
    
   public void chooseStationByIndex(int index)
   {   
	   List<WebElement>  options = stationTiles.findElements(By.className("icon-play"));
       System.out.println("See  STATION count:" + options.size());
       options.get(index).click();
       
        waitForPreroll();
       
   }
   
   public void playStationByIndex(int index)
   {
	   chooseStationByIndex(index);
   }
   
   
}
