package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebTablesPage {
    private final WebDriver driver;
    private final WebDriverWait wait; // Para gerenciar esperas

    private final By addButton = By.id("addNewRecordButton");
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("userEmail");
    private final By ageInput = By.id("age");
    private final By salaryInput = By.id("salary");
    private final By departmentInput = By.id("department");
    private final By submitButton = By.id("submit");
    private final By editButtons = By.cssSelector(".action-buttons .edit");
    private final By deleteButtons = By.cssSelector(".action-buttons .delete");
    private final By tableRows = By.cssSelector(".rt-tbody .rt-tr");

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Configurando espera
    }

    public void criarRegistro(String firstName, String lastName, String email, String age, String salary, String department) {
        driver.findElement(addButton).click();
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(ageInput).sendKeys(age);
        driver.findElement(salaryInput).sendKeys(salary);
        driver.findElement(departmentInput).sendKeys(department);
        driver.findElement(submitButton).click();
    }

    public void editarRegistro(int index, String firstName, String lastName, String email, String age, String salary, String department) {
        List<WebElement> edits = driver.findElements(editButtons);
        edits.get(index).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)); // Espera o campo ser visível
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(ageInput).clear();
        driver.findElement(ageInput).sendKeys(age);
        driver.findElement(salaryInput).clear();
        driver.findElement(salaryInput).sendKeys(salary);
        driver.findElement(departmentInput).clear();
        driver.findElement(departmentInput).sendKeys(department);
        driver.findElement(submitButton).click();
    }

    public void deletarRegistro(int index) {
        List<WebElement> deletes = driver.findElements(deleteButtons);
        deletes.get(index).click();

        // Esperar a confirmação de deleção, se necessário
        wait.until(ExpectedConditions.alertIsPresent()); // Aguarda que um alerta apareça
        driver.switchTo().alert().accept(); // Aceita o alerta
    }

    public void deletarTodosRegistros() {
        List<WebElement> deletes;
        while (true) {
            deletes = driver.findElements(deleteButtons);
            if (deletes.isEmpty()) {
                break; // Sai do loop se não houver mais botões de deletar
            }
            deletes.get(0).click();
            wait.until(ExpectedConditions.alertIsPresent()); // Espera pelo alerta de confirmação
            driver.switchTo().alert().accept(); // Aceita o alerta
        }
    }

    public int contarRegistros() {
        return driver.findElements(tableRows).size(); // Retorna o número de registros na tabela
    }
}
