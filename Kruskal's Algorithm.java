import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    static class Graph {
        int V; 
        ArrayList<Edge> edges;
        String[] vertexNames;

        Graph(int v) {
            V = v;
            edges = new ArrayList<>();
            vertexNames = new String[V];
        }

        void addEdge(int src, int dest, int weight) {
            Edge newEdge = new Edge();
            newEdge.src = src;
            newEdge.dest = dest;
            newEdge.weight = weight;
            edges.add(newEdge);
        }

        void addVertexName(int vertex, String name) {
            vertexNames[vertex] = name;
        }

        int find(Subset subsets[], int i) {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);
            return subsets[i].parent;
        }

        void union(Subset subsets[], int x, int y) {
            int xRoot = find(subsets, x);
            int yRoot = find(subsets, y);

            if (subsets[xRoot].rank < subsets[yRoot].rank)
                subsets[xRoot].parent = yRoot;
            else if (subsets[xRoot].rank > subsets[yRoot].rank)
                subsets[yRoot].parent = xRoot;
            else {
                subsets[yRoot].parent = xRoot;
                subsets[xRoot].rank++;
            }
        }

        void KruskalMST() {
            ArrayList<Edge> result = new ArrayList<>(); 
            Collections.sort(edges); 

            Subset subsets[] = new Subset[V];
            for (int v = 0; v < V; ++v) {
                subsets[v] = new Subset();
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            int i = 0; 

            while (i < edges.size()) {
                Edge next_edge = edges.get(i++);

                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);

                if (x != y) {
                    result.add(next_edge);
                    union(subsets, x, y);
                }
            }

            System.out.println("Edge \tWeight");
            for (Edge e : result)
                System.out.println(vertexNames[e.src] + "-" + vertexNames[e.dest] + "\t" + e.weight);
        }
    }

  
    public static void main(String[] args) {
        int V = 7; 
        Graph graph = new Graph(V);

        // add edges
        graph.addEdge(0, 1, 4); 
        graph.addEdge(0, 6, 10); 
        graph.addEdge(0, 2, 9); 
        graph.addEdge(1, 2, 8);  
        graph.addEdge(2, 3, 5); 
        graph.addEdge(2, 4, 2);  
        graph.addEdge(2, 6, 7); 
        graph.addEdge(3, 4, 3); 
        graph.addEdge(3, 5, 7);  
        graph.addEdge(4, 6, 6);  
        graph.addEdge(5, 6, 11); 

       
        graph.addVertexName(0, "A");
        graph.addVertexName(1, "B");
        graph.addVertexName(2, "C");
        graph.addVertexName(3, "D");
        graph.addVertexName(4, "E");
        graph.addVertexName(5, "F");
        graph.addVertexName(6, "G");

        System.out.println("Kruskal's Algorithm MST:");
        graph.KruskalMST();
    }
}

// Java
