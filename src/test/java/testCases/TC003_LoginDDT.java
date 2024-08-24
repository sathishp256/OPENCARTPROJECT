package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.homePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import utilities.DataProviders;


/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass
{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups= {"DDT"})
	public void verify_loginDDT(String email, String password, String exp)
	{
		logger.info("**** Starting TC_003_LoginDDT *****");
		
		try {
	
		//Home page
			homePage hp=new homePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); //Login link under MyAccount
			
				
			//Login page
			LoginPage lp=new LoginPage(driver);
			lp.setUsername(email);
			lp.setPassword(password);
			lp.clickLogin(); //Login button
				
			//My Account Page
			MyAccount macc=new MyAccount(driver);
			boolean targetPage=macc.checkAccount();
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					macc.myaccountlogin();
					macc.logout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.myaccountlogin();
					macc.logout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}
			
		logger.info("**** Finished TC_003_LoginDDT *****");
	}
	
}









