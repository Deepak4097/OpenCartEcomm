package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups={"sanity","Master"})
	public void verify_login() {
		
		logger.info("******************************* Test 002_loginTest Started ******************************");
		try {
		//Home Page
		HomePage hp =new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("Clicked on My Account Icon from HomePage");
		hp.clickLogin();
		
		logger.info("Clicked on Login link from MyAccount Dropdown");
		
		//Login Page
		
		LoginPage lp =new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		logger.info("Entered Email and Password for login");
		lp.clickonLoginBtn();
		logger.info("Clicked Login on Button");

		//My Account Page
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******************************* Test 002_loginTest Finished ******************************");
		
		}


}
