package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class changeStatusResponse {
    private String title;
    private String detail;
    private List<controllerStatus> controllerStatus;

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

    public List<changeStatusResponse.controllerStatus> getControllerStatus() {
        return controllerStatus;
    }

    public void setControllerStatus(List<changeStatusResponse.controllerStatus> controllerStatus) {
        this.controllerStatus = controllerStatus;
    }

    public class controllerStatus {
        private boolean status;

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
