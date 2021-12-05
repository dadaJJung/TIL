

### [2021 KAKAO BLIND RECRUITMENT](https://programmers.co.kr/learn/challenges) > [순위검색](https://programmers.co.kr/learn/courses/30/lessons/72412)

<br>

<b>문제</b>

---

카카오는 하반기 경력 개발자 공개채용을 진행 중에 있으며 현재 지원서 접수와 코딩테스트가 종료되었습니다. 이번 채용에서 지원자는 지원서 작성 시 아래와 같이 4가지 항목을 반드시 선택하도록 하였습니다.

- 코딩테스트 참여 개발언어 항목에 cpp, java, python 중 하나를 선택해야 합니다.
- 지원 직군 항목에 backend와 frontend 중 하나를 선택해야 합니다.
- 지원 경력구분 항목에 junior와 senior 중 하나를 선택해야 합니다.
- 선호하는 소울푸드로 chicken과 pizza 중 하나를 선택해야 합니다.

인재영입팀에 근무하고 있는 `니니즈`는 코딩테스트 결과를 분석하여 채용에 참여한 개발팀들에 제공하기 위해 지원자들의 지원 조건을 선택하면 해당 조건에 맞는 지원자가 몇 명인 지 쉽게 알 수 있는 도구를 만들고 있습니다.
예를 들어, 개발팀에서 궁금해하는 문의사항은 다음과 같은 형태가 될 수 있습니다.
`코딩테스트에 java로 참여했으며, backend 직군을 선택했고, junior 경력이면서, 소울푸드로 pizza를 선택한 사람 중 코딩테스트 점수를 50점 이상 받은 지원자는 몇 명인가?`

물론 이 외에도 각 개발팀의 상황에 따라 아래와 같이 다양한 형태의 문의가 있을 수 있습니다.

- 코딩테스트에 python으로 참여했으며, frontend 직군을 선택했고, senior 경력이면서, 소울푸드로 chicken을 선택한 사람 중 코딩테스트 점수를 100점 이상 받은 사람은 모두 몇 명인가?
- 코딩테스트에 cpp로 참여했으며, senior 경력이면서, 소울푸드로 pizza를 선택한 사람 중 코딩테스트 점수를 100점 이상 받은 사람은 모두 몇 명인가?
- backend 직군을 선택했고, senior 경력이면서 코딩테스트 점수를 200점 이상 받은 사람은 모두 몇 명인가?
- 소울푸드로 chicken을 선택한 사람 중 코딩테스트 점수를 250점 이상 받은 사람은 모두 몇 명인가?
- 코딩테스트 점수를 150점 이상 받은 사람은 모두 몇 명인가?

즉, 개발팀에서 궁금해하는 내용은 다음과 같은 형태를 갖습니다.

```
* [조건]을 만족하는 사람 중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇 명인가?
```

지원자가 지원서에 입력한 4가지의 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성한 값의 배열 info, 개발팀이 궁금해하는 문의조건이 문자열 형태로 담긴 배열 query가 매개변수로 주어질 때,
각 문의조건에 해당하는 사람들의 숫자를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.

<br>

<b>제한사항</b>

---

- info 배열의 크기는 1 이상 50,000 이하입니다.
- info 배열 각 원소의 값은 지원자가 지원서에 입력한 4가지 값과 코딩테스트 점수를 합친 "개발언어 직군 경력 소울푸드 점수" 형식입니다.
  - 개발언어는 cpp, java, python 중 하나입니다.
  - 직군은 backend, frontend 중 하나입니다.
  - 경력은 junior, senior 중 하나입니다.
  - 소울푸드는 chicken, pizza 중 하나입니다.
  - 점수는 코딩테스트 점수를 의미하며, 1 이상 100,000 이하인 자연수입니다.
  - 각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
