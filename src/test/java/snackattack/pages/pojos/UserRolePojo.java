package snackattack.pages.pojos;

public class UserRolePojo {
    private Integer id;
    private String roleType;
    private String roleName;

    public UserRolePojo() {
    }

    public UserRolePojo(Integer id, String roleType, String roleName) {
        this.id = id;
        this.roleType = roleType;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRolePojo{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
