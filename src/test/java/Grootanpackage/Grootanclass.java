package Grootanpackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

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
public class Grootanclass {
	public static WebDriver driver = null;
	
@Test
public void grootan() throws IOException
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
		WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-current='page']")));
		Home.click();
		Screenshot home = new AShot().takeScreenshot(driver);
		ImageIO.write(home.getImage(),"png",new File("D://Folder1//Home.png"));
		File f = new File ("D://Folder1//Home.png");
		if(f.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
		}
		WebDriverWait wait1 =new WebDriverWait(driver,100);
		WebElement services = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/#built-tech']")));
		services.click();
		driver.findElement(By.xpath("//h2[contains(.,'What We Do')]")).getText();
		Screenshot service = new AShot().takeScreenshot(driver);
		ImageIO.write(service.getImage(),"png",new File("D://Folder1//Services.png"));
		File f1 = new File ("D://Folder1//Services.png");
		if(f1.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
		}
		WebDriverWait wait2 =new WebDriverWait(driver,100);
		WebElement opensource = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/opensource']")));
		opensource.click();
		Screenshot Opensource = new AShot().takeScreenshot(driver);
		ImageIO.write(Opensource.getImage(),"png",new File("D://Folder1//Opensource.png"));
		File f2 = new File ("D://Folder1//opensource.png");
		if(f2.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
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
			ImageIO.write(blog.getImage(),"png",new File("D://Folder1//Blog.png"));
			File f3 = new File ("D://Folder1//Blog.png");
			if(f3.exists())
			{
				System.out.println("Image File captured ");
			}
			else
			{
				System.out.println("Image file not captured ");
		     } 
		driver.close();
		driver.switchTo().window(parent);
		WebDriverWait wait4 =new WebDriverWait(driver,100);
		WebElement Team = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
		Team.click();
		Screenshot team = new AShot().takeScreenshot(driver);
		ImageIO.write(team.getImage(),"png",new File("D://Folder1//Team.png"));
		File f4 = new File ("D://Folder1//Team.png");
		if(f4.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
		}
		WebDriverWait wait5 =new WebDriverWait(driver,100);
		WebElement Careers = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/careers']")));
		Careers.click();
		Screenshot careers = new AShot().takeScreenshot(driver);
		ImageIO.write(careers.getImage(),"png",new File("D://Folder1//Careers.png"));
		File f5 = new File ("D://Folder1//Careers.png");
		if(f5.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
		}
		WebDriverWait wait6 =new WebDriverWait(driver,100);
		WebElement Contactus = wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/contactus']")));
		Contactus.click();
		Screenshot contact = new AShot().takeScreenshot(driver);
		ImageIO.write(contact.getImage(),"png",new File("D://Folder1//Contactus.png"));
		File f6 = new File ("D://Folder1//Careers.png");
		if(f6.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
		}
		driver.close();
		SendMailSSLWithAttachment.mail(null);
       }
