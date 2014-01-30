package edu.union.adt.graph;
import java.util.*;

/**
 * A graph that establishes connections (edges) between objects of
 * (parameterized) type V (vertices).  The edges are directed.  An
 * undirected edge between u and v can be simulated by two edges: (u,
 * v) and (v, u).
 *
 * The API is based on one from
 *     http://introcs.cs.princeton.edu/java/home/
 *
 * Some method names have been changed, and the Graph type is
 * parameterized with a vertex type V instead of assuming String
 * vertices.
 *
 * @author Aaron G. Cass
 * @version 1
 */
public class HashGraph<V> implements Graph<V>
{

    private Map < V, List<V>> edges;

    /**
     * Create an empty graph.
     */
    public HashGraph() 
    {
         edges = new HashMap <V, List<V>>();
    }

    /**
     * @return the number of vertices in the graph.
     */
    public int numVertices()
    {
        return edges.size();
    }

    /**
     * @return the number of edges in the graph.
     */
    public int numEdges()
    {
        //Iterator edgesList edges.values().iterator();
        if(edges.isEmpty()){
            return 0;
        }
        else{
        boolean exit = false;
        Iterator vertices = edges.keySet().iterator();
        V nextKey;
        int count = 0;
        while(!exit){
            nextKey = (V)vertices.next();
            count += degree(nextKey); 
            if(vertices.hasNext() == false){exit = true;}
        }
        return count;}
    }
    

    /**
     * Gets the number of vertices connected by edges from a given
     * vertex.  If the given vertex is not in the graph, throws a
     * RuntimeException.
     *
     * @param vertex the vertex whose degree we want.
     * @return the degree of vertex 'vertex'
     */
    public int degree(V vertex)
    {
        if(edges.isEmpty()){
            throw new RuntimeException("originalException");
        }
        else{
        return edges.get(vertex).size();
    }
    }
    

    /**
     * Adds a directed edge between two vertices.  If there is already an edge
     * between the given vertices, does nothing.  If either (or both)
     * of the given vertices does not exist, it is added to the
     * graph before the edge is created between them.
     *
     * @param from the source vertex for the added edge
     * @param to the destination vertex for the added edge
     */
    public void addEdge(V from, V to)
    {
        if(!edges.containsKey(from)){
            addVertex(from);
        }
        if(!edges.containsKey(to)){
            addVertex(to);
        }
        if(!edges.get(from).contains(to)){
        edges.get(from).add(to);
        }
        

        }

    /**
     * Adds a vertex to the graph.  If the vertex already exists in
     * the graph, does nothing.  If the vertex does not exist, it is
     * added to the graph, with no edges connected to it.
     *
     * @param vertex the vertex to add
     */
    public void addVertex(V vertex)
    {
       if(edges.containsKey(vertex)) return;
        else
        {
        edges.put(vertex, new ArrayList<V>());
       }
    }

    /**
     * @return the an iterable collection for the set of vertices of
     * the graph.
     */
    public Iterable<V> getVertices()
    {
        return edges.keySet();
    }

    /**
     * Gets the vertices adjacent to a given vertex.  A vertex y is
     * "adjacent to" vertex x if there is an edge (x, y) in the graph.
     * Because edges are directed, if (x, y) is an edge but (y, x) is
     * not an edge, we would say that y is adjacent to x but that x is
     * NOT adjacent to y.
     *
     * @param from the source vertex
     * @return an iterable collection for the set of vertices that are
     * the destinations of edges for which 'from' is the source
     * vertex.  If 'from' is not a vertex in the graph, returns an
     * empty iterator.
     */
    public Iterable<V> adjacentTo(V from)
    {   
        return edges.get(from);
    }

    /**
     * Tells whether or not a vertex is in the graph.
     *
     * @param vertex a vertex
     * @return true iff 'vertex' is a vertex in the graph.
     */
    public boolean contains(V vertex)
    {
        return edges.containsKey(vertex);
    }

    /**
     * Tells whether an edge exists in the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     *
     * @return true iff there is an edge from the source vertex to the
     * destination vertex in the graph.  If either of the given
     * vertices are not vertices in the graph, then there is no edge
     * between them.
     */
    public boolean hasEdge(V from, V to)
    {
        return edges.containsKey(from) && edges.get(from).contains(to);
    }


    /** 
    *@param a graph
    *A graph is equal to another graph if and only if they contain the same verticies
    *as well as the same edges
    *@return returns true if and only if the two graphs are the same
    */
    public boolean equals(Object graphObject){

       HashGraph graphTest = (HashGraph)graphObject;
       return this.edges.equals(graphTest.edges);


    }

    /**
     * Gives a string representation of the graph.  The representation
     * is a series of lines, one for each vertex in the graph.  On
     * each line, the vertex is shown followed by ": " and then
     * followed by a list of the vertices adjacent to that vertex.  In
     * this list of vertices, the vertices are separated by ", ".  For
     * example, for a graph with String vertices "A", "B", and "C", we
     * might have the following string representation:
     *
     * <PRE>
     * A: A, B
     * B:
     * C: A, B
     * </PRE>
     *
     * This representation would indicate that the following edges are
     * in the graph: (A, A), (A, B), (C, A), (C, B) and that B has no
     * adjacent vertices.
     *
     * Note: there are no extraneous spaces in the output.  So, if we
     * replace each space with '*', the above representation would be:
     *
     * <PRE>
     * A:*A,*B
     * B:
     * C:*A,*B
     * </PRE>
     *
     * @return the string representation of the graph
     */
    public String toString()
    {
        
        String output = "";

        for(V v: edges.keySet()){
           output += v + ":";
           //if(edges.get(v).contains(v)){ 
            for(V k: edges.get(v)){
             output += edges.get(k);
        // }
     }
            output += "\n";


        }
        output = output.replace("[","");
        output = output.replace("]","");
        return output;
    }
    

//ALL NEW API



