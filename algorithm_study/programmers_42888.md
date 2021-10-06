

### [2019 KAKAO BLIND RECRUITMENT](https://programmers.co.kr/learn/challenges) > [오픈채팅방](https://programmers.co.kr/learn/courses/30/lessons/42888#)

<br>

<b>문제</b>

---

카카오톡 오픈채팅방에서는 친구가 아닌 사람들과 대화를 할 수 있는데, 본래 닉네임이 아닌 가상의 닉네임을 사용하여 채팅방에 들어갈 수 있다.

신입사원인 김크루는 카카오톡 오픈 채팅방을 개설한 사람을 위해, 다양한 사람들이 들어오고, 나가는 것을 지켜볼 수 있는 관리자창을 만들기로 했다. 채팅방에 누군가 들어오면 다음 메시지가 출력된다.

"[닉네임]님이 들어왔습니다."

채팅방에서 누군가 나가면 다음 메시지가 출력된다.

"[닉네임]님이 나갔습니다."

채팅방에서 닉네임을 변경하는 방법은 다음과 같이 두 가지이다.

- 채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
- 채팅방에서 닉네임을 변경한다.

닉네임을 변경할 때는 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경된다.

예를 들어, 채팅방에 "Muzi"와 "Prodo"라는 닉네임을 사용하는 사람이 순서대로 들어오면 채팅방에는 다음과 같이 메시지가 출력된다.

"Muzi님이 들어왔습니다."
"Prodo님이 들어왔습니다."

채팅방에 있던 사람이 나가면 채팅방에는 다음과 같이 메시지가 남는다.

"Muzi님이 들어왔습니다."
"Prodo님이 들어왔습니다."
"Muzi님이 나갔습니다."

Muzi가 나간후 다시 들어올 때, Prodo 라는 닉네임으로 들어올 경우 기존에 채팅방에 남아있던 Muzi도 Prodo로 다음과 같이 변경된다.

"Prodo님이 들어왔습니다."
"Prodo님이 들어왔습니다."
"Prodo님이 나갔습니다."
"Prodo님이 들어왔습니다."

채팅방은 중복 닉네임을 허용하기 때문에, 현재 채팅방에는 Prodo라는 닉네임을 사용하는 사람이 두 명이 있다. 이제, 채팅방에 두 번째로 들어왔던 Prodo가 Ryan으로 닉네임을 변경하면 채팅방 메시지는 다음과 같이 변경된다.

"Prodo님이 들어왔습니다."
"Ryan님이 들어왔습니다."
"Prodo님이 나갔습니다."
"Prodo님이 들어왔습니다."

채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어질 때, 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return 하도록 solution 함수를 완성하라.

<br>

<b>제한사항</b>

---

- record는 다음과 같은 문자열이 담긴 배열이며, 길이는 `1` 이상 `100,000` 이하이다.
- 다음은 record에 담긴 문자열에 대한 설명이다.
  - 모든 유저는 [유저 아이디]로 구분한다.
  - [유저 아이디] 사용자가 [닉네임]으로 채팅방에 입장 - "Enter [유저 아이디] [닉네임]" (ex. "Enter uid1234 Muzi")
  - [유저 아이디] 사용자가 채팅방에서 퇴장 - "Leave [유저 아이디]" (ex. "Leave uid1234")
  - [유저 아이디] 사용자가 닉네임을 [닉네임]으로 변경 - "Change [유저 아이디] [닉네임]" (ex. "Change uid1234 Muzi")
  - 첫 단어는 Enter, Leave, Change 중 하나이다.
  - 각 단어는 공백으로 구분되어 있으며, 알파벳 대문자, 소문자, 숫자로만 이루어져있다.
  - 유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별한다.
  - 유저 아이디와 닉네임의 길이는 `1` 이상 `10` 이하이다.
  - 채팅방에서 나간 유저가 닉네임을 변경하는 등 잘못 된 입력은 주어지지 않는다.

<br>

<b>입출력 예</b>

---

| record                                                       | result                                                       |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]` | `["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]` |

<br>

<br>

<b>풀이</b>

---

- 첫번째 버전 해결포인트
  - 아이디와 닉네임을 key-value로 가지는 자료구조 사용. 아이디와 대응되는 닉네임 정보를 보관한다. 채팅방 방문 기록을 아이디로 기록하고 있다가, 나중에 아이디에 대응되는 닉네임 값을 변경해서 출력 (HashMap 사용)
    - 이렇게 하는 이유는, 유저가 사용하는 닉네임이 바뀌면 유저의 모든 기록에서 닉네임을 변경해야 해서, 유저의 최신 닉네임 값을 유지하고 있다가 나중에 모두 변경해주면 되기 때문.
  - 채팅방 기록은 유저가 들어온 기록, 나간 기록을 모두 기록해야 한다, 따라서 유저아이디와 유저액션(Enter or Leave)에 대한 정보를 함께 기록했다. (ArrayList 사용)

```java
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        HashMap<String,String> map = new HashMap<>();
        ArrayList<User> list = new ArrayList<>();
        
        for(int i=0; i<record.length; i++){
            //parsing
            String[] info =  record[i].split(" ");
            String action = info[0];
            String userId = info[1];
            
            if(action.equals("Enter")){
                map.put(userId,info[2]);
                list.add(new User(userId,action));
            }else if(action.equals("Change")){
                map.put(userId,info[2]);
            }else{  //leave
                list.add(new User(userId,action));
            }
            
        }//for
        
        String[] result = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            User user = list.get(i);
            StringBuilder sb = new StringBuilder(map.get(user.userId));
            if(user.action.equals("Enter")){
                sb.append("님이 들어왔습니다.");
            }else{
                sb.append("님이 나갔습니다.");
            }
            
            result[i] = sb.toString();
        }//for
        
        
        return result;
    }
}


class User{
    String userId;
    String action;
    User(String userId, String action){
        this.userId = userId;
        this.action = action;
    }
}
```

<br>

