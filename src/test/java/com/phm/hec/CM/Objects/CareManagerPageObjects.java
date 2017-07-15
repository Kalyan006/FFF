package com.phm.hec.CM.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareManagerPageObjects {
	
	// Click on Care Management Menu
	@FindBy(xpath = "//a[text()='Care Management']")
	public WebElement LnkFREcaremgt;
	
	public void ClickCareManagementMenu() {
		LnkFREcaremgt.click();

	}
	//Click On Patient Search Sub Menu Under 
	// Care Management Menu
	
	@FindBy(xpath=".//*[@id='nestedAccordion']/div[1]/a[1]/h3")
	public WebElement lnkpatientsearch;
	
	public void ClickLnkPatientSearch() {
		lnkpatientsearch.click();
	}
	
	//Click on Assign Patient
	
	@FindBy(xpath=".//*[@id='nestedAccordion']/div[1]/a[4]/h3")
	public WebElement lnkAssignPatient;
	
	public void ClickLnkAssignPatient() {
		lnkAssignPatient.click();
	}
	//Click on Admission /Discharge
	
	@FindBy(xpath=".//*[@id='nestedAccordion']/div[1]/a[5]/h3")
	public WebElement lnkAdmissionDischarge;
	
	public void ClickLnkAdmissionDischarge() {
		lnkAdmissionDischarge.click();
	}
	//Click on Contact/Outreach
	
	@FindBy(xpath=".//*[@id='nestedAccordion']/div[1]/a[2]/h3")
	public WebElement lnkContactOutReach;
	
	public void ClickLnkContactOutReach() {
		lnkContactOutReach.click();
	}
	
	
	/*// click on care Manager Home Tab
	@FindBy(xpath = "//a[@id='lnkCareManager']")
	public WebElement LnkFREcaremgr;

	public void ClickOnCareManager() {
		LnkFREcaremgr.click();
	}*/

			

	// click on Form Resource And Education Sub Menu under care management.
	@FindBy(xpath = "//h3[text()='Forms, Resources and Education']")
	public WebElement LnkFREformEdu;

	public void ClickFormResourceEducation() {
		LnkFREformEdu.click();
	}
	
	
	//Click on Patient Management Menu
	
	
	
	
	
	
	//Click On Measure management Menu
	
}
