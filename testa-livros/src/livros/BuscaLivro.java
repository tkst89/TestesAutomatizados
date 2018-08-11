package livros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BuscaLivro {
	
	public String baseUrl;
	public WebDriver driver;
	public String autor;
	public String autorAmericanas;
	public String autorAmazon;
	public String isbn;
	
	
	@Before
	public void Iniciar() {
		baseUrl = "https://www.submarino.com.br/";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tamlyn\\Desktop\\Tamy\\FTS111\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void Finalizar() {
		driver.quit();
	}
	
	@Test
	public void BuscarProduto() throws InterruptedException {
		driver.get(baseUrl);
		driver.findElement(By.id("h_search-input")).sendKeys("Livros");
		driver.findElement(By.id("h_search-btn")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='content-middle']/div[5]/div/div/div/div[2]/div[1]/section/a/div[1]/figure/div/div/picture/img")).click();
		autor = driver.findElement(By.xpath("//*[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[3]/td[2]")).getText();
		isbn = driver.findElement(By.xpath("//*[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[5]/td[2]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Autor Submarino: " + autor);

		driver.get("https://www.amazon.com.br/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(isbn);
		driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		autorAmazon = driver.findElement(By.xpath(".//*[@id='search']/div/div[2]/div/span[3]/div[1]/div/div/div/div[2]/div/div[1]/div[1]/span[2]")).getText();
		System.out.println("Autor Amazon: " + autorAmazon);
		assertEquals(autor, autorAmazon);
		assertTrue(driver.findElement(By.xpath(".//*[@id='search']/div/div[2]/div/span[3]/div[1]/div/div/div/div[2]/div/div[1]/div[1]/span[2]")).getText().contains(autor));
		System.out.println("Passou Amazon");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	driver.get("https://www.americanas.com.br/");
	driver.findElement(By.id("h_search-input")).sendKeys(isbn);
	driver.findElement(By.id("h_search-btn")).click();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.xpath(".//*[@id='content-middle']/div[5]/div/div/div/div[1]/div/section/a/div[1]/figure/div/div/picture/img")).click();
	autorAmericanas = driver.findElement(By.xpath("//*[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[3]/td[2]")).getText();
	System.out.println("Autor Americanas: " + autorAmericanas);
	assertEquals(autor, autorAmericanas);
	assertTrue(driver.findElement(By.xpath("//*[@id='info-section']/div[2]/section/div/div/div[3]/table/tbody/tr[3]/td[2]")).getText().contains(autor));
	System.out.println("Passou Americanas");
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	
	driver.quit();
		}
}
	
	