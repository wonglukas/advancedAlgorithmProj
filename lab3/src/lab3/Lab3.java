/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Lukas
 */
public class Lab3 {

    static int cutRod (int[] price, int n) throws FileNotFoundException {
        int[] r = new int[n + 1];
        int q = 0;
        r[0] = 0;
        for (int j = 1; j <= n; j ++) {
            q = Integer.MIN_VALUE;
            for (int i = 0; i < j; i ++) {
                q = Math.max(q, price[i] + r[j - i - 1]);
            }
            r[j] = q;
        }
        return r[n];
    } 
    
    public static boolean DFS (LinkedList<Integer> adjList[], int V) {
        List<Integer> r = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        int curr = 0;
        while (stack.isEmpty() == false) {
            curr = stack.pop();
            if (r.contains(curr) == false)
                r.add(curr);
            for (int i : adjList[curr]) {
                if (r.contains(i) == false)
                    stack.add(i);
            }
        }
        if (r.size() == V)
            return true;
        return false;
    }
    static LinkedList<Integer> [] tranposeGraph(LinkedList<Integer> adjList[], int V) {
        LinkedList<Integer> l[] = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            Iterator<Integer> it = adjList[i].listIterator();
            while(it.hasNext())
                l[it.next()].add(i);
        }
        return l;
    }
    static boolean SCC(LinkedList<Integer> adjList[], int V) {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i ++) 
            visited[i] = false;
        boolean dfs = DFS(adjList, V);
        if (dfs == false)
            return false;
        return DFS(tranposeGraph(adjList, V), V);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file1 = new File("C:\\Users\\Battl\\Desktop\\School\\Third Year\\gitCPS688\\CPS688\\lab3\\src\\lab3\\cutRod.txt");
        Scanner sc1 = new Scanner(file1);
        int n = sc1.nextInt();
        int[] p =  new int[n];
        for (int i = 0; i < n; i ++) {
            p[i] = sc1.nextInt();
        }
        System.out.println("Maximum value obtainable: " + cutRod(p, n));

        File file2 = new File("C:\\Users\\Battl\\Desktop\\School\\Third Year\\gitCPS688\\CPS688\\lab3\\src\\lab3\\SCC.txt");
        Scanner sc2 = new Scanner(file2);
        int a = sc2.nextInt();
        int c = sc2.nextInt();
        LinkedList<Integer> adjList[] = new LinkedList[a];
        for (int i = 0; i < a; i ++) {
            adjList[i] = new LinkedList();
        }
        while (sc2.hasNext()) {
            adjList[sc2.nextInt()].add(sc2.nextInt());
        }
        System.out.println("Can all the antennas communicate with each other? " + SCC(adjList, a));
    }
    
}
