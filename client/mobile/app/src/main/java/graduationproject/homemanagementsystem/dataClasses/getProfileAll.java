package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class getProfileAll {
    private String title;
    private String detail;
    private List<data> productionData;

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

    public List<data> getProductionData() {
        return productionData;
    }

    public void setProductionData(List<data> productionData) {
        this.productionData = productionData;
    }
}
