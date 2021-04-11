package Completecode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
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

public class Junierengineer {

	public static WebDriver driver = null;
	XSSFRow Value;
	public static String filePath;


	@Test
	public static void Engineer() throws IOException, InterruptedException, InvalidFormatException
	{

		File file = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Excel\\Exceldata.xlsx");
		FileInputStream inputStream = new FileInputStream(file); 
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);	
		@SuppressWarnings("unused")
		CreationHelper createHelper = wb.getCreationHelper();
		XSSFSheet sheet = wb.getSheet("JuniorEngineers");

		CellStyle hlink_style = wb.createCellStyle();
		Font hlink_font = wb.createFont();
		hlink_font.setUnderline(Font.DEFAULT_CHARSET);
		hlink_font.setColor(IndexedColors.RED.getIndex());
		hlink_style.setFont(hlink_font);

		CellStyle path_style = wb.createCellStyle();
		Font path_font = wb.createFont();
		path_font.setUnderline(Font.U_SINGLE);
		path_font.setColor(IndexedColors.BLUE.getIndex());
		path_style.setFont(path_font);


		System.setProperty("webdriver.chrome.driver","C:\\Users\\Pandiyan\\Documents\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Window maximised");
		String baseUrl = "https://www.grootan.com/";
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver,100);
		WebElement Team = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
		Team.click();
		Thread.sleep(2000);
		String beforeXpath = "(//h5[contains(.,'Junior Engineer')])[";
		String afterXpath  = "]/preceding::h3[1]";
		
		List<WebElement> name = driver.findElements(By.xpath("(//h5[contains(.,'Junior Engineer')])"));
		System.out.println("Name count is = "+ name.size());
		int namecount = name.size();
		for (int i=0; i<=namecount; i++)
	    {
	    	
	    	XSSFRow rowValue = sheet.createRow(i);
	    	if (i == 0)
	    	{
	    		
	    		rowValue.createCell(0).setCellValue("NAME");
	    		rowValue.createCell(1).setCellValue("STATUS");
	    		rowValue.getCell(0).setCellStyle(hlink_style);
	    		rowValue.getCell(1).setCellStyle(hlink_style);
	    		
	    	}
	    	else
	    	{
	    	String actualXpath = beforeXpath+i+afterXpath;
	    	String Element = driver.findElement(By.xpath(actualXpath)).getText();
	    	System.out.println(Element);
	    	rowValue.createCell(0).setCellValue(Element);
	    	rowValue.createCell(1).setCellValue("Print the name successfully.");
	    
	    	
	    }}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		driver.close();
		SendEmail.mail(null);
		}

	}





