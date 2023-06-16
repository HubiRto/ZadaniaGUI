package pl.pomoku.zadania12.zad2;

public record ShoppingItem(String name, int quantity) {
    @Override
    public String toString() {
        return name + " (" + quantity + ")";
    }
}