    /**
     * Tells whether the graph is empty.
     *
     * @return true iff the graph is empty. A graph is empty if it has
     * no vertices and no edges.
     */
    public boolean isEmpty(){
        return edges.isEmpty() && edges.values().isEmpty();
    }

    /**
     * Removes and vertex from the graph.  Also removes any edges
     * connecting from the edge or to the edge.
     *
     * <p>Postconditions:
     *
     * <p>If toRemove was in the graph:
     * <ul>
     * <li>numVertices = numVertices' - 1
     * <li>toRemove is no longer a vertex in the graph
     * <li>for all vertices v: toRemove is not in adjacentTo(v)
     * </ul>
     *
     * @param toRemove the vertex to remove.
     */
    public void removeVertex(V toRemove){
        if(this.contains(toRemove)){
            Iterable<V> adjacentVerts = this.adjacentTo(toRemove);
            for( V v : edges.keySet()){
                    edges.get(v).remove(toRemove);
                }
            }
        edges.remove(toRemove);
        }


    /**
     * Removes an edge from the graph.
     *
     * <p>Postcondition: If from and to were in the graph and (from,
     * to) was an edge in the graph, then numEdges = numEdges' - 1
     */
    public void removeEdge(V from, V to){
        if(edges.containsKey(from) && edges.containsKey(to)){
        edges.get(from).remove(to);
        edges.get(to).remove(from);
    }

    }

    /**
     * Tells whether there is a path connecting two given vertices.  A
     * path exists from vertex A to vertex B iff there exists a
     * sequence x_1, x_2, ..., x_n where:
     *
     * <ul>
     * <li>x_1 = A
     * <li>x_n = B
     * <li>for all i from 1 to n-1, (x_i, x_{i+1}) is an edge in the graph.
     * </ul>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return true iff there is a path from 'from' to 'to' in the graph.
     */
    public boolean hasPath(V from, V to){
        Queue<V> unchecked = new PriorityQueue<V>();
        if(from.equals(to)){return true;}
        else if(!this.contains(from) && !this.contains(to)){return false;}
        else{
            unchecked.add(from);
            while(!unchecked.isEmpty()){
                if(unchecked.peek().equals(to)){return true;}
                else{
                    Iterable<V> vertexList = this.adjacentTo(unchecked.poll());
                    for(V v : vertexList){
                        unchecked.add(v);
                        if(v.equals(to)){return true;}
                    }
                }
            }
            return false;

        }



    }

    /**
     * Gets the length of the shortest path connecting two given
     * vertices.  The length of a path is the number of edges in the
     * graph.
     *
     * <ol> 
     * <li>If from = to, shortest path has length 0
     * <li>Otherwise, shortest path length is length of the shortest
     * possible path connecting from to to.  
     * </ol>
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return the length of the shortest path from 'from' to 'to' in
     * the graph.  If there is no path, returns Integer.MAX_VALUE  return Integer.MAX_VALUE;
     */
    public int pathLength(V from, V to){
        if(from.equals(to)){
            return 0;
        }
        Iterable<V> path = this.getPath(from,to);
        int count=0;
        for(V v: path){
            count++;
        }
        count-=1;
        return count;
    }



    /**
     * Returns the vertices along the shortest path connecting two
     * given vertices.  The vertices should be given in the order x_1,
     * x_2, x_3, ..., x_n, where:
     *
     * <ol>
     * <li>x_1 = from
     * <li>x_n = to
     * <li>for all i from 1 to n-1: (x_i, x_{i+1}) is an edge in the graph.
     * </ol>
     * 
     * @param from the source vertex
     * @param to the destination vertex
     * @return an Iterable collection of vertices along the shortest
     * path from 'from' to 'to'.  The Iterable should include the
     * source and destination vertices.
     */
    public Iterable<V> getPath(V from, V to){
        List<V> path = new ArrayList<V>();
        if(!this.contains(from) || !this.contains(to)){
            return path;
        }
        else if(from.equals(to)){
            path.add(from);
            return path;
        }
        Queue<V> unchecked = new PriorityQueue<V>();
        Map<V,V> checked = new HashMap<V,V>();
        unchecked.add(from);
        boolean exit = true;
        checked.put(from,null);
        while(exit && !unchecked.isEmpty()){

            for(V v : this.adjacentTo(unchecked.peek())){
                if(!checked.keySet().contains(v)){
                    for(V z : this.adjacentTo(unchecked.peek())){checked.put(z,unchecked.peek());}
                    if(!unchecked.peek().equals(to)){
                        unchecked.poll();
                        unchecked.add(v);
                    }
            }


            }
        if(checked.keySet().contains(to)){
                exit = false;
            }

            
        }
    
        
        V node = to;
        while(!node.equals(from)){
            path.add(node);
            node = checked.get(node);
        }
        path.add(from);
        Collections.reverse(path);
        return path;


         
    }









}