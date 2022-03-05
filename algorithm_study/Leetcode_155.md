### [155. Min Stack](https://leetcode.com/problems/min-stack/)

<br>

### 문제

---

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.

<br>

### 풀이 코드 - version1

---

- Stack과 PriorityQueue 두개의 자료구조를 사용했다.
- Stack에 값을 push/pop 할때마다 PriorityQueue에도 offer/remove
- getMin()을 수행할 때 pq에서 값을 꺼내면 가장 작은 값이 나온다.
- 하지만.. 이런 방법을 사용하면 PriorityQueue를 유지하기 위한 (heapify) 시간소요 

```java
class MinStack {
    Stack<Integer> stack;
    PriorityQueue<Integer> pq;

    public MinStack() {
        stack = new Stack<>();
        pq = new PriorityQueue<>();
    }

    public void push(int val) {
        stack.push(val);
        pq.offer(val);
    }

    public void pop() {
        int val = stack.pop();
        pq.remove(val);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return pq.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

<br>

### 풀이 코드 - version2

---

- 하나의 스택을 사용해서 구현한다.

- 스택에 Node instance를 넣는다 (value와 minValue)를 가지고 있음

- 스택에서 pop할때, pop한 노드의 value가 현재 노드 중에서 가장 작은 값 (min-value)이라고 해도, 그 전 노드는 현재 노드가 없을때의 min-value를 가지고 있기 때문에 서로 영향을 주지 않음

```java
class MinStack {
    
    class Node{
        int val;
        int minVal;
        Node(int val, int minVal){
            this.val = val;
            this.minVal = minVal;
        }
    }
    
    Stack<Node> stack;
    
    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        if(stack.isEmpty()){
            stack.push(new Node(val,val));
        }else{
            stack.push( new Node(val,Math.min(val,stack.peek().minVal))   );
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
       return stack.peek().val;
    }
    
    public int getMin() {
        return stack.peek().minVal;
    }
}

```


