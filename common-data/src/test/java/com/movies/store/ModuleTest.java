package com.movies.store;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ModuleTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ModuleTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ModuleTest.class );
    }

    /**
     * Rigourous Test 
     */
    public void testApp()
    {
        assertTrue( true );
    }
}