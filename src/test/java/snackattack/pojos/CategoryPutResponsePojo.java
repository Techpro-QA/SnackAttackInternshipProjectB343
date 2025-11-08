package snackattack.pojos;

import java.io.Serializable;

public class CategoryPutResponsePojo implements Serializable {
	private boolean success;
	private String message;
	private CategoryPutDataPojo data;
	private Object errors;
	private String status;
	private String timestamp;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setData(CategoryPutDataPojo data){
		this.data = data;
	}

	public CategoryPutDataPojo getData(){
		return data;
	}

	public void setErrors(Object errors){
		this.errors = errors;
	}

	public Object getErrors(){
		return errors;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"CategoryPutResponsePojo{" + 
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			",status = '" + status + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}