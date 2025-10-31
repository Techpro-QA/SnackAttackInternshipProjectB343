package snackattack.pages.pojos.productpojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestPojo implements Serializable {

    private String createdAt;
    private String updatedAt;
    private String name;
    private String description;
    private String contents;
    private int price;
    private int discount;
    private boolean available;
    private boolean active;
    private String imgBase64;
    private int orderQuantity;
    private boolean popular;
    private List<CategoriesPojo> categories;
    private List<AdditionsCategoryPojo> additionsCategory;

    public ProductRequestPojo() {}


    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getImgBase64() { return imgBase64; }
    public void setImgBase64(String imgBase64) { this.imgBase64 = imgBase64; }

    public int getOrderQuantity() { return orderQuantity; }
    public void setOrderQuantity(int orderQuantity) { this.orderQuantity = orderQuantity; }

    public boolean isPopular() { return popular; }
    public void setPopular(boolean popular) { this.popular = popular; }

    public List<CategoriesPojo> getCategories() { return categories; }
    public void setCategories(List<CategoriesPojo> categories) { this.categories = categories; }

    public List<AdditionsCategoryPojo> getAdditionsCategory() { return additionsCategory; }
    public void setAdditionsCategory(List<AdditionsCategoryPojo> additionsCategory) { this.additionsCategory = additionsCategory; }

    @Override
    public String toString() {
        return "ProductRequestPojo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                ", additionsCategory=" + additionsCategory +
                '}';
    }
}
