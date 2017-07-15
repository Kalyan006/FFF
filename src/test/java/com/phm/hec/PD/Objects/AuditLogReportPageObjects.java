package com.phm.hec.PD.Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.phm.hec.utility.GenericUtils;

public class AuditLogReportPageObjects {
	
	//select the product Category
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlProduct']")
	public WebElement ddlProductCategory;
	
	public void SelectDdlProductCategory(String productcategory) {
		GenericUtils.selectDdlByVisibleText(ddlProductCategory, productcategory);
	}
	
	//Select the Module name
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlModule']")
	public WebElement ddlModule;
	
	public void SelectDdlModule(String modulename) {
		GenericUtils.selectDdlByVisibleText(ddlModule, modulename);
	}
	
	//Select the Module Action
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlModuleAction']")
	public WebElement ddlModuleAction;
	
	public void SelectDdlModuleAction(String ModuleActionName) {
		GenericUtils.selectDdlByVisibleText(ddlModuleAction, ModuleActionName);
	}
	
	//Select the TimePeriod
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_ddlTimePeriod']")
	public WebElement ddlTimePeriod;
	
	public void SelectDdlTimePeriod(String timeperiod) {
		GenericUtils.selectDdlByVisibleText(ddlTimePeriod, timeperiod);
	}
	
	//Click on Search Button
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_btnSearch']")
	public WebElement btnSearch;
	
	public void ClickBtnSearch() {
		btnSearch.click();
	}
	
	//Click on Clear Button
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_btnClear']")
	public WebElement btnClear;
	
	public void ClickBtnClear() {
		btnClear.click();
	}

	//Click on Grid User Audit Log List
	@FindBy(xpath=".//*[@id='ContentPlaceHolder1_grdUserAuditLogList']/tbody/tr[2]/th[10]/a/div/img")
	public WebElement grdUserAuditLogList;
	
	public void ClickGridAuditLogList() {
		grdUserAuditLogList.click();
	}
	
	//Module Action Login for e provider
	@FindBy(xpath="(//tbody/tr/td[text()='Login'])[2]")
	public WebElement ModuleActionLogin;
	
	public String ModuleActionLoginTest() {
		String Modlogintxt = ModuleActionLogin.getText();
		return Modlogintxt;
	}
	
	//Log Date and Time For Module Action Login for e provider
	@FindBy(xpath="(//tbody/tr/td[text()='Login'])[2]/../td[10]")
	public WebElement loginlogdate;
	
	public String GetLoginLogDate() {
		String txtloginlog = loginlogdate.getText();
		return txtloginlog;
	}
	
	//Module Action Landing Page for e provider
	
	@FindBy(xpath="(//tbody/tr/td[text()='Home'])")
	public WebElement ModuleActionhome;
	
	public String ModuleActionHomeTest() {
		String hometxt = ModuleActionhome.getText();
		return hometxt;
	}
	
	//Log Date and Time For Landing Page
	
	@FindBy(xpath="(//tbody/tr/td[text()='Home'])/../td[10]")
	public WebElement homelogdate;
	public String GetHomeLogDate() {
		String txthomelog = homelogdate.getText();
		return txthomelog;
	}
	
	
	//Module Action Frequently Asked Question
	@FindBy(xpath="(//tbody/tr/td[text()='FREQUENTLY ASKED QUESTIONS'])")
	public WebElement ModuleActionFaq;
	
	public String ModuleActionFAQTest() {
		String Faqtxt = ModuleActionFaq.getText();
		return Faqtxt;
	}
	
	//Log Date and Time For Module Action Frequently Asked Question
		@FindBy(xpath="(//tbody/tr/td[text()='FREQUENTLY ASKED QUESTIONS'])/../td[10]")
		public WebElement faqlogdate;
		
		public String GetFAQLogDate() {
			String txtfaqlog = faqlogdate.getText();
			return txtfaqlog;
		}
	
