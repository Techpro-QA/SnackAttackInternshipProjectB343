package snackattack.pojos;

import java.io.Serializable;

public class AdminGetResponsePojo implements Serializable {
	private String message;
	private String status;
	private AdminGetObjectPojo object;

	public AdminGetResponsePojo() {
	}

	public AdminGetResponsePojo(String message, String status, AdminGetObjectPojo object) {
		this.message = message;
		this.status = status;
		this.object = object;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setObject(AdminGetObjectPojo object){
		this.object = object;
	}

	public AdminGetObjectPojo getObject(){
		return object;
	}

	@Override
 	public String toString(){
		return 
			"AdminGetResponsePojo{" + 
			"message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",object = '" + object + '\'' + 
			"}";
		}
}