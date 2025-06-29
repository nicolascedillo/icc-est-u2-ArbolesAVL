import java.util.ArrayList;
import java.util.List;

public class AVLTree {

  private Node root;
  private List<Integer> ordenDeInsercion = new ArrayList<>();

  public AVLTree() {
    this.root = null;
  }

  public int height(Node node) {
    if (node == null) {
      return 0;
    }
    return node.getHeight();
  }

  public int getBalance(Node node) {
    if (node == null) {
      return 0;
    }
    return height(node.getIzquierda()) - height(node.getDerecha());
  }

  public void insert(int value) {
    System.out.println("Insertando valor: " + value);
    ordenDeInsercion.add(value);
    root = insertRec(root, value);
    System.out.println("Inserción completa de: " + value + "\n");
  }

  private Node insertRec(Node nodoActual, int value) {
    if (nodoActual == null) {
      Node newNode = new Node(value);
      newNode.setHeight(1);
      System.out.println("Nodo creado con valor: " + newNode.getValue());
      return newNode;
    }

    if (value < nodoActual.getValue()) {
      System.out.println("Ir a la izquierda de " + nodoActual.getValue());
      nodoActual.setIzquierda(insertRec(nodoActual.getIzquierda(), value));
    } else if (value > nodoActual.getValue()) {
      System.out.println("Ir a la derecha de " + nodoActual.getValue());
      nodoActual.setDerecha(insertRec(nodoActual.getDerecha(), value));
    } else {
      System.out.println("Valor duplicado no insertado: " + value);
      return nodoActual;
    }

    int nuevaAltura = 1 + Math.max(height(nodoActual.getIzquierda()), height(nodoActual.getDerecha()));
    nodoActual.setHeight(nuevaAltura);
    System.out.println("Altura actualizada de " + nodoActual.getValue() + ": " + nuevaAltura);

    int balance = getBalance(nodoActual);
    System.out.println("Balance de " + nodoActual.getValue() + ": " + balance);

    // Caso Izquierda Izquierda
    if (balance > 1 && value < nodoActual.getIzquierda().getValue()) {
      System.out.println("Rotación Derecha (Izquierda-Izquierda) en " + nodoActual.getValue());
      return rotarDerecha(nodoActual);
    }

    // Caso Derecha Derecha
    if (balance < -1 && value > nodoActual.getDerecha().getValue()) {
      System.out.println("Rotación Izquierda (Derecha-Derecha) en " + nodoActual.getValue());
      return rotarIzquierda(nodoActual);
    }

    // Caso Izquierda Derecha
    if (balance > 1 && value > nodoActual.getIzquierda().getValue()) {
      System.out.println("Rotación Izquierda-Derecha en " + nodoActual.getValue());
      nodoActual.setIzquierda(rotarIzquierda(nodoActual.getIzquierda()));
      return rotarDerecha(nodoActual);
    }

    // Caso Derecha Izquierda
    if (balance < -1 && value < nodoActual.getDerecha().getValue()) {
      System.out.println("Rotación Derecha-Izquierda en " + nodoActual.getValue());
      nodoActual.setDerecha(rotarDerecha(nodoActual.getDerecha()));
      return rotarIzquierda(nodoActual);
    }

    return nodoActual;
  }

  private Node rotarDerecha(Node nodoDesbalanceado) {
    System.out.println("Ejecutando rotación derecha en " + nodoDesbalanceado.getValue());

    Node nodoHijoIzquierdo = nodoDesbalanceado.getIzquierda();
    Node subarbolDerechoHijoIzquierdo = nodoHijoIzquierdo.getDerecha();

    nodoHijoIzquierdo.setDerecha(nodoDesbalanceado);
    nodoDesbalanceado.setIzquierda(subarbolDerechoHijoIzquierdo);

    nodoDesbalanceado.setHeight(1 + Math.max(height(nodoDesbalanceado.getIzquierda()), height(nodoDesbalanceado.getDerecha())));
    nodoHijoIzquierdo.setHeight(1 + Math.max(height(nodoHijoIzquierdo.getIzquierda()), height(nodoHijoIzquierdo.getDerecha())));

    System.out.println("Rotación derecha completada. Nueva raíz del subárbol: " + nodoHijoIzquierdo.getValue());
    return nodoHijoIzquierdo;
  }

  private Node rotarIzquierda(Node nodoDesbalanceado) {
    System.out.println("Ejecutando rotación izquierda en " + nodoDesbalanceado.getValue());

    Node nodoHijoDerecho = nodoDesbalanceado.getDerecha();
    Node subarbolIzquierdoHijoDerecho = nodoHijoDerecho.getIzquierda();

    nodoHijoDerecho.setIzquierda(nodoDesbalanceado);
    nodoDesbalanceado.setDerecha(subarbolIzquierdoHijoDerecho);

    nodoDesbalanceado.setHeight(1 + Math.max(height(nodoDesbalanceado.getIzquierda()), height(nodoDesbalanceado.getDerecha())));
    nodoHijoDerecho.setHeight(1 + Math.max(height(nodoHijoDerecho.getIzquierda()), height(nodoHijoDerecho.getDerecha())));

    System.out.println("Rotación izquierda completada. Nueva raíz del subárbol: " + nodoHijoDerecho.getValue());
    return nodoHijoDerecho;
  }

  public void inOrder() {
    System.out.println("Recorrido inorden del árbol:");
    inOrderRec(root);
    System.out.println();
  }

  private void inOrderRec(Node nodo) {
    if (nodo != null) {
      inOrderRec(nodo.getIzquierda());
      System.out.print(nodo.getValue() + " ");
      inOrderRec(nodo.getDerecha());
    }
  }

  public void input() {
    System.out.println("Valores en orden de entrada:");
    for (int valor : ordenDeInsercion) {
      System.out.print(valor + " ");
    }
    System.out.println();
  }
}
