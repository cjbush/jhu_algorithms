/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cjbus_000
 */
public abstract class AbstractGraph implements IGraph {
    protected HashMap<String, Vertex> lookup = new HashMap<>();
    protected List<Edge> edges = new ArrayList<>();
    
    @Override
    public IGraph AddVertex(String name) {
	Vertex v = new Vertex(name);
	lookup.put(name, v);
	return this;
    }

    @Override
    public List<Vertex> GetAdjacentVertices(String name) {
	Vertex v = lookup.get(name);
	return v.getConnectedVertices();
    }

    @Override
    public List<Vertex> GetAllVertices() {
	return new ArrayList<>(lookup.values());
    }
    
    @Override
    public Vertex GetVertex(String name){
	return lookup.get(name);
    }
}
