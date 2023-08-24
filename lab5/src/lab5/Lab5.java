/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.util.Scanner;

/**
 *
 * @author Lukas
 */
public class Lab5 {
    static int chars = 256;
    static int N;
    static int M;
    static Scanner sc = new Scanner (System.in);
    static String text;
    static String pattern;

    static long patHash;
    static long Q = 2147483647;
    static int R = 256;
    static long RM = 1;

    public static long hash(String key, int M) {
        long h = 0;
        for (int i = 0; i < M; i ++) {
            h = (R * h + key.charAt(i)) % Q;
        }
        return h;
    }
    public static String RKsearch (String txt, String pat) {
        for (int i = 1; i <= M - 1; i ++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
        int N = txt.length();
        long txtHash = hash(txt, M);
        String s = "";
        if (patHash == txtHash) {
            s = s + " " + 0;
        }
        for (int i = M; i < N; i ++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash) {
                if (checkFunc(i - M + 1, txt, pat) == true) {
                    s = s + " " + (i - M + 1);
                }
            }
        } 
        return s;
    }

    public static boolean checkFunc (int num, String txt, String pat) {
        for (int i = 0; i < M; i++)  {
            if (txt.charAt(i + num) != pat.charAt(i))
                return false;
        }
        return true;
    }

    public static String BMsearch (String txt, String pat) {
        int N = txt.length();
        int M = pat.length();
        int skip = 0;
        int[] right = new int[256];
        String s = "";
        for (int c = 0; c < 256; c ++) {
            right[c] = -1;
        }
        for (int j = 0; j < M; j ++) {
            right[pat.charAt(j)] = j;
        }
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j --) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                s = s + " " + i;
                i += M;
            }
        }
        return s;
    }
    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter Text: ");
            text = sc.nextLine();
            System.out.print("Enter Pattern: ");
            pattern = sc.nextLine();
            N = text.length();
            M = pattern.length();
            System.out.println("\nBoyer-Moore");
            System.out.println("Pattern " + pattern + " found at index:" + BMsearch(text, pattern));
            System.out.println("\nRabin-Karp");
            System.out.println("Pattern " + pattern + " found at index:" + RKsearch(text, pattern));
        }
    }
}
