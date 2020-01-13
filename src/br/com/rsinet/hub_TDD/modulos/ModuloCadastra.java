package br.com.rsinet.hub_TDD.modulos;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.rsinet.hub_TDD.pageFactory.CadastraPage_POF;
import br.com.rsinet.hub_TDD.utils.ExcelUtils;
import br.com.rsinet.hub_TDD.utils.constantes;

// Now this method does not need any arguments

public class ModuloCadastra {

	public static void executa(WebDriver driver) throws Exception {

		Logger Log = Logger.getLogger("br.com.rsinet.hub_TDD.ModuloCadastra");

		PageFactory.initElements(driver, CadastraPage_POF.class);
		Log.info("A fabrica de objetos da p�gina de cadastro foi instanciada");

		String sNomeUsuario = ExcelUtils.getCellData(1, constantes.Col_userName);
		Log.info("O nome de usu�rio obtido do excel � " + sNomeUsuario);
		CadastraPage_POF.nomeUsuario.sendKeys(sNomeUsuario);
		Log.info("Insere o nome de usuario");

		String sEmail = ExcelUtils.getCellData(1, constantes.Col_Email);
		Log.info("O email de usu�rio obtido do excel � " + sEmail);
		CadastraPage_POF.emailUsuario.sendKeys(sEmail);
		Log.info("Insere o email do usu�rio");

		String sSenha = ExcelUtils.getCellData(1, constantes.Col_Senha);
		Log.info("A senha obtida do excel � " + sSenha);
		CadastraPage_POF.senhaUsuario.sendKeys(sSenha);
		Log.info("Insere a senha do usu�rio");

		String sSenhaConfirma = ExcelUtils.getCellData(1, constantes.Col_confirmacaoDeSenha);
		Log.info("A confirma��o de senha obtida do excel � " + sSenhaConfirma);
		CadastraPage_POF.confirmacaoSenhaUsuario.sendKeys(sSenhaConfirma);
		Log.info("Insere a confirma��o de  senha do usu�rio");

		String sPrimeiroNome = ExcelUtils.getCellData(1, constantes.Col_primeiroNome);
		Log.info("O primeiro nome do usu�rio obtida do excel � " + sPrimeiroNome);
		CadastraPage_POF.primeiroNome.sendKeys(sPrimeiroNome);
		Log.info("Insere o primeiro nome do usu�rio");

		String sUltimoNome = ExcelUtils.getCellData(1, constantes.Col_ultimoNome);
		Log.info("O ultimo nome do usu�rio obtida do excel � " + sUltimoNome);
		CadastraPage_POF.ultimoNome.sendKeys(sUltimoNome);
		Log.info("Insere o ultimo nome do usu�rio");

		String sTelefone = ExcelUtils.getCellData(1, constantes.Col_Telefone).toString();
		Log.info("O telefone do usu�rio obtida do excel � " + sTelefone);
		CadastraPage_POF.telefoneUsuario.sendKeys(sTelefone);
		Log.info("Insere o telefone do usu�rio");

		CadastraPage_POF.paisUsuario.click();
		Log.info("Seleciona a caixa de pa�s");
		Select paisBox = new Select(CadastraPage_POF.paisUsuario);
		Log.info("Instancia um objeto select para manipula��o do objeto");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Log.info("Foi aplicado no driver um comando de espera por 15 segundos");
		paisBox.selectByVisibleText("Brazil");

		String sCidade = ExcelUtils.getCellData(1, constantes.Col_cidade);
		Log.info("A cidade obtida do excel � " + sCidade);
		CadastraPage_POF.cidadeUsuario.sendKeys(sCidade);
		Log.info("Insere a cidade do usu�rio");

		String sEndereco = ExcelUtils.getCellData(1, constantes.Col_Endereco);
		Log.info("O endere�o obtida do excel � " + sEndereco);
		CadastraPage_POF.enderecoUsuario.sendKeys(sEndereco);
		Log.info("Insere o endere�o do usu�rio");

		String sEstado = ExcelUtils.getCellData(1, constantes.Col_Estado);
		Log.info("O estado obtido do excel � " + sEstado);
		CadastraPage_POF.estadoUsuario.sendKeys(sEstado);
		Log.info("Insere o estado do usu�rio");

		String sCEP = ExcelUtils.getCellData(1, constantes.Col_CEP).toString();
		Log.info("O endere�o obtida do excel � " + sCEP);
		CadastraPage_POF.cepUsuario.sendKeys(sCEP);
		Log.info("Insere o CEP do usu�rio");

		CadastraPage_POF.aceitaTermos.click();
		CadastraPage_POF.registraUsuario.click();

	}
}
