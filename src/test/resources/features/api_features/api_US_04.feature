
@Payment_Controller
Feature: Payment Controller API'sindeki temel islemlerin sırasıyla test edilmesi


  # GET /api/payments/{paymentId}
  # Bu senaryoda belirli bir ödemenin detaylarını alıyoruz
  @GetPayments
  @adminToken
  Scenario: Ödeme detaylarını al
    Given "PaymentsPaymentId" endpoint'ine baglanti kurulur
    When Ödeme detaylari GET istegi ile alinir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde ödeme bilgileri dogrulanmali



  # GET /api/payments
  # Bu senaryoda sistemdeki tüm ödemeleri listeliyoruz
  @GetPaymentsId
  @adminToken
  Scenario: Tüm ödemeleri listele
    Given "Payments" endpoint'ine baglanti kurulur
    When Ödemeler GET istegi ile listelenir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde ödeme listesi dogrulanmali




  # PUT /api/payments/{paymentId}/failureReason
  # Bu senaryoda bir ödemenin failure reason bilgisini güncelliyoruz
  @PaymentsFailureReason
  @adminToken
  Scenario: Ödemenin Failure Reason bilgisini güncelle
    Given "PaymentsFailureReason" endpoint'ine baglanti kurulur
    When Failure reason bilgisini guncellemek icin PUT istegi gonderilir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde failure reason bilgisi dogrulanmali




  # PUT /api/payments/updatePaymentStatus/{paymentId}
  # Bu senaryoda bir ödemenin durumunu güncelliyoruz
  @PaymentUpdatePaymentStatus
  @adminToken
  Scenario: Ödemenin durumunu güncelle
    Given "PaymentsUpdateStatus" endpoint'ine baglanti kurulur
    When Payment status bilgisini guncellemek icin PUT istegi gonderilir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde status bilgisi dogrulanmali




  # GET /api/payments/{paymentId}/transactionReference
  # Bu senaryoda belirli bir ödemenin transaction reference bilgisini alıyoruz
  @PaymentTransactionReference
  @adminToken
  Scenario: Transaction reference bilgisini al
    Given "PaymentsTransactionReference" endpoint'ine baglanti kurulur
    When Transaction reference GET istegi ile alinir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde transaction bilgisi dogrulanmali



  # POST /api/payments/createPayment
  # Bu senaryoda yeni bir ödeme oluşturuyoruz
  @PaymentCreate
  @userOrderToken
  Scenario: Yeni ödeme olustur
    Given "PaymentsCreatePayment" endpoint'ine baglanti kurulur
    When Yeni ödeme olusturmak icin POST istegi gonderilir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde olusturulan ödeme bilgisi dogrulanmali



    # GET /api/payments/{paymentId}/isRefundable
    # Bu senaryoda belirli bir ödemenin iadesi edilebilirligi bilgisini aliyoruz
  @RefundablePayment
  @adminToken

  Scenario:Bir Odemenin iade edilebilme bilgisini al
    Given "paymentIsRefundable" endpoint'ine baglanti kurulur
    When Refundable istegi GET istegi ile alinir
    And Status code 200 oldugu dogrulanir
    And Response body icinde iade islemi bilgisi dogrulanmali

#GET ile admin bir kullanicinin odemelerini listeleyebilmeli
  @Payment_UserPayments
  @adminToken
  Scenario: Kullanıcının ödemelerini listele
    Given "paymentsUserById" endpoint'ine baglanti kurulur
    When Odemeler GET istegi ile alinir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde "message" "Payments retrieved successfully" olmali


#GET Bir siparişe ait ödemelerin listelenmesi (dinamik)
  @Payment_OrderPayments
  @adminToken

  Scenario: Belirli siparişin ödemelerini listele
    Given "paymentsByOrderId" endpoint'ine baglanti kurulur
    When Siparis ödemeleri GET istegi ile alinir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde "message" "Payments retrieved successfully" olmali





# GET ile Kullanıcı ödemelerinin sayfalanmış olarak listelenmesi
  @Payment_ListUserPayments
  @adminToken

  Scenario: Kullanıcı ödemeleri sayfa bazlı listeleme testi
    Given "listUserPayments" endpoint'ine baglanti kurulur
    When Kullanici ödemeleri sayfalari  GET istegi ile alinir
    Then Status code 200 oldugu dogrulanir
    And Response body icinde basarili mesaj dogrulanmali



