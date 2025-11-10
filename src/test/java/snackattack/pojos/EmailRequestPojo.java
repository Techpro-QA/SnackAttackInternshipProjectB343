package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailRequestPojo implements Serializable {

    private String email;
    private String subject;
    private List<UserPojo> users;
    private List<String> filePath;

    public EmailRequestPojo() {}

    public EmailRequestPojo(String email, String subject, List<UserPojo> users, List<String> filePath) {
        this.email = email;
        this.subject = subject;
        this.users = users;
        this.filePath = filePath;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public List<UserPojo> getUsers() { return users; }
    public void setUsers(List<UserPojo> users) { this.users = users; }

    public List<String> getFilePath() { return filePath; }
    public void setFilePath(List<String> filePath) { this.filePath = filePath; }

    @Override
    public String toString() {
        return "EmailRequestPojo{" +
                "email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", users=" + users +
                ", filePath=" + filePath +
                '}';
    }
}
