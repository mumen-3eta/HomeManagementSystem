package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class processorResponse {
    private String title;
    private String detail;
    private List<processorData> connectionData;

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

    public List<processorData> getConnectionData() {
        return connectionData;
    }

    public void setConnectionData(List<processorData> connectionData) {
        this.connectionData = connectionData;
    }
}
