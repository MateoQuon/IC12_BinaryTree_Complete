public class BinaryTree<E extends Comparable<E>> {
    // Field
    private Node<E> mRoot;

    public BinaryTree()
    {
        mRoot = null;
    }

    public void clear()
    {
        mRoot = null;
        // Request garbage collection
        System.gc();
    }

    public boolean add(E element)
    {
        // Calls recursive add, starting at the root
         mRoot = addRecursive(mRoot, element);
         return true;
    }

    private Node<E> addRecursive(Node<E> current, E element)
    {
        // when the current node is null,
        // we've reached a leaf node and we can insert the new node in that position
        if (current == null)
            return new Node(element);
        // if the element is less than the current node's data,
        // we go to the left child
        else if (element.compareTo(current.mData) < 0)
            current.mLeft = addRecursive(current.mLeft, element);
        // if the element is greater than the current node's data,
        // we go to the right child
        else if (element.compareTo(current.mData) > 0)
            current.mRight = addRecursive(current.mRight, element);

        return current;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        inOrderTraverse(mRoot, sb);
        sb.setCharAt(sb.length()-2, ']');

        return sb.toString();
    }
    private void inOrderTraverse(Node current, StringBuilder sb){
        if (current == null)
            return;
        // 1. Traverse left
        inOrderTraverse(current.mLeft, sb);
        // 2. Visit Root/Append Root
        sb.append(current.mData).append(", ");
        // 3. Traverse right
        inOrderTraverse(current.mRight, sb);
    }

    // Nested Inner Class
    private class Node<E extends Comparable<E>>
    {
        private Node<E> mLeft;
        private E mData;
        private Node<E> mRight;

        public Node(E data, Node<E> left, Node<E> right)
        {
            // Assign all fields to parameters
            mData = data;
            mLeft = left;
            mRight = right;
        }
        public Node(E data)
        {
            this(data, null, null);
        }

        public boolean isLeaf()
        {
            return mLeft == null && mRight == null;
        }
    }
}
