import java.util.*;

class Solution {

    static class Edge {
        int to;
        long cost;

        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];
        int maxCost = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        List<Integer> topo = topoSort(graph, indegree);

        int low = 0, high = maxCost;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(graph, topo, online, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private List<Integer> topoSort(List<Edge>[] graph, int[] indegree) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> topo = new ArrayList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);

            for (Edge e : graph[u]) {
                if (--indegree[e.to] == 0) {
                    q.offer(e.to);
                }
            }
        }

        return topo;
    }

    private boolean check(List<Edge>[] graph, List<Integer> topo,
                          boolean[] online, long k, int minEdge) {

        int n = online.length;
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF) continue;
            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (Edge e : graph[u]) {
                int v = e.to;

                if (v != n - 1 && !online[v]) continue;
                if (e.cost < minEdge) continue;

                if (dist[v] > dist[u] + e.cost) {
                    dist[v] = dist[u] + e.cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}