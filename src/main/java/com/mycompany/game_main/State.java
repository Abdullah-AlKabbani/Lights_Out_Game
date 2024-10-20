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

public class State implements Comparable<State>{
        int n, l;
        char[][] p;
        int Count;
        int level_Count;
        State parent = null;
        double cost;
        int npnoods;
        int ngnoods;
        double h;
        double A;

    
        public State() {
            
            char[][] p2 = { 
                { 'G', 'G' }, 
                { 'G', 'R' } 
            };

            char[][] p3 = { 
                { 'G', 'G', 'R' }, 
                { 'G', 'R', 'G' }, 
                { 'R', 'G', 'G' } 
            };

            char[][] p4 = { 
                { 'G', 'G', 'R' , 'R' }, 
                { 'G', 'R', 'G' , 'G' }, 
                { 'R', 'G', 'G' , 'R' },
                { 'R', 'R', 'R' , 'G' },
            };

            char[][] test = { 
                {'G', 'G', 'R', 'G', 'G', 'G', 'G', 'G', 'G'}, 
                {'R', 'R', 'R', 'R', 'G', 'R', 'R', 'G', 'G'}, 
                {'R', 'R', 'G', 'G', 'R', 'R', 'G', 'R', 'R'},
                {'R', 'R', 'R', 'R', 'R', 'G', 'G', 'G', 'R'}, 
                {'R', 'G', 'G', 'G', 'G', 'G', 'G', 'R', 'G'}, 
                {'G', 'R', 'R', 'G', 'G', 'G', 'G', 'G', 'R'}, 
                {'G', 'G', 'R', 'R', 'R', 'G', 'G', 'R', 'R'},
                {'R', 'G', 'R', 'G', 'G', 'R', 'G', 'R', 'G'},
                {'G', 'R', 'R', 'R', 'G', 'R', 'R', 'G', 'R'}, 
             };

            // create_Level();
            // random_Level();
            p = p3;
            Count = 0;
            level_Count = 100;
            n = p.length;
            l = p[0].length;
            cost = 0;
            npnoods = 0;
            ngnoods = 0;

            //********************** we have 4 heuristic *************************************
            //h = heuristic();
            //h = manhattanDistanceHeuristic(p);
            //h = hammingDistanceHeuristic(p);
            h = maxHeuristic(p);

            A = cost + h;
        }

        public char Swich_State(char s) {
            if (s == 'G')
                return 'R';

            else if (s == 'R')
                return 'G';

            else
                return '#';
        }

        void click(int i, int j) {

            int row = i;
            int col = j;

            // change (i,j) position

            if (row >= 0 && row < n && col >= 0 && col < l && p[row][col] != '#') {
                p[row][col] = Swich_State(p[row][col]);
                Count++;

                // Check if cell above exists
                if (row - 1 >= 0 && row - 1 < n && col >= 0 && col < l && p[row - 1][col] != '#') {
                    p[row - 1][col] = Swich_State(p[row - 1][col]);
                }

                // Check if cell below exists
                if (row + 1 >= 0 && row + 1 < n && col >= 0 && col < l && p[row + 1][col] != '#') {
                    p[row + 1][col] = Swich_State(p[row + 1][col]);
                }

                // Check if cell to the left exists
                if (row >= 0 && row < n && col - 1 >= 0 && col - 1 < l && p[row][col - 1] != '#') {
                    p[row][col - 1] = Swich_State(p[row][col - 1]);
                }

                // Check if cell to the right exists
                if (row >= 0 && row < n && col + 1 >= 0 && col + 1 < l && p[row][col + 1] != '#') {
                    p[row][col + 1] = Swich_State(p[row][col + 1]);
                }
            }

            else {
                System.out.print("\nThis movement is forbidden \n\n");
            }

            // print Game ..
            //show();

        }

