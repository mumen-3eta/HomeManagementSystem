package graduationproject.homemanagementsystem.dataClasses;


import java.util.List;

public class controllerResponseAdmin {
    private String title;
    private String detail;
    private List<controllerData> newController;


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

    public List<controllerData> getNewController() {
        return newController;
    }

    public void setNewController(List<controllerData> connectionData) {
        this.newController = connectionData;
    }
}
