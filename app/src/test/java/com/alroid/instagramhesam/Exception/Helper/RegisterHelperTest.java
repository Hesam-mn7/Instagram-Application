package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerRegisterHelperComponent;
import com.alroid.instagramhesam.Exception.Exceptions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterHelperTest {
    RegisterHelper registerHelper;

    @Before
    public void setUp()
    {
//        registerHelper = new RegisterHelper();
        registerHelper = DaggerRegisterHelperComponent.create().getRegisterHelper();
    }

    @After
    public void tearDown()
    {
        registerHelper = null;
    }

    @Test
    public void Register(){
        //Arrange
        String name ="Hesam Mousavioun" , bio="Android developer" , profilePic = "profilePic";

        boolean expect =true;

        //Act
        boolean result= registerHelper.register(name,bio,profilePic);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.NameLenghtException.class)
    public void Register_with_empty_name(){
        //Arrange
        String name ="" , bio="Android developer" , profilePic = "profilePic";

        boolean expect =false;

        //Act
        boolean result= registerHelper.register(name,bio,profilePic);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.BioLenghtException.class)
    public void Register_with_empty_bio(){
        //Arrange
        String name ="Hesam Mousavioun" , bio="" , profilePic = "profilePic";

        boolean expect =false;

        //Act
        boolean result= registerHelper.register(name,bio,profilePic);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.NoProfilePicException.class)
    public void Register_with_empty_ProfilePic(){
        //Arrange
        String name ="Hesam Mousavioun" , bio="Android developer" , profilePic = "";

        boolean expect =false;

        //Act
        boolean result= registerHelper.register(name,bio,profilePic);

        //Assert
        Assert.assertEquals(expect , result);
    }

}