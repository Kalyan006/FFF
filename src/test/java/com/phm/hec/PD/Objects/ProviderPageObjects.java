package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.phm.hec.utility.GenericUtils;
import com.phm.hec.utility.GlobalVar;

public class ProviderPageObjects {

	// Provider page provider last name text field.
	@FindBy(id = "ContentPlaceHolder1_txtLastName")
	public WebElement TxtLastName;

	public void EnterProviderLastName(String lastname) {
		TxtLastName.sendKeys(lastname);
	}

	// Provider page provider first name text field.
	@FindBy(id = "ContentPlaceHolder1_txtFirstName")
	public WebElement TxtFirstName;

	public void EnterProviderFirstName(String firstname) {
		TxtFirstName.sendKeys(firstname);
	}

	// Provider page provider ddl status field.
	@FindBy(id = "ContentPlaceHolder1_ddlStatus")
	public WebElement DdlProviderStatus;

	public void SctProviderStatus(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlProviderStatus, vtext);
	}

	// Provider page provider NPI text field.
	@FindBy(id = "ContentPlaceHolder1_txtNPI")
	public WebElement TxtProviderNpi;

	public void EnterProviderNpi(String npi) {
		TxtProviderNpi.sendKeys(npi);
	}

	// Provider page provider tax id text field.
	@FindBy(id = "ContentPlaceHolder1_txtFederalTaxId")
	public WebElement TxtProviderTaxId;

	public void EnterProviderTaxId(String taxid) {
		TxtProviderTaxId.sendKeys(taxid);
	}

	// Provider page provider State Assigned id text field.
	@FindBy(id = "ContentPlaceHolder1_txtUMPI")
	public WebElement TxtPdrStateAssignedId;

	public void EnterPdrStateAssignedId(String StateAssignedId) {
		TxtPdrStateAssignedId.sendKeys(StateAssignedId);
	}

	// Provider page provider Region Code ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlACOList")
	public WebElement DdlPrRegionCode;

	public void SctPrRegionCode(String Vtext) {
		GenericUtils.selectDdlByVisibleText(DdlPrRegionCode, Vtext);
	}

	// Provider page Region Provider Status ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlRegionProviderStatus")
	public WebElement DdlRegionPrStatus;

	public void SctPrRegionStatus(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlRegionPrStatus, vtext);
	}

	// Provider page Provider source ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlProviderSource")
	public WebElement DdlProviderSource;

	public void SctProviderSource(String vtext) {
		GenericUtils.selectDdlByVisibleText(DdlProviderSource, vtext);
	}

	// Provider page Provider user name text field.
	@FindBy(id = "ContentPlaceHolder1_txtUserName")
	public WebElement TxtPrUserName;

	public void EnterPrUserName(String UserName) {
		TxtPrUserName.sendKeys(UserName);
	}

	// Provider page Provider Account name text field.
	@FindBy(id = "ContentPlaceHolder1_txtAssignAccount")
	public WebElement TxtPrAccountName;

	public void EnterPRAccountName(String AccountName) {
		TxtPrAccountName.sendKeys(AccountName);
	}

	// Provider page Provider img Account name text field.
	@FindBy(id = "ContentPlaceHolder1_imgbtnAccount")
	public WebElement BtnPrImgAccountName;

	public void ClickImgAccountName() {
		BtnPrImgAccountName.click();
	}

	// Provider page search provider button field.
	@FindBy(id = "ContentPlaceHolder1_btnSearchProvider")
	public WebElement BtnSearchProvider;

	public void ClickSearchProvider() {
		BtnSearchProvider.click();
	}

	// Provider page clear provider button field.
	@FindBy(id = "ContentPlaceHolder1_btnClearProvider")
	public WebElement BtnClearProvider;

	public void ClickClearProvider() {
		BtnClearProvider.click();
	}

	// Provider page Add provider button field.
	@FindBy(id = "ContentPlaceHolder1_btnAddProvider")
	public WebElement BtnAddProvider;

	public void ClickAddProvider() {
		BtnAddProvider.click();
	}
	
	// To verify provider last name present or not
	@FindBy(id = "ContentPlaceHolder1_grdProviderList_lblLastName_0")
	public WebElement verifyproviderlastname;

	public Boolean VerifyProviderLastNamePresentorNot() {
		WebDriverWait wait = new WebDriverWait(GlobalVar.Driver, 5);
		try {
			wait.until(ExpectedConditions.visibilityOf(verifyproviderlastname));
			Reporter.log("Provider last name exist... ", true);
			return true;
		} catch (Exception e) {
			Reporter.log("Provider last name not exist... ", true);
			return false;

		}
	
	}
}
