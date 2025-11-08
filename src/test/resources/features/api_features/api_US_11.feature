@User_Controller
  Feature: Kullanici işlemleri API doğrulamasi

    @patchUser
    @withToken
    Scenario: Kullanıcı geçerli token ile bilgilerini güncelleyebilmelidir
      Given "Login" endpoint'ine bağlantı kurulur ve geçerli token alınır
      When Kullanıcı "updateUser" endpoint'ine PATCH isteği gönderir
      Then Status code 200 olmalı
      And Response body içinde güncellenmiş kullanıcı bilgileri doğrulanmalı



    Feature: Kullanıcı bilgilerini token ile görüntüleme
    @getUser
    @withToken
    Scenario: Kullanıcı geçerli token ile kendi bilgilerini görüntüleyebilmelidir
      Given "Login" endpoint'ine bağlantı kurulur ve geçerli token alınır
      When Kullanıcı "getAuthenticatedUser" endpoint'ine GET isteği gönderir
      Then Status code 200 olmalı
      And Response body içinde kullanıcı email, isim ve adres bilgileri doğrulanmalı



      Feature: Kullanıcının sipariş geçmişini görüntüleme
      @getOrders
      @withToken
      Scenario: Kullanıcı geçerli token ile sipariş geçmişini görüntüleyebilmelidir
        Given "Login" endpoint'ine bağlantı kurulur ve geçerli token alınır
        When Kullanıcı "getOrdersByAuthUser" endpoint'ine GET isteği gönderir
        Then Status code 200 olmalı
        And Response body içinde sipariş listesi boş veya dolu olarak doğrulanmalı



