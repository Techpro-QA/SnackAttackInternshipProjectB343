package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import snackattack.pages.userpanelpages.SettingsPage;

public class UserSettingsStepdefs {

    SettingsPage settingsPage = new SettingsPage();

    @And("Kullanici Username Textbox'ini {string} ile doldurur")
    public void kullaniciUsernameTextboxIniIleDoldurur(String username) {
        settingsPage.settingUsernameTextbox.sendKeys(username);
    }

    @And("Kullanici Email Textbox'ini {string} ile doldurur")
    public void kullaniciEmailTextboxIniIleDoldurur(String email) {
        settingsPage.settingEmailTextBox.sendKeys(email);
    }

    @And("Kullanici First Name Textbox'ini {string} ile doldurur")
    public void kullaniciFirstNameTextboxIniIleDoldurur(String firstName) {
        settingsPage.settingFirstNameTextbox.sendKeys(firstName);
    }

    @And("Kullanici Last Name Textbox'ini {string} ile doldurur")
    public void kullaniciLastNameTextboxIniIleDoldurur(String lastName) {
        settingsPage.settingLastNameTextbox.sendKeys(lastName);
    }

    @And("Kullanici Address Textbox'ini {string} ile doldurur")
    public void kullaniciAddressTextboxIniIleDoldurur(String address) {
        settingsPage.settingAddressTextbox.sendKeys(address);
    }

    @And("Kullanici Phone Number Textbox'ini {string} ile doldurur")
    public void kullaniciPhoneNumberTextboxIniIleDoldurur(String phoneNumber) {
        settingsPage.settingPhoneNumber.sendKeys(phoneNumber);

    }

    @And("Kullanici Region Code Textbox'ini {string} ile doldurur")
    public void kullaniciRegionCodeTextboxIniIleDoldurur(String regionCode) {
        settingsPage.settingRegionCode.sendKeys(regionCode);
    }

    @And("Kullanici Password Textbox'ini {string} ile doldurur")
    public void kullaniciPasswordTextboxIniIleDoldurur(String password) {
        settingsPage.settingPassword.sendKeys(password);
    }

    @And("Kullanici Update Profile butonuna tiklar")
    public void kullaniciUpdateProfileButonunaTiklar() {
        settingsPage.settingUpdateProfileButton.click();
    }


}