	//Module Action Forms and template
	@FindBy(xpath="(//tbody/tr/td[text()='FORMS AND TEMPLATES'])")
	public WebElement ModuleActionFat;
	
	public String ModuleActionFATTest() {
		String Fattxt = ModuleActionFat.getText();
		return Fattxt;
	}
	
	//Log Date and Time For Module Action Form And Template
		@FindBy(xpath="(//tbody/tr/td[text()='FORMS AND TEMPLATES'])/../td[10]")
		public WebElement fatlogdate;
		
		public String GetFATLogDate() {
			String txtfatlog = fatlogdate.getText();
			return txtfatlog;
		}
	
	//Module Action EMR EDucation
		@FindBy(xpath="(//tbody/tr/td[text()='EMR EDUCATION'])")
		public WebElement ModuleActionEMREdu;
		
		public String ModuleActionEMREduTest() {
			String EmrEdutxt = ModuleActionEMREdu.getText();
			return EmrEdutxt;
		}
		
		//Log Date and Time For Module Action EMR EDucation
		@FindBy(xpath="(//tbody/tr/td[text()='EMR EDUCATION'])/../td[10]")
		public WebElement EmrEdulogdate;
		
		public String GetEMREduLogDate() {
			String txtEmrEdulog = EmrEdulogdate.getText();
			return txtEmrEdulog;
		}
		
		//Module Action Webinars
		@FindBy(xpath="(//tbody/tr/td[text()='WEBINARS'])")
		public WebElement ModuleActionWebinar;

		public String ModuleActionWebinarTest() {
			String webinartxt = ModuleActionWebinar.getText();
			return webinartxt;
		}	
		
		//Log Date and Time For Module Action Webinars
		@FindBy(xpath="(//tbody/tr/td[text()='WEBINARS'])/../td[10]")
		public WebElement webinarlogdate;
		
		public String GetWebinarLogDate() {
			String txtwebinarlog = webinarlogdate.getText();
			return txtwebinarlog;
		}
		//Module Action Decision Aids
		@FindBy(xpath="(//tbody/tr/td[text()='DECISION AIDS'])")
		public WebElement ModuleActiondecision;

		public String ModuleActionDecisionAidsTest() {
			String decisiontxt = ModuleActiondecision.getText();
			return decisiontxt;
		}
		
		//Log Date and Time For Module Action Decision Aids
		@FindBy(xpath="(//tbody/tr/td[text()='DECISION AIDS'])/../td[10]")
		public WebElement DecisionAidlogdate;
		
		public String GetDecisionAidLogDate() {
			String txDecisionAidlog = DecisionAidlogdate.getText();
			return txDecisionAidlog;
		}
		
		//Module Action Logout for e provider
		@FindBy(xpath="(//tbody/tr/td[text()='Logout'])[2]")
		public WebElement ModuleActionlogout;

		public String ModuleActionLogoutTest() {
			String logouttxt = ModuleActionlogout.getText();
			return logouttxt;
		}
		
		//Log Date and Time For Module Action Logout for e provider
		@FindBy(xpath="(//tbody/tr/td[text()='Logout'])[2]/../td[10]")
		public WebElement Logoutlogdate;

		public String GetLogOutLogDate() {
			String txlogoutlog = Logoutlogdate.getText();
			return txlogoutlog;
		}


		//MOdule Action Login For Care MAnager
		@FindBy(xpath="(//tbody/tr/td[text()='Login']/..)[1]/td[7]")
		public WebElement CMModuleActionLogin;

		public String CMModuleActionLoginTest() {
			String Modlogintxt = CMModuleActionLogin.getText();
			return Modlogintxt;
		}

		//Log Date and Time For Module Action Login for e provider
		@FindBy(xpath="(//tbody/tr/td[text()='Login']/..)[1]/td[7]/../td[10]")
		public WebElement CMloginlogdate;

		public String GetCMLoginLogDate() {
			String txtloginlog = CMloginlogdate.getText();
			return txtloginlog;
		}

