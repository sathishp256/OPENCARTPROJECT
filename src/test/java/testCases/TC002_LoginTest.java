package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import pageObjects.homePage;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Master","Sanity"})
	public void testLogin() {
	
	try {
	//homepage
	logger.info("***** Starting TC002 Execution****");
	homePage hm=new homePage(driver);
	hm.clickMyAccount();
	hm.clickLogin();
	
	//loginpage
	LoginPage lp=new LoginPage(driver);
	logger.info("**** LOGIN PAGE OPENED*****");
	lp.setUsername(p1.getProperty("username"));
	lp.setPassword(p1.getProperty("password"));
	lp.clickLogin();
	
	//myaccountpage
	MyAccount my=new MyAccount(driver);
	boolean result=my.checkAccount();
	logger.info("****Login Sucessful****");
	
	//Assert.assertEquals(result, true,"LoginFailed");
	
	Assert.assertTrue(result);
	}
	catch(Exception e) {
		logger.error("Failed");
		
		Assert.fail();
		
	}
	
	logger.info("**** Test Execution Completed ****");
		
	}
	

}
