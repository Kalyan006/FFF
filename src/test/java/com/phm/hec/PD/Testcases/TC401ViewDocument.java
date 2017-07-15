package com.phm.hec.PD.Testcases;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.phm.hec.PD.Objects.ProviderAdminPageObjects;
import com.phm.hec.PD.Objects.UploadFileObjects;
import com.phm.hec.pageObjects.IntermediatePageObjects;
import com.phm.hec.utility.GenericUtils;
import com.phm.hec.utility.GlobalVar;
import com.phm.hec.utility.ReportGenerator;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC401ViewDocument {
	public static Logger addlog = Logger.getLogger(TC402DownloadFile.class.getName());
	static ExtentTest TC_401_ViewDocument;
	public static String ScreenShotPath;
	public static String TestName = "";
	public static int PDCount;
	public static String filepath = GlobalVar.TestDataUploadFileTC398FilePath;
	
	@BeforeMethod
	public static void ViewUploadDocPreCondition() throws Throwable {
		TC_401_ViewDocument = GlobalVar.report.startTest("TC_401_ViewDocument",
				"Validate the user able to View Uploaded Document ");
		
		//Run the script for FileUplaod PreCondtion
		//TC398UploadFile.FileUploadPreCondition();
		//TC398UploadFile.UploadFile();
		// Logged into the PD Application
					/*Thread.sleep(GlobalVar.threadSleep);
					GlobalVar.Modules = "PD";
					readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
							GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
					Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword);
					TC_401_ViewDocument.log(LogStatus.INFO,
							"Login to PD Application - Login name :" + GlobalVar.LoginUsername + " Password :" + GlobalVar.LoginPassword);
					logger.info("Logged in to the PD Application");
		*/
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public static void viewUploadfile() throws Throwable

	{
		Logger logger = addlog;
		UploadFileObjects UFO = PageFactory.initElements(GlobalVar.Driver, UploadFileObjects.class);
		ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
		IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
		GenericUtils genutil = PageFactory.initElements(GlobalVar.Driver, GenericUtils.class);
		//HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
		boolean multitestdata = Boolean.parseBoolean(GlobalVar.multpletestdata);
		logger.info("FileDownload  test execution starts.");
		TC_401_ViewDocument.log(LogStatus.INFO, "View Uploaded Document test execution starts.");
		try{
			
			// Click on Provider Admin Home Tab
			Thread.sleep(GlobalVar.threadSleep);
			IntMedPage.ClickProviderAdmin();
			logger.info("Click on Provider Admin Home Tab.");

			// click on Document Admin Menu
			Thread.sleep(GlobalVar.threadSleep);
			logger.info("Click On Document Admin Menu.");
			PAP.ClickDocumentadminMenu();
			TC_401_ViewDocument.log(LogStatus.INFO, "Click On Document Admin Menu.");
	
			
			// Click On Upload file Sub Menu Under Document Admin Menu
			Thread.sleep(GlobalVar.threadSleep);
			logger.info("Click on Uploadfile Sub Menu Under Document Admin Menu.");
			PAP.ClickUploadFile();
			TC_401_ViewDocument.log(LogStatus.INFO, "Click On UploadFile Sub Menu Under Document Admin Menu.");
			
			// Take The Data From Excel Sheet
						String filepath = GlobalVar.TestDataUploadFileTC398FilePath;
						logger.info("File Path : " + filepath);
						TC_401_ViewDocument.log(LogStatus.INFO, "File Path : " + filepath);
						int rows;
						String Documentcategory;
						logger.info("Multiple Test Data : " + multitestdata);
						File file = new File(filepath);
						FileInputStream fis = new FileInputStream(file);
						@SuppressWarnings("resource")
						XSSFWorkbook workbook = new XSSFWorkbook(fis);
						XSSFSheet sheet = workbook.getSheet("PD_UploadFile");
						logger.info("Test Type " + GlobalVar.TestType +     " multitestdata " + multitestdata);

						// && (!"FALSE".equals(GlobalVar.multpletestdata))
						if (!"BAT".equals(GlobalVar.TestType)) {
							rows = sheet.getLastRowNum() + 1;
						} else {
							rows = 2;
						}

						int cols = sheet.getRow(1).getLastCellNum();
						logger.info("Test Type : " + GlobalVar.TestType + " || Total Rows :" + rows + "|| Total Columns : " + cols);

						for (int rownum = 1; rownum < rows; rownum++) {
							TestName = "TC_401_ViewDocument" + "_" + "row: " + rownum;
							sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
							String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
							String ExecuteYN = executeyn.toUpperCase().trim();
							Boolean Execute = ExecuteYN.equals("YES");
							logger.info("Test Data Execute Y/N : " + ExecuteYN + " Execute the Row " + rownum + " Execute = "
									+ Execute + "Test Type : " + GlobalVar.TestType);

							if (Execute == true || "BAT".equals(GlobalVar.TestType)) {
								try {
									// Pick the File Name from Excel
									sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
									String Filename = sheet.getRow(rownum).getCell(0).getStringCellValue();
									logger.info(Filename);
									// Search the Document category from Excel
									sheet.getRow(rownum).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
									Documentcategory = sheet.getRow(rownum).getCell(1).getStringCellValue();
									logger.info(Documentcategory);
									
									// View Uploaded Document based on Search document category
									UFO.SelectDocumentCatagory(Documentcategory);
									UFO.ClickbtnSearchDocument();
									logger.info("View Uploaded Document Based On : " +Documentcategory);
									TC_401_ViewDocument.log(LogStatus.INFO, "View Uploaded Document Based on : " + Documentcategory);
									
									try {
										if (UFO.verifyRecordPresentorNot() == false) {
											if (UFO.LblPresentOrNot() == true) {
												PDCount = genutil.CountPdUploadRecord(UFO.uploaddoc);
												logger.info("Total Record Count Based On Document category  : "+Documentcategory+ " = " +PDCount );
												TC_401_ViewDocument.log(LogStatus.INFO, "Total Record Count based on Document Category : "+Documentcategory+ " = " +PDCount);
												
											}
										}

										else {
											PDCount = 0;
										}
									} catch (Exception e) {
										logger.info("Exception Message :" + e.getMessage());

									}
									//click on clear button
									UFO.ClickclearBtn();
									//View Uploaded Document based On Filename
									UFO.Enterfilename(Filename);
									logger.info("View Uploaded document based On File Name :"+Filename);
									TC_401_ViewDocument.log(LogStatus.INFO, "View Uploaded document based On File Name : "+Filename);
									UFO.ClickbtnSearchDocument();
									try {
										if (UFO.verifyRecordPresentorNot() == false) {
											if (UFO.LblPresentOrNot() == true) {
												PDCount = genutil.CountPdUploadRecord(UFO.uploaddoc);
												logger.info("Total Record Count Based On File Name : "+Filename+ " = " +PDCount );
												TC_401_ViewDocument.log(LogStatus.INFO, "Total Record Count based on File Name : "+Filename+ " = " +PDCount);
												
											}
										}

										else {
											PDCount = 0;
										}
									} catch (Exception e) {
										logger.info("Exception Message :" + e.getMessage());

									}
									//click on Clear button
									UFO.ClickclearBtn();
									}
								catch (Exception e) {
									logger.info("Exception Message :" + e.getMessage());
									
								}
							}
							logger.info("Multi Test Data :" + multitestdata + " Execute : " + Execute);
							if ((Boolean.FALSE.equals(multitestdata)) && (Boolean.TRUE.equals(Execute))) {
								logger.info("Multi Test Data : " + (Boolean.FALSE.equals(multitestdata)) + " Execute Y/N : "
										+ (Boolean.TRUE.equals(Execute)));
								break;
							}
						}
		

			GlobalVar.report.endTest(TC_401_ViewDocument);
		
		}catch (Throwable t) {

			logger.info("ViewDocument File Exception :" + t.getMessage().toString());
			// ScreenShotPath = GenericUtils.CaptureScreenshot(GlobalVar.Driver,
			// "TC_401_ViewDocument" + LocalDateTime.now().getHour() +
			// LocalDateTime.now().getMinute() +
			// LocalDateTime.now().getSecond());
			ScreenShotPath = ReportGenerator.setLog("ViewDocument File Failed", t.getMessage().toString(),
					"ViewDocument file_TC401_Fail");
			String image = TC_401_ViewDocument.addScreenCapture(ScreenShotPath);
			TC_401_ViewDocument.log(LogStatus.FAIL, image);
			GlobalVar.report.endTest(TC_401_ViewDocument);

		}
		
	}

}
