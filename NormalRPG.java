import java.util.*;
public class NormalRPG{
  private String PlayerName;
  private String EnemyName;
  private int[] Player;
  private int[] Slime;
  
  public NormalRPG(){Player = new int[10];
    setlvl(Player, 1);
    sethp(Player, 25);
    setmaxhp(Player, 25);
    setmp(Player, 7);
    setmaxmp(Player, 7);
    setatk(Player, 6);
    setdef(Player, 2);
    setspd(Player, 3);
    setskills(Player, 3);
    PlayerName = "You";
    
    Slime = new int[10];
    setlvl(Slime, 1);
    sethp(Slime, 13);
    setmaxhp(Slime, 13);
    setmp(Slime, 0);
    setmaxmp(Slime, 0);
    setatk(Slime, 5);
    setdef(Slime, 1);
    setspd(Slime, 2);
    setskills(Slime, 0);
    EnemyName = "Slime";
  }
  
  public int getlvl(int[] a){
    return a[0];
  }
  public static int gethp(int[] a){
    return a[1];
  }
  public static int getmp(int[] a){
    return a[2];
  }
  public int getatk(int[] a){
    return a[3];
  }
  public int getdef(int[] a){
    return a[4];
  }
  public int getspd(int[] a){
    return a[5];
  }
  public int getskills(int[] a){
    return a[6];
  }
  public static int getmaxhp(int[] a){
    return a[7];
  }
  public static int getmaxmp(int[] a){
    return a[8];
  }
  
  
  public void setlvl(int[] a, int b){
    a[0] = b;
  }
  public void sethp(int[] a, int b){
    a[1] = b;
  }
  public void setmp(int[] a, int b){
    a[2] = b;
  }
  public void setatk(int[] a, int b){
    a[3] = b;
  }
  public void setdef(int[] a, int b){
    a[4] = b;
  }
  public void setspd(int[] a, int b){
    a[5] = b;
  }
  public void setskills(int[] a, int b){
    a[6] = b;
  }
  public void setmaxhp(int[] a, int b){
    a[7] = b;
  }
  public void setmaxmp(int[] a, int b){
    a[8] = b;
  }
  
  public void Attack(int[] attacker, int[] target, String attackername, String targetname){
    int c = (int)(Math.random() * 20);
    if (c == 0){
      System.out.println(attackername + " MISSED!");
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
      System.out.println(attackername + " just did a CRITICAL HIT: " + z + " damage dealt to " + targetname);
    }
    if (c > 0 && c < 19) {
      int z = (int)(getatk(attacker) - (getdef(target)));
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
  
  public void Block(int[] attacker, int[] target, String attackerName, String targetName){
    int z = (int)(0.5 * (getatk(attacker) - getdef(target)));
    sethp(target, gethp(target) - z);
    System.out.println(targetName + " blocked!\n" + attackerName + " did " + z + " damage to " + targetName);
  }
  
  public void Skills(int[] player, int[] enemy){
    clearScreen();
    String x = "";
    if (getskills(player) == 0){
      System.out.println("You seem to have forgotten how to use your skills.");
    }
    else{
      System.out.println("    Your Skill Knowledge  ");
      if (getskills(player) > 0){
        System.out.println(" 1. Fireball: Deal 15 fire dmg to enemy");
        x += "   [FIREBALL]\n";
        if (getskills(player) > 1){
          System.out.println(" 2. Heal: Heal 5% of your max mp");
          x += "   [HEAL]\n";
          if (getskills(player) > 2){
            System.out.println(" 3. Slash: Instantly deal 10 damage to the enemy, ignores defense and defensive traits (block included)");
            x += "   [SLASH]\n";
          }
        }
      }
      System.out.println("\n  What will you choose?\n" + x + "   [RETURN] to the fight menu");
      String s = (getInput()).toUpperCase();
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
        Skillattack(Player, enemy, PlayerName, EnemyName, s);
        if (isDead(enemy)){
          System.out.println(EnemyName + " WAS DEFEATED!");
          pressEnterToContinue();
        }
        else{
          Attack(enemy, player, EnemyName, PlayerName);
        }
      }
    }
  }
  
  public void Skillattack(int[] attacker, int[] target, String attackerName, String targetName, String skill){
    if (skill.equals("FIREBALL")){
      sethp(target, gethp(target) - 15); //FOR NOW NO ONE HAS FIRE IMMUNITY SO THIS IS RAW DMG
      System.out.println(attackerName + " just used Fireball: 15 fire dmg dealt to " + targetName + "!");
    }
    if (skill.equals("HEAL")){
      int z = (int)(getmaxhp(target) * 1.05);
      sethp(attacker, gethp(attacker) + z);
      if (gethp(attacker) > getmaxhp(attacker)) {
        sethp(attacker, getmaxhp(attacker));
      }
      System.out.println(attackerName + " just used Heal: Gain " + z + " hp!");
    }
    if (skill.equals("SLASH")){
      sethp(target, gethp(target) - 10);
      System.out.println(attackerName + " just used Slash: 10 damage dealt to " + targetName + "!");
    }
    if (gethp(target) < 0){
        sethp(target, 0);
      }
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
    System.out.println("Something jumps out of the shadows...");
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
        Block(enemy, player, EnemyName, PlayerName);
      }
      if (s.equals("SKILLS")){
        Skills(player, enemy);
      }
    }
  }
  
  public static void main(String[] args){
    NormalRPG r = new NormalRPG();
    clearScreen();
    System.out.println("NormalRPG - Starting. . . . . .Loading. . .. .. . ....Complete!");
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

