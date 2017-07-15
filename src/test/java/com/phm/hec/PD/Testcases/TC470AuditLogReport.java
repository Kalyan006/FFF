package com.phm.hec.PD.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phm.hec.PD.Objects.AuditLogReportPageObjects;
import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.PD.Objects.RolePageObjects;
import com.phm.hec.PEC.Objects.PecFormResourceAndEducationObjects;
import com.phm.hec.pageObjects.HeaderPageObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.testcases.Login;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReadExcelFile;
import com.phm.hec.utility.ReportGenerator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC470AuditLogReport {
		public static Logger addlog = Logger.getLogger(TC470AuditLogReport.class.getName());
		static ExtentTest TC_470_AuditLogReport;
		public static String ScreenShotPath;
		public static String dateStr ="" ;
		public static String ModuleActionLogin="";
		public static String  ModuleActionhome="";
		public static String ModuleActionFAQ ="";
		public static String ModuleActionFAT="";
		public static String ModuleActionEMREdu="";
		public static String ModuleActionWeninar="";
		public static String ModuleActionDecisionAids="";
		public static String ModuleActionLogout="";
		
		
		public static String filepath =GlobalVar.TestDataAuditReportTC470FilePath;
	
		@BeforeMethod
		public static void AuditReportPreCondition() throws Throwable {
			TC_470_AuditLogReport = GlobalVar.report.startTest("TC_470_AuditLogReport",
				"Validate the user able to Audit the Log Reports ");
			Logger logger = addlog;
			RolePageObjects RPO = PageFactory.initElements(GlobalVar.Driver, RolePageObjects.class);
			IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
			ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
			HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);

			if (GlobalVar.loggedin == false) {

				GlobalVar.Driver.get(GlobalVar.URL);
				ReadExcelFile readExcelFile = new ReadExcelFile();
				try {
					GlobalVar.Modules = "PD";
					readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
							GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
					TC_470_AuditLogReport.log(LogStatus.INFO,
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

			try {
			
				// Click on Implementation Menu
				Thread.sleep(GlobalVar.threadSleep);
				PAP.ClickLnkImplementation();
				logger.info("Implementation Menu Clicking...");

				// click on Click on Role SubMenu
				logger.info("Click Role SubMenu Under Implementation Menu");
				Thread.sleep(GlobalVar.threadSleep);
				PAP.ClickLnkRole();

				// Enter The Role Name
				RPO.EnterRoleName("Client Admin");

				// click on Search role button
				RPO.ClickSearchRole();

				// click on setup icon for the role
				logger.info("Click on RoleGrid List for Document Admin Feature");
				RPO.ClickGridRoleList();

				// select associate product name
				RPO.SelectAssociateProductNames("Provider Admin");
				
				//Click AuditLog Reports Sub Menu Under Reports Menu and Enable the feature
				TC_470_AuditLogReport.log(LogStatus.INFO, "Click AuditLog Reports Sub Menu Under Reports Menu and Enable the feature");
				String AHG = "";
			
				if(RPO.ClkReportChkbox.isSelected()){
					if(GlobalVar.Client==AHG){
				if(RPO.clkAuditChkboxP11.isSelected()){
			
					logger.info("The Audit Repots Feature already enabled.");
					TC_470_AuditLogReport.log(LogStatus.INFO, "The Audit Repots Feature already enabled.");
				}
				else{
			
					logger.info("Enabling the Audit Reports Features.");
					TC_470_AuditLogReport.log(LogStatus.INFO, "Enabling the Audit Reports Features.");
					Thread.sleep(GlobalVar.threadSleep);
					RPO.ClickAuditReportsChkBoxP11();
				}
				}else{
					if(RPO.ClkAuditChkboxP12.isSelected()){
					logger.info("The Audit Repots Feature already enabled.");
					TC_470_AuditLogReport.log(LogStatus.INFO, "The Audit Repots Feature already enabled.");
					
				}
					else{
						logger.info("Enabling the Audit Reports Features");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Enabling the Audit Reports Features");
						Thread.sleep(GlobalVar.threadSleep);
						RPO.ClickAuditReportsChkBoxP12();
					}
				}
				}
				else{	
					logger.info("Enabling the Audit Reports Features");
					TC_470_AuditLogReport.log(LogStatus.INFO, "Enabling the Audit Reports Features");
					RPO.ClickReportsCheckBox();

				}
				
				// Click on Save Button..
				RPO.ClickbtnSave();
				
				//Click on Logout Button
				Thread.sleep(GlobalVar.threadSleep);
				logger.info("Logout from the PD Application");
				TC_470_AuditLogReport.log(LogStatus.INFO, "Logout From the PD Application ");
				HPO.PDLogout();
			} 
			catch(NoSuchElementException nse){
			logger.info(nse.getMessage());
			}
			catch (Exception e) {
				logger.info("AuditReportPreCondition Exception :" + e.getMessage().toString());

			}
		}
	
		// Start the test case for Audit Log Reports..
		@SuppressWarnings("deprecation")
		@Test

		public static void AuditLogReports() throws Throwable {
		
			Logger logger = addlog;
			PecFormResourceAndEducationObjects PFRE = PageFactory.initElements(GlobalVar.Driver,
					PecFormResourceAndEducationObjects.class);
			ReadExcelFile readExcelFile = new ReadExcelFile();
			IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
			HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
			ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
			AuditLogReportPageObjects ALRP=PageFactory.initElements(GlobalVar.Driver, AuditLogReportPageObjects.class);
			try{
				logger.info("AuditLog Reports  test execution starts.");
				TC_470_AuditLogReport.log(LogStatus.INFO, "AuditLog Reports test execution starts.");
				//Take Date And Time
				 Date date = new Date();
				  SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
           
						// Take The Data From Excel Sheet Based On ExecuteYesOrNo
						String filepath = GlobalVar.TestDataAuditReportTC470FilePath;
						logger.info("File Path : " + filepath);
						TC_470_AuditLogReport.log(LogStatus.INFO, "File Path : " + filepath);
						int rows;
						File file = new File(filepath);
						FileInputStream fis = new FileInputStream(file);
						XSSFWorkbook workbook = new XSSFWorkbook(fis);
						XSSFSheet sheet = workbook.getSheet("TC-470ModuleActionInPEC");
						rows = sheet.getLastRowNum() + 1;
						int cols = sheet.getRow(1).getLastCellNum();
						logger.info("Total Rows :" + rows + " Total Columns : " + cols);
						
				for (int rownum = 1; rownum < rows; rownum++) {
					sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
					String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
					String ExecuteYN = executeyn.toUpperCase().trim();
					Boolean Execute = ExecuteYN.equals("YES");
					dateStr = "";
					logger.info("Execute Action Y/N : " + ExecuteYN + "  Execute the Row " + rownum + " Execute Action = "
							+ Execute);
					if(Execute ==true && rownum==1) {
						// Logged into the Provider Role in the Application
						Thread.sleep(GlobalVar.threadSleep);
						GlobalVar.Modules = "PEC";
						readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
								GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
						Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword);

						TC_470_AuditLogReport.log(LogStatus.INFO,
								"Login to PEC - Login name :" + GlobalVar.LoginUsername + " Password :" + GlobalVar.LoginPassword);
						logger.info("Logged in to the Provider Application");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Login" );
						//Pick the Click Time
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);

						// Pick the Module Action From Excel
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionLogin = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionLogin + "  & Log Date And Time : " + dateStr);

						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action In PEC :" + ModuleActionLogin + 
								"   Log Date And Time : " + dateStr);

						// click on Provider eConnect home tab..
						Thread.sleep(GlobalVar.threadSleep);
						IntMedPage.ClickOnProvidereConnect();
										
					}else if(Execute==true && rownum==2) {
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						 ModuleActionhome = sheet.getRow(rownum).getCell(0).getStringCellValue();
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Home" );
						 
						//Pick The Click Time
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);
						logger.info("Module Action in PEC: " + ModuleActionhome );
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action In PEC :" + ModuleActionhome + 
								"  & Log Date And Time : " + dateStr);
								

					}else if(Execute==true && rownum==3 ) {

						// click on form Resource And Education Sub Menu..
						logger.info("Click on Form Resourse and Education Sub Menu..");
						Thread.sleep(GlobalVar.threadSleep);
						PFRE.ClickFormsResourceAndEducation();

					}else if(Execute==true && rownum==4 ) {

						//Click on Frequently Asked Question
						logger.info("Click on Frequently Asked Question");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Click on Frequently Asked Question");
						PFRE.ClickFrequentlyAskedQuestion();
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionFAQ = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionFAQ + "  & Log Date And Time : " + dateStr);
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action in PEC :" + ModuleActionFAQ + 
								"   Log Date And Time : " + dateStr);
					}else if(Execute==true && rownum==5 ) {

						//Click on Forms And Template
						logger.info("Click on Forms And Template");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Click on Forms And Template");
						PFRE.ClickFormsAndTemplates();
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionFAT = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionFAT + "  & Log Date And Time : " + dateStr);
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action in PEC :" + ModuleActionFAT + 
								"   Log Date And Time : " + dateStr);

					}else if(Execute==true && rownum==6 ) {

						//Click on EMr Education
						logger.info("Click on EMR Education");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Click on EMR Education");
						PFRE.ClickEMREduction();
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);   
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionEMREdu = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionEMREdu + "  & Log Date And Time : " + dateStr );
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action in PEC :" + ModuleActionEMREdu + 
								"   Log Date And Time : " + dateStr);

					}else if(Execute==true && rownum==7 ) {

						//Click on Webinars
						logger.info("Click on Webinars");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Click on Webinars");
						PFRE.ClickWebinars();
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionWeninar = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionWeninar + "  & Log Date And Time : " + dateStr );
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action in PEC :" + ModuleActionWeninar + 
								"   Log Date And Time : " + dateStr);

					}else if(Execute==true && rownum==8 ) {

						//Click on Decision Aids
						logger.info("Click On Decision Aids");
						TC_470_AuditLogReport.log(LogStatus.INFO, "Perform Action : Click On Decision Aids");
						PFRE.ClickDecisionAids();
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionDecisionAids = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionDecisionAids + "  & Log Date And Time : " + dateStr );
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action in PEC :" + ModuleActionDecisionAids +
								"   Log Date And Time : " + dateStr);

					}else if(Execute==true && rownum==9 ) {

						// Logout from the PEC Application
						logger.info("Logout from the PEC application.");
						TC_470_AuditLogReport.log(LogStatus.PASS, "Perform Action : Logout from the PEC application.");
						Thread.sleep(GlobalVar.threadSleep);
						HPO.FREPageLogout();
						sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
						dateStr = sdf2.format(date);
						// Pick the Module Action
						sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						ModuleActionLogout = sheet.getRow(rownum).getCell(0).getStringCellValue();
						logger.info("Module Action in PEC : " + ModuleActionLogout + " & Log Date And Time : " + dateStr );
						TC_470_AuditLogReport.log(LogStatus.INFO, "Module Action in PEC :" + ModuleActionLogout + 
								"   Log Date And Time : " + dateStr);


					}	
					//Thread.sleep(30000);
				}	
							
				//Logged into the PD Application
				Thread.sleep(GlobalVar.threadSleep);
				GlobalVar.Modules = "PD";
				readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
						GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
				Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword);
				TC_470_AuditLogReport.log(LogStatus.INFO,
						"Login to PD - Login name :" + GlobalVar.LoginUsername + " Password :" + GlobalVar.LoginPassword);
				logger.info("Logged in to the PD Application");
							
				//Click on Provider Home Tab
				Thread.sleep(GlobalVar.threadSleep);
				logger.info("Clicking on Provider Admin Home Tab");
				IntMedPage.ClickProviderAdmin();
							
				//click on Reports Menu
				logger.info("Clicking on Reports menu");
				Thread.sleep(GlobalVar.threadSleep);
				PAP.ClickLnkReports();
							
				//Click on Audit Reports Sub Menu
				logger.info("Click on Audit Log  Sub Menu under Reports Menu.");
				TC_470_AuditLogReport.log(LogStatus.INFO, "Click on Audit Log  Sub Menu under Reports Menu.");
				Thread.sleep(GlobalVar.threadSleep);
				PAP.ClickLnkAuditReports();
							
				//Search the Product from Drop Down
				logger.info("Select the Product Provider eConnect from Drop Down.");
				TC_470_AuditLogReport.log(LogStatus.INFO, "Select the Product Provider eConnect from Drop Down.");
				ALRP.SelectDdlProductCategory("Provider eConnect");
				
				Thread.sleep(GlobalVar.threadSleep);
							
				//Click on Search Button
				Thread.sleep(GlobalVar.threadSleep);
				ALRP.ClickBtnSearch();
							
				//Click To Arrange in Order
				Thread.sleep(GlobalVar.threadSleep);
				ALRP.ClickGridAuditLogList();
				Thread.sleep(GlobalVar.threadSleep);
				//Take the Module Action From excel based On ExecuteYesOrNO
				for (int rownum = 1; rownum < rows; rownum++) {
					sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
					String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
					String ExecuteYN = executeyn.toUpperCase().trim();
					Boolean Execute = ExecuteYN.equals("YES");
					if(Execute ==true && rownum==1) {
						//Check for Module Action Login And Log Date & Time
						String txtLogin = ALRP.ModuleActionLoginTest();
						String txtloginlog = ALRP.GetLoginLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionLogin +  " && Log Date And Time : " + dateStr + 
								" ||  ModuleAction in AuditLog Reports  :  " + txtLogin + " && Log Date And Time : " + txtloginlog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionLogin + 
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
								+ txtLogin + " && Log Date And Time : " + txtloginlog);
						if(ModuleActionLogin.equals(txtLogin) && dateStr.equals(txtloginlog) ) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==2) {
						//Check for Module Action Home And Log Date & Time
						String txthome = ALRP.ModuleActionHomeTest();
						String txthomelog = ALRP.GetHomeLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionhome + " && Log Date And Time : " + dateStr + 
								" ||  ModuleAction in AuditLog Reports  :  " + txthome + " && Log Date And Time : " + txthomelog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionhome +  
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
								+ txthome + " && Log Date And Time : " + txthomelog);
						if(ModuleActionhome.equals(txthome) && dateStr.equals(txthomelog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==3 ) {
						//Check for Module Action		
					}else if(Execute==true && rownum==4 ) {
						//Check for Module Action FAQ And Log Date & Time
						String txtFaq = ALRP.ModuleActionFAQTest();
						String txtfaqlog = ALRP.GetFAQLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionFAQ +  " && Log Date And Time : " + dateStr +
								"  ||  ModuleAction in AuditLog Reports  :  " + txtFaq + " && Log Date And Time : " + txtfaqlog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionFAQ + 
								"  & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
								+ txtFaq + " && Log Date And Time : " + txtfaqlog); 
								
						if(ModuleActionFAQ.equals(txtFaq) && dateStr.equals(txtfaqlog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==5 ) {
						//Check for Module Action FAT And Log Date & Time
						String txtFat = ALRP.ModuleActionFATTest();
						String txtfatlog = ALRP.GetFATLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionFAT +  " && Log Date And Time : " + dateStr + 
								"  ||  ModuleAction in AuditLog Reports  :  " + txtFat + " && Log Date And Time : " + txtfatlog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionFAT +  
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  " 
								+ txtFat + " && Log Date And Time : " + txtfatlog);
						if(ModuleActionFAT.equals(txtFat) && dateStr.equals(txtfatlog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==6 ) {
						//Check for Module Action EMR EDu And Log Date & Time
						String txtEmrEdu = ALRP.ModuleActionEMREduTest();
						String txtEmrEdulog = ALRP.GetEMREduLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionEMREdu + " && Log Date And Time : " + dateStr +
								"  ||  ModuleAction in AuditLog Reports  :  " 	+ txtEmrEdu + " && Log Date And Time : " + txtEmrEdulog);
							
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionEMREdu +  
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  " 
								+ txtEmrEdu + " && Log Date And Time : " + txtEmrEdulog);

						if(ModuleActionEMREdu.equals(txtEmrEdu) && dateStr.equals(txtEmrEdulog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==7 ) {
						//Check for Module Action Webinars And Log Date & Time
						String txtWebinar = ALRP.ModuleActionWebinarTest();
						String txtWebinarlog = ALRP.GetWebinarLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionWeninar + " && Log Date And Time : " + dateStr + 
								"  ||  ModuleAction in AuditLog Reports  :  " + txtWebinar + " && Log Date And Time : " + txtWebinarlog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionWeninar +  
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  " 
								+ txtWebinar + " && Log Date And Time : " + txtWebinarlog);
						if(ModuleActionWeninar.equals(txtWebinar) && dateStr.equals(txtWebinarlog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==8 ) {
						//Check for Module Action Decision Aids And Log Date & Time
						String txtDecisionAids = ALRP.ModuleActionDecisionAidsTest();
						String txtDecisionAidlog = ALRP.GetDecisionAidLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionDecisionAids + " && Log Date And Time : " + dateStr + 
								"  ||  ModuleAction in AuditLog Reports  :  " + txtDecisionAids + " && Log Date And Time : " + txtDecisionAidlog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionDecisionAids + 
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
								+ txtDecisionAids + " && Log Date And Time : " +txtDecisionAidlog);

						if(ModuleActionDecisionAids.equals(txtDecisionAids) && dateStr.equals(txtDecisionAidlog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}else if(Execute==true && rownum==9 ) {
						//Check for Module Action Logout And Log Date & Time
						String txtLogout = ALRP.ModuleActionLogoutTest();
						String txtlogoutlog = ALRP.GetLogOutLogDate();
						logger.info("ModuleAction in PEC : " + ModuleActionLogout + " && Log Date And Time : " + dateStr + 
								"  ||  ModuleAction in AuditLog Reports  :  " + txtLogout + " && Log Date And Time : " + txtlogoutlog);
								
						TC_470_AuditLogReport.log(LogStatus.INFO, "ModuleAction in PEC : " + ModuleActionLogout +  
								" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
								+ txtLogout + " && Log Date And Time : " + txtlogoutlog );

						if(ModuleActionLogout.equals(txtLogout) && dateStr.equals(txtlogoutlog)) {
							logger.info("TRUE");
							TC_470_AuditLogReport.log(LogStatus.PASS, "True");
						}
						else {
							logger.info("FALSE");
							TC_470_AuditLogReport.log(LogStatus.FAIL, "False");
						}

					}		
				}	
				//close the WorkBook
						
				workbook.close();
				
				//Click on Logout Button
				/*Thread.sleep(GlobalVar.threadSleep);
				logger.info("Logout from the PD Application");
				TC_470_AuditLogReport.log(LogStatus.INFO, "Logout From the PD Application ");
				HPO.PDLogout();*/
				GlobalVar.report.endTest(TC_470_AuditLogReport);
						
			}catch (Throwable t) {

				logger.info("Audit Log Reports Exception :" + t.getMessage().toString());
				// ScreenShotPath = GenericUtils.CaptureScreenshot(GlobalVar.Driver,
				// "TC_470_AuditLogReport" + LocalDateTime.now().getHour() +
				//LocalDateTime.now().getMinute() +
				// LocalDateTime.now().getSecond());
				ScreenShotPath = ReportGenerator.setLog("AuditLog Reports Failed", t.getMessage().toString(),
						"AuditLog Reports_TC470_Fail");
				String image = TC_470_AuditLogReport.addScreenCapture(ScreenShotPath);
				TC_470_AuditLogReport.log(LogStatus.FAIL, image);
				GlobalVar.report.endTest(TC_470_AuditLogReport);

			}
	
		}
	
	}
