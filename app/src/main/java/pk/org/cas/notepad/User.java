package pk.org.cas.notepad;

import java.security.PublicKey;
import java.sql.Blob;
import java.util.Objects;

public class User {
    String name;
    String email;

    public static final String TABLE_NAME = "Users";
    public static final String COL_USER_ID = "UserId";
    public static final String COL_NAME = "Name";
    public static final String COL_EMAIL = "Email";
    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", TABLE_NAME, COL_USER_ID, COL_NAME, COL_EMAIL);
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS"+TABLE_NAME;
    public static final String SELECT_ALL_NOTES = "SELECT * FROM"+TABLE_NAME;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
