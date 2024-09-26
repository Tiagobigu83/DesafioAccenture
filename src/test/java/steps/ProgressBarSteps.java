package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProgressBarPage;
import utils.DriverFactory;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.time.Duration;
import org.openqa.selenium.WebElement;


public class ProgressBarSteps {

    WebDriver driver;
    ProgressBarPage progressBarPage;

    @Dado("que estou na página inicial do DemoQA")
    public void queEstouNaPaginaInicialDoDemoQA() {
        driver = DriverFactory.getDriver();  // Iniciar o WebDriver
        driver.get("https://demoqa.com/");
    }

    @Quando("eu clicar no menu Widgets")
    public void euClicarNoMenuWidgets() {
        // Criar uma instância de WebDriverWait com um tempo máximo de espera de 10 segundos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar até que o elemento esteja clicável
        WebElement widgetsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Widgets']")));

        // Clicar no elemento
        widgetsMenu.click();
    }

    @Quando("eu clicar no submenu Progress Bar")
    public void euClicarNoSubmenuProgressBar() {
        WebElement progressBarMenu = driver.findElement(By.xpath("//span[text()='Progress Bar']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", progressBarMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", progressBarMenu);
        progressBarPage = new ProgressBarPage(driver);
        progressBarPage.scrollToProgressBar();  // Rolagem para evitar problemas de visualização
    }

    @Quando("eu clicar no botão Start")
    public void euClicarNoBotaoStart() {
        progressBarPage.clickStart();
    }

    @Quando("eu parar a Progress Bar antes de atingir {int}%")
    public void euPararAProgressBarAntesDeAtingir(int percentage) {
        progressBarPage.waitForProgressBarToReachPercentage(percentage);
    }

    @Então("o valor exibido da Progress Bar deve ser menor ou igual a {int}%")
    public void oValorExibidoDaProgressBarDeveSerMenorOuIgualA(int percentage) {
        int currentValue = Integer.parseInt(progressBarPage.getProgressBarValue().replace("%", ""));
        assertTrue("O valor da Progress Bar deve ser menor ou igual a " + percentage + "%", currentValue <= percentage);
    }

    @Quando("eu volto a apertar no botão Start")
    public void euVoltoAApertarNoBotaoStart() {
        progressBarPage.clickStart();
    }

    @Quando("eu aguardo a Progress Bar atingir {int}%")
    public void euAguardoAProgressBarAtingir(int percentage) {
        progressBarPage.waitForProgressBarToReachPercentage(percentage);
    }

    @Então("o valor exibido da Progress Bar deve ser igual a {int}%")
    public void oValorExibidoDaProgressBarDeveSerIgualA(int percentage) {
        // Obtém o valor atual da Progress Bar e converte para inteiro
        int currentValue = Integer.parseInt(progressBarPage.getProgressBarValue().replace("%", ""));
        // Verifica se o valor atual é igual ao valor esperado
        assertEquals("O valor da Progress Bar deve ser igual a " + percentage + "%", percentage, currentValue);  // Troque assertTrue por assertEquals
        DriverFactory.quitDriver();  // Encerra o WebDriver
    }
}
