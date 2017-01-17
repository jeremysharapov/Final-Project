import java.util.*;
public class NormalRPG{
    private static String PlayerName;
    private static String EnemyName;
    private static int[] Player;
    private static int[] Slime;
    private static int[] Goblin;
    private static int[] DarkMage;
    private static int[] Dragon;
    
    public NormalRPG(){
	Player = new int[7];
	
	Slime = new int[7];
	setlvl(Slime, 1);
	sethp(Slime, 15);
	setatk(Slime, 5);
	setdef(Slime, 5);
	setspd(Slime, 5);
	setxp(Slime, 1);
	
	Goblin = new int[7];
	setlvl(Goblin, 1);
	sethp(Goblin, 25);
	setatk(Goblin, 15);
	setdef(Goblin, 10);
	setspd(Goblin, 5);
	setxp(Goblin, 2);

	DarkMage = new int[7];
	setlvl(DarkMage, 1);
	sethp(DarkMage, 20);
	setatk(DarkMage, 10);
	setdef(DarkMage, 10);
	setspd(DarkMage, 15);
	setmp(DarkMage, 20);
	setxp(DarkMage, 3);

	Dragon = new int[7];
	setlvl(Dragon, 1);
	sethp(Dragon, 50);
	setatk(Dragon, 20);
	setdef(Dragon, 15);
	setspd(Dragon, 10);
	setmp(Dragon, 10);
	setxp(Dragon, 5);
    }
    
