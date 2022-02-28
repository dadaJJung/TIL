### [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) 가장 긴 증가하는 부분순열

<br>

### 문제

---

- Given an integer array `nums`, return the length of the longest strictly increasing subsequence.
  
  A **subsequence** is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, `[3,6,2,7]` is a subsequence of the array `[0,3,1,6,2,2,7]`.

<br>

### 풀이 코드

---

```java
//version1 -> O(n^2)

class Solution {
    public int lengthOfLIS(int[] nums) {
        
        //dp[i] = i번째수를 마지막으로하는 증가하는 부분 수열의 길이

        int len = nums.length;
       int[] dp = new int[len];
        dp[0] = 1;
        
        int ret = 1;
        
        for(int i=1; i<len; i++){
            int maxLen = 1;
            for(int j=i-1; j>=0; j--){
                if(nums[j] >= nums[i]) continue;
                maxLen = Math.max(maxLen,dp[j]+1);
            }
            dp[i] = maxLen;
            ret = Math.max(ret,dp[i]);
        }
        
        
        return ret;
        
        
    }
}


```


