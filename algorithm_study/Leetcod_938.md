### [Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/)

<br>

### 문제

---

- 이진검색 트리에서 특정 범위내의 값의 합을 구한다

- Given the `root` node of a binary search tree and two integers `low` and `high`, return *the sum of values of all nodes with a value in the **inclusive** range* `[low, high]`

<br>

### 풀이 코드

---

```java
//version1
class Solution {
    int sum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        
        if(root!=null) inorder(root,low,high);
        return sum;
    }
    
    public void inorder(TreeNode root, int low, int high){
        if(root.left != null) inorder(root.left,low,high);
        
        if(low<=root.val && root.val<=high){
            sum += root.val;
        }else if(root.val > high){
            return;
        }
        
        if(root.right !=null) inorder(root.right,low,high);
    }
}

//---------------------------------------------------------------

//version2
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        
        if(root==null) return 0;
        
        if(root.val < low){ //오른쪽만 탐색
             return rangeSumBST(root.right,low,high);
        }else if(root.val > high){ //왼쪽만 탐색
            return rangeSumBST(root.left,low,high);
        }else{   // low <= root.val <= high
            return rangeSumBST(root.left,low,high) + root.val +                 rangeSumBST(root.right,low,high);
        }

    }
    
}
```


