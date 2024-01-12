
package mainclass;

import java.io.Serializable;

public class Square implements Serializable{

   
    private int  content;
 
   Square()
   {
	   this.content = -1;
   }
    public int   get_content()
    {
        return content;
    }
    public void edit(int  c)
    {
        this.content =c;
    }
    
}
