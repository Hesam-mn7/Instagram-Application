package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerSelectImagePostHelperComponent;
import com.alroid.instagramhesam.Exception.Exceptions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectImagePostHelperTest {
    SelectImagePostHelper selectImagePostHelper;

    @Before
    public void setUp()
    {
        //selectImagePostHelper = new SelectImagePostHelper();
        selectImagePostHelper = DaggerSelectImagePostHelperComponent.create().getSelectImagePostHelper();
    }

    @After
    public void tearDown()
    {
        selectImagePostHelper = null;
    }

    @Test
    public void Register(){
        //Arrange
        String imgPost ="imgPost";

        boolean expect =true;

        //Act
        boolean result= selectImagePostHelper.register(imgPost);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.NoPicException.class)
    public void Register_with_empty_Caption(){
        //Arrange
        String imgPost ="";

        boolean expect =false;

        //Act
        boolean result= selectImagePostHelper.register(imgPost);

        //Assert
        Assert.assertEquals(expect , result);
    }

}