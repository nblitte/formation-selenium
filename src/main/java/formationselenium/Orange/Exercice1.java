package formationselenium.Orange;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Exercice1 {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test() throws Exception {
	  //login
    driver.get("https://opensource-demo.orangehrmlive.com/");
    driver.findElement(By.id("txtUsername")).click();
    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
    driver.findElement(By.id("txtPassword")).click();
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("admin123");
    driver.findElement(By.id("btnLogin")).click();
    
    
    // ajouter employé
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("btnAdd")).click();
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("Hassan");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Imhah");
    String identifiant = driver.findElement(By.id("employeeId")).getAttribute("value");
    driver.findElement(By.id("btnSave")).click();
    assertTrue(driver.findElement(By.id("personal_txtEmpFirstName")).isDisplayed());
    assertTrue(driver.findElement(By.id("personal_txtEmpLastName")).isDisplayed());
    try {
      assertEquals(driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText(), "Hassan Imhah");
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
    
    
    //rechercheEmployé par Id
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("empsearch_id")).clear();
    driver.findElement(By.id("empsearch_id")).sendKeys(identifiant);
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.linkText("Hassan")).click();
    
    
    //mettre à jour la photo
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("empsearch_id")).clear();
    driver.findElement(By.id("empsearch_id")).sendKeys(identifiant);
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.linkText(identifiant)).click();
    driver.findElement(By.id("empPic")).click();
    driver.findElement(By.id("photofile")).clear();
    driver.findElement(By.id("photofile")).sendKeys("C:\\Users\\CSI\\Desktop\\user.jpg");
    driver.findElement(By.id("btnSave")).click();
    
    
    //
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
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

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
