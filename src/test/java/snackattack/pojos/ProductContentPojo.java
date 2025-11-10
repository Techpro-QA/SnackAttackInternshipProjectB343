package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContentPojo implements Serializable {
	private int id;
	private String createdAt;
	private String updatedAt;
	private String name;
	private String description;
	private String contents;
	private int price;
	private int discount;
	private List<Object> categories;
	private boolean available;
	private boolean active;
	private Object imgBase64;
	private List<Object> additionsCategory;
	private int orderQuantity;
	private boolean popular;

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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setContents(String contents){
		this.contents = contents;
	}

	public String getContents(){
		return contents;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setDiscount(int discount){
		this.discount = discount;
	}

	public int getDiscount(){
		return discount;
	}

	public void setCategories(List<Object> categories){
		this.categories = categories;
	}

	public List<Object> getCategories(){
		return categories;
	}

	public void setAvailable(boolean available){
		this.available = available;
	}

	public boolean isAvailable(){
		return available;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
	}

	public void setImgBase64(Object imgBase64){
		this.imgBase64 = imgBase64;
	}

	public Object getImgBase64(){
		return imgBase64;
	}

	public void setAdditionsCategory(List<Object> additionsCategory){
		this.additionsCategory = additionsCategory;
	}

	public List<Object> getAdditionsCategory(){
		return additionsCategory;
	}

	public void setOrderQuantity(int orderQuantity){
		this.orderQuantity = orderQuantity;
	}

	public int getOrderQuantity(){
		return orderQuantity;
	}

	public void setPopular(boolean popular){
		this.popular = popular;
	}

	public boolean isPopular(){
		return popular;
	}

	@Override
 	public String toString(){
		return 
			"ContentPojo{" + 
			"id = '" + id + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",contents = '" + contents + '\'' + 
			",price = '" + price + '\'' + 
			",discount = '" + discount + '\'' + 
			",categories = '" + categories + '\'' + 
			",available = '" + available + '\'' + 
			",active = '" + active + '\'' + 
			",imgBase64 = '" + imgBase64 + '\'' + 
			",additionsCategory = '" + additionsCategory + '\'' + 
			",orderQuantity = '" + orderQuantity + '\'' + 
			",popular = '" + popular + '\'' + 
			"}";
		}
}