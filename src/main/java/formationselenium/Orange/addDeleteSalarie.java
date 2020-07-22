package formationselenium.Orange;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class addDeleteSalarie {
	private WebDriver driver;
	private WebDriverWait wait;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	String identifiant="";
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver, 10);
		
		//login
		driver.get("https://opensource-demo.orangehrmlive.com/");
	    driver.findElement(By.id("txtUsername")).click();
	    driver.findElement(By.id("txtUsername")).clear();
	    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
	    driver.findElement(By.id("txtPassword")).click();
	    driver.findElement(By.id("txtPassword")).clear();
	    driver.findElement(By.id("txtPassword")).sendKeys("Nantes$2020");
	    driver.findElement(By.id("btnLogin")).click();
		
	}

	@Test(priority=0,dataProvider="salarie")
	public void addSalarie(String nom,String prenom) throws Exception {
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
	    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
	    driver.findElement(By.id("btnAdd")).click();
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys(prenom);
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys(nom);
	    identifiant = driver.findElement(By.id("employeeId")).getAttribute("value");
	    driver.findElement(By.id("btnSave")).click();
	    assertTrue(driver.findElement(By.id("personal_txtEmpFirstName")).isDisplayed());
	    assertTrue(driver.findElement(By.id("personal_txtEmpLastName")).isDisplayed());
	    try {
	      assertEquals(driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText(), prenom+" "+nom);
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.id("personal_txtLicExpDate")).click();
	    driver.findElement(By.id("personal_txtOtherID")).click();
	    driver.findElement(By.id("btnSave")).click();
	    driver.findElement(By.id("personal_optGender_1")).click();
	    driver.findElement(By.id("personal_cmbNation")).click();
	    driver.findElement(By.id("personal_cmbNation")).click();
	    driver.findElement(By.xpath("//form[@id='frmEmpPersonalDetails']/fieldset/ol[3]/li[4]/img")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Next'])[1]/following::select[2]")).click();
	    driver.findElement(By.linkText("12")).click();
	    driver.findElement(By.id("btnSave")).click();
	}
  
	@Test(priority=1,dataProvider="salarie")
	public void deleteSalarie(String nom,String prenom) throws Exception {
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
	    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
	    driver.findElement(By.id("empsearch_employee_name_empName")).clear();
	    driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys(prenom+" "+nom);
	    driver.findElement(By.id("searchBtn")).click();
	    driver.findElement(By.id("ohrmList_chkSelectAll")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnDelete"))).click();
	    //driver.findElement(By.id("btnDelete")).click();
	    if(driver.findElement(By.id("dialogDeleteBtn")).isDisplayed()) {
	    	driver.findElement(By.id("dialogDeleteBtn")).click();
	    }
	}

	@AfterMethod(alwaysRun = true)
  	public void tearDown() throws Exception {
		//logout
		for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("welcome"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    driver.findElement(By.id("welcome")).click();
	    driver.findElement(By.xpath("//div[@id='welcome-menu']/ul/li[3]/a")).click();
	    
	    identifiant="";
	    
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
		fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	
	@DataProvider(name = "salarie")
	public Object[][] salarieData() {
	 return new Object[][] {
	   { "Hassan", "Imhah"}//,
	   //{ "uncle", "bob"},
	   //{"james","Goslin"}
	 };
	}

}
