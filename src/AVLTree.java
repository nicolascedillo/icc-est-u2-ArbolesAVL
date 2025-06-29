public class AVLTree {

  private Node root;

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
    System.out.println("Valor a insertar: " + value);
    root = insertRec(root, value);
  }

  private Node insertRec(Node nodoActual, int value) {
    if (nodoActual == null) {
      Node newNode = new Node(value);
      newNode.setHeight(1);
      System.out.println("Nodo insertado: " + newNode.getValue() + " Balance al insertar: " + getBalance(newNode));
      return newNode;
    }

    if (value < nodoActual.getValue()) {
      nodoActual.setIzquierda(insertRec(nodoActual.getIzquierda(), value));
    } else if (value > nodoActual.getValue()) {
      nodoActual.setDerecha(insertRec(nodoActual.getDerecha(), value));
    } else {
      return nodoActual;
    }

    int nuevaAltura = 1 + Math.max(height(nodoActual.getIzquierda()), height(nodoActual.getDerecha()));
    nodoActual.setHeight(nuevaAltura);

    int balance = getBalance(nodoActual);

    // Caso Izquierda Izquierda
    if (balance > 1 && value < nodoActual.getIzquierda().getValue()) {
      System.out.println("Rotación Derecha (Izquierda-Izquierda)");
      return rotarDerecha(nodoActual);
    }

    // Caso Derecha Derecha
    if (balance < -1 && value > nodoActual.getDerecha().getValue()) {
      System.out.println("Rotación Izquierda (Derecha-Derecha)");
      return rotarIzquierda(nodoActual);
    }

    // Caso Izquierda Derecha
    if (balance > 1 && value > nodoActual.getIzquierda().getValue()) {
      System.out.println("Rotación Izquierda-Derecha");
      nodoActual.setIzquierda(rotarIzquierda(nodoActual.getIzquierda()));
      return rotarDerecha(nodoActual);
    }

    // Caso Derecha Izquierda
    if (balance < -1 && value < nodoActual.getDerecha().getValue()) {
      System.out.println("Rotación Derecha-Izquierda");
      nodoActual.setDerecha(rotarDerecha(nodoActual.getDerecha()));
      return rotarIzquierda(nodoActual);
    }

    return nodoActual;
  }

  // Rotación simple a la derecha
  private Node rotarDerecha(Node nodoDesbalanceado) {
    Node nodoHijoIzquierdo = nodoDesbalanceado.getIzquierda();
    Node subarbolDerechoHijoIzquierdo = nodoHijoIzquierdo.getDerecha();

    // Realizar rotación
    nodoHijoIzquierdo.setDerecha(nodoDesbalanceado);
    nodoDesbalanceado.setIzquierda(subarbolDerechoHijoIzquierdo);

    // Actualizar alturas
    nodoDesbalanceado.setHeight(1 + Math.max(height(nodoDesbalanceado.getIzquierda()), height(nodoDesbalanceado.getDerecha())));
    nodoHijoIzquierdo.setHeight(1 + Math.max(height(nodoHijoIzquierdo.getIzquierda()), height(nodoHijoIzquierdo.getDerecha())));

    return nodoHijoIzquierdo;
  }

  // Rotación simple a la izquierda
  private Node rotarIzquierda(Node nodoDesbalanceado) {
    Node nodoHijoDerecho = nodoDesbalanceado.getDerecha();
    Node subarbolIzquierdoHijoDerecho = nodoHijoDerecho.getIzquierda();

    // Realizar rotación
    nodoHijoDerecho.setIzquierda(nodoDesbalanceado);
    nodoDesbalanceado.setDerecha(subarbolIzquierdoHijoDerecho);

    // Actualizar alturas
    nodoDesbalanceado.setHeight(1 + Math.max(height(nodoDesbalanceado.getIzquierda()), height(nodoDesbalanceado.getDerecha())));
    nodoHijoDerecho.setHeight(1 + Math.max(height(nodoHijoDerecho.getIzquierda()), height(nodoHijoDerecho.getDerecha())));

    return nodoHijoDerecho;
  }
}
