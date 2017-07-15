package com.phm.hec.PD.Objects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.phm.hec.utility.GenericUtils;
import com.phm.hec.utility.GlobalVar;

public class AddAccountAssignRegionPageObjects {
	
	GenericUtils genu=new GenericUtils();
	// Enter assign region code
	@FindBy(id = "txtRegionCode")
	public WebElement TxtRegionCode;

	public void EnterAddAccRegionCode(String regioncode) {
		TxtRegionCode.sendKeys(regioncode);
	}
	//Add account page, assign region select all region name check box
	@FindBy(id="grdRegionList_chkBoxSelectAllACOId")
	public WebElement ChkBoxSelectAllRegionNameList;
	public void ClickAddAccountSelectAllRegionNameBox(){
		ChkBoxSelectAllRegionNameList.click();
	}
	
	//
	public Boolean verifyElementPresentorNot() {
		WebDriverWait wait = new WebDriverWait(GlobalVar.Driver, 5);
		try {
			wait.until(ExpectedConditions.visibilityOf(TxtRegionCode));
			Reporter.log("Element Exits ", true);
			return true;
		} catch (NoSuchElementException e) {
				return false;
			}
	}
	
	public boolean isElementExists(By TxtRegionCode) {
	boolean isExists = true;
	try {
	        GlobalVar.Driver.findElement(TxtRegionCode);
	    } catch (NoSuchElementException e) {
	        isExists = false;
	    }
	    return isExists;
	}
	// Enter assign region name
	@FindBy(id = "txtRegionName")
	public WebElement TxtRegionName;

	public String EnterAddAccRegionName(String regionname) {
		TxtRegionName.sendKeys(regionname);
		return regionname;
	}

	// Click search region
	@FindBy(id = "btnSearchUserAssign")
	public WebElement BtnSearchRegion;

	public void ClickAddAccSearchRegion() {
		BtnSearchRegion.click();
	}

	// Select assign region name check box
	@FindBy(id = "grdRegionList_chkBoxACOId_0")
	public WebElement ChkBoxRegionName;

	public void ClickAddAccSelectRegionBox() {
		ChkBoxRegionName.click();
	}

	// Select assign after check box region name
	@FindBy(xpath = ".//*[@id='grdRegionList_lblLastName_0']")
	public WebElement LblAddAccRegionName;

	public String AddAccLblRegionNameText() {
		String ARLblRegionName = LblAddAccRegionName.getText();
		return ARLblRegionName;

	}

	// Click on assign region button
	@FindBy(id = "btnAssignUserAccount")
	public WebElement BtnAssignAccount;

	public void ClickAddAccAssignUserRegion() {
		BtnAssignAccount.click();
	}
	
	
	
	
	

}