- query 배열의 크기는 1 이상 100,000 이하입니다.
- query의 각 문자열은 "[조건] X" 형식입니다.
  - [조건]은 "개발언어 and 직군 and 경력 and 소울푸드" 형식의 문자열입니다.
  - 언어는 cpp, java, python, - 중 하나입니다.
  - 직군은 backend, frontend, - 중 하나입니다.
  - 경력은 junior, senior, - 중 하나입니다.
  - 소울푸드는 chicken, pizza, - 중 하나입니다.
  - '-' 표시는 해당 조건을 고려하지 않겠다는 의미입니다.
  - X는 코딩테스트 점수를 의미하며 조건을 만족하는 사람 중 X점 이상 받은 사람은 모두 몇 명인 지를 의미합니다.
  - 각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
  - 예를 들면, "cpp and - and senior and pizza 500"은 "cpp로 코딩테스트를 봤으며, 경력은 senior 이면서 소울푸드로 pizza를 선택한 지원자 중 코딩테스트 점수를 500점 이상 받은 사람은 모두 몇 명인가?"를 의미합니다.

<br>

<b>입출력 예</b>

---

| info                                                         | query                                                        | result        |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------- |
| `["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"]` | `["java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"]` | [1,1,1,1,2,4] |

##### **입출력 예에 대한 설명**

지원자 정보를 표로 나타내면 다음과 같습니다.

| 언어   | 직군     | 경력   | 소울 푸드 | 점수 |
| ------ | -------- | ------ | --------- | ---- |
| java   | backend  | junior | pizza     | 150  |
| python | frontend | senior | chicken   | 210  |
| python | frontend | senior | chicken   | 150  |
| cpp    | backend  | senior | pizza     | 260  |
| java   | backend  | junior | chicken   | 80   |
| python | backend  | senior | chicken   | 50   |

- `"java and backend and junior and pizza 100"` : java로 코딩테스트를 봤으며, backend 직군을 선택했고 junior 경력이면서 소울푸드로 pizza를 선택한 지원자 중 코딩테스트 점수를 100점 이상 받은 지원자는 1명 입니다.
- `"python and frontend and senior and chicken 200"` : python으로 코딩테스트를 봤으며, frontend 직군을 선택했고, senior 경력이면서 소울 푸드로 chicken을 선택한 지원자 중 코딩테스트 점수를 200점 이상 받은 지원자는 1명 입니다.
- `"cpp and - and senior and pizza 250"` : cpp로 코딩테스트를 봤으며, senior 경력이면서 소울푸드로 pizza를 선택한 지원자 중 코딩테스트 점수를 250점 이상 받은 지원자는 1명 입니다.
- `"- and backend and senior and - 150"` : backend 직군을 선택했고, senior 경력인 지원자 중 코딩테스트 점수를 150점 이상 받은 지원자는 1명 입니다.
- `"- and - and - and chicken 100"` : 소울푸드로 chicken을 선택한 지원자 중 코딩테스트 점수를 100점 이상을 받은 지원자는 2명 입니다.
- `"- and - and - and - 150"` : 코딩테스트 점수를 150점 이상 받은 지원자는 4명 입니다.

<br>

<b>풀이 </b>

---

