package edu.union.adt.graph.tests.goodricn;

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
import java.util.*;

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
    
    @Test
    public void isEmpty()
    {
    	assertTrue("Empty graph returns not empty",g.isEmpty());
    	g.addVertex("foo");
    	assertFalse("not empty graph returns empty",g.isEmpty());
        g.removeVertex("foo");
        assertTrue("Empty graph returns not empty after removal",g.isEmpty());
    }

    @Test 
    public void removeVertex()
    {
        g.addVertex("foo");
        g.addEdge("foo","bar");
        g.addEdge("zee","foo");
        g.removeVertex("foo");
        assertFalse("The added vertex was not removed", g.contains("foo"));
        assertFalse("The related outbound edges were not removed", g.hasEdge("foo","bar"));
        assertEquals("vertex number does not change", g.numVertices(),1);
        assertEquals("The related inbound edges were not removed", g.hasEdge("bar","foo"));
        assertFalse("vertex is still in adjacency list of another vertex", g.adjacentTo("bar"));
    }

    @Test
    public void removeEdge(){
        g.addEdge("foo","bar");
        g.removeEdge("foo","bar");
        assertFalse("removed edge is still there", g.hasEdge("foo","bar"));
        assertEquals("num edges is incorrect",g.numEdges(),0);
    }

    @Test
    public void hasPath()
    {
        g.addEdge("foo","bar");
        g.addEdge("bar","end");
        assertTrue("there is no path from initial to final", g.hasPath("foo","end"));
        assertFalse("path is one way", g.hasPath("end","foo"));
        g.addEdge("foo","foo");
        assertTrue("there is no path to itself",g.haspath("foo","foo"));
    }

    @Test
    public void pathLength()
    {
        g.addEdge("foo","bar");
        g.addEdge("bar","end");
        assertEquals("path length of one is incorrect", g.pathLength("foo","bar"),1);
        assertEquals("path length larger than one is incorrect", g.pathLength("foo","end"),2);
    }

    @Test public void getPath()
    {
        g.addEdge("foo","bar");
        g.addEdge("bar","end");
        Iterable pathIterable = g.getPath("foo","end");
        List correctPath = new ArrayList();
        correctPath.add("foo");
        correctPath.add("bar");
        correctPath.add("end");
        assertEquals("paths are not the same", pathIterable,correctPath);


    }




}