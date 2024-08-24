package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends basePage {

	public MyAccount(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//h2[normalize-space()='My Account']")
WebElement myaccount;

@FindBy(xpath="//a[@title='My Account']")
WebElement myaccountlogin;

@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
WebElement logout;

public boolean checkAccount() {
	try {
	boolean res=myaccount.isDisplayed();
	return res;	
	}
	catch(Exception e) {
		return false;
	}
	
}

public void myaccountlogin() {
	myaccountlogin.click();
}

public void logout() {
	logout.click();
}


}
