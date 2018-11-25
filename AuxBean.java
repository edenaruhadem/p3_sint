package Xbean;

public class AuxBean
{
    private String num;
    public AuxBean(){
        num="0";
    }    
    public String getNum()
    {   
        System.out.println("gettttteeeeeeeerrrrr");     
        return (num);

    }
    public void setNum(String a)
    {
        this.num = a;
        System.out.println("set"+a);        
    }    
}