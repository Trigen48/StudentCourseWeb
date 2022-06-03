package za.co.tumelon.student_course_api.Model;

public class ResponseModel {

    private int status;
    private String message;

    public ResponseModel() {
        status = 0;
        message = "Message not set";
    }

    public ResponseModel(String message) {
        status = 0;
        setMessage(message);
    }

    public ResponseModel(int status, String message) {
        setStatus(status);
        setMessage(message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
