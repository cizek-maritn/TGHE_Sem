/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tgheSem;

/**
 *
 * @author Dell
 */
public class graphClass implements Comparable<graphClass>{
    private final String full;
    private final int num;
    
    public graphClass(String s, int n) {
        this.full=s;
        this.num=n;
    }

    public String getFull() {
        return full;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(graphClass o) {
        if (num>o.num) return 1;
        else if (num==o.num) return 0;
        else return -1;
    }
}
