package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups={"DataDriven","Master"})
	public void verifyLoginPage_DDT(String email, String pwd, String expRes) {

		logger.info(
				"******************************** TC003_LoginDDT started ************************************************************");
		try {
		// Home Page
		HomePage hp = new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("Clicked on My Account Icon from HomePage");
		hp.clickLogin();
		logger.info("Clicked on Login link from MyAccount Dropdown");

		// Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		logger.info("Entered Email and Password for login");
		lp.clickonLoginBtn();
		logger.info("Clicked Login on Button");

		// My Account Page
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExists();
		//Assert.assertTrue(targetPage);

		/*
		 * Data is valid = login successfully ---> Test case Pass ---> logout 
		 * 					login failed ---> Test case Fail
		 * 
		 * Data is invalid = login successfully ----> Test Case fail --> logout 
		 * 					 login failed -----> Test case pass
		 */
		if (expRes.equalsIgnoreCase("valid")) {

			if (targetPage == true) {
				map.clickLogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

		}

		if (expRes.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				map.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}

		logger.info(
				"******************************** TC003_LoginDDT Finished ************************************************************");
	}catch(Exception e){
		Assert.fail();
	}
	}

}
