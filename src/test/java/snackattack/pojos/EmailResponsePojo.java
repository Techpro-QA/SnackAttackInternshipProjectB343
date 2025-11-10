package snackattack.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailResponsePojo implements Serializable {

    private boolean success;
    private String message;
    private boolean data;
    private Object errors;   // API bazen null/obj döndürebilir
    private String status;
    private String timestamp;

    public EmailResponsePojo() {}

    public EmailResponsePojo(boolean success, String message, boolean data,
                             Object errors, String status, String timestamp) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.status = status;
        this.timestamp = timestamp;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isData() { return data; }
    public void setData(boolean data) { this.data = data; }

    public Object getErrors() { return errors; }
    public void setErrors(Object errors) { this.errors = errors; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "EmailResponsePojo{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errors=" + errors +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
