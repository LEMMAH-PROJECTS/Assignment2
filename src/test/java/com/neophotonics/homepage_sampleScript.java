package com.neophotonics;
import java.io.File;
//import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;

public class homepage_sampleScript {

	String username = "projectslemmah";
	String accesskey = "8ruDh6edsHhQj15DzNKouiJuS3wS62h1imby93xyx2j2fdRbgx";
	static RemoteWebDriver driver = null;
	String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	
	//@BeforeMethod
	//public synchronized void beforeMethod(Method method) throws MalformedURLException{
		
		//recorder = new ATUT
	//}
	
	@Test
	public static void homepageTest() {
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		new homepage_sampleScript().mainPage();
	}
	
	public void mainPage() {
		
		initialSetup();
		try {
			driver.get("https://www.neophotonics.com/");
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rev_slider_1_1\"]/rs-bullets/rs-bullet[1]")));
			//driver.wait(1000);
			driver.findElement(By.xpath("//*[@id=\"rev_slider_1_1\"]/rs-bullets/rs-bullet[1]")).click();
			driver.findElement(By.xpath("//*[@id=\"slideroneImage\"]/img")).click();
			
			//Switch to the required tab
	        ArrayList<String> ta = new ArrayList<String> (driver.getWindowHandles());
	        int i=ta.size();
	        System.out.println(i);
	       // driver.switchTo().window(ta.get(1));
	        driver.findElement(By.id("//*[@id=\"botr_A3rpdmZh_zgx4Ugky_div\"]/div[2]/video")).click();
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
	        LocalDateTime now = LocalDateTime.now();
	        System.out.println(dtf.format(now));
	        
			File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(System.getProperty("user.dir") + "/Screenshots/" + dtf.format(now) + ".png"));
			
			System.out.println("Clicked on Video-1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			tearDown();
		}
	}
	
	private void initialSetup() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("build", "6.1.5");
        capabilities.setCapability("name", "neophotonicsHomePageTest");
        capabilities.setCapability("resolution", "1024x768");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	private void tearDown() {
        if (driver != null) {
            System.out.println(((JavascriptExecutor) driver).executeScript("lambda-status=" + status));
            driver.quit(); //really important statement for preventing your test execution from a timeout.
        }
    }
}
