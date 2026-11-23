package ui;

import config.Config;
import org.example.ui.pages.LoginPage;
import org.example.ui.pages.ProductPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiLoginTest extends UiBaseTest{
    private final LoginPage loginPage = new LoginPage();

    @Test
    public void userCanLoginAndSeeProducts() {
        ProductPage productsPage = loginPage.login(Config.get("user.standard"), Config.get("password"));

        assertEquals("Products", productsPage.getTitleOfThePage());
        assertTrue(productsPage.getProductsCount() > 0, "No products on the Product page");
    }
}
