//{ Driver Code Starts
// Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.math.*;
import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

class GFG {
    static Node buildTree(String str) {
        // Corner Case
        if (str.length() == 0 || str.equals('N')) return null;
        String[] s = str.split(" ");

        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        // Starting from the second element
        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            // Get and remove the front of the queue
            Node currNode = q.remove();

            // Get the curr node's value from the string
            String currVal = s[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the curr node
                currNode.left = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= s.length) break;
            currVal = s[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the curr node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.right);
            }

            i++;
        }

        return root;
    }

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t > 0) {
            String s = br.readLine();
            Node root1 = buildTree(s);

            s = br.readLine();
            Node root2 = buildTree(s);

            Solution T = new Solution();
            List<Integer> ans = T.merge(root1, root2);
            for (int i = 0; i < ans.size(); i++) System.out.print(ans.get(i) + " ");
            System.out.println();

            t--;
        }
    }
}

// } Driver Code Ends


// User function Template for Java

/*
class Node
{
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}

*/
class Solution {
    void util (Node curr, Stack<Node> s){
        while(curr != null){
            s.add(curr);
            curr = curr.left;
        }
    }
    // Function to return a list of integers denoting the node
    // values of both the BST in a sorted order.
    public List<Integer> merge(Node root1, Node root2) {
        // Write your code here
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        util(root1, s1);
        util(root2, s2);
        List<Integer> res = new ArrayList<>();
        while(!s1.isEmpty() && !s2.isEmpty()){
            Node top1 = s1.peek();
            Node top2 = s2.peek();
            if(top1.data < top2.data){
                res.add (top1.data);
                s1.pop();
                util(top1.right,s1);
            } else if(top2.data < top1.data){
                res.add (top2.data);
                s2.pop();
                util(top2.right,s2);
            } else {
                s1.pop();
                s2.pop();
                res.add(top1.data);
                res.add(top2.data);
                util(top1.right,s1);
                util(top2.right,s2);
            }
        }
        while(!s1.isEmpty()){
            Node top1 = s1.pop();
            res.add(top1.data);
            util(top1.right,s1);
        }
        while(!s2.isEmpty()){
            Node top2 = s2.pop();
            res.add(top2.data);
            util(top2.right,s2);
        }
        return res;
    }
}
