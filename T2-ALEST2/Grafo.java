import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Grafo {
  protected static final String QUEBRA_DE_LINHA = System.getProperty("line.separator");

  protected Map<String, List<String>> grafo;

  public Grafo() {
    grafo = new HashMap<>();
  }

  public Grafo(String nomeArquivo) {
    this();
    In in = new In(nomeArquivo);
    String linha;
    while ((linha = in.readLine()) != null) {
      String[] aresta = linha.split(" ");
      adicionarAresta(aresta[0], aresta[1]);
    }
    in.close();
  }

  public void adicionarAresta(String v, String w) {
    adicionarNaLista(v, w);
    adicionarNaLista(w, v);
  }

  public Iterable<String> obterAdjacentes(String v) {
    return grafo.get(v);
  }

  public Set<String> obterVertices() {
    return grafo.keySet();
  }

  public String toDot() {
    Set<String> arestas = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    sb.append("graph {" + QUEBRA_DE_LINHA);
    sb.append("rankdir = LR;" + QUEBRA_DE_LINHA);
    sb.append("node [shape = circle];" + QUEBRA_DE_LINHA);
    for (String v : obterVertices().stream().sorted().toList()) {
      for (String w : obterAdjacentes(v)) {
        String arestaAtual = v.compareTo(w) > 0 ? v + w : w + v;
        if (!arestas.contains(arestaAtual)) {
          sb.append(v + " -- " + w + QUEBRA_DE_LINHA);
          arestas.add(arestaAtual);
        }
      }
    }
    sb.append("}" + QUEBRA_DE_LINHA);
    return sb.toString();
  }

  protected List<String> adicionarNaLista(String v, String w) {
    List<String> lista = grafo.get(v);
    if (lista == null)
    lista = new LinkedList<>();
    lista.add(w);
    grafo.put(v, lista);
    return lista;
  }
}
