import sun.lwawt.macosx.CPrinterDevice;

import java.util.*;

public class Test {

    public int countSubstrings(String s) {
        int total = 0;
        char arr[] = s.toCharArray();
        for(int i=0; i<arr.length; i++){
            // odd pattern
            int r=i;
            int l=i;
            while(l >= 0 && r< arr.length && arr[l] == arr[r]){
                total++;
                l--;
                r++;
            }

            // even pattern
            r=i+1;
            l=i;
            while(l >= 0 && r< arr.length && arr[l] == arr[r]){
                total++;
                l--;
                r++;
            }

        }

        return total;
    }

    public String longestPalindrome(String s) {
        String result = "";
        int max = 0;
        char arr[] = s.toCharArray();
        for(int i=0; i<arr.length; i++){
            // odd pattern
            int r=i;
            int l=i;
            while(l >= 0 && r< arr.length && arr[l] == arr[r]){
                if((r-l)+1 > max){
                    result = s.substring(l,r+1);
                    max = (r-l)+1;
                }
                l--;
                r++;
            }

            // even pattern
            r=i+1;
            l=i;
            while(l >= 0 && r< arr.length && arr[l] == arr[r]){
                if((r-l)+1 > max){
                    result = s.substring(l,r+1);
                    max = (r-l)+1;
                }
                l--;
                r++;
            }

        }




        return result;
    }


