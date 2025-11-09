package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditonCategoryPostResponsePojo implements Serializable {
	private boolean success;
	private String message;
	private AdditionCategoryDataPojo data;
	private Object errors;
	private String status;
	private String timestamp;

	public AdditonCategoryPostResponsePojo() {
	}

	public AdditonCategoryPostResponsePojo(boolean success, String message, AdditionCategoryDataPojo data, Object errors, String timestamp, String status) {
		this.success = success;
		this.message = message;
		this.data = data;
		this.errors = errors;
		this.timestamp = timestamp;
		this.status = status;
	}

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

	public void setData(AdditionCategoryDataPojo data){
		this.data = data;
	}

	public AdditionCategoryDataPojo getData(){
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
			"AdditonCategoryPostResponsePojo{" + 
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			",status = '" + status + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}