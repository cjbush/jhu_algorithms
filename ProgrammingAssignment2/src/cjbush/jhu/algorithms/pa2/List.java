/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa2;

import java.lang.reflect.Array;
import lombok.Data;

/**
 *
 * @author bushc
 */

    
    //Our own list implementation, because why not
public final class List<T> {

    @Data // <--- this just generates getters and setters. Thanks Lombok!
    private final class ListNode<T> {
        private ListNode next;
        private ListNode previous;
        private T value;
    }

    private ListNode<T> first;
    private ListNode<T> last;

    private int size = 0;

    public List(){}
    public List(T[] values){
        for(T d : values){
            add(d);
        }
    }

    public final void add(T d){
        ListNode node = new ListNode();
        node.setValue(d);
        if(first == null){
            first = node;
            last = first;
            size = 1;
        } else {
            last.setNext(node);
            node.setPrevious(last);
            last = node;
            size++;
        }
    }

    public final void add(List<T> l){
        ListNode<T> node = l.first;
        for(int i = 0; i < l.size; i++){
            this.add(node.value);
            node = node.next;
        }
    }

    public final T[] toArray(Class<T> clazz){
        ListNode<T> node = first;
        T[] retval;
        retval = (T[])Array.newInstance(clazz, size);
        for(int i = 0; i < size; i++){
            retval[i] = node.getValue();
            node = node.getNext();
        }
        return retval;
    }
}
