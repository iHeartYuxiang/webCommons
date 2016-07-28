package com.iheart.selenium.webCommons;


import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.iheart.selenium.utils.WaitUtility;

public class Navigation extends Page{
   
	public static void gotoLiveRadioPage_direct()
	{   
		String currentURL = driver.getCurrentUrl();
	
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	  //  String newURL = part1 + "//" + part2 + "/live/country/US/" ;
	    String newURL = part1 + "//" + part2 + "/live/country/" + Page.getCountry() +"/" ;
		System.out.println("SEE new url:"  + newURL );
		
		int count = 0;
		do {
		   driver.get(newURL);
			System.out.println("See url in browser now: " + driver.getCurrentUrl());
			count++;
		}while (!driver.getCurrentUrl().contains("live") && count < 3);
		
		
	}
	
	
	public static void gotoArtistRadioPage_direct()
	{  
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/artist/" ;
		System.out.println("SEE new url:"  + newURL );
		
		int count = 0;
		do{
	    	driver.get(newURL);
	    	WaitUtility.sleep(3000);
	    	count++;
		}while (!driver.getCurrentUrl().contains("artist") && count < 3); 
		
	}


	
	public static void gotoPerfectFor_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/perfect-for/" ;
		System.out.println("SEE new url:"  + newURL );
		int count = 0;
		do {
		   driver.get(newURL);
			System.out.println("See url in browser now: " + driver.getCurrentUrl());
			count++;
		}while (!driver.getCurrentUrl().contains("perfect") && count < 3);
		WaitUtility.sleep(1000);
	}

	
	
	public static void gotoPodcastPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/show/" ;
		System.out.println("SEE new url:"  + newURL );
		
		
		int count = 0;
		do {
		   driver.get(newURL);
			System.out.println("See url in browser now: " + driver.getCurrentUrl());
			count++;
		}while (!driver.getCurrentUrl().contains("show") && count < 3);
		
		WaitUtility.sleep(3000);
	}


	
	
	public void gotoGenrePage_direct()
	{   
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/genre/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);

	}
	
	
    
}
