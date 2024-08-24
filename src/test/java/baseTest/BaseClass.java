package baseTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p1;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException {
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p1=new Properties();
		
		p1.load(file);
		
		logger=LogManager.getLogger(this.getClass()); //log4j2 to load the loag4j2 file into project
		
		// **********  execution from REMOTE ENV **********
		
		if(p1.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
		DesiredCapabilities cap=new DesiredCapabilities();
		
		// os and browser from xml file
		
		if(os.equalsIgnoreCase("windows")) 
		{
			
			cap.setPlatform(Platform.WIN10);
			
		}
		else if(os.equalsIgnoreCase("linux")) 
		{
			cap.setPlatform(Platform.LINUX);
		}
		else 
		{
		
			System.out.println("Invalid OS");
			return;
			
		}
		
		//browser
		
		switch(br.toLowerCase()) {
		case "chrome": cap.setBrowserName("chrome");break;
		case "edge": cap.setBrowserName("MicrosoftEdge");break;
		case "firefox": cap.setBrowserName("firefox");break;
		default: System.out.println("Invalid Browser");return;
		}
		
		driver=new RemoteWebDriver(new URL("http://192.168.47.5:4444"),cap);
		
		}
		
		
		
		
		//execution in local env
		if(p1.getProperty("execution_env").equalsIgnoreCase("local"))  
		{
		switch(br.toLowerCase()) {
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver=new EdgeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		default:System.out.println("invalid browser name");return;
		}
	
		}
		
		
	
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p1.getProperty("url"));
		
	}
	
	
	public String randomString() {
		String random=RandomStringUtils.randomAlphabetic(5);
		return random;
	}
	
	public String randomNumber() {
		String random=RandomStringUtils.randomNumeric(10);
		return random;
	}
	
	public String randomAlphaNumber() {
		
		String randomstring1=RandomStringUtils.randomAlphabetic(5);
		String randomstring2=RandomStringUtils.randomNumeric(10);
		String random=RandomStringUtils.randomAlphanumeric(8);
		return (randomstring1+"@"+randomstring2);
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		
		driver.quit();
		
		
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
