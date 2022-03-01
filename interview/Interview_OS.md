# 운영체제 면접 질문 정리

---

<br>

### <mark>Cpu Scheduling</mark>

---

- 

<br>

### <mark>Process Synchronization (프로세스 동기화)</mark>

---

- 

<br>

### <mark>Deadlocks</mark>

---

- 

<br>

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

### 물리적 메모리 관리

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
  
  - <u>**TLB** (translation look-aside buffer) : 주소변환 속도 향상을 위해 TLB라는 일종의 캐시를 사용 </u>
    
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

- **Segmentaion**
  
  - 프로세스를 구성하는 주소공간을 의미 단위로 쪼갬
  
  - logical address => (segment-number, offset)
  
  - 페이징 기법과 다르게 세그먼트는 물리적 메모리에서 세그먼트 크기가 다 다름. 따라서 Segment Table의 엔트리는 물리적 메모리의 시작 위치 (base) 와 세그먼트의 길이 (limit) 를 가진다. 
  
  - 의미단위로 쪼개기 때문에, 공유(sharing)와 보안(protection)에 있어 페이징보다 효과적이다. 

<br>

- <u>물리적인 메모리 주소변환은 운영체제가 관여하지 않는다</u> / 운영체제가 메모리관리에서 하는 일 ? => 메인메모리에 올라와있지 않은 페이지가 요청될때 disk I/O, 메인메모리가 가득차있을 때 어떤 페이지를 swap area로 보낼지 결정 (page replacement)

<br>

### <mark>Virtual Memory (가상메모리)</mark>

---

- **Demand Paging (요구페이징)**
  
  - 요청이 있을 때 페이지를 메모리에 올림
  
  - page table에 valid/invalid bit 을 저장 (v: 메모리에 올라와있는 페이지 / i: 메모리에 올라오지 않은 페이지, 사용하지 않는 주소 영역의 페이지 )
  
  - invalid 페이지에 대해 요청이 들어옴 (page fault) => page fault trap => CPU가 운영체제에게 넘어간다 => page fault handler. 운영체제에서 필요한 페이지를 메모리로 올려준다.  

<br>

- Page replacement 와 replacement algorithm
  
  - 메인메모리 프레임이 모두 꽉 차있을 때, 어떤 페이지를 쫓아낼지 (victim) 결정
  
  - 곧바로 사용되지 않을 페이지를 쫓아내는 것이 좋음
  
  - replacement algorithm => page fault rate 을 최소화하게 페이지 교체하는 것이 목표
    
    - Optimal : 가장 먼 미래에 참조되는 페이지를 replace
    
    - FIFO : 가장 먼저 들어온 페이지를 replace
    
    - <mark>LRU</mark> (Least Recently Used): 가장 오래전에 참조된 페이지를 replace
      
      - 구현방법 : 메모리에 올라온 페이지들을 참조 순서에 따라 줄세우기. 어떤 페이지가 다시 참조되면 그 노드를 가장 끝으로 보낸다  =><mark> Doubly Linked List 사용 O(1)</mark>
    
    - <mark>LFU</mark> (Least Frequently Used) : 가장 적게 참조된 페이지를 replace 
      
      - 구현방법 : LinkedList로 가장 적게 참조된 노드부터 가장 많이 참조된 노드를 연결. 중간에 있는 노드가 다시 참조된다면 그 노드부터 더 많이 참조된 노드를 선형으로 탐색해야 함 => O(n) /  따라서, LFU 알고리즘 <mark>min-heap</mark>으로 구현한다. head으로 구현할 경우 페이지가 다시 참조되어 참조횟수가 증가하면 선형으로  n만큼 탐색하지 않고 이진트리에서 최대 높이 logn만큼 탐색 => <mark>O(logN)</mark>
    
    - <mark>Clock Algorithm</mark>
      
      - 근사 LRU알고리즘 
      
      - Second Chance Algorithm,  NRU (Not Recently Used) 등으로도 불림
      
      - **reference bit** 사용 (page table에 저장)
        
        - 초기에는 모두 0, 프로세스로부터 참조되면 1로 변환. 이 상태에서 page replacement가 진행되면 시계바늘처럼 한 방향으로 페이지를 탐색. 1bit를 만나면 0bit로 바꾸고 지나가고 bit을 만나면 그 페이지를 메모리에서 쫓아낸다. 
        
        - 즉, 1->0으로 변환하면서 돌아올 동안 다시 참조되지 않은 페이지 (0)  가 있다면 쫓아낸다 (가장 오랫동안 참조되지 않은 페이지일수는 있어도, 가장 최근에 참조된 페이지는 아님, 근사 LRU)
      
      - clock algorithm을 사용해서 메인메모리에서 쫓아낼 페이지를 선택 => 하지만 이 페이지에 변경 사항이 있다면 바로 쫓아낼 수 없음. 변경된 내용을 반영해야 한다. 이러한 테이블을 체크할 수 있도록 page table에 dirty bit을 추가한다.  

<br>

- page table은  (frame num, valid bit, auth, reference bit, dirty bit)의 애트리뷰트를 가진다. 

<br>

