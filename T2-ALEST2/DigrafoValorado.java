public class DigrafoValorado extends GrafoValorado {

  public DigrafoValorado(String nomeArquivo) {
    super(nomeArquivo);
  }
  public DigrafoValorado() {
    super();
  }



  @Override
  public void adicionarAresta(String v, String w, int peso) {
    Aresta a = new Aresta(v, w, peso);
    adicionarNaLista(v, a);
    if (!vertices.contains(v)) {
      vertices.add(v);
      totalVertices++;
    }
    if (!vertices.contains(w)) {
      vertices.add(w);
      totalVertices++;
    }
    totalVertices += 2;
    totalArestas++;
  }

  @Override
  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("digrafo {" + QUEBRA_DE_LINHA);
    sb.append("rankdir = LR;" + QUEBRA_DE_LINHA);
    sb.append("nodo [forma = circulo];" + QUEBRA_DE_LINHA);
    for (Aresta a : getArestas())
      sb.append(String.format("%s -> %s [label=\"%d\"]", a.getV(), a.getW(), a.getPeso()) + QUEBRA_DE_LINHA);
    sb.append("}" + QUEBRA_DE_LINHA);
    return sb.toString();
  }
}
