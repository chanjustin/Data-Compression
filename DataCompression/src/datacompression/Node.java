package datacompression;

/**
 *
 * @author test
 */
public class Node implements Comparable
{
    private char data;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(char data)
    {
        setData(data);
    }

    public char getData()
    {
        return data;
    }

    public void setData(char data)
    {
        this.data = data;
    }

    public void setLeftChild(Node node)
    {
        leftChild = node;
    }

    public void setRightChild(Node node)
    {
        rightChild = node;
    }

    public Node getLeftChild()
    {
        return leftChild;
    }

    public Node getRightChild()
    {
        return rightChild;
    }

    public Node getParent()
    {
        return parent;
    }

    public void setParent(Node node)
    {
        parent = node;
    }

    public int compareTo(Object node)
    {
        Node castedNode = (Node)node;

        if(getData() > (castedNode.getData()))
        {
            return 1;
        }
        else if(getData() == (castedNode.getData()))
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public boolean isLeftNode()
    {
        if(getParent() != null)
        {
            try
            {
                if(getParent().getLeftChild().equals(this))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch(NullPointerException e)
            {
                return false;
            }
        }
        else
        {
            //it's a root
            return false;
        }
    }
    
    public boolean isRightNode()
    {
        if(getParent() != null)
        {
            if(getParent().getRightChild().equals(this))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            //it's a root
            return false;
        }
    }

    public String toString()
    {
        return getData() + "";
    }
}
