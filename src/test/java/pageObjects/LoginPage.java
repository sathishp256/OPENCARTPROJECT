package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends basePage{
	
public LoginPage(WebDriver driver){
	super(driver);
}

@FindBy(xpath="//input[@id='input-email']")
WebElement username;

@FindBy(xpath="//input[@id='input-password']")
WebElement password;

@FindBy(xpath="//input[@value='Login']")
WebElement clicklogin;


public void setUsername(String uname) {
	username.sendKeys(uname);
}

public void setPassword(String pwd) {
	password.sendKeys(pwd);
}

public void clickLogin() {
	clicklogin.click();
	
}
	


}
