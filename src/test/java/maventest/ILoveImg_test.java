package maventest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import mavenpage.ILoveImg_page;

public class ILoveImg_test {
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
	}
	
	@BeforeMethod
	public void url() {
		driver.get("https://www.iloveimg.com/");
	}
	
	@Test
	public void teston() throws Exception {
		ILoveImg_page mi=new ILoveImg_page(driver);
		driver.manage().window().maximize();
		mi.logocheck();
		mi.titleverify();
		mi.contentverify();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		mi.datadrivenlogin();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		mi.scrolldown();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		mi.screenshot();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		mi.windowhandle();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		mi.mouseover();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		mi.fileupload();
		
		
		
		
		
	}

}
