package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactMessagePostPojo implements Serializable {
	private String name;
	private String email;
	private String subject;
	private String message;

	public ContactMessagePostPojo() {
	}

	public ContactMessagePostPojo(String name, String email, String subject, String message) {
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ContactMessagePostPojo{" + 
			"name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			",subject = '" + subject + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}