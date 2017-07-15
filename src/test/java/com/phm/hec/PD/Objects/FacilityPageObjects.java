package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacilityPageObjects {
	

	// Facility page facility name text field.
	@FindBy(id = "ContentPlaceHolder1_txtFacilityName")
	public WebElement TxtFacilityName;

	// Facility page facility address text field.
	@FindBy(id = "ContentPlaceHolder1_txtaddress")
	public WebElement TxtFacilityAddress;

	// Facility page facility city text field.
	@FindBy(id = "ContentPlaceHolder1_txtCity")
	public WebElement TxtFacilityCity;

	// Facility page facility zip text field.
	@FindBy(id = "ContentPlaceHolder1_txtZip")
	public WebElement TxtFacilityZip;

	// Facility page facility state ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlState")
	public WebElement DdlFacilityState;

	// Facility page facility county ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlCounty")
	public WebElement DdlFacilityCounty;

	// Facility page facility tax id text field.
	@FindBy(id = "ContentPlaceHolder1_txtTaxID")
	public WebElement TxtTaxId;
	
	public String EnterFacilityTaxID(String taxid) {
		TxtTaxId.sendKeys(taxid);
		return taxid;
	}

	// Facility page facility status ddl field.
	@FindBy(id = "ContentPlaceHolder1_ddlStatus")
	public WebElement DdlFacilityStatus;

	// Facility page facility search button field.
	@FindBy(id = "ContentPlaceHolder1_btnSearchUserAssign")
	public WebElement BtnSearchFacility;
	
	public void ClickOnSearchFacility() {
		BtnSearchFacility.click();
	}

	// Facility page facility clear button field.
	@FindBy(id = "ContentPlaceHolder1_btnCancelUserAssign")
	public WebElement BtnClear;

	// Facility page facility add button field.
	@FindBy(id = "ContentPlaceHolder1_btnAddFacility")
	public WebElement BtnAddFacility;
	
	//Click on Edit Button of Facility
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_grdAccountList_imgEdit_0']")
	public WebElement editfacility;
	
	public void ClickEditFacility() {
		editfacility.click();
	}

}
