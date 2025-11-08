@DB_US_17_user_roles_verification
  Feature: User_roles table doğrulanması

    Background:
      Given Veritabanı bağlantısı kurulur

    Scenario: User roles tablosundaki role_type isimleri doğrulanmalı
        When Kullanici "role"tablosundaki tüm kayıtları sorgular
        Then Role tablosu aşağıdaki verileri içermelidir:
        | id | role_type | role_name  |
        | 1  | ADMIN     | Admin      |
        | 2  | MEMBER    | Customers  |
        | 3  | EMPLOYEE  | Employees  |
        | 4  | ANONYMOUS | Anonymous  |
       And Bağlantı kapatılır

    Scenario: Role tablosunda olmayan role_type sorgulandığında kayıt bulunmamalıdır
      When Kullanıcı kayıtlı olmayan "MANAGER" role_type değerine sahip kaydı sorgular
      Then Herhangi bir kayıt dönmemelidir
      And Bağlantı kapatılır

    Scenario: Role tablosunda yinelenen (duplicate) role_type olmamalıdır
      When Kullanıcı role_type değerlerini sorgular
      Then Her role_type yalnızca bir kez bulunmalıdır
      And Bağlantı kapatılır