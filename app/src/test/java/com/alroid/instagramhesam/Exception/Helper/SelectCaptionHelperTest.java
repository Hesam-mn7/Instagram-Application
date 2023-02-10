package com.alroid.instagramhesam.Exception.Helper;

import com.alroid.instagramhesam.Di.Component.DaggerSelectCaptionHelperComponent;
import com.alroid.instagramhesam.Exception.Exceptions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectCaptionHelperTest {

    SelectCaptionHelper selectCaptionHelper;

    @Before
    public void setUp()
    {
        //selectCaptionHelper = new SelectCaptionHelper();
        selectCaptionHelper = DaggerSelectCaptionHelperComponent.create().getSelectCaptionHelper();
    }

    @After
    public void tearDown()
    {
        selectCaptionHelper = null;
    }

    @Test
    public void Register(){
        //Arrange
        String caption ="caption";

        boolean expect =true;

        //Act
        boolean result= selectCaptionHelper.register(caption);

        //Assert
        Assert.assertEquals(expect , result);
    }

    @Test(expected = Exceptions.CaptionLenghtException.class)
    public void Register_with_empty_Caption(){
        //Arrange
        String caption ="";

        boolean expect =false;

        //Act
        boolean result= selectCaptionHelper.register(caption);

        //Assert
        Assert.assertEquals(expect , result);
    }

}