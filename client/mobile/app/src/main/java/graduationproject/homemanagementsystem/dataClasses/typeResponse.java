package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class typeResponse {
    private String title;
    private String detail;
    private List<typeData> typeLabels;

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

    public List<typeData> getTypeLabels() {
        return typeLabels;
    }

    public void setTypeLabels(List<typeData> typeLabels) {
        this.typeLabels = typeLabels;
    }
}
