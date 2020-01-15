package br.com.rsinet.hub_TDD.testes;

import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_TDD.modulos.ModuloCadastra;
import br.com.rsinet.hub_TDD.modulos.ModuloHomePage;
import br.com.rsinet.hub_TDD.pageFactory.CadastraPage_POF;
import br.com.rsinet.hub_TDD.utils.ExcelUtils;
import br.com.rsinet.hub_TDD.utils.Print;
import br.com.rsinet.hub_TDD.utils.constantes;

public class TesteCadastro {

	static Logger Log = Logger.getLogger("Teste de Cadastro");
	static WebDriver driver;

	@BeforeMethod
	public void configuracoes() throws Exception {

		DOMConfigurator.configure("log4j.xml");
		Log.info("Configurado arquivo de registros de logs");

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		Log.info("Configurado driver de execu��o");

		driver = new ChromeDriver();
		Log.info("Novo driver instanciado");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Driver recebeu um comando de espera implicito por 10 segundos");

		driver.manage().window().maximize();
		Log.info("A janela foi maximizada");

		ExcelUtils.setExcelFile(constantes.Path_TestData, "TesteCadastro");
		Log.info("Configurado o arquivo do excel a ser lido");

		driver.get(constantes.URL);
		Log.info("O Website foi acessado");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Driver recebeu um comando de espera implicito por 10 segundos");

	}

	@Test(priority = 0)
	public void testeCadastroValido() throws Exception {
		try {
			Reporter.log(" A aplica��o foi iniciada com sucesso |");

			ModuloHomePage.executa(driver);
			Log.info("O modulo da p�gina inicial foi executado com sucesso");
			Reporter.log(" A p�gina de cria��o de novo usu�rio foi acessada |");
			
			ModuloCadastra.executa(13, driver);
			Log.info("O m�dulo do cadastro foi executado com sucesso");
		
		} catch (Exception e) {
			Log.info("Ocorreu uma exce��o");
			e.printStackTrace();
			Log.info("Foi impresso o caminho do erro");
		}
		Reporter.log("Um novo usu�rio foi cadastrado com sucesso |");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.urlToBe("https://www.advantageonlineshopping.com/#/"));
		Log.info("Driver recebeu um comando de espera para aguardar a atualiza��o da Url");

		String url = driver.getCurrentUrl();
		Log.info("O endere�o da url foi obtido: " + url);

		assertTrue("Usu�rio cadastrado com sucesso", url.equals("https://www.advantageonlineshopping.com/#/"));
		Log.info("Realiza a compara��o entre as urls esperada e a obtida");
		WebElement element = driver.findElement(By.id("laptopsImg"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Log.info("Driver recebeu um comando de espera implicito por 10 segundos");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		Print.takeSnapShot("TesteCadastroValido", driver);
		Log.info("Um PrintScreen � obtido da tela atual");
		Reporter.log("O teste de cadastro v�lido foi conclu�do com sucesso |");
		Reporter.log(" A aplica��o foi encerrada");
	}

	@Test(priority = 1)
	public void testeCadastroInvalido() throws Exception {
		try {
			

			ModuloHomePage.executa(driver);
			Log.info("O modulo da p�gina inicial foi executado com sucesso");
			
			ModuloCadastra.executa(2, driver);
			Log.info("O m�dulo do cadastro foi executado com sucesso");
		} catch (Exception e) {
			Log.info("Ocorreu uma exce��o");
			e.printStackTrace();
			Log.info("Foi impresso o caminho do erro");
		
		}
		CadastraPage_POF.registraUsuario.click();
		
		Reporter.log("O usu�rio n�o foi cadastrado");
	
		String url = driver.getCurrentUrl();
		Log.info("O endere�o da url foi obtido: " + url);
		
		assertTrue("Usu�rio n�o cadastrado", url.equals("https://www.advantageonlineshopping.com/#/register"));
		Log.info("Realiza a compara��o entre as urls esperada e a obtida");
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scrollBy(0,150)", "");
		Log.info("Foi aplicado um comando de rolagem de tela, para melhoria do print obtido");

		Reporter.log("A mensagem de usu�rio j� cadastrado foi obtida com sucesso");
		Print.takeSnapShot("TesteCadastroInvalido", driver);
		Log.info("Tira um PrintScreen para comprovar fim do teste");
		Reporter.log(" O teste foi finalizado ");
	
	}

	@AfterMethod
	public void encerra() {
		
		driver.quit();
		Log.info("O driver � encerrado");
		
	}
}
