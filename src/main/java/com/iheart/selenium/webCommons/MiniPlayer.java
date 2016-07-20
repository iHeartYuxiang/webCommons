package com.iheart.selenium.webCommons;



import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MiniPlayer extends Page {
	
	
	//Station Info section
	@FindBy(css="#bottom-fixed > a .player-station")  private WebElement playerStation;
	@FindBy(css="#bottom-fixed > a .player-song")  private WebElement playerSong;
	@FindBy(css="#bottom-fixed > a .player-artist")  private WebElement playerArtist;
	
	
    //Player controls
	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(1) > i") private  WebElement  thumbDown;
	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(2) > i")  private WebElement thumbUp;
	@FindBy(css="#player > div.player-center > div.player-controls > button.playing.btn-circle.medium.play > i")
	   private WebElement playingButton;
	@FindBy(css="#player > div.player-center > div.player-controls > button.paused.btn-circle.medium.play > i")
		private WebElement pauseButton;
	
	@FindBy(css="#player > div.player-right > button:nth-child(1)") 
	    private WebElement myStations;
	
	@FindBy(css="#player > div.player-right > button:nth-child(2)") 
    private WebElement listenHistory;
	
	//@FindBy(css="#player > div.player-right > button.btn-circle.small > i")
	@FindBy(css=".icon-full-screen-expand")  private WebElement expendIcon;
	
	
	
    public boolean isPlaying()
    {
    	try {
    		playingButton.getAttribute("class");
    		return true;
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
	
	
  

}
