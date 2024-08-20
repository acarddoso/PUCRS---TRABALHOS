public class App
{
  public static void main(String[] args) {

    DigrafoValorado g = new DigrafoValorado("z150.txt");

    for (String v : g.getVertices()) {
      System.out.print(v + ": ");
      for (Aresta e : g.getAdj(v))
        System.out.print(e + " ");
      System.out.println();
    }

    System.out.println();
    System.out.println(g.toDot());
    g.dfs1(g);
    System.out.println("O valor total de hidrogenios: " + g.valorTotal("ouro"));
  }
}
