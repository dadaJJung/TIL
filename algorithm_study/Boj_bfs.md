### [숨바꼭질](https://www.acmicpc.net/problem/1697)

---

```java
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int me = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[1000000];

        //x-1, x+1, 2x
        Queue<Integer> q = new LinkedList<>();
        q.offer(me);
        visited[me] = true;
        int time = 0;
        int size = q.size();



        while(!q.isEmpty()){
            int cur = q.poll();
            --size;

            if(cur==sister){
                break;
            }

            if(cur-1>=0 && visited[cur-1]==false){
                visited[cur-1] = true;
                q.offer(cur-1);
            }

            //+1
            if(cur+1<visited.length && visited[cur+1]==false){
                visited[cur+1] = true;
                q.offer(cur+1);
            }

            //x2
            if(cur*2 <visited.length && visited[cur*2]==false){
                visited[cur*2] = true;
                q.offer(cur*2);
            }


            if(size==0){
                time++;
                size = q.size();
            }

        }



        System.out.println(time);


    }
}

```

<br>

<br>

### [숨바꼭질 4]([13913번: 숨바꼭질 4](https://www.acmicpc.net/problem/13913))

---

- 동생을 찾아온 경로를 기록해야함

- 현재 위치가 어느 위치로부터 왔는지 기록 (from[현재위치] = 직전위치)

- 동생의 위치를 찾았을 경우, 동생의 위치부터 from[sister]를 추적해가면서 경로를 찾는다

- 위 경로를 출력할때는 출발지점부터 출력해야하는데 위에서는 역순으로 출발점을 찾아간다

- 따라서, 동생의 위치부터 출발점까지의 경로를 찾아가면서 이 위치값을 stack에 보관

- stack에서 하나씩 pop하면서 출력 

```java
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int me = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());
        int MAX = 1000000;

        boolean[] visited = new boolean[MAX];
        int[] from = new int[MAX];


        Queue<Integer> q = new LinkedList<>();
        q.offer(me);
        visited[me] = true;
        from[me] = -1;  //출발점
        int time = 0;
        int size = q.size();

        while(!q.isEmpty()) {
            int cur = q.poll();
            --size;

            if (cur == sister) {
                break;
            }


            //-1
            if (0 <= cur - 1 && visited[cur - 1] == false) {
                visited[cur - 1] = true;
                from[cur - 1] = cur;
                q.offer(cur - 1);
            }

            //+1
            if (cur + 1 < MAX && visited[cur + 1] == false) {
                visited[cur + 1] = true;
                from[cur + 1] = cur;
                q.offer(cur + 1);
            }

            //x2
            if (cur * 2 < MAX && visited[cur * 2] == false) {
                visited[cur * 2] = true;
                from[cur * 2] = cur;
                q.offer(cur * 2);
            }

            if (size == 0) {
                time++;
                size = q.size();
            }

        }

        System.out.println(time);


        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for(int i=sister; i!=-1; i=from[i]){
            stack.add(i);
        }

        while(!stack.isEmpty()) sb.append(stack.pop()+" ");
        System.out.println(sb.toString());



    }
}

```