		//Module Action intervention Scheduler

		@FindBy(xpath="((//tbody/tr/td[text()='Intervention Scheduler']/..)[1]/td)[6]")
		public WebElement ModuleActionintervention;

		public String ModuleActionInterventionTest() {
			String intschedulertxt = ModuleActionintervention.getText();
			return intschedulertxt;
		}

		//Log Date and Time For intervention Scheduler

		@FindBy(xpath="((//tbody/tr/td[text()='Intervention Scheduler']/..)[1]/td)[10]")
		public WebElement interventionlogdate;
		public String GetInterventionLogDate() {
			String txtinterventionlog = interventionlogdate.getText();
			return txtinterventionlog;
		}	

		//Module Action Patient Search
		@FindBy(xpath="//tbody/tr/td[text()='Patient Search']")
		public WebElement ModuleActionpatientsch;

		public String ModuleActionPatientSchTest() {
			String patientschtxt = ModuleActionpatientsch.getText();
			return patientschtxt;
		}

		//Log Date and Time For patient Search
		@FindBy(xpath="//tbody/tr/td[text()='Patient Search']/../td[10]")
		public WebElement patientschlogdate;

		public String GetpatientSchLogDate() {
			String txtpatientschlog = patientschlogdate.getText();
			return txtpatientschlog;
		}
		//Module Action Contact/OutReach
		@FindBy(xpath="//tbody/tr/td[text()='Contacts / Outreach']")
		public WebElement ModuleActioncontact;

		public String ModuleActionContactOutTest() {
			String contactouttxt = ModuleActioncontact.getText();
			return contactouttxt;
		}

		//Log Date and Time For Contact/OutReach
		@FindBy(xpath="//tbody/tr/td[text()='Contacts / Outreach']/../td[10]")
		public WebElement contactlogdate;

		public String GetContactoutLogDate() {
			String txtcontactlog = contactlogdate.getText();
			return txtcontactlog;
		}

		//Module Action For Assign Patient
		@FindBy(xpath="//tbody/tr/td[text()='Assign Patients']")
		public WebElement ModuleActionAssignpatient;

		public String ModuleActionAssignPatientTest() {
			String assigntxt = ModuleActionAssignpatient.getText();
			return assigntxt;
		}

		//Log Date and Time For Module Action Assign patient
		@FindBy(xpath="//tbody/tr/td[text()='Assign Patients']/../td[10]")
		public WebElement assignlogdate;

		public String GetAssignPatientLogDate() {
			String txtassignlog = assignlogdate.getText();
			return txtassignlog;
		}

		//Module Action Admission Discharge
		@FindBy(xpath="//tbody/tr/td[text()='Admission Discharge']")
		public WebElement ModuleActionAdmission;

		public String ModuleActionAdmissionDchTest() {
			String admissiontxt = ModuleActionAdmission.getText();
			return admissiontxt;
		}

		//Log Date and Time For Module Action Admission Discharge
		@FindBy(xpath="//tbody/tr/td[text()='Admission Discharge']/../td[10]")
		public WebElement Admissionlogdate;

		public String GetAdmissionDchLogDate() {
			String txtadmissionlog = Admissionlogdate.getText();
			return txtadmissionlog;
		}
		//Module Action Logout for care manager
		@FindBy(xpath="(//tbody/tr/td[text()='Logout']/..)[1]/td[7]")
		
		public WebElement CMModuleActionlogout;

		public String CMModuleActionLogoutTest() {
			String CMlogouttxt = CMModuleActionlogout.getText();
			return CMlogouttxt;
		}

		//Log Date and Time For Module Action Logout for Care Manager
		@FindBy(xpath="(//tbody/tr/td[text()='Logout']/..)[1]/td[7]/../td[10]")
		public WebElement CMLogoutlogdate;

		public String GetCMLogOutLogDate() {
			String txlogoutlog = CMLogoutlogdate.getText();
			return txlogoutlog;
		}

}
