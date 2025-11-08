package snackattack.stepdefs.db_stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import snackattack.utilities.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DB_Carts_Stepdefs {

    ResultSet resultSet;

    @When("Carts tablosundaki veriler sorgulanir")
    public void cartsTablosundakiVerilerSorgulanir() {




    }

    @Then("User id'leri {string} ve {string} olan cart bilgileri dogrulanir")
    public void userIdLeriVeOlanCartBilgileriDogrulanir(String userId1, String userId2) throws SQLException {

        List<String> userIds = Arrays.asList(userId1, userId2);

        for (String userId : userIds) {
            String query = "SELECT id, total_amount, " +
                    "TO_CHAR(created_at, 'YYYY-MM-DD HH24:MI:SS') AS created_at, " +
                    "TO_CHAR(updated_at, 'YYYY-MM-DD HH24:MI:SS') AS updated_at, " +
                    "status FROM snack_attack_db.carts WHERE user_id = '" + userId + "';";

            resultSet = DBUtils.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double totalAmount = resultSet.getDouble("total_amount");
                String createdAt = resultSet.getString("created_at");
                String updatedAt = resultSet.getString("updated_at");
                String status = resultSet.getString("status");

                System.out.println("🧾 ID: " + id);
                System.out.println("💰 Total Amount: " + totalAmount);
                System.out.println("📅 Created At: " + createdAt);
                System.out.println("🕒 Updated At: " + updatedAt);
                System.out.println("📦 Status: " + status);
                System.out.println("-------------------------------");

                // ✅ Basit doğrulamalar
                Assert.assertTrue("ID 0'dan büyük olmalı!", id > 0);
                Assert.assertNotNull("Created At boş olamaz!", createdAt);
                Assert.assertEquals("Status ACTIVE olmalı!", "ACTIVE", status);

                // Updated_at boş olabilir — sadece uyarı verelim
                if (updatedAt == null || updatedAt.isEmpty()) {
                    System.out.println("⚠️ Uyarı: Updated At boş olabilir.");
                }
            }
        }
    }
    }

