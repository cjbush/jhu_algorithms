/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa2;

import java.lang.reflect.Array;
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
            
            DoublyLinkedListNode<T> tempTop = Top;
            Top = node;
            if(tempTop != null){
                Top.Next = tempTop;
                tempTop.Previous = Top;
            }          
            Size++;
        }
        
        public T pop(){
            if(Top == null) return null;
            T value = Top.getValue();
            DoublyLinkedListNode<T> temp = Top;
            Top = Top.getNext();
            if(Top != null) Top.setPrevious(null);
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
    
    private Class<T> clazz; //Java generic types suck.
    
    
    public MultiStack(Class<T> clazz){
        A = new Stack<T>();
        B = new Stack<T>();
        
        this.clazz = clazz;
    }
    
    public MultiStack<T> PushA(T value){
        A.push(value);
        return this;
    }
    
    public MultiStack<T> PushB(T value){
        B.push(value);
        return this;
    }
    
    public T[] MultiPopA(int k){
        int i = 0;
        T[] retval = (T[])Array.newInstance(clazz, Math.min(k, A.Size));
        while(i < k){
            if(A.IsEmpty()) break;
            retval[i++] = A.pop();
        }
        return retval;
    }
    
    public T[] MultiPopB(int k){
        int i = 0;
        T[] retval = (T[])Array.newInstance(clazz, Math.min(k, B.Size));
        while(i < k){
            if(B.IsEmpty()) break;
            retval[i++] = B.pop();
        }
        return retval;
    }
    
    public MultiStack<T> Transfer(int k){
        int i = 0;
        while(i++ < k){
            if(A.IsEmpty()) break;
            B.push(A.pop());
        }
        return this;
    }
    
    public int SizeA(){
        return A.getSize();
    }
    
    public int SizeB(){
        return B.getSize();
    }
    
    public boolean IsAEmpty(){
        return A.IsEmpty();
    }
    
    public boolean IsBEmpty(){
        return B.IsEmpty();
    }
    
    public MultiStack<T> ClearA(){
        A = new Stack<T>();
        return this;
    }
    
    public MultiStack<T> ClearB(){
        B = new Stack<T>();
        return this;
    }
    
    public MultiStack<T> ClearAll(){
        ClearA();
        ClearB();
        return this;
    }
}
