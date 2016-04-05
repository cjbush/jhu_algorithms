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
public interface ISorter<T extends Comparable<? super T>> {
    T[] sort(T[] A);
}
