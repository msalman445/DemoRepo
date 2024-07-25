package pk.org.cas.notepad;

import android.graphics.Bitmap;

import java.security.PublicKey;
import java.sql.Blob;
import java.util.Objects;

public class User {
    int userId = 0;
    String name,email;
    Bitmap profilePic;

    public static final String TABLE_NAME = "Users";
    public static final String COL_USER_ID = "UserId";
    public static final String COL_NAME = "Name";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PROFILE_PIC = "ProfilePic";
    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s BLOB)", TABLE_NAME, COL_USER_ID, COL_NAME, COL_EMAIL, COL_PROFILE_PIC);
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String SELECT_ALL_USERS = "SELECT * FROM "+TABLE_NAME;
    public static final String TRUNCATE_TABLE = "TRUNCATE TABLE "+TABLE_NAME;

    public User() {
    }

    public User(String name, String email, Bitmap profilePic) {
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
    }

    public User(int userId, String name, String email, Bitmap profilePic) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.profilePic = profilePic;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getProfilePic(), user.getProfilePic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getName(), getEmail(), getProfilePic());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profilePic=" + profilePic +
                '}';
    }
}
