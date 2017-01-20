package io.github.abhipil.ds.tree;

class SplayTree<K extends Comparable<K>>, V>{
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

    public SplayTree(){}

    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (root==null)
            throw new IllegalStateException("Tree is empty");
        return get(key, root);
    }

    private V get(K key, TreeNode root) {
        TreeNode v = splay(root, key);
        if (key.equals(v.key))
            return v.value;
        return null;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        this.root = put(key, value, root);
    }

    /**
     * inserts node with key and value to the tree rooted at root
     * 1. splay the node with key, or the last node if not in tree, to the root
     * 2. if root has key, update root value,
     * 3. else insert new node above root, with root in right or left subtree
     * @param  key   
     * @param  value 
     * @param  root  
     * @return root with new node inserted
     */
    private TreeNode put(K key, V value, TreeNode root) {
        if (root==null)
            return new TreeNode(key, value);
        root = splay(root, key);

        int cmp = root.key.compareTo(key);
        if (cmp<0) { // shift root to left subtree
            TreeNode node = new TreeNode(key, value);
            n.right = root.right;
            n.left = root;
            root.right=null;
            root = n;
        } else if (cmp>0) { // shift root to right subtree
            TreeNode node = new TreeNode(key, value);
            n.left = root.left;
            n.right = root;
            root.left=null;
            root = n;
        } else root.value = value;
        return root;
    }

    // TODO: figure deletion 
    public void delete(K key) {};

    /**
     * splay up the node with key to the root in a tree rooted at root
     * if the key does not exist, splay up the node where search ended
     * As described by 
     * @param  root [description]
     * @param  key  [description]
     * @return root with node with key splayed up to the root
     */
    private TreeNode splay(TreeNode root, K key) {
        if (root==null || node==null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp<0) { // left subtree
            if (root.left==null) return root;
            int cmpLeft = key.compareTo(root.left.key);
            if (cmpLeft<0) { // left of the left
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            } else if (cmpLeft>0) { // right of the left
                root.left.right = splay(root.left.right, key);
                root.left = rotateLeft(root.left);
            }
            // the "zig" step if both conditions above fail
            return rotateRight(root);
        } else if (cmp>0) { // right subtree
            if (root.right==null) return null;
            int cmpRight = key.compareTo(root.rigth.key);
            if (cmpRight<0) {
                root.right.left = splay(root.right.left, key);
                root.right = rotateRight(root.right);
            } else if (cmpRight>0) { // right of the right subtree
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            }
            // the "zig" step if both conditions above fail
            return rotateLeft(root);
        }
        return root;
    }

    private TreeNode rotateRight(TreeNode node) {
        if (node==null || node.left==null) return null;
        Node child = node.left;
        node.left = child.right;
        child.right = node;
        return child;
    }

    private TreeNode rotateLeft(TreeNode node) {
        if (node==null || node.right==null) return null;
        Node child = node.right;
        node.right = child.left;
        child.left = node;
        return child;
    }
}
