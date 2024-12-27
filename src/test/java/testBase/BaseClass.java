package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	

	 public static WebDriver driver;
	 public Logger logger;
	 public Properties p;

	  @BeforeClass(groups= {"Sanity","Regression","Master"})
	  @Parameters({"OS","browser"})
	  public void setup(String OS,String br) throws IOException
	  {
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		 p.load(file);
		  logger=LogManager.getLogger(this.getClass());
		  switch(br.toLowerCase())
		  {
		  case "chrome": driver=new ChromeDriver();break;
		  case "edge":   driver= new EdgeDriver();break;
		  case "firefox":driver= new FirefoxDriver();break; 
		  default: System.out.println("Wrong browse name:");return;
		  }
		  
		  
		  driver.manage().deleteAllCookies();
		  driver.get(p.getProperty("AppUrl"));
		  driver.manage().window().maximize();
		  
	  }
	@AfterClass(groups= {"Sanity","Regression","Master"})
	  public void tearDown() throws InterruptedException
	  {
		  Thread.sleep(3000);
		  driver.quit();
	  }
	
	public String randomString()
	{
		String randomstring=RandomStringUtils.randomAlphabetic(5);
		return randomstring;
	}
	
	public String randomNumber()
	{
		String randomNumber=RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}
	public String alphanumeric()
	{
		String randomstring=RandomStringUtils.randomAlphabetic(3);
		String randomNumber=RandomStringUtils.randomNumeric(2);
		return (randomstring+"@"+randomNumber);
	}
	
	public String captureScreen(String tname)
	{
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp;
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}

}
