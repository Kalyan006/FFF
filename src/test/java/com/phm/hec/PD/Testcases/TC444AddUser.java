package com.phm.hec.PD.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.phm.hec.PD.Objects.AddProviderPageObjects;
import com.phm.hec.PD.Objects.AddUserAssignAccountPageObject;
import com.phm.hec.PD.Objects.AddUserAssignProviderPageObject;
import com.phm.hec.PD.Objects.AddUserAssignRegionPageObject;
import com.phm.hec.PD.Objects.AddUserAssignRolePageObject;
import com.phm.hec.PD.Objects.AddUserPageObject;
import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.PD.Objects.UserPageObjects;
import com.phm.hec.pageObjects.HeaderPageObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.testcases.Login;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReadExcelFile;

public class TC444AddUser {

	public static Logger addlog = Logger.getLogger(TC444AddUser.class.getName());

	@SuppressWarnings("deprecation")
	@Test
	public static void AddUser() throws Throwable {
		IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
		ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
		AddUserPageObject APO = PageFactory.initElements(GlobalVar.Driver, AddUserPageObject.class);
		UserPageObjects upo = PageFactory.initElements(GlobalVar.Driver, UserPageObjects.class);
		AddProviderPageObjects appo = PageFactory.initElements(GlobalVar.Driver, AddProviderPageObjects.class);
		AddUserAssignAccountPageObject aapo = PageFactory.initElements(GlobalVar.Driver,AddUserAssignAccountPageObject.class);
		AddUserAssignProviderPageObject uapo = PageFactory.initElements(GlobalVar.Driver,AddUserAssignProviderPageObject.class);
		AddUserAssignRolePageObject uarpo = PageFactory.initElements(GlobalVar.Driver,AddUserAssignRolePageObject.class);
		AddUserAssignRegionPageObject auarpo = PageFactory.initElements(GlobalVar.Driver,AddUserAssignRegionPageObject.class);
		HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
		Logger logger = addlog;
		Thread.sleep(GlobalVar.threadSleep);

		IntMedPage.ClickProviderAdmin();
		PAP.ClickLnkUser();
		PAP.ClickLnkUser();
		String filepath = GlobalVar.TestDataAddUserTC444FilePath;
		File AddUsersrc = new File(filepath);
		FileInputStream fis = new FileInputStream(AddUsersrc);
		if (GlobalVar.loggedin == false) {

			GlobalVar.Driver.get(GlobalVar.URL);
			ReadExcelFile readExcelFile = new ReadExcelFile();
			try {
				GlobalVar.Modules = "PD";
				readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
						GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
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
			//IntMedPage.ClickProviderAdmin();
			logger.info("Click on user submenu");
			PAP.ClickLnkUser();
			logger.info("Click on add user button");
			upo.ClickAddUser();
			String filepath1 = GlobalVar.TestDataAddUserTC444FilePath;
			logger.info("Test data File Path :"+filepath1);
			File AddUsersrc1 = new File(filepath1);
			FileInputStream fis1 = new FileInputStream(AddUsersrc1);

			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis1);
			XSSFSheet sheet = workbook.getSheet("PD_AddUser");
			int rows;
			int cols = sheet.getRow(1).getLastCellNum();
			if (!"BAT".equals(GlobalVar.TestType)) {
				rows = sheet.getLastRowNum() + 1;
			} else {
				rows = 2;
			}

			for (int rownum = 1; rownum < rows; rownum++) {

				sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
				String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
				String ExecuteYN = executeyn.toUpperCase().trim();
				Boolean Execute = ExecuteYN.equals("YES");
				if (Execute == true || "BAT".equals(GlobalVar.TestType)) {
					sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					String LastName = sheet.getRow(rownum).getCell(0).getStringCellValue();
					APO.EnterLastName(LastName);
					logger.info("User last name :"+LastName);
					sheet.getRow(rownum).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					String FirstName = sheet.getRow(rownum).getCell(1).getStringCellValue();
					APO.EnterFirstName(FirstName);
					logger.info("User first name :"+FirstName);
					sheet.getRow(rownum).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					String EmailID = sheet.getRow(rownum).getCell(2).getStringCellValue();
					APO.EnterEmailAddress(EmailID);
					logger.info("User email id :"+EmailID);
					sheet.getRow(rownum).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					String PhoneNumber = sheet.getRow(rownum).getCell(3).getStringCellValue();
					APO.EnterPhoneNum(PhoneNumber);
					logger.info("User phone number :"+PhoneNumber);
					sheet.getRow(rownum).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					String UserName = sheet.getRow(rownum).getCell(4).getStringCellValue();
					APO.EnterUserName(UserName);
					logger.info("User name :"+UserName);
					sheet.getRow(rownum).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
					String Password = sheet.getRow(rownum).getCell(5).getStringCellValue();
					APO.EnterPassword(Password);
					logger.info("Password :"+Password);
					sheet.getRow(rownum).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
					String Address1 = sheet.getRow(rownum).getCell(6).getStringCellValue();
					APO.EnterAddress1(Address1);
					logger.info("User Address1 :"+Address1);
					sheet.getRow(rownum).getCell(7).setCellType(Cell.CELL_TYPE_STRING);
					String Address2 = sheet.getRow(rownum).getCell(7).getStringCellValue();
					APO.EnterAddress2(Address2);
					logger.info("User Address2 :"+Address2);
					sheet.getRow(rownum).getCell(8).setCellType(Cell.CELL_TYPE_STRING);
					String CityName = sheet.getRow(rownum).getCell(8).getStringCellValue();
					APO.EnterCityName(CityName);
					logger.info("City name :"+CityName);

					/*	sheet.getRow(rownum).getCell(9).setCellType(Cell.CELL_TYPE_STRING); String
				StateName=sheet.getRow(rownum).getCell(9).getStringCellValue();
				APO.SelectState(StateName);*/

					sheet.getRow(rownum).getCell(10).setCellType(Cell.CELL_TYPE_STRING);
					String ZipCode = sheet.getRow(rownum).getCell(10).getStringCellValue();
					APO.EnterZipCode(ZipCode);
					logger.info("Zip code :"+ZipCode);
					sheet.getRow(rownum).getCell(11).setCellType(Cell.CELL_TYPE_STRING);
					String TitalName = sheet.getRow(rownum).getCell(11).getStringCellValue();
					APO.EnterTitle(TitalName);
					logger.info("TitalName :"+TitalName);
					sheet.getRow(rownum).getCell(12).setCellType(Cell.CELL_TYPE_STRING);
					String PhoneNumber1 = sheet.getRow(rownum).getCell(12).getStringCellValue();
					APO.EnterPhoneNumber1(PhoneNumber1);
					logger.info("PhoneNumber1 :"+PhoneNumber1);
					sheet.getRow(rownum).getCell(13).setCellType(Cell.CELL_TYPE_STRING);
					String PhoneNumber2 = sheet.getRow(rownum).getCell(13).getStringCellValue();
					APO.EnterPhoneNumber2(PhoneNumber2);
					logger.info("PhoneNumber2 :"+PhoneNumber2);
					sheet.getRow(rownum).getCell(14).setCellType(Cell.CELL_TYPE_STRING);
					String FaxNumber = sheet.getRow(rownum).getCell(14).getStringCellValue();
					APO.EnterFaxNumber(FaxNumber);
					logger.info("FaxNumber :"+FaxNumber);
					sheet.getRow(rownum).getCell(15).setCellType(Cell.CELL_TYPE_STRING);
					String Responsibility = sheet.getRow(rownum).getCell(15).getStringCellValue();
					APO.EnterResponsibility(Responsibility);
					logger.info("Responsibility :"+Responsibility);
					APO.ClickAssignRegion();
					appo.switchToWindowByIndex(1);
					sheet.getRow(rownum).getCell(16).setCellType(Cell.CELL_TYPE_STRING);
					String RegionCode = sheet.getRow(rownum).getCell(16).getStringCellValue();
					auarpo.EnterRegionCode(RegionCode);
					logger.info("RegionCode :"+RegionCode);
					logger.info("Click on search region");
					auarpo.ClickSearchRegion();
					logger.info("Select region check box");
					auarpo.SelectChkBox();
					logger.info("Click on assign region button");
					auarpo.ClickAssignRegion();
					Thread.sleep(GlobalVar.threadSleep);
					appo.switchToWindowByIndex(0);
					logger.info("Click on assign account button");
					APO.ClickAssignAccount();
					appo.switchToWindowByIndex(1);
					sheet.getRow(rownum).getCell(17).setCellType(Cell.CELL_TYPE_STRING);
					String taxid = sheet.getRow(rownum).getCell(17).getStringCellValue();
					aapo.EnterTaxID(taxid);
					logger.info("Tax id :"+taxid);
					logger.info("Click on serach account");
					aapo.ClickSearchAccount();
					logger.info("Click on account checkbox");
					aapo.ClickCheckBox();
					logger.info("Click on assign account button");
					aapo.ClickAssignAccount();
					Thread.sleep(GlobalVar.threadSleep);
					appo.switchToWindowByIndex(0);
					logger.info("Click on assign provider button");
					APO.ClickAssinProvidert();
					appo.switchToWindowByIndex(1);
					sheet.getRow(rownum).getCell(18).setCellType(Cell.CELL_TYPE_STRING);
					String NPI = sheet.getRow(rownum).getCell(18).getStringCellValue();
					uapo.EnterNPI(NPI);
					logger.info("Provider NPI :"+NPI);
					logger.info("Click on serach provider button");
					uapo.ClickSearchProvider();
					logger.info("Click on provider checkbox");
					uapo.SelectCheckBox();
					uapo.ClickAssignProvider();
					Thread.sleep(GlobalVar.threadSleep);
					appo.switchToWindowByIndex(0);
					APO.ClickAssinRole();
					appo.switchToWindowByIndex(1);
					sheet.getRow(rownum).getCell(19).setCellType(Cell.CELL_TYPE_STRING);
					String RoleName = sheet.getRow(rownum).getCell(19).getStringCellValue();
					uarpo.EnterRoleName(RoleName);
					uarpo.ClickSearchRole();
					uarpo.SelectRoleHandIcon();
					Thread.sleep(GlobalVar.threadSleep);
					appo.switchToWindowByIndex(0);
					logger.info("Select welcome email checkbox ");
					APO.SelectWlcmChkBox();
					logger.info("Select reset password checkbox");
					APO.SelectResetPwdChkBox();
					logger.info("Select disable manage alert radio button");
					APO.SelectDisableButton();


				}
			}
		}
	 }
	}