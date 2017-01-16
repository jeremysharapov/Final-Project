import java.util.*;
public class NormalRPG{
//----------------------------------------------------Variables---------------------------------------------------------
  private String PlayerName;
  private String EnemyName;
  
  private int[] Player;
  private int[] Slime;
  
  private int PlayerClass;
  private int QuestCount;
  
  private boolean MageBrooch;
  private boolean TravelerGuide;
  
//--------------------------------------------------Initialization------------------------------------------------------
  public NormalRPG(){
    Player = new int[10];
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
    
    boolean CursedAmulet = false;
    boolean TravelerGuide = false;
    
    int QuestCount = 0;
    
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
  
//---------------------------------------------------Accessors----------------------------------------------------------
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
  
//----------------------------------------------------Mutators----------------------------------------------------------
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
  
  public void setQuestCount(int a){
    QuestCount = a;
  }
  
//-------------------------------------------Boolean mutators and methods-----------------------------------------------
  public void setMageBrooch(boolean a){
    MageBrooch = a;
  }
  
  public void setTravelerGuide (boolean a){
    TravelerGuide = a;
  }  
  
  private static boolean isDead(int[] unit){
    boolean D = false;
    if (gethp(unit) == 0){
      D = true;
    }
    return D;
  }
  
//-----------------------------------------------Battle method options--------------------------------------------------
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
      if (getspd(player) < getspd(enemy) && s.equals("RETURN") == false){
        Attack(enemy, player, EnemyName, PlayerName);
        if (isDead(player)){
          System.out.println(PlayerName + " WAS DEFEATED!");
          pressEnterToContinue();
        }
        else{
          Attack(player, enemy, PlayerName, EnemyName);
        }
      }
      if (s.equals("RETURN") == false){
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
      setmp(attacker, getmp(attacker) - 1);
      System.out.println(attackerName + " just used [Fireball]: 15 fire dmg dealt to " + targetName + "!");
    }
    if (skill.equals("HEAL")){
      int z = (int)(getmaxhp(target) * 1.05);
      sethp(attacker, gethp(attacker) + z);
      setmp(attacker, getmp(attacker) - 1);
      System.out.println(attackerName + " just used [Heal]: Gain " + z + " hp!");
    }
    if (skill.equals("SLASH")){
      sethp(target, gethp(target) - 10);
      setmp(attacker, getmp(attacker) - 1);
      System.out.println(attackerName + " just used [Slash]: 10 damage dealt to " + targetName + "!");
    }
    if (gethp(target) < 0){
        sethp(target, 0);
      }
    if (getmp(attacker) < 0){
      setmp(target, 0);
    }
    if (gethp(attacker) > getmaxhp(attacker)) {
      sethp(attacker, getmaxhp(attacker));
    }
  }

//-----------------------------------------Main methods, while loops, etc----------------------------------------------- 
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
      PlayerClass = 0;
      System.out.println("You are now a Demo");
    }
    pressEnterToContinue();
  }
  
  private void Battle(int[] player, int[] enemy){
    boolean flee = false;
    System.out.println("Something jumps out of the shadows...");
    System.out.println(PlayerName + " encountered " + EnemyName + "!");
    while (isDead(player) != true && isDead(enemy) != true && flee != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println(PlayerName + "(lv." + getlvl(player) + ")");
      System.out.println("        HP is at " + gethp(player) + "/" + getmaxhp(player) + "       MP is at " + getmp(player) + "/" + getmaxmp(player));
      System.out.println(EnemyName + "(lv." + getlvl(enemy) + ")");
      System.out.println("        HP is at " + gethp(enemy) + "/" + getmaxhp(enemy) + "       MP is at " + getmp(enemy) + "/" + getmaxmp(enemy));
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
      if (s.equals("FLEE")){
        flee = true;
      }
    }
  }
  
  private void Map(){
    boolean leave = false;
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("Where to now?\n  Longa Fruit Forest [OUTSKIRTS]\n  Longa Fruit Forest [ROAD]\n  Longa [TOWN]\n  [EXIT] the Game"); 
      String s = (getInput()).toUpperCase();
      if (s.equals("OUTSKIRTS")){
        LongaOutskirts();
      }
      if (s.equals("TOWN")){
        LongaTown();
      }
      if (s.equals("EXIT")){
        leave = true;
      }
    }
  }
  
  private void LongaOutskirts(){
    clearScreen();
    System.out.println("Now traveling to Longa Forest. . . . . .Loading. . .. .. . ....Complete!");
    pressEnterToContinue();
    clearScreen();
    System.out.println("You have arrived at the Longa Fruit Forest Outskirts!");
    System.out.println("The forest is eerily silent.");
    System.out.println("You are filled with trepidation.");
    boolean leave = false;
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("What will you do?\n  [SEARCH] for clues\n  [LEAVE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("SEARCH")){
        LongaOutskirtsCutScene();
      }
      if (s.equals("LEAVE")){
        leave = true;
      }
    }
    Battle(Player, Slime);
  }
  
  private void LongaTown(){
    clearScreen();
    System.out.println("Now traveling to Longa Town. . . . . .Loading. . .. .. . ....Complete!");
    pressEnterToContinue();
    clearScreen();
    System.out.println("You have arrived in Longa Town!");
    System.out.println("Citizens greet you as you walk by; the sounds of happy children and a lively crowd fill the air.");
    System.out.println("You are filled with joy.");
    boolean leave = false;
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("Where to now?\n  [HEALING] Hospital\n  [LONGA] Office\n  [GATE] Entrance\n  [LOOK] around the town\n  [EXAMINE] the Traveler's Guide\n  [LEAVE] Longa Town"); 
      String s = (getInput()).toUpperCase();
      if (s.equals("HEALING")){
        Hospital();
      }
      if (s.equals("LONGA")){
        LongaOffice();
      }
      if (s.equals("GATE")){
        GateEntrance();
      }
      if (s.equals("LOOK")){
        LongaTownCutScene();
      }
      if (s.equals("LEAVE")){
        leave = true;
      }
    }
  }
  
  private void Hospital(){
    clearScreen();
    System.out.println("You enter the Healing Hospital. A woman sits at a desk reading a medical book.");
    boolean leave = false;
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("What will you do?\n  [TALK] to the woman\n  [ENTER] the Healing Room\n  [LEAVE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("TALK")){
        mariaCheckup();
      }
      if (s.equals("ENTER")){
        LongaHealingRoomCutScene();
      }
      if (s.equals("LEAVE")){
        leave = true;
      }
    }
  }
  
  private void mariaCheckup(){
    clearScreen();
    boolean leave = false;
    System.out.println("As you approach the woman she suddenly looks up. On her chest is a nametag that reads Maria.");
    System.out.println("\nMaria: Hiya! Want me to give ya a checkup?");
    pressEnterToContinue();
    System.out.println("Maria: I insist. It's fast and easy and won't cost ya a penny.");
    System.out.println("Maria: Plus I'll heal ya if I find anything wrong for free, doctor's promise.");
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("Maria: So what'll it be?\n  [CHECKUP]\n  [LEAVE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("CHECKUP")){
        CheckupCutScene();
      }
      if (s.equals("LEAVE")){
        leave = true;
      }
    }
    System.out.println("Maria: Seeya around!");
  }
  
  private void LongaOffice(){
    clearScreen();
    System.out.println("You enter the Longa Office. The Mayor sits at his desk, ogling at a rather suspicious looking piece of paper.");
    boolean leave = false;
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("What will you do?\n  [TALK] to the Mayor\n  [LEAVE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("TALK")){
        MayorTalk();
      }
      if (s.equals("LEAVE")){
        leave = true;
      }
    }
  }
  
  private void MayorTalk(){
    clearScreen();
    boolean leave = false;
    System.out.println("As you approach the Mayor he suddenly looks up and jumps, hastily stuffing the paper(read: picture) into a random drawer.");
    System.out.println("\nMayor: Ahem! You shouldn't sneak up on me like that youngster, ho ho~! Who knows when this ol' heart of mine might kick the bucket!");
    pressEnterToContinue();
    System.out.println("He leans forward.");
    System.out.println("\nMayor: Let's just keep what you saw a secret, eh? A single man's gotta find some source of spice in his life after all, ho ho~!");
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("Mayor: Now, what is it that you need?\n  [QUEST]\n  [LEAVE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("QUEST")){
        QuestCutScene();
      }
      if (s.equals("LEAVE")){
        leave = true;
      }
    }
    System.out.println("Mayor: Farewell youngster!");
  }
    
  private void GateEntrance(){
    clearScreen();
    if (QuestCount >= 2){
      System.out.println("You approach the Gate Entrance. Jaryl leans against one side of the stone archway focussed on something in his hands.");
      boolean leave = false;
      while (leave != true){
        pressEnterToContinue();
        clearScreen();
        System.out.println("What will you do?\n  [TALK] to Jaryl\n  [LEAVE]");
        String s = (getInput()).toUpperCase();
        if (s.equals("TALK")){
          JarylTalk();
          leave = true;
        }
        if (s.equals("LEAVE")){
          leave = true;
        }
      }
    }
    else {
      System.out.println("You approach the Gate Entrance. You don't see anyone nearby.");
    }
  }
  
  private void JarylTalk(){
    clearScreen();
    boolean leave = false;
    System.out.println("As you approach the man you notice him sharpening a small dagger.");
    System.out.println("He sees you and puts his items away, before standing up from his slouch.");
    while (leave != true){
      pressEnterToContinue();
      clearScreen();
      System.out.println("Jaryl: Hello, what is it that you need?\n  [JOIN] the search party\n  [LEAVE]");
      String s = (getInput()).toUpperCase();
      if (s.equals("JOIN")){
        JoinSearchPartyCutScene();
        leave = true;
      }
      if (s.equals("LEAVE")){
        leave = true;
        System.out.println("Jaryl: I see, stay safe out there.");
      }
    }
  }
    
  
      
          
