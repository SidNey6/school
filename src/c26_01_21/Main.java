package c26_01_21;

import _shared.BinarySearchTree;

public class Main {

    private BinarySearchTree<Datenpunkt> tree;

    public Main(){
        tree = new BinarySearchTree<Datenpunkt>();

        tree.insert(new Datenpunkt("tickende Uhr", 25));
        tree.insert(new Datenpunkt("ruhige Bibliothek", 40));
        tree.insert(new Datenpunkt("schreiendes Baby", 80));
    }

    public Datenpunkt search(int pLautstaerke){
        Datenpunkt temp = new Datenpunkt("", pLautstaerke);
        return tree.search(temp);
    }

    public static void main(String[] args){
        Main main = new Main();
        Datenpunkt dp = main.search(40);
        System.out.println("Gefundener Datenpunkt:" + dp.getName());
        }
    }