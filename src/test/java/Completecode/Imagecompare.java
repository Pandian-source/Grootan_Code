package Completecode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
public class Imagecompare {
	
	public static WebDriver driver = null;
	
	@Test
	public void comparison() throws IOException, InterruptedException
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
		
	    WebDriverWait wait =new WebDriverWait(driver,20);
		WebElement Team = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
		Team.click();
		Thread.sleep(5000);
		WebElement photo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/img/testimonials/lokesh.jpg']")));
		Screenshot check = new AShot().takeScreenshot(driver,photo);
		ImageIO.write(check.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\CTO.png"));
		File f4 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\CTO.png");
		String CTO = f4.getAbsolutePath();
		WebElement photo1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/img/testimonials/sasi.jpg']")));
		Screenshot check1 = new AShot().takeScreenshot(driver,photo1);
		ImageIO.write(check1.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\HR.png"));
		File f5 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\HR.png");
		String HR = f5.getAbsolutePath();
		if(f4.exists())
		{
			if (f5.exists())
			{
			System.out.println("Image File captured ");
		}}
		else
		{
			System.out.println("Image file not captured ");
		}
		BufferedImage expectedImage = ImageIO.read(new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\CTO.png"));
		BufferedImage actualImage = check1.getImage();
		ImageDiffer imDiff = new ImageDiffer();
		ImageDiff diff = imDiff.makeDiff(actualImage, expectedImage);
		if(diff.hasDiff()==true)
		{
			XSSFCell OPN = sheet.createRow(15).createCell(0);
			OPN.setCellValue("Images are not same");
			OPN.setCellStyle(hlink_style);
			XSSFCell OPN2 = sheet.getRow(15).createCell(1);
			OPN2.setCellValue("FAIL");
			XSSFCell OPN3 = sheet.getRow(15).createCell(2);
			OPN3.setCellValue(CTO + "and"+ HR);
			OPN3.setCellStyle(path_style);
		}
		else
		{
			XSSFCell OPN = sheet.createRow(15).createCell(0);
			OPN.setCellValue("Images are same");
			XSSFCell OPN2 = sheet.getRow(15).createCell(1);
			OPN2.setCellValue("PASS");
			XSSFCell OPN3 = sheet.getRow(15).createCell(2);
			OPN3.setCellValue(CTO + "and"+ HR);
			OPN3.setCellStyle(path_style);
		}
		FileOutputStream outputStream = new FileOutputStream (file);
		wb.write(outputStream);	
		driver.close();
}}
