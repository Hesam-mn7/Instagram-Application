package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerCreateAccountHelperComponent;
import com.alroid.instagramhesam.Exception.Exceptions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountHelperTest {
    CreateAccountHelper createAccountHelper;

    @Before
    public void setUp()
    {
        //createAccountHelper = new CreateAccountHelper();
        createAccountHelper = DaggerCreateAccountHelperComponent.create().getCreateAccountHelper();
    }

    @After
    public void tearDown()
    {
        createAccountHelper = null;
    }

    @Test
    public void registerCreateAccount(){
        //Arrange
        String userName ="hesam_mn7" , password="1234k5fd6"
                , phoneNumber = "09198067831" , email="hmousavioun@gmail.com";

        boolean expect =true;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.UsernameWeakLenghtException.class)
    public void registerCreateAccount_with_short_username(){
        //Arrange
        String userName ="he" , password="1234k5fd6"
                , phoneNumber = "09198067831" , email="hmousavioun@gmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.UsernameWeakLenghtException.class)
    public void registerCreateAccount_with_empty_username(){
        //Arrange
        String userName ="" , password="1234k5fd6"
                , phoneNumber = "09198067831" , email="hmousavioun@gmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.PasswordWeakLenghtException.class)
    public void registerCreateAccount_with_short_password(){
        //Arrange
        String userName ="hesam_mn7" , password="12dd"
                , phoneNumber = "09198067831" , email="hmousavioun@gmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }
    @Test(expected = Exceptions.PasswordWeakLenghtException.class)
    public void registerCreateAccount_with_empty_password(){
        //Arrange
        String userName ="hesam_mn7" , password=""
                , phoneNumber = "09198067831" , email="hmousavioun@gmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.PhoneNumberLenghtException.class)
    public void registerCreateAccount_with_incorrect_phoneNumber(){
        //Arrange
        String userName ="hesam_mn7" , password="1234k5fd6"
                , phoneNumber = "198067831" , email="hmousavioun@gmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.PhoneNumberLenghtException.class)
    public void registerCreateAccount_with_empty_phoneNumber(){
        //Arrange
        String userName ="hesam_mn7" , password="1234k5fd6"
                , phoneNumber = "" , email="hmousavioun@gmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.EmailWeakLenghtException.class)
    public void registerCreateAccount_with_incorrect_email(){
        //Arrange
        String userName ="hesam_mn7" , password="1234k5fd6"
                , phoneNumber = "09198067831" , email="hmousavioungmail.com";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }
    @Test(expected = Exceptions.EmailWeakLenghtException.class)
    public void registerCreateAccount_with_empty_email(){
        //Arrange
        String userName ="hesam_mn7" , password="1234k5fd6"
                , phoneNumber = "09198067831" , email="";

        boolean expect =false;

        //Act
        boolean result= createAccountHelper.registerCreateAccount(userName,password,phoneNumber,email);

        //Assert
        Assert.assertEquals(expect , result);
    }


}