> 혼자 풀어보다가 너무 막혀서 [카카오코딩테스트- 순위검색](https://www.youtube.com/watch?v=eBQtFteduyw) 영상을 참고해서 해결했다ㅜㅜ 나올 수 있는 모든 조합의 수는 4x3x3x3=108개이다. 먼저 이 풀이에서는 개발언어, 직군, 경력, 소울푸드의 조합에 따라 108개의 인덱스로 변환되도록 구현했다. 
>
> 이 영상에서는 내가 해결하기 어려웠던 부분을 Bit를 이용한 부분집합으로 해결했다. Bit를 이용해서 특정 조건을 고려하지 않거나 (-) 또는 특정 조건을 고려하는 방법의 조합을 만들 수 있다. 예를 들어 java-backend-senior-pizza의 조건을 가진 참가자는 아래와 같이 총 16개의 조건에 의해 검색될 수 있다.
>
> -,-,-,- / -,-,-,pizza / -,-,senior,- / -,-,senior,pizza / -,backend,-,- / -,backend,-,pizza / -,backend,senior,- / -,backend,senior,pizza / java,-,-,- / java,-,-,pizza / java,-,senior,- / java,-,senior,pizza / java,backend,-,- / java,backend,-,pizza / java,backend,senior,- / java,backend,senior,pizza
>
> 따라서 참가자의 조건마다, 16개의 조합을 만들어서 미리 기록해둔다.  같은 조건에 서로 다른 점수를 가진 참가자들이 존재할 수 있다. 이 점수를 ArrayList로 기록하고 정렬한다. 나중에 특정 조건을 만족하는 참가자를 찾을때 이 리스트에서 이진탐색으로 몇점 이상인 참가자들만 빠르게 검색할 수 있다. 나는 lower_bound를 구현해서 특정 조건을 만족하는 참가자의 수를 찾도록 구현했다.
>
> level2문제인데 풀릴듯 안풀리고, 굉장히 어렵게 느껴졌던 문제이다. 하지만 이 문제를 풀면서 Bit를 이용한 부분집합, 이진탐색, lowerbound 등의 개념을 확실히 정리할 수 있어서 너무 유익한 문제였다. 

<br>

<b>주의해야할  포인트</b>

---

- 특정 조건을 가진 참가자의 점수를 기록한 ArrayList의 길이가 0일수도 있다 (즉, 그 조건을 만족하는 참가자가 한명도 없을 수 도 있다. ) 이런 경우에는 lowerbound값을 구하지 않고 넘어가도록 처리해야한다.

<br>

<b>전체코드 - version1</b>

```java
import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        
        HashMap<String,Integer> wordMap = new HashMap<>();
        wordMap.put("-",0);
        wordMap.put("cpp",1);
        wordMap.put("java",2);
        wordMap.put("python",3);
        wordMap.put("backend",1);
        wordMap.put("frontend",2);
        wordMap.put("junior",1);
        wordMap.put("senior",2);
        wordMap.put("chicken",1);
        wordMap.put("pizza",2);
        
        List<List<Integer>> scoreList = new ArrayList<>();
        for(int i=0; i<4*3*3*3; i++){
            scoreList.add(new ArrayList<>());
        }

        for(String str : info){
            String[] word = str.split(" ");
            int[] arr = {wordMap.get(word[0])*3*3*3,
                         wordMap.get(word[1])*3*3,
                         wordMap.get(word[2])*3,
                         wordMap.get(word[3]) 
                         };
            int score = Integer.parseInt(word[4]);
            
            for(int i=0; i<(1<<4); i++){ // - 또는 특정값 모든 경우
                int index = 0;
                for(int j=0; j<4; j++){
                    if( (i & (1<<j) )!=0){  //-아니라 특정값을 가질 경우
                        index += arr[j];
                    }
                }
                scoreList.get(index).add(score);
            }   
        }
        
         //점수 기준으로 정렬해야함
          for(int i=0; i<4*3*3*3; i++){
              Collections.sort(scoreList.get(i));
          }

         int[] ans = new int[query.length];
         int idx = 0;
        
         //찾기 -> 이진탐색 필요
         for(String str : query){
             String[] word = str.split(" ");
             int index = wordMap.get(word[0])*3*3*3 
                       + wordMap.get(word[2])*3*3
                       + wordMap.get(word[4])*3
                       + wordMap.get(word[6]);
              
             int score = Integer.parseInt(word[7]);
             int temp = 0;

             if(scoreList.get(index).size()>0){
                  int lowerbound = getLowerBound(scoreList.get(index),score);
             
                 if(scoreList.get(index).get(lowerbound)>=score){
                     temp = scoreList.get(index).size() - lowerbound;
                 }else{
                     temp = 0;
                 }
             }else{
                 temp = 0;
             }
            
             
             ans[idx++] = temp;
         }  
            
        return ans;
    }
    
    //score랑 같은 수가 있으면 그 중 가장 작은 index값
    //score랑 같은 수가 없으면 그것보다 큰 수중 가장 작은 index값 
    private int getLowerBound(List<Integer> list, int score){
        int left = 0;
        int right = list.size()-1;
        
        while(left<right){
            int mid = (left + right)/2;
            if(list.get(mid)<score){ 
                left = mid+1;
            }else{ 
                right = mid;
            }
        }
        
        return right;
    }

    
}
```

<br>

