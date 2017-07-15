package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.*;
import com.phm.hec.utility.GenericUtils;
import com.phm.hec.utility.GlobalVar;

public class AddProviderPageObjects {
	
	//Add Provider page select check box to enter organization details
	@FindBy(id = "ContentPlaceHolder1_chkBoxOrganization")
	public WebElement PrChkBoxOrganization;

	public void ClickPrOrganizationChkBox() {
		PrChkBoxOrganization.click();
	}

	// Add Provider page provider category ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlProviderTypeId")
	public WebElement PrDdlProviderCategory;

	public void SctProviderCategory(String vtext) {
		Select sct=new Select(PrDdlProviderCategory);
		List<WebElement> listofoptions=sct.getOptions();
		for(WebElement option: listofoptions){
			String PRCategory=option.getText().trim();
			if(PRCategory.contains(vtext)){
				sct.selectByVisibleText(vtext);
				break;
			}
		}
		//GenericUtils.selectDdlByVisibleText(DdlProviderCategory, prcategory);
	}

	// Add Provider page provider last name text field.
	@FindBy(id = "ContentPlaceHolder1_txtLastName")
	public WebElement TxtPrLastName;

	public void EnterPrLastName(String lastname) {
		TxtPrLastName.sendKeys(lastname);
	}

	// Add Provider page provider middle name text field.
	@FindBy(id = "ContentPlaceHolder1_txtMiddleName")
	public WebElement TxtPrMiddleName;

	public void EnterPrMiddleName(String middlename) {
		TxtPrMiddleName.sendKeys(middlename);
	}

	// Add Provider page provider first name text field.
	@FindBy(id = "ContentPlaceHolder1_txtFirstName")
	public WebElement TxtPrFirstName;

	public void EnterPrFirstName(String FirstName) {
		TxtPrFirstName.sendKeys(FirstName);
	}

	// Add Provider page provider title text field.
	@FindBy(id = "ContentPlaceHolder1_txtTitle")
	public WebElement PrTxtTitle;

	public void EnterPrTitle(String title) {
		PrTxtTitle.sendKeys(title);
	}

	// Add Provider page provider Degree text field.
	@FindBy(id = "ContentPlaceHolder1_txtDegree")
	public WebElement TxtPrDegree;

	public void EnterPrDegree(String degree) {
		TxtPrDegree.sendKeys(degree);
	}

	// Add Provider page provider Suffix text field.
	@FindBy(id = "ContentPlaceHolder1_txtSuffix")
	public WebElement TxtPrSuffix;

	public void EnterPrSuffix(String suffix) {
		TxtPrSuffix.sendKeys(suffix);
	}

	// Add Provider page Provider Identification npi text field.
	@FindBy(id = "ContentPlaceHolder1_txtNPI")
	public WebElement TxtPrNPI;

	public void EnterPrNpi(String npi) {
		TxtPrNPI.sendKeys(npi);
	}

	// Add Provider page Provider Identification TaxId text field.
	@FindBy(id = "ContentPlaceHolder1_txtTaxId")
	public WebElement TxtPrTaxId;

	public void EnterPrTaxId(String taxid) {
		TxtPrTaxId.sendKeys(taxid);
	}

	// Add Provider page Provider Identification State-Assigned ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtUMPI")
	public WebElement TxtPrStateAssignedID;

	public void EnterPrStateAssignedId(String assignedid) {
		TxtPrStateAssignedID.sendKeys(assignedid);
	}

	// Add Provider page Provider Identification Internal ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtInternalId")
	public WebElement TxtPrInternalId;

	public void EnterPrInternalId(String internalid) {
		TxtPrInternalId.sendKeys(internalid);
	}

	// Add Provider page Provider Identification Organization Assigned ID text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txtOrganizationId")
	public WebElement TxtPrOrganizationAssignedId;

	public void EnterPrOrganizationAssignedId(String AssignedId) {
		TxtPrOrganizationAssignedId.sendKeys(AssignedId);
	}

	// Add Provider page Provider Identification State License ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtStateLicense")
	public WebElement TxtPrStateLicenseId;

	public void EnterPrStateLicenseId(String StateLicenseId) {
		TxtPrStateLicenseId.sendKeys(StateLicenseId);
	}

	// Add Provider page Provider Identification SSN text field.
	@FindBy(id = "ContentPlaceHolder1_txtSSN")
	public WebElement TxtPrSSN;

