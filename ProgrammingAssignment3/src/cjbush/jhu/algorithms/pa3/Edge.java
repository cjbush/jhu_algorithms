/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

import lombok.Data;

/**
 *
 * @author cjbus_000
 */
@Data
public class Edge {
    private Vertex source;
    private Vertex destination;
    
    public Edge(Vertex source, Vertex destination, boolean directed){
	this.source = source;
	this.destination = destination;
	this.source.getConnectedVertices().add(destination);
	if(!directed){
	    this.destination.getConnectedVertices().add(source);
	}
    }
}
