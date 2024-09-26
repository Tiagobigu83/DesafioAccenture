package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver; // Campo para armazenar a instância do WebDriver

    // Método para criar uma nova instância do WebDriver
    public static WebDriver criarDriver() {
        // Define o caminho do driver do Chrome, ajuste conforme seu ambiente
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // Ajuste o caminho

        // Cria uma nova instância do ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximiza a janela do navegador
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Espera implícita
        return driver;
    }

    // Método para obter o WebDriver existente ou criar um novo
    public static WebDriver getDriver() {
        if (driver == null) {
            return criarDriver(); // Cria um novo driver se não houver
        }
        return driver; // Retorna o driver existente
    }

    // Método para iniciar o WebDriver - compatível com o solicitado
    public static WebDriver iniciarDriver() {
        return getDriver(); // Usa o método existente para iniciar ou obter o WebDriver
    }

    // Método para encerrar o driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Limpa a referência ao driver
        }
    }

    // Método para encerrar o WebDriver - compatível com o solicitado
    public static void fecharDriver() {
        quitDriver(); // Apenas chama o método quitDriver
    }
}
