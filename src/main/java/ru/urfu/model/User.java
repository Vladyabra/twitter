package ru.urfu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.nio.charset.Charset;

@Entity
@Table(name = "TBL_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 16, message = "Username size must be between {min} and {max}")
    @Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Illegal characters in username")
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 5, max = 64, message = "Email size must be between {min} and {max}")
    @Email(message = "Incorrect email")
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @NotNull
    @Size(min = 6, max = 32, message = "Password size must be between {min} and {max}")
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    @JsonProperty("gravatarUrl")
    private String gravatarUrl() {
        return "https://www.gravatar.com/avatar/" + DigestUtils.md5DigestAsHex(
                getEmail().getBytes(Charset.forName("UTF-8")));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null && username.equals(user.username);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + username.hashCode();
        return result;
    }
}
