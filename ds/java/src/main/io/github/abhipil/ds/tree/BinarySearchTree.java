package io.github.abhipil.ds.tree;

public class BinarySearchTree<K extends Comparable<K>, V>{
    private TreeNode root=null;

    private class TreeNode{
        private K key;
        private V value;
        private TreeNode left, right;

        public TreeNode(K key,V value) {
            this.key=key;
            this.value=value;
        }
    }

    public BinarySearchTree(){}

    /**
     * inserts key into bst, and returns the node inserted
     * @param  key   
     * @param  value 
     */
    public void put(K key, V value) {
        if (key == null || value==null)
            throw new IllegalArgumentException("Key cannot be null");
        if (root==null) {
          root = new TreeNode(key, value);
          return;  
        }
        put(key, value, root);
        if (!isBST())
            throw new IllegalStateException("Tree violates BST property");
    }

    public TreeNode put(K key, V value, TreeNode root) {
        TreeNode runner = root, node=null;
        while(runner!=null && !key.equals(runner.key)) {
            node = runner;
            if (key.compareTo(runner.key)<0) {
                runner = runner.left;
            } else runner = runner.right;
        }
        if (runner!=null) {
            runner.value = value;
        } else {
            runner = new TreeNode(key, value);
            if (key.compareTo(node.key)<0) {
                node.left = runner;
            } else node.right = runner;
        }
        return runner;
    }

    /**
     * returns value of the node with key k, if none exists returns null
     * @param  key 
     * @return value
     */
    public V get(K key){
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (root==null)
            throw new IllegalStateException("Tree is empty");
        return get(key, root);
    }

    private V get(K key, TreeNode root) {
        TreeNode runner = root;
        while(runner!=null && !key.equals(runner.key)){
            if (key.compareTo(runner.key)<0){
                runner = runner.left;
            } else {
                runner = runner.right;
            }
        }
        if (runner==null) return null;
        return runner.value;
    }

    /**
     * deletes node with key K from the tree
     * Deletion strategy as described in CLRS[2009]
     * 1. recursively find the node n with key k
     * 2. if n.right is null return n.left
     * 3. if n.left is null return n.right
     * 4. find the min from n.right and replace with n
     *     4.1 if n.right is the min in the subtree,
     *         replace n with n.right.right
     *     4.2 else find the min node, recursively delete it and
     *         replace n with this node
     * @param key
     */
    public void delete(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (root==null)
            throw new IllegalStateException("Tree is empty");
        root = rdelete(key, root);
        if (!isBST())
            throw new IllegalStateException("Tree violates BST property");
    }

    // recursive delete
    private TreeNode rdelete(K key, TreeNode root) {
        if (root==null) return root;
        if (key.compareTo(root.key)<0) {
            root.left = rdelete(key, root.left);
        } else if (key.compareTo(root.key)>0) {
            root.right = rdelete(key, root.right);
        } else {
            if (root.right==null) return root.left;
            if (root.left==null) return root.right;
            if (root.right.left!=null){
                TreeNode rightMin = min(root.right);
                rightMin.right = rdelete(rightMin.key, root.right);
                root.right = rightMin;
            }
            root.right.left = root.left;
            root = root.right;
        }
        return root;
    }

    /**
     * returns the value of the node with the smallest key 
     * @return value
     */
    public V min() {
        if (root==null)
            throw new IllegalStateException("Tree is empty");
        return min(root).value; 
    }

    private TreeNode min(TreeNode root) {
        while(root.left!=null) root = root.left;
        return root;
    }


    public void inorder() {
        rInorder(root);
        System.out.println();
    }

    // recursive inorder traversal
    private void rInorder(TreeNode root) {
        if (root==null) return;
        rInorder(root.left);
        System.out.print(root.value+" ");
        rInorder(root.right);
    }

    private boolean isBST(){
        if (root==null) return true;
        return isBST(root, null, null);
    }

    /**
     * returns true if the root is a valid binary search tree, else false
     * As described in ALGS[2011]
     * @param  root [description]
     * @param  prev [description]
     * @param  next [description]
     * @return      [description]
     */
    private boolean isBST(TreeNode root, K prev, K next) {
        if (root==null) return true;
        if (prev!=null && prev.compareTo(root.key)>0) return false;
        if (next!=null && next.compareTo(root.key)<0) return false;
        return isBST(root.left, prev, root.key) 
                && isBST(root.right, root.key, next);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.insert(4, "you");
        bst.insert(2, "how");
        bst.insert(1, "hello");
        bst.insert(3, "are");
        bst.insert(5, "?");
        bst.inorder();
        System.out.println(bst.get(2));
        bst.delete(2);
        bst.inorder();
    }
}