    public int fib(int n) {
        if(n == 0){
            return n;
        }
        if(n == 1){
            return n;
        }

        return  fib(n-2)+fib(n-1);
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int left = 0;
        int right = left + 1;
        while(left < nums.length){
            if(nums[left]+nums[right] == target){
                result[0] = nums[left];
                result[1] = nums[right];
                return result;
            }else if(right >= nums.length - 1){
                if(left  < nums.length - 1){
                    left++;
                    right = left + 1;
                }
            }else{
                right ++;
            }
        }

        return result;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m;
        int right = 0;
        int i =0;
        while(left < m+n && right < n){
            nums1[left] = nums2[right];
            left++;
            right++;
        }
        Arrays.sort(nums1);
        System.out.println(nums1);
    }



    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        char[] arr = s.toCharArray();
        for(int i=0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i]) + 1);
            }else{
                map.put(arr[i], 1);
            }
        }
        char[] arr2 = t.toCharArray();
        int left=0;
        int right=arr2.length -1;
        Set<Character> set = new HashSet<>();
        int sum=0;
        char tmp = 'x';
        while(left < arr2.length){
            tmp = arr2[left];
            if(set.contains(tmp)){
                left++;
                right = arr2.length - 1;
                continue;
            }

            if(left == right){
                if(!map.containsKey(tmp)){
                    return false;
                }
                if(map.get(tmp) != (sum+1)){
                    return false;
                }else{
                    return true;
                }
            }

            if(arr2[right] == tmp){
                sum++;
                right--;
            }else if(right == left + 1){
                right = arr2.length - 1;
                left++;
                if(!map.containsKey(tmp)){
                    return false;
                }
                if(map.get(tmp) != (sum+1)){
                    return false;
                }
                sum = 0;
                set.add(tmp);
            }else{
                right--;
            }
        }

        return true;
    }
    public void call(String sample){
        System.out.println("TEst");
    }

    public int removeDuplicates(int[] nums) {
        if(nums.length == 1){
            return 1;
        }
        int left = 0;
        int sum = 0;
        int right = 1;
        while(left < nums.length && right < nums.length){
            if(nums[left] != nums[right]){
                sum ++;
            }
            left ++;
            right = left + 1;
        }
        return sum + 1;
    }

    public int findJudge(int n, int[][] trust) {
        int[] count = new int[n+1];
        for(int[] t : trust){
            count[t[0]]--;
            count[t[1]]++;
        }
        for(int i=1; i<=n; i++){
            if(count[i] == n-1) return i;
        }
        return -1;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Graph g = new Graph(n);
        for(int i=0; i<edges.length; i++){
            g.addEdge(edges[i][0], edges[i][1]);
        }

        Map<Integer,List<Integer>>  graph = g.adjList();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        stack.add(source);
        while(!stack.isEmpty()){
           int current = stack.pop();
           if(current == destination) return true;
           if(!set.contains(current)){
               List<Integer> ls = graph.get(current);
               set.add(current);
               for(Integer v: ls){
                   stack.add(v);
               }
           }
        }
        return false;
    }

    public int minReorder(int n, int[][] connections) {
        Map<Integer,List<Integer>>  graph = buildDirectedGraph(connections);
        Set<Integer> visitedSet = new HashSet<>();
        int count = 0;
        for(int i=0; i<n; i++){
            count = exploreG(graph,i,visitedSet,count);
        }
        return count;
    }

    private int exploreG(Map<Integer,List<Integer>> graph, int vertices, Set<Integer> visited, int count){
        if(visited.contains(vertices)) return count;
        visited.add(vertices);
        List<Integer> neighbours = graph.get(vertices);
        for(int neighbour: neighbours){
            if(neighbour != 0 && neighbour > vertices){
                count++;
            }
            count = exploreG(graph,neighbour,visited,count);
        }
        return count;
    }


    public List<Integer> usingIndegree(int n, List<List<Integer>> edges){//[0,1],[0,2],[2,5],[3,4],[4,2]]
        int[] indegree = new int[n];
        for (List<Integer> edge : edges) {
            indegree[edge.get(1)] = indegree[edge.get(1)] + 1;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                res.add(i);
            }
        }

        return res;
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> vls = new ArrayList<>();
        Map<Integer,List<Integer>> graph = buildDirectedGraphFromList(edges);
        Set<Integer> visitedSet = new HashSet<>();
        for(int i=0; i<n; i++){
            if(explore(graph,i,visitedSet) == true){
                vls.add(i);
            }
        }
        return vls;
    }

    public List<Integer> notVisited(List<Integer> vls, int n, Set<Integer> visited){
        for(int i=0; i<n; i++){
           if(!visited.contains(i)){
               vls.add(i);
           }
        }
        return vls;
    }

    private boolean explore(Map<Integer,List<Integer>> graph, int vertices, Set<Integer> visited){
        if(visited.contains(vertices)) return false;
        visited.add(vertices);
        List<Integer> neighbours = graph.get(vertices);
        for(int neighbour: neighbours){
            explore(graph,neighbour,visited);
        }
        return true;
    }

    // Directed graph with list<list>
    private Map<Integer,List<Integer>> buildDirectedGraphFromList(List<List<Integer>> edges){
        Map<Integer,List<Integer>>  graph = new HashMap<>();
        for(int i=0; i<edges.size(); i++){
            int a = edges.get(i).get(0);
            int b = edges.get(i).get(1);
            if(!graph.containsKey(a)) graph.put(a,new ArrayList<>());
            if(!graph.containsKey(b)) graph.put(b,new ArrayList<>());
            graph.get(a).add(b);
        }
        System.out.println(graph);
        return graph;
    }

    // For Undirected graph
    private  Map<Integer,List<Integer>>  buildUnDirectedGraph(int[][] edges){
        Map<Integer,List<Integer>>  graph = new HashMap<>();
        for(int i=0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            if(!graph.containsKey(a)) graph.put(a,new ArrayList<>());
            if(!graph.containsKey(b)) graph.put(b,new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        System.out.println(graph);
        return graph;
    }

    // For Directed graph
    private  Map<Integer,List<Integer>>  buildDirectedGraph(int[][] edges){
        Map<Integer,List<Integer>>  graph = new HashMap<>();
        for(int i=0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            if(!graph.containsKey(a)) graph.put(a,new ArrayList<>());
            if(!graph.containsKey(b)) graph.put(b,new ArrayList<>());
            graph.get(a).add(b);
        }
        System.out.println(graph);
        return graph;
    }

    public int findCenter(int[][] edges) {
        int a = edges[0][0];
        int b = edges[0][1];
        int c = edges[1][0];
        int d = edges[1][1];
        if(a==c) return a;
        if(a==d) return a;
        if(b==c) return c;
        if(b==d) return b;
        return 0;
    }

    public static void main(String[] args) {
        String s = "dgqztusjuu", t = "dqugjzutsu";
        //System.out.println(new Test().isAnagram(s,t));
       // System.out.println(new Test().longestPalindrome("babad"));
       // System.out.println(new Test().fib(3));
        int[] nums1 = {0,0,1,1,1,2,2,3,3,4};
        int[] nums2 = {1,1,2};
        //System.out.println(new Test().twoSum(nums,6));
        //new Test().merge(nums1,3,nums2,3);

       // System.out.println(new Test().removeDuplicates(nums2));
        int [][] tes = {{0,1},{0,2},{3,5},{5,4},{4,3}}; //0,1],[1,2],[2,0] //[[0,1],[0,2],[3,5],[5,4],[4,3]]
       // System.out.println(new Test().findJudge(3,tes));
       // boolean ss = new Test().validPath(6,tes,0,5);
       // System.out.println(ss);
        int [][] tes2 = {{1,2},{2,3},{4,2}};
        //System.out.println( new Test().findCenter(tes2)); 0,2],[2,5],[3,4],[4,2]]
        List<List<Integer>> ls = new ArrayList<>();
        List<Integer> sLs = new ArrayList<>();
        sLs.add(0);
        sLs.add(1);
        List<Integer> sLs1 = new ArrayList<>();
        sLs1.add(2);
        sLs1.add(5);
        List<Integer> sLs2 = new ArrayList<>();
        sLs2.add(3);
        sLs2.add(4);
        List<Integer> sLs3 = new ArrayList<>();
        sLs3.add(4);
        sLs3.add(2);
        List<Integer> sLs4 = new ArrayList<>();
        sLs4.add(0);
        sLs4.add(2);
        ls.add(sLs);
        ls.add(sLs4);
        ls.add(sLs1);
        ls.add(sLs2);
        ls.add(sLs3);
        //System.out.println(new Test().usingIndegree(6,ls));
        //[[0,1],[1,3],[2,3],[4,0],[4,5]] , 6
        int [][] tt = {{1,0},{1,2},{2,3},{4,2}}; //[[1,0],[1,2],[3,2],[3,4]] //[[1,0],[1,2],[2,3],[4,2]]
        System.out.println(new Test().minReorder(5,tt));
    }
}
class Graph{
    int vertices;
    //LinkedList<Integer>[] adj_list;
    Map<Integer,List<Integer>> adj;
    Graph(int vertices){
        this.vertices = vertices;
        //adj_list = new LinkedList[vertices];
        adj = new HashMap<>();
        for (int i=0; i<vertices; ++i)
            //adj_list[i] = new LinkedList();
            adj.put(i,new ArrayList<>());
    }
    void addEdge(int v, int w) {
        //adj_list[v].add(w);  // Add w to v's list.
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    Map<Integer,List<Integer>> adjList(){
        System.out.println(adj);
        return adj;
    }
}
