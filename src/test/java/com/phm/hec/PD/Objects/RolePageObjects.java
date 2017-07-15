package com.phm.hec.PD.Objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.phm.hec.utility.GenericUtils;

public class RolePageObjects {
	public static Logger addlog = Logger.getLogger(RolePageObjects.class.getName());
	Logger logger=addlog;

		GenericUtils GenUtil = new GenericUtils();
		// Enter the Role Name
		@FindBy(id = "ContentPlaceHolder1_TxtRoleName")
		public WebElement TxtRolename;
		
		public void EnterRoleName(String rolename) {
			TxtRolename.sendKeys(rolename);
		}

		// Click On Role Search Button
		@FindBy(xpath = ".//*[@id='ContentPlaceHolder1_btnSearchRole']")
		public WebElement ClksearchRole;
		public void ClickSearchRole() {
			ClksearchRole.click();
		}

		// click on Grid Role List
		@FindBy(id = "ContentPlaceHolder1_gridRoleList_imgSetup_0")
		public WebElement Clkgridrole;
		
		public void ClickGridRoleList() {
			Boolean elePresent = GenUtil.isElementPresent(By.id("ContentPlaceHolder1_gridRoleList_imgSetup_0"));
			if ((Boolean.TRUE.equals(elePresent))){
				
				Clkgridrole.click();
				//Reporter.log("Grid Role list option exist",true);
				logger.info("Grid Role list option exist");
			}
			else
			{
				//Reporter.log("Grid Role list option not exist",true);
				logger.info("Grid Role list option not exist");
			}

		}

		// Select Associate Product Name From The Drop Down
		@FindBy(id = "ContentPlaceHolder1_drAssProductNames")
		public WebElement SelectAssProduct;
		
		public void SelectAssociateProductNames(String txt) {
			GenericUtils.selectDdlByVisibleText(SelectAssProduct, txt);
		}

		// Select the Particular Checked Box
		@FindBy(xpath = ".//*[@id='content_inner']/table/tbody/tr[7]/td[1]/input")
		public WebElement Clkcheckbox;
		
		public void ClickCheckBox() {
			Clkcheckbox.click();
		}

		// Select the check box for Care management menu in role setup
	
		@FindBy(xpath = "//label[contains(text(),'Care Management')]/..//input[@type='checkbox']")
		public WebElement ClkCaremgtchkbox;
		
		public void ClickCareManagementcheckbox() {
			ClkCaremgtchkbox.click();

		}

		// select the check box for forms,resources and education sub menu in role setup for AHG And DCPCA
		@FindBy(xpath=".//*[@id='content_inner']/table/tbody/tr[3]/td[2]/input[9]")
	
		public WebElement ClkformsEdusubmenuP9;
		
		public void ClickFormResourceEducationSubMenuP9() {
			ClkformsEdusubmenuP9.click();
		}
		
		
		// select the check box for forms,resources and education sub menu in role setup for PPO Client
		
		@FindBy(xpath=".//*[@id='content_inner']/table/tbody/tr[5]/td[2]/input[1]")
		public WebElement ClkformEdusubmenuP1;
		
		public void ClickFormResourceEducationSubMenuP1()
		{
			ClkformEdusubmenuP1.click();
		}
		// click on save button
		@FindBy(id = "ContentPlaceHolder1_btnSave")
		public WebElement btnSave;
		
		public void ClickbtnSave() {
			btnSave.click();
		}
		
		//Click on Reports Check Box Under Role Setup Menu
		@FindBy(xpath="//label[contains(text(),'Reports')]/..//input[@type='checkbox']")
		public WebElement ClkReportChkbox;
		
		public void ClickReportsCheckBox() {
			ClkReportChkbox.click();
		}
		
		//Click on Audit Reports Check Box Based On P12Client PPO &DCPCA
		@FindBy(xpath=".//*[@id='content_inner']/table/tbody/tr[5]/td[2]/input[12]")
		public WebElement ClkAuditChkboxP12;
		
		public void ClickAuditReportsChkBoxP12() {
			ClkAuditChkboxP12.click();
		}
		
		//Click on Audit Reports Check Box Based on P11Client AHG
		@FindBy(xpath=".//*[@id='content_inner']/table/tbody/tr[5]/td[2]/input[11]")
		public WebElement clkAuditChkboxP11;
		
		public void ClickAuditReportsChkBoxP11() {
			clkAuditChkboxP11.click();
			
		}

	}
