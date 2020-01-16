package formationselenium.Orange;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestA {
  @BeforeClass(alwaysRun = true)
  public void setUpClassA(){
	  System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
  }
  @BeforeTest(alwaysRun = true)
  public void setUpTestA(){
	  System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
  }
  @Test (priority=1)
  public void test_A_1() {
	  System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
	  assertEquals(2, 2);
  }
  @Test(priority=0)
  public void test_A_2() {
	  System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
  }
  @AfterTest(alwaysRun = true)
  public void tearDownTestA(){
	  System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
  }
  @AfterClass(alwaysRun = true)
  public void tearDownClassA(){
	  System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
  }
}

