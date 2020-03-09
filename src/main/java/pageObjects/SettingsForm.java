package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsForm {
	
	public SettingsForm(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SETTINGS']")
	public WebElement settingsIcon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Logout']")
	public WebElement logoutSelection;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@text='Logout']")
	public WebElement logoutOption;
	
}
