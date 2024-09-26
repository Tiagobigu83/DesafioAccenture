package pages;

import org.junit.jupiter.api.Assertions; // Certifique-se de que está usando a versão correta do JUnit
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormPage {
    private final WebDriver driver;

    // Elementos do formulário
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("userEmail");
    private final By mobileInput = By.id("userNumber");
    private final By closeButton = By.id("closeLargeModal");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherFormulario(String firstName, String lastName, String email, String mobile) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(mobileInput).sendKeys(mobile);
    }

    // Método para selecionar gênero
    public void selecionarGenero(String genero) {
        if (genero.equalsIgnoreCase("Male")) {
            driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        } else if (genero.equalsIgnoreCase("Female")) {
            driver.findElement(By.cssSelector("label[for='gender-radio-2']")).click();
        } else if (genero.equalsIgnoreCase("Other")) {
            driver.findElement(By.cssSelector("label[for='gender-radio-3']")).click();
        }
    }

    // Método para selecionar hobby
    public void selecionarHobby(String hobby) {
        WebElement elementoHobby;

        if (hobby.equalsIgnoreCase("Sports")) {
            elementoHobby = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
        } else if (hobby.equalsIgnoreCase("Reading")) {
            elementoHobby = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-2']"));
        } else if (hobby.equalsIgnoreCase("Music")) {
            elementoHobby = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-3']"));
        } else {
            throw new IllegalArgumentException("Hobby não reconhecido: " + hobby);
        }

        // Faz scroll até o elemento antes de clicar
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementoHobby);

        // Clica no elemento correspondente ao hobby
        elementoHobby.click();
    }

    public void submeterFormulario() {
        WebElement submitButton = driver.findElement(By.id("submit"));

        // Usa JavascriptExecutor para rolar até o botão
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);

        // Espera o botão estar visível e clicável
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        // Clica no botão
        submitButton.click();
    }

    public boolean umPopupAberto() {
        try {
            // Localiza o elemento que representa o conteúdo do popup
            WebElement elementoPopup = driver.findElement(By.id("example-modal-sizes-title-lg")); // Certifique-se de que este ID é o correto

            // Verifica se o popup está visível na página
            boolean popupVisivel = elementoPopup.isDisplayed();
            Assertions.assertTrue(popupVisivel, "O popup não está aberto.");
            return true; // Retorna true se o popup estiver visível
        } catch (NoSuchElementException e) {
            // Se o elemento não for encontrado, falha a verificação e retorna false
            Assertions.fail("O elemento que identifica o popup não foi encontrado.");
            return false; // Retorna false se não encontrar o popup
        } catch (StaleElementReferenceException e) {
            // Trata o caso em que o elemento está presente, mas não está mais em uma condição válida para ser interagido
            Assertions.fail("O elemento que identifica o popup não está acessível no momento.");
            return false; // Retorna false se o popup não for acessível
        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada
            Assertions.fail("Ocorreu um erro inesperado ao verificar o popup: " + e.getMessage());
            return false; // Retorna false em caso de erro
        }
    }

    public void fecharPopup() {
        WebElement closeButtonElement = driver.findElement(closeButton); // Localiza o botão de fechar o popup

        // Força o clique usando JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", closeButtonElement);
    }

}
