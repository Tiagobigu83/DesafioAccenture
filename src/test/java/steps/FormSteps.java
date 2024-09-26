package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;
import pages.FormPage;

import java.time.Duration;
import java.util.Random;

public class FormSteps {
    private WebDriver driver;
    private FormPage formPage; // Declare formPage aqui

    @Dado("que estou na página inicial do site")
    public void queEstouNaPaginaInicialDoSite() {
        // Cria uma nova instância do driver
        driver = DriverFactory.criarDriver();

        // Maximiza a janela do navegador
        driver.manage().window().maximize();

        // Navega para a página inicial
        driver.get("https://demoqa.com");

        // Associa a instância de FormPage
        formPage = new FormPage(driver);
    }

    @Quando("eu clico no menu Forms")
    public void euClicoNoMenuForms() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//h5[text()='Forms']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    @Quando("depois clico no submenu Practice Form")
    public void depoisClicoNoSubmenuPracticeForm() {
        // Clica no submenu "Practice Form"
        driver.findElement(By.xpath("//span[text()='Practice Form']")).click();

        // Espera até que o anúncio apareça (ajuste o seletor conforme necessário)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Espera um elemento alternativo que indica que o anúncio está carregado
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Texto do Anúncio')]"))); // Ajuste para um texto visível do anúncio

            // Após o anúncio ser visível, rola até o botão "Submit"
            WebElement submitButton = driver.findElement(By.id("submit")); // Ajuste o seletor conforme necessário
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", submitButton);

        } catch (TimeoutException e) {
            System.out.println("Anúncio não apareceu a tempo. Verifique o seletor.");
        }
    }


    @Quando("eu preencho o formulário com valores aleatórios")
    public void euPreenchoOFormularioComValoresAleatorios() {
        Random random = new Random();
        String firstName = "Nome" + random.nextInt(1000);
        String lastName = "Sobrenome" + random.nextInt(1000);
        String email = "email" + random.nextInt(1000) + "@exemplo.com";
        String mobile = "123456789" + random.nextInt(10);

        // Preenche os campos de nome, sobrenome, email e número de telefone
        formPage.preencherFormulario(firstName, lastName, email, mobile);

        // Seleciona o gênero "Male"
        formPage.selecionarGenero("Male");

        // Seleciona o hobby "Sports"
        formPage.selecionarHobby("Sports");
    }


    @Quando("eu submeto o formulário")
    public void euSubmetoOFormulario() {
        formPage.submeterFormulario();
    }

    @Entao("um popup é aberto")
    public void umPopupEAberto() {
        assert formPage.umPopupAberto() : "O popup não está aberto.";
    }

    @Entao("eu fecho o popup")
    public void euFechoOPopup() {
        formPage.fecharPopup();
        driver.quit(); // Fecha o driver após os testes
    }
}
