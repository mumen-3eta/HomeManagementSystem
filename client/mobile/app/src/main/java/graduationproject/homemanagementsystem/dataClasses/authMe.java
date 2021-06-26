package graduationproject.homemanagementsystem.dataClasses;

public class authMe {
    private String title;
    private String detail;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User {
        private int id;
        private String email;
        private String user_name;
        private boolean is_admin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public boolean isIs_admin() {
            return is_admin;
        }

        public void setIs_admin(boolean is_admin) {
            this.is_admin = is_admin;
        }
    }
}
