public class RBTree {    // red-black tree for organizing products
    private TreeNode root = null; // root node of tree

    // insert product into tree
    public void insert(TreeNode product) {

// if there are no nodes, first insert becomes root
        if (root == null) {
            root = product;
            root.isRed = false; // root always black

            return;
        }

        TreeNode parent = null;
        TreeNode current = root;

        // insert products by id #, smaller ones go left, bigger ones go right
        while (current != null) {
            parent = current;
            if (product.getId().compareTo(current.productId) < 0) {

                current = current.left;

            } else if (product.getId().compareTo(current.productId) > 0) {
                current = current.right;

            } else {
                // productId already inserted
                System.out.println("Product with ID " + product.getId() + " already exists.");
                return;
            }
        }

        product.parent = parent;

        if (product.getId().compareTo(parent.productId) < 0) {
            parent.left = product;
        } else {
            parent.right = product;
        }

        // make red-black tree formatted correctly in terms of colors
        fixInsert(product);
    }

   // fixes any problems with the red-black tree after new node is inserted such as right child red nodes or double red nodes
    // allows tree to stay relatively balanced so that the height of the tree stays low, increasing efficiency
    private void fixInsert(TreeNode node) {
        while (node != root && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) { // parent is the left child
                TreeNode uncle = node.parent.parent.right;

                // unc node is red
                if (uncle != null && uncle.isRed) {
                    node.parent.isRed = false;  // make parent black
                    uncle.isRed = false;        // make unc black
                    node.parent.parent.isRed = true; // make grandparent red
                    node = node.parent.parent; // move up to grandparent
                } else {
                    // node is right child
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    // node is left child
                    node.parent.isRed = false;  // make parent black
                    node.parent.parent.isRed = true; // make grandparent red
                    rightRotate(node.parent.parent);
                }
            } else { // parent is right child
                TreeNode uncle = node.parent.parent.left;

                // unc is red
                if (uncle != null && uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    // node is left child
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    // node is right child
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false; // root is always black
    }

    // rotate given node x to the left
    private void leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

   // rotate given node y to the right
    private void rightRotate(TreeNode y) {
        TreeNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    // search for product via given productId
    public TreeNode search(String productId) {
        TreeNode current = root;
        while (current != null) {
            if (productId.equals(current.productId)) {
                return current;
            } else if (productId.compareTo(current.productId) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null; // productId not in the tree
    }

}
