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

- **연속 할당** => 각각의 프로세스가 메모리의 연속적인 공간에 적재
  
  - 고정분할 방식 (외부조각 / 내부조각)
  
  - 가변분할 방식 (외부조각)
    
    - Hole 발생 => First-Fit / Best-Fit / Worst-Fit 
    
    - Compaction 

- **불연속 할당** => 하나의 프로세스가 메모리 여러 위치에 분산되어 적재
  
  - **Paging**
    
    - 프로그램을 구성하는 주소 공간을 같은 크기의 page로 자른다.
    
    - 물리적인 메모리도 페이지 크기로 나누는데, frame이라고 한다.
    
    - 주소 변환이 페이지 단위로 이루어짐 => page table 필요
  
  - **Segmentation**
    
    - 프로그램의 주소 공간을 의미있는 단위로 자른다 (code, data, stack)
  
  - **Paged Segmentation**

<br>

- **Paging (페이징)**
  
  - page table의 위치? => main memory
  
  - logical address => (page number, offset)
  
  - 메모리 접근 연산에는 page table 접근할때 1번, data/instruction 접근할때 1번, 모두 2번의 접근 필요
  
  - 기존 연속할당 방식에서 MMU가 가지고 있던 Base Register는 Page-table base register(PTBR, page table 가리킴)를 Limit Register는 Page-table length register(PTLR, 테이블 크기 보관) 
  
  - **TLB** (translation look-aside buffer) :속도 향상을 위해 TLB라는 일종의 캐시를 사용 
    
    - 메인메모리보다 접근속도가 빠름
    
    - 자주 사용되는 페이지 테이블의 일부 엔트리들을 캐싱
    
    - 프로세스마다 주소변환 정보가 다름 -> context swiching할때마다 TLB도 flush
  
  - Page Table의 엔트리 => frame, valid/invalid bit / protection bit (read/write , read-only)

<br>

- Multi-Level Paging
  
  - 기존 페이지 테이블은 공간 낭비가 심하다 (프로세스의 전체 메모리 공간 중 일부분만 사용되는데, 페이지 테이블은 중간 에 안쓰는 페이지 정보도 모두 포함)
  
  - Page Table 자체를 Page로 구성
  
  - 사용되지 않는 주소 공간에 대한 outer page table의 엔트리 값은 null (대응하는 inner page table이 없음)

<br>

- Segmentaion 
  
  - 프로세스를 구성하는 주소공간을 의미 단위로 쪼갬
  
  - logical address => (segment-number, offset)
  
  - 페이징 기법과 다르게 세그먼트는 물리적 메모리에서 세그먼트 크기가 다 다름... 따라서 Segment Table의 엔트리는 물리적 메모리의 시작 위치 (base) 와 세그먼트의 길이 (limit)
  
  - 의미단위로 쪼개기 때문에, 공유(sharing)와 보안(protection)에 있어 페이징보다 효과적이다. 


