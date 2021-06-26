package graduationproject.homemanagementsystem.dataClasses;


import java.util.List;

public class controllerResponse {
    private String title;
    private String detail;
    private List<controllerData> connectionData;


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

    public List<controllerData> getConnectionData() {
        return connectionData;
    }

    public void setConnectionData(List<controllerData> connectionData) {
        this.connectionData = connectionData;
    }
}
