package cuoiki;

import java.util.*;

class BranchBoundMST {
    private int V; // Số đỉnh của đồ thị
    private List<List<Edge>> adjacencyList; // Danh sách kề của đồ thị

    public BranchBoundMST(int V) {
        this.V = V;
        adjacencyList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        adjacencyList.get(src).add(edge);
        edge = new Edge(dest, src, weight);
        adjacencyList.get(dest).add(edge);
    }

    public int findMinSpanningTree() {
        int[] parent = new int[V]; // Mảng lưu các đỉnh cha của các đỉnh trong cây bao trùm
        int[] key = new int[V]; // Mảng lưu các trọng số tương ứng với các đỉnh
        boolean[] visited = new boolean[V]; // Mảng lưu trạng thái đã được thăm của các đỉnh

        // Khởi tạo các giá trị ban đầu
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Đỉnh bắt đầu của cây bao trùm là đỉnh 0
        parent[0] = -1; // Đỉnh 0 là đỉnh gốc, không có đỉnh cha
        key[0] = 0; // Đỉnh gốc có trọng số là 0

        // Tạo hàng đợi ưu tiên để lưu trữ các cạnh theo trọng số
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        // Thêm cạnh xuất phát vào hàng đợi ưu tiên
        pq.offer(new Edge(0, 0, 0));

        while (!pq.isEmpty()) {
            Edge minEdge = pq.poll();
            int currVertex = minEdge.dest;

            // Kiểm tra xem đỉnh hiện tại đã được thăm hay chưa
            if (visited[currVertex])
                continue;

            // Đánh dấu đỉnh hiện tại đã được thăm
            visited[currVertex] = true;

            // Cập nhật đỉnh cha và trọng số tương ứng
            parent[currVertex] = minEdge.src;
            key[currVertex] = minEdge.weight;

            // Duyệt qua các đỉnh kề của đỉnh hiện tại
            for (Edge edge : adjacencyList.get(currVertex)) {
                int dest = edge.dest;
                int weight = edge.weight;

                // Nếu đỉnh chưa được thăm và trọng số nhỏ hơn trọng số hiện tại, cập nhật cạnh và thêm vào hàng đợi ưu tiên
                if (!visited[dest] && weight < key[dest]) {
                    pq.offer(new Edge(currVertex, dest, weight));
                }
            }
        }

        // In ra cây bao trùm tối thiểu
        printMinSpanningTree(parent);

        // Tính tổng trọng số của cây bao trùm tối thiểu
        int minSpanningWeight = 0;
        for (int i = 0; i < V; i++) {
            minSpanningWeight += key[i];
        }

        return minSpanningWeight;
    }

    public void printMinSpanningTree(int[] parent) {
        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i);
        }
    }

}


