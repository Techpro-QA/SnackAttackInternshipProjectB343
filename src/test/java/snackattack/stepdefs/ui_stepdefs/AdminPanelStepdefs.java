package snackattack.stepdefs.ui_stepdefs;

import io.cucumber.java.en.And;
import snackattack.pages.AdminPanelPage;
import snackattack.utilities.JSUtils;

public class AdminPanelStepdefs {

    AdminPanelPage adminPanelPage = new AdminPanelPage();

    @And("Admin Customer Management'a tiklar")
    public void adminCustomerManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.customerManagementMenu);
        adminPanelPage.customerManagementMenu.click();
    }

    @And("Admin Admin Management'a tiklar")
    public void adminAdminManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.adminManagementMenu);
        adminPanelPage.adminManagementMenu.click();
    }

    @And("Admin Order Management'a tiklar")
    public void adminOrderManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.orderManagementMenu);
        adminPanelPage.orderManagementMenu.click();
    }

    @And("Admin Product Management'a tiklar")
    public void adminProductManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.productManagementMenu);
        adminPanelPage.productManagementMenu.click();
    }

    @And("Admin Category Management'a tiklar")
    public void adminCategoryManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.categoryManagementMenu);
        adminPanelPage.categoryManagementMenu.click();
    }

    @And("Admin Payment Management'a tiklar")
    public void adminPaymentManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.paymentManagementMenu);
        adminPanelPage.paymentManagementMenu.click();
    }

    @And("Admin Add.Cat.Management'a tiklar")
    public void adminAddCatManagementATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.addCatManagementMenu);
        adminPanelPage.addCatManagementMenu.click();
    }

    @And("Admin Support Requests'a tiklar")
    public void adminSupportRequestsATiklar() {
        JSUtils.JSscrollIntoView(adminPanelPage.supportRequestsMenu);
        adminPanelPage.supportRequestsMenu.click();
    }
}
