package snackattack.pojos;

import java.io.Serializable;

public class PaymentGetPaymentDataPojo implements Serializable {
	private int id;
	private int orderId;
	private String paymentDate;
	private Object amount;
	private String paymentMethod;
	private String status;

	public PaymentGetPaymentDataPojo() {
	}

	public PaymentGetPaymentDataPojo(int id, int orderId, String paymentDate, Object amount, String paymentMethod, String status) {
		this.id = id;
		this.orderId = orderId;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataPojo{" + 
			"id = '" + id + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",paymentDate = '" + paymentDate + '\'' + 
			",amount = '" + amount + '\'' + 
			",paymentMethod = '" + paymentMethod + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}