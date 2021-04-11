package Grootanpackage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
		WebDriverWait wait =new WebDriverWait(driver,100);
		WebElement Team = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
		Team.click();
		WebElement photo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='img/testimonials/lokesh.jpg']")));
        Thread.sleep(1000);
		Screenshot check = new AShot().takeScreenshot(driver,photo);
		ImageIO.write(check.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\CTO.png"));
		File f4 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\CTO.png");
		WebElement photo1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='img/testimonials/sasi.jpg']")));
		Screenshot check1 = new AShot().takeScreenshot(driver,photo1);
		ImageIO.write(check1.getImage(),"png",new File("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\HR.png"));
		File f5 = new File ("C:\\Users\\Pandiyan\\eclipse-workspace\\Grootan\\Comparison\\HR.png");
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
			System.out.println("Images are not same ");
		}
		else
		{
			System.out.println("Images are same ");
		}
		driver.close();
}}
