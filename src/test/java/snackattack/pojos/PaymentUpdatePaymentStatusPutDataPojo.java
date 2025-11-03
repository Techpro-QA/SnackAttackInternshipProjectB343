package snackattack.pojos;

import java.io.Serializable;

public class PaymentUpdatePaymentStatusPutDataPojo implements Serializable {
	private int id;
	private int orderId;
	private int userId;
	private String paymentDate;
	private Object amount;
	private String paymentMethod;
	private String transactionReference;
	private String failureReason;
	private boolean isRefundable;
	private String currency;
	private String paymentGateway;

	public PaymentUpdatePaymentStatusPutDataPojo() {
	}

	public PaymentUpdatePaymentStatusPutDataPojo(int id, int orderId, int userId, String paymentDate, Object amount, String paymentMethod, String transactionReference, String failureReason, boolean isRefundable, String currency, String paymentGateway) {
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionReference = transactionReference;
		this.failureReason = failureReason;
		this.isRefundable = isRefundable;
		this.currency = currency;
		this.paymentGateway = paymentGateway;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setPaymentDate(String paymentDate){
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate(){
		return paymentDate;
	}

	public void setAmount(Object amount){
		this.amount = amount;
	}

	public Object getAmount(){
		return amount;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public void setTransactionReference(String transactionReference){
		this.transactionReference = transactionReference;
	}

	public String getTransactionReference(){
		return transactionReference;
	}

	public void setFailureReason(String failureReason){
		this.failureReason = failureReason;
	}

	public String getFailureReason(){
		return failureReason;
	}

	public void setIsRefundable(boolean isRefundable){
		this.isRefundable = isRefundable;
	}

	public boolean isIsRefundable(){
		return isRefundable;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setPaymentGateway(String paymentGateway){
		this.paymentGateway = paymentGateway;
	}

	public String getPaymentGateway(){
		return paymentGateway;
	}

	@Override
 	public String toString(){
		return 
			"DataPojo{" + 
			"id = '" + id + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",userId = '" + userId + '\'' + 
			",paymentDate = '" + paymentDate + '\'' + 
			",amount = '" + amount + '\'' + 
			",paymentMethod = '" + paymentMethod + '\'' + 
			",transactionReference = '" + transactionReference + '\'' + 
			",failureReason = '" + failureReason + '\'' + 
			",isRefundable = '" + isRefundable + '\'' + 
			",currency = '" + currency + '\'' + 
			",paymentGateway = '" + paymentGateway + '\'' + 
			"}";
		}
}