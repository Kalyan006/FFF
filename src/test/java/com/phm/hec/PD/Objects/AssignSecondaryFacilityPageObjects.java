package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssignSecondaryFacilityPageObjects {
	
	// Secondary Facility Page,facility name text field
	@FindBy(id = "txtFacilityName")
	public WebElement TxtPrSecFacilityName;

	public void EnterPrSecFacilityName(String FacilityName) {
		TxtPrSecFacilityName.sendKeys(FacilityName);
	}

	// Secondary Facility Page,tax id text field
	@FindBy(xpath = "//input[@id='txtTaxID']")
	public WebElement TxtPrSecFacilityTaxId;

	public void EnterPrSecFacilityTaxId(String TaxId) {
		TxtPrSecFacilityTaxId.sendKeys(TaxId);
	}

	// Secondary Facility Page,search facility button
	@FindBy(id = "btnSearchUserAssign")
	public WebElement BtnPrSecSearchFacility;

	public void ClickPrSecSearchFacility() {
		BtnPrSecSearchFacility.click();
	}

	// Secondary Facility Page,search facility link
	@FindBy(xpath = "//table/tbody/tr[3]/td/input[@type='image']")
	public WebElement LnkPrSecFacilityName;

	public void ClickPrSecLnkFacilityName() {
		LnkPrSecFacilityName.click();
	}

}
