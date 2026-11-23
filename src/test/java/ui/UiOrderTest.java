package ui;

import config.Config;
import org.example.ui.pages.LoginPage;
import org.example.ui.pages.ProductPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiOrderTest extends UiBaseTest {
    private final LoginPage loginPage = new LoginPage();

    @Test
    public void verifyUserCanSortAndAddProductToCart() {

        ProductPage productsPage = loginPage
                .login(Config.get("user.standard"), Config.get("password"));
        assertEquals("Products", productsPage.getTitleOfThePage());
        productsPage.sortBy("Price (low to high)");
        List<Double> actualPrices = productsPage.getPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted().toList();
        assertEquals(expectedPrices, actualPrices, "Products aren't sorted by price");

        String productName = productsPage.addFirstProductToCart();
        List<String> cartProducts = productsPage.openCart().getProductNames();
        assertEquals(List.of(productName), cartProducts, "Cart should contain only the added product");
    }
}