//---------------------------------------Cut Scenes(and Quest Item Get!)------------------------------------------------
  private void LongaOutskirtsCutScene(){
    clearScreen();
    setMageBrooch(true);
    System.out.println("You search the area for clues.");
    System.out.println(".....!");
    pressEnterToContinue();
    System.out.println("You found a Mage Brooch!\nYou turn it over to find a partially scratched off name: Tol...e F...ryn");
    pressEnterToContinue();
    System.out.println("You carefully put it into your bag.");
  }
  
  private void LongaTownCutScene(){
    clearScreen();
    if (TravelerGuide == false){
      setTravelerGuide(true);
      System.out.println("You search the area for anything.");
      System.out.println(".....!");
      pressEnterToContinue();
      System.out.println("You found a Traveler Guide!\nA quick glance reveals that it's chock full of very useful information!");
      pressEnterToContinue();
      System.out.println("You put it in the front pocket of your bag before continuing to look around.");
      pressEnterToContinue();
      clearScreen();
    }
    System.out.println("The town is brimming with a myraid of sights, smells, and sounds.");
    System.out.println("To the North is a giant Longa Eternal-Blossom tree, its flowers a cosmic cloud of blues, purples, and pinks.");
    pressEnterToContinue();
    System.out.println("To the West is a magnificent marble fountain, carved to resemble the Longa Goddess, and from her hands flows pure, crystal water.");
    System.out.println("The Longa Office is right behind it.");
    pressEnterToContinue();
    System.out.println("To the East is Residential District. Clothes of a rainbow of colors stream across the sky.");
    System.out.println("Further down the street you see the Healing Hospital.");
    pressEnterToContinue();
    System.out.println("To the South lies the Gate Entrance to Longa Town. It stands tall and strong, a large stone guardian overseeing all who come in.");
  }
  
  private void DeadCutScene(){
    clearScreen();
    System.out.println("You passed out.");
    pressEnterToContinue();
    sethp(Player, getmaxhp(Player));
    setmp(Player, getmaxmp(Player));
    System.out.println("???: ...Ho ho~! Looks like you're finally awake!");
    pressEnterToContinue();
    System.out.println("You look around to find yourself in the Healing Room. In front of you stands the Mayor of Longa Town.");
    pressEnterToContinue();
    System.out.println("Mayor: Jaryl happened to catch the last bit of your scuffle with that creature...");
    System.out.println("Mayor: He gave them a good ol' beating, ho ho~! Best thank him later when you can.");
    pressEnterToContinue();
    System.out.println("Mayor: Well, you're free to go whenever.");
    System.out.println("Mayor: Just don't let Maria see you, else she might make you do another checkup, ho ho~!");
    pressEnterToContinue();
    System.out.println("Mayor: ...Though I personally wouldn't mind checking her up, hoo!");
    System.out.println("\nThe Mayor jubilantly strolls out of the room, leaving you alone.");
  }
  
  private void CheckupCutScene(){
    clearScreen();
    System.out.println("She gets up from her desk and guides you to a chair nearby. She then casts [Analyze] on you.");
    System.out.println("\nMaria: Alrighty let's see here...");
    pressEnterToContinue();
    if (gethp(Player) < getmaxhp(Player) || getmp(Player) < getmaxmp(Player)){
      System.out.println("Maria: Looks like you're a bit under the weather, buddy. Don't worry I'll heal ya in a jiffy.");
      pressEnterToContinue();
      System.out.println("You feel yourself being filled with energy as Maria casts [Ultimate Heal] on you.");
      sethp(Player, getmaxhp(Player));
      setmp(Player, getmaxmp(Player));
      System.out.println("    ~HP maxed! MP maxed!~");
      System.out.println("\nMaria: There ya go, now you're fit as a fiddle!");
      pressEnterToContinue();
      System.out.println("She gives you a slap on the back, before moving back to her desk.");
    }
    else {
      System.out.println("Maria: Search showed a negative in injuries, soldier. Looks like I'm gonna hafta give ya a clean bill of health!");
      pressEnterToContinue();
      System.out.println("She grins at you before giving you a slap on the back");
      System.out.println("\nMaria: Good job keeping yourself safe out there. Now let's hope ya stay that way buddy.");
      pressEnterToContinue();
      System.out.println("She moves back to her desk");
    }
    System.out.println("\nMaria: Let me know if you need anything else, ok?");
  }
  
  private void LongaHealingRoomCutScene(){
    clearScreen();
    System.out.println("You enter the Healing Room.");
    pressEnterToContinue();
    System.out.println("Inside you see cabinets filled with pills and herbs. By the windows are two adjustable beds.");
    System.out.println("It smells like disinfectants and rubbing alcohol.");
    pressEnterToContinue();
    System.out.println("You find nothing of interest here so you go back to the previous room.");
  }
  
  private void QuestCutScene(){
    clearScreen();
    if ((MageBrooch == false && QuestCount == 1) ||
        (MageBrooch == true  && QuestCount == 2) ||
                               (QuestCount == 3)){
      String x = "";
      if (QuestCount == 1){
        x += "search the Longa Outskirts for Toliare.";
      }
      if (QuestCount == 2){
        x += "meet Jaryl at the Gate Entrance to go find Toliare.";
      }
      if (QuestCount == 3){
        x += "meet Jaryl at the Longa Fruit Forest Road to go find Toliare! (Warning: Prepare for battle)";
      }
      System.out.println("Mayor: Ho ho~ welcome back, Adventurer, did you forget your Quest?");
      System.out.println("Mayor: Don't forget, your objective is to " + x);
      System.out.println("Mayor: Good luck!");
    }
    if (MageBrooch == false && QuestCount == 0){
      System.out.println("Mayor: Ho ho~, I sense an adventurer here! Only folks like you ask a town's head if they've got any Quests!");
      System.out.println("Mayor: Worry not, we always have one or two coming in. Let's see here...");
      pressEnterToContinue();
      System.out.println("Mayor: Oho, here it is! This one came in two days ago, some worried folk heard a commotion in the forest outskirts.");
      System.out.println("Mayor: We sent dear Toliare to check it out yesterday afternoon.");
      pressEnterToContinue();
      System.out.println("He frowns, eyes pinched with worry.");
      System.out.println("\nMayor: Unfortunately she hasn't come back yet. I was about to ask Jaryl to go look for her actually.");
      System.out.println("Mayor: But now that you're here it's best for you to go eh? Can't leave the Gate unprotected for too long after all.");
      pressEnterToContinue();
      System.out.println("Mayor: Besides, that close to the border of the forest there's only slimes.");
      System.out.println("Mayor: Good luck youngster.");
      pressEnterToContinue();
      clearScreen();
      System.out.println("  ~Story Quest: Longa Outskirts BEGIN!~");
      System.out.println("\nObjective: Search the Longa Forest Outskirts for Toliare!");
      setQuestCount(1);
    }
    if (MageBrooch == true && QuestCount == 1){
      System.out.println("  ~Story Quest: Longa Outskirts CLEAR!~");
      System.out.println("\nYou carefully pull out the Mage Brooch from your bag and show it to the Mayor.");
      System.out.println("\nMayor: Oh no that's...");
      pressEnterToContinue();
      System.out.println("He grabs an orange crystal on his table and shouts at it");
      System.out.println("\nMayor: Jaryl, I need you immediately!");
      pressEnterToContinue();
      System.out.println("It flashes briefly, and suddenly a man climbs through the large window in the office.");
      System.out.println("\n???: What is it, Mayor?");
      System.out.println("\nMayor: Jaryl, the youngster here found this in the forest outskirts.");
      pressEnterToContinue();
      clearScreen();
      System.out.println("Jaryl takes the Mage Brooch, turning it over to see the name on the back. His eyes widen before he suddenly closes them.");
      System.out.println("  ~A foreign magic fills the air for a brief moment~");
      pressEnterToContinue();
      System.out.println("Jaryl: This is Toliare's alright. I sensed another's magic clinging to this Brooch though. I fear it belongs to whatever took her.");
      System.out.println("\nMayor: Something? Not someone?");
      System.out.println("\nJaryl: I've been feeling an abnormal amount of magic gathered in the heart of the mountains recently. It hasn't moved much so I haven't sought it out yet but...");
      pressEnterToContinue();
      System.out.println("Mayor: ...Is it sentinent?");
      System.out.println("\nJaryl: Something with that much magic almost certainly will be.");
      pressEnterToContinue();
      clearScreen();
      System.out.println("Mayor: What could it possibly want with Toliare?");
      System.out.println("\nJaryl: I wouldn't know, but it's best we rescue her immediately. I'll go prepare, please gather anyone else capable to help and send them to the Gate Entrance, Mayor.");
      System.out.println("\nJaryl leaves through the window. The Mayor then turns to you.");
      pressEnterToContinue();
      System.out.println("Mayor: Dear Adventurer, I know this seems daunting for one so young like you but can you please go with Jaryl?");
      System.out.println("Mayor: He is the only one in this town I would trust to safely go so far into the forest, but he'll need help.");
      pressEnterToContinue();
      System.out.println("Mayor: Thank you, Adventurer, and stay safe. If it get's too hard don't worry, Jaryl is a capable man, ho ho~! He'll get you out in a jiffy.");
      pressEnterToContinue();
      clearScreen();
      System.out.println("  ~Story Quest: Join Jaryl's Search Party BEGIN!~");
      System.out.println("\nObjective: Meet Jaryl at the Gate Entrance to go find Toliare!");
      setQuestCount(2);
    }
  }
  
  private void JoinSearchPartyCutScene(){
    clearScreen();
    System.out.println("  ~Story Quest: Join Jaryl's Search Party CLEAR!~");
    System.out.println("\nJaryl: So the Mayor told you to help me? Be careful out there, some of those creatures are fast. Stay near me at all times just in case.");
    pressEnterToContinue();
    System.out.println("Jaryl: Did he ask anyone else to come?");
    pressEnterToContinue();
    System.out.println("Jaryl: I see. Alright then. I'll go ahead first and scout out the area alright? Meet me at the Longa Fruit Forest Road."); 
    System.out.println("\nHe speaks to a small orange crystal in his hand before sprinting into the forest. He's fast!");
    pressEnterToContinue();
    clearScreen();
    System.out.println("  ~Story Quest: Meet Jaryl at the Longa Fruit Forest Road Begin!~");
    System.out.println("\nObjective: Meet Jaryl at the Longa Fruit Forest Road to go find Toliare! (Warning: Prepare for battle)");
    setQuestCount(3);
  }
    
  
//------------------------------------------------Scanner shtuff--------------------------------------------------------
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
  
//--------------------------------------------Actual Game(aka the main)-------------------------------------------------
  public static void main(String[] args){
    NormalRPG r = new NormalRPG();
    clearScreen();
    System.out.println("NormalRPG - Starting. . . . . .Loading. . .. .. . ....Complete!");
    pressEnterToContinue();
    clearScreen();
    System.out.println("You wake up in the outskirts of the Longa Fruit Forest. You feel very weak...best you head to town.");
    pressEnterToContinue();
    r.Battle(r.Player, r.Slime);
    if (isDead(r.Player)){
      r.DeadCutScene();
    }
    else {
      clearScreen();
      System.out.println("Congratulations! You have leveled up!\nYou are now level 2");
      pressEnterToContinue();
      r.ChooseYourClass(); 
    }
    r.LongaTown();
    r.Map();
  }
}

