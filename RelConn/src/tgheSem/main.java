/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tgheSem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class main {

    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        //stations je počet stanic, connections je počet spojení
        String[] input=sc.nextLine().split("\\s+");
        int stations=Integer.parseInt(input[0]);
        int connections=Integer.parseInt(input[1]);
        System.out.println(stations+" "+connections);
        //newLines je pole začátků řádků, neighbors je pole sousedů a hodnocení hran
        int[] newLines=new int[stations+1];
        float[][] neighbors=new float[connections*2][2];
        //parsování spojení
        List<String> list=new ArrayList<String>();
        for(int i=0;i<connections*2;i+=2) {
            input=sc.nextLine().split("\\s+");
            list.add(input[0]+" "+input[1]+" "+input[2]);
            list.add(input[1]+" "+input[0]+" "+input[2]);
        }
        Collections.sort(list);
        for (int i=0;i<connections*2;i++) {
            input=list.get(i).split("\\s+");
            newLines[Integer.parseInt(input[0])+1]++;
            neighbors[i][0]=Integer.parseInt(input[1]);
            neighbors[i][1]=Float.parseFloat(input[2]);
        }
        for (int i=1;i<stations+1;i++) {
            newLines[i]=newLines[i]+newLines[i-1];
        }
        for (int i=0;i<connections*2;i++) {
            System.out.println(neighbors[i][0]+" "+neighbors[i][1]);
        }
        //kolik výsledků chceme
        int cycles=Integer.parseInt(sc.nextLine());
        for (int i=0;i<cycles;i++) {
            input=sc.nextLine().split("\\s+");
            int start=Integer.parseInt(input[0]);
            int end=Integer.parseInt(input[1]);
            System.out.println(start+" "+end);
        }
    }
    
}
