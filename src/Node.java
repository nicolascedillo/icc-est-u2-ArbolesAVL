public class Node {

  private Node izquierda;
  private Node derecha;
  private int value;
  private int height;

  public Node(int value){
    this.izquierda = null;
    this.derecha = null;
    this.value = value;
  }

  public Node getIzquierda() {
    return izquierda;
  }

  public void setIzquierda(Node izquierda) {
    this.izquierda = izquierda;
  }

  public Node getDerecha() {
    return derecha;
  }

  public void setDerecha(Node derecha) {
    this.derecha = derecha;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

    public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return "Node [value=" + value + "]";
  }

}
