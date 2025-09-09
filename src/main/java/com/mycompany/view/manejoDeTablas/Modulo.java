package com.mycompany.view.manejoDeTablas;

public class Modulo {
    public String l = "";
    public int u = -1;
    public String e = ""; 

    public Modulo(int u){
        this.u = u;
    }

    public Modulo(String e){
        this.e = e;
    }

    public String toString(){
        return this.l + " " + this.u + " " + this.e;
    }
}