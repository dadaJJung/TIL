# [지옥에서온 GIT 🤔](https://www.youtube.com/watch?v=fCY1t3QSEhw&list=PLuHgQVnccGMA8iwZwrGyNXCGy2LAAsTXk&index=6)

> 생활코딩 Git 강의를 학습하면서 정리한 내용입니다. Git,제대로 알고 사용하자 !!!

<br>

### 자주 사용되는 명령어들

- `git init`  : 현재 디렉토리를 버전관리 하겠다는 사실을 git에게 알려주는 명령어. `git init` 명령어를 실행하면, 해당 디렉토리 내부에 `.git ` 디렉토리가 생성된다.

- `git add`  : git에게 어떤 파일을 추적(track) 하라고 알려주는 명령어.
  - 최초로 추적할 때, 또는 수정한 파일 적용하기 위해서 add 명령어를 사용
  - 한개의 커밋에 하나의 작업을 담고 있는것이 이상적이지만, 커밋 시기를 놓쳤을 경우 여러 작업을 담은 하나의 거대한 커밋을 만들어야만 한다.  이때 git은 add라는 작업을 통해 커밋하고자 하는 작업만 선택할 수 있도록 해준다. 
  - `git add .` : 현재 디렉토리 아래의 모든 파일을 add ,  `git add f1.txt` : f1.txt 파일만 add
  - add한 파일은 stage(커밋을 대기하는 곳) 에 올라간다. 
- `git status` : 현재 branch 정보와, git이 관리하는 디렉토리 내부에서 수정된 파일이나 추적되지 않고 있는 파일을 알려준다. `git add` 명령어를 사용해서 트랙킹을 시작한다. 
- `git log` : commit 아이디, commit 메시지나, 시간, 작성한 사람 등의 정보를 확인할 수 있다. 

- `git log -p` : 각각의 commit 사이에 소스코드 차이점이 무엇인지 보여준다. 
- 각각의 커밋버전은  `commit e0aaed7f4eb155794d3a7e52f8bb9067bb5c6f67`  이 모습처럼 고유한 커밋 아이디를 가진다. 
- `git diff XXXX..YYYY` : XXXX 와 YYYY 커밋 사이의 소스코드 차이점을 보여준다. 
- `git diff` : 파일을 수정 후 `git diff` 명령어를 작성하면, 소스코드의 어떠한 부분이 수정되었는지 보여준다. commit 전에 마지막으로 어떤 부분이 수정되었는지 확인할 수 있다. 

- `git reset` : 특정 커밋을 리셋한다. 



<br>

### GISTORY로 GIT의 원리를 학습해보자

#### 1. git add

- `git add` 했을때, add된 파일의 내용은 objects 디렉토리 내부의 특정 위치에 저장되고, 인덱스 파일 안에는 add된 파일명에 매칭되는 이 경로가 저장된다. 

- git은 파일을 저장할 때 파일의 이름이 달라도 내용이 일치하면 objects 디렉토리 내부의 같은 파일 안에 저장된다. 

- add한 파일의 내용에 따라 저장 위치가 결정되는 메커니즘 
  - 내가 아닌 다른사람이 나와 완전히 똑같은 내용을 가진 파일을 add했을 때, 똑같은 이름을 가진 오브젝트 파일을 가리키게 된다. 
- 즉, 깃은 우리가 저장한 파일의 내용을 `sha-1` 이라고 하는 해쉬 알고리즘을 통과시켜서 어떠한 값을 도출한다. 그리고 그 값을 사용해서 objects 파일 아래의 특정 경로에 파일 내용을 저장한다. 
- 예제
  - 내가 만든 파일 :  f1.txt (hello),   다른 사람이 만든 파일 : f2.txt (hello) 일 때
  - hello -> (sha-1) -> aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d
  - objects/aa/f4c61ddcc5e8a2dabede0f3b482cd9aea9434d 위 경로의 파일에 hello라는 내용이 저장된다.
  - 인덱스 파일안에 add된 파일이 가리키는 오브젝트 정보가 저장된다
    - f1.txt.  aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d
    - f2.txt   aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d

#### 2. git commit

- 커밋 메시지도 파일 내용처럼 objects라는 디렉토리 안에 오브젝트 파일로 저장됨
  - 오브젝트 파일 안에는 여러 커밋 정보가 들어있다 ( 이전 커밋 정보(parent), 커밋을 작성한 사람의 정보, 커밋 메세지, 커밋된 파일 정보(tree) )
  - tree에는 커밋된 파일의 이름과 파일에 담겨있는 내용을 가지고 있는 오브젝트의 링크 정보가 저장되어 있다.
  - 각각의 커밋버전은 그 버전이 만들어진 시점의 스냅샷을 tree라는 구조를 통해 가지고 있다.
- objects 디렉토리 안에 들어가는 파일은 오브젝트이다. 이 오브젝트 파일은 크게 3가지 중 하나이다
  - 파일의 내용을 담고 있는 blob
  - 어떤 디렉토리 또는 파일명과, 그 파일의 내용 오브젝트를 링크로 가지고 있는 tree
  - 커밋 정보를 담고 있는 commit

#### 3. index파일과 status

- `git status` 명령어를 사용하면 현재 수정되었지만 add되지 않은 파일이 무엇인지 알려준다
  - 깃은 내부적으로 인덱스에 있는 정보와 현재 파일 내용이 일치하지 않을 경우 파일이 수정되었음을 알려준다. 
  - 변경한 파일을 add 했을 경우, 오브젝트가 생성되고, 인덱스도 변경된다. 
  - add 후 다시 `git status` 명령어를 입력하면, `Changes to be committed:` 라는 메시지가 뜬다.
  - 깃은 인덱스에 있는 정보와 (add된 내용이 반영됨), 마지막 커밋 정보가 일치하지 않을 경우, 커밋될 파일이 있다는 사실을 알려준다

<br>

### Branch

- 브랜치 생성 => `git branch exp`
- 브랜치 변경 => `git checkout exp`
- 현재 브랜치 정보 => `git branch`

- [깃 브랜치 전략](https://www.youtube.com/watch?v=jeaf8OXYO1g) 
- 
- 
- branch 정보확인 부터 다시..

