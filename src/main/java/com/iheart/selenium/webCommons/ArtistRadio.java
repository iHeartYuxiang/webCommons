package com.iheart.selenium.webCommons;

import com.iheart.selenium.utils.WaitUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;



public  class ArtistRadio extends Page {
   
	/*
	@FindBy(css="#player > div.player-left > div.player-info > a.player-artist.type-secondary.type-xsmall") private WebElement songPlaying;
	@FindBy(css=".player-artist") public WebElement songAfterSkip;
    
    //Skip Limit
     @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)") 
         private WebElement firstCustomRadio;
  
    //Custome Radio playing button
    @FindBy(css="button.playing:nth-child(3)") private WebElement customePlayButton;
    
  //logged in: Custom ThumpUp
  	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(2) > i") private WebElement customThumpUp;
  	
  	@FindBy(css="#player > div.player-left > div.player-info > a.player-artist.type-secondary.type-xsmall") private WebElement currentSong;
  	
  	//@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
  	@FindBy(css="#main > div.directory-custom > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")	
  	   private WebElement customFirstLinkPlayButton;
  	 	
  	//@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i") private WebElement customFirstLinkPlayButton;
    @FindBy(css="button.text:nth-child(4)") private WebElement customSkipButton;
  	@FindBy(css=".favorite") private WebElement  customFavorite; 
  	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)") private WebElement firstArtist;
   	*/
  	
  	@FindBy(css="#main > div.directory-custom > div > div > div > select") private WebElement genreSelect;
  	
    @FindBy(css=".station-tiles")  private WebElement stationTiles;
	
	
   public WebElement genres; 
   public By findGenreBy;
	
   public By findStationBy;
   
   public WebElement firstStation;
   public WebElement stationPlaying;
   
   //for create station
   public WebElement stationPlay;
   
	    
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
         player.songTrack.click();
         WaitUtility.sleep(1000);
         stationPlay.click();
         waitForPreroll();
         
    }
    
   public void chooseGenreByIndex(int index)
   {
	   new Select(genreSelect).selectByIndex(index);
   }
    
   public void chooseStationByIndex(int index)
   {   //Artist radio page takes extra logn time to load sometimes.
	   WaitUtility.sleep(5000);
	   List<WebElement>  options = stationTiles.findElements(findStationBy);
       System.out.println("See  STATION count:" + options.size());
       options.get(index).click();
       
       //handle possible pre-roll
        waitForPreroll();
       
   }
   
   public void playStationByIndex(int index)
   {
	   chooseStationByIndex(index);
   }
   
   
}
