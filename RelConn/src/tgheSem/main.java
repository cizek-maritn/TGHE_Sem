/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tgheSem;

import java.util.ArrayList;
import java.util.Comparator;
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
        double[][] neighbors=new double[connections*2][2];
        //parsování spojení
        List<graphClass> list=new ArrayList<graphClass>();
        for(int i=0;i<connections*2;i+=2) {
            input=sc.nextLine().split("\\s+");
            list.add(new graphClass((input[0]+" "+input[1]+" "+input[2]),Integer.parseInt(input[0])));
            list.add(new graphClass((input[1]+" "+input[0]+" "+input[2]),Integer.parseInt(input[1])));
        }
        list.sort(new graphComp());
        for (int i=0;i<connections*2;i++) {
            input=list.get(i).getFull().split("\\s+");
            int point=(int) Double.parseDouble(input[0])+1;
            newLines[point]+=1;
            neighbors[i][0]=Integer.parseInt(input[1]);
            neighbors[i][1]=Double.parseDouble(input[2]);
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
            double[] probabilities = new double[stations];
            probabilities[start]=1;
            //stromová struktura nejlepších spojení od koncové stanice
            int[] tree = new int[stations];
            tree[start]=-1;
            //body, které již byly prolezeny. 0 = neprolezen, 1 = prolezen
            int[] tested = new int[stations];
            int next=start;
            for (int j=0;j<stations;j++) {
                //urceni dalsiho bodu pro prolezeni
                double probVar=0;
                for (int k=0; k<stations;k++) {
                    if (tested[k]==0 && probabilities[k]>0) {
                        probVar=probabilities[k];
                        if (tested[next]==1) {
                            next=k;
                        } else if (probVar>probabilities[next]) {
                            next = k;
                        }
                    }
                }
                //prolezení daného bodu
                for (int k=0;k<(newLines[next+1]-newLines[next]);k++) {
                    int currentPoint = (int) neighbors[newLines[next]+k][0];                    
                    double currentProb = neighbors[newLines[next]+k][1]*probabilities[next];
                    if (probabilities[currentPoint]<currentProb) {
                        probabilities[currentPoint]=currentProb;
                        tree[currentPoint]=next;
                    }
                }
                tested[next]=1;
            }
            int output=end;
            String s = "" + end;
            while (tree[output]!=-1) {
                s+=" " + tree[output];
                output=tree[output];
            }
            String[] f = s.split("\\s");
            for (int j=0;j<f.length;j++) {
                System.out.print(f[f.length-j-1]);
                if (j!=f.length-1) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            
        }
    }
    private static class graphComp implements Comparator<graphClass> {
        @Override
        public int compare(graphClass o1, graphClass o2) {
            if (o1.getNum()>o2.getNum()) return 1;
            else if (o1.getNum()==o2.getNum()) return 0;
            else return -1;
        }
        
    }
}
