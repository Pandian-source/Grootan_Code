package Completecode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

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
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class Rerun 
{
	public static WebDriver driver = null;
	XSSFRow Value;
	public static String filePath;

	@Test
	public void grootanrerunandcompare() throws IOException
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
		@SuppressWarnings("unused")
		CreationHelper createHelper = wb.getCreationHelper();
		XSSFSheet sheet = wb.getSheet("TSR");
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
		
		
		WebDriverWait wait =new WebDriverWait(driver,100);
		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-current='page']")));
		Home.click();
		Screenshot home = new AShot().takeScreenshot(driver);
		ImageIO.write(home.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Home.png"));
		BufferedImage actualImage = home.getImage();
		File f = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Home.png");
		String path = f.getAbsolutePath();
		BufferedImage expectedImage = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Home.png"));
		ImageDiffer imDiff = new ImageDiffer();
		ImageDiff diff = imDiff.makeDiff(actualImage, expectedImage);
		if(diff.hasDiff()==true)
		{
			XSSFCell HOM1 = sheet.createRow(8).createCell(0);
			HOM1.setCellValue("Images are not same");
			XSSFCell HOM2 = sheet.getRow(8).createCell(1);
			HOM2.setCellValue("FAIL");
			XSSFCell HOM3 = sheet.getRow(8).createCell(2);
			HOM3.setCellValue(path);
			HOM3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell HOM1 = sheet.createRow(8).createCell(0);
			HOM1.setCellValue("Images are same");
			XSSFCell HOM2 = sheet.getRow(8).createCell(1);
			HOM2.setCellValue("PASS");
			XSSFCell HOM3 = sheet.getRow(8).createCell(2);
			HOM3.setCellValue(path);
			HOM3.setCellStyle(path_style);
		}
		
		WebDriverWait wait1 =new WebDriverWait(driver,100);
		WebElement services = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/#built-tech']")));
		services.click();
		driver.findElement(By.xpath("//h2[contains(.,'What We Do')]")).getText();
		Screenshot service = new AShot().takeScreenshot(driver);
		ImageIO.write(service.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Services.png"));
		BufferedImage actualImageservice = service.getImage();
		File f1 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Services.png");
		String pathservice = f1.getAbsolutePath();
		BufferedImage expectedImageservice = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Services.png"));
		ImageDiffer imDiff1 = new ImageDiffer();
		ImageDiff diff1 = imDiff1.makeDiff(actualImageservice, expectedImageservice);
		if(diff1.hasDiff()==true)
		{
			XSSFCell SER = sheet.createRow(9).createCell(0);
			SER.setCellValue("Images are not same");
			XSSFCell SER2 = sheet.getRow(9).createCell(1);
			SER2.setCellValue("FAIL");
			XSSFCell SER3 = sheet.getRow(9).createCell(2);
			SER3.setCellValue(pathservice);
			SER3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell SER = sheet.createRow(9).createCell(0);
			SER.setCellValue("Images are same");
			XSSFCell SER2 = sheet.getRow(9).createCell(1);
			SER2.setCellValue("PASS");
			XSSFCell SER3 = sheet.getRow(9).createCell(2);
			SER3.setCellValue(pathservice);
			SER3.setCellStyle(path_style);
		}
		
		WebDriverWait wait2 =new WebDriverWait(driver,100);
		WebElement opensource = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/opensource']")));
		opensource.click();
		Screenshot Opensource = new AShot().takeScreenshot(driver);
		ImageIO.write(Opensource.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Opensource.png"));
		BufferedImage actualImagesource = Opensource.getImage();
		File f2 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Opensource.png");
		String pathopen = f2.getAbsolutePath();
		BufferedImage expectedsource = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Opensource.png"));
		ImageDiffer imDiff2 = new ImageDiffer();
		ImageDiff diff2 = imDiff2.makeDiff(actualImagesource, expectedsource);
		if(diff2.hasDiff()==true)
		{
			XSSFCell OPN = sheet.createRow(10).createCell(0);
			OPN.setCellValue("Images are not same");
			XSSFCell OPN2 = sheet.getRow(10).createCell(1);
			OPN2.setCellValue("FAIL");
			XSSFCell OPN3 = sheet.getRow(10).createCell(2);
			OPN3.setCellValue(pathopen);
			OPN3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell OPN = sheet.createRow(10).createCell(0);
			OPN.setCellValue("Images are same");
			XSSFCell OPN2 = sheet.getRow(10).createCell(1);
			OPN2.setCellValue("PASS");
			XSSFCell OPN3 = sheet.getRow(10).createCell(2);
			OPN3.setCellValue(pathopen);
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
		ImageIO.write(blog.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Blog.png"));
		BufferedImage actualImageblog = blog.getImage();
		File f3 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Blog.png");
		String pathblog = f3.getAbsolutePath();
		BufferedImage expectedblog = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Blog.png"));
		ImageDiffer imDiff3 = new ImageDiffer();
		ImageDiff diff3 = imDiff3.makeDiff(actualImageblog, expectedblog);
		if(diff3.hasDiff()==true)
		{
			XSSFCell BLG = sheet.createRow(11).createCell(0);
			BLG.setCellValue("Images are not same");
			XSSFCell BLG2 = sheet.getRow(11).createCell(1);
			BLG2.setCellValue("FAIL");
			XSSFCell BLG3 = sheet.getRow(11).createCell(2);
			BLG3.setCellValue(pathblog);
			BLG3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell BLG = sheet.createRow(11).createCell(0);
			BLG.setCellValue("Images are same");
			XSSFCell BLG2 = sheet.getRow(11).createCell(1);
			BLG2.setCellValue("PASS");
			XSSFCell BLG3 = sheet.getRow(11).createCell(2);
			BLG3.setCellValue(pathblog);
			BLG3.setCellStyle(path_style);
			
		}
		driver.close();
		driver.switchTo().window(parent);
		WebDriverWait wait4 =new WebDriverWait(driver,100);
		WebElement Team = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
		Team.click();
		Screenshot team = new AShot().takeScreenshot(driver);
		ImageIO.write(team.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Team.png"));
		BufferedImage actualImageteam = team.getImage();
		File f4 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Team.png");
		String pathteam = f4.getAbsolutePath();
		BufferedImage expectedteam = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Team.png"));
		ImageDiffer imDiff4 = new ImageDiffer();
		ImageDiff diff4 = imDiff4.makeDiff(actualImageteam, expectedteam);
		if(diff4.hasDiff()==true)
		{
			XSSFCell TEA = sheet.createRow(12).createCell(0);
			TEA.setCellValue("Images are not same");
			XSSFCell TEA2 = sheet.getRow(12).createCell(1);
			TEA2.setCellValue("FAIL");
			XSSFCell TEA3 = sheet.getRow(12).createCell(2);
			TEA3.setCellValue(pathteam);
			TEA3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell TEA = sheet.createRow(12).createCell(0);
			TEA.setCellValue("Images are same");
			XSSFCell TEA2 = sheet.getRow(12).createCell(1);
			TEA2.setCellValue("PASS");
			XSSFCell TEA3 = sheet.getRow(12).createCell(2);
			TEA3.setCellValue(pathteam);
			TEA3.setCellStyle(path_style);
		}
		WebDriverWait wait5 =new WebDriverWait(driver,100);
		WebElement Careers = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/careers']")));
		Careers.click();
		Screenshot careers = new AShot().takeScreenshot(driver);
		ImageIO.write(careers.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Careers.png"));
		BufferedImage actualImagecareer = careers.getImage();
		File f5 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Careers.png");
		String pathcaree = f5.getAbsolutePath();
		BufferedImage expectedcareer = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Team.png"));
		ImageDiffer imDiff5 = new ImageDiffer();
		ImageDiff diff5 = imDiff5.makeDiff(actualImagecareer, expectedcareer);
		if(diff5.hasDiff()==true)
		{
			XSSFCell CAR = sheet.createRow(13).createCell(0);
			CAR.setCellValue("Images are not same");
			XSSFCell CAR2 = sheet.getRow(13).createCell(1);
			CAR2.setCellValue("FAIL");
			XSSFCell CAR3 = sheet.getRow(13).createCell(2);
			CAR3.setCellValue(pathcaree);
			CAR3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell CAR = sheet.createRow(13).createCell(0);
			CAR.setCellValue("Images are same");
			XSSFCell CAR2 = sheet.getRow(13).createCell(1);
			CAR2.setCellValue("PASS");
			XSSFCell CAR3 = sheet.getRow(13).createCell(2);
			CAR3.setCellValue(pathcaree);
			CAR3.setCellStyle(path_style);
		}
		WebDriverWait wait6 =new WebDriverWait(driver,100);
		WebElement Contactus = wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/contactus']")));
		Contactus.click();
		Screenshot contact = new AShot().takeScreenshot(driver);
		ImageIO.write(contact.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Contactus.png"));
		BufferedImage actualImagecontact = careers.getImage();
		File f6 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder2\\Contactus.png");
		String pathcon = f6.getAbsolutePath();
		BufferedImage expectedcontact = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Folder1\\Team.png"));
		ImageDiffer imDiff6 = new ImageDiffer();
		ImageDiff diff6 = imDiff6.makeDiff(actualImagecontact, expectedcontact);
		if(diff6.hasDiff()==true)
		{
			XSSFCell CON = sheet.createRow(14).createCell(0);
			CON.setCellValue("Images are not same");
			XSSFCell CON2 = sheet.getRow(14).createCell(1);
			CON2.setCellValue("FAIL");
			XSSFCell CON3 = sheet.getRow(14).createCell(2);
			CON3.setCellValue(pathcon);
			CON3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell CON = sheet.createRow(14).createCell(0);
			CON.setCellValue("Images are same");
			XSSFCell CON2 = sheet.getRow(14).createCell(1);
			CON2.setCellValue("PASS");
			XSSFCell CON3 = sheet.getRow(14).createCell(2);
			CON3.setCellValue(pathcon);
			CON3.setCellStyle(path_style);
		}
		FileOutputStream outputStream = new FileOutputStream (file);
		wb.write(outputStream);	
		driver.close();
		
	}
}
