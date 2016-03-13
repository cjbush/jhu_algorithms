/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa2;

import java.util.ArrayList;

/**
 *
 * @author bushc
 */
public class BucketSorter<T> implements ISorter<T> {

    @Override
    public T[] sort(T[] A) {
        final int n = A.length;
        final ArrayList<T>[] B = (ArrayList<T>[])new Object[n - 1];
        for(int i = 0; i < n; i++){
            B[i] = new ArrayList<T>();
        }
        for(int i = 1; i <= n; i++){
            B[Math.floor()].add(A[i]);
        }
        
        return A;
    }
    
}
