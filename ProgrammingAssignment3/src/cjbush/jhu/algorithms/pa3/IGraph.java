/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

import java.util.List;

/**
 *
 * @author cjbus_000
 */
public interface IGraph {
    
    IGraph AddVertex(String v);
    IGraph AddEdge(String src, String dest);
    
    List<Vertex> GetAdjacentVertices(String v);
    List<Vertex> GetAllVertices();
    Vertex GetVertex(String name);
        
}
