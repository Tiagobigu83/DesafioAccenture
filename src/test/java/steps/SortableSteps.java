package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.SortablePage;
import utils.DriverFactory;

public class SortableSteps {
    private WebDriver driver; // Variável para armazenar a instância do WebDriver
    private SortablePage sortablePage;

    @Before // Método executado antes de cada cenário
    public void setUp() {
        driver = DriverFactory.iniciarDriver(); // Inicia ou obtém o WebDriver
        sortablePage = new SortablePage(driver); // Inicializa a página Sortable
    }

    @Dado("que inicio a página do DemoQA")
    public void queInicioaPaginaDoDemoQA() {
        driver.get("https://demoqa.com/"); // Navega até a URL
    }

    @Quando("eu escolher a opção Interactions")
    public void euEscolherAOpcaoInteractions() {
        driver.findElement(By.xpath("//div[text()='Interactions']")).click();
    }

    @Quando("eu clicar no submenu Sortable")
    public void euClicarNoSubmenuSortable() {
        driver.findElement(By.xpath("//span[text()='Sortable']")).click();
    }

    @Quando("ordeno os elementos usando drag and drop")
    public void ordenoOsElementosUsandoDragAndDrop() {
        sortablePage.dragAndDropItemsInOrder(); // Chama o método de arrastar e soltar na página
    }

    @After // Método executado após cada cenário
    public void tearDown() {
        DriverFactory.fecharDriver(); // Fecha o WebDriver após os testes
    }
}
