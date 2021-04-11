package Grootanpackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Testname {
	
	public static WebDriver driver = null;
	String beforeXpath;
	String afterXpath;
	String actualXpath;
	XSSFRow rowValue;
	
@Test
public void junier() throws IOException, InterruptedException
{
	
	File file = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Excel\\Exceldata.xlsx");
	@SuppressWarnings("resource")
	XSSFWorkbook wb = new XSSFWorkbook();
	XSSFSheet sheet = wb.createSheet("Junier Engineer");
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Pandiyan\\Documents\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	System.out.println("Window maximised");
	String baseUrl = "https://www.grootan.com/";
	driver.get(baseUrl);
	WebDriverWait wait =new WebDriverWait(driver,100);
	WebElement Team = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
    Team.click();
    Thread.sleep(2000);
    
    String beforeXpath = "(//h5[contains(.,'Junior Engineer')])[";
    String afterXpath  = "]/preceding::h3[1]";
    
    List<WebElement> name = driver.findElements(By.xpath("(//h5[contains(.,'Junior Engineer')])"));
    System.out.println("Name count is = "+ name.size());
    int namecount = name.size();
    
    for (int i=1; i<=namecount; i++)
    {
    	for (int j=0; j<=0; j++)
    	{
    	XSSFRow rowValue = sheet.createRow(i);
    	String actualXpath = beforeXpath+i+afterXpath;
    	String Element = driver.findElement(By.xpath(actualXpath)).getText();
    	System.out.println(Element);
		rowValue.createCell(j).setCellValue(Element);
	}
	FileOutputStream fos = new FileOutputStream(file);
	wb.write(fos);
	}
    driver.close();
}}

