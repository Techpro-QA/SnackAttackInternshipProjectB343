package snackattack.pojos;

import java.util.List;

public class AdditionRequestPojo {

    private int id;
    private String createdAt;
    private String updatedAt;
    private String name;
    private String description;
    private double price;
    private boolean active;
    private CategoryRequest categoryRequest;
    private int productId;
    private List<String> removeIngredients;
    private List<String> addExtras;

    // Default constructor
    public AdditionRequestPojo() {}

    // Getter ve Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public CategoryRequest getCategoryRequest() { return categoryRequest; }
    public void setCategoryRequest(CategoryRequest categoryRequest) { this.categoryRequest = categoryRequest; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public List<String> getRemoveIngredients() { return removeIngredients; }
    public void setRemoveIngredients(List<String> removeIngredients) { this.removeIngredients = removeIngredients; }

    public List<String> getAddExtras() { return addExtras; }
    public void setAddExtras(List<String> addExtras) { this.addExtras = addExtras; }

    @Override
    public String toString() {
        return "AdditionRequestPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", active=" + active +
                ", categoryRequest=" + categoryRequest +
                ", productId=" + productId +
                ", removeIngredients=" + removeIngredients +
                ", addExtras=" + addExtras +
                '}';
    }

    // Inner class
    public static class CategoryRequest {
        private int id;
        private String createdAt;
        private String updatedAt;
        private String name;
        private boolean active;

        public CategoryRequest() {}

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

        public String getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }

        @Override
        public String toString() {
            return "CategoryRequest{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", active=" + active +
                    '}';
        }
    }
}
