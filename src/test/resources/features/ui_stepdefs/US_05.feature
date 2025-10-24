@US_05
  Feature: User olarak sayfaya giris yapabilme ve Kullanici bilgilerini degistirebilme
    Scenario: TC_01
      Given Sayfaya gidilir
      And Kullanici "user" olarak giriş yapar
      And Kullanici user panelden Settings'a tiklar
      And Kullanici Username Textbox'ini "h_kirca" ile doldurur
      And Kullanici Email Textbox'ini "havva101997@gmail.com" ile doldurur
      And Kullanici First Name Textbox'ini "Havva6" ile doldurur
      And Kullanici Last Name Textbox'ini "kirca" ile doldurur
      And Kullanici Address Textbox'ini "hjhjl" ile doldurur
      And Kullanici Phone Number Textbox'ini "542 322 1395" ile doldurur
      And Kullanici Region Code Textbox'ini "+90(TR)" ile doldurur
      And Kullanici Password Textbox'ini "62874091" ile doldurur
      And Kullanici Update Profile butonuna tiklar

