# [지옥에서온 GIT 🤔](https://www.youtube.com/watch?v=fCY1t3QSEhw&list=PLuHgQVnccGMA8iwZwrGyNXCGy2LAAsTXk&index=6)

> 생활코딩 Git 강의를 학습하면서 정리한 내용입니다. Git,제대로 알고 사용하자 !!!

<br>

- `git init`  : 현재 디렉토리를 버전관리 하겠다는 사실을 git에게 알려주는 명령어. 

- `git init` 명령어를 실행하면, 해당 디렉토리 내부에 `.git ` 디렉토리가 생성된다.

- `git add`  : git에게 어떤 파일을 추적(track) 하라고 알려주는 명령어.
  - 최초로 추적할 때, 또는 수정한 파일 적용하기 위해서 add 명령어를 사용
  - 커밋 하나에 하나의 작업을 담고 있는것이 이상적이지만, 커밋 시기를 놓쳤을 경우 여러 작업을 담은 하나의 커밋을 만들어야만 한다.  이때 git은 add라는 작업을 통해 커밋하고자 하는 작업만 선택적으로 커밋할 수 있도록 해준다.  
  - `git add .` : 현재 디렉토리 아래의 모든 파일을 add / `git add f1.txt` : f1.txt 파일만 add
  - add한 파일은 stage(커밋을 대기하는 곳) 에 올라간다. 
- `git status` : 현재 branch 정보와, git이 관리하는 디렉토리 내부에서 수정된 파일이나 추적되지 않고 있는 파일을 알려준다. `git add` 명령어를 사용해서 트랙킹을 시작한다. 
- `git log` : commit 아이디, commit 메시지나, 시간, 작성한 사람 등의 정보를 확인할 수 있다. 

- `git log -p` : 각각의 commit 사이에 소스코드 차이점이 무엇인지 보여준다. 
- 각각의 커밋버전은  `commit e0aaed7f4eb155794d3a7e52f8bb9067bb5c6f67`  이 모습처럼 고유한 커밋 아이디를 가진다. 
- `git diff XXXX..YYYY` : XXXX 와 YYYY 커밋 사이의 소스코드 차이점을 보여준다. 
- `git diff` : 파일을 수정 후 `git diff` 명령어를 작성하면, 소스코드의 어떠한 부분이 수정되었는지 보여준다. commit 전에 마지막으로 어떤 부분이 수정되었는지 확인할 수 있다. 



- git reset 부터 



