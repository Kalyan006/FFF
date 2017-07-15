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

public class TC403DeleteFile {

	public static Logger addlog = Logger.getLogger(TC403DeleteFile.class.getName());
	static ExtentTest TC_403_DeleteFile;
	public static String ScreenShotPath;
	public static String TestName = "";
	public static int PDCount;
	public static String filepath = GlobalVar.TestDataUploadFileTC398FilePath;
	public static String FileDownloadPath= "C:\\Users\\Ravi.Yadav\\Downloads";
	//public static String FileDownloadPath= "D:\\Downloads";
	
	@BeforeMethod
	public static void FileDeletePreCondition() throws Throwable {
		TC_403_DeleteFile = GlobalVar.report.startTest("TC_403_DeleteFile",
				"Validate the user able to Delete the file");
		
		//Run the script for FileUplaod PreCondtion
		//TC398UploadFile.FileUploadPreCondition();
		//TC398UploadFile.UploadFile();
		// Logged into the PD Application
					/*Thread.sleep(GlobalVar.threadSleep);
					GlobalVar.Modules = "PD";
					readExcelFile.getUsernamePassword(GlobalVar.TestDataFilePath, GlobalVar.UserDetailsTestData,
							GlobalVar.SheetName, GlobalVar.Client, GlobalVar.Modules);
					Login.login1(GlobalVar.LoginUsername, GlobalVar.LoginPassword);
					TC_403_DeleteFile.log(LogStatus.INFO,
							"Login to PD Application - Login name :" + GlobalVar.LoginUsername + " Password :" + GlobalVar.LoginPassword);
					logger.info("Logged in to the PD Application");
		
		*/
	}

	@SuppressWarnings({ "deprecation", "resource" })
	@Test
	public static void deletefile() throws Throwable

	{
		Logger logger = addlog;
		UploadFileObjects UFO = PageFactory.initElements(GlobalVar.Driver, UploadFileObjects.class);
		ProviderAdminPageObjects PAP = PageFactory.initElements(GlobalVar.Driver, ProviderAdminPageObjects.class);
		IntermediatePageObjects IntMedPage = PageFactory.initElements(GlobalVar.Driver, IntermediatePageObjects.class);
		GenericUtils genutil = PageFactory.initElements(GlobalVar.Driver, GenericUtils.class);
		//HeaderPageObjects HPO = PageFactory.initElements(GlobalVar.Driver, HeaderPageObjects.class);
		boolean multitestdata = Boolean.parseBoolean(GlobalVar.multpletestdata);
		logger.info("FileDelete  test execution starts.");
		TC_403_DeleteFile.log(LogStatus.INFO, "FileDelete test execution starts.");
		try{
			
			// Click on Provider Admin Home Tab
			Thread.sleep(GlobalVar.threadSleep);
			IntMedPage.ClickProviderAdmin();
			logger.info("Click on Provider Admin Home Tab.");

			// click on Document Admin Menu
			Thread.sleep(GlobalVar.threadSleep);
			logger.info("Click On Document Admin Menu.");
			PAP.ClickDocumentadminMenu();
			TC_403_DeleteFile.log(LogStatus.INFO, "Click On Document Admin Menu.");
	
			
			// Click On Upload file Sub Menu Under Document Admin Menu
			Thread.sleep(GlobalVar.threadSleep);
			logger.info("Click on Uploadfile Sub Menu Under Document Admin Menu.");
			PAP.ClickUploadFile();
			TC_403_DeleteFile.log(LogStatus.INFO, "Click On UploadFile Sub Menu Under Document Admin Menu.");
			
			// Take The Data From Excel Sheet
						String filepath = GlobalVar.TestDataUploadFileTC398FilePath;
						logger.info("File Path : " + filepath);
						TC_403_DeleteFile.log(LogStatus.INFO, "File Path : " + filepath);
						int rows;
						String Documentcategory;
						logger.info("Multiple Test Data : " + multitestdata);
						File file = new File(filepath);
						FileInputStream fis = new FileInputStream(file);
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
							TestName = "TC_402_DownloadFile" + "_" + "row: " + rownum;
							sheet.getRow(rownum).getCell(cols - 1).setCellType(Cell.CELL_TYPE_STRING);
							String executeyn = sheet.getRow(rownum).getCell(cols - 1).getStringCellValue();
							String ExecuteYN = executeyn.toUpperCase().trim();
							Boolean Execute = ExecuteYN.equals("YES");
							logger.info("Test Data Execute Y/N : " + ExecuteYN + " Execute the Row " + rownum + " Execute = "
									+ Execute + "Test Type : " + GlobalVar.TestType);

							if (Execute == true || "BAT".equals(GlobalVar.TestType)) {
								try {
									// Pick the File Name
									sheet.getRow(rownum).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
									String Filename = sheet.getRow(rownum).getCell(0).getStringCellValue();
									logger.info(Filename);
									// Search the Document category from Excel
									sheet.getRow(rownum).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
									Documentcategory = sheet.getRow(rownum).getCell(1).getStringCellValue();
									logger.info(Documentcategory);
									
									// Search the document based on the category
									UFO.SelectDocumentCatagory(Documentcategory);
									UFO.ClickbtnSearchDocument();
									
									try {
										if (UFO.verifyRecordPresentorNot() == false) {
											if (UFO.LblPresentOrNot() == true) {
												PDCount = genutil.CountPdUploadRecord(UFO.uploaddoc);
												
											}
										}

										else {
											PDCount = 0;
										}
									} catch (Exception e) {
										logger.info("Exception Message :" + e.getMessage());

									}
									
									if(PDCount>0){
										//Delete the particular file.
										logger.info("Click on Delete Button");
										UFO.Clickdeletebutton(Filename);
										logger.info("File Deleted : " + "File Name - " + Filename);
										TC_403_DeleteFile.log(LogStatus.INFO, "File Deleted : " + "File Name - " + Filename);
										Thread.sleep(GlobalVar.threadSleep);
										//Verify File Exits Or Not
										boolean status = UFO.verifyFileIsPresentOrNot(Filename);
										if(status==false){
											TC_403_DeleteFile.log(LogStatus.PASS, "File Not Exits");
											
										}else{
											TC_403_DeleteFile.log(LogStatus.FAIL, "File Exits");
										}
										
										//TC_403_DeleteFile.l
										//UFO.verifyFileIsNotPresent(Filename);
									
										
										}
										
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
		

			GlobalVar.report.endTest(TC_403_DeleteFile);
		
		}catch (Throwable t) {

			logger.info("DeleteFile Exception :" + t.getMessage().toString());
			// ScreenShotPath = GenericUtils.CaptureScreenshot(GlobalVar.Driver,
			// "TC_403_DeleteFile" + LocalDateTime.now().getHour() +
			// LocalDateTime.now().getMinute() +
			// LocalDateTime.now().getSecond());
			ScreenShotPath = ReportGenerator.setLog("DeleteFile Failed", t.getMessage().toString(),
					"Delete file_TC402_Fail");
			String image = TC_403_DeleteFile.addScreenCapture(ScreenShotPath);
			TC_403_DeleteFile.log(LogStatus.FAIL, image);
			GlobalVar.report.endTest(TC_403_DeleteFile);

		}
		
	}
}
