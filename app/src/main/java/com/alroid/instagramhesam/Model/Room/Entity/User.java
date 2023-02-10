package com.alroid.instagramhesam.Model.Room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.alroid.instagramhesam.Exception.Exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String userName;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String bio;

    @ColumnInfo
    private String profilePic;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String phoneNumber;

    @Ignore
    public User(int id, String userName, String password, String name, String bio,
                String profilePic, String email, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.bio = bio;
        this.profilePic = profilePic;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length()<3)
            throw new Exceptions.UsernameWeakLenghtException();
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length()<6)
            throw new Exceptions.PasswordWeakLenghtException();
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty())
            throw new Exceptions.NameLenghtException();
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        if (bio.isEmpty())
            throw new Exceptions.BioLenghtException();
        this.bio = bio;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        if (profilePic.isEmpty())
            throw new Exceptions.NoProfilePicException();
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!validateEmail(email))
            throw new Exceptions.EmailWeakLenghtException();
        this.email = email;
    }
    public boolean validateEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!(phoneNumber.length()==11 && phoneNumber.startsWith("0") || phoneNumber.length()==10 && !phoneNumber.startsWith("0")))
            throw  new Exceptions.PhoneNumberLenghtException();
        this.phoneNumber = phoneNumber;
    }



}
