package br.com.rsinet.hub_TDD.testes;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_TDD.modulos.ModuloBusca;
import br.com.rsinet.hub_TDD.pageFactory.HomePage_POF;
import br.com.rsinet.hub_TDD.pageFactory.PaginaBusca_POF;
import br.com.rsinet.hub_TDD.utils.Driver_Factory;
import br.com.rsinet.hub_TDD.utils.ExcelUtils;
import br.com.rsinet.hub_TDD.utils.Print;
import br.com.rsinet.hub_TDD.utils.constantes;

public class TestBuscaLupa {

	static Logger Log = Logger.getLogger(Logger.class.getName());
	static WebDriver driver;
	HomePage_POF HomePage;
	PaginaBusca_POF BuscaPage;

	@BeforeMethod
	public void configura() throws Exception {

		DOMConfigurator.configure("log4j.xml");
		Log.info("Configurado arquivo de registros de logs");

		driver = Driver_Factory.configDriver();
		Log.info("Faz a configura��o do driver");

		driver.get(constantes.URL);
		Log.info("O Website foi acessado");

		HomePage = PageFactory.initElements(driver, HomePage_POF.class);
		Log.info("A fabrica de objetos da p�gina inicial foi instanciada");

		BuscaPage = PageFactory.initElements(driver, PaginaBusca_POF.class);
		Reporter.log(" A aplica��o foi iniciada com sucesso |");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Driver recebeu um comando de espera implicito por 10 segundos");

	}
	
	@Test(priority = 1)
	public void testaBuscaLupaValido() throws Exception {
		
		Reporter.log(" A aplica��o foi iniciada com sucesso |");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Driver recebeu um comando de espera implicito por 10 segundos");

		HomePage.clickOn_busca();
		HomePage.sendText_busca();
		
		Log.info("O m�dulo de busca foi executado com sucesso");
		Reporter.log("O campo de busca recebeu os par�metros enviados |");
		
		WebElement result = driver.findElement(By.xpath("//*[@id=\"16\"]"));
		result.click();
		Log.info("Clica no elemento que foi buscado");

		AssertJUnit.assertTrue("Busca realizada com sucesso",
				driver.getPageSource().contains("HP ELITEPAD 1000 G2 TABLET"));
		Log.info("Realiza a compara��o atrav�s do m�todo Assert");
		
		Reporter.log("A aplica��o retornou o produto pesquisado |");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Driver recebeu um comando de espera implicito por 10 segundos");

		Print.takeSnapShot("TesteBuscaLupaSucesso", driver);
		Log.info("Tira um PrintScreen");
		Reporter.log("A busca pela lupa foi realizada com sucesso");
	}

	@Test(priority = 0)
	public void testaBuscaLupaInvalido() throws Exception {
		
		Reporter.log(" A aplica��o foi iniciada com sucesso |");
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://www.advantageonlineshopping.com/#/"));
	
		JavascriptExecutor scroll = (JavascriptExecutor) driver;
		scroll.executeScript("scrollBy(0,750)", "");
		Log.info("Aplica um scroll na tela para que o elemento fique vis�vel para o print");

		Reporter.log(" A p�gina � deslizada para que o elemento desejado fique vis�vel |");
		WebElement element = driver.findElement(By.id("details_10"));
		wait.until(ExpectedConditions.visibilityOf(element));

		Print.takeSnapShot("TesteBuscaLupaInvalido", driver);
		Log.info("Um PrintScreen � obtido da tela atual");

		ModuloBusca.executa(2, driver);
		Log.info("Insere o valor no campo de pesquisa");

		wait.until(ExpectedConditions.urlToBe("https://www.advantageonlineshopping.com/#/search/?viewAll=HP%20ELITEBOOK%20FOLIO"));
		Log.info("Um comando de espera � fornecido ao driver para que aguarde pelo carregamento da p�gina");
		
		AssertJUnit.assertTrue("Teste com falha", driver.getPageSource().contains("No results for "));

		Log.info("A compara��o com m�todo Assert foi realizada com sucesso");
		Reporter.log(" A busca foi realizada com sucesso |");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Aguarda 10s");
		Reporter.log(" A busca pela lupa n�o retornou o resultado esperado | ");
		Print.takeSnapShot("TesteBuscaLupaFalha", driver);
		Log.info("Tira um PrintScreen");
		Reporter.log(" O teste foi finalizado ");
	}

	@AfterMethod
	public void encerra() {
		driver.close();
	}
}