        public void show() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    if (p[i][j] == 'G') {
                        System.out.print("\u001B[38;2;255;165;0m|\u001B[0m" + "\u001B[32mG\u001B[0m");
                    } else if (p[i][j] == 'R') {
                        System.out.print("\u001B[38;2;255;165;0m|\u001B[0m" + "\u001B[31mR\u001B[0m");
                    } else {
                        System.out.print("\u001B[38;2;255;165;0m|\u001B[0m" + "\u001B[33m#\u001B[0m");
                    }
                }
                System.out.print("\u001B[38;2;255;165;0m|\u001B[0m" + "\n");
            }
            System.out.println("Cost =" + cost);
            System.out.println("heuristic =" + h);
            System.out.println("A* =" + A);
            System.out.println("Moves =" + Count + '\n');
        }

        public boolean Check_Lights() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    if (p[i][j] == 'G') {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean Check_Count() {
            if (Count < level_Count) {
                return true;
            } else {
                return false;
            }
        }

        public boolean Check() {
            if (Check_Count() && !Check_Lights()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean Win() {
            if (Count < level_Count && Check_Lights()) {
                return true;
            } else {
                return false;
            }
        }

        public void create_Level() {
            try (Scanner scanner = new Scanner(System.in)) {
                // Get the dimensions of the char array from the user
                System.out.print("Enter the number of rows: ");
                int n = scanner.nextInt();

                System.out.print("Enter the number of columns: ");
                int m = scanner.nextInt();
                System.out.print("Enter max number of steps for this level: ");
                int max = scanner.nextInt();

                char[][] user_level = new char[n][m];

                // Prompt the user to enter the values for each element of the array
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                        user_level[i][j] = scanner.next().charAt(0);
                    }
                }

                p = user_level;
                level_Count = max;
            }

            show();
        }

        public void random_Level() {
            try (Scanner scanner = new Scanner(System.in)) {
                // Get the dimensions of the char array from the user
                System.out.print("Enter the number of rows: ");
                int n = scanner.nextInt();
   
                System.out.print("Enter the number of columns: ");
                int m = scanner.nextInt();
   
                System.out.print("Enter max number of steps for this level: ");
                int max = scanner.nextInt();
   
                char[][] level_rand = new char[n][m];
   
                Random random = new Random();
   
                /*
                 * Fill the array with random boolean values
                 * for (int i = 0; i < n; i++) {
                 * for (int j = 0; j < m; j++) {
                 * arr[i][j] = random.nextBoolean();
                 * }
                 * }
                 */
   
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        level_rand[i][j] = (random.nextInt(2) == 0) ? 'G' : 'R';
                    }
                }
   
                p = level_rand;
                level_Count = max;
            }

            show();
        }
    
        State deepCopy() {
            State g1 = new State();
            g1.parent = this;
            g1.n = this.n;
            g1.l = this.l;
            g1.p = new char[n][l];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    g1.p[i][j] = this.p[i][j];
                }
            }
            g1.Count = this.Count;
            g1.level_Count = this.level_Count;
            return g1;
        }

        
        public int num_lights_off(State parent, State child){
            int count = 0;

             for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    if(parent.p[i][j] == 'G' && child.p[i][j] == 'R'){
                        count++;
                    }
                }
            }

            return count;
        }

        public Set<State> getNextState() {
            Set<State> s = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    State g1 = this.deepCopy();
                    g1.click(i, j);
                    g1.cost = this.num_lights_off(this, g1)+cost;
                    g1.h = g1.heuristic();
                    g1.A = g1.cost + g1.h;
                    s.add(g1);
                }
            }
            return s;
        }

        public double getCost(){
            return this.cost;
        }

        public double getHuristic(){
            return this.h;
        }

        public double getA(){
            return this.A;
        }


        public State dfsGame() {
            HashSet<State> visited = new HashSet<>();
            Stack<State> st = new Stack<>();
            st.push(this);
            show();
            ngnoods++;
            while (!st.isEmpty()) {
                if (!visited.contains(st.peek())) {
                    visited.add(st.peek());
                    State a = st.pop();
                    this.npnoods++;
                    st.addAll(a.getNextState());
                    ngnoods += a.getNextState().size();
                    if (a.Check_Lights()) {
                        return a;
                    }
                } else {
                    this.npnoods++;
                    st.pop();
                }
            }
            System.out.println("not found");
            return this;
        }

        public State bfsGame() {
            HashSet<State> visited = new HashSet<>();
            Queue<State> qs = new LinkedList<>();
            qs.add(this);
            ngnoods++;
            while (!qs.isEmpty()) {
                if (qs.peek().Check_Lights()) {
                    return qs.peek();
                }
                if (!visited.contains(qs.peek())) {
                    visited.add(qs.peek());
                    State a = qs.poll();
                    this.npnoods++;
                    qs.addAll(a.getNextState());
                    ngnoods += a.getNextState().size();
                } else {
                    this.npnoods++;
                    qs.poll();
                }
            }
            System.out.println("not found");
            return this;
        }

        public boolean state_same(State s1,State s2){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    if(s1.p[i][j] != s2.p[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }

        public State ucsGame() {
            HashSet<State> visited = new HashSet<>();
            PriorityQueue<State> st = new PriorityQueue<>(Comparator.comparingDouble(State :: getCost));
            st.add(this);
            ngnoods++;
            visited.add(this);
            while (!st.isEmpty()) {
                State a = st.poll();
                this.npnoods++;
                Set<State> ls = a.getNextState();
                ngnoods += a.getNextState().size();
                for (State g : ls) {
                    if (g.Check_Lights()) {
                        return g;
                    }

                    if (!visited.contains(g)) {
                        visited.add(g);
                        st.add(g);
                    }
                    else {
                        for(State k : st){
                            if(state_same(g, k)){
                                if(g.cost< k.cost){
                                    k = g;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("not found");
            return this;
        }

        public double heuristic(){
            double count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < l; j++) {
                    if(p[i][j] == 'G'){
                        count++;
                    }
                }
            }

            return count/3;
        }
        
        public double manhattanDistanceHeuristic(char[][] board) {
            double distance = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'G') {
                        distance += Math.abs(i - board.length / 2) + Math.abs(j - board[0].length / 2);
                    }
                }
            }
            return distance;
        }

        public double hammingDistanceHeuristic(char[][] board) {
            double distance = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'G') {
                        distance++;
                    }
                }
            }
            return distance;
        }

        public double maxHeuristic(char[][] board) {
            double max = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'G') {
                        int dist = Math.max(Math.abs(i - board.length / 2), Math.abs(j - board[0].length / 2));
                        if (dist > max) {
                            max = dist;
                        }
                    }
                }
            }
            return max;
        }
        

        
        
        public State hcGame() {
            HashSet<State> visited = new HashSet<>();
            PriorityQueue<State> st = new PriorityQueue<>(Comparator.comparingDouble(State :: getHuristic));
            st.add(this);
            ngnoods++;
            visited.add(this);
            while (!st.isEmpty()) {
                State a = st.poll();
                this.npnoods++;
                Set<State> ls = a.getNextState();
                ngnoods += a.getNextState().size();
                State bestState = null;
                int besthuristic = 99999;
                for (State g : ls) {
                    if (g.Check_Lights()) {
                        return g;
                    }
                    if (!visited.contains(g)) {
                        if(g.h<besthuristic){
                            bestState = g;
                        }
                    }
                }
                if(bestState != null){
                    st.add(bestState);
                    visited.add(bestState);
                }
            }
            System.out.println("not found");
            return this;
        }

        public State AStarGame() {
            HashSet<State> visited = new HashSet<>();
            PriorityQueue<State> st = new PriorityQueue<>(Comparator.comparingDouble(State :: getA));
            st.add(this);
            ngnoods++;
            visited.add(this);
            while (!st.isEmpty()) {
                State a = st.poll();
                this.npnoods++;
                Set<State> ls = a.getNextState();
                ngnoods += a.getNextState().size();
                for (State g : ls) {
                    if (g.Check_Lights()) {
                        return g;
                    }
                    if (!visited.contains(g)) {
                        visited.add(g);
                        st.add(g);
                    }
                    else {
                        for(State k : st){
                            if(state_same(g, k)){
                                if(g.A< k.A){
                                    k = g;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("not found");
            return this;
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
            System.out.println("num of generated noods:" + this.ngnoods);
            System.out.println("num of process noods:" + this.npnoods);
            System.out.println("deep of solation:" + --i);
    
        }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.n;
        hash = 23 * hash + this.l;
        hash = 23 * hash + Arrays.deepHashCode(this.p);
        hash = 23 * hash + this.Count;
        hash = 23 * hash + this.level_Count;
        hash = 23 * hash + Objects.hashCode(this.parent);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.cost) ^ (Double.doubleToLongBits(this.cost) >>> 32));
        hash = 23 * hash + this.npnoods;
        hash = 23 * hash + this.ngnoods;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.h) ^ (Double.doubleToLongBits(this.h) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.A) ^ (Double.doubleToLongBits(this.A) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (this.n != other.n) {
            return false;
        }
        if (this.l != other.l) {
            return false;
        }
        if (this.Count != other.Count) {
            return false;
        }
        if (this.level_Count != other.level_Count) {
            return false;
        }
        if (Double.doubleToLongBits(this.cost) != Double.doubleToLongBits(other.cost)) {
            return false;
        }
        if (this.npnoods != other.npnoods) {
            return false;
        }
        if (this.ngnoods != other.ngnoods) {
            return false;
        }
        if (Double.doubleToLongBits(this.h) != Double.doubleToLongBits(other.h)) {
            return false;
        }
        if (Double.doubleToLongBits(this.A) != Double.doubleToLongBits(other.A)) {
            return false;
        }
        if (!Arrays.deepEquals(this.p, other.p)) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(State s) {
        if (A > s.A) {
            return 1;
        }
        if (A < s.A) {
            return -1;
        }

        return 0;
    }

    /* 

    // cost
    public int compareTo(State s) {
        
        if (cost > s.cost) {
            return 1;
        }

        if (cost < s.cost) {
            return -1;
        }

        return 0;
    }

    // Huristic
    public int compareTo(State s) {
        
        if (h > s.h) {
            return 1;
        }

        if (h < s.h) {
            return -1;
        }

        return 0;
    }

    // A*
    public int compareTo(State s) {
        if (A > s.A) {
            return 1;
        }
        if (A < s.A) {
            return -1;
        }

        return 0;
    }

    */

}
