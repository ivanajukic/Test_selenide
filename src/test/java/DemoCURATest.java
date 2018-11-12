import com.codeborne.selenide.CollectionCondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DemoCURATest {

    @Before
    public void setDriverAndOpenWebPage(){
        System.setProperty("webdriver.chrome.driver", "C:\\Java JDK\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        open("https://katalon-demo-cura.herokuapp.com/");
    }

    @After
    public void closeConnection(){
        close();
    }

    @Test
    public void LogInAndLogOut(){
        LogIn();
        $(By.xpath("//*[@class='col-sm-12 text-center']")).shouldBe(visible);
        LogOut();
        $(By.id("menu-toggle")).click();
        $(By.xpath("//a[text()='Login']")).shouldBe(visible);
    }

    @Test
    public void LogInAndMakeAppointment(){
        LogIn();
        $(By.xpath("//*[@class='col-sm-12 text-center']")).shouldBe(visible);
        CreateAppointment();
        $(By.xpath("//*[text()='Please be informed that your appointment has been booked as following:']")).shouldBe(visible);
    }

    @Test
    public void LogInAndCheckHistory(){
        LogIn();
        $(By.xpath("//*[@class='col-sm-12 text-center']")).shouldBe(visible);
        CheckHistory();
        $(By.xpath("//*[text()='No appointment.']")).shouldBe(visible);
    }

    @Test
    public void CreateMultipleAppointmentsAndCheckHistory(){
        LogIn();
        $(By.xpath("//*[@class='col-sm-12 text-center']")).shouldBe(visible);
        CreateAppointment();
        $(By.xpath("//*[text()='Please be informed that your appointment has been booked as following:']")).shouldBe(visible);
        CheckHistory();
        $$(By.className("col-sm-offset-2")).shouldHave(CollectionCondition.size(1));
        $(By.id("btn-make-appointment")).click();
        CreateAppointment();
        $(By.xpath("//*[text()='Please be informed that your appointment has been booked as following:']")).shouldBe(visible);
        CheckHistory();
        $$(By.className("col-sm-offset-2")).shouldHave(size(2));
    }


    private void LogIn(){
        $(By.id("menu-toggle")).click();
        $(By.linkText("Login")).click();
        String username = $(By.xpath("//input[@aria-describedby='demo_username_label']")).getValue();
        String password = $(By.xpath("//input[@aria-describedby='demo_password_label']")).getValue();
        $(By.id("txt-username")).setValue(username);
        $(By.id("txt-password")).setValue(password);
        $(By.id("btn-login")).click();
    }

    private void CreateAppointment(){
        $(By.id("combo_facility")).selectOptionContainingText("Hongkong");
        $(By.id("chk_hospotal_readmission")).click();
        $(By.id("radio_program_none")).click();
        $(By.id("txt_visit_date")).setValue("12/11/2018");
        $(By.id("txt_comment")).setValue("Test");
        $(By.id("btn-book-appointment")).click();
    }

    private void CheckHistory(){
        $(By.id("menu-toggle")).click();
        $(By.xpath("//a[text()='History']")).click();
    }

    private void LogOut(){
        $(By.id("menu-toggle")).click();
        $(By.xpath("//a[text()='Logout']")).click();
    }

}
