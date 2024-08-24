package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.homePage;
import pageObjects.registerPage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups= {"Master","Regression"})
	public void test_account_registration() {
		
		try {
		logger.info("**** Starting TC001*****");
		
	
		homePage hm=new homePage(driver);
		hm.clickMyAccount();
		hm.clickRegister();
		logger.info("**** Regisrtation page opened *****");
		
		
		registerPage rp=new registerPage(driver);
		logger.info("**** Filling customer details *****");
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setTelephone(randomNumber());
		String password=randomAlphaNumber();
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		
		logger.info("**** validating expected message *****");
		String msg=rp.getConfirmationMsg();
		
		System.out.println(msg);
		
		if(msg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug Logs....");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(msg, "Your Account Has Been Created!!!");
		}
		catch(Exception e) {
			//logger.error("Test Failed...");
			//logger.debug("Debug Logs");
			Assert.fail();
		}
		
		logger.info("**** Finsihed TC001 ***");
		
	}

}
