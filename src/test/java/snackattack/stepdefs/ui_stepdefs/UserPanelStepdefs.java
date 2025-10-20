package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import snackattack.pages.UserPanelPage;

public class UserPanelStepdefs {
    UserPanelPage userPanelPage = new UserPanelPage();

    @And("Kullanici user panelden My Cart'a tiklar")
    public void kullaniciUserPaneldenMyCartATiklar() {
        userPanelPage.userMyCartMenu.click();
    }

    @And("Kullanici user panelden My Orders'a tiklar")
    public void kullaniciUserPaneldenMyOrdersATiklar() {
        userPanelPage.userMyOrdersMenu.click();
    }

    @And("Kullanici user panelden Settings'a tiklar")
    public void kullaniciUserPaneldenSettingsATiklar() {
        userPanelPage.userSettingsMenu.click();
    }




}