	public void EnterPrSSN(String SSN) {
		TxtPrSSN.sendKeys(SSN);
	}

	// Add Provider page Provider Identification Sub ID/Site ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtSiteId")
	public WebElement TxtPrSubIdSiteId;

	public void EnterPrSubIdSiteId(String SubIdSiteId) {
		TxtPrSubIdSiteId.sendKeys(SubIdSiteId);
	}

	// Add Provider page Provider Identification status ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlProStatus")
	public WebElement DdlPrProStatus;

	public void SctPrDdlProStatus(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlPrProStatus, vtext);
	}

	// Add Provider page Taxonomy & Description Taxonomy Code Set ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlTaxonomyCodeSet")
	public WebElement DdlPrTaxonomyCodeSet;

	public void SctPrDdlTaxonomyCodeSet(String vtext) throws InterruptedException{
		Select sct = new Select(DdlPrTaxonomyCodeSet);
		List<WebElement> listofoptions = sct.getOptions();
		for (WebElement option : listofoptions) {
			String TaxonomyCodeSet = option.getText();
			String filename = vtext;
			String subString = null;
			int iend = filename.indexOf("-");
			if (iend != -1)
				subString = filename.substring(0, iend);
			String truncated = subString.replaceAll("[^\\p{Print}]", "");
			String str = truncated.trim();
			if (TaxonomyCodeSet.contains(str)) {
				sct.selectByValue(str);
				break;
			}
		}
	}

	// Add Provider page Taxonomy & Description Taxonomy Code ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlTaxonomyCodeSub")
	public WebElement DdlPrTaxonomyCode;

	public void SctPrDdlTaxonomyCode(String vtext) throws InterruptedException {
			Select sct = new Select(DdlPrTaxonomyCode);
			List<WebElement> listofoptions = sct.getOptions();
			for (WebElement option : listofoptions) {
				String TaxonomyCode = option.getText();
				String filename = vtext;
				String subString = null;
				int iend = filename.indexOf("-");
				if (iend != -1)
					subString = filename.substring(0, iend);
				String truncated = subString.replaceAll("[^\\p{Print}]", "");
				String str = truncated.trim();
				if (TaxonomyCode.contains(str)) {
					sct.selectByValue(str);
					break;
				}
			}
	}

	// Add Provider page Taxonomy & Description ProviderType ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlProviderType")
	public WebElement DdlProviderType;

	public void SctDdlProviderType(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlProviderType, vtext);
	}

	// Add Provider page Provider Contact Information last name text field.
	@FindBy(id = "ContentPlaceHolder1_txtContactLastName")
	public WebElement TxtPCILastName;

	public void EnterPCILastName(String LastName) {
		TxtPCILastName.sendKeys(LastName);
	}

	// Add Provider page Provider Contact Information First name text field.
	@FindBy(id = "ContentPlaceHolder1_txtContactFirstName")
	public WebElement TxtPCIFirstName;

	public void EnterPCIFirstName(String FirstName) {
		TxtPCIFirstName.sendKeys(FirstName);
	}

	// Add Provider page Provider Contact Information Email text field.
	@FindBy(id = "ContentPlaceHolder1_txtEmail")
	public WebElement TxtPCIEmail;

	public void EnterPCIEmail(String EmailName) {
		TxtPCIEmail.sendKeys(EmailName);
	}

	// Add Provider page Provider Contact Information Address1 text field.
	@FindBy(id = "ContentPlaceHolder1_txtAddress1")
	public WebElement TxtPCIAddress1;

	public void EnterPCIAddress1(String ProviderAddress1) {
		TxtPCIAddress1.sendKeys(ProviderAddress1);
	}

	// Add Provider page Provider Contact Information Address2 text field.
	@FindBy(id = "ContentPlaceHolder1_txtAddress2")
	public WebElement TxtPCIAddress2;

	public void EnterPCIAddress2(String ProviderAddress2) {
		TxtPCIAddress2.sendKeys(ProviderAddress2);
	}

	// Add Provider page Provider Contact Information City text field.
	@FindBy(id = "ContentPlaceHolder1_txtCity")
	public WebElement TxtPCICity;

	public void EnterPCICity(String ProviderCity) {
		TxtPCICity.sendKeys(ProviderCity);
	}

	// Add Provider page Provider Contact Information State ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlState")
	public WebElement DdlPCIState;

	public void SctDdlPCIState(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlPCIState, vtext);
	}

	// Add Provider page Provider Contact Information zip text field.
	@FindBy(id = "ContentPlaceHolder1_txtZip")
	public WebElement TxtPCIZip;

	public void EnterPCIZip(String ProviderZip) {
		TxtPCIZip.sendKeys(ProviderZip);
	}

	// Add Provider page Provider Contact Information Fax text field.
	@FindBy(id = "ContentPlaceHolder1_txtFax")
	public WebElement TxtPCIFax;

	public void EnterPCIFax(String ProviderFax) {
		TxtPCIFax.sendKeys(ProviderFax);
	}

	// Add Provider page Provider Contact Information Phone text field.
	@FindBy(id = "ContentPlaceHolder1_txtPhone")
	public WebElement TxtPCIPhone;

	public void EnterPCIPhone(String ProviderPhone) {
		TxtPCIPhone.sendKeys(ProviderPhone);
	}

	// Add Provider page Provider Other Information employed ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlEmployed")
	public WebElement DdlPOIEmployed;

	public void SctDdlPOIEmployed(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlPOIEmployed, vtext);
	}

	// Add Provider page Provider Other Information Prefered communication ddl
	// field.
	@FindBy(id = "ContentPlaceHolder1_ddlPreferedcommunication")
	public WebElement DdlPOIcommunication;

	public void SctDdlPOIcommunication(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlPOIcommunication, vtext);
	}

	// Add Provider page Provider Other Information Hospital affiliation1 text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txthospaff1")
	public WebElement TxtPOIhospaff1;

	public void EnterPOIhospaff1(String Providerhospaff1) {
		TxtPOIhospaff1.sendKeys(Providerhospaff1);
	}

	// Add Provider page Provider Other Information Hospital affiliation2 text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txthospaff2")
	public WebElement TxtPOIhospaff2;

	public void EnterPOIhospaff2(String Providerhospaff2) {
		TxtPOIhospaff2.sendKeys(Providerhospaff2);
	}

	// Add Provider page Provider Other Information Hospital affiliation3 text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txthospaff3")
	public WebElement TxtPOIhospaff3;

	public void EnterPOIhospaff3(String Providerhospaff3) {
		TxtPOIhospaff3.sendKeys(Providerhospaff3);
	}

	// Add Provider page Provider Other Information Hospital affiliation4 text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txthospaff4")
	public WebElement TxtPOIhospaff4;

	public void EnterPOIhospaff4(String Providerhospaff4) {
		TxtPOIhospaff4.sendKeys(Providerhospaff4);
	}

	// Add Provider page Payment Information Merchant Number text field.
	@FindBy(id = "ContentPlaceHolder1_txtMerchantID")
	public WebElement TxtCDPGMerchantId;

	public void EnterCDPGMerchantId(String MerchantID) {
		TxtCDPGMerchantId.sendKeys(MerchantID);
	}

	// Add Provider page Credit / Debit Payment Gateway Operator/user id text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txtOperatorORUserID")
	public WebElement TxtCDPGOperatorOrUserId;

	public void EnterCDPGOperatorOrUserId(String OperatorId) {
		TxtCDPGOperatorOrUserId.sendKeys(OperatorId);
	}

	// Add Provider page Credit / Debit Payment Gateway Password text field.
	@FindBy(id = "ContentPlaceHolder1_txtPassword")
	public WebElement TxtCDPGPaymentPassword;

	public void EnterCDPGPaymentPassword(String Password) {
		TxtCDPGPaymentPassword.sendKeys(Password);
	}

	// Add Provider page Credit / Debit Payment Gateway DeviceID text field.
	@FindBy(id = "ContentPlaceHolder1_txtDeviceID")
	public WebElement TxtCDPGDeviceID;

	public void EnterCDPGDeviceID(String DeviceID) {
		TxtCDPGDeviceID.sendKeys(DeviceID);
	}

	// Add Provider page Credit / Debit Payment Gateway Registration Key text
	// field.
	@FindBy(id = "ContentPlaceHolder1_txtRegistrationKey")
	public WebElement TxtCDPGRegistrationKey;

	public void EnterCDPGRegistrationKey(String RegisKey) {
		TxtCDPGRegistrationKey.sendKeys(RegisKey);
	}

	// Add Provider page Check Payment Gateway, Gateway ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlGateWay")
	public WebElement DdlCPGGateway;

	public void SctDdlCPGGateway(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlCPGGateway, vtext);
	}

	// Add Provider page Check Payment Gateway, Site id text field.
	@FindBy(id = "ContentPlaceHolder1_txtSite")
	public WebElement TxtCPGSiteId;

	public void EnterCPGSiteId(String SiteId) {
		TxtCPGSiteId.sendKeys(SiteId);
	}

	// Add Provider page Check Payment Gateway, Location ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtLocationID")
	public WebElement TxtCPGLocationID;

	public void EnterCPGLocationId(String LocId) {
		TxtCPGLocationID.sendKeys(LocId);
	}

	// Add Provider page Check Payment Gateway, POS program ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtPOSProgramID")
	public WebElement TxtCPGPOSProgramID;

	public void EnterCPGPOSProgramId(String ProgramID) {
		TxtCPGPOSProgramID.sendKeys(ProgramID);
	}

	// Add Provider page Check Payment Gateway, Lane ID text field.
	@FindBy(id = "ContentPlaceHolder1_txtLaneID")
	public WebElement TxtCPGLaneId;

	public void EnterCPGLaneId(String LaneId) {
		TxtCPGLaneId.sendKeys(LaneId);
	}

	// Add Provider page click on Assign Region button 
	@FindBy(id = "ContentPlaceHolder1_btnAssignRegion")
	public WebElement BtnPrAssignRegion;

	public void ClickPrAssignRegion() {
		BtnPrAssignRegion.click();
	}

	public void switchToWindowByIndex(int index) {
		int count = 0;
		Set<String> windows = GlobalVar.Driver.getWindowHandles();
		for (String window : windows) {
			GlobalVar.Driver.switchTo().window(window);
			if (count == index) {
				System.out.println("Switched into window : " + index);
				break;

			}
			count++;
		}
	}

	// Add provider page select assign region then click on delete button
	@FindBy(id = "ContentPlaceHolder1_grdACOList_ButtonDelete_0")
	public WebElement BtnPrDeleteRegion;

	public void ClickPrDeleteRegion() {
		BtnPrDeleteRegion.click();
	}

	public String rowNo(String value) {
		WebElement table = GlobalVar.Driver.findElement(By.id("tableId"));
		WebElement tbody = table.findElement(By.tagName("tbody"));
		List<WebElement> rows = tbody.findElements(By.tagName("tr"));
		// ArrayList<String> ListOdIds=new ArrayList<>();
		String rowNo = "";
		for (int i = 0; i < rows.size(); i++) {
			WebElement row = tbody.findElement(By.xpath("//table[@id='tableId']/tbody/tr[" + (i + 1) + "]"));
			if (row.getText().trim().contains(value)) {
				rowNo = Integer.toString(i + 1);
				break;
			}

		}

		return rowNo;
	}

	/*
	 * public void GetRegionRowNo(String tablename,String regioncode,int
	 * recordstartrow) { //Grab the table WebElement
	 * table=GlobalVar.Driver.findElement(By.id(tablename)); //Now get all the
	 * tr elements from the table List<WebElement>
	 * allrows=table.findElements(By.tagName(regioncode)); //And iterate over
	 * them,getting the cells for(WebElement row: allrows) { String td = null;
	 * List<WebElement> cells=row.findElements(By.tagName(td)); //Print the
	 * contents of all cell for(WebElement cell:cells) {
	 * System.out.println(cell.getText()); }
	 * 
	 * }
	 */

	// Add Provider page Primary Assign Account button field.
	@FindBy(id = "ContentPlaceHolder1_btnPrimaryAssignAccount")
	public WebElement BtnPrAssignPrimaryAccount;

	public void ClickPrAssignPrimaryAccountBtn() {
		BtnPrAssignPrimaryAccount.click();
	}

	// Add Provider page Secondary Assign Account button field.
	@FindBy(id = "ContentPlaceHolder1_btnSecondaryAssignAccount")
	public WebElement BtnPrAssignSecondaryAccount;

	public void ClickPrAssignSecondaryAccountBtn() {
		BtnPrAssignSecondaryAccount.click();
	}

	// Add Provider page Assign Primary Facility button field.
	@FindBy(id = "ContentPlaceHolder1_btnAssignFacility")
	public WebElement BtnPrAssignPrimaryFacility;

	public void ClickPrAssignPrimaryFacilityBtn() {
		BtnPrAssignPrimaryFacility.click();
	}

	// Add Provider page Assign Secondary Facility button field.
	@FindBy(id = "ContentPlaceHolder1_btnAssignSecFacility")
	public WebElement BtnPrAssignSecondaryFacility;

	public void ClickPrAssignSecondaryFacilityBtn() {
		BtnPrAssignSecondaryFacility.click();
	}

	// Add Provider page Assign Committee button field.
	@FindBy(id = "ContentPlaceHolder1_btnAssignCommittee")
	public WebElement BtnPrAssignCommittee;

	public void ClickPrAssignCommitteeBtn() {
		BtnPrAssignCommittee.click();
	}

	// Add Provider page AddCallLog button field.
	@FindBy(id = "ContentPlaceHolder1_btnAddCallLog")
	public WebElement BtnPrAddCallLog;

	public void ClickPrAddCallLogBtn() {
		BtnPrAddCallLog.click();
	}

	// Add Provider page HealthEC Health Plan Name text field.
	@FindBy(id = "ContentPlaceHolder1_txtNewOrbitPayerName")
	public WebElement TxtPrHealthPlanName;

	public void EnterPrHealthPlanName(String HealthPlanName) {
		TxtPrHealthPlanName.sendKeys(HealthPlanName);
	}

	// Add Provider page HealthEC Health Plan img.
	@FindBy(id = "ContentPlaceHolder1_hlSearchPayerName")
	public WebElement LnkPrHealthPlan;

	public void ClickPrHealthPlan() {
		LnkPrHealthPlan.click();
	}

	// Add Provider page Transaction Type ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlTransactionType")
	public WebElement DdlPrTransactionType;

	public void SctDdlPrTransactionType(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlPrTransactionType, vtext);
	}

	// Add Provider page LegacyID text field.
	@FindBy(id = "ContentPlaceHolder1_txtLegacyID")
	public WebElement TxtPrLegacyID;

	public void EnterPrLegacyID(String LegacyID) {
		TxtPrLegacyID.sendKeys(LegacyID);
	}

	// Add Provider page add button field.
	@FindBy(id = "ContentPlaceHolder1_btnAdd")
	public WebElement BtnPrAdd;

	public void ClickPrAddButton() {
		BtnPrAdd.click();
	}

	// Add Provider page Save Provider button field.
	@FindBy(id = "ContentPlaceHolder1_btnSaveProvider")
	public WebElement BtnPrSave;

	public void ClickPrSaveButton() {
		BtnPrSave.click();
	}

	// Add provider page, after save successfull message display
	@FindBy(id = "ContentPlaceHolder1_lblProviderStatus")
	public WebElement msg;

	public void CheckValidMsg() {
		String text = msg.getText();
		Reporter.log("Display successfull message----" + text, true);
	}

	// Add provider page,count records before
	@FindBy(id = "ContentPlaceHolder1_lblRecordInfo1")
	public WebElement LblRecordBefore;

	public void CountBefore() {
		String text1 = LblRecordBefore.getText();
		Reporter.log("count the number of provider before adding----" + text1, true);
	}

	// Add provider page,count records after
	@FindBy(id = "ContentPlaceHolder1_lblRecordInfo1")
	public WebElement LblRecordAfter;

	public void CountAfter() {
		String text2 = LblRecordAfter.getText();
		Reporter.log("count the number of provider after adding----" + text2, true);
	}

	public void JavaScriptClick(WebElement element) {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using javascript click");
				((JavascriptExecutor) GlobalVar.Driver).executeScript("arguments[0].click;", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Eelement is not attached to the page document" + e.getStackTrace());
		}
	}

	// Add Provider page clear Provider button field.
	@FindBy(id = "ContentPlaceHolder1_btnClearProvider")
	public WebElement BtnPrClear;

	public void ClickPrClearButton() {
		BtnPrClear.click();
	}

	// Add Provider page back Provider button field.
	@FindBy(id = "ContentPlaceHolder1_btnBackProvider")
	public WebElement BtnPrBack;

	public void ClickPrBackButton() {
		BtnPrBack.click();
	}

}