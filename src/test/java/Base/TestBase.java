package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.mysql.cj.log.Log;

import Utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static Logger log = Logger.getLogger(TestBase.class.getName());
	public static FileInputStream fis;
    public static ExcelReader read = new ExcelReader(".\\src\\test\\resources\\Excel\\User.xlsx");
	public static WebDriverWait wait;
	
	public void typeEle(String key, String value)
	{
		
		if(key.contains("_xpath"))
			{
				
			driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);;
				log.info("Xpath, Types In "+key);
			}
		else if(key.contains("_css"))
			{
				driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
				log.info("CSS, Types In "+key);
			}
		else if(key.contains("_id"))
			{
				driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
				log.info("ID, Types In "+key);
			}
		else if(key.contains("_name"))
			{
				driver.findElement(By.name(OR.getProperty(key))).sendKeys(value);;
				log.info("Name, Types In "+key);
			}
	}
	
	public void click(String key)
	{
		
		if(key.contains("_xpath"))
			{
				driver.findElement(By.xpath(OR.getProperty(key))).click();
				log.info("Xpath, clicked on "+key);
			}else if(key.contains("_css"))
			{
				driver.findElement(By.cssSelector(OR.getProperty(key))).click();
				log.info("CSS, clicked on "+key);
			}else if(key.contains("_id"))
			{
				driver.findElement(By.id(OR.getProperty(key))).click();
				log.info("Id, clicked on "+key);
			}else if(key.contains("_name"))
				
			{
				driver.findElement(By.name(OR.getProperty(key))).click();
				log.info("Name, clicked on "+key);
			}
	}
	
	/*
	public boolean isElementFound(String key)
	{
		
		if(key.contains("_xpath"))
			{
			try {
				driver.findElement(By.xpath(OR.getProperty(key)));
				return true;
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info(e.getMessage());
				return false;
			}
				
			}else if(key.contains("_css"))
			{
				driver.findElement(By.cssSelector(OR.getProperty(key)));
				log.info("CSS, clicked on "+key);
			}else if(key.contains("_id"))
			{
				driver.findElement(By.id(OR.getProperty(key)));
				log.info("Id, clicked on "+key);
			}else if(key.contains("_name"))
				
			{
				driver.findElement(By.name(OR.getProperty(key)));
				log.info("Name, clicked on "+key);
			}
	}*/
	
	
	@BeforeSuite
	public void setUP() {
		if (driver == null) {
			PropertyConfigurator.configure(".\\src\\test\\resources\\Properties\\log4j.properties");
			log.info("Log4J Properties File Configured");
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\Properties\\OR.properties");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("Or Properties Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.info("Config Properties Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(Config.getProperty("testBrowser").equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("Chrome Browser Opened");
			}else if(Config.getProperty("testBrowser").equalsIgnoreCase("ie"))
			{
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("Internet Explorer Browser Opened");
			}else if(Config.getProperty("testBrowser").equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox Browser Opened");
			}else
			{
				log.info("Wrong Brower Name");
			}
			
			driver.get(Config.getProperty("testSite"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),TimeUnit.SECONDS);;
			wait = new WebDriverWait(driver, 5);
			
		}
	}
	
	@AfterSuite
	public void tearDown()
	{
		driver.close();
	}

}
