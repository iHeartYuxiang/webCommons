package com.iheart.selenium.webCommons;

import com.iheart.selenium.utils.WaitUtility;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public  class Player extends Page{
	
	//Station Info section
	@FindBy(css="#bottom-fixed > a .player-station")  private WebElement playerStation;
	@FindBy(css="#bottom-fixed > a .player-song")  private WebElement playerSong;
	@FindBy(css="#bottom-fixed > a .player-artist")  private WebElement playerArtist;
	
	//@FindBy(css="#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hide > div:nth-child(1) > button > i")
	@FindBy(css=".icon-more-horizontal") private WebElement more;
	@FindBy(css="#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hide > div.dropdown-content > nav > ul > li:nth-child(4) > a")
        private WebElement share;
	
	
    //Player controls
	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(1) > i") private  WebElement  thumbDown;
	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(2) > i")  private WebElement thumbUp;
	@FindBy(css="#player > div.player-center > div.player-controls > button.playing.btn-circle.medium.play > i")
	   private WebElement playingButton;
	@FindBy(css="#player > div.player-center > div.player-controls > button.paused.btn-circle.medium.play > i")
		private WebElement pauseButton;
	
	
	@FindBy(css="#player > div.player-center > div > button.btn.text.no-border.xsmall > span")
	   private WebElement skipOrScan;
	
	//middle -slide bar
	@FindBy(css="#player > div.player-center > div.player-duration.artist-radio > span.type-xsmall.type-secondary.player-duration-position")
	   private WebElement songPlayed;
	@FindBy(css="#player > div.player-center > div.player-duration.artist-radio > span.type-xsmall.type-secondary.player-duration-duration")
	   private WebElement songDuration;
	
	
	
	//RIGHT SIDE
	@FindBy(css="#player > div.player-right > button:nth-child(1)") 
	    private WebElement myStations;
	
	@FindBy(css="#player > div.player-right > button:nth-child(2)") 
    private WebElement listenHistory;
	
	//@FindBy(css="#player > div.player-right > button.btn-circle.small > i")
	@FindBy(css=".icon-full-screen-expand")  private WebElement expendIcon;
	@FindBy(css=".icon-full-screen-collapse")  private WebElement collapseIcon;
	
	
	public Player()
	{
		super();
	}
	
	public Player(WebDriver _driver)
	{
		super(_driver);   
		setPlayer(this);
	}
	
	
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
	

    public boolean isFullScreenMode()
    {
    	try {
    		collapseIcon.getAttribute("class");
    		return true;
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
	
   public void expandPlayerToFullScreen()
   {
	   if (!isFullScreenMode())
		   expendIcon.click();
   }
    
   public void collapePlayerToMini()
   {
	   if (isFullScreenMode())
		   collapseIcon.click();
   }
	
	public void play()
	{  
	   	pauseButton.click();
		waitForPreroll();
	}
	 
	
	
	public void doSkip(String type)
	{   
		String currentTrack, nowPlaying;
		currentTrack = playerSong.getText();
	    
		if (!skipOrScan.getAttribute("class").equals("icon-skip"))
			return; 
		
		skipOrScan.click();
	    nowPlaying = playerSong.getText();
	    System.out.println("before/after:" + currentTrack + "/" + nowPlaying);
	    //Verify new episode is playing
	    if (currentTrack.equals(nowPlaying))
	    	handleError("Skip is not working.", "doSkipFor" + type);
	}
	
	
	public void doShare()
	{  
		more.click();
		
		share.click();
	
	}   
	
	
	//default to live Radio
	public void doThumbUp()
	{
		doThumbUp("liveRadio");
	}
	
	
	public void doThumbUp(String stationType)
	{  
		int count = 0; 
		
		//Try a little bit more
		while(isThumbUpDisabled() && count < 3)
		{	System.out.println("thumbUp button is disabled. Scan or skip now..");
		    try{ 
		       skipOrScan.click();
		    }catch(Exception e)
		    {   
		    	
		    }
			count++;
		}
		
		//if it is still disabled, return 
		if(isThumbUpDisabled()) return;
		
		//If this is thumbUp before, double-click
		if (isThumbUpDone())
		{	
			thumbUp.click();
		}

	    thumbUp.click();
		
	}
	
	private boolean isThumbUpDisabled()
	{   
		String disableValue ="";
		try{
		    disableValue =  thumbUp.getAttribute("disabled");
			
		}catch(Exception e)
		{
			
		}
		
		if (disableValue == null) return false;
		
		return disableValue.equals("true");
	
	}
	
	private boolean isThumbDownDisabled()
	{   String disableValue ="";
		try{
		    disableValue =  thumbDown.getAttribute("disabled");
			  
		}catch(Exception e)
		{
			
		}
		if (disableValue == null) return false;
		
		return disableValue.equals("true");
	}
	
	
	//This is for live radio
	public void doThumbDown()
	{
		doThumbDown("liveRadio");
		
	}
	
	
	public void doThumbDown(String stationType)
	{   
		int count = 0; 
		
		//Try a little bit more
		while(isThumbDownDisabled() && count < 3)
		{	System.out.println("thumbDown button is disabled. Scan or skip now..");
		    try{
		       skipOrScan.click();
		    }catch(Exception e)
		    {   
		    	
		    }
			count++;
		}
		
		//if it is still disabled, return 
		if(isThumbDownDisabled()) return;
		
		//If this is thumbUp before, double-click
		if (isThumbDownDone())
		{	
			thumbDown.click();
		}
		
		thumbDown.click();
		
		
	}
	
	private boolean isThumbUpDone()
	{
		return  thumbUp.getAttribute("class").equals("icon-thumb-up-filled");
		   
	}
	
	private boolean isThumbDownDone()
	{
		return  thumbUp.getAttribute("class").equals("icon-thumb-down-filled");
	}
	
	
	
	public void doScan()
	{  
		String currentSong = "";
		try{
		   currentSong = playerSong.getText();
		   System.out.println("Current Song:" + currentSong);
		}catch(Exception e)
		{
			System.out.println("Song track info is missing..");
		}
	
	    if(!skipOrScan.getAttribute("class").equals("icon-scan"))
	    {
	    	handleError("Scan button is not found.", "doScan");
	    	return;
	    }
	    
		try{
	    	skipOrScan.click(); //Scan button could be greyed out when commercial is on? 
		}catch(Exception e)
		{   
			skipOrScan.click();
		}
		waitForPreroll(); 
		//Verify that new song is playing 
	    if (!currentSong.equals(""))
	    {	
			String newSong = "";
			try {
				//in case song track info is not available;
				newSong = playerSong.getText();
			}catch(Exception e)
			{
				
			}
			
		    if (!newSong.equals("") && newSong.equals(currentSong))
				handleError("Scan is not working.", "doScan");
	    }   
		
	}
	
	public void doSkip()
	{   
		if(!skipOrScan.getAttribute("class").equals("icon-scan"))
	    {
	    	handleError("Scan button is not found.", "doScan");
	    	return;
	    }
	    
		
		//Some radio doesn't have this field, such as Yoga music radio.
		String currentSong = "";
		try{
			currentSong = playerSong.getText();
		}catch(Exception e)
		{
			System.out.println("No song track info is provided.");
		}
		
		skipOrScan.click();
		
		
		//Verify that new song is playing 
		if  (!currentSong.equals(""))
		{	
			String newSong = playerSong.getText();
			System.out.println("currentSong/NextSong:" + currentSong + "/" + newSong);
			if (newSong.equals(currentSong))
				handleError("Skip is not working.", "doSkip");
		}	
		
	}
	
	
	public void play_pause()
	{
	   if (isPlaying())
		 pauseButton.click();
	   else
		   playingButton.click();
	}
	
	
	
	public void pauseAndResume(String type)
	{   WebElement theOne;
	    playingButton.click();
		//verify it is paused
	    if(isPlaying())
	    	getErrors().append("Station playing is not paused.");
	    
	    pauseButton.click();
	    //verify it is resumed
	    if(!isPlaying())
	    	getErrors().append("Station playing is not RESUMED.");
	    
	}
	
	
	
	
	public void makeSureItIsPlaying()
	{   
		if (!isPlaying())
			pauseButton.click();
	}
	
	
	 public void play2SongsInArow()
	 {
	    	/*
	    	 * What's the song playing now?
	    	 * Wait for 5 minutes, is a new song playing? If yes, wait for another 5 minutes?
	    	 * if not, wait for another 2 seconds? 
	    	 * 
	    	 * How to make sure that it is just 2 songs?
	    	 */
	    	String currentSong = playerSong.getText();
	    	String _songDuration = songDuration.getText();
	    	System.out.println("songDuration:" + _songDuration);
	    	
	     
	        System.out.println("Next song is about to play");
	       
	        //Check song again: 
	        String nextSong = playerSong.getText();
	        System.out.println("see current/next song:" + currentSong + "/" + nextSong);
	       
	        if (currentSong.equals(nextSong))
	        {   
	        	WaitUtility.sleep(2000);
	        }
	        
	        
	        _songDuration = songDuration.getText();
	    	System.out.println("next songDuration:" + _songDuration);
	    	
	    }
	    
    //duration format: 4:46
    private long convertToMillisecond(String duration)
    {
    	int minutes = Integer.parseInt(duration.split(":")[0]);
    	int seconds = Integer.parseInt(duration.split(":")[1]);
    	
    	long millis = (minutes * 60 * 1000 ) +  (seconds * 1000);
    	System.out.println("See songduration in milli:" + millis);
    	return millis;
    }
	    
	    
	public boolean isCommercialOn()
	{   //if audio commercial is on, thumbup/down button is disabled. 
		
		return isThumbUpDisabled();
	}
	
	public String getStationPlaying()
	{
		return playerStation.getText();
	}
	
	public void gotoListenHistory()
	{
		try{
			listenHistory.click();
		}catch(Exception e)
		{
			//In case that page is resized:
			more.click();
			//click on dropdown. 
			Actions action = new Actions(driver);
			
	    	WebElement we = driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hide > div:nth-child(1) > button > i"));
	    													    
	    	try{
	    	  action.moveToElement(we).moveToElement(driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options > div > nav > ul > li:nth-child(4) > a"))).click().build().perform();
	    	}catch(Exception eX)
	    	{
	    		
	    	}
		
		}
	}
	
}
