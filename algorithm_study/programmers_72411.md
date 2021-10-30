

### [2021 KAKAO BLIND RECRUITMENT](https://programmers.co.kr/learn/challenges) > [메뉴리뉴얼](https://programmers.co.kr/learn/courses/30/lessons/72411)

<br>

<b>문제</b>

---

레스토랑을 운영하던 `스카피`는 코로나19로 인한 불경기를 극복하고자 메뉴를 새로 구성하려고 고민하고 있습니다.
기존에는 단품으로만 제공하던 메뉴를 조합해서 코스요리 형태로 재구성해서 새로운 메뉴를 제공하기로 결정했습니다. 어떤 단품메뉴들을 조합해서 코스요리 메뉴로 구성하면 좋을 지 고민하던 "스카피"는 이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.
단, 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성하려고 합니다. 또한, 최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함하기로 했습니다.

예를 들어, 손님 6명이 주문한 단품메뉴들의 조합이 다음과 같다면,
(각 손님은 단품메뉴를 2개 이상 주문해야 하며, 각 단품메뉴는 A ~ Z의 알파벳 대문자로 표기합니다.)

| 손님 번호 | 주문한 단품메뉴 조합 |
| --------- | -------------------- |
| 1번 손님  | A, B, C, F, G        |
| 2번 손님  | A, C                 |
| 3번 손님  | C, D, E              |
| 4번 손님  | A, C, D, E           |
| 5번 손님  | B, C, F, G           |
| 6번 손님  | A, C, D, E, H        |

가장 많이 함께 주문된 단품메뉴 조합에 따라 "스카피"가 만들게 될 코스요리 메뉴 구성 후보는 다음과 같습니다.

| 코스 종류     | 메뉴 구성  | 설명                                                 |
| ------------- | ---------- | ---------------------------------------------------- |
| 요리 2개 코스 | A, C       | 1번, 2번, 4번, 6번 손님으로부터 총 4번 주문됐습니다. |
| 요리 3개 코스 | C, D, E    | 3번, 4번, 6번 손님으로부터 총 3번 주문됐습니다.      |
| 요리 4개 코스 | B, C, F, G | 1번, 5번 손님으로부터 총 2번 주문됐습니다.           |
| 요리 4개 코스 | A, C, D, E | 4번, 6번 손님으로부터 총 2번 주문됐습니다.           |

각 손님들이 주문한 단품메뉴들이 문자열 형식으로 담긴 배열 orders, "스카피"가 `추가하고 싶어하는` 코스요리를 구성하는 단품메뉴들의 갯수가 담긴 배열 course가 매개변수로 주어질 때, "스카피"가 새로 추가하게 될 코스요리의 메뉴 구성을 문자열 형태로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.

<br>

<b>제한사항</b>

---

- orders 배열의 크기는 2 이상 20 이하입니다.
- orders 배열의 각 원소는 크기가 2 이상 10 이하인 문자열입니다.
  - 각 문자열은 알파벳 대문자로만 이루어져 있습니다.
  - 각 문자열에는 같은 알파벳이 중복해서 들어있지 않습니다.
- course 배열의 크기는 1 이상 10 이하입니다.
  - course 배열의 각 원소는 2 이상 10 이하인 자연수가 `오름차순`으로 정렬되어 있습니다.
  - course 배열에는 같은 값이 중복해서 들어있지 않습니다.
- 정답은 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 `오름차순` 정렬해서 return 해주세요.
  - 배열의 각 원소에 저장된 문자열 또한 알파벳 `오름차순`으로 정렬되어야 합니다.
  - 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다.
  - orders와 course 매개변수는 return 하는 배열의 길이가 1 이상이 되도록 주어집니다.

<br>

<b>입출력 예</b>

---

| orders                                              | course  | result                              |
| --------------------------------------------------- | ------- | ----------------------------------- |
| `["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]`   | [2,3,4] | `["AC", "ACDE", "BCFG", "CDE"]`     |
| `["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"]` | [2,3,5] | `["ACD", "AD", "ADE", "CD", "XYZ"]` |
| `["XYZ", "XWY", "WXA"]`                             | [2,3,4] | `["WX", "XY"]`                      |

<br>

<b>풀이 </b>

---

> level2 문제라서 쉽게 풀릴 줄 알았는데, 구현하는데 생각보다 너무 힘들었다..ㅜㅜㅜ. 처음에 문제를 제대로 이해하지 못해서 고생을 좀 했다. 나는 2명 이상의 손님에게 선택된 모든 단품메뉴 조합을 리턴해야 한다고 생각했다. 하지만 문제를 제대로 읽어보면 `최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함하기로...` 라고 되어있는데, 이 말은 2명 이상의 손님에게 선택된 단품메뉴 조합은 메뉴 후보가 될 수 있는 것이지 최종 메뉴로 선택되는 건 아니라는 뜻이다. 그렇다면 최종 코스 메뉴로 선정될 수 있는 단품메뉴 조합은 무엇일까??  바로 코스에 포함되는 단품메뉴 갯수가 매개변수로 주어질 때, 그 갯수만큼의 단품메뉴 조합 중에서 가장 많이 선택된 조합이 코스메뉴로 선정되는 것이다. (`각 손님들이 주문할 때 가장 많이 함께 주문한 단품메뉴들을 코스요리 메뉴로 구성하기로 했습니다.`) 만약 매개변수로 주어진 단품메뉴 갯수를 만족하고, 손님들에게 선택된 횟수도 동일한 여러개의 조합이 존재할 경우에는 모두 코스요리로 선정 (`만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다`) 복잡쓰.. 
>
> 첫번째로 작성한 코드 너무 복잡한 것 같다.. 다른 사람들 풀이도 참고해서 다시 한번 풀어봐야겠다.

