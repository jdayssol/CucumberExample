package com.jdayssol.training;

import org.junit.Assert;
import org.junit.Test;

public class SampleShould {

    @Test
    public void make_assertions() throws Exception  {
        Assert.assertTrue(true);
    }

    @Test(expected=Exception.class)
    public void throw_exception() throws Exception {
        throw new Exception();
    }

    @Test(timeout=1000)
    public void last_less_than_a_second() throws Exception {

    }

}
