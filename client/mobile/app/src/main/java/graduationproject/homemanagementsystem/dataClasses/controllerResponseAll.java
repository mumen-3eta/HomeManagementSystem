package graduationproject.homemanagementsystem.dataClasses;


import java.util.List;

public class controllerResponseAll {
    private String title;
    private String detail;
    private List<controllerData> productionData;


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

    public List<controllerData> getProductionData() {
        return productionData;
    }

    public void setProductionData(List<controllerData> productionData) {
        this.productionData = productionData;
    }
}
