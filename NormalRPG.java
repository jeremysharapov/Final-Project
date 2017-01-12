import java.util.*;
public class NormalRPG{
  private String PlayerName;
  private String EnemyName;
  private int[] Player;
  private int[] Slime;
  
  public NormalRPG(){
    Player = new int[5];
    setlvl(Player, 1);
    sethp(Player, 25);
    setatk(Player, 6);
    setdef(Player, 2);
    setspd(Player, 3);
    PlayerName = "You";
    
    Slime = new int[5];
    setlvl(Slime, 1);
    sethp(Slime, 13);
    setatk(Slime, 5);
    setdef(Slime, 1);
    setspd(Slime, 2);
    EnemyName = "Slime";
  }
  
  public int getlvl(int[] a){
    return a[0];
  }
  public static int gethp(int[] a){
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
  public void setatk(int[] a, int b){
    a[2] = b;
  }
  public void setdef(int[] a, int b){
    a[3] = b;
  }
  public void setspd(int[] a, int b){
    a[4] = b;
  }
  
  public void Attack(int[] attacker, int[] target, String attackername, String targetname){
    int c = (int)(Math.random() * 20);
    if (c == 0){
      System.out.println(attackername + " MISSED!");
    }
    if (c == 19){
      int z = (int)(getattack(attacker) * 3) - (getdef(target));
      if (z < 0) {
        z = 0;
      }
      sethp(target, gethp(target) - z);
      if (gethp(target) < 0){
        sethp(target, 0);
      }
      System.out.println(attackername + " just did a CRITICAL HIT: " + z + " damage dealt to " + targetname);
    }
    if (c > 0 && c < 19) {
      int z = (int)(getattack(attacker) - (getdef(target)));
      if (z < 0) {
        z = 0;
      }
      sethp(target, gethp(target) - z);
      if (gethp(target) < 0){
        sethp(target, 0);
      }
      System.out.println(attackername + " did " + z + " damage to " + targetname);
    }
  }
  
  public void Block(int[] attacker, int[] target){
    int z = (int)(0.5 * (getattack(attacker) - getdef(target)));
    sethp(target, gethp(target) - z);
    System.out.println("Blocked: " + z + " damage dealt");
  }
  
  private static void clearScreen(){
    System.out.print("\033[2J\033[;H");
  }
  
  private static void pressEnterToContinue(){ 
    System.out.println("Press enter to continue...");
    Scanner scanner = new Scanner(System.in);
    String S = scanner.nextLine();
  }
  
  private static String getInput() {
    Scanner scanner = new Scanner(System.in);
    String S = scanner.nextLine(); 
    return S;
  }
  
  private static boolean isDead(int[] unit){
    boolean D = false;
    if (gethp(unit) == 0){
      D = true;
    }
    return D;
  }
  
  private void ChooseYourClass(){
    clearScreen();
    System.out.println("Choose your class:\n  [Demo]");
    String s = getInput().toUpperCase();
    if (s.equals("DEMO")){
      setlvl(Player, 2);
      sethp(Player, 27);
      setatk(Player, 7);
      setdef(Player, 3);
      setspd(Player, 3);
      PlayerName = "{Demo}You";
      System.out.println("You are now a Demo");
    }
  }
  
  private void Battle(int[] player, int[] enemy){
    System.out.println(PlayerName + " encountered " + EnemyName + "!");
    while (isDead(player) != true && isDead(enemy) != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println(PlayerName + "r hp is " + gethp(player));
      System.out.println(EnemyName + "'s hp is " + gethp(enemy));
      System.out.println("\n     What will you do?\n  [ATTACK]        [BLOCK]\n  [SKILLS]        [ITEMS]\n  [ANALYZE]       [FLEE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("ATTACK")){
        if (getspd(player) < getspd(enemy)){
          Attack(enemy, player, EnemyName, PlayerName);
          if (isDead(player)){
            System.out.println(PlayerName + " WAS DEFEATED!");
            pressEnterToContinue();
          }
          else{
            Attack(player, enemy, PlayerName, EnemyName);
          }
        }
        else{
          Attack(player, enemy, PlayerName, EnemyName);
          if (isDead(enemy)){
            System.out.println(EnemyName + " WAS DEFEATED!");
            pressEnterToContinue();
          }
          else{
            Attack(enemy, player, EnemyName, PlayerName);
          }
        }
      }
      if (s.equals("BLOCK")){
        Block(enemy, player);
      }
    }
  }
  
  public static void main(String[] args){
    NormalRPG r = new NormalRPG();
    clearScreen();
    System.out.println("NormalRPG - Starting....Loading........Complete!");
    pressEnterToContinue();
    clearScreen();
    //Needs tutorial
    System.out.println("You wake up in the middle of the Longa Fruit Forest. You feel very weak...best you head to town.");
    System.out.println("[STAY] and search for clues or [GO] to Longa Town?");
    String s = (getInput()).toUpperCase();
    if (s.equals("GO") || s.equals("STAY")){
      r.Battle(r.Player, r.Slime);
      if (isDead(r.Player)){
        System.out.println("You have died.");
      }
      else {
        clearScreen();
        System.out.println("Congratulations! You have leveled up!\nYou are now level 2");
        pressEnterToContinue();
        r.ChooseYourClass();
      }
    }
  }
}

