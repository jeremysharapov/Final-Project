public class NormalRPG{
    private int[] Demo;
    private int[] Slime;
 
    public NormalRPG(){
      Demo = new int[10];
      Slime = new int[10];
    }
    
    public int getlvl(int[] a) {
      return a[0];
    }
    public int gethp(int[] a){
      return a[1];
    }
    public int getattack(int[] a){
      return a[2];
    }
    public int getdef(int[] a){
      return a[3];
    }
    public int getspd(int[] a){
      return a[4];
    }
    
    public void setlvl(int[] a, int b){
      a[0] = b;
    }
    public void sethp(int[] a, int b){
      a[1] = b;
    }
    public void setattack(int[] a, int b){
      a[2] = b;
    }
    public void setdef(int[] a, int b){
      a[3] = b;
    }
    public void setspd(int[] a, int b){
      a[4] = b;
    }
    
    public String Attack(int[] attacker, int[] target){
      int c = (int)(Math.random() * 20);
      if (c == 0){
        return "MISS";
      }
      if (c == 19){
        int z = (int)(getattack(attacker) * 1.5) - (getdef(target));
        if (z < 0) {
          z = 0;
        }
        sethp(target, gethp(target) - z);
        if (gethp(target) < 0){
          sethp(target, 0);
        }
        return "CRITICAL HIT:" + z + " damage dealt";
      }
      else {
        int z = (int)(getattack(attacker) - (getdef(target)));
        if (z < 0) {
          z = 0;
        }
        sethp(target, gethp(target) - z);
        if (gethp(target) < 0){
          sethp(target, 0);
        }
        return "" + z + " damage dealt";
      }
    }
          
                                           
    }
    
    

