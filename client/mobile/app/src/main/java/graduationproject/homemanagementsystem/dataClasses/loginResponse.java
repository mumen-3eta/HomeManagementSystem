package graduationproject.homemanagementsystem.dataClasses;

import com.google.gson.annotations.SerializedName;

public class loginResponse {
    private String title;
    private String detail;

    public loginResponse(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public loginResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
