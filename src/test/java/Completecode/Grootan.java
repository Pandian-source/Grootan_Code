package Completecode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.CellStyle;
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

public class Grootan 
{
	public static WebDriver driver = null;
	XSSFRow Value;
	public static String filePath;
	
	@Test
	public void test() throws IOException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Pandiyan\\Documents\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Window maximised");
		String baseUrl = "https://www.grootan.com/";
		String expectedTitle = "Grootan Technologies";
		String actualTitle = "";
		driver.get(baseUrl);
		actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		if (actualTitle.contentEquals(expectedTitle))
		{
			System.out.println("Got the correct Page title ");
		} 
		else 
		{
			System.out.println("Failed to get the correct page title");

		}
		
		File file = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Excel\\Exceldata.xlsx");
		FileInputStream inputStream = new FileInputStream(file); 
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);	
		XSSFSheet sheet = wb.getSheet("TSR");
		CellStyle hlink_style = wb.createCellStyle();
		Font hlink_font = wb.createFont();
		hlink_font.setUnderline(Font.DEFAULT_CHARSET);
		hlink_font.setColor(IndexedColors.RED.getIndex());
		hlink_style.setFont(hlink_font);

		CellStyle path_style = wb.createCellStyle();
		Font path_font  = wb.createFont();
		path_font.setUnderline(Font.U_SINGLE);
		path_font.setColor(IndexedColors.BLUE.getIndex());
		path_style.setFont(path_font);
		
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();			
		System.out.println("Row Count of the excel sheet is: "+rowCount);
			
	    WebDriverWait wait =new WebDriverWait(driver,100);
		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-current='page']")));
		Home.click();
		Screenshot home = new AShot().takeScreenshot(driver);
		ImageIO.write(home.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Home.png"));
		File f = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Home.png");
		String path = f.getAbsolutePath();
		if(f.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell HOM1 = sheet.createRow(1).createCell(0);
			HOM1.setCellValue("Homepage clicked and screenshots taken successfully");
			XSSFCell HOM2 = sheet.getRow(1).createCell(1);
			HOM2.setCellValue("PASS");
			XSSFCell HOM3 = sheet.getRow(1).createCell(2);
			HOM3.setCellValue(path);
			HOM3.setCellStyle(path_style);
			
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell HOM1 = sheet.createRow(1).createCell(0);
			HOM1.setCellValue("Home page screenshots Not Taken");
			XSSFCell HOM2 = sheet.createRow(1).createCell(1);
			HOM2.setCellValue("FAIL");
			XSSFCell HOM3 = sheet.createRow(1).createCell(2);
			HOM3.setCellValue("No Path");
			HOM3.setCellStyle(path_style);
		}
		WebDriverWait wait1 =new WebDriverWait(driver,100);
		WebElement services = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/#built-tech']")));
		services.click();
		driver.findElement(By.xpath("//h2[contains(.,'What We Do')]")).getText();
		Screenshot service = new AShot().takeScreenshot(driver);
		ImageIO.write(service.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Services.png"));
		File f1 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Services.png");
		String pathservice = f1.getAbsolutePath();
		if(f1.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell SER = sheet.createRow(2).createCell(0);
			SER.setCellValue("Service page clicked and screenshots taken successfully");
			XSSFCell SER2 = sheet.getRow(2).createCell(1);
			SER2.setCellValue("PASS");
			XSSFCell SER3 = sheet.getRow(2).createCell(2);
			SER3.setCellValue(pathservice);
			SER3.setCellStyle(path_style);
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell SER = sheet.createRow(2).createCell(0);
			SER.setCellValue("Service page screenshots Not taken");
			XSSFCell SER2 = sheet.getRow(2).createCell(1);
			SER2.setCellValue("FAIL");
			XSSFCell SER3 = sheet.getRow(2).createCell(2);
			SER3.setCellValue("No Path");
			SER3.setCellStyle(path_style);
		}
		
		WebDriverWait wait2 =new WebDriverWait(driver,100);
		WebElement opensource = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/opensource']")));
		opensource.click();
		Screenshot Opensource = new AShot().takeScreenshot(driver);
		ImageIO.write(Opensource.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Opensource.png"));
		File f2 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\opensource.png");
		String pathopen = f2.getAbsolutePath();
		if(f2.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell OPN = sheet.createRow(3).createCell(0);
			OPN.setCellValue("Opensource page clicked and screenshots taken successfully");
			XSSFCell OPN2 = sheet.getRow(3).createCell(1);
			OPN2.setCellValue("PASS");
			XSSFCell OPN3 = sheet.getRow(3).createCell(2);
			OPN3.setCellValue(pathopen);
			OPN3.setCellStyle(path_style);
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell OPN = sheet.createRow(3).createCell(0);
			OPN.setCellValue("Opensource page screenshots Not taken");
			XSSFCell OPN2 = sheet.getRow(3).createCell(1);
			OPN2.setCellValue("FAIL");
			XSSFCell OPN3 = sheet.getRow(3).createCell(2);
			OPN3.setCellValue("No Path");
			OPN3.setCellStyle(path_style);
		}
		String parent = driver.getWindowHandle();
		System.out.println("Parent window is"+parent);
		WebDriverWait wait3 =new WebDriverWait(driver,100);
		WebElement Blog = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://blog.grootan.com']")));
		Blog.click();
		Set<String>Windows = driver.getWindowHandles();
		for(String child:Windows)
		{
			if(!parent.equalsIgnoreCase(child))
			{
				driver.switchTo().window(child);
			}
		}
		WebElement Text = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(.,'Groot Insights')]")));
		Text.getText();
		Screenshot blog = new AShot().takeScreenshot(driver);
		ImageIO.write(blog.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Blog.png"));
		File f3 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Blog.png");
		String pathblog = f3.getAbsolutePath();
		if(f3.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell BLG = sheet.createRow(4).createCell(0);
			BLG.setCellValue("Blog page clicked and screenshots taken successfully");
			XSSFCell BLG2 = sheet.getRow(4).createCell(1);
			BLG2.setCellValue("PASS");
			XSSFCell BLG3 = sheet.getRow(4).createCell(2);
			BLG3.setCellValue(pathblog);
			BLG3.setCellStyle(path_style);
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell BLG = sheet.createRow(4).createCell(0);
			BLG.setCellValue("Blog page screenshots Not taken");
			XSSFCell BLG2 = sheet.getRow(4).createCell(1);
			BLG2.setCellValue("FAIL");
			XSSFCell BLG3 = sheet.getRow(4).createCell(2);
			BLG3.setCellValue("No Path");
			BLG3.setCellStyle(path_style);
		} 
		driver.close();
		driver.switchTo().window(parent);
		WebDriverWait wait4 =new WebDriverWait(driver,100);
		WebElement Team = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
		Team.click();
		Screenshot team = new AShot().takeScreenshot(driver);
		ImageIO.write(team.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Team.png"));
		File f4 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Team.png");
		String pathteam = f4.getAbsolutePath();
		if(f4.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell TEA = sheet.createRow(5).createCell(0);
			TEA.setCellValue("Team page clicked and screenshots taken successfully");
			XSSFCell TEA2 = sheet.getRow(5).createCell(1);
			TEA2.setCellValue("PASS");
			XSSFCell TEA3 = sheet.getRow(5).createCell(2);
			TEA3.setCellValue(pathteam);
			TEA3.setCellStyle(path_style);
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell TEA = sheet.createRow(5).createCell(0);
			TEA.setCellValue("Team page screenshots Not taken");
			XSSFCell TEA2 = sheet.getRow(5).createCell(1);
			TEA2.setCellValue("FAIL");
			XSSFCell TEA3 = sheet.getRow(5).createCell(2);
			TEA3.setCellValue("No Path");
			TEA3.setCellStyle(path_style);
		}
		WebDriverWait wait5 =new WebDriverWait(driver,100);
		WebElement Careers = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/careers']")));
		Careers.click();
		Screenshot careers = new AShot().takeScreenshot(driver);
		ImageIO.write(careers.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Careers.png"));
		File f5 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Careers.png");
		String pathcaree = f5.getAbsolutePath();
		if(f5.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell CAR = sheet.createRow(6).createCell(0);
			CAR.setCellValue("careers page clicked and screenshots taken successfully");
			XSSFCell CAR2 = sheet.getRow(6).createCell(1);
			CAR2.setCellValue("PASS");
			XSSFCell CAR3 = sheet.getRow(6).createCell(2);
			CAR3.setCellValue(pathcaree);
			CAR3.setCellStyle(path_style);
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell CAR = sheet.createRow(6).createCell(0);
			CAR.setCellValue("careers page screenshots Not taken");
			XSSFCell CAR2 = sheet.getRow(6).createCell(1);
			CAR2.setCellValue("FAIL");
			XSSFCell CAR3 = sheet.getRow(6).createCell(2);
			CAR3.setCellValue("NO Path");
			CAR3.setCellStyle(path_style);
		}
		WebDriverWait wait6 =new WebDriverWait(driver,100);
		WebElement Contactus = wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/contactus']")));
		Contactus.click();
		Screenshot contact = new AShot().takeScreenshot(driver);
		ImageIO.write(contact.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Contactus.png"));
		File f6 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Contactus.png");
		String pathcon = f6.getAbsolutePath();
		if(f6.exists())
		{
			System.out.println("Image File captured ");
			XSSFCell CON = sheet.createRow(7).createCell(0);
			CON.setCellValue("Contactus page clicked and screenshots taken successfully");
			XSSFCell CON2 = sheet.getRow(7).createCell(1);
			CON2.setCellValue("PASS");
			XSSFCell CON3 = sheet.getRow(7).createCell(2);
			CON3.setCellValue(pathcon);
			CON3.setCellStyle(path_style);
		}
		else
		{
			System.out.println("Image file not captured ");
			XSSFCell CON = sheet.createRow(7).createCell(0);
			CON.setCellValue("Contactus page screenshots Not taken");
			XSSFCell CON2 = sheet.getRow(7).createCell(1);
			CON2.setCellValue("PASS");
			XSSFCell CON3 = sheet.getRow(7).createCell(2);
			CON3.setCellValue(pathcon);
			CON3.setCellStyle(path_style);
		}
		FileOutputStream outputStream = new FileOutputStream (file);
		wb.write(outputStream);	
		driver.close();
	}
	
	}
	
	
	
	

	

