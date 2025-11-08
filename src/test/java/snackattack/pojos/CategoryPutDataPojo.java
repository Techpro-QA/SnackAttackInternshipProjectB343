package snackattack.pojos;

import java.io.Serializable;

public class CategoryPutDataPojo implements Serializable {
	private int id;
	private String createdAt;
	private String updatedAt;
	private String name;
	private boolean active;
	private String icon;

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

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
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
			",icon = '" + icon + '\'' + 
			"}";
		}
}