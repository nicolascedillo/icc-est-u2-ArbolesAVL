public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Nombres: Nicolas Cedillo - Mateo Miller");

        
        AVLTree aT = new AVLTree();

        aT.insert(10);
        aT.insert(20);
        aT.insert(15);

        aT.input();
        aT.inOrder();

    }
}