- **Paging System에서 LRU, LFU 알고리즘 사용이 가능한가?=> No!**
  
  - CPU가 논리 주소를 바라보면, 페이지 테이블을 참조해서 논리주소가 물리주소로 변환. 근데 만약 해당 페이지가 메인메모리에 올라와있지 않다면? CPU제어가 운영체제에게 넘어가고 운영체제가 보조기억장치에서 그 페이지를 찾아서 메인메모리로 올려줘야 한다. 이때 메인메모리에 공간이 없다면 어떤 페이지를 쫓아내야할지 정해야함. 이때 LRU나 LFU 알고리즘을 사용. 예를 들어 운영체제가 LRU알고리즘을 사용한다고 할때 가장 오래전에 참조된 페이지를 찾아야 함. 그런데, 과연 운영체제가 가장 오래전에 참조된 페이지를 알수 있나?? -> 알수없다!.. 왜냐하면 요청한 페이지가 이미 메모리에 올라와있다면 운영체제에게 CPU 제어권이 넘어가지 않는다. (하드웨어적으로 주소변환을 하기 때문) => 따라서 페이징 시스템에서 운영체제는 페이지들이 참조된 정보들을 반쪽만 알게된다. (메모리에 이미 해당 페이지가 있으면 운영체제는 그 페이지가 참조된 시점을 알지 못하고, 메모리에 없을 때만 CPU제어권이 운영체제에게 넘어가서 알수있게 된다 ) => 따라서 페이징 시스템에서는 LRU, LFU 알고리즘을 사용할 수 없다. 대신 Clock Algorithm 사용

<br>

- **<mark>Thrashing</mark>**
  
  - 프로세스의 수행에 필요한 최소한의 프레임을 할당해주지 않아서 page fault가 빈번하게 발생하는 경우. cpu utilization이 낮아진다. 
  
  - degree of multiprogramming이 높아질때, 어느 정도까지는 cpu utilization이 올라가다가, 어떤 포인트부터 cpu utilization이 급감한다. 왜일까? 너무 많은 프로세스가 메인 메모리에 올라오면 각각의 프로세스가 CPU를 할당받아 작업할 때 필요한 최소한의 페이지가 없기때문에 매번 page fault가 발생한다. 다른 프로세스에게 cpu가 넘어가도 또 page fault 발생. cpu는 할일이 없어서 쉬게 되면 운영체제는 더 많은 프로세스를 메모리에 올려야 된다고 생각.. 악순환 반복 => Thrashing
  
  - working-set model
    
    - 프로세스에서 집중적으로 참조되는 page들의 집합
    
    - working-set이 한꺼번에 메모리에 올라가거나 아니면 메모리를 반납하도록 해서 thrashing 발생을 줄인다.
  
  - PFF(Page-Fault Frequency) Scheme
    
    - page fault rate의 상한값과 하한값을 둔다
    
    - page fault rate이 상한값을  넘으면 => frame 더 할당 / page fault rate이 하한값 이하이면 = frame수를 줄인다.

<br>

### <mark>File System</mark>

---

- File
  
  - A named collection of related information
  
  - 일반적으로 비휘발성으로 보조기억장치에 저장
  
  - 관련 연산 : create, delete, read, write, open, close, reposition (lseek)

- <mark>File System</mark>
  
  - <mark>운영체제에서 파일을 관리하는 부분 </mark>
  
  - <mark>파일과 메타데이터, 디렉토리 정보 등을 관리</mark>

- Directory
  
  - 디렉토리 안에 있는 파일들의 메타데이터 일부를 보관하고 있는 특별한 파일 (그 디렉토리에 속한 파일 이름, 속성 등)

- Partition (=Logical Disk)
  
  - 하나의 물리적 디스크 안에 여러개의 파티션을 두거나, 여러개의 물리적 디스크를 하나의 파티션으로 구성
  
  - 물리적 디스크를 파티션으로 구성한뒤 파티션에 file system을 깔거나 swapping 용도로 사용할 수 있다

- buffer caching
  
  - 파일의 내용을 읽어서 사용자 메모리 영역에 바로 넣어주지 않고, 운영체제의 메모리 영역에 보관했다가 사용자에게 전달해준다. 

- File System의 **Mounting**
  
  - 특정 디렉토리와 특정 파일 시스템을 연결

<br>

