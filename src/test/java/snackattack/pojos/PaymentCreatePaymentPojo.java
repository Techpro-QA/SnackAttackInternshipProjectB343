package snackattack.pojos;

import java.io.Serializable;

public class PaymentCreatePaymentPojo implements Serializable {
	private int orderId;
	private Object amount;
	private String paymentMethod;
	private String paymentDate;
	private String currency;
	private String paymentGateway;
	private String description;
	private String createdAt;
	private String updatedAt;
	private Object failureReason;

	public PaymentCreatePaymentPojo() {
	}

	public PaymentCreatePaymentPojo(int orderId, Object amount, String paymentMethod, String paymentDate, String currency, String paymentGateway, String description, String createdAt, String updatedAt, Object failureReason) {
		this.orderId = orderId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.currency = currency;
		this.paymentGateway = paymentGateway;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.failureReason = failureReason;
	}

	public void setOrderId(int orderId){
		this.orderId = orderId;
	}

	public int getOrderId(){
		return orderId;
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

	public void setPaymentDate(String paymentDate){
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate(){
		return paymentDate;
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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setFailureReason(Object failureReason){
		this.failureReason = failureReason;
	}

	public Object getFailureReason(){
		return failureReason;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePojo{" + 
			"orderId = '" + orderId + '\'' + 
			",amount = '" + amount + '\'' + 
			",paymentMethod = '" + paymentMethod + '\'' + 
			",paymentDate = '" + paymentDate + '\'' + 
			",currency = '" + currency + '\'' + 
			",paymentGateway = '" + paymentGateway + '\'' + 
			",description = '" + description + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			",failureReason = '" + failureReason + '\'' + 
			"}";
		}
}