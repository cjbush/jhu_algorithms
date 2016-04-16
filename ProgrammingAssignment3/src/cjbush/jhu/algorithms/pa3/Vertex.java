/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author cjbus_000
 */
@Data
public class Vertex {
    
    public enum Color {
	BLACK,
	GRAY,
	WHITE
    }
    
    private String name;
    private List<Vertex> connectedVertices;
    
    private int d;
    private Vertex pi;
    private int f;
    private Color color;
    

    public Vertex(String name) {
	this.name = name;	    
	this.connectedVertices = new ArrayList<>();
    }
}
