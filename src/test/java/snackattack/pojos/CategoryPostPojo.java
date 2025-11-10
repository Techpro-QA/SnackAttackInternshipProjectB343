package snackattack.pojos;

import java.io.Serializable;

public class CategoryPostPojo implements Serializable {
	private String name;
	private boolean active;

	public CategoryPostPojo() {
	}

	public CategoryPostPojo(String name, boolean active) {
		this.name = name;
		this.active = active;
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

	@Override
 	public String toString(){
		return 
			"CategoryPostPojo{" + 
			"name = '" + name + '\'' + 
			",active = '" + active + '\'' + 
			"}";
		}
}