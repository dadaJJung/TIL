### [LeetCode 692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)

<br>

### 문제

---

- Given an array of strings `words` and an integer `k`, return *the* `k` *most frequent strings*.

- Return the answer **sorted** by **the frequency** from highest to lowest. Sort the words with the same frequency by their **lexicographical order**.

<br>

### 풀이 코드  - version1

---

- hashmap을 사용해서 단어별 빈도수를 저장한다

- 단어와 단어의 빈도수를 가지고 있는 Word 클래스를 만들고 Comparable인터페이스를 구현해서 단어별 정렬 기준을 정해준다

- PriorityQueue에 Word 인스턴스들을 넣고 k개 만큼 뽑아서 List에 넣어 리턴한다

```java
//version1
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        //단어별 빈도수 저장 
        HashMap<String,Word> map = new HashMap<>();
        for(String key : words){
            if(map.containsKey(key)){ 
                map.get(key).count++;
            }else{
                map.put(key,new Word(key));
            }
        }

        PriorityQueue<Word> pq = new PriorityQueue<>();
        for(Word word : map.values()){
            pq.offer(word);
        }
        
        
        List<String> list = new ArrayList<>();

        for(int i=0; i<k; i++){
            list.add(pq.poll().word);
        }

        return list;
        
    }
}


class Word implements Comparable<Word>{
    
    String word;
    int count;
    
    Word(String word){
        this.word = word;
        this.count = 1;
    }
    
    public int compareTo(Word o){
        if(this.count!=o.count){  //횟수 내림차순
            return o.count - this.count;
        }else{  //문자열 오름차순
            return this.word.compareTo(o.word);
        }
    }
    
}


```

<br>

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        HashMap<String,Word> map = new HashMap<>();
        for(String key : words){
            if(map.containsKey(key)){ 
                map.get(key).count++;
            }else{
                map.put(key,new Word(key));
            }
        }
        
        //ex) 3,4,5 유지
        // 6 들어올 때? -> 3이 나가야함
        //4,5,6
        PriorityQueue<Word> pq = new PriorityQueue<>(k,(a,b)-> a.count!=b.count?a.count-b.count : b.word.compareTo(a.word));
        
        for(Word word : map.values()){
            pq.offer(word);
            if(pq.size()>k) pq.poll();
        }
    
        
        List<String> list = new ArrayList<>();
        while(!pq.isEmpty()){
            list.add( 0, pq.poll().word );
        }
        return list;
        
    }
}


class Word{
    
    String word;
    int count;
    
    Word(String word){
        this.word = word;
        this.count = 1;
    }
  
}
```
