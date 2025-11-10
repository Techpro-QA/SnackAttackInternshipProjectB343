package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPojo implements Serializable {

    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String regionCode;
    private UserRolePojo userRole;
    private String password;
    private boolean built_In;

    public UserPojo() {}

    public UserPojo(String userName, String email, String firstName, String lastName,
                    String address, String phoneNumber, String regionCode,
                    UserRolePojo userRole, String password, boolean built_In) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.regionCode = regionCode;
        this.userRole = userRole;
        this.password = password;
        this.built_In = built_In;
    }

    // GETTER & SETTER & toString

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserPojo userPojo = (UserPojo) o;
        return built_In == userPojo.built_In && Objects.equals(userName, userPojo.userName) && Objects.equals(email, userPojo.email) && Objects.equals(firstName, userPojo.firstName) && Objects.equals(lastName, userPojo.lastName) && Objects.equals(address, userPojo.address) && Objects.equals(phoneNumber, userPojo.phoneNumber) && Objects.equals(regionCode, userPojo.regionCode) && Objects.equals(userRole, userPojo.userRole) && Objects.equals(password, userPojo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, firstName, lastName, address, phoneNumber, regionCode, userRole, password, built_In);
    }
}


