package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class locationResponse {
    private String title;
    private String detail;
    private List<locationData> locationLabels;

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

    public List<locationData> getLocationLabels() {
        return locationLabels;
    }

    public void setLocationLabels(List<locationData> locationLabels) {
        this.locationLabels = locationLabels;
    }
}
