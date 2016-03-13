/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa2;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bushc
 */
public class MultiStack<T> {
    
    private class Stack<T>{
        private DoublyLinkedListNode<T> Top;
        
        @Getter
        private int Size;
        
        public Stack(){
            Size = 0;
        }
        
        public void push(T value){
            DoublyLinkedListNode<T> node = new DoublyLinkedListNode<T>();
            node.setValue(value);
            Top.setPrevious(node);
            node.setNext(Top);
            Size++;
        }
        
        public T pop(){
            T value = Top.getValue();
            DoublyLinkedListNode<T> temp = Top;
            Top = Top.getNext();
            Top.setPrevious(null);
            temp = null;
            Size--;
            return value;
        }
        
        public Boolean IsEmpty(){
            return Size == 0;
        }
        
    }
    
    private class DoublyLinkedListNode<T>{
        @Getter @Setter
        private DoublyLinkedListNode<T> Previous;
        @Getter @Setter
        private DoublyLinkedListNode<T> Next;
        @Getter @Setter
        private T Value;
    }
    
    private Stack<T> A;
    private Stack<T> B;
    
    public MultiStack(){
        A = new Stack<T>();
        B = new Stack<T>();
    }
    
    public void PushA(T value){
        A.push(value);
    }
    
    public void PushB(T value){
        B.push(value);
    }
    
    public T[] MultiPopA(int k){
        int i = 0;
        final ArrayList<T> values = new ArrayList<T>();
        while(i++ < k){
            if(A.IsEmpty()) break;
            values.add(A.pop());
        }
        return (T[])values.toArray();
    }
    
    public T[] MultiPopB(int k){
        int i = 0;
        final ArrayList<T> values = new ArrayList<T>();
        while(i++ < k){
            if(B.IsEmpty()) break;
            values.add(B.pop());
        }
        return (T[])values.toArray();
    }
    
    public void Transfer(int k){
        int i = 0;
        while(i++ < k){
            if(A.IsEmpty()) break;
            B.push(A.pop());
        }
    }
}
