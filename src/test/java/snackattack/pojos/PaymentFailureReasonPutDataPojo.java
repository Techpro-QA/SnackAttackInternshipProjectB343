package snackattack.pojos;

import java.io.Serializable;

public class PaymentFailureReasonPutDataPojo implements Serializable {
	private int id;
	private String paymentDate;
	private Object amount;
	private String status;
	private String transactionReference;
	private String failureReason;
	private boolean isRefundable;

	public PaymentFailureReasonPutDataPojo() {
	}

	public PaymentFailureReasonPutDataPojo(int id, String paymentDate, Object amount, String status, String transactionReference, String failureReason, boolean isRefundable) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.status = status;
		this.transactionReference = transactionReference;
		this.failureReason = failureReason;
		this.isRefundable = isRefundable;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
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

	@Override
 	public String toString(){
		return 
			"DataPojo{" + 
			"id = '" + id + '\'' + 
			",paymentDate = '" + paymentDate + '\'' + 
			",amount = '" + amount + '\'' + 
			",status = '" + status + '\'' + 
			",transactionReference = '" + transactionReference + '\'' + 
			",failureReason = '" + failureReason + '\'' + 
			",isRefundable = '" + isRefundable + '\'' + 
			"}";
		}
}