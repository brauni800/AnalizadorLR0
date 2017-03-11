/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.util.Stack;

/**
 *
 * @author b1796
 */
public class LR0 {
    private void desplazar(String s, Stack p){
        String a;
        if(p.empty()){
            a = Character.toString(s.charAt(0));
            p.push(a);
        }
        else{
            a = Character.toString(s.charAt(0)) + (String)p.pop();
            p.push(a);
        }
    }
    
    private void reducir(String s, String c, Stack p){
        String remp = ((String)p.pop()).replaceFirst(s, c);
        p.push(remp);
    }
    
    private String reacomodarCadena(String s){
        int a = s.length();
        String b = s;
        for(int i = 1; i < a; i++){
            if(s.equals(b))
                s = "";
            s += Character.toString(b.charAt(i));
        }
        return s;
    }
    
    public static void main(String[] args) {
        String palabra = "aabb", aux;
        String[][] reglas = {{"S", "Aa"}, {"A", "bS"}, {"A", "b"}};
        Stack pila = new Stack();
        LR0 lr = new LR0();
        boolean band = true;
        
        do{
            if(pila.empty()){
                lr.desplazar(palabra, pila);
                palabra = lr.reacomodarCadena(palabra);
            }
            else{
                for(int i = 0; i < reglas.length; i++){
                    aux = (String)pila.peek();
                    int resultado = aux.indexOf(reglas[i][1]);
                    if(resultado != -1){
                        lr.reducir(reglas[i][1], reglas[i][0], pila);
                        band = true;
                        break;
                    } else
                        band = false;
                }
                if(!band){
                    lr.desplazar(palabra, pila);
                    palabra = lr.reacomodarCadena(palabra);
                }
            }
            System.out.println(pila.peek());
        }while(!((String)pila.peek()).equals(reglas[0][0]));
        System.out.println("La cadena SI es válida");
    }
}
