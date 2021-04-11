package Excelpackage;
import java.io.IOException;
import java.util.List;
import Excelpackage.Xls_Reader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Newtest {

	public static WebDriver driver = null;
	String beforeXpath;
	String afterXpath;
	String actualXpath;
	XSSFRow rowValue;
	
@Test
public void junier() throws IOException, InterruptedException
{
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
    
    String excelFilePath = "C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Excel\\Exceldata.xlsx";
    Xls_Reader reader = new Xls_Reader (excelFilePath);
    reader.addSheet("sheetname");
    reader.addColumn("sheetname", "Name");
    
    for (int i=1; i<=namecount; i++)
    {
    	
    	String actualXpath = beforeXpath+i+afterXpath;
    	String Element = driver.findElement(By.xpath(actualXpath)).getText();
    	System.out.println(Element);
    	reader.setCellData("sheetname","Name", i, Element);
		
	}
    driver.close();
}}
