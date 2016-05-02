/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

/**
 *
 * @author bushc
 */
public class PartBDFS implements IDFS {

    private int time = 0;
    
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
            }
        }
    }
    
    private void DFS_VISIT(IGraph G, Vertex u){
        u.setD(++time);
        u.setColor(Vertex.Color.GRAY);
        for(Vertex v : G.GetAdjacentVertices(u.getName())){
            if(v.getColor() == Vertex.Color.WHITE){
                System.out.printf("%s-%s is a tree edge\n", v.getName(), u.getName());
                v.setPi(u);
                DFS_VISIT(G, v);
            } else if(v.getColor() == Vertex.Color.GRAY){
                System.out.printf("%s-%s is a back edge\n", v.getName(), u.getName());
            } else{
                System.out.printf("%s-%s is either a forward edge or a cross edge\n", v.getName(), u.getName());
            }
        }
        u.setColor(Vertex.Color.BLACK);
        u.setF(++time);
    }
    
}
