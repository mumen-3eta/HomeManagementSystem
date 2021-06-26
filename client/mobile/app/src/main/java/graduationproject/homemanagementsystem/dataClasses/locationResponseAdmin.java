package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class locationResponseAdmin {
    private String title;
    private String detail;
    private List<locationData> data;

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

    public List<locationData> getData() {
        return data;
    }

    public void setData(List<locationData> locationLabels) {
        this.data = locationLabels;
    }
}
