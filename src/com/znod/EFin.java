package com.znod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class EFin {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	int randomInt = (int) (10000 * Math.random());
	private String userName;
	private String password;

	Properties proptiesFile = new Properties();

	String randomNumber = Integer.toString(randomInt);
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		//baseUrl = "http://znod.cloudapp.net/";
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		try{
		proptiesFile.load(new FileInputStream("./src/com/znod/test.properties")); 
		baseUrl = proptiesFile.getProperty("BASEURL");
		userName = proptiesFile.getProperty("USERNAME");
		password = proptiesFile.getProperty("PASSWORD");

		}
		catch(FileNotFoundException e){
			System.out.println("Unable to read property file");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void customerCreate() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnSubmit")).click();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		WebElement we = driver
				.findElement(By
						.xpath("/.//*[@id='frmMaster']/div[2]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/nav/ul/li[1]/a"));
		action.moveToElement(we)
				.moveToElement(driver.findElement(By.id("companyLink1")))
				.click().build().perform();

		driver.findElement(By.xpath("(//input[@id='rblCompany'])[4]")).click();
		driver.findElement(By.id("btnCompOk")).click();
		driver.findElement(By.id("navTR")).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		action.doubleClick(driver.findElement(By.id("txttranssummaryDate")));
		action.perform();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		/*
		 * use below snippet to select previous month
		 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
		 */
		/*
		 * use below snippet to select next month
		 * driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
		 */
         /*
          * DatePicker is a table.So navigate to each cell If a particular cell
          * matches value 9 then select it
	*/
		WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			// Select 9th Date 
			if (cell.getText().equals("9")) {
				cell.findElement(By.linkText("9")).click();
				break;
			}
		}

		driver.findElement(By.id("imgCC")).click();
		driver.findElement(By.id("cbVoucher")).click();
		driver.findElement(By.id("txtCustomerName")).clear();
		driver.findElement(By.id("txtCustomerName")).sendKeys("AutomateTest");
		driver.findElement(By.id("txtCustomerNameTamil")).clear();
		driver.findElement(By.id("txtCustomerNameTamil")).sendKeys(
				"AutomateTest");
		driver.findElement(By.id("txtCustomerCity")).clear();
		driver.findElement(By.id("txtCustomerCity")).sendKeys("Server");
		driver.findElement(By.id("txtCustomerCityTamil")).clear();
		driver.findElement(By.id("txtCustomerCityTamil")).sendKeys("Server");
		driver.findElement(By.id("txtCustomerMobileNo")).clear();
		driver.findElement(By.id("txtCustomerMobileNo")).sendKeys(
				randomNumber.concat(randomNumber));
		driver.findElement(By.id("btnCustomerSave")).click();
		assertEquals("Customer details saved successfully",
				closeAlertAndGetItsText());

		WebElement welogout = driver
				.findElement(By
						.xpath("/.//*[@id='frmMaster']/div[2]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/nav/ul/li[1]/a"));
		action.moveToElement(welogout)
				.moveToElement(driver.findElement(By.id("logOut"))).click()
				.build().perform();

	}

	@Test
	public void customerUpdate() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnSubmit")).click();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		WebElement we = driver
				.findElement(By
						.xpath("/.//*[@id='frmMaster']/div[2]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/nav/ul/li[1]/a"));
		action.moveToElement(we)
				.moveToElement(driver.findElement(By.id("companyLink1")))
				.click().build().perform();

		 

		driver.findElement(By.xpath("(//input[@id='rblCompany'])[4]")).click();
		driver.findElement(By.id("btnCompOk")).click();
		driver.findElement(By.id("navTR")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		action.doubleClick(driver.findElement(By.id("txttranssummaryDate")));
		action.perform();

		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

		WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			// Select 9th Date
			if (cell.getText().equals("9")) {
				cell.findElement(By.linkText("9")).click();
				break;
			}
		}

		driver.findElement(By.id("imgCU")).click();
		driver.findElement(By.id("txtacCustomerName")).sendKeys(
				"stephenthangavel");
		driver.findElement(By.id("txtCustomerCity")).clear();
		driver.findElement(By.id("txtCustomerCity")).sendKeys("velur");
		driver.findElement(By.id("txtCustomerCityTamil")).clear();
		driver.findElement(By.id("txtCustomerCityTamil")).sendKeys("velur");
		driver.findElement(By.id("btnCustomerSave")).click();
		assertEquals("Customer details updated successfully",
				closeAlertAndGetItsText());

		WebElement welogout = driver
				.findElement(By
						.xpath("/.//*[@id='frmMaster']/div[2]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/nav/ul/li[1]/a"));
		action.moveToElement(welogout)
				.moveToElement(driver.findElement(By.id("logOut"))).click()
				.build().perform();

	}

	@Test
	public void loanEntryDaily() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnSubmit")).click();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		WebElement we = driver
				.findElement(By
						.xpath("/.//*[@id='frmMaster']/div[2]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/nav/ul/li[1]/a"));
		action.moveToElement(we)
				.moveToElement(driver.findElement(By.id("companyLink1")))
				.click().build().perform();

		driver.findElement(By.xpath("(//input[@id='rblCompany'])[4]")).click();
		driver.findElement(By.id("btnCompOk")).click();
		driver.findElement(By.id("navTR")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		action.doubleClick(driver.findElement(By.id("txttranssummaryDate")));
		action.perform();

		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

		/*
		 * DatePicker is a table.So navigate to each cell If a particular cell
		 * matches value 9 then select it
		 */
		WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			// Select 9th Date
			if (cell.getText().equals("9")) {
				cell.findElement(By.linkText("9")).click();
				break;
			}
		}
		driver.findElement(By.id("imgDL")).click();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.findElement(By.id("imgLoan")).click();

		driver.findElement(By.id("txtPartyName")).clear();
		driver.findElement(By.id("txtPartyName")).sendKeys("ANUPRIYA");
		new Select(driver.findElement(By.id("ddlLoanType")))
				.selectByVisibleText("Daily Collection");
		driver.findElement(By.id("txtLoanAmount")).clear();
		driver.findElement(By.id("txtLoanAmount")).sendKeys(randomNumber);
		driver.findElement(By.id("btnLoanEntrySave")).click();
		assertEquals("Loan details saved successfully",
				closeAlertAndGetItsText());
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

	}

	@Test
	public void loanEntryWeekly() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnSubmit")).click();

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		Actions action = new Actions(driver);

		WebElement we = driver
				.findElement(By
						.xpath("/.//*[@id='frmMaster']/div[2]/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/nav/ul/li[1]/a"));
		action.moveToElement(we)
				.moveToElement(driver.findElement(By.id("companyLink1")))
				.click().build().perform();
 
		driver.findElement(By.xpath("(//input[@id='rblCompany'])[4]")).click();
		driver.findElement(By.id("btnCompOk")).click();
		driver.findElement(By.id("navTR")).click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		action.doubleClick(driver.findElement(By.id("txttranssummaryDate")));
		action.perform();

		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

		/*
		 * DatePicker is a table.So navigate to each cell If a particular cell
		 * matches value 9 then select it
		 */
		WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			// Select 9th Date
			if (cell.getText().equals("9")) {
				cell.findElement(By.linkText("9")).click();
				break;
			}
		}
		driver.findElement(By.id("imgDL")).click();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.findElement(By.id("imgLoan")).click();
		driver.findElement(By.id("txtPartyName")).clear();
		driver.findElement(By.id("txtPartyName")).sendKeys("DURAIRAJ");
		new Select(driver.findElement(By.id("ddlLoanType")))
				.selectByVisibleText("Weekly Collection");
		driver.findElement(By.id("txtLoanAmount")).clear();
		driver.findElement(By.id("txtLoanAmount")).sendKeys(
				randomNumber.concat("1"));
		driver.findElement(By.id("btnLoanEntrySave")).click();
		assertEquals("Loan details saved successfully",
				closeAlertAndGetItsText());
	}

	@After
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
