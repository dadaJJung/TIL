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

<br>

<br>

### [13549번: 숨바꼭질 3](https://www.acmicpc.net/problem/13549)

---

- 순간이동은 0초 / 걷기는 1초가 걸린다.

- 덱을 이용해서 순간이동은 덱의 앞에 걷기는 덱의 뒤에 넣는다

```java
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int me = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());

        int MAX = 1000000;
        int[] visited = new int[MAX];
        Arrays.fill(visited,-1);

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(me);
        visited[me] = 0;

        while(!deque.isEmpty()){

            int cur = deque.poll();

            if(cur==sister){
                System.out.println(visited[cur]);
                break;
            }

            //*2 (순간이동 0초)
            if(cur*2<MAX && visited[cur*2]==-1){
                visited[cur*2] = visited[cur];
                deque.addFirst(cur*2);
            }

            //+1
            if(cur+1<MAX && visited[cur+1]==-1){
                visited[cur+1] = visited[cur] + 1;
                deque.addLast(cur+1);
            }

            //-1
            if(cur-1>=0 && visited[cur-1]==-1){
                visited[cur-1] = visited[cur] + 1;
                deque.addLast(cur-1);
            }
        }



    }
}

```

<br>

<br>

### [12851번: 숨바꼭질 2](https://www.acmicpc.net/problem/12851)

---

- count[i] => i위치까지 오는데 걸린 시간 / count[i] == -1 이면 이 위치에 도착한적이 없음 

- `count[i]==-1 || cost == count[i]`
  
  - i위치에 처음 방문하면 => q에 넣기
  
  - i위치에 방문했었던 적이 있더라도, i위치까지 오는데 걸린 시간이 같다면 => q에 넣기
    
    - bfs를 사용하기 때문에 같은 위치에 다시 도착할 때 걸리는 시간은 처음 도착할 때 시간과 같거나 크다 (적게 걸리는 경우는 없다) 
    
    - 따라서, 특정 위치에 처음 도착했던 시간보다 더 오랜 시간이 걸리느 경로로 도착한 경우는 최적의 경로가 아니니까 그냥 지나가고
    
    - 처음 도착했던 시간과 같은 시간으로 특정 위치에 도달할 수 있는 경로가 있다면 => 가장 빠른 방법으로 동생을 찾는 방법 중 하나일 수 있기 때문에 q에 넣어줌

```java
package Boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12851 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int me = Integer.parseInt(st.nextToken());
        int sister = Integer.parseInt(st.nextToken());

        int MAX = 1000000;
        int[] count = new int[MAX];
        Arrays.fill(count,-1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(me);
        count[me] = 0;

        int result = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur==sister){
                ++result;
                continue;
            }

            int cost = count[cur] + 1;

            //*2 (순간이동 0초)
            if(cur*2<MAX && (count[cur*2]==-1 || cost == count[cur*2]) ){
                count[cur*2] = cost;
                q.offer(cur*2);
            }

            //+1
            if(cur+1<MAX &&  (count[cur+1]==-1 || cost == count[cur+1]) ){
                count[cur+1] = cost;
                q.offer(cur+1);
            }

            //-1
            if(cur-1>=0 &&  (count[cur-1]==-1 || cost == count[cur-1]) ){
                count[cur-1] = cost;
                q.offer(cur-1);
            }

        }


        System.out.println(count[sister]);
        System.out.println(result);



    }
}


```
