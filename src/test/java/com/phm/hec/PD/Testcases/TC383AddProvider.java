package com.phm.hec.PD.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.phm.hec.PD.Objects.AddCallLogPageObjects;
import com.phm.hec.PD.Objects.AddProviderPageObjects;
import com.phm.hec.PD.Objects.AssignCommitteePageObjects;
import com.phm.hec.PD.Objects.AssignPrimaryAccountPageObjects;
import com.phm.hec.PD.Objects.AssignPrimaryFacilityPageObjects;
import com.phm.hec.PD.Objects.AssignRegionPageObjects;
import com.phm.hec.PD.Objects.AssignSecondaryAccountPageObjects;
import com.phm.hec.PD.Objects.AssignSecondaryFacilityPageObjects;
import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.PD.Objects.ProviderPageObjects;
import com.phm.hec.pageObjects.HeaderPageObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.testcases.Login;
import com.phm.hec.utility.GenericUtils;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReadExcelFile;
import com.phm.hec.utility.ReportGenerator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC383AddProvider {

	static ExtentTest TC_383_AddProvider;
	public static String ScreenShotPath="";
	public static String testdata;
	public static String TestName="";
	public static String FirstName="";
	public static String LastName="";
	public static String PRCategory="";
	public static String ARRegionCode="";
	public static String PRRegionName="";
	public static String PRPrimaryAccountName="";
	public static String APATaxID="";
	public static String PRSecondaryAccountName="";
	public static String ASATaxID="";
	public static String PRPrimaryFacilityName="";
	public static String APFTaxID="";
	public static String PRSecondaryFacilityName="";
	public static String ASFTaxID="";
	public static String ACCommitteeName="";
	public static Boolean Execute = false;
	public static Logger addlog = Logger.getLogger(TC383AddProvider.class.getName());
	static boolean confirm;

	@Test
	public static void AddProvider() throws Throwable {
		try{
			Logger logger = Logger.getLogger(TC383AddProvider.class.getName());
			TC_383_AddProvider = GlobalVar.report.startTest("TC_383_AddProvider");


			// Intermediate page object
			IntermediatePageObjects INM = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
			// Provider page object
			ProviderPageObjects ppo = PageFactory.initElements(GlobalVar.Driver, ProviderPageObjects.class);
			// Add provider page object
			AddProviderPageObjects appo = PageFactory.initElements(GlobalVar.Driver, AddProviderPageObjects.class);
			// Assign region page object
			AssignRegionPageObjects arpo = PageFactory.initElements(GlobalVar.Driver, AssignRegionPageObjects.class);
			// Assign primary account page object
			AssignPrimaryAccountPageObjects apapo = PageFactory.initElements(GlobalVar.Driver,
					AssignPrimaryAccountPageObjects.class);
			// Assign secondary account page object
			AssignSecondaryAccountPageObjects asapo = PageFactory.initElements(GlobalVar.Driver,
					AssignSecondaryAccountPageObjects.class);
			// Assign primary facility page object
			AssignPrimaryFacilityPageObjects apfpo = PageFactory.initElements(GlobalVar.Driver,
					AssignPrimaryFacilityPageObjects.class);
			// Assign secondary facility page object
			AssignSecondaryFacilityPageObjects asfpo = PageFactory.initElements(GlobalVar.Driver,
					AssignSecondaryFacilityPageObjects.class);
			// Assign committee page object
			AssignCommitteePageObjects acpo = PageFactory.initElements(GlobalVar.Driver, AssignCommitteePageObjects.class);
			// Add call log page object
			AddCallLogPageObjects aclpo = PageFactory.initElements(GlobalVar.Driver, AddCallLogPageObjects.class);

			// Provider Admin page objects
			ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);

			// Header page object
			HeaderPageObjects hpo = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);

			boolean multitestdata=Boolean.parseBoolean(GlobalVar.multpletestdata);


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
					INM.ClickProviderAdmin();
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
						INM.ClickProviderAdmin();

					} else {
						Thread.sleep(GlobalVar.threadSleep);
						logger.info("Click on Provider Admin Home Tab...");
						INM.ClickProviderAdmin();
					}
				} catch (Exception e) {
					logger.info("Exception Message :" + e.getMessage());
				}
			}

			Thread.sleep(GlobalVar.threadSleep);

			//Click on organization link
			PAP.ClickLnkOrganization();

			// Click on the Provider link in the left Menu Pane
			PAP.ClickLnkProvider();
			
			TC_383_AddProvider.log(LogStatus.INFO, "TC Add Provider -Test Execution Starts");
		
			String filepath = GlobalVar.TestDataAddProviderTC383FilePath;
			logger.info("File Path for Add Provider: " + filepath);
			// Data Driven starting here

			File AddProvider = new File(filepath);
			FileInputStream fis = new FileInputStream(AddProvider);
			int rows;
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			String AddPrSheetName=GlobalVar.TDSheetName.toString().trim();
			AddPrSheetName=AddPrSheetName.toUpperCase().trim();
			logger.info("Sheet Name :" + AddPrSheetName);
			XSSFSheet sheet = workbook.getSheet(AddPrSheetName);
			
			if(!"BAT".equals(GlobalVar.TestType)){
				rows=sheet.getLastRowNum()+1;
			}else{
				rows=2;
			}
			
			int cols = sheet.getRow(1).getLastCellNum();
			logger.info("Test Type : " + GlobalVar.TestType + " || Total Rows :" + rows + "|| Total Columns : " + cols);
			
			for (int rownum = 1; rownum < rows; rownum++) {

				TestName="TC_383_Add_Provider" + "_" + "rows :" + rownum;
				String Executeyn = GenericUtils.cellValue(sheet, rownum, cols-1);
				String ExecuteYN = Executeyn.toUpperCase().trim();
				Boolean Execute = ExecuteYN.equals("YES");
				logger.info(Execute);
				/*logger.info("Test Data Execute Y/N : " + ExecuteYN + " Execute the Row " + rownum + " Execute = "
						+ Execute + "Test Type : " + GlobalVar.TestType);
				*/
				if (Execute == true || "BAT".equals(GlobalVar.TestType)){

					try{	
						
						// Click on Add Provider Button
						logger.info("Clicked on Add Provider");
						ppo.ClickAddProvider();

						String ChkboxOrg = GenericUtils.cellValue(sheet, rownum, 0);
						String OrgChkBox = ChkboxOrg.toUpperCase().trim();
						Boolean ORGCHK = OrgChkBox.equals("YES");
						if (ORGCHK == true) {
							appo.ClickPrOrganizationChkBox();
							logger.info("Provider Name, Organization (Select checkbox to enter Organization details) :" + ORGCHK);
						}
						// Select Provider Category
						try{						
							PRCategory =GenericUtils.cellValue(sheet, rownum, 1);
							int PRCategoryLength=PRCategory.length();
							if(PRCategoryLength>0){
								appo.SctProviderCategory(PRCategory);
								logger.info("Provider Category : " + PRCategory);
							}
						}catch(Exception e){
							e.printStackTrace();

						}
						// Enter Provider last Name
						try{
							LastName = GenericUtils.cellValue(sheet, rownum, 2);
							int LastNameLength=LastName.length();
							if(LastNameLength>0){
								appo.EnterPrLastName(LastName);
								logger.info("Provider Last Name : " + LastName);
							}
						}catch(Exception e){
							e.printStackTrace();

						}
						//Enter provider middle name						
						try{							
							String MiddleName = GenericUtils.cellValue(sheet, rownum, 3);
							int MiddleNameLength=MiddleName.length();
							if(MiddleNameLength>0){
								appo.EnterPrMiddleName(MiddleName);
								logger.info("Provider Middle Name : " + MiddleName);
							}
						}catch(Exception e){
							e.printStackTrace();

						}
						// Enter Provider first name
						try{						
							FirstName = GenericUtils.cellValue(sheet, rownum, 4);
							int FirstNameLength=FirstName.length();
							if(FirstNameLength>0)
								appo.EnterPrFirstName(FirstName);
								logger.info("Provider First Name : " + FirstName);
						}catch(Exception e){
							e.printStackTrace();

						}
						// Enter Provider Title
						try{							
							String PRTitle = GenericUtils.cellValue(sheet, rownum, 5);
							int PrTitleLength=PRTitle.length();
							if(PrTitleLength>0){
								appo.EnterPrTitle(PRTitle);
								logger.info("Provider Name, Title : " + PRTitle);
							}
						}catch(Exception e){
							e.printStackTrace();	
						}

						// Enter Provider Degree
						try{							
							String PRDegree = GenericUtils.cellValue(sheet, rownum, 6);
							int PrDegreeLength=PRDegree.length();
							if(PrDegreeLength>0){
								appo.EnterPrDegree(PRDegree);
								logger.info("Provider Name, Degree : " + PRDegree);
							}
						}catch(Exception e){
							e.printStackTrace();	
						}

						// Enter Provider Name Suffix
						try{							
							String PRSufix =GenericUtils.cellValue(sheet, rownum, 7);
							int PrSufixLength=PRSufix.length();
							if(PrSufixLength>0){
								appo.EnterPrSuffix(PRSufix);
								logger.info("Provider Name, Suffix : " + PRSufix);
							}
						}catch(Exception e){
							e.printStackTrace();					
						}
						// Enter Provider Identification NPI
						try{
							String PRNPI = GenericUtils.cellValue(sheet, rownum, 8);
							int PrNpiLength=PRNPI.length();
							if(PrNpiLength>0){
								appo.EnterPrNpi(PRNPI);
								logger.info("Provider Identification, NPI : " + PRNPI);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Identification Tax ID
						try{							
							String PRTAXID = GenericUtils.cellValue(sheet, rownum, 9);
							int PrTaxIdLength=PRTAXID.length();
							if(PrTaxIdLength>0){
								appo.EnterPrTaxId(PRTAXID);
								logger.info("Provider Identification, Tax ID : " + PRTAXID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Identification State-Assigned ID
						try{							
							String PRStateAssignedID = GenericUtils.cellValue(sheet, rownum, 10);
							int PrStateAssignedIdLength=PRStateAssignedID.length();
							if(PrStateAssignedIdLength>0){
								appo.EnterPrStateAssignedId(PRStateAssignedID);
								logger.info("Provider Identification, State-Assigned ID : " + PRStateAssignedID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}

						// Enter Provider Identification Internal ID
						try{							
							String PRINTERNALID = GenericUtils.cellValue(sheet, rownum, 11);
							int PrInternalIdLength=PRINTERNALID.length();
							if(PrInternalIdLength>0){
								appo.EnterPrInternalId(PRINTERNALID);
								logger.info("Provider Identification, Internal ID : " + PRINTERNALID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Identification Organisation Assigned ID
						try{							
							String PROrganizationAssignedID = GenericUtils.cellValue(sheet, rownum, 12);
							int PROrganizationAssignedIDLength=PROrganizationAssignedID.length();
							if(PROrganizationAssignedIDLength>0){
								appo.EnterPrOrganizationAssignedId(PROrganizationAssignedID);
								logger.info("Provider Identification, Organization Assigned ID : " + PROrganizationAssignedID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Identification State License ID
						try{							
							String PRStateLicenseID = GenericUtils.cellValue(sheet, rownum, 13);
							int PRStateLicenseIDLength=PRStateLicenseID.length();
							if(PRStateLicenseIDLength>0){
								appo.EnterPrStateLicenseId(PRStateLicenseID);
								logger.info("Provider Identification, State License ID : " + PRStateLicenseID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Identification SSN
						try{							
							String PRSSN = GenericUtils.cellValue(sheet, rownum, 14);
							int PRSSNLength=PRSSN.length();
							if(PRSSNLength>0){
								appo.EnterPrSSN(PRSSN);
								logger.info("Provider Identification, SSN : " + PRSSN);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Identification Sub ID/Site ID
						try{							
							String PRSubIDSiteID = GenericUtils.cellValue(sheet, rownum, 15);
							int PRSubIDSiteIDLength=PRSubIDSiteID.length();
							if(PRSubIDSiteIDLength>0){
								appo.EnterPrSubIdSiteId(PRSubIDSiteID);
								logger.info("Provider Identification, Sub ID/Site ID : " + PRSubIDSiteID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Select Provider Identification Status/DdlProStatus
						try{							
							String PRStatus = GenericUtils.cellValue(sheet, rownum, 16);
							int PRStatusLength=PRStatus.length();
							if(PRStatusLength>0){
								appo.SctPrDdlProStatus(PRStatus);
								logger.info("Provider Identification, Status : " + PRStatus);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Taxonomy & Description Taxonomy Code Set
						try{							
							String PRTaxonomyCodeSet = GenericUtils.cellValue(sheet, rownum, 17);
							int PRTaxonomyCodeSetLength=PRTaxonomyCodeSet.length();
							if(PRTaxonomyCodeSetLength>0){
								appo.SctPrDdlTaxonomyCodeSet(PRTaxonomyCodeSet);
								logger.info("Taxonomy & Description, Taxonomy Code Set : " + PRTaxonomyCodeSet);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Taxonomy & Description Taxonomy Code
						try{							
							String PRTaxonomyCode = GenericUtils.cellValue(sheet, rownum, 18);
							int PRTaxonomyCodeLength=PRTaxonomyCode.length();
							if(PRTaxonomyCodeLength>0){
								appo.SctPrDdlTaxonomyCode(PRTaxonomyCode);
								logger.info("Taxonomy & Description, Taxonomy Code : " + PRTaxonomyCode);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Taxonomy & Description Provider Type
						try{							
							String PRProviderType =GenericUtils.cellValue(sheet, rownum, 19);
							int PRProviderTypeLength=PRProviderType.length();
							if(PRProviderTypeLength>0){
								appo.SctDdlProviderType(PRProviderType);
								logger.info("Taxonomy & Description, Provider Type  : " + PRProviderType);
							}
						}catch(Exception e){
							e.printStackTrace();
						}

						// Enter Provider Contact Information Last Name
						try{							
							String PCILastName =GenericUtils.cellValue(sheet, rownum, 20);
							int PCILastNameLength=PCILastName.length();
							if(PCILastNameLength>0){
								appo.EnterPCILastName(PCILastName);
								logger.info("Provider Contact Information, Last Name : " + PCILastName);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information First Name
						try{							
							String PCIFirstName = GenericUtils.cellValue(sheet, rownum, 21);
							int PCIFirstNameLength=PCIFirstName.length();
							if(PCIFirstNameLength>0){
								appo.EnterPCIFirstName(PCIFirstName);
								logger.info("Provider Contact Information, First Name : " + PCIFirstName);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information Email
						try{							
							String PCIEmail = GenericUtils.cellValue(sheet, rownum, 22);
							int PCIEmailLength=PCIEmail.length();
							if(PCIEmailLength>0){
								appo.EnterPCIEmail(PCIEmail);
								logger.info("Provider Contact Information, Email : " + PCIEmail);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information Address 1
						try{							
							String PCIAddress1 = GenericUtils.cellValue(sheet, rownum, 23);
							int PCIAddress1Length=PCIAddress1.length();
							if(PCIAddress1Length>0){
								appo.EnterPCIAddress1(PCIAddress1);
								logger.info("Provider Contact Information, Address 1 : " + PCIAddress1);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information Address 2
						try{							
							String PCIAddress2 = GenericUtils.cellValue(sheet, rownum, 24);
							int PCIAddress2Length=PCIAddress2.length();
							if(PCIAddress2Length>0){
								appo.EnterPCIAddress2(PCIAddress2);
								logger.info("Provider Contact Information, Address 2 : " + PCIAddress2);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information City
						try{							
							String PCICity = GenericUtils.cellValue(sheet, rownum, 25);
							int PCICityLength=PCICity.length();
							if(PCICityLength>0){
								appo.EnterPCICity(PCICity);
								logger.info("Provider Contact Information, City : " + PCICity);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information State
						try{							
							String PCIState = GenericUtils.cellValue(sheet, rownum, 26);
							int PCIStateLength=PCIState.length();
							if(PCIStateLength>0){
								appo.SctDdlPCIState(PCIState);
								logger.info("Provider Contact Information, State : " + PCIState);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information Zip
						try{							
							String PCIZip = GenericUtils.cellValue(sheet, rownum, 27);
							int PCIZipLength=PCIZip.length();
							if(PCIZipLength>0){
								appo.EnterPCIZip(PCIZip);
								logger.info("Provider Contact Information, State : " + PCIZip);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information Fax
						try{							
							String PCIFAX = GenericUtils.cellValue(sheet, rownum, 28);
							int PCIFAXLength=PCIFAX.length();
							if(PCIFAXLength>0){
								appo.EnterPCIFax(PCIFAX);
								logger.info("Provider Contact Information, Fax : " + PCIFAX);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Contact Information Phone
						try{							
							String PCIPHONE = GenericUtils.cellValue(sheet, rownum, 29);
							int PCIPHONELength=PCIPHONE.length();
							if(PCIPHONELength>0){
								appo.EnterPCIPhone(PCIPHONE);
								logger.info("Provider Contact Information, Phone : " + PCIPHONE);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Other Information Employed
						try{							
							String POIEmployed = GenericUtils.cellValue(sheet, rownum, 30);
							int POIEmployedLength=POIEmployed.length();
							if(POIEmployedLength>0){
								appo.SctDdlPOIEmployed(POIEmployed);
								logger.info("Provider Other Information, Employed : " + POIEmployed);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Other Information Preferred communication
						try{							
							String POIPreferredcommunication =GenericUtils.cellValue(sheet, rownum, 31);
							int POIPreferredcommunicationLength=POIPreferredcommunication.length();
							if(POIPreferredcommunicationLength>0){
								appo.SctDdlPOIcommunication(POIPreferredcommunication);
								logger.info("Provider Other Information, Preferred communication : " + POIPreferredcommunication);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Other Information Hospital affiliation1
						try{							
							String POIHospitalAffiliation1 =GenericUtils.cellValue(sheet, rownum, 32);
							int POIHospitalAffiliation1Length=POIHospitalAffiliation1.length();
							if(POIHospitalAffiliation1Length>0){
								appo.EnterPOIhospaff1(POIHospitalAffiliation1);
								logger.info("Provider Other Information, Hospital affiliation1 : " + POIHospitalAffiliation1);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Other Information Hospital affiliation2
						try{							
							String POIHospitalAffiliation2 =GenericUtils.cellValue(sheet, rownum, 33);
							int POIHospitalAffiliation2Length=POIHospitalAffiliation2.length();
							if(POIHospitalAffiliation2Length>0){
								appo.EnterPOIhospaff2(POIHospitalAffiliation2);
								logger.info("Provider Other Information, Hospital affiliation2 : " + POIHospitalAffiliation2);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Other Information Hospital affiliation3
						try{							
							String POIHospitalAffiliation3 = GenericUtils.cellValue(sheet, rownum, 34);
							int POIHospitalAffiliation3Length=POIHospitalAffiliation3.length();
							if(POIHospitalAffiliation3Length>0){
								appo.EnterPOIhospaff3(POIHospitalAffiliation3);
								logger.info("Provider Other Information, Hospital affiliation3 : " + POIHospitalAffiliation3);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Enter Provider Other Information Hospital affiliation4
						try{							
							String POIHospitalAffiliation4 = GenericUtils.cellValue(sheet, rownum, 35);
							int POIHospitalAffiliation4Length=POIHospitalAffiliation4.length();
							if(POIHospitalAffiliation4Length>0){
								appo.EnterPOIhospaff4(POIHospitalAffiliation4);
								logger.info("Provider Other Information, Hospital affiliation4 : " + POIHospitalAffiliation4);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Credit / Debit Payment Gateway Merchant ID
						try{							
							String CDPMerchantID = GenericUtils.cellValue(sheet, rownum, 36);
							int CDPMerchantIDLength=CDPMerchantID.length();
							if(CDPMerchantIDLength>0){
								appo.EnterCDPGMerchantId(CDPMerchantID);
								logger.info("Credit / Debit Payment Gateway, Merchant ID : " + CDPMerchantID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Credit / Debit Payment Gateway Operation User ID
						try{
							String CDPOperatorUserID = GenericUtils.cellValue(sheet, rownum, 37);
							int CDPOperatorUserIDLength=CDPOperatorUserID.length();
							if(CDPOperatorUserIDLength>0){
								appo.EnterCDPGOperatorOrUserId(CDPOperatorUserID);
								logger.info("Credit / Debit Payment Gateway, Operator / User ID : " + CDPOperatorUserID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Credit / Debit Payment Gateway Password
						try{
							String CDPPassword = GenericUtils.cellValue(sheet, rownum, 38);
							int CDPPasswordLength=CDPPassword.length();
							if(CDPPasswordLength>0){
								appo.EnterCDPGPaymentPassword(CDPPassword);
								logger.info("Credit / Debit Payment Gateway, Password : " + CDPPassword);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Credit / Debit Payment Gateway Device ID
						try{
							String CDPDeviceID = GenericUtils.cellValue(sheet, rownum, 39);
							int CDPDeviceIDLength = CDPDeviceID.length();
							if(CDPDeviceIDLength>0){
								//if(CDPDeviceID!=null || !CDPDeviceID.equals("")){
								appo.EnterCDPGDeviceID(CDPDeviceID);
								logger.info("Credit / Debit Payment Gateway, Device ID : " + CDPDeviceID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Credit / Debit Payment Gateway Registration Key
						try{
							String CDPRegistrationKey = GenericUtils.cellValue(sheet, rownum, 40);
							int CDPRegistrationKeyLength=CDPRegistrationKey.length();
							if(CDPRegistrationKeyLength>0){
								appo.EnterCDPGRegistrationKey(CDPRegistrationKey);
								logger.info("Credit / Debit Payment Gateway, Registration Key : " + CDPRegistrationKey);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Check Payment Gateway - Gateway
						try{							
							String CPGGateway = GenericUtils.cellValue(sheet, rownum, 41);
							int CPGGatewayLength=CPGGateway.length();
							if(CPGGatewayLength>0){
								appo.SctDdlCPGGateway(CPGGateway);
								logger.info("Credit / Debit Payment Gateway, Gateway : " + CPGGateway);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Check Payment Gateway - Site ID
						try{
							String CPGSiteID = GenericUtils.cellValue(sheet, rownum, 42);
							int CPGSiteIDLength=CPGSiteID.length();
							if(CPGSiteIDLength>0){
								appo.EnterCPGSiteId(CPGSiteID);
								logger.info("Credit / Debit Payment Gateway, Site ID : " + CPGSiteID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Check Payment Gateway - Location ID
						try{
							String CPGLocationID = GenericUtils.cellValue(sheet, rownum, 43);
							int CPGLocationIDLength=CPGLocationID.length();
							if(CPGLocationIDLength>0){
								appo.EnterCPGLocationId(CPGLocationID);
								logger.info("Credit / Debit Payment Gateway, Location ID : " + CPGLocationID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Check Payment Gateway - POS Program ID
						try{							
							String CPGPOSProgramID = GenericUtils.cellValue(sheet, rownum, 44);
							int CPGPOSProgramIDLength=CPGPOSProgramID.length();
							if(CPGPOSProgramIDLength>0){
								appo.EnterCPGPOSProgramId(CPGPOSProgramID);
								logger.info("Credit / Debit Payment Gateway, POSProgram ID : " + CPGPOSProgramID);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						// Check Payment Gateway - Lane ID
						try{
							String CPGLaneID = GenericUtils.cellValue(sheet, rownum, 45);
							int CPGLaneIDLength=CPGLaneID.length();
							if(CPGLaneIDLength>0){
								appo.EnterCPGLaneId(CPGLaneID);
								logger.info("Credit / Debit Payment Gateway, Lane ID : " + CPGLaneID);

							}
						}catch(Exception e){
							e.printStackTrace();
						}
						TC_383_AddProvider.log(LogStatus.INFO, "Provider Category : " + PRCategory 
								+ " || Provider Last Name : " + LastName + " || Provider First Name : " + FirstName);

						// Add provider page,Click on assign region button
						String AssignRegionYN=GenericUtils.cellValue(sheet, rownum, 46).toString().toUpperCase();
						boolean ExecuteAssReg=AssignRegionYN.equals("YES");
						if(ExecuteAssReg==true){
							appo.ClickPrAssignRegion();
							logger.info("Clicked on Assign Region Button : " + ExecuteAssReg);

							// Switch to the Child Window
							appo.switchToWindowByIndex(1);

							// Enter Region code to search
							ARRegionCode = GenericUtils.cellValue(sheet, rownum, 47).toString().toUpperCase();
							if(ARRegionCode!="NO"){
								arpo.EnterPrRegionCode(ARRegionCode);
								logger.info("Assign Region,Region Code : " + ARRegionCode);

								// Click on the Search Region Button
								arpo.ClickPrSearchRegion();

								// Click select region box
								arpo.ClickPrSelectRegionBox();

								//Add Provider page,to get assign region, region name

								// To locate table
								WebElement Prregionname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner_Lookups']/div[4]/div"));
								// To locate rows of table
								List<WebElement> rowsTable = Prregionname
										.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[3]"));
								// To calculate no of rows in table
								int rowscount = rowsTable.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow = rowsTable.get(row)
											.findElements(By.xpath(".//*[@id='grdAccountList_lblAccountName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount = columnsRow.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount; col++) {
										//To retrieve text from specific cell
										PRRegionName = columnsRow.get(col).getText();
										logger.info("Region Name : " + PRRegionName);

									}
								}

								// select region start date
								arpo.ClickOnFromDateCalendar();

								// Select the From date
								GlobalVar.Driver.findElement(By.xpath("//td[text()='11']")).click();

								// select region termed date
								arpo.ClickOnTermDateCalendar();

								//Select the termed date
								GlobalVar.Driver.findElement(By.xpath("//td[text()='11']")).click();

								// select region status
								String ARRegionPRStatus = GenericUtils.cellValue(sheet, rownum, 48);
								arpo.SctRegionstatus(ARRegionPRStatus);
								logger.info("Region Provider Status : " + ARRegionPRStatus);

								// Click on singed contract
								String ARPRSignedContract = GenericUtils.cellValue(sheet, rownum, 49);
								arpo.SctSignedContract(ARPRSignedContract);
								logger.info("Assign Region, Signed Contract : " + ARPRSignedContract);

								// click assign region button
								arpo.ClickPrAssignUserRegionBtn();
							}
						}
						// Switch to parent window
						appo.switchToWindowByIndex(0);

						// Delete assign region(Plz don't delete)
						// appo.ClickDeleteRegion();

						// Add provider page,Click on assign primary account button
						String AssignPrimaryAccountYN=GenericUtils.cellValue(sheet, rownum, 50).toString().toUpperCase();
						boolean ExecuteAssPryAcc=AssignPrimaryAccountYN.equals("YES");
						if(ExecuteAssPryAcc==true){
							appo.ClickPrAssignPrimaryAccountBtn();
							logger.info("Clicked on Primary Assign Account Button : " + ExecuteAssPryAcc);

							// Switch to child window
							appo.switchToWindowByIndex(1);

							/*// Enter accont name
							sheet.getRow(rownum).getCell(51).setCellType(Cell.CELL_TYPE_STRING);
							String APAAccountName = sheet.getRow(rownum).getCell(51).getStringCellValue();
							apapo.EnterPryAccountName(APAAccountName);*/

							// Enter tax id 
							APATaxID =GenericUtils.cellValue(sheet, rownum, 51);
							if(APATaxID!="No"){
								apapo.EnterPryTaxId(APATaxID);
								logger.info("Primary Account TaxID : " + APATaxID);
							}
							// Click on search user region
							apapo.ClickPrPrimarySearchUserRegionBtn();

							//Add provider page, provider primary account name

							// To locate table
							WebElement PrPrimaryAccountname = GlobalVar.Driver
									.findElement(By.xpath(".//*[@id='content_inner']/div[4]/div"));
							// To locate rows of table
							List<WebElement> rowsTable1 = PrPrimaryAccountname
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
									PRPrimaryAccountName = columnsRow1.get(col).getText();
									logger.info("Primary Account Name : " + PRPrimaryAccountName);

								}
							}


							// Click on link
							apapo.ClickPrPrimaryAccountName();

							// Switch to parent window
							appo.switchToWindowByIndex(0);
						}
						// Click on Assign Secondary Account button
						String AssignSeconAccountYN=GenericUtils.cellValue(sheet, rownum, 52).toString().toUpperCase();
						boolean ExecuteAssSeconAcc=AssignSeconAccountYN.equals("YES");
						if(ExecuteAssSeconAcc==true){
							appo.ClickPrAssignSecondaryAccountBtn();
							logger.info("Clicked on Secondary Assign Account Button : " + ExecuteAssSeconAcc);

							// Switch to child window
							appo.switchToWindowByIndex(1);

							/*// Enter secondary accont name
							sheet.getRow(rownum).getCell(53).setCellType(Cell.CELL_TYPE_STRING);
							String ASAAccountName = sheet.getRow(rownum).getCell(53).getStringCellValue();
							asapo.EnterSecondaryAccountName(ASAAccountName);*/

							// Enter secondary tax id
							ASATaxID = GenericUtils.cellValue(sheet, rownum, 53).toString().toUpperCase();
							if(!"ALL".equals(ASATaxID)){
								asapo.EnterSecondaryTaxId(ASATaxID);
								logger.info("Secondary Account TaxID : " + ASATaxID);

								// Click on secondary search account button
								asapo.ClickSeconSearchUser();

								// Select one secondary account name
								asapo.ClickSeconAccountName();

								//Add provider page, provider secondary account name

								// To locate table
								WebElement PrSecondaryAccountname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']/div[4]/div"));
								// To locate rows of table
								List<WebElement> rowsTable2 = PrSecondaryAccountname
										.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount2 = rowsTable2.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount2; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow2 = rowsTable2.get(row)
											.findElements(By.xpath(".//*[@id='grdAccountList_lblAccountName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount2 = columnsRow2.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount2; col++) {
										// To retrieve text from specific cell
										PRSecondaryAccountName= columnsRow2.get(col).getText();
										logger.info("Secondary Account Name : " + PRSecondaryAccountName);

									}
								}

								// Click on secondary assign account
								asapo.ClickSeconAssignAccount();
							}
							else{

								asapo.ClickAddProviderSelectAllSecondaryAccountNameBox();
								logger.info("Add provider page, select all assign secondary account ,Account Name");

								//Add provider page, provider secondary account name

								// To locate table
								WebElement PrSecondaryAccountname = GlobalVar.Driver
										.findElement(By.xpath(".//*[@id='content_inner']/div[4]/div"));
								// To locate rows of table
								List<WebElement> rowsTable2 = PrSecondaryAccountname
										.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[2]"));
								// To calculate no of rows in table
								int rowscount2 = rowsTable2.size();
								// Loop will execute till the last row of table
								for (int row = 0; row < rowscount2; row++) {
									// To locate columns(cells) of that specific row
									List<WebElement> columnsRow2 = rowsTable2.get(row)
											.findElements(By.xpath(".//*[@id='grdAccountList_lblAccountName_0']"));
									// To calculate no of columns(cells) in that
									// specific row
									int colscount2 = columnsRow2.size();
									// Loop will execute till the last cell of that
									// specific row
									for (int col = 0; col < colscount2; col++) {
										// To retrieve text from specific cell
										PRSecondaryAccountName= columnsRow2.get(col).getText();
										logger.info("Secondary Account Name : " + PRSecondaryAccountName);

									}
								}

								// Click on secondary assign account
								asapo.ClickSeconAssignAccount();

							}
						}
						// Switch to parent window
						appo.switchToWindowByIndex(0);

						// Click on Assign Primary Facility button						
						String AssignPryFacilityYN=GenericUtils.cellValue(sheet, rownum, 54).toString().toUpperCase();
						boolean ExecuteAssPryFacility=AssignPryFacilityYN.equals("YES");
						if(ExecuteAssPryFacility==true){
							appo.ClickPrAssignPrimaryFacilityBtn();
							logger.info("Clicked on Primary facility Button : " + ExecuteAssPryFacility);

							// Switch to child window
							appo.switchToWindowByIndex(1);
							Thread.sleep(GlobalVar.threadSleep);

							// Switch to parent window
							appo.switchToWindowByIndex(0);

							// Switch to child window
							appo.switchToWindowByIndex(1);

							/*// Enter primary facility name
							sheet.getRow(rownum).getCell(55).setCellType(Cell.CELL_TYPE_STRING);
							String APFFacilityName = sheet.getRow(rownum).getCell(55).getStringCellValue();
							apfpo.EnterPryFacilityName(APFFacilityName);*/

							// Enter Primary facility tax id(Plz don't delete)
							APFTaxID =GenericUtils.cellValue(sheet, rownum, 55);
							if(APFTaxID!="No"){
								apfpo.EnterPryFacilityTaxId(APFTaxID);
								logger.info("Primary Facility TaxID : " + APFTaxID);
							}
							// Click search facility
							apfpo.ClickPrySearchFacility();

							//Add provider page, provider primary facility name

							// To locate table
							WebElement PrPrimaryFacilityName = GlobalVar.Driver
									.findElement(By.xpath(".//*[@id='content_inner']/div[4]/div"));
							// To locate rows of table
							List<WebElement> rowsTable3 = PrPrimaryFacilityName
									.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[2]"));
							// To calculate no of rows in table
							int rowscount3 = rowsTable3.size();
							// Loop will execute till the last row of table
							for (int row = 0; row < rowscount3; row++) {
								// To locate columns(cells) of that specific row
								List<WebElement> columnsRow3 = rowsTable3.get(row)
										.findElements(By.xpath(".//*[@id='grdAccountList_lblFacilityName_0']"));
								// To calculate no of columns(cells) in that
								// specific row
								int colscount3 = columnsRow3.size();
								// Loop will execute till the last cell of that
								// specific row
								for (int col = 0; col < colscount3; col++) {
									// To retrieve text from specific cell
									PRPrimaryFacilityName= columnsRow3.get(col).getText();
									logger.info("Primary Facility Name :" + PRPrimaryFacilityName);

								}
							}

							// Click link facility name
							apfpo.ClickPryFacilityName();
						}
						// Switch to parent window
						appo.switchToWindowByIndex(0);

						// Click on Secondary Facility button						
						String AssignSeconFacilityYN=GenericUtils.cellValue(sheet, rownum, 56).toString().toUpperCase();
						boolean ExecuteSeconFacility=AssignSeconFacilityYN.equals("YES");
						if(ExecuteSeconFacility==true){
							appo.ClickPrAssignSecondaryFacilityBtn();
							logger.info("Clicked on Secondary facility Button : " + ExecuteSeconFacility);

							// Switch to child window
							appo.switchToWindowByIndex(1);

							/*// Enter secondary facility name
							sheet.getRow(rownum).getCell(57).setCellType(Cell.CELL_TYPE_STRING);
							String ASFFacilityName = sheet.getRow(rownum).getCell(57).getStringCellValue();
							asfpo.EnterSecFacilityName(ASFFacilityName);*/

							// Enter secondary facility tax id
							ASFTaxID =GenericUtils.cellValue(sheet, rownum, 57);
							if(ASFTaxID!="No"){
								asfpo.EnterPrSecFacilityTaxId(ASFTaxID);
								logger.info("Assign Secondary Facility, TaxID : " + ASFTaxID);
							}
							// Click on search facility button
							asfpo.ClickPrSecSearchFacility();

							//Add provider page, provider secondary facility name

							// To locate table
							WebElement PrSecondaryFacilityName = GlobalVar.Driver
									.findElement(By.xpath(".//*[@id='content_inner']/div[4]/div"));
							// To locate rows of table
							List<WebElement> rowsTable4 = PrSecondaryFacilityName
									.findElements(By.xpath(".//*[@id='grdAccountList']/tbody/tr[3]/td[2]"));
							// To calculate no of rows in table
							int rowscount4 = rowsTable4.size();
							// Loop will execute till the last row of table
							for (int row = 0; row < rowscount4; row++) {
								// To locate columns(cells) of that specific row
								List<WebElement> columnsRow4 = rowsTable4.get(row)
										.findElements(By.xpath(".//*[@id='grdAccountList_lblFacilityName_0']"));
								// To calculate no of columns(cells) in that
								// specific row
								int colscount4 = columnsRow4.size();
								// Loop will execute till the last cell of that
								// specific row
								for (int col = 0; col < colscount4; col++) {
									// To retrieve text from specific cell
									PRSecondaryFacilityName= columnsRow4.get(col).getText();
									logger.info("Assign Secondary Facility,Facility Name : " + PRSecondaryFacilityName);

								}
							}

							// Click on secondary facility link faicility name
							asfpo.ClickPrSecLnkFacilityName();

						}
						// Switch to parent window
						appo.switchToWindowByIndex(0);

						// Click on Assign Committee
						String AssignCommitteeYN=GenericUtils.cellValue(sheet, rownum, 58).toString().toUpperCase();
						boolean ExecuteAssignComm=AssignCommitteeYN.equals("YES");
						if(ExecuteAssignComm==true){
							appo.ClickPrAssignCommitteeBtn();
							logger.info("Clicked on assign committee Button : " + ExecuteAssignComm);

							// Switch to child window
							appo.switchToWindowByIndex(1);

							// Enter committee name							
							ACCommitteeName = GenericUtils.cellValue(sheet, rownum, 59);
							if(ACCommitteeName!="No"){
								acpo.EnterCommitteeName(ACCommitteeName);
								logger.info("Assign Committee : " + ACCommitteeName);
							}
							// Click on search committee button
							acpo.ClickSearchComm();

							// Click select check box committee name
							acpo.ClickLnkComm();

							// Click assign committee
							acpo.ClickAssignCommittee();

						}

						// Switch to parent window
						appo.switchToWindowByIndex(0);

						// Click on add call log button						
						String AddCallLogYN=GenericUtils.cellValue(sheet, rownum, 60).toString().toUpperCase();
						boolean ExecuteAddCall=AddCallLogYN.equals("YES");
						if(ExecuteAddCall==true){
							appo.ClickPrAddCallLogBtn();
							logger.info("Clicked on Add call log Button : " + ExecuteAddCall);

							// Switch to child window
							appo.switchToWindowByIndex(1);
							Thread.sleep(GlobalVar.threadSleep);

							//Switch to parent window
							appo.switchToWindowByIndex(0);

							//Switch to child window
							appo.switchToWindowByIndex(1);

							// Add call log page,select Call log tracker reason type
							String ACLReasonType = GenericUtils.cellValue(sheet, rownum, 61);
							aclpo.SctReasonType(ACLReasonType);
							logger.info("Call Log Tracker, Reason Type : " + ACLReasonType);

							Thread.sleep(GlobalVar.threadSleep);
							// Add call log page,contact mode							
							String ACLContactMode = GenericUtils.cellValue(sheet, rownum, 62);
							aclpo.SctContactMode(ACLContactMode);
							logger.info("Call Log Tracker, Contact Mode : " + ACLContactMode);

							// Enter communication date
							aclpo.ClickCommDate();

							GlobalVar.Driver.findElement(By.xpath("//td[text()='17']")).click();

							// Enter created date
							aclpo.ClickCreatedDate();
							GlobalVar.Driver.findElement(By.xpath("//td[text()='17']")).click();

							// Enter call log tracker note							
							String ACLNotes = GenericUtils.cellValue(sheet, rownum, 63);
							if(ACLNotes!="No"){
								aclpo.EnterNotes(ACLNotes);
								logger.info("Call Log Tracker, Notes : " + ACLNotes);
							}
							// Add call log page, select contact type
							String ACLContactType = GenericUtils.cellValue(sheet, rownum, 64);
							aclpo.SctContactType(ACLContactType);
							logger.info("Call Log Tracker, Contact Type : " + ACLContactType);

							// Call log tracker click check box							
							String CallLogChkBoxYN=GenericUtils.cellValue(sheet, rownum, 65).toString().toUpperCase();
							boolean ExecuteCallLog=CallLogChkBoxYN.equals("YES");
							if(ExecuteCallLog==true){
								aclpo.ClickFollowUp();
								logger.info("Clicked on follow up check box : " + ExecuteCallLog);
							}

							// Enter follow up date
							aclpo.ClickFollowupdate();
							GlobalVar.Driver.findElement(By.xpath("//td[text()='17']")).click();

							// Enter follow up note							
							String ACLFollowUpNotes = GenericUtils.cellValue(sheet, rownum, 66);
							aclpo.EnterFollowupNotes(ACLFollowUpNotes);
							logger.info("Call Log Tracker, Follow Up Notes : " + ACLFollowUpNotes);

							// Call log tracker,click on submit button
							aclpo.ClickCallLogSubmit();

						}

						// Switch to parent window
						appo.switchToWindowByIndex(0);

						// Enter healthec health plan(Plz don't delete)
						/*
						 * sheet.getRow(rownum).getCell(67).setCellType(Cell.
						 * CELL_TYPE_STRING); String PRHealthECHealthPlanName =
						 * sheet.getRow(rownum).getCell(67).getStringCellValue();
						 * appo.EnterHealthPlanName(PRHealthECHealthPlanName);
						 */

						// Select healthec health plan
						// appo.ClickHealthPlan();

						// click on transaction type(Plz don't delete)
						/*
						 * sheet.getRow(rownum).getCell(68).setCellType(Cell.
						 * CELL_TYPE_STRING); String PRTransactionType =
						 * sheet.getRow(rownum).getCell(68).getStringCellValue();
						 * appo.SctDdlTransactionType(PRTransactionType);
						 */

						// Enter lagacy id(Plz don't delete)
						/*
						 * sheet.getRow(rownum).getCell(69).setCellType(Cell.
						 * CELL_TYPE_STRING); String PRLegacyID =
						 * sheet.getRow(rownum).getCell(69).getStringCellValue();
						 * appo.EnterLegacyID(PRLegacyID);
						 */

						// Click on add button
						// appo.ClickAddButton();

						// Click on save provider
						appo.ClickPrSaveButton();
						logger.info("Press Save Provider Button To Save All Provider Information");

						//Enter Provider last name to search
						ppo.EnterProviderLastName(LastName);

						//Provider page ,click searh provider button				
						ppo.ClickSearchProvider();

						//Provider page,provider last name present or not
						confirm =ppo.VerifyProviderLastNamePresentorNot();
						

					}
					catch(Exception e){
						logger.info("Add Provider Exception :" + e.getMessage().toString());
						ScreenShotPath = ReportGenerator.setLog("Add Provider failed", e.getMessage().toString(),
								"Add Provider TC_383_Faill");
						String image = TC_383_AddProvider.addScreenCapture(ScreenShotPath);
						TC_383_AddProvider.log(LogStatus.FAIL, image);
					}

					TC_383_AddProvider.log(LogStatus.INFO, "Region Code : "  + ARRegionCode + " || Region Name : " 
							+ PRRegionName + " || Primary TaxID : " + APATaxID + " || Primary Account Name : " 
							+ PRPrimaryAccountName + " || Secondary TaxID : " + ASATaxID + " || Secondary Account Name : "
							+ PRSecondaryAccountName + " || Primary Facility TaxID : " + APFTaxID + " || Primary Facility Name : " 
							+ PRPrimaryFacilityName + " || Secondary Facility TaxID : " + ASFTaxID + " || Secondary Facility Name : " 
							+ PRSecondaryFacilityName + " || Assign Committee : " + ACCommitteeName );
					
					if(confirm==true){
						logger.info("Provider details have been created successfully");
						TC_383_AddProvider.log(LogStatus.PASS, "Provider details have been created successfully");
					}
					else{
						logger.info("Provider details creation failed");
						TC_383_AddProvider.log(LogStatus.FAIL, "Provider details creation failed");
					}

					logger.info("Multi Test Data :" + multitestdata + " Execute : " + Execute);
					if ((Boolean.FALSE.equals(multitestdata)) && (Boolean.TRUE.equals(Execute))) {
						logger.info("Multi Test Data : " + (Boolean.FALSE.equals(multitestdata)) + " Execute Y/N : "
								+ (Boolean.TRUE.equals(Execute)));
						break;
					}

				}

			}
			//Close the Workbook
			workbook.close();

			GlobalVar.report.endTest(TC_383_AddProvider);

		}

		catch(Exception e){
			e.printStackTrace();
			ScreenShotPath = ReportGenerator.setLog("Add Provider failed", e.getMessage().toString(),
					"Add Provider TC_383_Fail");
			String image = TC_383_AddProvider.addScreenCapture(ScreenShotPath);
			TC_383_AddProvider.log(LogStatus.FAIL, image);
			GlobalVar.report.endTest(TC_383_AddProvider);
		}

	}

}
