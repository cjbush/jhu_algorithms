/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

/**
 *
 * @author cjbus_000
 */
public class PartADFS implements IDFS {

    private int time = 0;
    
    private final Stack<Vertex> S = new Stack<>();
    
    @Override
    public void DFS(IGraph G) {
	for(Vertex u : G.GetAllVertices()){
	    u.setColor(Vertex.Color.WHITE);
	    u.setPi(null);
	}
	time = 0;
	for(Vertex u : G.GetAllVertices()){
	    if(u.getColor() == Vertex.Color.WHITE){
		DFS_VISIT(G, u);
		while(S.getSize() > 0){
		    Vertex t = S.Pop();
		    DFS_VISIT(G, t);
		}
	    }
	}
    }
    
    private void DFS_VISIT(IGraph G, Vertex u){
	u.setD(++time);
	u.setColor(Vertex.Color.GRAY);
	for(Vertex v : G.GetAdjacentVertices(u.getName())){
	    if(v.getColor() == Vertex.Color.WHITE){
		v.setPi(u);
		S.Push(v);
	    }
	}
	u.setColor(Vertex.Color.BLACK);
	u.setF(++time);
    }
    
}
