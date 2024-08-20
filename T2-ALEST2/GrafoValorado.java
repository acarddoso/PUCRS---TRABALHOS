import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoValorado {
    protected static final String QUEBRA_DE_LINHA = System.getProperty("line.separator");

    protected Map<String, List<Aresta>> grafo;
    protected Set<String> vertices;
    private Map<String, BigInteger> resultados;
    protected int totalVertices;
    protected int totalArestas;

    public GrafoValorado() {
        grafo = new HashMap<>();
        vertices = new HashSet<>();
        resultados = new HashMap<>();
        totalVertices = totalArestas = 0;
    }

    public GrafoValorado(String nomeArquivo) {
        this();
        In in = new In(nomeArquivo);
        String linha;
        while ((linha = in.readLine()) != null) {
            String[] aresta = linha.split(" ");
            for (int i = 1; i < aresta.length - 2; i = i + 2) {
                adicionarAresta(aresta[i], aresta[aresta.length - 1], Integer.parseInt(aresta[i - 1]));
            }
        }
    }

    public void adicionarAresta(String v, String w, int peso) {
        Aresta a = new Aresta(v, w, peso);
        adicionarNaLista(v, a);
        adicionarNaLista(w, a);
        if (!vertices.contains(v)) {
            vertices.add(v);
            totalVertices++;
        }
        if (!vertices.contains(w)) {
            vertices.add(w);
            totalVertices++;
        }
        totalArestas += 2;
    }

    public Iterable<Aresta> getAdj(String v) {
        List<Aresta> res = grafo.get(v);
        if (res == null) res = new LinkedList<>();
        return res;
    }

    public int getTotalVertices() {
        return totalVertices;
    }

    public int getTotalArestas() {
        return totalArestas;
    }

    public Set<String> getVertices() {
        return vertices;
    }

    public Iterable<Aresta> getArestas() {
        Set<Aresta> arestas = new HashSet<>();
        for (String vertice : getVertices().stream().sorted().toList()) {
            for (Aresta a : getAdj(vertice)) {
                if (!arestas.contains(a)) {
                    arestas.add(a);
                }
            }
        }
        return arestas;
    }

    public void dfs1(GrafoValorado g) {
        String v = "hidrogenio";
        resultados.put(v, BigInteger.valueOf(1));
        dfs(g, v, BigInteger.valueOf(1));
    }

    private void dfs(GrafoValorado g, String v, BigInteger d) {
        for (Aresta a : g.getAdj(v)) {
            String w = a.getW();
            BigInteger aux = d.multiply(BigInteger.valueOf(a.getPeso()));
            if (resultados.containsKey(w)) {
                BigInteger novoValor = aux.add(resultados.get(w));
                resultados.put(w, novoValor);
            } else {
                resultados.put(w, aux);
            }
            dfs(g, w, aux);
        }
    }

    public Map tabela() {
        return resultados;
    }

    public BigInteger valorTotal(String elemento) {
        return resultados.get(elemento);
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("grafo {" + QUEBRA_DE_LINHA);
        sb.append("rankdir = LR;" + QUEBRA_DE_LINHA);
        sb.append("nodo [shape = circle];" + QUEBRA_DE_LINHA);
        for (Aresta a : getArestas())
            sb.append(String.format("%s -- %s [label=\"%d\"]", a.getV(), a.getW(), a.getPeso()) + QUEBRA_DE_LINHA);
        sb.append("}" + QUEBRA_DE_LINHA);
        return sb.toString();
    }

    protected List<Aresta> adicionarNaLista(String v, Aresta a) {
        List<Aresta> lista = grafo.get(v);
        if (lista == null)
            lista = new LinkedList<>();
        lista.add(a);
        grafo.put(v, lista);
        return lista;
    }
}
