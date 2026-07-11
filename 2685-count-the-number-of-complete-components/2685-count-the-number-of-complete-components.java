import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] result = dfs(i, graph, visited);

                int nodes = result[0];
                int degreeSum = result[1];

                // Complete graph with k nodes has k * (k - 1) total degree
                if (degreeSum == nodes * (nodes - 1)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private int[] dfs(
        int node,
        List<Integer>[] graph,
        boolean[] visited
    ) {
        visited[node] = true;

        int nodes = 1;
        int degreeSum = graph[node].size();

        for (int neighbour : graph[node]) {
            if (!visited[neighbour]) {
                int[] result = dfs(neighbour, graph, visited);
                nodes += result[0];
                degreeSum += result[1];
            }
        }

        return new int[]{nodes, degreeSum};
    }
}