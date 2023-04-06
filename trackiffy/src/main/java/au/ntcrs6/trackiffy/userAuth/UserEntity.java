package au.ntcrs6.trackiffy.userAuth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isProvincial; // fasle = local
    private Boolean isActive; // false = disabled user

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, String password,
            Boolean isProvincial, Boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isProvincial = isProvincial;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsProvincial() {
        return isProvincial;
    }

    public void setIsProvincial(Boolean isProvincial) {
        this.isProvincial = isProvincial;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        System.out.println("\u001B[32m" +
                "UserEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
                + email
                + ", password=" + password + ", isProvincial=" + isProvincial + ", isActive="
                + isActive + "]" + "\u001B[0m");
        return null;
    }

}
