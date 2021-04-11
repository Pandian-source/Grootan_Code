package ExcelRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
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

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;


public class Loginpage {

	public static WebDriver driver = null;
	XSSFRow Value;


	@Test
	public void Openbrowser() throws IOException, InterruptedException, InvalidFormatException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Pandiyan\\Documents\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Window maximised");
		String baseUrl = "https://b2bui2.tltid.com/login";
		String expectedTitle = "Login";
		String actualTitle = "";
		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		if (actualTitle.contentEquals(expectedTitle))
		{
			System.out.println("Got the Title");

		} 
		else 
		{
			System.out.println("not got the Title");

		} 	 
		System.out.println("driver="+driver);
		WebDriverWait wait =new WebDriverWait(driver,100);
		Thread.sleep(10);
		WebElement Element= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		String b = Element.getText();
		System.out.println(b);
		Element.click();
		if(b.contentEquals("Login"))
		{
			System.out.println("Text Equal");
		}
		else
		{
			System.out.println("Text Not Equal");
		}

		File file = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Excel\\Writedata.xlsx");
		FileInputStream inputStream = new FileInputStream(file); 
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);	
		@SuppressWarnings("unused")
		CreationHelper createHelper = wb.getCreationHelper();
		XSSFSheet sheet = wb.getSheet("DETAILS");
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

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();			
		System.out.println("Row Count of the excel sheet is: "+rowCount);


		System.out.println("driver="+driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		for(int i=1; i<=rowCount; i++) 			
		{
			username.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			password.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			Thread.sleep(2000);
			button.click();
			Thread.sleep(3000);
			if(driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isDisplayed())
			{
			Screenshot Login = new AShot().takeScreenshot(driver);
			ImageIO.write(Login.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder3\\Login.png"));
			File f = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder3\\Login.png");
			String path = f.getAbsolutePath();
			XSSFCell cell = sheet.getRow(i).createCell(2);	
			cell.setCellValue("FAIL");
			cell.setCellStyle(hlink_style);
			if(f.exists())
			{
				System.out.println("Image File captured ");
				XSSFCell cell1 = sheet.getRow(i).createCell(3);	
				cell1.setCellValue(path);
				cell1.setCellStyle(path_style);
			}
			else 
			{
				System.out.println("Image file not captured ");
				XSSFCell cell1 = sheet.getRow(i).createCell(3);	
				cell1.setCellValue("Image Not Captured");
			}
			FileOutputStream outputStream = new FileOutputStream (file);
			wb.write(outputStream);	
			username.clear();
			password.clear();
		    }
			else
			{
			System.out.println("driver="+driver);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			WebDriverWait wait2 = new WebDriverWait(driver, 100);
			Thread.sleep(30000);
			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='text'])[2]"))).click();
			driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("YVR");
			XSSFCell cell1 = sheet.getRow(i).createCell(2);	
			cell1.setCellValue("PASS");
			System.out.println("driver="+driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebDriverWait wait21 = new WebDriverWait(driver, 10);
			wait21.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='text'])[3]"))).click();
			driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("YYZ");
			FileOutputStream outputStream1 = new FileOutputStream (file);
			wb.write(outputStream1);
			wb.close();
		    }}
		    driver.close();
		    System.out.println("browser closed");
	}
}






