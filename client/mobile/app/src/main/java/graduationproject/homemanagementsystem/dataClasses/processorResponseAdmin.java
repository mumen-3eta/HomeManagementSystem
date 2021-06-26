package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class processorResponseAdmin {
    private String title;
    private String detail;
    private List<processorData> newProcessor;

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

    public List<processorData> getNewProcessor() {
        return newProcessor;
    }

    public void setNewProcessor(List<processorData> connectionData) {
        this.newProcessor = connectionData;
    }
}
