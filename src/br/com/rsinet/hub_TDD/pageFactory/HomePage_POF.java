package br.com.rsinet.hub_TDD.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage_POF {

	static WebDriver driver;

	@FindBy(how = How.ID, using = "menuUser")
	public static WebElement minhaConta;

	@FindBy(how = How.XPATH, using = "/html/body/login-modal/div/div/div[3]/a[2]")
	public static WebElement novaConta;

	
}