/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lukas
 */
public class Lab4 {
    public ArrayList<ArrayList<Integer>> setGraph(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        String[] s;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        while (sc.hasNextLine()) {
            s = sc.nextLine().split(" ");
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < s.length; i ++) {
                arr.add(Integer.parseInt(s[i]));
            }
            graph.add(arr);
        }
        return graph;
    }
    public boolean findJob(ArrayList<ArrayList<Integer>> graph, boolean[] seen, int i, int[] m, int jobs, int apps) {
        for (int j = 0; j < jobs; j ++) {
            if (graph.get(i).get(j) == 1 && seen[j] ==  false){
                seen[j] = true;
                if (m[j] < 0 || findJob(graph, seen, m[j], m, jobs, apps)) {
                    m[j] = i;
                    return true;
                }
            }
        }
        return false;
    }
    // if job is not taken, assign job
    // if job is taken, check who has the job already if they have any alternatives and make sure they are not already visited
    // if a new job can be assigned to the one who has a job already then assign that job to them and x gets this job
    // if not, return false and move on.

    public int maxJobs(ArrayList<ArrayList<Integer>> graph) {
        int jobs = graph.get(0).size();
        int apps = graph.size();
        int[] m = new int[jobs];
        int r = 0;
        for (int i = 0; i < m.length; i ++) 
            m[i] = -1;
        for (int i = 0; i < apps; i ++) {
            boolean[] seen = new boolean[jobs];
            for(int j = 0; j < jobs; j ++ )
                seen[j] = false;
            if (findJob(graph, seen, i, m, jobs, apps))
                r++;
        }
        int j = 0;
        for (int i : m) {
            System.out.println(j +  ": " + i);
            j ++;
        }
        return r;

    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("C:\\Users\\Battl\\Desktop\\School\\Third Year\\gitCPS688\\CPS688\\lab4\\src\\lab4\\MBM.txt");
        Lab4 lab4 = new Lab4();
        ArrayList<ArrayList<Integer>> graph = lab4.setGraph(file);
        for (int i = 0; i < 6; i ++) {
            for (int j = 0; j < 6; j ++) {
                System.out.print(graph.get(i).get(j ) + " "); 
            }
            System.out.println();
        }
        System.out.println( "Maximum number of applicants that can get job is "+ lab4.maxJobs(graph));
    }
    
}