@Test
public void grootanrerun() throws IOException
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
	WebElement Home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-current='page']")));
	Home.click();
	Screenshot home = new AShot().takeScreenshot(driver);
	ImageIO.write(home.getImage(),"png",new File("D://Folder2//Home.png"));
	BufferedImage actualImage = home.getImage();
	File f = new File ("D://Folder2//Home.png");
	BufferedImage expectedImage = ImageIO.read(new File("D://Folder1//Home.png"));
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
	if(f.exists())
	{
		System.out.println("Image File captured ");
	}
	else
	{
		System.out.println("Image file not captured ");
	}
	WebDriverWait wait1 =new WebDriverWait(driver,100);
	WebElement services = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/#built-tech']")));
	services.click();
	driver.findElement(By.xpath("//h2[contains(.,'What We Do')]")).getText();
	Screenshot service = new AShot().takeScreenshot(driver);
	ImageIO.write(service.getImage(),"png",new File("D://Folder2//Services.png"));
	BufferedImage actualImageservice = service.getImage();
	File f1 = new File ("D://Services//Home.png");
	BufferedImage expectedImageservice = ImageIO.read(new File("D://Folder1//Services.png"));
    ImageDiffer imDiff1 = new ImageDiffer();
	ImageDiff diff1 = imDiff1.makeDiff(actualImageservice, expectedImageservice);
	if(diff1.hasDiff()==true)
	{
		System.out.println("Images are not same ");
	}
	else
	{
		System.out.println("Images are same ");
	}
	if(f1.exists())
	{
		System.out.println("Image File captured ");
	}
	else
	{
		System.out.println("Image file not captured ");
	}
	WebDriverWait wait2 =new WebDriverWait(driver,100);
	WebElement opensource = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/opensource']")));
	opensource.click();
	Screenshot Opensource = new AShot().takeScreenshot(driver);
	ImageIO.write(Opensource.getImage(),"png",new File("D://Folder2//Opensource.png"));
	BufferedImage actualImagesource = Opensource.getImage();
	File f2 = new File ("D://Folder2//Opensource.png");
	BufferedImage expectedsource = ImageIO.read(new File("D://Folder1//Opensource.png"));
    ImageDiffer imDiff2 = new ImageDiffer();
	ImageDiff diff2 = imDiff2.makeDiff(actualImagesource, expectedsource);
	if(diff2.hasDiff()==true)
	{
		System.out.println("Images are not same ");
	}
	else
	{
		System.out.println("Images are same ");
	}
	if(f2.exists())
	{
		System.out.println("Image File captured ");
	}
	else
	{
		System.out.println("Image file not captured ");
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
		ImageIO.write(blog.getImage(),"png",new File("D://Folder2//Blog.png"));
		BufferedImage actualImageblog = blog.getImage();
		File f3 = new File ("D://Folder2//Blog.png");
		BufferedImage expectedblog = ImageIO.read(new File("D://Folder1//Blog.png"));
	    ImageDiffer imDiff3 = new ImageDiffer();
		ImageDiff diff3 = imDiff3.makeDiff(actualImageblog, expectedblog);
		if(diff3.hasDiff()==true)
		{
			System.out.println("Images are not same ");
		}
		else
		{
			System.out.println("Images are same ");
		}
		if(f3.exists())
		{
			System.out.println("Image File captured ");
		}
		else
		{
			System.out.println("Image file not captured ");
		}
	driver.close();
	driver.switchTo().window(parent);
	WebDriverWait wait4 =new WebDriverWait(driver,100);
	WebElement Team = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/team']")));
	Team.click();
	Screenshot team = new AShot().takeScreenshot(driver);
	ImageIO.write(team.getImage(),"png",new File("D://Folder2//Team.png"));
	BufferedImage actualImageteam = team.getImage();
	File f4 = new File ("D://Folder2//Team.png");
	BufferedImage expectedteam = ImageIO.read(new File("D://Folder1//Team.png"));
    ImageDiffer imDiff4 = new ImageDiffer();
	ImageDiff diff4 = imDiff4.makeDiff(actualImageteam, expectedteam);
	if(diff4.hasDiff()==true)
	{
		System.out.println("Images are not same ");
	}
	else
	{
		System.out.println("Images are same ");
	}
	if(f4.exists())
	{
		System.out.println("Image File captured ");
	}
	else
	{
		System.out.println("Image file not captured ");
	}
	WebDriverWait wait5 =new WebDriverWait(driver,100);
	WebElement Careers = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/careers']")));
	Careers.click();
	Screenshot careers = new AShot().takeScreenshot(driver);
	ImageIO.write(careers.getImage(),"png",new File("D://Folder2//Careers.png"));
	BufferedImage actualImagecareer = careers.getImage();
	File f5 = new File ("D://Folder2//Careers.png");
	BufferedImage expectedcareer = ImageIO.read(new File("D://Folder1//Team.png"));
    ImageDiffer imDiff5 = new ImageDiffer();
	ImageDiff diff5 = imDiff5.makeDiff(actualImagecareer, expectedcareer);
	if(diff5.hasDiff()==true)
	{
		System.out.println("Images are not same ");
	}
	else
	{
		System.out.println("Images are same ");
	}
	if(f5.exists())
	{
		System.out.println("Image File captured ");
	}
	else
	{
		System.out.println("Image file not captured ");
	}
	WebDriverWait wait6 =new WebDriverWait(driver,100);
	WebElement Contactus = wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/contactus']")));
	Contactus.click();
	Screenshot contact = new AShot().takeScreenshot(driver);
	ImageIO.write(contact.getImage(),"png",new File("D://Folder2//Contactus.png"));
	BufferedImage actualImagecontact = careers.getImage();
	File f6 = new File ("D://Folder2//Contactus.png");
	BufferedImage expectedcontact = ImageIO.read(new File("D://Folder1//Team.png"));
    ImageDiffer imDiff6 = new ImageDiffer();
	ImageDiff diff6 = imDiff6.makeDiff(actualImagecontact, expectedcontact);
	if(diff6.hasDiff()==true)
	{
		System.out.println("Images are not same ");
	}
	else
	{
		System.out.println("Images are same ");
	}
	if(f6.exists())
	{
		System.out.println("Image File captured ");
	}
	else
	{
		System.out.println("Image file not captured ");
	}
	driver.close();
	SendMailSSLWithAttachment.mail(null);
   }
}

