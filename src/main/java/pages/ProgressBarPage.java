package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProgressBarPage {

    private final WebDriver driver;
    private final By progressBar = By.className("progress-bar");

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para clicar no botão Start
    public void clickStart() {
        driver.findElement(By.id("startStopButton")).click();
    }

    // Método para obter o valor atual da Progress Bar
    public String getProgressBarValue() {
        return driver.findElement(progressBar).getText();
    }

    // Método para rolar até a barra de progresso (caso necessário)
    public void scrollToProgressBar() {
        driver.findElement(progressBar).getLocation();
    }

    // Método para esperar até a Progress Bar atingir uma porcentagem específica usando WebDriverWait
    public void waitForProgressBarToReachPercentage(int percentage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until((ExpectedCondition<Boolean>) driver -> {
            String progressBarText = getProgressBarValue().replace("%", "");
            int currentValue = Integer.parseInt(progressBarText);
            return currentValue >= percentage;
        });
    }
}
