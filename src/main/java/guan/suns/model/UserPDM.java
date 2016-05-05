package guan.suns.model;

import guan.suns.basicClass.Gender;
import guan.suns.basicClass.PersonType;

import javax.persistence.*;

/**
 * Created by lenovo on 2016/5/5.
 */
@Entity
@Table(name = "userTable")
public class UserPDM {
    @Id
    @Column(name = "userID")
    private String userID;

    @Enumerated(EnumType.STRING)
    @Column(name = "personType")
    private PersonType personType;

    @Column(name = "password")
    private String password;

    public UserPDM(String userID, PersonType personType, String password) {
        this.userID = userID;
        this.personType = personType;
        this.password = password;
    }

    public UserPDM() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
