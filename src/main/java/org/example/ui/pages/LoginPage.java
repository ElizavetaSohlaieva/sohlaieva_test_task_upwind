package org.example.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement login_input = $("#user-name");
    private final SelenideElement password_input = $("#password");
    private final SelenideElement login_button = $("#login-button");

   public ProductPage login(String userName, String password) {
       login_input.setValue(userName);
       password_input.setValue(password);
       login_button.click();
       return new ProductPage();
   }
}
