package Assignment5;

public class Vertice {
    int distSrc = Integer.MAX_VALUE;
    Vertice predecessor;
    String name = "";    
    Boolean reachable=false;
    Vertice(String s){
        name = s;
    }

}
