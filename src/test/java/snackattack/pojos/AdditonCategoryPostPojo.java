package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditonCategoryPostPojo implements Serializable {
	private String name;
	private boolean active;

	public AdditonCategoryPostPojo() {
	}

	public AdditonCategoryPostPojo(String name, boolean active) {
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
			"AdditonCategoryPostPojo{" + 
			"name = '" + name + '\'' + 
			",active = '" + active + '\'' + 
			"}";
		}
}