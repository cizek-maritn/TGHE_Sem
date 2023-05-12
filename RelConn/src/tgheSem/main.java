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
        //kolik výsledků chceme
        int cycles=Integer.parseInt(sc.nextLine());
        //algoritmus jde od konce do startu
        for (int i=0;i<cycles;i++) {
            input=sc.nextLine().split("\\s+");
            //start a konec algoritmu
            int start=Integer.parseInt(input[0]);
            int end=Integer.parseInt(input[1]);
            //sance na konekci, prakticky distance
            float[] probabilities = new float[stations];
            probabilities[end]=1;
            //stromová struktura nejlepších spojení od koncové stanice
            int[] tree = new int[stations];
            tree[end]=-1;
            //body, které již byly prolezeny. 0 = neprolezen, 1 = prolezen
            int[] tested = new int[stations];
            int next=end;
            for (int j=0;j<stations;j++) {
                //urceni dalsiho bodu pro prolezeni
                for (int k=0; k<stations;k++) {
                    if (tested[k]==0 && probabilities[k]>0) {
                        next=k; break;
                    }
                }
                //prolezení daného bodu
                for (int k=0;k<(newLines[k+1]-newLines[k]);k++) {
                    int currentPoint = (int) neighbors[newLines[next]+k][0];
                    float currentProb = neighbors[newLines[next]+k][1]*probabilities[next];
                    if (probabilities[currentPoint]<currentProb) {
                        probabilities[currentPoint]=currentProb;
                        tree[currentPoint]=next;
                    }
                }
                tested[next]=1;
            }
            int output=start;
            String s = "" + start;
            while (tree[output]!=-1) {
                s+=" " + tree[output];
                output=tree[output];
            }
            System.out.println(s);
        }
    }
    
}
