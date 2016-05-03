/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

/**
 *
 * @author cjbus_000
 */
public class Main {
    
    public static void main(String[] args){
	Stack<Integer> stack = new Stack<Integer>();
	
	//Verify that I can actually write a stack
	stack.Push(1);
	stack.Push(2);
	stack.Push(3);
	stack.Push(4);
	
	assert stack.getSize() == 4;
	assert stack.Peek() == 4;
	assert stack.Pop() == 4;
	assert stack.Pop() == 3;
	assert stack.Pop() == 2;
	assert stack.Pop() == 1;
	assert stack.Pop() == null;
	assert stack.getSize() == 0;	
	//Oh good, I still know how to do that.
	
	
	//Now to test the actual stuff. 
	//Let's start with the textbook DFS implementation, to make
	//sure we haven't screwed anything up
	IGraph g = CreateUndirectedGraph();
	IDFS dfs = new TextBookDFS();
	dfs.DFS(g);
	for(Vertex v : g.GetAllVertices()){
	    assert v.getColor() == Vertex.Color.BLACK;
	}
	
	
	g = CreateDirectedGraph();
	dfs.DFS(g);
	ValidateDirectedGraph(g);	
	
	
	//Now we'll do the DFS implementation from part A
	dfs = new PartADFS();
	g = CreateDirectedGraph();
	dfs.DFS(g);
	ValidateDirectedGraph(g);
        
        dfs = new PartBDFS();
        g = CreateDirectedGraph();
        dfs.DFS(g);
    }
    
    private static IGraph CreateUndirectedGraph(){	
	//This is the graph from p590 of the text
	return new UndirectedGraph().AddVertex("1")
		.AddVertex("2")
		.AddVertex("3")
		.AddVertex("4")
		.AddVertex("5")
		.AddEdge("1", "5")
		.AddEdge("1", "2")
		.AddEdge("2", "5")
		.AddEdge("2", "3")
		.AddEdge("2", "4")
		.AddEdge("3", "4")
		.AddEdge("4", "5");
    }
    
    private static IGraph CreateDirectedGraph(){
	//This is the graph from p605 of the text
	return new DirectedGraph()
		.AddVertex("u")
		.AddVertex("v")
		.AddVertex("x")
		.AddVertex("y")
		.AddVertex("w")
		.AddVertex("z")
		.AddEdge("u", "v")
		.AddEdge("u", "x")
		.AddEdge("x", "v")
		.AddEdge("v", "y")
		.AddEdge("y", "x")
		.AddEdge("w", "z")
		.AddEdge("w", "z")
		.AddEdge("z", "z");
    }
    
    private static void ValidateDirectedGraph(IGraph g){
	for(Vertex v : g.GetAllVertices()){
	    assert v.getColor() == Vertex.Color.BLACK;
	}
    }
}
