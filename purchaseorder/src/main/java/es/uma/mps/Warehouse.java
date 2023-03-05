package es.uma.mps;

public interface Warehouse {
    boolean thereAreProducts(String name, int amount) ;
    void remove(String name, int amount) ;
}
