package edu.union.adt.graph.tests.goodricn;
package edu.union.ad.graph.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.union.adt.graph.Graph;
import edu.union.adt.graph.GraphFactory;

@RunWith(JUnit4.class)
public class GoodrichGraphTests
{
	private Graph<String> g;

    @Before
    public void setUp()
    {
        g = GraphFactory.<String>createGraph();
    }

    @After
    public void tearDown()
    {
        g = null;
    }
    
    



}