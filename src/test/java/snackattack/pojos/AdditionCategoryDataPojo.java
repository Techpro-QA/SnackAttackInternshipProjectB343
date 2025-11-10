package snackattack.pojos;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionCategoryDataPojo implements Serializable {
	private int id;
	private String createdAt;
	private String updatedAt;
	private String name;
	private boolean active;
	private Object additions;

	public AdditionCategoryDataPojo() {
	}

	public AdditionCategoryDataPojo(int id, String createdAt, String updatedAt, String name, boolean active, Object additions) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.active = active;
		this.additions = additions;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
	}

	public void setAdditions(Object additions){
		this.additions = additions;
	}

	public Object getAdditions(){
		return additions;
	}

	@Override
 	public String toString(){
		return 
			"DataPojo{" + 
			"id = '" + id + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",active = '" + active + '\'' + 
			",additions = '" + additions + '\'' + 
			"}";
		}
}