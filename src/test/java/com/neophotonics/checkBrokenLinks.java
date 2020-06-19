package com.neophotonics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.helper.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.neophotonics.utility.WriteToTxtFile;



public class checkBrokenLinks {
//	private static final String USER_AGENT = "Mozilla/5.0";
//	private static final String URL = "https://www.neophotonics.com/";
	private static String filePath = System.getProperty("user.dir") + "/outputData/brokenLinksReport.txt";
	private static String message = "";
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		try {
			// Set the path of the ChromeDriver
			if (OSValidator.isWindows()) {
				System.out.println("This is Windows");
				System.setProperty("webdriver.chrome.driver",
						"" + System.getProperty("user.dir") + "/drivers/windows/chromedriver.exe");
			} else if (OSValidator.isMac()) {
				System.out.println("This is Mac");
				System.setProperty("webdriver.chrome.driver",
						"" + System.getProperty("user.dir") + "/drivers/mac/chromedriver");
			}
	    	// Create a new chrome web driver
	    	WebDriver driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.get("https://www.neophotonics.com/");
			
			List<WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("Total links are : " + links.size());
			
			for(int i=0;i<links.size();i++) {
				System.out.println(links.get(i).getText());
				message = links.get(i).getText();
				WebElement ele = links.get(i);
				String url = ele.getAttribute("href");
				//System.out.println("List Index : " + i + "URL : " + url);
				verifyLinkActive(url);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void verifyLinkActive(String linkurl) throws IOException {
		
		try {
			URL url = new URL(linkurl);
			HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();
			httpURLConnect.setRequestMethod("GET");
//			httpURLConnect.setRequestProperty("User-Agent", USER_AGENT);
			httpURLConnect.setConnectTimeout(3000);
			//httpURLConnect.connect();
			int responseCode = httpURLConnect.getResponseCode();
			if(responseCode==200)
			{
				System.out.println(linkurl + " - " + " Link OK");
				message = message + linkurl + " - " + " Link OK";
				WriteToTxtFile.WriteFile(filePath,message);
						//httpURLConnect.getResponseMessage());
//				BufferedReader responseReader = new BufferedReader(new InputStreamReader(
//						httpURLConnect.getInputStream()));
//				String responseLine;
//	            StringBuffer response = new StringBuffer();
//	            while ((responseLine = responseReader.readLine()) != null) {
//	                response.append(responseLine+"\n");
//	            }
//	            responseReader.close();
	            // print result
	           //System.out.println(response.toString()); 
			}
			//return null;
			if (httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
			{
				System.out.println(linkurl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
				message = message + linkurl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND;
				WriteToTxtFile.WriteFile(filePath,message);
			}
			message = "";
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("mMalformedURL Exception :" + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
}
