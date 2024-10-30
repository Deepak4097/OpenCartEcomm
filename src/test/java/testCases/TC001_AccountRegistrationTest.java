package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {

		logger.info("*****************TC001_AccountRegistrationTest Started***********************");
		try {
			HomePage hp = new HomePage(driver);
			hp.ClickMyAccount();
			hp.ClickRegistration();
		logger.info("clicked on Registration page!!!");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
		logger.info("Providing Customer Detail using random class");	
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");
			regpage.setTelephone(randomeNumber());

			String Password = randomAlphaNumeric();

			regpage.setPassword(Password);
			regpage.setConfirmPassword(Password);

			regpage.setCheckPolicy();
			regpage.setBtnContinue();
			
			logger.info("Validating register Message");
			String confirmMSg = regpage.getConfirmationMessage();
			
			if(confirmMSg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			
			else {
				logger.error("Test Failed...!!!!");
				logger.debug("Debug losgs"); // for debug level 
				Assert.assertTrue(false);
			}
		//	Assert.assertEquals(confirmMSg, "Your Account Has Been Created!");
		} catch (Exception e) {
			
			Assert.fail();
			}
		logger.info("*****************TC001_AccountRegistrationTest Finished***********************");
	}

}
