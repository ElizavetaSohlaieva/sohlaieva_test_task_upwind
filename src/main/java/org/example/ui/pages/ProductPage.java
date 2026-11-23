package org.example.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ProductPage {

    private final SelenideElement title = $(".title");
    private final ElementsCollection productList = $$(".inventory_item");
    private final ElementsCollection productPrices = $$(".inventory_item_price");
    private final SelenideElement sortSelect = $(".product_sort_container");
    private final SelenideElement cartLink = $(".shopping_cart_link");

    private static final By PRODUCT_NAME       = By.cssSelector(".inventory_item_name");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[id^='add-to-cart']");

    public String getTitleOfThePage() {
        webdriver().shouldHave(urlContaining("/inventory.html"));
        return title.shouldBe(visible).getText();
    }

    public int getProductsCount() {
        return productList.size();
    }

    public void sortBy(String option) {
        sortSelect.selectOption(option);
    }

    public List<Double> getPrices() {
        return productPrices.texts().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    public String addFirstProductToCart() {
        SelenideElement firstProduct = productList.first();
        String name = firstProduct.$(PRODUCT_NAME).getText();
        firstProduct.$(ADD_TO_CART_BUTTON).click();
        return name;
    }

    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }
}