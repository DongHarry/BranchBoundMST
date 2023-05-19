package cuoiki;

public class Main {
    public static void main(String[] args) {
        int numVertices = 5;
        BranchBoundMST mst = new BranchBoundMST(numVertices);

        // Thêm các cạnh vào đồ thị
        mst.addEdge(0, 1, 2);
        mst.addEdge(0, 3, 6);
        mst.addEdge(1, 2, 3);
        mst.addEdge(1, 3, 8);
        mst.addEdge(1, 4, 5);
        mst.addEdge(2, 4, 7);
        mst.addEdge(3, 4, 9);

        int minSpanningWeight = mst.findMinSpanningTree();
        System.out.println("Minimum Spanning Tree Weight: " + minSpanningWeight);
    }
}
//    public static BranchBroundMST createRandomGraph(int numVertices, int numEdges, int maxWeight) {
//        BranchBroundMST g = new BranchBroundMST(numVertices);
//        Random random = new Random();
//        for (int i = 0; i < numEdges; i++) {
//            int src = random.nextInt(numVertices);
//            int dest = random.nextInt(numVertices);
//            int weight = random.nextInt(maxWeight) + 1;
//
//
//            if (src != dest) {
//                g.addEdge(src, dest, weight);
//            }
//        }
//        return g;
//    }


