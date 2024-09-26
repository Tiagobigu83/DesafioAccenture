package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AlertFramePage;
import utils.DriverFactory;

import java.util.ArrayList;

public class NewWindowSteps {
    private WebDriver driver;
    private AlertFramePage alertFramePage;

    @Dado("que estou na página inicial")
    public void queEstouNaPaginaInicial() {
        driver = DriverFactory.criarDriver();
        driver.manage().window().maximize(); // Maximiza a janela do navegador
        driver.get("https://demoqa.com");
        alertFramePage = new AlertFramePage(driver);
    }


    @Quando("eu clicar no menu Alerts, Frame & Windows")
    public void clicarMenuAlertsFrameWindows() {
        WebElement menuElement = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", menuElement);
    }

    @Quando("eu clicar no submenu Browser Windows")
    public void euClicoNoSubmenuBrowserWindows() {
        alertFramePage.clicarSubmenuBrowserWindows();
    }

    @Quando("eu clicar no botão New Window")
    public void euClicoNoBotaoNewWindow() {
        alertFramePage.clicarBotaoNewWindow();
    }

    @Então("uma nova janela deve ser aberta")
    public void umaNovaJanelaDeveSerAberta() {
        ArrayList<String> abas = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(abas.get(1)); // Muda para a nova janela
    }

    @Então("eu devo validar que a mensagem This is a sample page está presente")
    public void euDevoValidarQueAMensagemEstaPresente() {
        alertFramePage.validarMensagem();
    }

    @Então("eu fecho a nova janela")
    public void euFechoANovaJanela() {
        driver.close(); // Fecha a nova janela
        driver.switchTo().window(driver.getWindowHandles().iterator().next()); // Volta para a aba original
    }
}
