import com.codeborne.selenide.CollectionCondition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTest {

    @Test
    public void UserCanSearchKeywordWIthGoogle(){
        System.setProperty("webdriver.chrome.driver", "C:\\Java JDK\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");

        open("http://google.com");
        $(By.name("q")).setValue("foi predmeti").pressEnter();
        $$(By.className("g")).shouldHave(CollectionCondition.size(10));
        $(By.className("g")).shouldHave(text("Informacijski i poslovni sustavi"));
    }
}
