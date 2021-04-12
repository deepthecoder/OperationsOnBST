public class Node
{
    int data,freq=0;
    Node left,right;
    public Node(int dt)
    {
        data=dt;
        left=null;
        right=null;
        ++freq;
    }
}