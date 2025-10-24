package snackattack.utilities;
import snackattack.utilities.ConfigReader;

import java.sql.*;
public class DBUtils {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    // :small_blue_diamond: 1. Bağlantıyı aç
    public static void createConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        ConfigReader.getProperty("dbUrl"),
                        ConfigReader.getProperty("dbUser"),
                        ConfigReader.getProperty("dbPassword")
                );
                System.out.println(":white_check_mark: Veritabanı bağlantısı kuruldu.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(":x: Veritabanı bağlantısı kurulamadı: " + e.getMessage());
        }
    }
    // :small_blue_diamond: 2. Sorgu çalıştır
    public static ResultSet executeQuery(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(":x: SQL sorgusu çalıştırılamadı: " + e.getMessage());
        }
        return resultSet;
    }
    // :small_blue_diamond: 3. Kaynakları kapat
    public static void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println(":lock: Veritabanı bağlantısı kapatıldı.");
        } catch (SQLException e) {
            System.err.println(":x: Bağlantı kapatma hatası: " + e.getMessage());
        }
    }
    // (Opsiyonel) 4. Connection döndür
    public static Connection getConnection() {
        return connection;
    }
}