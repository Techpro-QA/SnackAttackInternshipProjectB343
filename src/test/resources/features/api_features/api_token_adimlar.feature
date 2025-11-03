Feature: admin,user,privateAdmin,privateUser ve dynamicToken
  Scenario: Kullanici token bilgilerini alabilmeli
    And Response body icinde user token bilgileri dogrulanmali
    And Response body icinde admin token bilgileri dogrulanmali
    And Response body icinde private admin bilgileri dogrulanmali
    And Response body icinde private user bilgileri dogrulanmali
    And Response body icinde dynamic token bilgileri dogrulanmali