package by.lukyanets.dto;

import java.util.Date;

public class UserManagementDto {

    private Long id;
    private String name;
    private String email;
    private Date dateOfRegistration;
    private Date dateOfLastLogin;
    private boolean isActive;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getDateOfLastLogin() {
        return dateOfLastLogin;
    }

    public void setDateOfLastLogin(Date dateOfLastLogin) {
        this.dateOfLastLogin = dateOfLastLogin;
    }




}
