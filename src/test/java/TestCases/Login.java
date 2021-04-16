package TestCases;

import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Utilities.DataUtil;

public class Login extends TestBase{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider="dp")
	public void testLogin(String usrname, String password)
	{
		typeEle("email_id",usrname);
		typeEle("pass_id",password);
		click("loginbtn_xpath");
	}
	
	/*@Test(priority=3)
	public void getTilte()
	{
		System.out.println(driver.getTitle());
	}*/
/*	@Test(priority=1)
	public void signUP()
	{
		click("createbutton_xpath");
		Set<String> winds = driver.getWindowHandles();
		
		System.out.println(winds.size());
		
		typeEle("fName_xpath");
		typeEle("lName_xpath");
		
	}*/
}