<br>

<b>해결 포인트</b>

---

- 사용자가 함께 주문한 단품요리 조합 중에서 나올 수 있는 모든 조합을 구한다.
- 이 때 매개변수로 주어지는 코스요리를 구성하는 단품메뉴들의 갯수에 해당하는 조합만 걸러내서 map에 담는다
  - key : 단품메뉴들의 조합 / value : 이 단품메뉴 조합을 선택한 손님의 수

- 이제 위 map에서 같은 길이의 단품메뉴조합 중에서 가장 많은 손님이 선택한 조합을 다시 한번 걸러낸다.
- 예를 들어 코스요리를 구성하는 단품메뉴들의 갯수로 2가 주어졌을 때 (메뉴A, 메뉴B : 2명의 손님이 선택), (메뉴A, 메뉴C : 3명의 손님이 선택) 두가지 모두 코스요리 후보가 될 수 있다. 하지만 (A,C)의 조합이 더 많은 손님(3명)이 선택했기 때문에, 두 조합 중에서 (A,C)가 최종 코스요리로 선정된다.
- 만약 (A,B: 3명의 손님이 선택), (A,C : 3명의 손님이 선택), (D,E: 2명의 손님이 선택) 이런식으로 가장 많이 함께 주문된 메뉴 구성이 여러개라면 (A,B), (A,C) 모두 최종 코스 요리로 선정된다. 

<br>

<b>전체코드 - version1</b>

```java
import java.util.*;

class Solution {
    
    private HashMap<String,Integer> map = new HashMap<>();  
    private HashSet<Integer> set = new HashSet<>();
    int maxFoodCnt;  //코스요리를 구성하는 단품메뉴들의 갯수 중 최댓값
    
    public String[] solution(String[] orders, int[] course) {
        
        maxFoodCnt = course[course.length -1];
        for(int i=0; i<course.length; i++) set.add(course[i]);
        
        for(int i=0; i<orders.length; i++){
            String menus = orders[i];
            char[] arrMenus = menus.toCharArray();
           
            //정렬을 하지 않을경우 같은 메뉴 조합에 대해서도 다른 조합으로 간주될수 있어서.. 예)(A,C)(C,A)
            Arrays.sort(arrMenus);  
          
            makeCourse(arrMenus,0,"");
        }
        
        
        //***** 가장 많은 손님에게 선택된 메뉴 구성을 걸러내기 위한 장치 *****
        
        //1.
        //hotCourse[i]
      	//코스에 포함될 단품메뉴 갯수가 i인 조합중에서, 가장많은 손님에게 선택된 메뉴조합 정보 기록 
        //복수개일 수 있기 때문에 단순 배열이 아닌 리스트 배열로 구성
        ArrayList<String>[] hotCourse = new ArrayList[11];  

      	//2.
        //selectedCnt[i] = j; 
      	//코스에 포함될 단품메뉴 갯수가 i인 조합중에서, 가장많은 손님에게 선택된(j) 
      	int[] selectedCnt = new int[11]; 
        
      	//**********
      
        
        //key : 코스
        //value(map.get(key)) : 코스가 선택된 수
        for(String key : map.keySet()){
            if(map.get(key)<2) continue;  //2명이상에게 선택되지 않으면 패스
          
            int len = key.length();  //코스에 포함될 단품메뉴 갯수
          
            if(selectedCnt[len]==0){  //초기값 셋팅
                selectedCnt[len] = map.get(key);
                hotCourse[len] = new ArrayList<>();
                hotCourse[len].add(key);
            }else{
                if(selectedCnt[len] > map.get(key)) continue; //기존값보다 인기없는 구성 패스
              
                if(selectedCnt[len] < map.get(key)){ 
                    selectedCnt[len] = map.get(key);
                    hotCourse[len].clear();
                }
  	            hotCourse[len].add(key);
            }
        }
        
      
        //최종 선정된 코스 정보를 담을 리스트
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<course.length; i++){
            int cnt = course[i];
            if(hotCourse[cnt]==null) continue;
            for(String str :  hotCourse[cnt]){
                list.add(str);
            }
        }
        
        //알파벳기준 정렬
        Collections.sort(list);
        
        String[] ret = new String[list.size()];
        for(int i=0; i<ret.length; i++){
            ret[i] = list.get(i);
        }
        return ret;
        
    }
    
    
  private void makeCourse(char[] arrMenus,int index,String combination){
     
    //코스요리를 구성하는 단품메뉴들의 최댓값을 넘길 경우 -> 더 이상 필요없는 조합 -> return 
    if(combination.length() > maxFoodCnt) return;

    if(index == arrMenus.length){
      if(set.contains(combination.length())){ //매개변수로 주어진 단품메뉴 갯수와 같을 때만 
        if(!map.containsKey(combination)) map.put(combination,1);
        else map.put(combination, map.get(combination)+1);
      }
      return;
    }

    makeCourse(arrMenus,index+1,combination+arrMenus[index]);
    makeCourse(arrMenus,index+1,combination);
  } 
    
}
```

<br>

