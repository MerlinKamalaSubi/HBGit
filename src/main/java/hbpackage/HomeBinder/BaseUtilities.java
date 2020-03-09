package hbpackage.HomeBinder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseUtilities {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	// Start the server
	public AppiumDriverLocalService startAppiumServer()
	{
		boolean flag= checkIfServerIsRunnning(4723);
		System.out.println(flag);
		if (!flag)
		{
			service= AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void startEmulator() throws IOException, InterruptedException
	{


		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startServer.bat");
		//Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException, InterruptedException

	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\hbpackage\\HomeBinder\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir,(String) prop.get("HomeBinderApp"));

		DesiredCapabilities caps = new DesiredCapabilities();

		String deviceName = (String) prop.get("device");

		// Through mvn command
		// String deviceName = System.getProperty("device");

		if(deviceName.contains("emulator"))
		{
			startEmulator();
		}
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);

		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");

		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());


		String packageName = (String) prop.get("AppPackage");
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,packageName);

		String activityName = (String) prop.get("AppActivity");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,activityName);

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		return driver;

	}

	public static void getScreenshot(String testName) throws IOException
	{
		File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File (System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+testName+".png"));
	}

}
