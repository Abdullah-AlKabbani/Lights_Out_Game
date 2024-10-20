package com.mycompany.game_main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Set;
//import java.util.*;

/**
 *
 * @author this programe was med By Abdullah AL-Kabbani in 1/11/2023.
 */
public class Node {
    State parent;
    State body = new State();
    Set<State> child;

    public Node(){
        parent = body.parent;
        child = body.getNextState();
    }
}
