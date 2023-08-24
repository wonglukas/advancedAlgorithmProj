package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Lukas
 */
public class Lab2 {
    public boolean isSafe (int board[][], int row, int col, int n) {
        for (int i = 0; i < col; i ++) 
            if (board[row][i] == 1)
                return false;
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
        for (int i = row, j = col; j >= 0 && i < n; i++, j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }
    public boolean solve (int board[][], int col, int n) {
        if (col >= n)
            return true;
        for (int i = 0; i < n; i ++) {
            if (isSafe(board, i, col, n)) {
                board[i][col] = 1;
                if (solve(board, col + 1, n) == true)
                    return true;
                board[i][col] = 0;
            }
        }
        return false;
    }
    public void nQueens (int n) {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                board[i][j] = 0; 
            }
        }
        if (solve(board, 0, n) == false) {
            System.out.println("Solution does not exist");
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        } 
        
    }
    
    public boolean checkLoop (List<List<Integer>> array, int n) {
        List<Integer> r = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        List<List<Integer>> arr = array;
        stack.push(0);
        int curr = 0;
        while (stack.isEmpty() == false) {
            curr = stack.pop();
            if (r.contains(curr) == true)
                return false;
            if (r.contains(curr) == false)
                r.add(curr);
            for (int i : arr.get(curr)) {
                if (r.contains(i) == false)
                    stack.add(i);
            }
        }
        return true;
    }
    public void checkAcyclic () throws FileNotFoundException{
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        File file = new File("C:\\Users\\Battl\\Desktop\\School So far\\VSCODE\\CPS688\\lab2\\src\\lab2\\acyclic.txt");
        Scanner sc = new Scanner(file);
        boolean first = true;
        int n = 0;
        while (sc.hasNextLine()) {
            String[] st = sc.nextLine().split(" ");
            int[] s = new int[2];
            s[0] = Integer.parseInt(st[0]);
            s[1] = Integer.parseInt(st[1]);
            if (first == true) {
                n = s[0];
                if (n == 1)
                first = false;
                for (int i = 0; i < n; i ++) {
                    List<Integer> ls = new ArrayList<>();
                    arr.add(ls);
                }
            } else {
                if (arr.get(s[0]) == null) {
                List<Integer> ls = new ArrayList<>();
                ls.add(s[1]);
                arr.set(s[0], ls);
                } else {
                    List<Integer> ls = arr.get(s[0]);
                    if (ls.contains(s[1]) == false) {
                        ls.add(s[1]);
                        arr.set(s[0], ls);
                    }
                }
                if (arr.get(s[1]) == null) {
                    List<Integer> ls = new ArrayList<>();
                    ls.add(s[0]);
                    arr.set(s[1], ls);
                } else {
                    List<Integer> ls = arr.get(s[1]);
                    if (ls.contains(s[0]) == false) {
                        ls.add(s[0]);
                        arr.set(s[1], ls);
                    }
                }
            }
        }
        System.out.print("Is the given graph acyclic?: ");
        if (checkLoop(arr, n))
            System.out.println("yes");
        else
            System.out.println("no");
    }
    
    public int min(int k[], Boolean mSet[], int v) {
        int min = Integer.MAX_VALUE;
        int minI = -1;

        for (int i = 0; i < v; i++)
            if (mSet[i] == false && k[i] < min) {
            min = k[i];
            minI = i;
        }
        return minI;
    }
    public void minSpan () throws FileNotFoundException{
        List<List<Integer>> arr = new ArrayList<List<Integer>>();
        File file = new File("C:\\Users\\Battl\\Desktop\\School So far\\VSCODE\\CPS688\\lab2\\src\\lab2\\minspan.txt");
        Scanner sc = new Scanner(file);
        boolean first = true;
        int v = 0, e = 0;
        int[] w = new int[3];
        int[][] graph = null;
        while (sc.hasNextLine()) {
            String[] st = sc.nextLine().split(" ");
            if (first == true) {
                first = false;
                v = Integer.parseInt(st[0]);
                e = Integer.parseInt(st[1]);
                graph = new int[v][v];
            } else {
                w[0] = Integer.parseInt(st[0]);
                w[1] = Integer.parseInt(st[1]);
                w[2] = Integer.parseInt(st[2]);
                graph[w[0]][w[1]] = w[2];
                graph[w[1]][w[0]] = w[2];
            } 
        }
        
        int[] p = new int[v];
        int[] k = new int[v];
        Boolean[] mSet = new Boolean[v];
        for (int i = 0; i < v; i ++) {
            k[i] = Integer.MAX_VALUE;
            mSet[i] = false;
        }
        k[0] = 0;
        p[0] = -1;
        for (int j = 0; j < v - 1; j++) {
            int m = min(k, mSet, v);
            mSet[m] = true;
            for (int i = 0; i < v; i++)
                if (graph[m][i] != 0 && mSet[i] == false && graph[m][i] < k[i]) {
                    p[i] = m;
                    k[i] = graph[m][i];
                }
        }
        int counter = 0;
        for (int i = 1; i < v; i++){
            counter += graph[i][p[i]];
            System.out.println("Edge "+ p[i] + "-" + i + " has a weight of " + graph[i][p[i]]);
        }
        System.out.println("MST = "+ counter);
    }
    
    public int maxValue(int a, int b) {
        if (a > b)
            return a;
        return b;
    }
    public int bag (int w, int[] candy, int[] weight, int n) {
        if (n == 0 || w == 0)
            return 0;
        if (weight[n - 1] > w)
            return bag (w, candy, weight, n - 1);
        else 
            return maxValue(candy[n - 1] + bag(w - weight[n - 1], candy, weight, n - 1), bag(w, candy, weight, n - 1));
    }  
    public int candyBag () throws FileNotFoundException{
        File file = new File("C:\\Users\\Battl\\Desktop\\School So far\\VSCODE\\CPS688\\lab2\\src\\lab2\\candy.txt");
        Scanner sc = new Scanner(file);
        int[] candy = new int[0], weight = new int[0];
        int b = 0;
        for (int i = 0; i < 4; i ++) {
            if (i == 0) {
                candy = new int[Integer.parseInt(sc.nextLine())];
                weight = new int[candy.length];
            } else if (i == 1) {
                String[] st = sc.nextLine().split(" ");
                for (int j = 0; j < st.length; j ++) {
                    candy[j] = Integer.parseInt(st[j]);
                }
            } else if (i == 2) {
                String[] st = sc.nextLine().split(" ");
                for (int j = 0; j < st.length; j ++) {
                    weight[j] = Integer.parseInt(st[j]);
                }
            } else if (i == 3) {
                b = Integer.parseInt(sc.nextLine());
            }
        }
        return bag(b, candy, weight, candy.length);
    }
    
    public void LIS () throws FileNotFoundException{
        File file = new File("C:\\Users\\Battl\\Desktop\\School So far\\VSCODE\\CPS688\\lab2\\src\\lab2\\LIS.txt");
        Scanner sc = new Scanner(file);
        String[] st = sc.nextLine().split(" ");
        int[] l = new int[st.length];
        int[] arr = new int[l.length];
        ArrayList<Integer> ret = new ArrayList<>();
        int r = 0;
        for (int i = 0; i < st.length; i ++)  {
            l[i] = Integer.parseInt(st[i]);
            arr[i] = 1;
        }
        for (int i = 1; i < l.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (l[i] > l[j] && arr[i] < arr[j] + 1) {
                    arr[i] = arr[j] + 1;
                }
            }
        }
        for (int i = 0; i < l.length; i++) {
            if (r < arr[i]) {
                ret.add(l[i]);
                r = arr[i];
            }
        }
        System.out.println("LIS = " + r);
        System.out.print("LIS is: " + ret.toString());
    }
    public static void main(String[] args) throws FileNotFoundException{
        Lab2 q = new Lab2();
        System.out.print("N Queens Problem: ");
        Scanner sc = new Scanner(System.in);
        q.nQueens(Integer.parseInt(sc.nextLine()));

        Lab2 ac = new Lab2();
        System.out.println("Acyclic Problem: from text file");
        ac.checkAcyclic();
        
        Lab2 mst = new Lab2();
        System.out.println("Minimum Spanning Tree: from text file");
        mst.minSpan();
        
        Lab2 c = new Lab2();
        System.out.println("Candy Problem: from text file");
        System.out.println("Max sentimental: " + c.candyBag());
        
        Lab2 l = new Lab2();
        System.out.println("LIS Problem: from text file");
        l.LIS();
    }
    
}
