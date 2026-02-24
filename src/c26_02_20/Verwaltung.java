import _shared.List;
import _shared.Vertex;
import _shared.Edge;
import _shared.Graph;

public class Verwaltung {
    private Graph graph;

    public Verwaltung(){
        graph = new Graph();
    }

    public void fuelleBeispielgraph() {
        Vertex vertexA = new Vertex("1");
        Vertex vertexB = new Vertex("2");
        Vertex vertexC = new Vertex("3");
        Vertex vertexD = new Vertex("4");
        Vertex vertexE = new Vertex("5");
        Vertex vertexF = new Vertex("6");
        Vertex vertexG = new Vertex("7");
        Vertex vertexP = new Vertex("8");

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);
        graph.addVertex(vertexE);
        graph.addVertex(vertexF);
        graph.addVertex(vertexG);
        graph.addVertex(vertexP);

        Edge edgeAE = new Edge(vertexA, vertexE, 2);
        Edge edgeAP = new Edge(vertexA, vertexP, 4);
        Edge edgeAG = new Edge(vertexA, vertexG, 5);
        Edge edgeEC = new Edge(vertexE, vertexC, 1);
        Edge edgeEF = new Edge(vertexE, vertexF, 1);
        Edge edgePF = new Edge(vertexP, vertexF, 6);
        Edge edgeGB = new Edge(vertexG, vertexB, 7);
        Edge edgeCF = new Edge(vertexC, vertexF, 2);
        Edge edgeFD = new Edge(vertexF, vertexD, 11);
        Edge edgeBD = new Edge(vertexB, vertexD, 3);
        
        graph.addEdge(edgeAE);
        graph.addEdge(edgeAP);
        graph.addEdge(edgeAG);
        graph.addEdge(edgeEC);
        graph.addEdge(edgeEF);
        graph.addEdge(edgePF);
        graph.addEdge(edgeGB);
        graph.addEdge(edgeCF);
        graph.addEdge(edgeFD);
        graph.addEdge(edgeBD);

    }
    
    public List<List<Vertex>> gibAllePfade(char pNameStart, char pNameEnde) {
        Vertex startVertex = graph.getVertex(pNameStart+"");
        Vertex endVertex = graph.getVertex(pNameEnde+"");
        List<List<Vertex>> ergebnis = new List<List<Vertex>>();

        List<Vertex> nachbarn = startVertex.getNeighbours();
        ergebnis.toFirst();
        do {
            ergebnis.next();



        } while (ergebnis.hasAccess());
        for(nachbarn.toFirst(); nachbarn.hasAccess(); nachbarn.next()) {
            ergebnis.append(new List<Vertex>());
            ergebnis.getContent().insert(startVertex);
        }

        


        return ergebnis;
    }
    public List<String> gibKuerztestenPfad(char pNameStart, char pNameEnde) {
        /*
        List<List<String>> allePfade = gibAllePfade(pNameStart, pNameEnde);
        List<String> erg = null;
        //DEIN CODE HIER
        return erg;
        */
    }
}
 