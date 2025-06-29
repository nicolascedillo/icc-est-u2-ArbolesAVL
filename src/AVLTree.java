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

  private Node insertRec(Node node, int value) {
    if (node == null) {
      Node newNode = new Node(value);
      newNode.setHeight(1);
      System.out.println("Nodo insertado: " + newNode.getValue() + " Balance al insertar: " + getBalance(newNode));
      return newNode;

    }

    if (value < node.getValue()) {
      node.setIzquierda(insertRec(node.getIzquierda(), value));
    } else if (value > node.getValue()) {
      node.setDerecha(insertRec(node.getDerecha(), value));
    } else {
      return node;
    }

    System.out.println("Node actual: " + node.getValue());

    // Actualizar la altura de este ancestro node

    int altura = 1 + Math.max(height(node.getIzquierda()), height(node.getDerecha()));

    node.setHeight(altura);
    System.out.println("\tAltura = " + node.getHeight());

    int balance = getBalance(node);
    System.out.println("\tBalance = " + balance);

    // Caso Izquierda Izquierda
    if (balance > 1 && value < node.getIzquierda().getValue()) {
      System.out.println("Rotacion Derecha");
      return rotateRight(node);
    }

    // Caso Derecha Derecha
    if (balance < -1 && value > node.getDerecha().getValue()) {
      System.out.println("Rotacion Izquierda");
      return rotateLeft(node);
    }

    // Caso Izquierda Derecha
    if (balance > 1 && value > node.getIzquierda().getValue()) {
      System.out.println("Cambio");
      System.out.println("Rotacion Derecha");
      node.setIzquierda(rotateLeft(node.getIzquierda()));
      return rotateRight(node);
    }

    // Caso Derecha Izquierda
    if (balance < -1 && value < node.getDerecha().getValue()) {
      System.out.println("Cambio");
      System.out.println("Rotacion Izquierda");
      node.setDerecha(rotateRight(node.getDerecha()));
      return rotateLeft(node);
    }

    return node;
  }

private Node rotateRight(Node y) {
    Node x = y.getIzquierda();
    Node T2 = x.getDerecha();

    x.setDerecha(y);
    y.setIzquierda(T2);

    y.setHeight(1 + Math.max(height(y.getIzquierda()), height(y.getDerecha())));
    x.setHeight(1 + Math.max(height(x.getIzquierda()), height(x.getDerecha())));

    return x;
}

private Node rotateLeft(Node x) {
    Node y = x.getDerecha();
    Node T2 = y.getIzquierda();

    y.setIzquierda(x);
    x.setDerecha(T2);

    x.setHeight(1 + Math.max(height(x.getIzquierda()), height(x.getDerecha())));
    y.setHeight(1 + Math.max(height(y.getIzquierda()), height(y.getDerecha())));

    return y;
}

}
