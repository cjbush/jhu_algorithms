/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa3;

import lombok.Data;
import lombok.Getter;

/**
 *
 * @author cjbus_000
 */
public class Stack<T> {
    
    @Data
    private class StackNode<T> {
	private T value;
	private StackNode<T> next;
	private StackNode<T> previous;
	
	public StackNode(T value){
	    this.value = value;
	}
    }
    
    private StackNode<T> top;
    
    @Getter private int size = 0;
    
    public void Push(T value){
	StackNode<T> node = new StackNode(value);
	if(top == null) top = node;
	else {
	    StackNode<T> temp = top;
	    top = node;
	    temp.setPrevious(node);
	    node.setNext(temp);
	}
	size++;
    }
    
    public T Pop(){
	T value = Peek();
	if(value == null) return null;
	top = top.next;
	if(top != null) {
	    top.previous = null;
	}
	size--;
	return value;
    }
    
    public T Peek(){
	if(top != null) return top.getValue();
	return null;
    }    
}
