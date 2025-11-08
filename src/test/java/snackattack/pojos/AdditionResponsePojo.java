package snackattack.pojos;

public class AdditionResponsePojo {

    private boolean success;
    private String message;
    private AdditionData data;
    private String status;

    // Getter & Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdditionData getData() {
        return data;
    }

    public void setData(AdditionData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Inner class: AdditionData
    public static class AdditionData {
        private int id;
        private String name;
        private String description;
        private double price;
        private boolean active;
        private String additionCategoryName;

        // Getter & Setter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getAdditionCategoryName() {
            return additionCategoryName;
        }

        public void setAdditionCategoryName(String additionCategoryName) {
            this.additionCategoryName = additionCategoryName;
        }
    }
}
