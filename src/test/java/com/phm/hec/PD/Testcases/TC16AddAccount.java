package com.phm.hec.PD.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.phm.hec.PD.Objects.AccountPageObjects;
import com.phm.hec.PD.Objects.AddAccountAssignFacilityPageObjects;
import com.phm.hec.PD.Objects.AddAccountAssignProviderPageObjects;
import com.phm.hec.PD.Objects.AddAccountAssignRegionPageObjects;
import com.phm.hec.PD.Objects.AddAccountAssignUserPageObjects;
import com.phm.hec.PD.Objects.AddAccountPageObjects;
import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.pageObjects.HeaderPageObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.testcases.Login;
import com.phm.hec.utility.GenericUtils;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReadExcelFile;
import com.phm.hec.utility.ReportGenerator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC16AddAccount {

	static ExtentTest TC_16_Add_Account;
	public static String ScreenShotPath;
	public static String testdata;
	public static String RegionName = "";
	public static String FacilityName = "";
	public static String ProviderLastName = "";
	public static String ProviderFirstName = "";
	public static String UserLastName = "";
	public static String UserFirstName = "";
	public static String AccountName="";
	public static String AddAccAccountType="";
	public static String AddAccAccountCategory="";
	public static Boolean Execute = false;
	public static String TestName = "";
	public static String ARRegionCode = "";
	public static String AFTaxId = "";
	public static String APNPI = "";
	public static String AUUserName = "";
	public static Logger addlog = Logger.getLogger(TC16AddAccount.class.getName());
	static boolean confirm;

	@Test
	public static void AddAccount() throws Throwable {


		try {
			Logger logger = Logger.getLogger(TC16AddAccount.class.getName());
			TC_16_Add_Account = GlobalVar.report.startTest("TC_16_Add_Account");


			// Intermediate page object
			IntermediatePageObjects ipo = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
			// Provider admin page object
			ProviderAdminPageObjects papo = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
			// Header page object
			HeaderPageObjects hpo = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
			// Account page object
			AccountPageObjects apo = PageFactory.initElements(GlobalVar.Driver, AccountPageObjects.class);
			// Add account page object
			AddAccountPageObjects aapo = PageFactory.initElements(GlobalVar.Driver, AddAccountPageObjects.class);
			// Assign region page object
			AddAccountAssignRegionPageObjects aaarpo = PageFactory.initElements(GlobalVar.Driver,
					AddAccountAssignRegionPageObjects.class);
			// Assign faciity page object
			AddAccountAssignFacilityPageObjects aaafpo = PageFactory.initElements(GlobalVar.Driver,
					AddAccountAssignFacilityPageObjects.class);
			// Assign provider page object
			AddAccountAssignProviderPageObjects aaappo = PageFactory.initElements(GlobalVar.Driver,
					AddAccountAssignProviderPageObjects.class);
			// Assign user page object
			AddAccountAssignUserPageObjects aaaupo = PageFactory.initElements(GlobalVar.Driver,
					AddAccountAssignUserPageObjects.class);
			boolean multitestdata = Boolean.parseBoolean(GlobalVar.multpletestdata);

			if (GlobalVar.loggedin == false) {

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
					ipo.ClickProviderAdmin();
					GlobalVar.loggedin = true;
				} else {
					GlobalVar.loggedin = false;
					logger.info("Login Failed : Username :" + GlobalVar.LoginUsername + " Password : "
							+ GlobalVar.LoginPassword);
				}
			} else {
				// Click on the PHM Icon in the Upper Right corner of the page
				try {
					Boolean txtExists = GlobalVar.Driver.getPageSource().contains("Release Notes");
					if (txtExists == false) {
						logger.info("Click on PHM Icon");
						hpo.ClickPHM();
						Thread.sleep(GlobalVar.threadSleep);
						logger.info("Click on Provider Admin");
						ipo.ClickProviderAdmin();
						//logger.info("Clicking on Provider Admin");

					} else {
						Thread.sleep(GlobalVar.threadSleep);
						logger.info("Click on Provider Admin Home Tab...");
						ipo.ClickProviderAdmin();
					}
				} catch (Exception e) {
					logger.info("Exception Message :" + e.getMessage());
				}
			}

			Thread.sleep(GlobalVar.threadSleep);

			// Click on organization link
			papo.ClickLnkOrganization();

			// Click on account link
			papo.ClickLnkAccount();

			TC_16_Add_Account.log(LogStatus.INFO, "TC 16 Add Account - Test execution starts");
			
			String filepath = GlobalVar.TestDataAddAccountTC16FilePath;

			logger.info("File Path for Add Account : " + filepath);

			// Data driven starts here

			File AddAccount = new File(filepath);
			FileInputStream fis = new FileInputStream(AddAccount);
			int rows;
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			String AddAccSheetName = GlobalVar.TDSheetName.toString().trim();
			AddAccSheetName = AddAccSheetName.toUpperCase().trim();
			logger.info("Sheet Name : " + AddAccSheetName);

			XSSFSheet sheet = workbook.getSheet(AddAccSheetName);
		
			if (!"BAT".equals(GlobalVar.TestType)) {
				rows = sheet.getLastRowNum() + 1;
			} else {
				rows = 2;
			}

			int cols = sheet.getRow(1).getLastCellNum();
			logger.info("Test Type : " + GlobalVar.TestType + " || Total Rows :" + rows + " || Total Columns : " + cols);

			
			for (int rownum = 1; rownum < rows; rownum++) {
				TestName = "TC_16_Add_Account" + "_" + "rows: " + rownum;
				String Executeyn = GenericUtils.cellValue(sheet, rownum, cols - 1);
				String ExecuteYN = Executeyn.toUpperCase().trim();
				Boolean Execute = ExecuteYN.equals("YES");
				logger.info(Execute);
				/*logger.info("Test Data Execute Y/N : " + ExecuteYN + " Execute the Row " + rownum + " Execute = "
						+ Execute + "Test Type : " + GlobalVar.TestType);*/

				if (Execute == true || "BAT".equals(GlobalVar.TestType)) {
					try {

						//TC_16_Add_Account.log(LogStatus.INFO, "TC 16 Add Account - Test execution starts");

						// Click on add account button
						apo.ClickBtnAddAccount();
						logger.info("Click on Add Account");


						// Add account page Account Information,enter account name
						try{
							AccountName = "UATAddAccount" + LocalDateTime.now().getHour()
									+ LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond();
							int AccountNameLength=AccountName.length();
							if(AccountNameLength>0){
								aapo.EnterAddAccAccountName(AccountName);
								logger.info("Account Information, Account Name : " + AccountName);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Add account page Account Information,select
						// account type
						try{
							AddAccAccountType = GenericUtils.cellValue(sheet, rownum, 1).trim();
							int AddAccAccountTypeLength=AddAccAccountType.length();
							if(AddAccAccountTypeLength>0){
								aapo.SctAddAccAccountType(AddAccAccountType);
								logger.info("Account Information, Account Type : " + AddAccAccountType);
							}
						}catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Account Information,select
						// account category
						try{
							AddAccAccountCategory = GenericUtils.cellValue(sheet, rownum, 2).trim();
							int AddAccAccountCategoryLength=AddAccAccountCategory.length();
							if(AddAccAccountCategoryLength>0){
								aapo.SctAddAccAccountCategory(AddAccAccountCategory);
								logger.info("Account Information, Account Category : " + AddAccAccountCategory);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Add account page Identifier Information,enter SSN
						try{
							String AddAccSsn = GenericUtils.cellValue(sheet, rownum, 3).trim();
							int AddAccSsnLength=AddAccSsn.length();
							if(AddAccSsnLength>0){
								aapo.EnterAddAccSsn(AddAccSsn);
								logger.info("Identifier Information, SSN : " + AddAccSsn);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Identifier Information,enter NPI
						try{
							String Npi = GenericUtils.cellValue(sheet, rownum, 4).trim();
							int NpiLength=Npi.length();
							if(NpiLength>0){
								aapo.EnterAddAccNpi(Npi);
								logger.info("Identifier Information, NPI : " + Npi);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Identifier Information,enter tax
						// id
						try{
							String Taxid = GenericUtils.cellValue(sheet, rownum, 5).trim();
							int TaxidLength=Taxid.length();
							if(TaxidLength>0){
								aapo.EnterAddAccTaxId(Taxid);
								logger.info("Identifier Information, TaxID : " + Taxid);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Identifier Information,select
						// state
						try{
							String State = GenericUtils.cellValue(sheet, rownum, 6).trim();
							int StateLength=State.length();
							if(StateLength>0){
								aapo.SctAddAccDdlState(State);
								logger.info("Identifier Information, State : " + State);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Identifier Information,state
						// assigned id
						try{
							String StateAssignedId = GenericUtils.cellValue(sheet, rownum, 7).trim();
							int StateAssignedIdLength=StateAssignedId.length();
							if(StateAssignedIdLength>0){
								aapo.EnterAddAccStateAssignedId(StateAssignedId);
								logger.info("Identifier Information, StateAssignedID : " + StateAssignedId);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Identifier Information,enter
						// upin
						try{
							String Upin = GenericUtils.cellValue(sheet, rownum, 8).trim();
							int UpinLength=Upin.length();
							if(UpinLength>0){
								aapo.EnterAddAccUpin(Upin);
								logger.info("Identifier Information, UPIN : " + Upin);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Taxonomy & Description,select
						// taxonomy code set
						try{
							String TaxonomyCodeSet = GenericUtils.cellValue(sheet, rownum, 9).trim();
							int TaxonomyCodeSetLength=TaxonomyCodeSet.length();
							if(TaxonomyCodeSetLength>0){
								aapo.SctAddAccTaxonomyCodeSet(TaxonomyCodeSet);
								logger.info("Taxonomy & Description, Taxonomy Code Set : " + TaxonomyCodeSet);
							}
						}
						catch(Exception e){
							e.printStackTrace();

						}

						// Add account page Taxonomy & Description,select
						// taxonomy code
						try{
							String TaxonomyCode = GenericUtils.cellValue(sheet, rownum, 10).trim();
							int TaxonomyCodeLength=TaxonomyCode.length();
							if(TaxonomyCodeLength>0){
								Thread.sleep(GlobalVar.threadSleep);
								aapo.SctAddAccDdlTaxonomyCode(TaxonomyCode);
								logger.info("Taxonomy & Description, Taxonomy Code : " + TaxonomyCode);
							}
						}
						catch(Exception e){
							e.printStackTrace();

						}

						// Add account page Address & Contact
						// Information,enter last name
						try{
							String LastName = GenericUtils.cellValue(sheet, rownum, 11);
							int LastNameLength=LastName.length();
							if(LastNameLength>0){
								aapo.EnterAddAccLastName(LastName);
								logger.info("Address & Contact Information, Last Name : " + LastName);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter First Name
						try{
							String FirstName = GenericUtils.cellValue(sheet, rownum, 12);
							int FirstNameLength=FirstName.length();
							if(FirstNameLength>0){
								aapo.EnterAddAccFirstName(FirstName);
								logger.info("Address & Contact Information, First Name : " + FirstName);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter email
						try{
							String Email = GenericUtils.cellValue(sheet, rownum, 13);
							int EmailLength=Email.length();
							if(EmailLength>0){
								aapo.EnterAddAccEmail(Email);
								logger.info("Address & Contact Information, Email : " + Email);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter Address 1
						try{
							String Address1 = GenericUtils.cellValue(sheet, rownum, 14);
							int Address1Length=Address1.length();
							if(Address1Length>0){
								aapo.EnterAddAccTxtAddress1(Address1);
								logger.info("Address & Contact Information, Address1 : " + Address1);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter Address 2
						try{
							String Address2 = GenericUtils.cellValue(sheet, rownum, 15);
							int Address2Length=Address2.length();
							if(Address2Length>0){
								aapo.EnterAddAccTxtAddress2(Address2);
								logger.info("Address & Contact Information, Address2 : " + Address2);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						// Add account page Address & Contact
						// Information,enter city
						try{
							String City = GenericUtils.cellValue(sheet, rownum, 16);
							int CityLength=City.length();
							if(CityLength>0){
								aapo.EnterAddAccCity(City);
								logger.info("Address & Contact Information, City : " + City);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,select state
						try{
							String ACIState = GenericUtils.cellValue(sheet, rownum, 17);
							int ACIStateLength=ACIState.length();
							if(ACIStateLength>0){
								aapo.SctAddAccDdlAcState(ACIState);
								logger.info("Address & Contact Information, State : " + ACIState);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter zip
						try{
							String Zip = GenericUtils.cellValue(sheet, rownum, 18);
							int ZipLength=Zip.length();
							if(ZipLength>0){
								aapo.EnterAddAccZip(Zip);
								logger.info("Address & Contact Information, Zip : " + Zip);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter fax
						try{
							String Fax = GenericUtils.cellValue(sheet, rownum, 19);
							int FaxLength=Fax.length();
							if(FaxLength>0){
								aapo.EnterAddAccFax(Fax);
								logger.info("Address & Contact Information, Fax : " + Fax);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter phone 1
						try{
							String Phone1 = GenericUtils.cellValue(sheet, rownum, 20);
							int Phone1Length=Phone1.length();
							if(Phone1Length>0){
								aapo.EnterAddAccTelephone1(Phone1);
								logger.info("Address & Contact Information, Phone 1 : " + Phone1);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter Extension1
						try{
							String Ext1 = GenericUtils.cellValue(sheet, rownum, 21);
							int Ext1Length=Ext1.length();
							if(Ext1Length>0){
								aapo.EnterAddAccExtension1(Ext1);
								logger.info("Address & Contact Information, Ext 1 : " + Ext1);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter phone 2
						try{
							String Phone2 = GenericUtils.cellValue(sheet, rownum, 22);
							int Phone2Length=Phone2.length();
							if(Phone2Length>0){
								aapo.EnterAddAccTelephone2(Phone2);
								logger.info("Address & Contact Information, Phone 2 : " + Phone2);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Address & Contact
						// Information,enter Extension2
						try{
							String Ext2 = GenericUtils.cellValue(sheet, rownum, 23);
							int Ext2Length=Ext2.length();
							if(Ext2Length>0){
								aapo.EnterAddAccExtension2(Ext2);
								logger.info("Address & Contact Information, Ext 2 : " + Ext2);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						Thread.sleep(GlobalVar.threadSleep);
						// Add account page Additional Information,select
						// batch claims format
						aapo.ClickAddAccBatchClaims();


						// Add account page, select ANSI/X12 format
						aapo.ClickRdoAnsi();


						// Add account page select Propertiary format
						// aapo.ClickRdoPropertiary();

						// Add account page Credit / Debit Payment
						// Gateway,enter merchant id
						try{
							String MerchantID = GenericUtils.cellValue(sheet, rownum, 24);
							int MerchantIDLength=MerchantID.length();
							if(MerchantIDLength>0){
								aapo.EnterAddAccMerchantId(MerchantID);
								logger.info("Credit / Debit Payment Gateway, Merchant ID : " + MerchantID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Credit / Debit Payment
						// Gateway,enter Operator / User ID
						try{
							String OperatorUserID = GenericUtils.cellValue(sheet, rownum, 25);
							int OperatorUserIDLength=OperatorUserID.length();
							if(OperatorUserIDLength>0){
								aapo.EnterAddAccOperatorOrUserId(OperatorUserID);
								logger.info("Credit / Debit Payment Gateway, Operator / User ID : " + OperatorUserID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Credit / Debit Payment
						// Gateway,enter Password
						try{
							String Password = GenericUtils.cellValue(sheet, rownum, 26);
							int PasswordLength=Password.length();
							if(PasswordLength>0){
								aapo.EnterAddAccPassword(Password);
								logger.info("Credit / Debit Payment Gateway, Password : " + Password);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Credit / Debit Payment
						// Gateway,enter Device ID
						try{
							String DeviceID = GenericUtils.cellValue(sheet, rownum, 27);
							int DeviceIDLength=DeviceID.length();
							if(DeviceIDLength>0){
								aapo.EnterAddAccDeviceId(DeviceID);
								logger.info("Credit / Debit Payment Gateway Device ID : " + DeviceID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						// Add account page Credit / Debit Payment
						// Gateway,enter Registration Key
						try{
							String RegistrationKey = GenericUtils.cellValue(sheet, rownum, 28);
							int RegistrationKeyLength=RegistrationKey.length();
							if(RegistrationKeyLength>0){
								aapo.EnterAddAccRegisKey(RegistrationKey);
								logger.info("Credit / Debit Payment Gateway Registration Key : " + RegistrationKey);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Check Payment Gateway,enter Site
						// ID
						try{
							String SiteID = GenericUtils.cellValue(sheet, rownum, 29);
							int SiteIDLength=SiteID.length();
							if(SiteIDLength>0){
								aapo.EnterAddAccSiteID(SiteID);
								logger.info("Check Payment Gateway Site ID  : " + SiteID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Check Payment Gateway,enter
						// Location ID
						try{
							String LocationID = GenericUtils.cellValue(sheet, rownum, 30);
							int LocationIDLength=LocationID.length();
							if(LocationIDLength>0){
								aapo.EnterAddAccLocationID(LocationID);
								logger.info("Check Payment Gateway Location ID  : " + LocationID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Check Payment Gateway,enter
						// POSProgram ID
						try{
							String POSProgramID = GenericUtils.cellValue(sheet, rownum, 31);
							int POSProgramIDLength=POSProgramID.length();
							if(POSProgramIDLength>0){
								aapo.EnterAddAccPOSProgramID(POSProgramID);
								logger.info("Check Payment Gateway POSProgram ID : " + POSProgramID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}

						// Add account page Check Payment Gateway,enter Lane
						// ID
						try{
							String LaneID = GenericUtils.cellValue(sheet, rownum, 32);
							int LaneIDLength=LaneID.length();
							if(LaneIDLength>0){
								aapo.EnterAddAccTxtLaneID(LaneID);
								logger.info("Check Payment Gateway Lane ID : " + LaneID);
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						TC_16_Add_Account.log(LogStatus.INFO,
								"Account Information, Account Name : " + AccountName + 
								" || Account Information, Account Type : " + AddAccAccountType +
								" || Account Information, Account Category : " + AddAccAccountCategory );

						// Add account page,Click on assign region button
						String AssignRegionYN=GenericUtils.cellValue(sheet, rownum, 33).toString().toUpperCase();
						boolean ExecuteAssReg=AssignRegionYN.equals("YES");
						if(ExecuteAssReg==true){
							aapo.ClickAddAccAssignRegion();
							logger.info("Clicked on Assign Region Button");

							// Switch to the Child Window
							aapo.switchToWindowByIndex(1);

							// Assign region page,enter region code
							//try{
							ARRegionCode = GenericUtils.cellValue(sheet, rownum, 34).toString().toUpperCase().trim();
							if(!"ALL".equals(ARRegionCode)){
								aaarpo.EnterAddAccRegionCode(ARRegionCode);
								logger.info("Region Code : " + ARRegionCode);

								// Add account page,click search region button
								aaarpo.ClickAddAccSearchRegion();

								// Add account page,select assign region check box
								aaarpo.ClickAddAccSelectRegionBox();

								// To get assign region, region name

								// To locate table
								WebElement eleregionname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']/div[6]/div[1]"));

								// To locate rows of table
								List<WebElement> rowsTable = eleregionname
										.findElements(By.xpath(".//*[@id='grdRegionList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount = rowsTable.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow = rowsTable.get(row)
											.findElements(By.xpath(".//*[@id='grdRegionList_lblLastName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount = columnsRow.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount; col++) {
										//To retrieve text from specific cell
										RegionName = columnsRow.get(col).getText();	
										logger.info("Region Name : " + RegionName);

									}
								}

								// Add account page,click on assign region button
								aaarpo.ClickAddAccAssignUserRegion();
							}
							else{

								aaarpo.ClickAddAccountSelectAllRegionNameBox();
								logger.info("Add account page select all Region Name");

								// To get assign region, region name

								// To locate table
								WebElement eleregionname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']/div[6]/div[1]"));
								// To locate rows of table
								List<WebElement> rowsTable = eleregionname
										.findElements(By.xpath(".//*[@id='grdRegionList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount = rowsTable.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow = rowsTable.get(row)
											.findElements(By.xpath(".//*[@id='grdRegionList_lblLastName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount = columnsRow.size();

									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount; col++) {
										//To retrieve text from specific cell
										RegionName = columnsRow.get(col).getText();
										logger.info("Region Name : " + RegionName);

									}
								}

								// Add account page,click on assign region button
								aaarpo.ClickAddAccAssignUserRegion();
							}
						}
						//Switch to parent window
						aapo.switchToWindowByIndex(0);

						//Add account page,Click on assign facility button
						String AssignFacilityYN=GenericUtils.cellValue(sheet, rownum, 35).toString().toUpperCase();
						boolean ExecuteAssFac=AssignFacilityYN.equals("YES");
						if(ExecuteAssFac==true){
							aapo.ClickAddAccAssignFacility();
							logger.info("Clicked on Assign Facility Button");

							// Switch to the Child Window
							aapo.switchToWindowByIndex(1);
							Thread.sleep(GlobalVar.threadSleep);

							// Switch to the parent Window
							aapo.switchToWindowByIndex(0);

							// Switch to the Child Window
							aapo.switchToWindowByIndex(1);
							//Don't Delete
							// Assign facility page,enter facility name
							/*
							 * String AFFacilityName
							 * =GenericUtils.cellValue(sheet, rownum, 34);
							 * logger.info(AFFacilityName);
							 * aaafpo.EnterAddAccFacilityName(AFFacilityName);
							 */
							// TC_16_Add_Account.log(LogStatus.INFO, "Enter
							// facility name to assign facility");

							// Assign facility page ,enter taxid
							AFTaxId = GenericUtils.cellValue(sheet, rownum, 36).toString().toUpperCase();
							if(!"ALL".equals(AFTaxId)){
								aaafpo.EnterAddAccTaxId(AFTaxId);
								logger.info("Tax ID : " + AFTaxId);

								// Assign facility page,click on search facility
								// button
								aaafpo.ClickAddAccSearchFaciity();

								// Assign facility page,click facility name check
								// box
								aaafpo.ClickAddAccSctFacilityNameBox();

								// Assign facility, facility name

								// To locate table
								WebElement elefacilityname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner_Lookups']/div[6]/div[1]"));
								// To locate rows of table
								List<WebElement> rowsTable1 = elefacilityname
										.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount1 = rowsTable1.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount1; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow1 = rowsTable1.get(row)
											.findElements(By.xpath(".//*[@id='grdAccountList_lblAccountName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount1 = columnsRow1.size();

									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount1; col++) {
										// To retrieve text from specific cell
										FacilityName = columnsRow1.get(col).getText();
										logger.info("Facility Name : " + FacilityName);

									}
								}

								// Assign facility,click assign facility button check box
								aaafpo.ClickAddAccAssignFacilityBtn();
							}
							else{

								aaafpo.ClickAddAccountSelectAllFacilityNameBox();
								logger.info("Assign facility page select all Facility Name ");

								// Assign facility, facility name
								// To locate table
								WebElement elefacilityname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner_Lookups']/div[6]/div[1]"));
								// To locate rows of table
								List<WebElement> rowsTable1 = elefacilityname
										.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount1 = rowsTable1.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount1; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow1 = rowsTable1.get(row)
											.findElements(By.xpath(".//*[@id='grdAccountList_lblAccountName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount1 = columnsRow1.size();

									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount1; col++) {
										// To retrieve text from specific cell
										FacilityName = columnsRow1.get(col).getText();
										logger.info("Facility Name : " + FacilityName);
									}
								}

								// Assign facility,click assign facility button check box
								aaafpo.ClickAddAccAssignFacilityBtn();
							}
							// Switch to parent window
							aapo.switchToWindowByIndex(0);

						}

						// Assign provider page,Click on assign provider
						// button
						String AssignProviderYN=GenericUtils.cellValue(sheet, rownum, 37).toString().toUpperCase();
						boolean ExecuteAssProv=AssignProviderYN.equals("YES");
						if(ExecuteAssProv==true){
							aapo.ClickAddAccAssignProvider();
							logger.info("Clicked on Assign Provider Button");

							// Switch to the Child Window
							aapo.switchToWindowByIndex(1);
							Thread.sleep(GlobalVar.threadSleep);

							// Switch to the parent Window
							aapo.switchToWindowByIndex(0);

							// Switch to the Child Window
							aapo.switchToWindowByIndex(1);

							// Assign Provider page,enter assign provider npi
							APNPI = GenericUtils.cellValue(sheet, rownum, 38).toString().toUpperCase();
							if(!"ALL".equals(APNPI)){
								aaappo.EnterAddAccTxtNpi(APNPI);
								logger.info("Provider NPI : " + APNPI);


								// Assign provider page,click search provider button
								aaappo.ClickAddAccSearchProvider();

								// Assign provider page,click check box button
								aaappo.ClickAddAccChkBoxToSct();

								// Assign provider,provider last name

								// To locate table
								WebElement eleprlastname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']"));
								// To locate rows of table
								List<WebElement> rowsTable2 = eleprlastname
										.findElements(By.xpath(".//*[@id='grdProviderListERX']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount2 = rowsTable2.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount2; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow2 = rowsTable2.get(row)
											.findElements(By.xpath(".//*[@id='grdProviderListERX_lblLastNameERX_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount1 = columnsRow2.size();

									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount1; col++) {
										// To retrieve text from specific cell
										ProviderLastName = columnsRow2.get(col).getText();
										logger.info("Provider Last Name : " + ProviderLastName);

									}
								}

								// Assign provider, provider first name
								// To locate table
								WebElement eleprfirstname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']"));
								// To locate rows of table
								List<WebElement> rowsTable3 = eleprfirstname
										.findElements(By.xpath(".//*[@id='grdProviderListERX']/tbody/tr[3]/td[3]"));
								// To calculate no of rows in table
								int rowscount3 = rowsTable3.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount3; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow3 = rowsTable3.get(row)
											.findElements(By.xpath(".//*[@id='grdProviderListERX_lblFirstNameERX_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount3 = columnsRow3.size();

									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount3; col++) {
										// To retrieve text from specific cell
										ProviderFirstName = columnsRow3.get(col).getText();
										logger.info("Provider First Name : " + ProviderFirstName);

									}
								}

								// Assign provider page,click assign provider button
								aaappo.ClickAddAccAssignProviderBtn();
							}
							else{

								aaappo.ClickAddAccountSelectAllFacilityLastNameBox();
								logger.info("Add account page select all Facility Last Name");

								// To locate table
								WebElement eleprlastname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']"));
								// To locate rows of table
								List<WebElement> rowsTable2 = eleprlastname
										.findElements(By.xpath(".//*[@id='grdProviderListERX']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount2 = rowsTable2.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount2; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow2 = rowsTable2.get(row)
											.findElements(By.xpath(".//*[@id='grdProviderListERX_lblLastNameERX_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount1 = columnsRow2.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount1; col++) {
										// To retrieve text from specific cell
										ProviderLastName = columnsRow2.get(col).getText();
										logger.info("Provider Last Name : " + ProviderLastName);

									}
								}

								// Assign provider, provider first name
								// To locate table
								WebElement eleprfirstname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']"));
								// To locate rows of table
								List<WebElement> rowsTable3 = eleprfirstname
										.findElements(By.xpath(".//*[@id='grdProviderListERX']/tbody/tr[3]/td[3]"));
								// To calculate no of rows in table
								int rowscount3 = rowsTable3.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount3; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow3 = rowsTable3.get(row)
											.findElements(By.xpath(".//*[@id='grdProviderListERX_lblFirstNameERX_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount3 = columnsRow3.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount3; col++) {
										// To retrieve text from specific cell
										ProviderFirstName = columnsRow3.get(col).getText();
										logger.info("Provider First Name : " + ProviderFirstName);

									}
								}

								// Assign provider page,click assign provider button
								aaappo.ClickAddAccAssignProviderBtn();
							}
							// Switch to parent window
							aapo.switchToWindowByIndex(0);
						}
						// Assign user page,Click on assign user button
						String AssignUserYN=GenericUtils.cellValue(sheet, rownum, 39).toString().toUpperCase();
						boolean ExecuteAssUser=AssignUserYN.equals("YES");
						if(ExecuteAssUser==true){
							aapo.ClickAddAccAssignUser();
							logger.info("Clicked on Assign User Button");

							// Switch to the Child Window
							aapo.switchToWindowByIndex(1);
							Thread.sleep(GlobalVar.threadSleep);

							// Switch to the parent Window
							aapo.switchToWindowByIndex(0);

							// Switch to child window
							aapo.switchToWindowByIndex(1);

							// Assign user page,enter user name
							AUUserName = GenericUtils.cellValue(sheet, rownum, 40).toString().toUpperCase();
							if(!"ALL".equals(AUUserName)){
								aaaupo.EnterAddAccUserName(AUUserName);
								logger.info("User Name :" + AUUserName);

								// Assign user page,click on search user button
								aaaupo.ClickAddAccSearchUser();

								// Assign user page,click on user check box
								aaaupo.ClickAddAccUserChkBox();

								// Assign user, user last name

								// To locate table
								WebElement eleuserlastname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='gridHolder']/div"));
								// To locate rows of table
								List<WebElement> rowsTable4 = eleuserlastname
										.findElements(By.xpath(".//*[@id='gridCustomUserList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount4 = rowsTable4.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount4; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow4 = rowsTable4.get(row)
											.findElements(By.xpath(".//*[@id='gridCustomUserList_lblLastName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount4 = columnsRow4.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount4; col++) {
										// To retrieve text from specific cell
										UserLastName = columnsRow4.get(col).getText();
										logger.info("User Last Name : " + UserLastName);

									}
								}

								// Assign user, user first name
								// To locate table
								WebElement eleuserfirstname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='gridHolder']/div"));
								// To locate rows of table
								List<WebElement> rowsTable5 = eleuserfirstname
										.findElements(By.xpath(".//*[@id='gridCustomUserList']/tbody/tr[3]/td[3]"));
								// To calculate no of rows in table
								int rowscount5 = rowsTable5.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount5; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow5 = rowsTable5.get(row)
											.findElements(By.xpath(".//*[@id='gridCustomUserList_lblFirstName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount5 = columnsRow5.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount5; col++) {
										// To retrieve text from specific cell
										UserFirstName = columnsRow5.get(col).getText();
										logger.info("Assign User, First Name :" + UserFirstName);

									}
								}
							}
							else{
								aaaupo.ClickAddAccountSelectAllUserLastNameBox();
								logger.info("Add account page select all User Last Name");

								// Assign user, user last name
								// To locate table
								WebElement eleuserlastname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='gridHolder']/div"));
								// To locate rows of table
								List<WebElement> rowsTable4 = eleuserlastname
										.findElements(By.xpath(".//*[@id='gridCustomUserList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount4 = rowsTable4.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount4; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow4 = rowsTable4.get(row)
											.findElements(By.xpath(".//*[@id='gridCustomUserList_lblLastName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount4 = columnsRow4.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount4; col++) {
										// To retrieve text from specific cell
										UserLastName = columnsRow4.get(col).getText();
										logger.info("User Last Name : " + UserLastName);

									}
								}

								// Assign user, user first name

								// To locate table
								WebElement eleuserfirstname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='gridHolder']/div"));
								// To locate rows of table
								List<WebElement> rowsTable5 = eleuserfirstname
										.findElements(By.xpath(".//*[@id='gridCustomUserList']/tbody/tr[3]/td[3]"));
								// To calculate no of rows in table
								int rowscount5 = rowsTable5.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount5; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow5 = rowsTable5.get(row)
											.findElements(By.xpath(".//*[@id='gridCustomUserList_lblFirstName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount5 = columnsRow5.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount5; col++) {
										// To retrieve text from specific cell
										UserFirstName = columnsRow5.get(col).getText();
										logger.info("User First Name : " + UserFirstName);

									}
								}
							}
							// Assign user page,click on assign user button
							aaaupo.ClickAddAccUserAccountSave();

							// Switch to parent window
							aapo.switchToWindowByIndex(0);
						}

						Thread.sleep(GlobalVar.threadSleep);

						// Add account page,click on save button
						aapo.ClickAddAccSave();
						logger.info("Press Save Account Button To Save all Account Information");

						// Enter account name to search
						apo.EnterAccountName(AccountName);
						logger.info("To Search Account Information Account Name : " + AccountName);

						// Account page,click on search account button
						apo.ClickAccSearchAccount();

						// Account name present or not
						confirm = apo.verifyAccountNamePresentorNot();
						
					}
					catch (Exception e) {

						logger.info("Add Account Exception :" + e.getMessage().toString());
						ScreenShotPath = ReportGenerator.setLog("Add account failed", e.getMessage().toString(),
								"Add account TC_16_Fail");
						String image = TC_16_Add_Account.addScreenCapture(ScreenShotPath);
						TC_16_Add_Account.log(LogStatus.FAIL, image);
					
					}

					TC_16_Add_Account.log(LogStatus.INFO,
							"Region Code : " + ARRegionCode + " || Region Name : " + RegionName + " || Facility Name : "
									+ FacilityName + " || Tax ID : " + AFTaxId + " || Provider NPI : " + APNPI + " || Provider Last Name : "
									+ ProviderLastName + " || Provider First Name : " + ProviderFirstName + " || User Name : " + AUUserName
									+ " || User Last Name : " + UserLastName + " || User First Name : " + UserFirstName);
					
					if (confirm == true) {
						logger.info("Account details have been created successfully");
						TC_16_Add_Account.log(LogStatus.PASS, "Account details have been created successfully");
					}
					else
					{
						logger.info("Account details creation failed");
						TC_16_Add_Account.log(LogStatus.FAIL, "Account details creation failed");
					}
					
					logger.info("Multi Test Data :" + multitestdata + " Execute : " + Execute);
					if ((Boolean.FALSE.equals(multitestdata)) && (Boolean.TRUE.equals(Execute))) {
						logger.info("Multi Test Data : " + (Boolean.FALSE.equals(multitestdata)) + " Execute Y/N : "
								+ (Boolean.TRUE.equals(Execute)));
						break;
					}
				
					
				}

				//Close the Workbook
				workbook.close();

			}
			
			GlobalVar.report.endTest(TC_16_Add_Account);

		}catch (Exception e) {
			e.printStackTrace();
			ScreenShotPath = ReportGenerator.setLog("Add account failed", e.getMessage().toString(),
					"Add account TC_16_Fail");
			String image = TC_16_Add_Account.addScreenCapture(ScreenShotPath);
			TC_16_Add_Account.log(LogStatus.FAIL, image);
			GlobalVar.report.endTest(TC_16_Add_Account);
			
		}

	}

}