# 네트워크 면접 질문 정리

---

<br>

- packet delay의 네가지 요소
  
  - nodal processing delay
    
    - check bit errors
    
    - determine output link
  
  - queueing delay
    
    - time waiting at output link for transmission
    
    - depends on congestion level of router
  
  - transmission delay
    
    - output link로 packet을 밀어넣는 시간
    
    - packet의 크기와 link의 bandwidth에 따라 결정
    
    - L(packet length) / R(link bandwidth)
  
  - propagation delay
    
    - 비트가 링크에 실린후 목적지에 도달할 때까지 걸리는 시간

<br>

- **Internet protocol stack**   
  
  - **<mark>application</mark>** : supporting network applications
    
    - FTP, SMTP, HTTP, DNS
    
    - message
  
  - **<mark>transport</mark>** : process - process data transfer
    
    - TCP, UDP
    
    - source process to dest process delivery
    
    - segment
  
  - **<mark>network</mark>** : routing of datagrams from source to destination
    
    - IP, routing protocols
    
    - source host to dest host delivery
    
    - datagram
  
  - **<mark>link</mark>** : data transger between neighboring network elements
    
    - ethernet, 802.111(wifi), ppp
    
    - hop by hop delivery
    
    - frame
  
  - **<mark>physical</mark>** : bits on the wire

- 각 계층은 아래 계층의 서비스를 이용해서 자신의 목표를 수행한다.

<br>

-  
