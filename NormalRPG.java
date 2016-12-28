public class NormalRPG{
    private String[] Demo;
    private String[] Slime;
 
    public NormalRPG(){
      Demo[10];
      Slime[10];
    }
    
    public int getlvl(String[] a) {
      return a[0];
    }
    public int gethp(String[] a){
      return a[1];
    }
    public int getatttack(String[] a){
      return a[2];
    }
    public int getdef(String[] a){
      return a[3];
    }
    public int getspd(String[] a){
      return a[4];
    }
    
    public int setlvl(String[] a, int b){
      a[0] = b;
    }
    public int sethp(String[] a, int b){
      a[1] = b;
    }
    public int setattack(String[] a, int b){
      a[2] = b;
    }
    public int setdef(String[] a, int b){
      a[3] = b;
    }
    public int setspd(String[] a, int b){
      a[4] = b;
    }
}