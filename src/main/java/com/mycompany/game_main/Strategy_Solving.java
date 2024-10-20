package com.mycompany.game_main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author this programe was med By Abdullah AL-Kabbani in 1/11/2023.
 */

import java.util.*;

public class Strategy_Solving {
    int choice;

    public Strategy_Solving(){
        choice = 1;
    }

    public void solution(State g) {
        Stack<State> ls = new Stack<>();
        int i = 0;
        while (g != null) {
            ls.add(g);
            i += 1;
            g = g.parent;
        }
        while (!ls.isEmpty()) {
            ls.pop().show();
        }
        System.out.println("num of generated noods:" + g.ngnoods);
        System.out.println("num of process noods:" + g.npnoods);
        System.out.println("deep of solation:" + --i);

    }
    
    public void play_user() {
        Scanner input = new Scanner(System.in);
        State G = new State();
        System.out.print("\nTry to solve this buzzle ... \n\n");
        G.show();

        while (G.Check()) {
            System.out.print("\nEnter Your Next Move ... \n");
            System.out.print("Enter i:");
            int i = input.nextInt();
            System.out.print("Enter j:");
            int j = input.nextInt();
            G.click(i, j);
            G.show();
        }
        if (!G.Check_Lights()) {
            System.out.print("\n\t \u001B[38;2;255;165;0m*** Game Over ***\u001B[0m \n\n");
        } else {
            System.out.print("\n\t \u001B[38;2;255;165;0m*** You Win ***\u001B[0m \n\n");
        }
       System.out.println("\033[0;33m==================================================================\u001B[0m");
    }

    public void play_DFS() {
        double stdfs = System.nanoTime();
        System.out.println("֍֍֍֍ DFS algorithm ֍֍֍֍");
        State g = new State();
        State dfs = g.dfsGame();
        g.solution(dfs);
        double etdfs = System.nanoTime();
        double timedfs = etdfs - stdfs;
        System.out.println("֍֍֍֍ Time of run ֍֍֍֍\n" + timedfs / 1000000 + " mile second");
        System.out.println("\033[0;33m==================================================================\u001B[0m");
    }

    public void play_BFS() {
        double stbfs = System.nanoTime();
        System.out.println("֍֍֍֍ BFS algorithm ֍֍֍֍");
        State g2 = new State();
        State bfs = g2.bfsGame();
        g2.solution(bfs);
        double etbfs = System.nanoTime();
        double timebfs = etbfs - stbfs;
        System.out.println("\n֍֍֍֍ Time of run ֍֍֍֍\n" + timebfs / 1000000 + " mile second");
        System.out.println("\033[0;33m==================================================================\u001B[0m");
    }

    public void play_UCS() {
        double stucs = System.nanoTime();
        System.out.println("֍֍֍֍ UCS algorithm ֍֍֍֍");
        State g3 = new State();
        State ucs = g3.ucsGame();
        g3.solution(ucs);
        double etucs = System.nanoTime();
        double timeucs = etucs - stucs;
        System.out.println("\n֍֍֍֍ Time of run ֍֍֍֍\n" + timeucs / 1000000 + " mile second");
        System.out.println("\033[0;33m==================================================================\u001B[0m");
    }


    public void play_HC() {
        double sthc = System.nanoTime();
        System.out.println("֍֍֍֍ HC algorithm ֍֍֍֍");
        State g4 = new State();
        State hc = g4.hcGame();
        g4.solution(hc);
        double ethc = System.nanoTime();
        double timehc = ethc - sthc;
        System.out.println("֍֍֍֍ Time of run ֍֍֍֍\n" + timehc / 1000000 + " mile second");
        System.out.println("\033[0;33m==================================================================\u001B[0m");
    }

 
    public void play_A_Star() {
        double stastar = System.nanoTime();
        System.out.println("֍֍֍֍ A* algorithm ֍֍֍֍");
        State g5 = new State();
        State aStar = g5.AStarGame();
        g5.solution(aStar);
        double etastar = System.nanoTime();
        double timeastar = etastar - stastar;
        System.out.println("֍֍֍֍ Time of run ֍֍֍֍\n" + timeastar / 1000000 + " mile second");
        System.out.println("\033[0;33m==================================================================\u001B[0m");
    }




    public void start_game(){
        
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\t \u001B[35m *** Welcome to our AAK_Lights Game *** \u001B[0m \n\n");
            System.out.print("Please select your choice frome below ... \n");
            System.out.print("to paly user press 1, \n");
            System.out.print("to paly DFS press 2, \n");
            System.out.print("to paly BFS press 3, \n");
            System.out.print("to paly UCS press 4, \n");
            System.out.print("to paly HC press 5, \n");
            System.out.print("to paly A* press 6, \n");
            System.out.print("to Exit press 0, \n");
            choice = input.nextInt();

            if (choice == 1) {
                this.play_user();
            }

            else if (choice == 2) {
                this.play_DFS();
            }

            else if (choice == 3) {
                this.play_BFS();
            }

            else if (choice == 4) {
                this.play_UCS();
            }

            else if (choice == 5) {
                this.play_HC();
            }

            else if (choice == 6) {
                this.play_A_Star();
            }

            else {
                continue;
            }
        } while (choice != 0);
        System.out.print("\n\t \u001B[35m*** Thanks For Playing Our Game ***\u001B[0m \n\n");
        System.out.println("\033[0;33m==================================================================\u001B[0m \n");
    }
}
