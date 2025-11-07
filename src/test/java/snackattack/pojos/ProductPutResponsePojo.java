package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPutResponsePojo implements Serializable {
    private int id;
    private String createdAt;
    private String updatedAt;
    private String name;
    private String description;
    private String contents;
    private Object price;
    private Object discount;
    private List<ProductCategoriesPojo> categories;
    private boolean available;
    private boolean active;
    private Object imgBase64;
    private List<ProductAdditionsCategoryPojo> additionsCategory;
    private int orderQuantity;
    private boolean popular;

    public ProductPutResponsePojo() {
    }

    public ProductPutResponsePojo(int id, String createdAt, String updatedAt, String name, String description, String contents, Object price, Object discount, List<ProductCategoriesPojo> categories, boolean available, boolean active, Object imgBase64, List<ProductAdditionsCategoryPojo> additionsCategory, int orderQuantity, boolean popular) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.description = description;
        this.contents = contents;
        this.price = price;
        this.discount = discount;
        this.categories = categories;
        this.available = available;
        this.active = active;
        this.imgBase64 = imgBase64;
        this.additionsCategory = additionsCategory;
        this.orderQuantity = orderQuantity;
        this.popular = popular;
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

    public void setPrice(Object price){
        this.price = price;
    }

    public Object getPrice(){
        return price;
    }

    public void setDiscount(Object discount){
        this.discount = discount;
    }

    public Object getDiscount(){
        return discount;
    }

    public void setCategories(List<ProductCategoriesPojo> categories){
        this.categories = categories;
    }

    public List<ProductCategoriesPojo> getCategories(){
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

    public void setAdditionsCategory(List<ProductAdditionsCategoryPojo> additionsCategory){
        this.additionsCategory = additionsCategory;
    }

    public List<ProductAdditionsCategoryPojo> getAdditionsCategory(){
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
                "DataPojo{" +
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