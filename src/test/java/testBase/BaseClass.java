package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(alwaysRun=true,groups={"sanity","Regression","Master","DataDriven"})
	@Parameters({"OS","Browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading Config.properties
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger =LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			//Remote os
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}
			
			else if(os.equalsIgnoreCase("Linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("MAC")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("Invalid operating system");
				return;
			}
			//Remote Browser
			
			
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MircosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default :System.out.println("Invalid Browse"); return; 
			}
			//creating driver for remote
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
		
		switch(br.toLowerCase()) {
		case "chrome": driver =new  ChromeDriver(); break;
		case "edge": driver =new EdgeDriver(); break;
		case "firefox": driver =new FirefoxDriver(); break;
		default :System.out.println("Invalid Browse"); return; 
		}
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));  //reading appURL From config.properties using getProperty()
		driver.manage().window().maximize();
		
	}	

	public String randomeString() {
		String generateString= RandomStringUtils.randomAlphabetic(10);
		return generateString;
	}
	
	public String randomeNumber() {
		String generateNumber= RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	public String randomAlphaNumeric() {
		String generatedPassword= RandomStringUtils.randomAlphanumeric(8);
		return ("!"+generatedPassword);
	}
	
	public String captureScreen(String tname)throws Exception{
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot=  (TakesScreenshot) driver; 
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname + "_"+ "timestamp"+ ".png";
		File taregetFile = new File(targetFilePath);
		sourceFile.renameTo(taregetFile);
		
		return targetFilePath;
	}
	
	
	@AfterClass(alwaysRun = true,groups= {"sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		driver.quit();	
		
		}
	
	
	

}
