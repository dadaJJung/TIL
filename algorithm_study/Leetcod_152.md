### [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

<br>

### 문제

---

- Given an integer array `nums`, find a contiguous non-empty subarray within the array that has the largest product, and return *the product*.
  
  The test cases are generated so that the answer will fit in a **32-bit** integer.
  
  A **subarray** is a contiguous subsequence of the array.

<br>

### 해결 포인트

---

- dp로 해결했다.

- 처음에 세운 점화식은 채점결과 오답. why? dp[i]는 i번째 위치까지의 연속된 최대곱을 가지도록 점화식을 세움 (`dp[i] = Math.max(dp[i-1]*nums[i] , nums[i])` )  하지만 이 문제의 조건을 보면 배열의 값의 범위가  -10 <= nums[i] <= 10 으로 음수값을 포함한다. 음수값을 가질 경우 저 점화식에 따라 최댓값을 구하면 잘못된 결과가 출력된다. 
  
  - 예) {-3, 2, -4} 의 경우 저 점화식대로 최댓값을 구하면 -4가 나온다 하지만, 실제 결과는 24가 나와야 한다.

- 해결 방법 !!!! 
  
  - i번째 인덱스까지 연속된 최대곱과 최소곱을 모두 가지고 있는다. 최소곱이 음수이고 연속되는 다른 값이 음수일 경우 최대곱에 연속된 수를 곱한것보다 (양수x음수), 최소곱에 연속된 수를 곱한게 (음수x음수) 결과적으로 더 큰 값이 나올 수 있기 때문이다.

<br>

### 풀이 코드 - version 1

---

```java
class Solution {
    public int maxProduct(int[] nums) {
        
        int[] maxDP = new int[nums.length];
        int[] minDP = new int[nums.length];
        
        //초기값
        maxDP[0] = nums[0];
        minDP[0] = nums[0];
        
        int max = maxDP[0];
        
        for(int i=1; i<nums.length; i++){

            maxDP[i] = Math.max(Math.max(maxDP[i-1]*nums[i],minDP[i-1]*nums[i]), nums[i]);
            minDP[i] = Math.min(Math.min(maxDP[i-1]*nums[i],minDP[i-1]*nums[i]), nums[i]);
            
            max = Math.max(max, Math.max(maxDP[i],minDP[i]));
            
        }
        
        return max;
        
    
    }
}


```


