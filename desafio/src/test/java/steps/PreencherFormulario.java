package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PreencherFormulario {

    WebDriver driver = new ChromeDriver();

        @Dado("^que estou na página inicial do site$")
        public void que_estou_na_página_inicial_do_site() {
            driver.get("https://demoqa.com/");
        }

        @Quando("^eu clico no menu Forms$")
        public void eu_clico_no_menu_forms() {

        }

        @Quando("^depois no submenu Practice Form$")
        public void depois_no_submenu_practice_form() {

        }

        @Quando("^eu preencho o formulário com valores aleatórios$")
        public void eu_preencho_o_formulário_com_valores_aleatórios() {

        }

        @Quando("^eu submeto o formulário$")
        public void eu_submeto_o_formulário() {

        }

        @Então("um popup é aberto")
        public void um_popup_é_aberto() {

        }

        @Então("eu fecho o popup")
        public void eu_fecho_o_popup() {

        }


    }
