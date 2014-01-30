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
        assertEquals("Empty graph still has count for vertices", g.numVertices(),0);
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
        assertEquals("vertex number does not change", g.numVertices(),2);
        assertFalse("The related inbound edges were not removed", g.hasEdge("bar","foo"));
        Iterable adj = g.adjacentTo("bar");
        Iterator adjac = adj.iterator();
        assertFalse("vertex is still in adjacency list of another vertex",adjac.hasNext());
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
        assertTrue("there is no path to itself",g.hasPath("foo","foo"));
    }

    @Test
    public void pathLength()
    {
        g.addEdge("foo","bar");
        g.addEdge("bar","end");
        assertEquals("path length of one is incorrect", 1, g.pathLength("foo","bar"));
        assertEquals("path length larger than one is incorrect", g.pathLength("foo","end"),2);
    }

    @Test public void getPath()
    {
        g.addEdge("foo","bar");
        g.addEdge("bar","end");
        Iterable pathIterable = g.getPath("foo","end");
        List correctPath = new ArrayList();
        System.out.print(pathIterable.toString());
        correctPath.add("foo");
        correctPath.add("bar");
        correctPath.add("end");
        assertEquals("paths are not the same", pathIterable, correctPath);
    }

    @Test public void largeGraphTest()
    {
        g.addEdge("a","b");
        g.addEdge("b","c");
        g.addEdge("c","d");
        g.addEdge("d","e");
        g.addEdge("e","a");
        g.addEdge("b","b");
        g.addEdge("d","b");
        g.addEdge("a","e");
        assertTrue("there seems to be no path from to e", g.hasPath("a","e"));
        assertTrue("there seems to be no path to e", g.hasPath("e","a"));
        assertEquals("path length from a to e is incorrect", g.pathLength("a","e"),1);
        assertEquals("path length from e to a is incorrect", g.pathLength("e","a"),1);
        g.removeVertex("b");
        assertEquals("incorrect number of paths after deleting node b", g.numEdges(),4);
    }

    @Test public void selfPaths()
    {
        g.addEdge("a","b");
        g.addEdge("a","a");
        g.addEdge("b","b");
        g.addEdge("b","a");
        assertTrue("path to itself returns false",g.hasPath("a","a"));
        assertEquals("path length to itself returns greater than 0",g.pathLength("a","a"),0);
    }

    @Test public void correctShortestPath()
    {
        g.addEdge("a","b");
        g.addEdge("a","c");
        g.addEdge("b","c");
        Iterable attemptedShortestPath = g.getPath("a","c");
        Iterator asp = attemptedShortestPath.iterator();
        asp.next();
        String sp= asp.next().toString();
        assertEquals("it is not the correct shortest path", "c", sp);

    }

    @Test public void cycles(){
        g.addEdge("a","b");
        g.addEdge("b","a");
        g.addEdge("a","c");
        g.addEdge("c","a");
        g.addEdge("c","b");
        g.addEdge("b","c");
        g.addEdge("c","c");
        g.addEdge("a","a");
        g.addEdge("b","b");
        g.addEdge("b","d");
        assertEquals("length of path a-a is to long should be 0 ",0,g.pathLength("a","a"));
        assertEquals("length of path a-b is to long should be 1 ",1,g.pathLength("a","b"));
        assertEquals("length of self path should one a->a",0,g.pathLength("a","a"));
        assertEquals("length of self path should be zero d->d",0, g.pathLength("d","d"));

    }






}