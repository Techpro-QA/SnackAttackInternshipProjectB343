package snackattack.pojos;

import java.io.Serializable;

public class AdminGetObjectPojo implements Serializable {
	private int userId;
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String userRole;
	private String createdAt;
	private String regionCode;
	private boolean builtIn;
	private String password;

	public AdminGetObjectPojo() {
	}

	public AdminGetObjectPojo(int userId, String userName, String email, String firstName, String lastName, String address, String phoneNumber, String userRole, String createdAt, String regionCode, boolean builtIn, String password) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userRole = userRole;
		this.createdAt = createdAt;
		this.regionCode = regionCode;
		this.builtIn = builtIn;
		this.password = password;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setUserRole(String userRole){
		this.userRole = userRole;
	}

	public String getUserRole(){
		return userRole;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setRegionCode(String regionCode){
		this.regionCode = regionCode;
	}

	public String getRegionCode(){
		return regionCode;
	}

	public void setBuiltIn(boolean builtIn){
		this.builtIn = builtIn;
	}

	public boolean isBuiltIn(){
		return builtIn;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	@Override
 	public String toString(){
		return 
			"ObjectPojo{" + 
			"userId = '" + userId + '\'' + 
			",userName = '" + userName + '\'' + 
			",email = '" + email + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",address = '" + address + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",userRole = '" + userRole + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",regionCode = '" + regionCode + '\'' + 
			",built_in = '" + builtIn + '\'' + 
			",password = '" + password + '\'' + 
			"}";
		}
}