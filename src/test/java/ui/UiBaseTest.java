package ui;

import config.Config;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class UiBaseTest {
    @BeforeEach
    void openSite() {
        Configuration.baseUrl = Config.get("ui.base.url");
        open("/");
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}