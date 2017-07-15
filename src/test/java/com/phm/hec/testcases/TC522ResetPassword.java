/******************************************************************************
 * Test case Name 	: 	TC_522 Validate the Reset Password Functionality
 * Author			:	Mahesh Anton
 * Date of Creation	:	06/21/2017
 * Change History	:	
 ******************************************************************************/
package com.phm.hec.testcases;

import org.testng.annotations.Test;

import com.phm.hec.utility.GlobalVar;
import com.relevantcodes.extentreports.ExtentTest;

import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TC522ResetPassword {
	
	static ExtentTest TC_522_Reset_Password;
	
	public static String ScreenShotPath;
	public static String testdata;
	
	public static String TestName = "";
	public static boolean Execute;
  @Test
  public void ResetPassword() {
	Logger logger = Logger.getLogger(TC522ResetPassword.class.getName());
	TestName = "TC_522_Reset_Password";
	TC_522_Reset_Password = GlobalVar.report.startTest(TestName, "Validate the user able to reset the Password through Provider Admin");
	
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
