/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjbush.jhu.algorithms.pa2;

import cjbush.jhu.algorithms.pa2.BucketSorter.SortIntoBucketsLambda;
import java.util.Arrays;

/**
 *
 * @author bushc
 */
public class Main {
    public static void main(String[] args){
        
        //Test for bucket sort
        final Double[] unsorted = { 0.79d, 0.13d, 0.16d, 0.64d, 0.39d, 0.20d, 0.89d, 0.53d, 0.71d, 0.42d };
        final Double[] sortedExpected = { 0.13d, 0.16d, 0.20d, 0.39d, 0.42d, 0.53d, 0.64d, 0.71d, 0.79d, 0.89d };
        
        final ISorter<Double> sorter = new BucketSorter(Double.class, (SortIntoBucketsLambda<Double>) (int i, int n, Double[] A, List<Double>[] B) -> {
            B[(int)Math.floor(n * A[i])].add(A[i]);
        });
        final Double[] sortedActual = sorter.sort(unsorted);
        
        assert sortedExpected.length == sortedActual.length;
        assert !Arrays.equals(sortedActual, unsorted);
        assert Arrays.equals(sortedExpected, sortedActual);
        
        
        //Test for radix sort, if I'd implemented it
        /*final String[] unsortedRadix = { "COW", "DOG", "SEA", "RUG", "ROW", "MOB", "BOX", "TAB", "BAR", "EAR", "TAR", "DIG", "BIG", "TEA", "NOW", "FOX" };
        final String[] sortedExpectedRadix = { "BAR", "BIG", "BOX", "COW", "DIG", "DOG", "EAR", "FOX", "MOB", "NOW", "ROW", "RUG", "SEA", "TAB", "TAR", "TEA" };
        final ISorter<String> radix = new RadixSorter();
        final String[] sortedActualRadix = radix.sort(unsortedRadix);
        
        assert sortedExpectedRadix.length == sortedActualRadix.length;
        assert !Arrays.equals(sortedActualRadix, unsortedRadix);
        assert Arrays.equals(sortedExpectedRadix, sortedActualRadix);*/
        
        
        //Test the multistack
        final MultiStack<String> ms = new MultiStack(String.class);
        PopulateStack(ms);
        assert ms.SizeA() == 3;
        assert ms.SizeB() == 3;
        
        String[] value = ms.MultiPopA(2);
        assert value != null;
        assert value.length == 2;
        assert value[0].equals("a");
        assert value[1].equals("b");
        assert ms.SizeA() == 1;
        assert ms.SizeB() == 3;
        
        value = ms.MultiPopB(1);
        assert value != null;
        assert value.length == 1;
        assert value[0].equals("d");
        assert ms.SizeA() == 1;
        assert ms.SizeB() == 2;
        
        value = ms.MultiPopA(4);
        assert value != null;
        assert value.length == 1;
        assert value[0].equals("c");
        assert ms.SizeA() == 0;
        assert ms.IsAEmpty() == true;
        assert ms.SizeB() == 2;
        assert ms.IsBEmpty() == false;
        
        
        ms.ClearAll();
        assert ms.IsAEmpty() & ms.IsBEmpty();
        
        PopulateStack(ms);
        assert !ms.IsAEmpty() & !ms.IsBEmpty();
        
        ms.Transfer(3);
        assert ms.IsAEmpty();
        assert !ms.IsBEmpty();
        assert ms.SizeB() == 6;
        
        ms.ClearAll();
        assert ms.IsBEmpty() & ms.IsAEmpty();
        PopulateStack(ms);
        ms.Transfer(10);
        assert ms.IsAEmpty() & !ms.IsBEmpty();
        assert ms.SizeB() == 6;
        
        ms.ClearAll();
        assert ms.IsAEmpty() & ms.IsBEmpty();
        PopulateStack(ms);
        ms.Transfer(2);
        assert !ms.IsAEmpty() & !ms.IsBEmpty();
        assert ms.SizeA() == 1;
        assert ms.SizeB() == 5;       
    }
    
    private static final void PopulateStack(MultiStack stack){
        stack.PushA("c")
          .PushA("b")
          .PushA("a")
          .PushB("f")
          .PushB("e")
          .PushB("d");
    }
}
