package snackattack.utilities;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    // URL verisi
    public static final String SPEEDYLI_URL = "https://www.speedyli.com/";
    // Success mesajı
    public static final String SUCCESS_MESSAGE = "You are registered successfully";
    // Wait süreleri
    public static final int IMPLICIT_WAIT = 15;
    public static final int EXPLICIT_WAIT = 10;
    public static final int SCROLL_WAIT = 2;
    // Form verileri
    public static String expectedFirstName;
    public static String expectedLastName;
    public static String expectedPhoneNumber;
    public static String expectedAddress;
    public static String expectedZipCode;
    public static String expectedEmail;
    public static int userId;

    //Register verileri //user icin
    public static String email;
    public static String lastName;
    public static String firstName;
    public static String password;
    public static String userName;
    public static String address;
    public static String phoneNumber;
    public static String newPassword;

    //private admin verileri
    public static String adminEmail;
    public static String adminPassword;


    //Product verileri
    public static String expectedProductId;
    public static String expectedProductName;
    public static String expectedDescriptionText;
    public static String expectedContentsText;
    public static String expectedPriceText;
    public static String expectedDiscountText;
    public static String expectedCategoryText;
    public static String expectedAdditionCategoryText;
    public static Integer createdProductId;
    public static Map<String, Object> putPayload;


    //Payment verileri
    public static String expectedFirstRowPaymentId;
    public static String expectedFirstRowOrderId;
    public static String expectedFirstRowUserId;
    public static String expectedFirstRowAmount;
    public static String expectedFirstRowPaymentDate;


    //Category Management verileri
    public static String expectedCategoryName;
    public static Integer createdCategoryId;

    //Admin Management verileri
    public static String expectedAdminId;

    //Customer Management verileri
    public static String expectedUserId;

    //Contact Message verileri
    public static String subject;
    public static String message;
    public static String createdMessageId;



    //Additon Category Dataları
    public static Integer createdAdditonCategoryId;
}