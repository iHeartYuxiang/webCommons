package com.iheart.selenium.webCommons;


import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Navigation extends Page{
   
	public void gotoLiveRadioPage()
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
	
	public void gotoGenrePage()
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
