package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssignSecondaryAccountPageObjects {
	//Assign Secondary account page account name text field
	@FindBy(id = "txtAccountName")
	public WebElement TxtSecondaryAccountName;

	public void EnterSecondaryAccountName(String AccountName) {
		TxtSecondaryAccountName.sendKeys(AccountName);
	}

	//Assign Secondary account page secondary tax id text field
	@FindBy(id = "txtTaxID")
	public WebElement TxtSecondaryTaxId;

	public void EnterSecondaryTaxId(String TaxId) {
		TxtSecondaryTaxId.sendKeys(TaxId);
	}
	
	//Assign secondary account page, select all region name check box
	@FindBy(id="grdAccountList_chkBoxSelectAllAccountId")
	public WebElement ChkBoxSelectAllSecondaryAccountName;
	public void ClickAddProviderSelectAllSecondaryAccountNameBox(){
		ChkBoxSelectAllSecondaryAccountName.click();
	}

	//Assign Secondary account page account name text field
	@FindBy(id = "btnSearchUserAssign")
	public WebElement BtnSeconSearchUser;

	public void ClickSeconSearchUser() {
		BtnSeconSearchUser.click();
	}

	//Assign Secondary account page,select any one account name
	@FindBy(id = "grdAccountList_chkBoxAccountId_0")
	public WebElement ChkSeconAccountName;

	public void ClickSeconAccountName() {
		ChkSeconAccountName.click();
	}

	//Assign Secondary account page,Click on secondary assign account
	@FindBy(id = "btnAssignUserAccount")
	public WebElement BtnSeconAssignAccount;

	public void ClickSeconAssignAccount() {
		BtnSeconAssignAccount.click();
	}

}
