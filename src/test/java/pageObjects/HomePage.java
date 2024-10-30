package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class HomePage extends BasePage{
	
	
	public HomePage (WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyAccount;
	

	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegistration;
	
	@FindBy(xpath ="//a[normalize-space()='Login']")
	WebElement linkLogin;
	
	
	public void ClickMyAccount() {
		linkMyAccount.click();
	}

	public void ClickRegistration() {
		linkRegistration.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}

} 