    public static int getlvl(int[] a){
	return a[0];
    }
    public static int gethp(int[] a){
	return a[1];
    }
    public static int getatk(int[] a){
	return a[2];
    }
    public static int getdef(int[] a){
	return a[3];
    }
    public static int getspd(int[] a){
	return a[4];
    }
    public static int getmp(int[] a){
	return a[5];
    }
    public static int getxp(int[] a){
	return a[6];
    }
    
    
    public static void setlvl(int[] a, int b){
	a[0] = b;
    }
    public static void sethp(int[] a, int b){
	a[1] = b;
    }
    public static void setatk(int[] a, int b){
	a[2] = b;
    }
    public static void setdef(int[] a, int b){
	a[3] = b;
    }
    public static void setspd(int[] a, int b){
	a[4] = b;
    }
    public static void setmp(int[] a, int b){
	a[5] = b;
    }
    public static void setxp(int[] a, int b){
	a[6] = b;
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
    
    public void Block(int[] attacker, int[] target){
	int z = (int)(0.5 * (getatk(attacker) - getdef(target)));
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
	System.out.println("Choose your class:\n  [Knight]   [Wizard]   [Warrior]   [Ninja]");
	String s = getInput().toUpperCase();
	if (s.equals("KNIGHT")){
	    setlvl(Player, 1);
	    sethp(Player, 50);
	    setatk(Player, 20);
	    setdef(Player, 15);
	    setspd(Player, 5);
	    setmp(Player, 5);
	    PlayerName = "Knight";
	    System.out.println("You are now a Knight");
	}
	if (s.equals("WIZARD")){
	    setlvl(Player, 1);
	    sethp(Player, 50);
	    setatk(Player, 5);
	    setdef(Player, 5);
	    setspd(Player, 15);
	    setmp(Player, 20);
	    PlayerName = "Wizard";
	    System.out.println("You are now a Wizard");
	}
	if (s.equals("WARRIOR")){
	    setlvl(Player, 1);
	    sethp(Player, 100);
	    setatk(Player, 10);
	    setdef(Player, 15);
	    setspd(Player, 5);
	    setmp(Player, 5);
	    PlayerName = "Warrior";
	    System.out.println("You are now a Warrior");
	}
	if (s.equals("NINJA")){
	    setlvl(Player, 1);
	    sethp(Player, 25);
	    setatk(Player, 15);
	    setdef(Player, 5);
	    setspd(Player, 20);
	    setmp(Player, 10);
	    PlayerName = "Ninja";
	    System.out.println("You are now a Ninja");
	}
    }
    
    private void Battle(int[] player, int[] enemy, int lvl){
	System.out.println(PlayerName + " encountered " + EnemyName + "!");
	ResetStats(lvl);
	while (isDead(player) != true && isDead(enemy) != true){
	    pressEnterToContinue();
	    clearScreen();
	    System.out.println(PlayerName + "'s hp is " + gethp(player));
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
			System.out.println(PlayerName + " gained " + getxp(enemy) + "xp");
			setxp(player, getxp(enemy) + getxp(player));
			levelup(player);
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

    private void levelup(int[] player){
	while (getxp(player) >= getlvl(player)){
	    setxp(player, getxp(player) - getlvl(player));
	    setlvl(player, getlvl(player) + 1);

	    if (PlayerName == "Knight"){
		sethp(player, gethp(player) + 10);
		setatk(player, getatk(player) + 4);
		setdef(player, getdef(player) + 3);
		setspd(player, getspd(player) + 1);
		setmp(player, getmp(player) + 1);
	    }

	    if (PlayerName == "Wizard"){
		sethp(player, gethp(player) + 10);
		setatk(player, getatk(player) + 1);
		setdef(player, getdef(player) + 1);
		setspd(player, getspd(player) + 3);
		setmp(player, getmp(player) + 4);
	    }

	    if (PlayerName == "Warrior"){
		sethp(player, gethp(player) + 20);
		setatk(player, getatk(player) + 2);
		setdef(player, getdef(player) + 3);
		setspd(player, getspd(player) + 1);
		setmp(player, getmp(player) + 1);
	    }

	    if (PlayerName == "Ninja"){
		sethp(player, gethp(player) + 5);
		setatk(player, getatk(player) + 3);
		setdef(player, getdef(player) + 1);
		setspd(player, getspd(player) + 4);
		setmp(player, getmp(player) + 2);
	    }
	    
	    System.out.println(PlayerName + " leveled up! " + PlayerName + " is now level " + getlvl(player));
	}
    }
	
    
    public static void ResetStats(int lvl){
	setlvl(Slime, 1);
	sethp(Slime, 15);
	setatk(Slime, 5);
	setdef(Slime, 5);
	setspd(Slime, 5);
	setxp(Slime, 1);

	setlvl(Goblin, 1);
	sethp(Goblin, 25);
	setatk(Goblin, 15);
	setdef(Goblin, 10);
	setspd(Goblin, 5);
	setxp(Goblin, 2);

	setlvl(DarkMage, 1);
	sethp(DarkMage, 20);
	setatk(DarkMage, 10);
	setdef(DarkMage, 10);
	setspd(DarkMage, 15);
	setmp(DarkMage, 20);
	setxp(DarkMage, 3);

	setlvl(Dragon, 1);
	sethp(Dragon, 50);
	setatk(Dragon, 20);
	setdef(Dragon, 15);
	setspd(Dragon, 10);
	setmp(Dragon, 10);
	setxp(Dragon, 5);

	if(EnemyName == "Slime"){
	    for (int i = lvl; i > 1; i--){
		sethp(Slime, gethp(Slime) + 3);
		setatk(Slime, getatk(Slime) + 1);
		setdef(Slime, getdef(Slime) + 1);
		setspd(Slime, getspd(Slime) + 1);
		setxp(Slime, 2 * getxp(Slime));
	    }
	}

	if(EnemyName == "Goblin"){
	    for (int i = lvl; i > 1; i--){
		sethp(Goblin, gethp(Goblin) + 5);
		setatk(Goblin, getatk(Goblin) + 3);
		setdef(Goblin, getdef(Goblin) + 2);
		setspd(Goblin, getspd(Goblin) + 1);
		setxp(Goblin, 2 * getxp(Goblin));
	    }
	}

	if(EnemyName == "DarkMage"){
	    for (int i = lvl; i > 1; i--){
		sethp(DarkMage, gethp(DarkMage) + 4);
		setatk(DarkMage, getatk(DarkMage) + 2);
		setdef(DarkMage, getdef(DarkMage) + 2);
		setspd(DarkMage, getspd(DarkMage) + 3);
		setmp(DarkMage, getmp(DarkMage) + 4);
		setxp(DarkMage, 2 * getxp(DarkMage));
	    }
	}

	if(EnemyName == "Dragon"){
	    for (int i = lvl; i > 1; i--){
		sethp(Dragon, gethp(Dragon) + 10);
		setatk(Dragon, getatk(Dragon) + 4);
		setdef(Dragon, getdef(Dragon) + 3);
		setspd(Dragon, getspd(Dragon) + 2);
		setmp(Dragon, getmp(Dragon) + 2);
		setxp(Dragon, 2 * getxp(Dragon));
	    }
	}
    }
	
				  
    public static void main(String[] args){
	NormalRPG r = new NormalRPG();
	clearScreen();
	System.out.println("NormalRPG - Starting....Loading........Complete!");
	pressEnterToContinue();
	clearScreen();
	r.ChooseYourClass();
	//Needs tutorial
	System.out.println("You wake up in the middle of the Longa Fruit Forest. You feel very weak...best you head to town.");
	System.out.println("[STAY] and search for clues or [GO] to Longa Town?");
	String s = (getInput()).toUpperCase();
	if (s.equals("GO") || s.equals("STAY")){
	    EnemyName = "Slime";
	    r.Battle(r.Player, r.Slime, 2);
	    if (isDead(r.Player)){
		System.out.println("You have died.");
	    }
	    else {
		clearScreen();
		pressEnterToContinue();
	    }
	}
    }
}

