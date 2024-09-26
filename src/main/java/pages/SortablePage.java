package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SortablePage {
    private final WebDriver driver; // Instância do WebDriver
    private final Actions actions;  // Ações do Selenium para drag and drop

    // Construtor que recebe o WebDriver
    public SortablePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver); // Inicializa as ações
    }

    // Método para arrastar e soltar os itens na ordem desejada
    public void dragAndDropItemsInOrder() {
        // Localiza os elementos que precisam ser ordenados
        List<WebElement> items = driver.findElements(By.cssSelector(".vertical-list-container .list-group-item"));

        // Ordenação em ordem crescente (ajuste conforme necessário)
        String[] desiredOrder = {"A", "B", "C", "D", "E", "F", "G", "H"};

        // Executa o drag and drop na ordem desejada
        for (String itemName : desiredOrder) {
            for (WebElement item : items) {
                if (item.getText().equals(itemName)) {
                    // Se o item estiver na lista, arraste-o para a posição correta
                    actions.clickAndHold(item).moveToElement(items.get(0)).release().perform(); // Exemplo de posição, ajuste conforme necessário
                    break;
                }
            }
        }
    }
}
