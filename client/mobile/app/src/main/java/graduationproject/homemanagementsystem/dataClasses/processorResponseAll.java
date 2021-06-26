package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class processorResponseAll {
    private String title;
    private String detail;
    private List<processorData> productionData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String message) {
        this.detail = message;
    }

    public List<processorData> getProductionData() {
        return productionData;
    }

    public void setProductionData(List<processorData> productionData) {
        this.productionData = productionData;
    }
}
