/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa2;

/**
 *
 * @author bushc
 */
public class BucketSorter<T extends Comparable<? super T>> implements ISorter<T> {
    
    public interface SortIntoBucketsLambda<T extends Comparable<? super T>> {
        void sortIntoBuckets(final int i, final int n, final T[] A, final List<T>[] B);
    }
    
    private SortIntoBucketsLambda lambda;
    private Class<T> clazz;
    
    public BucketSorter(Class<T> clazz, SortIntoBucketsLambda lambda){
        this.lambda = lambda;
        this.clazz = clazz;
    }

    @Override
    public T[] sort(T[] A) {
        final int n = A.length;
        final List<T>[] B = new List[n];
        final List<T> sorted = new List();
        for(int i = 0; i < n; i++){
            B[i] = new List();
        }
        for(int i = 0; i < n; i++){
            lambda.sortIntoBuckets(i, n, A, B);
        }
        for(int i = 0; i < n; i++){
            T[] unsortedBucket = B[i].toArray(clazz);
            T[] sortedBucket = InsertionSort(unsortedBucket);
            B[i] = new List(sortedBucket);
        }
        for(List bucket : B){
            sorted.add(bucket);
        }  
        return sorted.toArray(clazz);
    }
    
    private static <K extends Comparable<? super K>>  K[] InsertionSort(K[] A){
        for(int j = 1; j < A.length; j++){
            K key = A[j];
            int i = j;
            while(--i >= 0 && A[i].compareTo(key) > 0){
                A[i+1]=A[i];
            }
            A[i+1] = key;
        }
        return A;
    }
    
}


