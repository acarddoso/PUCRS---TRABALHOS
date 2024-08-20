public class Aresta implements Comparable<Aresta>
{
  private String v;
  private String w;
  private int peso;

  public Aresta(String v, String w, int peso) {
    this.v = v;
    this.w = w;
    this.peso = peso;
  }

  public int getPeso() { return peso; }

  public String getV() { return v; }

  public String getW() { return w; }

  @Override
  public int compareTo(Aresta a) {
    return Double.compare(this.peso, a.peso);
  }

  @Override
  public String toString() {
      return v + "-" + w + "(" + peso + ")";
  }
}
