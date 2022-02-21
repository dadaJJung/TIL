### [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)

<br>

### 문제

---

- Given a `m x n` `grid` filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

- **Note:** You can only move either down or right at any point in time.

<br>

### 풀이 코드 - version 1

---

```java
class Solution {
    public int minPathSum(int[][] grid) {
 
        int[][] dp = new int[grid.length][grid[0].length];
        int MAX_VALUE = 200*200*100 + 1;
        
        for(int[] arr : dp) Arrays.fill(arr,MAX_VALUE);
        dp[0][0] = grid[0][0];
        
        recursion(0,0,grid,dp);
        
        return dp[grid.length-1][grid[0].length-1];
    
    }
    
    public void recursion(int i, int j, int[][] grid, int[][] dp){
    
        
        //현재 위치까지 오는 최소 값 => dp[i][j];

        //to right
        if(j+1 < grid[0].length && dp[i][j] + grid[i][j+1] < dp[i][j+1]){
            dp[i][j+1] = dp[i][j] + grid[i][j+1];
            recursion(i,j+1,grid,dp);
        } 
        
        //to bottom
        if(i+1 < grid.length && dp[i][j] + grid[i+1][j] < dp[i+1][j]){
            dp[i+1][j] = dp[i][j] + grid[i+1][j];
            recursion(i+1,j,grid,dp);
        }
        
        
    }
    
    
}
```

<br>

### 풀이 코드 - version 2

---

- dp[i][j] => (i,j) 위치까지 오는데 필요한 최소비용

- 점화식 : dp[i][j] = Min( dp[i-1][j] , dp[i][j-1] ) + grid[i][j]

```java
class Solution {
    public int minPathSum(int[][] grid) {
        
        int[][] dp = new int[grid.length][grid[0].length];
        int MAX_VALUE = 200*200*100 + 1;
        
        dp[0][0] = grid[0][0];
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(i==0 && j==0) continue;
                
                int up = i-1>=0 ? dp[i-1][j] : MAX_VALUE;
                int left = j-1>=0 ? dp[i][j-1] : MAX_VALUE;
                dp[i][j] = Math.min(up,left) + grid[i][j];
            }
        }
        
        
        
        return dp[grid.length-1][grid[0].length-1];
        
    }
    
}
```


