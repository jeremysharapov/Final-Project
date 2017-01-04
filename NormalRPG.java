import java.util.*;

public class NormalRPG{
    private int[] Demo;
    private int[] Slime;
    
    public NormalRPG(){
      int[] Demo = new int[5];
      setlvl(Demo, 1);
      sethp(Demo, 25);
      setatk(Demo, 6);
      setdef(Demo, 2);
      setspd(Demo, 3);
      int[] Slime = new int[5];
      setlvl(Slime, 1);
      sethp(Slime, 10);
      setatk(Slime, 5);
      setdef(Slime, 1);
      setspeed(Slime, 2);
    }
    
    public int getlvl(int[] a){
      return a[0];
    }
    public int gethp(int[] a){
      return a[1];
    }
    public int getatk(int[] a){
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
    public void setatk(int[] a, int b){
      a[2] = b;
    }
    public void setdef(int[] a, int b){
      a[3] = b;
    }
    public void setspd(int[] a, int b){
      a[4] = b;
    }
    
    public void BATTLE(int[] attacker, int[] target){
      //if input block Block(attacker, target);
      if (getspd(attacker) > getspd(target)){
        Attack(attacker, target);
        Attack(target, attacker);
      }
      if (getspd(target) > getspd(attacker)){
        Attack(target, attacker);
        Attack(attacker, target);
      }
    }   
    
    public void Attack(int[] attacker, int[] target){
      int c = (int)(Math.random() * 20);
      if (c == 0){
        Sysgtem.out.println("MISS");
      }
      if (c == 19){
        int z = (int)(getatk(attacker) * 3) - (getdef(target));
        if (z < 0) {
          z = 0;
        }
        sethp(target, gethp(target) - z);
        if (gethp(target) < 0){
          sethp(target, 0);
        }
        System.out.println("CRITICAL HIT:" + z + " damage dealt");
      }
      else {
        int z = (int)(getatk(attacker) - (getdef(target)));
        if (z < 0) {
          z = 0;
        }
        sethp(target, gethp(target) - z);
        if (gethp(target) < 0){
          sethp(target, 0);
        }
        System.out.println(z + " damage dealt");
      }
      if (gethp(target) == 0){
        System.out.println(target + "WAS DEFEATED!");
      } 
    }

    public String Block(int[] attacker, int[] target){
      int z = (int)(0.5 * (getatk(attacker) - getdef(target)));
      sethp(target, gethp(target) - z);
      return "BLOCKED:" + z + "damage dealt";
    }
    
    private static void clearScreen(){
      System.out.print("\033[2J\033[;H");
    }
    
    private static void pressEnterToContinue(){ 
      System.out.println("Press enter to continue...");
      try{
        System.in.read();
      }  
      catch(Exception e){
      }  
    }
    
    private static String getInput() {
      Scanner scanner = new Scanner(System.in);
      return scanner.nextLine();
    }
    
    public static void main(String[] args){
      clearScreen();
      System.out.println("Demo was attacked by Slime!");
      pressEnterToContinue();
      System.out.println("What will you do?                      ATTACK                           BLOCK");
      System.out.println(getInput());
    }
}



