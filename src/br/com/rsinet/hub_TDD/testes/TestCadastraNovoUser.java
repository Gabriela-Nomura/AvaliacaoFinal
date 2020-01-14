package br.com.rsinet.hub_TDD.testes;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.rsinet.hub_TDD.modulos.ModuloCadastra;
import br.com.rsinet.hub_TDD.modulos.ModuloHomePage;
import br.com.rsinet.hub_TDD.pageFactory.CadastraPage_POF;
import br.com.rsinet.hub_TDD.screenShots.Print;
import br.com.rsinet.hub_TDD.utils.ExcelUtils;
import br.com.rsinet.hub_TDD.utils.constantes;

public class TestCadastraNovoUser {

	static Logger Log = Logger.getLogger("br.com.rsinet.hub_TDD.TesteCadastraNovoUser");
	static WebDriver driver;

	@BeforeMethod
	public void configuracoes() throws Exception {

		DOMConfigurator.configure("log4j.xml");
		Log.info("Configurado arquivo de registros de log");

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		Log.info("Realizada a configura��o do driver");

		driver = new ChromeDriver();

		Log.info("Inicializa��o do navegador Chrome");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Foi aplicado no driver um comando de espera por 10 segundos");

		driver.manage().window().maximize();
		Log.info("A janela foi maximizada");

		driver.get(constantes.URL);
		Log.info("O Website foi acessado");

		ExcelUtils.setExcelFile(constantes.Path_TestData + constantes.File_TestData, "Planilha1");
		Log.info("O arquivo do excel foi configurado");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Log.info("Foi aplicado no driver um comando de espera por 15 segundos");
		Reporter.log(" A aplica��o foi iniciada com sucesso");
	}

	@Test (priority = 0)
	public void testaCadastroValido() throws Exception {
		try {

			ModuloHomePage.executa(driver);
			Log.info("O modulo da p�gina inicial foi executado com sucesso");
			ModuloCadastra.executa(driver);
			Log.info("O m�dulo do cadastro foi executado com sucesso");
		} catch (Exception e) {
			Log.info("Ocorreu uma exce��o");
			e.printStackTrace();
			Log.info("Foi impresso o caminho do erro");
			Reporter.log("Um novo usu�rio foi cadastrado com sucesso");
		}

		// FALTA A IMPLEMENTA��O DOS METODOS COMPARATIVOS DE TESTE
		Thread.sleep(8000);
		String url = driver.getCurrentUrl();
		System.out.println(url);

		assertTrue("Usu�rio cadastrado com sucesso", url.equals("https://www.advantageonlineshopping.com/#/"));
		Log.info("Realiza a compara��o atrav�s do m�todo Assert");
		
		Print.takeSnapShot("TesteCadastraUsuariocomsucesso", driver);
		Log.info("Tira um PrintScreen");
	}
	@Test (priority = 1)
	public void testaCadastroInvalido() throws Exception {
		try {

			ModuloHomePage.executa(driver);
			Log.info("O modulo da p�gina inicial foi executado com sucesso");
			ModuloCadastra.executa(driver);
			Log.info("O m�dulo do cadastro foi executado com sucesso");
		} catch (Exception e) {
			Log.info("Ocorreu uma exce��o");
			e.printStackTrace();
			Log.info("Foi impresso o caminho do erro");
			Reporter.log("O usu�rio n�o foi cadastrado");
		}

		// FALTA A IMPLEMENTA��O DOS METODOS COMPARATIVOS DE TESTE
		Thread.sleep(5000);
		
		String url = driver.getCurrentUrl();
		
		
		assertTrue("Usu�rio n�o cadastrado", url.equals("https://www.advantageonlineshopping.com/#/register"));
		Log.info("Realiza a compara��o atrav�s do m�todo Assert");
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		CadastraPage_POF.registraUsuario.click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scrollBy(0,150)", ""); 
		
		Print.takeSnapShot("TesteCadastraUsuariocomFalha", driver);
		Log.info("Tira um PrintScreen");}
	@AfterMethod
	public void encerra() {

		driver.quit();
	}
}
