package com.phm.hec.PD.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phm.hec.CM.Objects.CareManagerPageObjects;
import com.phm.hec.PD.Objects.AuditLogReportPageObjects;
import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.PD.Objects.RolePageObjects;
import com.phm.hec.pageObjects.HeaderPageObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.testcases.Login;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReadExcelFile;
import com.phm.hec.utility.ReportGenerator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC517CMAuditLogReport {
	public static Logger addlog = Logger.getLogger(TC517CMAuditLogReport.class.getName());
	static ExtentTest TC_517_CMAuditLogReport;
	public static String AHG = "";
	public static String dateStr ="" ;
	public static String ScreenShotPath;
	public static String ModuleActionLogin="";
	public static String  ModuleActionintervention="";
	public static String ModuleActionpatientsearch ="";
	public static String ModuleActionContactOut="";
	public static String ModuleActionAssignPatient="";
	public static String ModuleActionAdmissionDischarge="";
	public static String ModuleActionLogout="";
	//public static String TestName = "";
	public static String filepath =GlobalVar.TestDataAuditReportTC470FilePath;
	
	@BeforeMethod
	public static void CMAuditReportPreCondition() throws Throwable {
		TC_517_CMAuditLogReport = GlobalVar.report.startTest("TC_517_CMAuditLogReport",
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
				TC_517_CMAuditLogReport.log(LogStatus.INFO,
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
			TC_517_CMAuditLogReport.log(LogStatus.INFO, "Click AuditLog Reports Sub Menu Under Reports Menu and Enable the feature");
			
				if(RPO.ClkReportChkbox.isSelected()){
					if(GlobalVar.Client==AHG){
				if(RPO.clkAuditChkboxP11.isSelected()){
			
					logger.info("The Audit Repots Feature already enabled.");
					TC_517_CMAuditLogReport.log(LogStatus.INFO, "The Audit Repots Feature already enabled.");
				}
				else{
			
					logger.info("Enabling the Audit Reports Features.");
					TC_517_CMAuditLogReport.log(LogStatus.INFO, "Enabling the Audit Reports Features.");
					Thread.sleep(GlobalVar.threadSleep);
					RPO.ClickAuditReportsChkBoxP11();
				}
				}else{
					if(RPO.ClkAuditChkboxP12.isSelected()){
					logger.info("The Audit Repots Feature already enabled.");
					TC_517_CMAuditLogReport.log(LogStatus.INFO, "The Audit Repots Feature already enabled.");
					
				}
					else{
						logger.info("Enabling the Audit Reports Features");
						TC_517_CMAuditLogReport.log(LogStatus.INFO, "Enabling the Audit Reports Features");
						Thread.sleep(GlobalVar.threadSleep);
						RPO.ClickAuditReportsChkBoxP12();
					}
			}
			}
			else{	
				logger.info("Enabling the Audit Reports Features");
				TC_517_CMAuditLogReport.log(LogStatus.INFO, "Enabling the Audit Reports Features");
				RPO.ClickReportsCheckBox();

			}
				
				// Click on Save Button..
				RPO.ClickbtnSave();
				
				//Click on Logout Button
				Thread.sleep(GlobalVar.threadSleep);
				logger.info("Logout from the PD Application");
				TC_517_CMAuditLogReport.log(LogStatus.INFO, "Logout From the PD Application ");
				HPO.PDLogout();
		} 
		catch(NoSuchElementException nse){
			logger.info(nse.getMessage());
		}
		catch (Exception e) {
		
		logger.info("CMAuditReportPreCondition Exception :" + e.getMessage().toString());

	}
		}
	

@SuppressWarnings("deprecation")
@Test

public static void CMAuditLogReports() throws Throwable {	
		Logger logger = addlog;
		
		ReadExcelFile readExcelFile = new ReadExcelFile();
		IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
		HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
		ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
		AuditLogReportPageObjects ALRP=PageFactory.initElements(GlobalVar.Driver, AuditLogReportPageObjects.class);
		CareManagerPageObjects CMP =PageFactory.initElements(GlobalVar.Driver, CareManagerPageObjects.class);
		try {
			logger.info("AuditLog Reports  test execution starts.");
			TC_517_CMAuditLogReport.log(LogStatus.INFO, "AuditLog Reports test execution starts.");	
						//Take Date And Time
						Date date = new Date();
						SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
								// Take The Data From Excel Sheet Based On ExecuteYesOrNo
						String filepath = GlobalVar.TestDataAuditReportTC470FilePath;
						logger.info("File Path : " + filepath);
						TC_517_CMAuditLogReport.log(LogStatus.INFO, "File Path : " + filepath);
						int rows;
						File file = new File(filepath);
						FileInputStream fis = new FileInputStream(file);
						XSSFWorkbook workbook = new XSSFWorkbook(fis);
						XSSFSheet sheet = workbook.getSheet("TC-517ModuleActionInCM");
						rows = sheet.getLastRowNum() + 1;
						int cols = sheet.getRow(1).getLastCellNum();
						logger.info("Total Rows :" + rows + " Total Columns : " + cols);
						// Take The Data From Excel Sheet Based On ExecuteYesOrNo
						for (int rownum = 1; rownum < rows; rownum++) {
							sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
							String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
							String ExecuteYN = executeyn.toUpperCase().trim();
							Boolean Execute = ExecuteYN.equals("YES");
							logger.info("Execute Action Y/N : " + ExecuteYN + "  Execute the Row " + rownum + " Execute Action = "
									+ Execute);
									if(Execute ==true && rownum==1) {
										// Logged into the Care Manager Role in the Application
										Thread.sleep(GlobalVar.threadSleep);
										GlobalVar.Modules = "CM";
										readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
												GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
										Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword);
										TC_517_CMAuditLogReport.log(LogStatus.INFO,
												"Login to CM - Login name :" + GlobalVar.LoginUsername + " Password :" + GlobalVar.LoginPassword);
										logger.info("Logged in to the Care Manager Application");
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Logged in to the Care Manager Application" );
										//Pick the Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										// Pick the Module Action From Excel Sheet
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionLogin = sheet.getRow(rownum).getCell(0).getStringCellValue();
										logger.info("Module Action in CM : " + ModuleActionLogin + "	& Log Date And Time : " + dateStr);
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionLogin + 
												"	& Log Date And Time : " + dateStr);
										
										// Click on Care Manager Home Tab
										Thread.sleep(GlobalVar.threadSleep);
										IntMedPage.ClickOnCareManagerLink();
										
										
									}else if(Execute==true && rownum==2) {
										// Pick the Module Action
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionintervention = sheet.getRow(rownum).getCell(0).getStringCellValue();
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Intervention Scheduler" );
										 
										//Pick The Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										logger.info("Module Action in CM: " + ModuleActionintervention + "	& Log Date And Time : " + dateStr );
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionintervention + 
												"	& Log Date And Time : " + dateStr);
												
									}else if(Execute==true && rownum==3 ) {
										
										// Click on Patient Search Under Care Management Menu 
										Thread.sleep(GlobalVar.threadSleep);
										logger.info("Click on Patient Search Under Care Management Menu.");
										CMP.ClickLnkPatientSearch();
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Click On Patient Search Under Care Management Menu" );
										//Pick the Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										// Pick the Module Action From Excel Sheet
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionpatientsearch = sheet.getRow(rownum).getCell(0).getStringCellValue();
										logger.info("Module Action in CM : " + ModuleActionpatientsearch + "	& Log Date And Time : " + dateStr);
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionpatientsearch + 
												"	& Log Date And Time : " + dateStr);
									
									}else if(Execute==true && rownum==4 ) {
										
										//Click on Contact/Outreach
										Thread.sleep(GlobalVar.threadSleep);
										CMP.ClickLnkContactOutReach();
										logger.info("Click on Contact/Outreach");
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Click on Contact/Outreach" );
										//Pick the Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										// Pick the Module Action From Excel Sheet
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionContactOut = sheet.getRow(rownum).getCell(0).getStringCellValue();
										logger.info("Module Action in CM : " + ModuleActionContactOut + "	& Log Date And Time : " + dateStr);
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionContactOut + 
												"	& Log Date And Time : " + dateStr);
										
									}else if(Execute==true && rownum==5 ) {
										
										//Click on Assign Patient
										Thread.sleep(GlobalVar.threadSleep);
										CMP.ClickLnkAssignPatient();
										logger.info("Click on Assign Patient");
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Click on Assign Patient" );
										//Pick the Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										// Pick the Module Action From Excel Sheet
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionAssignPatient = sheet.getRow(rownum).getCell(0).getStringCellValue();
										logger.info("Module Action in CM : " + ModuleActionAssignPatient + "	& Log Date And Time : " + dateStr);
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionAssignPatient + 
												"	& Log Date And Time : " + dateStr);
																																								
										}else if(Execute==true && rownum==6 ) {
										
										//Click on Admission Discharge
										Thread.sleep(GlobalVar.threadSleep);
										CMP.ClickLnkAdmissionDischarge();
										logger.info("Click on Admission Discharge");
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Click on Admission Discharge" );
										//Pick the Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										// Pick the Module Action From Excel Sheet
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionAdmissionDischarge = sheet.getRow(rownum).getCell(0).getStringCellValue();
										logger.info("Module Action in CM : " + ModuleActionAdmissionDischarge + "	& Log Date And Time : " + dateStr);
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionAdmissionDischarge + 
												"	& Log Date And Time : " + dateStr);
									
										}else if(Execute==true && rownum==7 ) {
										
										// Logout from the PEC Application
										logger.info("Logout from the CM application.");
										Thread.sleep(GlobalVar.threadSleep);
										HPO.FREPageLogout();
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Perform Action : Logout from the CM application" );
										//Pick the Click Time
										sdf2.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));
										dateStr = sdf2.format(date);
										// Pick the Module Action From Excel Sheet
										sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
										ModuleActionLogout = sheet.getRow(rownum).getCell(0).getStringCellValue();
										logger.info("Module Action in CM : " + ModuleActionLogout + "	& Log Date And Time : " + dateStr);
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "Module Action In CM :" + ModuleActionLogout + 
												"	& Log Date And Time : " + dateStr);
									}		
	
						}		
						
						//Logged into the PD Application
						Thread.sleep(GlobalVar.threadSleep);
						GlobalVar.Modules = "PD";
						readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
								GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
						Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword);
						TC_517_CMAuditLogReport.log(LogStatus.INFO,
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
						TC_517_CMAuditLogReport.log(LogStatus.INFO, "Click on Audit Log  Sub Menu under Reports Menu.");
						Thread.sleep(GlobalVar.threadSleep);
						PAP.ClickLnkAuditReports();
						
						//Search the Product from Drop Down
						logger.info("Select the Product Care Manager from Drop Down.");
						TC_517_CMAuditLogReport.log(LogStatus.INFO, "Select the Product Care Manager from Drop Down.");
						Thread.sleep(GlobalVar.threadSleep);
						ALRP.SelectDdlProductCategory("Care Manager");
						
						//Select the Time Period
						Thread.sleep(GlobalVar.threadSleep);
						ALRP.SelectDdlTimePeriod("1 Month");
						
						//Click on Search Button
						Thread.sleep(GlobalVar.threadSleep);
						ALRP.ClickBtnSearch();
						
						//Click To Arrange in Order
						Thread.sleep(GlobalVar.threadSleep);
						ALRP.ClickGridAuditLogList();
						Thread.sleep(GlobalVar.threadSleep);
					
						
						// Take The Module Action From Excel Sheet Based On ExecuteYesOrNo
						for (int rownum = 1; rownum < rows; rownum++) {
							sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
							String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
							String ExecuteYN = executeyn.toUpperCase().trim();
							Boolean Execute = ExecuteYN.equals("YES");
							logger.info("Execute Action Y/N : " + ExecuteYN + "  Execute the Row " + rownum + " Execute Action = "
									+ Execute);
									if(Execute ==true && rownum==1) {
										//Check for Module Action Login and Log Date and Time
										String txtLogin = ALRP.CMModuleActionLoginTest();
										String txtloginlog = ALRP.GetCMLoginLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionLogin +  " && Log Date And Time : " + dateStr + 
												" ||  ModuleAction in AuditLog Reports  :  " + txtLogin + " && Log Date And Time : " + txtloginlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionLogin + 
												" && Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtLogin + " && Log Date And Time : " + txtloginlog);
										if(ModuleActionLogin.equals(txtLogin) && dateStr.equals(txtloginlog) ) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}
										
										
									}else if(Execute==true && rownum==2) {
										//Check for Module Action Intervention and Log Date and Time
										String txtintervention = ALRP.ModuleActionInterventionTest();
										String txtinterventionlog = ALRP.GetInterventionLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionintervention + " && Log Date And Time : " + dateStr + 
												" ||  ModuleAction in AuditLog Reports  :  " + txtintervention + " && Log Date And Time : " + txtinterventionlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionintervention +  
												" && Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtintervention + " && Log Date And Time : " + txtinterventionlog);
										if(ModuleActionintervention.equals(txtintervention) && dateStr.equals(txtinterventionlog)) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}
										
												
									}else if(Execute==true && rownum==3 ) {
										
										//Check for Module Action Patient search and Log Date and Time
										String txtpatientsch = ALRP.ModuleActionPatientSchTest();
										String txtpatientschlog = ALRP.GetInterventionLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionpatientsearch + " && Log Date And Time : " + dateStr + 
												" ||  ModuleAction in AuditLog Reports  :  " + txtpatientsch + " && Log Date And Time : " + txtpatientschlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionpatientsearch +  
												"  && Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtpatientsch + " && Log Date And Time : " + txtpatientschlog);
										if(ModuleActionpatientsearch.equals(txtpatientsch) && dateStr.equals(txtpatientschlog)) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}
												
									}else if(Execute==true && rownum==4 ) {
										
										//Check for Module Action Contact/OutSearch and Log Date and Time
										String txtcontact = ALRP.ModuleActionContactOutTest();
										String txtcontactlog = ALRP.GetContactoutLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionContactOut + "  && Log Date And Time : " + dateStr + 
												" ||  ModuleAction in AuditLog Reports  :  " + txtcontact + " && Log Date And Time : " + txtcontactlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionContactOut +  
												" && Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtcontact + " && Log Date And Time : " + txtcontactlog);
										if(ModuleActionContactOut.equals(txtcontact) && dateStr.equals(txtcontactlog)) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}
										
									}else if(Execute==true && rownum==5 ) {
										
										//Check for Module Action Assign Patient and Log Date and Time
										String txtassign = ALRP.ModuleActionAssignPatientTest();
										String txtassignlog = ALRP.GetAssignPatientLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionAssignPatient + " && Log Date And Time : " + dateStr + 
												" ||  ModuleAction in AuditLog Reports  :  " + txtassign + " && Log Date And Time : " + txtassignlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionAssignPatient +  
												" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtassign + " && Log Date And Time : " + txtassignlog);
										if(ModuleActionAssignPatient.equals(txtassign) && dateStr.equals(txtassignlog)) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}
										
									}else if(Execute==true && rownum==6 ) {
										
										//Check for Module Action Admission Discharge and Log Date and Time
										String txtadmissiondch = ALRP.ModuleActionAdmissionDchTest();
										String txtadmissiondchlog = ALRP.GetAdmissionDchLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionAdmissionDischarge + " && Log Date And Time : " + dateStr + 
												" ||  ModuleAction in AuditLog Reports  :  " + txtadmissiondch + " && Log Date And Time : " + txtadmissiondchlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionAdmissionDischarge +  
												" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtadmissiondch + " && Log Date And Time : " + txtadmissiondchlog);
										if(ModuleActionAdmissionDischarge.equals(txtadmissiondch) && dateStr.equals(txtadmissiondchlog)) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}
											
									}else if(Execute==true && rownum==7 ) {
										
										//Check for Module Action Logout And Log Date & Time
										String txtLogout = ALRP.CMModuleActionLogoutTest();
										String txtlogoutlog = ALRP.GetCMLogOutLogDate();
										logger.info("ModuleAction in CM : " + ModuleActionLogout + " && Log Date And Time : " + dateStr + 
												"  ||  ModuleAction in AuditLog Reports  :  " + txtLogout + " && Log Date And Time : " + txtlogoutlog);
												
										TC_517_CMAuditLogReport.log(LogStatus.INFO, "ModuleAction in CM : " + ModuleActionLogout +  
												" & Log Date And Time : " + dateStr + "  ||  ModuleAction in AuditLog Reports  :  "
												+ txtLogout + " && Log Date And Time : " + txtlogoutlog );

										if(ModuleActionLogout.equals(txtLogout) && dateStr.equals(txtlogoutlog)) {
											logger.info("TRUE");
											TC_517_CMAuditLogReport.log(LogStatus.PASS, "True");
										}
										else {
											logger.info("FALSE");
											TC_517_CMAuditLogReport.log(LogStatus.FAIL, "False");
										}		
									}		
	
								}
						//Close the Workbook
						workbook.close();
						//Click on Logout Button
						//Thread.sleep(GlobalVar.threadSleep);
						//logger.info("Logout from the PD Application");
						//TC_470_AuditLogReport.log(LogStatus.INFO, "Logout From the PD Application ");
						//HPO.PDLogout();
						GlobalVar.report.endTest(TC_517_CMAuditLogReport);
						
			}catch(Throwable t) {
				logger.info("Audit Log Reports Exception :" + t.getMessage().toString());
				// ScreenShotPath = GenericUtils.CaptureScreenshot(GlobalVar.Driver,
				// "TC_517_CMAuditLogReport" + LocalDateTime.now().getHour() +
				//LocalDateTime.now().getMinute() +
				// LocalDateTime.now().getSecond());
				ScreenShotPath = ReportGenerator.setLog("AuditLog Reports Failed", t.getMessage().toString(),
						"AuditLog Reports_TC517_Fail");
				String image = TC_517_CMAuditLogReport.addScreenCapture(ScreenShotPath);
				TC_517_CMAuditLogReport.log(LogStatus.FAIL, image);
				GlobalVar.report.endTest(TC_517_CMAuditLogReport);
			}
		}
}












