package br.com.rsinet.hub_TDD.modulos;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import br.com.rsinet.hub_TDD.pageObject.Cadastra_Page;
import br.com.rsinet.hub_TDD.pageObject.Home_Page;
import br.com.rsinet.hub_TDD.utils.ExcelUtils;
import br.com.rsinet.hub_TDD.utils.constantes;

// Now this method does not need any arguments

public class ModuloCadastra {

	private static WebDriver driver;

	public static void executa() throws Exception {
		 Logger Log = Logger.getLogger("br.com.rsinet.hub_TDD.ModuloCadastra");
		 
//		 This is to get the values from Excel sheet, passing parameters (Row num &amp; Col num)to getCellData method

		String sNomeUsuario = ExcelUtils.getCellData(1, constantes.Col_userName);
		Log.info("O nome de usu�rio obtido do excel � "+ sNomeUsuario );
		Cadastra_Page.nomeUsuario.sendKeys(sNomeUsuario);
		Log.info("Insere o nome de usuario");
		
		String sEmail = ExcelUtils.getCellData(1, constantes.Col_Email);
		Log.info("O email de usu�rio obtido do excel � "+ sEmail );
		Cadastra_Page.emailUsuario.sendKeys(sEmail);
		Log.info("Insere o email do usu�rio");

		String sSenha = ExcelUtils.getCellData(1, constantes.Col_Senha);
		Log.info("A senha obtida do excel � "+ sSenha );
		Cadastra_Page.senhaUsuario.sendKeys(sSenha);
		Log.info("Insere a senha do usu�rio");
		
		String sSenhaConfirma = ExcelUtils.getCellData(1, constantes.Col_confirmacaoDeSenha);
		Log.info("A confirma��o de senha obtida do excel � "+ sSenhaConfirma );
		Cadastra_Page.confirmacaoSenhaUsuario.sendKeys(sSenhaConfirma);
		Log.info("Insere a confirma��o de  senha do usu�rio");
		
		String sPrimeiroNome = ExcelUtils.getCellData(1, constantes.Col_primeiroNome);
		Log.info("O primeiro nome do usu�rio obtida do excel � "+ sPrimeiroNome);
		Cadastra_Page.primeiroNome.sendKeys(sPrimeiroNome);
		Log.info("Insere o primeiro nome do usu�rio");
		
		String sUltimoNome = ExcelUtils.getCellData(1, constantes.Col_ultimoNome);
		Log.info("O ultimo nome do usu�rio obtida do excel � "+ sUltimoNome);
		Cadastra_Page.ultimoNome.sendKeys(sUltimoNome);
		Log.info("Insere o ultimo nome do usu�rio");
		
		String sTelefone = ExcelUtils.getCellData(1, constantes.Col_Telefone);
		Log.info("O telefone do usu�rio obtida do excel � "+ sTelefone);
		Cadastra_Page.telefoneUsuario.sendKeys(sTelefone);
		Log.info("Insere o telefone do usu�rio");
		
		Cadastra_Page.paisUsuario.click();
		Log.info ("Seleciona a caixa de pa�s");
		Select paisBox = new Select(Cadastra_Page.paisUsuario);
		Log.info ("Instancia um objeto select para manipula��o do objeto");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Log.info("Foi aplicado no driver um comando de espera por 15 segundos");
		paisBox.selectByVisibleText("Brazil");
		
		String sCidade = ExcelUtils.getCellData(1, constantes.Col_cidade);
		Log.info("A cidade obtida do excel � "+ sCidade);
		Cadastra_Page.cidadeUsuario.sendKeys(sCidade);
		Log.info("Insere a cidade do usu�rio");
		

		String sEndereco = ExcelUtils.getCellData(1, constantes.Col_Endereco);
		Log.info("O endere�o obtida do excel � "+ sEndereco);
		Cadastra_Page.enderecoUsuario.sendKeys(sEndereco);
		Log.info("Insere o endere�o do usu�rio");
		Cadastra_Page.aceitaTermos.click();
		Cadastra_Page.registraUsuario.click();
	}
}
		/*
		Home_Page.lnk_MyAccount(driver).click();
		Log.info("Click action performed on My Account link");

		LogIn_Page.txtbx_UserName(driver).sendKeys(sUserName);
		Log.info("Username entered in the Username text box");

		LogIn_Page.txtbx_Password(driver).sendKeys(sPassword);
		Log.info("Password entered in the Password text box");

		((WebElement) LogIn_Page.btn_LogIn(driver)).click();
		Log.info("Click action performed on Submit button");*/

