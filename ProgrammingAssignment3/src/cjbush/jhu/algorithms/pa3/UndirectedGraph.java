/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

/**
 *
 * @author cjbus_000
 */
public class UndirectedGraph extends AbstractGraph {        

    @Override
    public IGraph AddEdge(String src, String dest) {
	Edge e = new Edge(lookup.get(src), lookup.get(dest), false);
	edges.add(e);
	return this;
    }
    
}
