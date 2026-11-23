package org.example.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final SelenideElement cartList = $(".cart_list");
    private final ElementsCollection cartItemNames = $$(".cart_item .inventory_item_name");

    public List<String> getProductNames() {
        cartList.shouldBe(visible);
        return cartItemNames.texts();
    }
}
