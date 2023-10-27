package mavenpage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class ILoveImg_page {
	WebDriver driver;
	
	By logo=By.xpath("/html/body/header/nav/a/img");
	By login=By.xpath("/html/body/header/nav/div[2]/a[1]");
	By email=By.xpath("//*[@id=\"loginEmail\"]");
	By pass=By.xpath("//*[@id=\"inputPasswordAuth\"]");
	By loginbutton=By.xpath("//*[@id=\"loginBtn\"]");
	By moretools=By.xpath("/html/body/header/nav/div[1]/ul[2]/li/span");
	By compress=By.xpath("/html/body/header/nav/div[1]/ul[2]/li/div/ul/li[1]/ul/li[2]/a");
	By select=By.xpath("//*[@id=\"pickfiles\"]/span");
	By ilovepdf=By.xpath("/html/body/div[2]/div/div[1]/div[2]/ul/li[3]");
	By mergepdf=By.xpath("/html/body/div[1]/div[3]/div/div[1]/a");
	
	
	public ILoveImg_page(WebDriver driver) {
		this.driver=driver;
	}
	public void logocheck() {
		WebElement lo=driver.findElement(logo);
		boolean b=lo.isDisplayed();
		if(b) {
			System.out.println("logo is displayed");
		}
		else {
			System.out.println("logo is not displayed");
		}
	}
	
	public void titleverify() {
		String acttitle=driver.getTitle();
		System.out.println(acttitle);
		String title="iloveimg";
		if(acttitle.equals(title)) {
			System.out.println("same title");
		}
		else {
			System.out.println("different title");
		}
	}
	
	public void contentverify() {
		String conten=driver.getPageSource();
		if(conten.contains("Login")) {
			System.out.println("content found");
		}
		else {
			System.out.println("content not found");
		}
	}
	
	public void datadrivenlogin() throws IOException {
		driver.findElement(login).click();
		File f=new File("E:\\iloveimg.xlsx");
		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("Sheet1");
		System.out.println(sh.getLastRowNum());
		
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			String username=sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println(username);
			String pswd=sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println(pswd);
			
			
			driver.findElement(email).sendKeys(username);
			driver.findElement(pass).sendKeys(pswd);
			driver.findElement(loginbutton).click();
			driver.navigate().refresh();
		}
	}
	
	public void screenshot() throws IOException {
		File fi=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(fi, new File("D://pageshot.png"));
	}
	
	public void mouseover() {
		WebElement a=driver.findElement(moretools);
		Actions act=new Actions(driver);
		act.moveToElement(a).perform();
		driver.findElement(compress).click();
	}
	
	
	
	public void scrolldown() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		
		driver.findElement(ilovepdf);
		
	}
	
	public void windowhandle() {
		String parentWindow=driver.getWindowHandle();
		System.out.println("parent window Title " +driver.getTitle());
		driver.findElement(ilovepdf).click();
		
		Set<String> allwindowhandles=driver.getWindowHandles();
		    
		for(String handle:allwindowhandles)
		{
			System.out.println(handle);
			
			if(!handle.equalsIgnoreCase(parentWindow))
			{
				driver.switchTo().window(handle);
				driver.findElement(mergepdf).click();
				driver.close();
			
			}
			driver.switchTo().window(parentWindow);
		}
	}
	
	public void fileupload() throws Exception {
		driver.findElement(select).click();
		fileUpload("E:\\ME.jpg");
	}
	private void fileUpload(String m) throws AWTException {
		StringSelection strSelection=new StringSelection(m);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
		
		Robot robot=new Robot();
		robot.delay(3000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
}
