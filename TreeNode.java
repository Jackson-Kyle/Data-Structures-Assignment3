public class TreeNode {         //node in the red black tree
    String productId;       // unique identifier
    String name;         // product name
    String category;     // product category
    String price;        // product price
    TreeNode left, right, parent;
    boolean isRed;       // boolean that classifies node as red or black

    //constructor for a treenode
    public TreeNode(String Id, String name, String category, String price) {
        this.productId = Id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isRed = true; // new nodes are always red
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    // get functions for all elements of TreeNode

    public String getId(){
        return productId;
    }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public String getPrice(){
        return price;
    }

}
