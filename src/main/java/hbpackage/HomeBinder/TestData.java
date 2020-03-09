package hbpackage.HomeBinder;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name="InputData")
	public Object[][]  getDataforLogin() 
	{
		Object[][] data =new Object [1][2];
				{
			//0th Row
			data[0][0] = "merlin.subi+phaseq@capestart.com";
			data[0][1] = "password";
			
			return data;
				}

	}
}