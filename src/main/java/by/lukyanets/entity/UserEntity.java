package by.lukyanets.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRegistration;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfLastLogin;
    @Column
    private boolean active = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOFRegistration) {
        this.dateOfRegistration = dateOFRegistration;
    }

    public Date getDateOfLastLogin() {
        return dateOfLastLogin;
    }

    public void setDateOfLastLogin(Date dateOfLastLogin) {
        this.dateOfLastLogin = dateOfLastLogin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
