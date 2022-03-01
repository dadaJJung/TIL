### [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

<br>

### 문제 - 주어진 이진트리가 이진검색트리인지 true/false 리턴하는 문제

---

- Given the `root` of a binary tree, *determine if it is a valid binary search tree (BST)*.

- A **valid BST** is defined as follows:
  
  - The left subtree of a node contains only nodes with keys **less than** the node's key.
  
  - The right subtree of a node contains only nodes with keys **greater than** the node's key.
  
  - Both the left and right subtrees must also be binary search trees.
  
  

<br>

### 풀이 - 첫번째 풀이

---

- 이진검색트리는 inorder 트리순회를 하면 오름차순으로 정렬된 노드값이 나옴.

- 주어진 이진트리를 inorder 순회하면서 직전 노드값보다 작은값이 나오면  (=오름차순 정렬이 깨지면) 유효한 이진검색트리가 아니므로 false를 리터하도록 함

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//inorder -> 오름차순 

class Solution {
    
    boolean isFirst = true;
    boolean ret = true;
    public int prevNum = 0;
    
    public boolean isValidBST(TreeNode root) {
        
        inorder(root);
        return ret;
        
    }
    
    
    //left-root-right
    public void inorder(TreeNode root){
        if(!ret) return;
        if(root==null) return;
        
        inorder(root.left);
        
        if(isFirst){
            isFirst = false;
            prevNum = root.val;
        }else{
            if(root.val > prevNum) prevNum = root.val;
            else ret = false;
        }
        
        inorder(root.right);
        
    }
    
}

```

<br>

### 풀이 - 두번째 풀이

---

- 재귀적으로 구현

- 먼저, lower bound와 upper bound값을 넘겨줘서 현재 노드가 그 범위 안에 드는지 먼저 확인 

- 위 조건을 만족하면 왼쪽 subtree와 오른쪽 subtree값을 재귀 호출, 둘 중 하나라도 false를 리턴하면 false 리턴

```java
class Solution {
    
    
    public boolean isValidBST(TreeNode root) {
       
        return isValidBST(root,false,false,0,0);
       
    }
    
   
     public boolean isValidBST(TreeNode root, boolean lowerBoundExist, boolean upperBoundExist,
                               int lowerBound, int upperBound) {
    
       if(root==null) return true;
        
       //subtree검사하기 전에 현재 노드가 lower~upper 범위에 들어오는지 확인  
       if(lowerBoundExist && root.val <= lowerBound) return false;
       if(upperBoundExist && upperBound <= root.val) return false;  
         
       //subtree중 하나라도 false이면 false리턴   
        return isValidBST(root.left,lowerBoundExist,true,lowerBound,root.val) 
            && isValidBST(root.right,true,upperBoundExist,root.val,upperBound) ;
         
     }
    
}


```
