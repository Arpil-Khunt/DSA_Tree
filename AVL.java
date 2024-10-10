public class AVL {
    public AVL(){

    }
    class Node{
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value){
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
    }
    private Node root;

    //height of the tree
    public int height(){
        return height(root);
    }
    private int height(Node node){
        if(node == null){
            return -1;
        }
        return node.height;
    }

    //insert value in tree
    public void insert(int value){
        root = insert(value,root);
    }
    private Node insert(int value, Node node){
        if(node == null){
            node = new Node(value);
            return node;
        }
        if(value < node.value){
            node.left = insert(value,node.left);
        }
        if(value > node.value){
            node.right = insert(value,node.right);
        }
        node.height = Math.max(height(node.left),height(node.right))+1;
        return rotate(node);
    }

    //populate
    public void populate(int[] value){
        for(int i = 0; i<value.length; i++){
            this.insert(value[i]);
        }
    }

    //populate sorted value
    public void populateSorted(int[] value){
        populateSorted(value,0,value.length-1);
    }
    private void populateSorted(int[] value, int start, int end){
        if(start >= end){
            return;
        }
        int mid = start + (end-start)/2;
        this.insert(value[mid]);
        populateSorted(value,start,mid);
        populateSorted(value,mid+1,end);

    }

    //balance a tree
    public Node rotate(Node node){
        if(height(node.left)-height(node.right) > 1){
            //left heavy

            if(height(node.left.left) - height(node.left.right) > 0){
                //left-left case
                return rightRotate(node);
            }
            if(height(node.left.left) - height(node.left.right)<0){
                //left-right case
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if(height(node.left)-height(node.right) < -1){
            //right heavy
            if(height(node.right.left) - height(node.right.right)<0){
                //right-right case
                return leftRotate(node);
            }
            if(height(node.right.left)-height(node.right.right)>0){
                //right-left case
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    //right rotate
    public Node rightRotate(Node p){
        Node c = p.left;
        Node t = c.right;
        c.right = p;
        p.left = t;

        p.height = Math.max(height(p.left),height(p.right)+1);
        c.height = Math.max(height(c.left),height(c.right)+1);
        return c;
    }

    //left rotate
    public Node leftRotate(Node p){
        Node c = p.right;
        Node t = c.left;
        c.left = p;
        p.right = t;

        p.height = Math.max(height(p.left),height(p.right)+1);
        c.height = Math.max(height(c.left),height(c.right)+1);
        return c;
    }

    //check tree isBalanced
    public boolean isBalanced(){
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){
        if(node == null){
           return true;
        }
        return Math.abs(height(node.left)-height(node.right)) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    //display
    public void display(){
        display(this.root,"Root node: ");
    }
    private void display(Node node , String details){
        if(node == null){
            return;
        }
        System.out.println(details + node.getValue());

        //print left child
        display(node.left,"Left child of "+node.getValue()+" : ");

        //print right child
        display(node.right,"Right child of "+node.getValue()+" : ");
    }

    //pretty display
    public void prettyDisplay(){
        if(root != null) prettyDisplay(this.root,0);
    }
    private void prettyDisplay(Node node, int level){
        if(node == null){
            return;
        }
        prettyDisplay(node.right,level+1);
        if(level != 0){
            for(int i = 0; i<level-1; i++){
                System.out.print("|\t\t");
            }
            System.out.println("|------->"+node.value);
        }else{
            System.out.println(node.value);
        }
        prettyDisplay(node.left,level+1);
    }
}
