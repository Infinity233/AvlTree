import java.util.ArrayDeque;

public class AvlTree {

    int Element;
    AvlTree Lchild = null, Rchild = null;
    int Height;


    public static int getHeight(AvlTree node) {
        if (node == null) {
            return -1;
        }
        return node.Height;
    }

    public static AvlTree Insert(int x, AvlTree T) {
        if (T == null) {
            T = new AvlTree();
            T.Height = 0;
            T.Element = x;
        }
        else if (x < T.Element) {
            T.Lchild = Insert(x, T.Lchild);
            if (getHeight(T.Lchild) - getHeight(T.Rchild) == 2) {

                if(x<T.Lchild.Element){
                    T = SingleRotateWithLeft(T);
                }
                else
                    T = DoubleRotateWithLeft(T);

            }


        }
        else if (x > T.Element){
            T.Rchild = Insert(x,T.Rchild);
            if(getHeight(T.Rchild)-getHeight(T.Lchild)==2){
                if(x>T.Rchild.Element){
                    T = SingleRotateWithRight(T);
                }
                else
                    T = DoubleRotateWithRight(T);
            }
        }
        T.Height = Math.max(getHeight(T.Lchild), getHeight(T.Rchild)) + 1;
        return T;
    }

    public static AvlTree SingleRotateWithLeft(AvlTree k2){

        AvlTree k1 =  k2.Lchild;
        k2.Lchild = k1.Rchild;
        k1.Rchild = k2;

        k2.Height = Math.max(getHeight(k2.Rchild), getHeight(k2.Lchild)) + 1;
        k1.Height = Math.max(getHeight(k2.Lchild), k2.Height) + 1;

        return k1;
    }

    public static AvlTree SingleRotateWithRight(AvlTree k2){

        AvlTree k1 =  k2.Rchild;
        k2.Rchild = k1.Lchild;
        k1.Lchild = k2;

        k2.Height = Math.max(getHeight(k2.Rchild), getHeight(k2.Lchild)) + 1;
        k1.Height = Math.max(getHeight(k2.Rchild), k2.Height) + 1;

        return k1;
    }

    public static AvlTree DoubleRotateWithLeft(AvlTree k3) {
        k3.Lchild = SingleRotateWithRight(k3.Lchild);

        return SingleRotateWithLeft(k3);
    }

    public static AvlTree DoubleRotateWithRight(AvlTree k3) {
        k3.Rchild = SingleRotateWithLeft(k3.Rchild);

        return SingleRotateWithRight(k3);
    }

    public static void printTree(AvlTree root) {
        if (root == null) {
            return;
        }

        class TreeNode{
            AvlTree node;
            int height;

            public TreeNode (){
            }

            public TreeNode(AvlTree node, int height) {
                this.node = node;
                this.height = height;
            }
        }

        AvlTree p = root;
        TreeNode t = new TreeNode();
        t.node = p;
        t.height = 0;
        ArrayDeque<TreeNode> s = new ArrayDeque<>();
        s.clear();
        while(t.node!= null || !s.isEmpty()){

            while(t.node!=null){
                s.push(new TreeNode(t.node,t.height));
                t.node = t.node.Rchild;
                ++t.height;
            }

            if(!s.isEmpty()){
                t = s.pop();
                for(int i=0;i<t.height;++i){
                    System.out.print(" ");
                }
                System.out.println(t.node.Element);
                t.node = t.node.Lchild;
                ++t.height;
            }

        }

    }

    public static void print(AvlTree root,int height){
        if(root==null){
            return;
        }

        print(root.Rchild,height+1);
        for(int i=0;i<height;++i) System.out.print("  ");
        System.out.println(root.Element);
        print(root.Lchild,height+1);

    }

    public static void main(String[] args) {
        class TreeNode{
            AvlTree node;
            int height;
        }
        AvlTree root = null;
        root = Insert(1,root);
        root = Insert(2,root);
        root = Insert(3,root);
        for(int i=4;i<8;++i){
            root = Insert(i,root);
        }

        for(int i=16;i>=10;--i){
            root = Insert(i,root);
        }

        root = Insert(8,root);
        root = Insert(9,root);

        print(root,0);

    }

}
