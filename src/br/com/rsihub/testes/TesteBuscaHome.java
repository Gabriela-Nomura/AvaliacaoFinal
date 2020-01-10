package br.com.rsihub.testes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.rsihub.pageObject.Home_Page;
import br.com.rsihub.utils.constantes;

public class TesteBuscaHome {
	
	static WebDriver driver;
	
		public static void homePage() throws Exception {
		driver.get(constantes.URL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Home_Page.minhaConta.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement PopUp = driver.findElement(By.xpath("/html/body/login-modal/div/div/div[3]/sec-form"));
		PopUp.click();
		Home_Page.novaConta.click();

	}

}