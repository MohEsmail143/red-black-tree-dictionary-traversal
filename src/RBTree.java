class Node
{
    int color;  // 1 RED, 0 BLACK
    String data;
    Node parent, left, right;
}

public class RBTree
{
    private final Node NULL;
    private Node root;
    private int treeSize = 0;

    public RBTree()
    {
        NULL = new Node();
        NULL.color = 0;
        NULL.left = null;
        NULL.right = null;
        root = NULL;
    }

    public Node getRoot()
    {
        return this.root;
    }

    public int getHeight(Node root)
    {
        if (root == NULL)
        {
            return 0;
        } else
        {
            return 1 + Math.max(getHeight(root.right), getHeight(root.left));
        }
    }

    public int getSize()
    {
        return this.treeSize;
    }

    public Node min(Node node)
    {
        while (node.left != NULL)
        {
            node = node.left;
        }
        return node;
    }

    public Node max(Node node)
    {
        while (node.right != NULL)
        {
            node = node.right;
        }
        return node;
    }

    public Node successor(Node x)
    {
        if (x.right != NULL)
        {
            return min(x.right);
        }

        Node y = x.parent;
        while (y != NULL && x == y.right)
        {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public boolean search(String value)
    {
        Node resultOfSearch = search(this.root, value);
        return resultOfSearch != NULL;
    }

    private Node search(Node root, String value)
    {
        if (root == NULL || root.data.compareToIgnoreCase(value) == 0)
        {
            return root;
        }

        if (value.compareToIgnoreCase(root.data) < 0)
        {
            return search(root.left, value);
        } else
        {
            return search(root.right, value);
        }
    }

    public void insert(String key)
    {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = NULL;
        node.right = NULL;
        node.color = 1;
        Node y = null;
        Node x = this.root;
        this.treeSize++; // increment TreeSize of nodes in RBT

        while (x != NULL)
        {
            y = x;
            if (node.data.compareToIgnoreCase(x.data) < 0)
            {
                x = x.left;
            } else
            {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null)
        {
            root = node;
        } else if (node.data.compareToIgnoreCase(y.data) < 0)
        {
            y.left = node;
        } else
        {
            y.right = node;
        }

        if (node.parent == null)
        {
            node.color = 0;
            return;
        }

        if (node.parent.parent == null)
        {
            return;
        }

        balanceTree(node);
    }

    private void balanceTree(Node x)
    {
        Node u;
        while (x.parent.color == 1)
        {
            if (x.parent == x.parent.parent.right)
            {
                u = x.parent.parent.left;
                if (u.color == 1)
                {
                    u.color = 0;
                    x.parent.color = 0;
                    x.parent.parent.color = 1;
                    x = x.parent.parent;
                } else
                {
                    if (x == x.parent.left)
                    {
                        x = x.parent;
                        rightNodeRotate(x);
                    }
                    x.parent.color = 0;
                    x.parent.parent.color = 1;
                    leftNodeRotate(x.parent.parent);
                }
            } else
            {
                u = x.parent.parent.right;

                if (u.color == 1)
                {
                    u.color = 0;
                    x.parent.color = 0;
                    x.parent.parent.color = 1;
                    x = x.parent.parent;
                } else
                {
                    if (x == x.parent.right)
                    {
                        x = x.parent;
                        leftNodeRotate(x);
                    }
                    x.parent.color = 0;
                    x.parent.parent.color = 1;
                    rightNodeRotate(x.parent.parent);
                }
            }
            if (x == root)
            {
                break;
            }
        }
        root.color = 0;
    }

    public void leftNodeRotate(Node x)
    {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NULL)
        {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null)
        {
            this.root = y;
        } else if (x == x.parent.left)
        {
            x.parent.left = y;
        } else
        {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightNodeRotate(Node x)
    {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NULL)
        {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null)
        {
            this.root = y;
        } else if (x == x.parent.right)
        {
            x.parent.right = y;
        } else
        {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
}
