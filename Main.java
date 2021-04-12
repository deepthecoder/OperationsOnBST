import java.util.Scanner;
class Main 
{
    static Node root;
    static int max=-1;
    static int min=-1;
    //static String output="";
    public Main()
    {
      root=null;
    }
    public void insert(int key)
    {
        Node temp=root;
        temp=searchNode(temp,key);
        if(temp!=null)
          temp.freq++;
        else
            root=insertNode(root,key);
    }

    public Node insertNode(Node root,int key)
    {
      if(root==null)
      {
        root=new Node(key);
        return root;
      }
      if(key<=root.data)
        root.left=insertNode(root.left,key);
      if(key>root.data)
        root.right=insertNode(root.right,key);
      return root;
    } 

    public Node searchNode(Node temp,int key)
    {
      if(temp==null || temp.data==key)
          return temp;
      else
      {
        if(temp.data<key)
          return searchNode(temp.right,key);
        if(temp.data>key)
         return searchNode(temp.left,key);
        return temp;
      }
      
    }

    public int minValue1(Node root)
    {
        int minv = root.data;
        while (root.right != null)
        {
            minv = root.right.data;
            root = root.right;
        }
        return minv;
    }

    public int minValue(Node root)
    {
        int minv = root.data;
        while (root.left != null)
        {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public Node deleteNode(Node root,int key)
    {
         if (root == null)
            return root;

        if (key < root.data)
            root.left = deleteNode(root.left, key);
        else if (key > root.data)
            root.right = deleteNode(root.right, key);
 
        else {
            if(root.freq>1)
            {
              root.freq--;
              return root;
            }
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            int r= minValue(root.right);
            int l= minValue1(root.left);
            
            if(l!=root.data)
            {
                root.data=l;
                root.left = deleteNode(root.left, root.data);
            }
            
            else if(r!=root.data)
            {
                root.data=r;
                root.right = deleteNode(root.right, root.data);
            }
            
        }
 
        return root;
    }

    public Node maxElement(Node temp)
    {
        if(temp==null)
        {
            max=0;
            return null;
        }
        if(temp.right==null)
        {
          //System.out.println(temp.data+"("+temp.freq+")");
          return temp;
        }
        return maxElement(temp.right);
    }

    public Node minElement(Node temp)
    {
        if(temp==null)
        {
            min=0;
             return null;
        }
           
        if(temp.left==null)
        {
          //System.out.println(temp.data+"("+temp.freq+")");
          return temp;
        }
        return minElement(temp.left);
    }

    public void inorder(Node root,StringBuilder output)
    {
      if(root==null)
        return ;
      inorder(root.left,output);
      output.append(""+root.data+"("+root.freq+")");
      //System.out.print(output);
       inorder(root.right,output);
    }

    public void preOrder(Node root,StringBuilder output)
    {
      if(root==null)
        return;
      output.append(""+root.data+"("+root.freq+")");
      preOrder(root.left,output);
      preOrder(root.right,output);
    }

    public void postOrder(Node root,StringBuilder output)
    {
      if(root==null)
        return;
      postOrder(root.left,output);
      postOrder(root.right,output);
      output.append(""+root.data+"("+root.freq+")");
    }

    public static void main(String[] args) 
    {
       Main obj=new Main();
       Scanner sc=new Scanner(System.in);
       String command=sc.nextLine();
       String[] arr=command.split(" ",100);
       StringBuilder output=new StringBuilder();
       int action=Integer.parseInt(arr[0]);
       int data=-1;
      try
      {
          for(int i=0;i<arr.length;)
          {
            if(arr[i].equals("1") || arr[i].equals("2") || arr[i].equals("8"))
            {
              
              action = Integer.parseInt(arr[i]);
              data=Integer.parseInt(arr[i+1]);
              if(action==1)
              {
                obj.insert(data);
              }
              if(action==2)
              {
                Node temp=root;
                temp=obj.searchNode(temp, data);
                if(temp!=null)
                  output.append(""+temp.data+"("+temp.freq+")");
                if(temp==null)
                  output.append(""+data+"(0)");
              }
              if(action == 8)
              {
                 Node temp=root;
                temp=obj.searchNode(temp,data);
                if(temp!=null)
                root=obj.deleteNode(root,temp.data);
              }

              i+=2;
            }
            else
            {
              action=Integer.parseInt(arr[i]);
              if(action==3)
            {
                Node tmax=obj.maxElement(root);
                if(tmax!=null)
                output.append(tmax.data+"("+tmax.freq+")");
                else
                output.append("0(0)");

            }
            if(action==4)
            {
                Node tmin=obj.minElement(root);
                if(tmin!=null)
                output.append(tmin.data+"("+tmin.freq+")");
                else
                output.append("0(0)");
            }
            if(action == 5)
            {
                obj.preOrder(root,output);
              if(root==null)
                {
                //     output.delete(0,output.length());
                //     if(max!=-1 && min!=-1)
                //   output.append("0(0)0(0)");
                // else if(max!=-1 && min==-1)
                //   output.append("0(0)");
                // else if(max==-1 && min!=-1)
                //   output.append("0(0)");
               
                }
            }
            if(action==6)
            {
               obj.postOrder(root,output);
              if(root==null)
                {
                //     output.delete(0,output.length());
                //     if(max!=-1 && min!=-1)
                //   output.append("0(0)0(0)");
                // else if(max!=-1 && min==-1)
                //   output.append("0(0)");
                // else if(max==-1 && min!=-1)
                //   output.append("0(0)");
               
                } 
                
            }
            if(action==7)
            {
              
              obj.inorder(root,output);
              if(root==null)
                {
                //   output.delete(0,output.length());
                //     if(max!=-1 && min!=-1)
                //   output.append("0(0)0(0)");
                // else if(max!=-1 && min==-1)
                //   output.append("0(0)");
                // else if(max==-1 && min!=-1)
                //   output.append("0(0)");
               
                }
                
            }
            if(action==0)
              break;
              i++;
            }
            
          }
          System.out.println(output);
      }
      catch(Exception e)
      {
        System.out.print(e);
      } 
    }
}
