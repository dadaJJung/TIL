# 운영체제 면접 질문 정리

---

### <br>

### 메모리 관리 관련 용어들

---

- 주소 바인딩
  
  - Compile time binding
    
    - 컴파일 시점에 물리적 메모리 주소가 정해짐
  
  - Load time binding
    
    - 실행시에 비어있는 메모리 위치에 올라갈 수 있음
  
  - Run time binding
    
    - 프로그램 실행 중에도 메모리 주소가 바뀐다
    
    - MMU (하드웨어) 의 지원 필요

<br>

- **MMU** (주소변환을 위한 하드웨어)
  
  - base register / limit register
  
  - logical address를 physical address로 변환해준다

<br>

- **Dynamic Loading**
  
  - Loading : 메모리로 올리는 것
  
  - 프로세스 전체를 메모리에 올리는 게 아니라 필요한 부분만 load

- **Dynamic Linking <-> Static Linking**
  
  - Static Linking : 라이브러리가 컴파일 한 실행파일에 포함됨. 실행 파일의 크기가 크다. 메모리 낭비 (코드 중복)
  
  - Dynamic Linking : 라이브러리가 실행시에 연결(link), 이미 메모리에 있으면 공유해서 사용, 없으면 디스크에서 읽어온다 (운영체제의 도움 필요)
    
    - 윈도우 : dll (dynamic linking library)  / 리눅스 : shared object



<br>

### 물리적 메모리 할당

---

- 연속 할당 => 각각의 프로세스가 메모리의 연속적인 공간에 적재
  
  - 고정분할 방식 (외부조각 / 내부조각)
  
  - 가변분할 방식 (외부조각)
    
    - Hole 발생 => First-Fit / Best-Fit / Worst-Fit 
    
    - Compaction 

- 불연속 할당 => 하나의 프로세스가 메모리 여러 위치에 분산되어 적재
  
  - Paging
    
    - 프로그램을 구성하는 주소 공간을 같은 크기의 page로 자른다.
    
    - 물리적인 메모리도 페이지 크기로 나누는데, frame이라고 한다.
    
    - 주소 변환이 페이지 단위로 이루어짐 => page table 필요
  
  - Segmentation 
    
    - 프로그램의 주소 공간을 의미있는 단위로 자른다 (code, data, stack)
  
  - Paged Segmentation


