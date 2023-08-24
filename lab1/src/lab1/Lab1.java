/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;
import java.util.*;
/**
 *
 * @author Lukas
 */
public class Lab1 {
    static List<List<Integer>> arr = new ArrayList<List<Integer>>();
    static int nodes = 0;
    public static void addEdge (int a, int b) {
        if (arr.get(a) == null) {
            List<Integer> ls = new ArrayList<>();
            ls.add(b);
            arr.set(a, ls);
        }
        else {
            List<Integer> ls = arr.get(a);
            if (ls.contains(b) == false) {
                ls.add(b);
                arr.set(a, ls);
            }
        }
        
        if (arr.get(b) == null) {
            List<Integer> ls = new ArrayList<>();
            ls.add(a);
            arr.set(b, ls);
        }
        else {
            List<Integer> ls = arr.get(b);
            if (ls.contains(a) == false) {
                ls.add(a);
                arr.set(b, ls);
            }
        }
    }
    public static int degreeVertex (int a) {
        List<Integer> ls = new ArrayList<Integer>();
        ls = arr.get(a);
        return ls.size();
    }
    public static void printAdjVertices (int a) {
        System.out.print(Integer.toString(a) + ": ");
        for (int i : arr.get(a)) {
            System.out.print(Integer.toString(i) + " ");
        }
        System.out.println("");
    }
    public static List<Integer> BFS () {
        List<Integer> r = new ArrayList<Integer>();
        System.out.print("BFS: ");
        for (int i = 0; i < nodes; i ++) {
            List<Integer> lst = new ArrayList<Integer>();
            lst = arr.get(i);
            if (r.contains(i) != true)
                    r.add(i);
            for (int j : lst) {
                if (r.contains(j) != true)
                    r.add(j);
            }
        }
        return r;
        
    }
    public static List<Integer> DFS () {
        List<Integer> r = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        System.out.print("DFS: ");
        stack.push(0);
        int curr = 0;
        while (stack.isEmpty() == false) {
            curr = stack.pop();
            System.out.println(curr);
            if (r.contains(curr) == false)
                r.add(curr);
            for (int i : arr.get(curr)) {
                if (r.contains(i) == false)
                    stack.add(i);
            }
        }
        return r;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 6; i ++) {
            List<Integer> ls = new ArrayList<>();
            arr.add(ls);
        }
        nodes = 6;
        addEdge(0, 1);
        addEdge(0, 3);
        addEdge(1, 2);
        addEdge(2, 4);
        addEdge(3, 4);
        addEdge(3, 5);
        printAdjVertices(0);
        printAdjVertices(1);
        printAdjVertices(2);
        printAdjVertices(3);
        printAdjVertices(4);
        printAdjVertices(5);
        System.out.println(BFS());
        System.out.println(DFS());
    }
    
}
