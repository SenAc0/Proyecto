/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Okrim
 */
public class Pizarra implements Serializable {
    private ArrayList<Ovalo> lista;
    private ArrayList<Rectangulo> lista2;
    private ArrayList<Linea> lista3;
    private ArrayList<Texto> lista4;
    private ArrayList<Ovalo> lista5;
    private ArrayList<Flecha> lista6;
    private ArrayList<Goma> lista7;
    public Pizarra(){
        lista =  new ArrayList<>();
        lista2 =  new ArrayList<>();
        lista3 =  new ArrayList<>();
        lista4 =  new ArrayList<>();
        lista5 =  new ArrayList<>();
        lista6 =  new ArrayList<>();
        lista7 = new ArrayList<>();
    }
    public ArrayList<Ovalo> getLista() {
        return lista;
    }
    public ArrayList<Rectangulo> getLista2() {
        return lista2;
    }
    public Rectangulo getRect(){
        return lista2.get(0);
    }
    public ArrayList<Linea> getLista3() {
        return lista3;
    }
    public ArrayList<Texto> getLista4() {
        return lista4;
    }
    public ArrayList<Ovalo> getLista5() {
        return lista5;
    }

    public ArrayList<Flecha> getLista6() {
        return lista6;
    }
    public ArrayList<Goma> getLista7(){
        return lista7;
    }
}
