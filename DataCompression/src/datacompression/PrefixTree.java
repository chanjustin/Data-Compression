package datacompression;

/**
 *
 * @author test
 */

public class PrefixTree
{
    private Node root;
    private boolean leafsTaken;
    private String code;

    public PrefixTree(String prefixCode)
    {
        if(prefixCode != null)
        {
            for(int i = 0; i < prefixCode.length(); i++)
            {
                insert(prefixCode.charAt(i));
            }
        }
    }

    public void uncompress(String codedMessage)
    {
        Node root;
        int numberOfCharacters = 0;

        for(int i = 0; i < codedMessage.length();)
        {
            root = this.root;
            while(root.getLeftChild() != null && root.getRightChild() != null)
            {
                if(codedMessage.charAt(i) == '0')
                {
                    root = root.getLeftChild();
                }
                else if(codedMessage.charAt(i) == '1')
                {
                    root = root.getRightChild();
                }
                i++;
            }
            System.out.print(root.getData());
            numberOfCharacters++;
        }
        double compressionRatio = (codedMessage.length()/(double)(numberOfCharacters*8))*100;
        System.out.println("\nNumber of bits = " + codedMessage.length());
        System.out.println("Number of characters = " + numberOfCharacters);
        System.out.println("Compression ratio = " + compressionRatio + "%");
    }

    public void traverse()
    {
        System.out.printf("%25s%25s%25s\n","character","bits","encoding");
        internalPreorderTraverse(root);
        System.out.println();
    }

    private String getEncoding(Node node)
    {
        if(node != this.root)
        {
            if(node.isLeftNode())
            {
                code += "0";
            }
            else
            {
                code += "1";
            }
            getEncoding(node.getParent());
        }
        return code;
    }

    private void internalPreorderTraverse(Node root)
    {
        if(root != null)
        {
            if(root.getData() != '*')
            {
                code = "";
                code = getReverseString(getEncoding(root));
                System.out.printf("%25s%25s%25s\n",root,code,code.length());
            }
            if(root.getLeftChild() != null)
            {
                internalPreorderTraverse(root.getLeftChild());
            }
            if(root.getRightChild() != null)
            {
                internalPreorderTraverse(root.getRightChild());
            }
        }
    }

    private String getReverseString(String string)
    {
        String newString = "";
        for(int i = string.length()-1; i >= 0; i--)
        {
            newString += string.charAt(i);
        }
        return newString;
    }

    public void insert(char data)
    {
        internalInsert(new Node(data),this.root);
    }

    private void internalInsert(Node nodeToAdd, Node root)
    {
        if(this.root == null)
        {
            this.root = nodeToAdd;
        }

        else if(root.getLeftChild() == null)
        {
            root.setLeftChild(nodeToAdd);
            nodeToAdd.setParent(root);
        }
        else if(root.getLeftChild().getData() == '*' && !leavesTaken(root.getLeftChild()))
        {
            internalInsert(nodeToAdd,root.getLeftChild());
        }
        else if(root.getRightChild() == null)
        {
            root.setRightChild(nodeToAdd);
            nodeToAdd.setParent(root);
        }
        else
        {
            internalInsert(nodeToAdd,root.getRightChild());
        }

    }

    private boolean leavesTaken(Node root)
    {
        leafsTaken = true;
        return internalLeavesTaken(root);
    }

    private boolean internalLeavesTaken(Node root)
    {
        if(root.getData() == '*' && (root.getLeftChild() == null || root.getRightChild() == null))
        {
            leafsTaken = false;
        }

        if(leafsTaken && root.getLeftChild() != null || root.getRightChild() != null)
        {
            internalLeavesTaken(root.getLeftChild());
            internalLeavesTaken(root.getRightChild());
        }
        return leafsTaken;
    }
}