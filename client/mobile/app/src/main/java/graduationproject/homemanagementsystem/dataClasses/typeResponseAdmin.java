package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class typeResponseAdmin {
    private String title;
    private String detail;
    private List<typeData> data;

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

    public List<typeData> getData() {
        return data;
    }

    public void setData(List<typeData> typeLabels) {
        this.data = typeLabels;
    }
}
