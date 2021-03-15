package graduationproject.homemanagementsystem.dataClasses;

public class deviceClass {
    private String name;
    private String location;
    private String type;

    public deviceClass() {
    }

    public deviceClass(String name, String location, String type) {
        this.name = name;
        this.location = location;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
