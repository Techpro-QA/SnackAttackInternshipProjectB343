package snackattack.pojos;

import java.io.Serializable;

public class CategoryPutPojo implements Serializable {
	private int id;
	private String name;
	private boolean active;

	public CategoryPutPojo(int id, String name, boolean active) {
		this.id = id;
		this.name = name;
		this.active = active;
	}

	public CategoryPutPojo() {



	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"CategoryPutPojo{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",active = '" + active + '\'' + 
			"}";
		}
}