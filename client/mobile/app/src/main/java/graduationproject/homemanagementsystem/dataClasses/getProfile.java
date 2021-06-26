package graduationproject.homemanagementsystem.dataClasses;

import java.util.List;

public class getProfile {
    private String title;
    private String detail;
    private List<data> profileData;

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

    public List<data> getProfileData() {
        return profileData;
    }

    public void setProfileData(List<data> profileData) {
        this.profileData = profileData;
    }

}
