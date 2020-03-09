package hbpackage.HomeBinder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
// import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.LoginForm;

class LoginTest extends BaseUtilities {

	@Test(dataProvider = "InputData",dataProviderClass = TestData.class)

	public void login(String username, String password) throws IOException, InterruptedException {

		service = startAppiumServer();

		AndroidDriver<AndroidElement> driver = Capabilities("HomeBinderApp");
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		LoginForm loginformpage = new LoginForm(driver);
		loginformpage.emailField.sendKeys(username);

		loginformpage.getpasswordField().sendKeys(password);

		driver.hideKeyboard();

		loginformpage.loginbtnField.click();

		System.out.println("Login Successful");
		
		Thread.sleep(10000);

		// service.stop();

	}

	
	  @BeforeTest public void killAllNodes() throws IOException,
	  InterruptedException
	  
	  { 
	  
	  Runtime.getRuntime().exec("taskkill /F /IM node.exe"); Thread.sleep(3000); }
	 
}
