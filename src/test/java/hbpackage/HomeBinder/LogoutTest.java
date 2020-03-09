package hbpackage.HomeBinder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import pageObjects.SettingsForm;

class LogoutTest extends BaseUtilities {


	@Test

	public void logout () throws IOException, InterruptedException {

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);


		SettingsForm settingsformpage = new SettingsForm(driver);
		settingsformpage.settingsIcon.click();

		settingsformpage.logoutSelection.click();

		settingsformpage.logoutOption.click();

		System.out.println("Logged Out Successfully");

		// service.stop();

	}

}
