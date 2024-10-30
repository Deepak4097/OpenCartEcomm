package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath ="//input[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath ="//input[@id='input-password']")
	WebElement txtpassword;

	@FindBy(xpath ="//input[@value='Login']")
	WebElement btnlogin;

	public void setEmail(String email) {
		txtEmailAddress.clear();
		txtEmailAddress.sendKeys(email);
	}
	
	public void setPassword(String loginpwd) {
		txtpassword.clear();
		txtpassword.sendKeys(loginpwd);
	}
	
	public void clickonLoginBtn() {
		btnlogin.click();
	}


}
