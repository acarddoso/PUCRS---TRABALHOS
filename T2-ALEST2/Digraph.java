public class Digraph extends Grafo {

  public Digraph(String filename) {
    super(filename);
  }

  @Override
  public void adicionarAresta(String v, String w) {
    adicionarNaLista(v, w);
  }

  @Override
  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {"+QUEBRA_DE_LINHA);
    sb.append("rankdir = LR;"+QUEBRA_DE_LINHA);
    sb.append("node [shape = circle];"+QUEBRA_DE_LINHA);
    for(String v: obterVertices().stream().sorted().toList())
      for (String w: obterAdjacentes(v))
        sb.append(v + " -> " + w + QUEBRA_DE_LINHA);
    sb.append("}" + QUEBRA_DE_LINHA);
    return sb.toString();
  }
}
