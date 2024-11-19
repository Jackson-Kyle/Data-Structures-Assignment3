import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();

        // path to the .csv file
        String csvFile = "C:\\Users\\kdj51\\IdeaProjects\\Assignment3\\src\\amazon-product-data.csv";

       CSVReader.parseCSV(csvFile, tree);

       // create nodes to insert
        TreeNode manualNode1 = new TreeNode("4522345", "toy car", "vehicles", "$29.99" );
        TreeNode manualNode2 = new TreeNode("4522345", "car", "vehicles", "$29.99" );
        TreeNode manualNode3 = new TreeNode("5465184654", "toy train", "locomotives", "$19.99" );

        // insert nodes
        tree.insert(manualNode1);
        tree.insert(manualNode2);
        tree.insert(manualNode3);


        for(int i = 0; i<3; i++) {
            // allow user to search for products
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Product ID to search: ");
            String searchKey = scanner.nextLine();

            // search for the product in the tree
            TreeNode result = tree.search(searchKey);

            // print all the info of the product
            if (result != null) {
                System.out.println("\nProduct found:");
                System.out.println("ProductId: " + result.productId);
                System.out.println("Name: " + result.name);
                System.out.println("Category: " + result.category);
                System.out.println("Price: " + result.price);
            } else { // product not in tree
                System.out.println("\nProduct with ID " + searchKey + " not found.");
            }
        }



    }
}