- 파일 할당 방법 (Allocation of File Data in Disk)
  
  - **Contiguous Allocation (연속할당)** 
    
    - 파일의 데이터를 디스크에 연속적으로 할당
    
    - 장: 데이터가 인접한 블록에 존재. 한번의 seek/rotation으로 많은 양의 데이터를 transfer =>  I/O속도가 빠름
    
    - 장: 직접접근이 가능하다 (Direct Access = Random Access)
    
    - 단: 외부 조각(external fragment) 문제 
    
    - 단: 파일 크기가 변경될때 제약이 있음
  
  - **Linked Allocation**
    
    - 파일의 데이터를 디스크에 불연속적으로 할당. 여러 블록(섹터)에 흩어져 있음. 각 블록마다 다음 블록의 위치를 가지고 있다. 
    
    - 장: 외부조각 문제가 발생하지 않음
    
    - 단: 직접접근 불가능 (파일의 첫번째 블록부터 찾고자 하는 블록을 순차적으로 탐색해야 한다). 
    
    - 단: 하나의 sector가 고장나서 pointer가 유실되면 많은 데이터를 잃게된다.
    
    - 단: 모든 sector가 다음 sector의 포인터를 가지고 있어야 해서 공간의 낭비가 생긴다. 
  
  - **Indexed Allocation**
    
    - 인덱스 블록을 만들어서, 각각의 블록이 어느 위치에 위치해있는지 인덱스 블록 안에 기록.
    
    - 장: 직접접근이 가능하다 (Direct Access = Random Access)
    
    - 단: 작은 파일이라도 두개의 블록이 필요 (인덱스 블록, 데이터 블록) => 공간낭비
    
    - 단: 큰 파일일 경우 => 여러개의 인덱스 블록이 필요 
      
      - linked scheme : 인덱스블록의 맨 마지막 엔트리는 다음 인덱스 블록의 위치를 가리키도록 함
      
      - multi-level index : 하나의 인덱스 블록의 엔트리들이 다른 인덱스 블록의 인덱스 위치를 기록
  
  <br>

- Unix File System
  
  - Boot Block / Super Block / INode / Data
  
  - Directory  
    
    - Directory Entry 
      
      - 디렉토리를 표현하는 자료구조로, 디렉토리 내부의 파일 갯수만큼 엔트리가 존재한다.
      
      - 각각의 엔트리는 FileName + Inode를 가리키는 포인터로 이루어짐 
      
      - *Inode란? => 파일이름을 제외한 파일의 메타데이터   

<br>

- page cache / buffer cache
  
  - page chache
    
    - 가상메모리 기법에서 필요한 페이지만 메인메모리 프레임에 올려놓는 것을 cache라고 부름
  
  - buffer cache
    
    - 프로그램이 실행되다가 데이터파일 읽는 read() 시스템콜 할때, 운영체제가 파일을 디스크에서 읽어서 buffer cache에 보관했다가 사용자에게 카피해서 넘겨준다. 다음에 동일 파일 데이터에 대한 요청이 오면 buffer cache에 있는 내용을 넘겨준다.

<br>

- Memory Mapped I/O

- 





<br>

### <mark>Disk Management & Scheduling</mark>

---

-  Booting 
  
  - 메모리 영역중에 비휘발성 영역인 ROM에 small bootstrap loader가 있음 => 전원을 켜면 CPU가 ROM의 주소를 가리키고 이 부트스트랩 로더가 실행됨 => 하드디스크에서 0번 섹터에 있는 내용을 메모리에 올리고 실행하게 함 => 하드디스크의 0번 섹터에는 Boot Block이 있음 => Boot Block을 메모리에 올리고 실행하면 => 운영체제를 커널을 메모리에 올려서 실행하게 함.  => Booting

- 디스크 접근 시간
  
  - <mark>Seek Time</mark> : 디스크 헤드가 해당 실린더로 움직이는데 걸리는 시간 (가장 시간이 많이 걸리는 부분)
  
  - <mark>Rotational Latency</mark> : 헤드가 원하는 섹터에 도달하기까지 걸리는 회전 지연 시간
  
  - <mark>Trasfer Time</mark> : 실제 데이터의 전송 시간

- <mark>Disk Scheduling : Seek Time을 최소화 하는 것이 목표 </mark>

<br>

- <mark>Disk Scheduling</mark>
  
  - FCFS
  
  - SSTF (shortest seek time first) 
    
    - 현재 헤드 위치에서 가장 가까운 위치의 요청부터 처리
    
    - 기아(starvation) 문제 
  
  - SCAN
    
    - 엘리베이터 스케쥴링
    
    - 디스크 한쪽 끝에서 다른 한쪽 끝으로 이동하며 가는 길목에 있는 요청을 처리. 한쪽 끝에 도달하면 다시 역방향으로.
  
  - C-SCAN (Circular Scan)
    
    - 헤드가 한쪽 끝에서 다른 끝으로 이동하면 길목에 있는 요청을 처리. 한쪽 끝에 도달했으면 다시 출발점으로 이동 (이때는 요청을 처리하지 않음)
    
    - scan보다 균일한 대기 시간을 제공
  
  - N-SCAN
    
    - arm이 한 방향으로 움직이기 시작하면 그 시점 이후에 도착한 job은 되돌아올때 서비스 
  
  - LOOK
    
    - scan이나 c-scan은 헤드가 디스크 끝에서 끝으로 이동
    
    - Look은 헤드가 진행중이다가 그 방향에 더이상 기다리는 요청이 없으면 이동방향을 바꾼다
  
  - C-LOOK

<br>

- RAID (Redundant Array of Independent Disk) 
  
  - 여러개의 독립 디스크에 일부 중복된 데이터를 나눠서 저장
    
    - 디스크 처리 속도 향상 : 병렬적으로 데이터를 읽어옴
    
    - 신뢰성 향상 : 동일 정보를 여러 디스크에 중복 저장, 하나의 디스크 고장시 다른 디스크에서 읽어옴 
