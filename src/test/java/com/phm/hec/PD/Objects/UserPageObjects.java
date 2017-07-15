package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPageObjects {

	// Button Add User
	@FindBy(xpath = ".//*[@id='ContentPlaceHolder1_btnAddUser']")
	public WebElement LnkbtnAddUser;

	public void ClickAddUser() {
		LnkbtnAddUser.click();
	}

}
