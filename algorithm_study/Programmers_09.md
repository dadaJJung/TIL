2021 카카오 채용연계형 인턴십 > [거리두기 확인하기](https://programmers.co.kr/learn/courses/30/lessons/81302)

---

<br>

### 문제 설명

***

개발자를 희망하는 죠르디가 카카오에 면접을 보러 왔습니다.

코로나 바이러스 감염 예방을 위해 응시자들은 거리를 둬서 대기를 해야하는데 개발 직군 면접인 만큼
아래와 같은 규칙으로 대기실에 거리를 두고 앉도록 안내하고 있습니다.

> 1. 대기실은 5개이며, 각 대기실은 5x5 크기입니다.
> 2. 거리두기를 위하여 응시자들 끼리는 맨해튼 거리[1](https://programmers.co.kr/learn/courses/30/lessons/81302#fn1)가 2 이하로 앉지 말아 주세요.
> 3. 단 응시자가 앉아있는 자리 사이가 파티션으로 막혀 있을 경우에는 허용합니다.

예를 들어,

| ![PXP.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/8c056cac-ec8f-435c-a49a-8125df055c5e/PXP.png) | ![PX_XP.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/d611f66e-f9c4-4433-91ce-02887657fe7f/PX_XP.png) | ![PX_OP.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/ed707158-0511-457b-9e1a-7dbf34a776a5/PX_OP.png) |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| 위 그림처럼 자리 사이에 파티션이 존재한다면 맨해튼 거리가 2여도 거리두기를 **지킨 것입니다.** | 위 그림처럼 파티션을 사이에 두고 앉은 경우도 거리두기를 **지킨 것입니다.** | 위 그림처럼 자리 사이가 맨해튼 거리 2이고 사이에 빈 테이블이 있는 경우는 거리두기를 **지키지 않은 것입니다.** |
| ![P.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/4c548421-1c32-4947-af9e-a45c61501bc4/P.png) | ![O.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/ce799a38-668a-4038-b32f-c515b8701262/O.png) | ![X.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/91e8f98b-baeb-4f81-8cb6-5bafebebdcc7/X.png) |
|          응시자가 앉아있는 자리(`P`)를 의미합니다.           |                 빈 테이블(`O`)을 의미합니다.                 |                  파티션(`X`)을 의미합니다.                   |

5개의 대기실을 본 죠르디는 각 대기실에서 응시자들이 거리두기를 잘 기키고 있는지 알고 싶어졌습니다. 자리에 앉아있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열 `places`가 매개변수로 주어집니다. 각 대기실별로 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 배열에 담아 return 하도록 solution 함수를 완성해 주세요.

<br>

### 제한사항

***

- `places`의 행 길이(대기실 개수) = 5
  - `places`의 각 행은 하나의 대기실 구조를 나타냅니다.
- `places`의 열 길이(대기실 세로 길이) = 5
- `places` 의 원소는 `P`,`O`,`X` 로 이루어진 문자열입니다.
  - `places` 원소의 길이(대기실 가로 길이) = 5
  - `P`는 응시자가 앉아있는 자리를 의미합니다.
  - `O`는 빈 테이블을 의미합니다.
  - `X`는 파티션을 의미합니다.
- 입력으로 주어지는 5개 대기실의 크기는 모두 5x5 입니다.
- return 값 형식
  - 1차원 정수 배열에 5개의 원소를 담아서 return 합니다.
  - `places`에 담겨 있는 5개 대기실의 순서대로, 거리두기 준수 여부를 차례대로 배열에 담습니다.
  - 각 대기실 별로 모든 응시자가 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 담습니다.

<br>

### 입출력 예

***

| places                                                       | result          |
| ------------------------------------------------------------ | --------------- |
| `[["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]` | [1, 0, 1, 1, 1] |

<br>

### 풀이 - version1 

***

- 대기실(5x5)을 돌면서 사람이 앉아있을 경우에만 (`P`) 그 주변으로 거리두기를 지키고 있는지 탐색함. 거리가 1인 경우는 무조건 거리두기를 지키지 않았기 때문에 false를 리턴하게 했고, 맨해튼 거리가 2인 경우에는 특정 조건을 만족할 경우 (중간에 파티션 없고 책상이 있을경우) false를 리턴하게 했다. 테스트케이스를 모두 통과하긴 했는데, 조건문 작성하는 부분이 너무 지저분한 것 같다. 
- 이 때 P를 주변으로 상하좌우를 모두 탐색하지 않고 위, 오른쪽, 아래만 탐색하도록 했다. 왜냐하면 왼쪽은 그 전 P에서 오른쪽을 탐색할 때 중복될 수 있기 때문이다. 

<br>

```java
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++){
            char[][] arr = new char[5][5];
            for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    arr[j][k] = places[i][j].charAt(k);
                }
            }
            
            answer[i] = check(arr)?1:0;
            
        }
        
        return answer;
        
    }
    
    private boolean check(char[][] arr){
        
         for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(arr[i][j]!='P') continue;
                
                //맨해튼거리 1일때 -> 무조건 false
                if( (i-1>=0&&arr[i-1][j]=='P') || (j+1<5&&arr[i][j+1]=='P') || (i+1<5&&arr[i+1][j]=='P')) return false;
                
                //맨해튼거리 2일때 - 일직선
                if(i-2>=0 && arr[i-2][j]=='P' && arr[i-1][j]=='O') return false;
                if(i+2<5 && arr[i+2][j]=='P' && arr[i+1][j]=='O') return false;
                if(j+2<5 && arr[i][j+2]=='P' && arr[i][j+1]=='O') return false;
                
                //맨해튼거리 2일때 - 대각선
                if(i-1>=0 && j+1<5 && arr[i-1][j+1]=='P' && (arr[i-1][j]=='O'|| arr[i][j+1]=='O')) return false;
                if(i+1<5 && j+1<5 && arr[i+1][j+1]=='P' && (arr[i][j+1]=='O' || arr[i+1][j]=='O')) return false;
                
            }
         }
        
        return true;
        
    }
    
    
}
```

<br>

<br>

### 풀이 - version2 

***

- 위 풀이보다 좀 더 간단한 방법을 생각해보았다! 

- 맨해튼거리가 1인 경우는 응시자 P를 기준으로 상하좌우에 다른 응시자가 한명이라도 존재한다면 거리두기 위반이다. 

- 맨해튼거리가 2인 경우는 맨해튼거리가 1인 경우보다 조건이 좀 더 복잡하다. 

  - 맨해튼거리가 직선으로 2인 경우 : 응시자와 응시자 사이에 파티션 X가 있을 경우에는 맨해튼거리가 2이지만 거리두기가 인정된다.

  - 맨해튼거리가 대각선으로 2인 경우 : 아래 예시처럼 대각선 주변으로 모두 파티션이 있을 경우에는 거리두기가 인정된다. 

    예) X P

    ​     P X

- 위 설명처럼 맨해튼거리가1인 경우는 응시자 P를 기준으로 잡고 상하좌우를 살펴서 다른 응시자가 한명이라도 존재하면 false를 리턴하면 된다. 문제는 맨해튼 거리가 2인 경우인데, 이 경우에는 응시자 P를 기준으로 삼지 않고 빈테이블을 기준으로 상하좌우를 탐색하면 쉽게 해결된다. 빈테이블을 기준으로 상하좌우를 살펴서 응시자가 2명이상 있다면 실패이다. 
- 즉 파티션은 건너뛰고, 응시자 P 또는 빈테이블 O가 나왔을 경우에만 상하좌우를 살펴 검증하면 된다. 

<br>

```java
class Solution {
    public int[] dx = {-1,0,1,0};
    public int[] dy = {0,-1,0,1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++){
           answer[i] = check(places[i]);
        }
        
        return answer;
    }
    
    private int check(String[] place){
        
         for(int i=0; i<5; i++){
             for(int j=0; j<5; j++){
                 char cur = place[i].charAt(j);
                 if(cur=='X') continue; //P 또는 O일 때만 검사
                 int sum = (cur=='P')?1:0; //P를 기준으로 시작할 때는 1명을 카운트하고 시작
                 
                 //상하좌우 네방향 검사
                 for(int k=0; k<4; k++){
                     int px = i+dx[k];
                     int py = j+dy[k];
                     if(px<0 || px>=5 || py<0 || py>=5) continue;  //범위벗어나면 패스
                     char adj = place[px].charAt(py);
                     if(adj=='P') sum++;
                 }
                 
                 if(sum>=2) return 0;  //주변에 P가 2이상 있을경우 이 대기실은 거리두기 위반
                 
             }
         } 
        
        return 1;  
    }
    
    
}
```

