package model;

public class User {
    private String login;
    private String password;

    public User(String login) {
        this.login = login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User user = (User)o;
            return this.getLogin().equals(user.getLogin());
        }
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getLogin().hashCode();
        return result;
    }
}
