package snackattack.pojos;

public class UserRegisterResponsePojo {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String message;
    private boolean success;
    private UserRolePojo userRole;

    public UserRegisterResponsePojo() {
    }

    public UserRegisterResponsePojo(String email, String firstName, String lastName, String phoneNumber, String message, boolean success, UserRolePojo userRole) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.success = success;
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserRolePojo getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRolePojo userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRegisterResponsePojo{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", userRole=" + userRole +
                '}';
    }
}
