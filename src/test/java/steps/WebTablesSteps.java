package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.WebTablesPage;
import utils.DriverFactory;

import java.time.Duration;

public class WebTablesSteps {
    private final WebDriver driver;
    private final WebTablesPage webTablesPage;
    private final WebDriverWait wait;

    public WebTablesSteps() {
        this.driver = DriverFactory.getDriver(); // Use a DriverFactory para obter o WebDriver
        this.webTablesPage = new WebTablesPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adicionando um tempo de espera explícito de 10 segundos
    }

    @Dado("que inicio a página inicial")
    public void queInicioAPaginaInicial() {
        driver.get("https://demoqa.com/");
    }

    @Quando("eu clicar no menu Elements")
    public void euClicarNoMenuElements() {
        WebElement elementsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Elements']")));
        elementsMenu.click();
    }

    @Quando("clicar Web Tables no submenu")
    public void clicarWebTablesNoSubmenu() {
        WebElement webTablesSubMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Web Tables']")));
        webTablesSubMenu.click();
    }

    @Quando("eu criar um novo registro com os dados {string}, {string}, {string}, {string}, {string}, {string}")
    public void euCriarUmNovoRegistroComOsDados(String firstName, String lastName, String email, String age, String salary, String department) {
        webTablesPage.criarRegistro(firstName, lastName, email, age, salary, department);
    }

    @Quando("eu editar o registro {int} com os dados {string}, {string}, {string}, {string}, {string}, {string}")
    public void euEditarORegistroComOsDados(int index, String firstName, String lastName, String email, String age, String salary, String department) {
        webTablesPage.editarRegistro(index, firstName, lastName, email, age, salary, department);
    }

    @Quando("eu deletar o registro {int}")
    public void euDeletarORegistro(int index) {
        webTablesPage.deletarRegistro(index);
    }

    @Então("eu fecho o navegador")
    public void euFechoONavegador() {
        driver.quit();
    }

    @Quando("eu criar {int} novos registros")
    public void euCriarNovosRegistros(int count) {
        for (int i = 0; i < count; i++) {
            webTablesPage.criarRegistro("Nome" + i, "Sobrenome" + i, "email" + i + "@teste.com", String.valueOf(20 + i), String.valueOf(1000 + (i * 100)), "Departamento" + i);
        }
        int totalRegistros = webTablesPage.contarRegistros();
        System.out.println("Total de registros após criação: " + totalRegistros);
    }

    @Quando("eu deletar todos os registros")
    public void euDeletarTodosOsRegistros() {
        webTablesPage.deletarTodosRegistros();
        int totalRegistros = webTablesPage.contarRegistros();
        System.out.println("Total de registros após deleção: " + totalRegistros);
    }
}
