package com.phm.hec.PD.Testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.phm.hec.PD.Objects.EditFacilityPageObjects;
import com.phm.hec.PD.Objects.FacilityPageObjects;
import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.pageObjects.HeaderPageObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.testcases.Login;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReadExcelFile;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC200ValidateEditFacility {
	public static Logger addlog = Logger.getLogger(TC200ValidateEditFacility.class.getName());
	static ExtentTest TC_200_ValidateEditFacility;
	
	@Test
	public static void ValidateEditFacility () throws Throwable {
		TC_200_ValidateEditFacility = GlobalVar.report.startTest("TC_200_ValidateEditFacility",
				"User able to Edit And Validate the Facility");
		Logger logger = addlog;
		IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
		ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
		HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
		FacilityPageObjects FPO = PageFactory.initElements(GlobalVar.Driver, FacilityPageObjects.class);
		EditFacilityPageObjects EFPO =PageFactory.initElements(GlobalVar.Driver, EditFacilityPageObjects.class);
		if (GlobalVar.loggedin == false) {

			GlobalVar.Driver.get(GlobalVar.URL);
			ReadExcelFile readExcelFile = new ReadExcelFile();
			try {
				GlobalVar.Modules = "PD";
				readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
						GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
				TC_200_ValidateEditFacility.log(LogStatus.INFO,
						"Login to PD - Login name :" + GlobalVar.LoginUsername + " Password :" + GlobalVar.LoginPassword);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword) == true) {
				logger.info("Login Success : Username :" + GlobalVar.LoginUsername + " Password : "
						+ GlobalVar.LoginPassword);

				Thread.sleep(GlobalVar.threadSleep);
				IntMedPage.ClickProviderAdmin();
				GlobalVar.loggedin = true;

			} else {
				GlobalVar.loggedin = false;
				logger.info("Login Failed : Username :" + GlobalVar.LoginUsername + " Password : "
						+ GlobalVar.LoginPassword);
			}
		} else {

			try {
				Boolean txtExists = GlobalVar.Driver.getPageSource().contains("Release Notes");
				if (txtExists == false) {
					logger.info("Clicking on PHM Icon");
					HPO.ClickPHM();
					Thread.sleep(GlobalVar.threadSleep);
					logger.info("Clicking on Provider Admin Icon");
					IntMedPage.ClickProviderAdmin();

				} else {
					Thread.sleep(GlobalVar.threadSleep);
					logger.info("Clicking on Provider Admin Home Tab");
					IntMedPage.ClickProviderAdmin();

				}
			} catch (Exception e) {
				logger.info("Exception Message :" + e.getMessage());
			}
		}
		
		//Click On Facility Sub Menu Under Organization Menu'
		Thread.sleep(GlobalVar.threadSleep);
		PAP.ClickLnkFacility();
		
		//Enter the TaxId of Any Facility
		Thread.sleep(GlobalVar.threadSleep);
		String Taxid = FPO.EnterFacilityTaxID("117524907");
		TC_200_ValidateEditFacility.log(LogStatus.INFO, "Enter Facility TaxID : " + Taxid);
		
		//Click on Search Facility Button
		Thread.sleep(GlobalVar.threadSleep);
		FPO.ClickOnSearchFacility();
		
		//Click on Edit Facility TAb
		Thread.sleep(GlobalVar.threadSleep);
		FPO.ClickEditFacility();
		
		//Enter Facility Additional Information-Address1
		EFPO.EnterFacilityAddress1("948");
		
		//Enter Facility Additional Information-Address2
		EFPO.EnterFacilityAddress2("hsr layout");
		
		//Enter Facility Additional Information-City
		EFPO.EnterFacilityCity("");
		
		
		//Enter Facility Additional Information-State
		EFPO.SelectddlFacilityState("");
		
		
		//Enter Facility Additional Information-Zip
		EFPO.EnterFacilityZip("");
		
		
		//Enter Facility Additional Information-Country
		EFPO.SelectddlFacilityCountry("");
		
		//Enter Facility Additional Information-Phone
		EFPO.EnterFacilityPhone("");
		
		//Enter Facility Additional Information-Fax
		EFPO.EnterFacilityFax("");
		
		//Enter Facility Additional Information-NPI
		EFPO.EnterFacilityGroupNPI("");
		
		//Enter Facility Additional Information-Term Date
		EFPO.ClickCalenderimgTermDate();
		//Select the Term Date from Calender
		Thread.sleep(GlobalVar.threadSleep);
		EFPO.ClickOnCalenderDate();
		
		//Enter Facility Additional Information-Status
		EFPO.SelectddlFacilityStatus("");
		
		
		
		
		
		
		
		
		GlobalVar.report.endTest(TC_200_ValidateEditFacility);
		
	
	}

}
