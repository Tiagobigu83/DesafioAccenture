package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertFramePage {
    private final WebDriver driver;

    // Seletor dos elementos da página
    private final By menuAlertsFrameWindows = By.xpath("//h5[text()='Alerts, Frame & Windows']");
    private final By submenuBrowserWindows = By.xpath("//span[text()='Browser Windows']");
    private final By buttonNewWindow = By.id("windowButton");
    private final By messageSamplePage = By.xpath("//h1[text()='This is a sample page']");

    public AlertFramePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarMenuAlertsFrameWindows() {
        driver.findElement(menuAlertsFrameWindows).click();
    }

    public void clicarSubmenuBrowserWindows() {
        driver.findElement(submenuBrowserWindows).click();
    }

    public void clicarBotaoNewWindow() {
        driver.findElement(buttonNewWindow).click();
    }

    public void validarMensagem() {
        WebElement mensagem = driver.findElement(messageSamplePage);
        assertTrue(mensagem.isDisplayed(), "A mensagem 'This is a sample page' não está presente.");
    }
}
