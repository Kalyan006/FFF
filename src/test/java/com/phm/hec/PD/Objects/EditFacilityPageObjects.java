package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.phm.hec.utility.GenericUtils;

public class EditFacilityPageObjects {
	
	
	// Facility Additional Information Block
	
	//Enter the Address1
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtFacilityAddress1']")
	public WebElement txtFacilityAddress1;
	
	public void EnterFacilityAddress1(String address1) {
		txtFacilityAddress1.sendKeys(address1);
		
	}
	//Enter the Address2
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtFacilityAddress2']")
	public WebElement txtFacilityAddress2;
	
	public void EnterFacilityAddress2(String address2) {
		txtFacilityAddress1.sendKeys(address2);
		
	}
	
	//Enter City Name
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtFacilityCity']")
	public WebElement txtFacilityCity;
	
	public void EnterFacilityCity(String Facilitycity) {
		txtFacilityCity.sendKeys(Facilitycity);
		
	}
	
	//Select State from Drop Down
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlFacilityState']")
	public WebElement ddlFacilityState;
	
	public void SelectddlFacilityState(String state) {
		GenericUtils.selectDdlByVisibleText(ddlFacilityState, state);
	}
	
	//Enter the Zip Code

		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtFacilityZip']")
		public WebElement txtFacilityZip;
		
		public void EnterFacilityZip(String FacilityZip) {
			txtFacilityZip.sendKeys(FacilityZip);
			
		}
	
		//Select Country from Drop Down
		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlFacilityCounty']")
		public WebElement ddlFacilityCounty;
		
		public void SelectddlFacilityCountry(String country) {
			GenericUtils.selectDdlByVisibleText(ddlFacilityCounty, country);
		}

		//Enter the Phone Number

		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtFacilityPhone']")
		public WebElement txtFacilityPhone;

		public void EnterFacilityPhone(String FacilityPhone) {
			txtFacilityPhone.sendKeys(FacilityPhone);

		}

		//Enter the FAx Code

		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtFacilityFax']")
		public WebElement txtFacilityFax;

		public void EnterFacilityFax(String FacilityFax) {
			txtFacilityFax.sendKeys(FacilityFax);

		}

		//Enter the NPI

		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_txtGroupNPI']")
		public WebElement txtFacilityGroupNPI;

		public void EnterFacilityGroupNPI(String FacilityNPI) {
			txtFacilityGroupNPI.sendKeys(FacilityNPI);

		}

		//Select Status from Drop Down
		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlStatus']")
		public WebElement ddlFacilityStatus;

		public void SelectddlFacilityStatus(String Status) {
			GenericUtils.selectDdlByVisibleText(ddlFacilityStatus, Status);
		}

		//Click on Save Button

		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_btnSaveFacility']")
		public WebElement btnSaveFacility;

		public void ClickbtnSaveFacility() {
			btnSaveFacility.click();
		}

		//Click on Term Date  Calender Icon

		@FindBy(xpath=".//*[@id='ContentPlaceHolder1_imgTermDate']")
		public WebElement imgTermDate;

		public void ClickCalenderimgTermDate() {
			imgTermDate.click();
		}

		//Pick the Date From Calender 

		@FindBy(xpath="//td[text()='14']")
		public WebElement pickTermDate;

		public void ClickOnCalenderDate() {
			pickTermDate.click();
		}

				
				
				

}
