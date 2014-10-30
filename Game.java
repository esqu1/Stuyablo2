import java.util.Random;
import java.util.Scanner;
import java.lang.InterruptedException;
public class Game{
    private static int n, m = 0;
    private static Adventurer[] players;
    private static int[] stats = new int[12];
    
    public static boolean isInt(String str){
	if(str.equals("")){
	    return false;
	}
	boolean valid = true;
	for(int i = 0; i < str.length(); i++){
	    if(! Character.isDigit(str.charAt(i))){
	        return false;
	    }
	}
	return true;
    }

    public static Adventurer setupPlayer(){
	Adventurer player;
	Random r = new Random();
	Scanner s = new Scanner(System.in);

	System.out.println("New player! What will be your adventurer's name?");
	String name = s.nextLine();
	System.out.println("Welcome, " + name + "! Choose a class:\nA : Warrior\nB : Wizard\nC : Rogue\nD : Martial Artist");
	String choice = s.nextLine();
	while(! choice.equalsIgnoreCase("A") && ! choice.equalsIgnoreCase("B") && ! choice.equalsIgnoreCase("C") && !choice.equalsIgnoreCase("D")){ // if they enter something invalid
	    System.out.println("Invalid response received. Please try again.");
	    choice = s.nextLine();
	}
	if(choice.equals("A")){
	    player = new Warrior(name);
	    System.out.println("You chose Warrior!");
	}else if(choice.equals("B")){
	    player = new Wizard(name);
	    if(name.equals("Harry")){
		System.out.println("Yer a Wizard, " + name + "!");
	    }else{
		System.out.println("You chose Wizard!");
	    }
	}else if(choice.equals("C")){
	    player = new Rogue(name);
	    System.out.println("You chose Rogue!");
	}else{
	    player = new MartialArtist(name);
	    System.out.println("You chose Martial Artist!");
	}
	setupStats(player);
	return player;
    }

    public static Adventurer[] setupPlayers(){
	players = new Adventurer[4];
	System.out.println("Your team will consist of four players.");
	for(int i = 0; i < 4; i++){
	    players[i] = setupPlayer();
	}
	return players;
    }
    
    public static Adventurer setupOpp(){
	Random r = new Random();
	Adventurer opponent;
	int oppChoice = r.nextInt(3) + 1;
	if(oppChoice == 1){
		opponent = new Warrior();
		System.out.println("Your opponent is a Warrior!");
	}else if(oppChoice == 2){
	    opponent = new Wizard();
	    System.out.println("Your opponent is a Wizard!");
	}else{
	    opponent = new Rogue();
	    System.out.println("Your opponent is a Rogue!");
	}
	return opponent;
    }

    public static void setupStats(Adventurer player){
	Scanner s = new Scanner(System.in);
	System.out.println("\nYou will now need to distribute 30 points among your strength, dexterity, and integrity.");
	System.out.println("Enter how many strength points you want. (max 30) Your strength affects the power of your attacks.");
	
	boolean t = true;
	String str = s.nextLine();
	while(t){
	    while(! isInt(str)){
		System.out.println("Invalid response received. Please try again.");
		str = s.nextLine();
	    }
	    int i = Integer.parseInt(str);
	    if(i <= 30){
		player.setSTR(i);
		stats[m] = i;
		m++;
		t = false;
	    }else{
		System.out.println("That number is invalid.");
		str = s.nextLine();
	    }
	}
	        
	
	System.out.println("Now set your dexterity. (max " + (30 - player.getSTR()) + ") Dexterity affects your accuracy relative to others'. \nThe remaining points will to integrity, which affects your special attack accuracy.");
	t = true;
	str = s.nextLine();
	while(t){
	    while(! isInt(str)){
		System.out.println("Invalid response received. Please try again.");
		str = s.nextLine();
	    }
	    int j = Integer.parseInt(str);
	    if(j <= 30 - player.getSTR()){
		player.setDEX(j);
		player.setINT(30 - j - player.getSTR());
		stats[m] = j;
		m++;
		stats[m] = 30 - j - player.getSTR();
		m++;
		t = false;
	    }else{
		System.out.println("That number is invalid.");
		str = s.nextLine();
	    }
	}
    }

    public static boolean checkHealth(){
	for(int i = 0; i < 4; i++){
	    if(players[i].getHP() > 1){
		return false;
	    }
	}
	return true;
    }

    public static String getAllStats(){
	String s = "";
	for(int i = 0; i < 4; i++){
	    s += players[i].getStats() + "\n";
	}
	return s;
    }
    
    public static void combat(Adventurer[] players, Adventurer opponent){
	Random r = new Random();
	Scanner s = new Scanner(System.in);
	boolean endmatch = true;
	String loser = "nobody";
        System.out.println("\n********** GAME BEGINS **********\n");
	

	if(n == 1){
	    for(int i = 0; i < 4; i++){
		
		System.out.println("********** " + players[i].getName() + "'S TURN **********\n");
		if(players[i].getHP() < 1){
		    System.out.println(players[i].getName() + " is down! He cannot make a move!");
		}else{
		    System.out.println("Current stats:\n" + getAllStats() + "\n" + opponent.getStats() + "\n");
		    System.out.println("Choose an action:\nA : attack\nS : special attack\nG : give up");
		    String act = s.nextLine();	    
		    
		    while(! act.equals("A") && ! act.equals("S") && ! act.equals("G")){
			System.out.println("Invalid response received. Please try again.");
			act = s.nextLine();
		    }
		    if(act.equals("A")){
			players[i].attack(opponent);
		    }else if(act.equals("S")){
			players[i].specialAttack(opponent);
		    }else if(act.equals("G")){
			endmatch = false;
			loser = "players";
		    }
		    if(opponent.getHP() < 1){
			endmatch = false;
			loser = "opponent";
		    }
		}
		
	    }
	}
	String act2 = "";
	while(endmatch){
	    System.out.println("********** OPPONENT'S TURN **********\n");
	    if(n == 2){
		System.out.println("Current stats:\n" + getAllStats() + "\n" + opponent.getStats() + "\n");
	    }
	    double chance = r.nextDouble();
	    int target = r.nextInt(4);
	    if(chance < 0.6){
		opponent.attack(players[target]);
	    }else{
		if(opponent.getSC() > 5){
		    opponent.specialAttack(players[target]);
		}else{
		    opponent.attack(players[target]);
		} 
	    }
	    if(checkHealth()){
		endmatch = false;
		loser = "players";
		break;
	    }

	    for(int i = 0; i < 4; i++){
		
		System.out.println("********** " + players[i].getName() + "'S TURN **********\n");
		if(players[i].getHP() < 1){
		    System.out.println(players[i].getName() + " is down! He cannot make a move!");
		}else{
		    if(n==1){
			System.out.println("Current stats:\n" + getAllStats() + "\n" + opponent.getStats() + "\n");
		    }
		    System.out.println("Choose an action:\nA : attack\nS : special attack\nG : give up");
		    act2 = s.nextLine();	    
		    
		    while(! act2.equals("A") && ! act2.equals("S") && ! act2.equals("G")){
			System.out.println("Invalid response received. Please try again.");
			act2 = s.nextLine();
		    }
		    if(act2.equals("A")){
			players[i].attack(opponent);
		    }else if(act2.equals("S")){
			players[i].specialAttack(opponent);
		    }else if(act2.equals("G")){
			endmatch = false;
			loser = "players";
		    }
		    if(opponent.getHP() < 1){
			endmatch = false;
			loser = "opponent";
			break;
		    }
		}
	    }
      	}
	if(loser.equals("players")){
	    System.out.println("The good side has lost!\n" + "Malevolence has won!!");
	}else if(loser.equals("opponent")){
	    System.out.println("Malevolence has lost!\nThe good side has won!!");
	}
    }

    public static void combat(Adventurer player, Adventurer opponent){
	Random r = new Random();
	Scanner s = new Scanner(System.in);
	boolean endmatch = true;
	String loser = "nobody";
        System.out.println("\n********** GAME BEGINS **********\n");
	

	if(n == 1){
	    System.out.println("********** YOUR TURN **********\n");
	    System.out.println("Current stats:\n" + player.getStats() + "\n" + opponent.getStats() + "\n");
	    System.out.println("Choose an action:\nA : attack\nS : special attack\nG : give up");
	    String act = s.nextLine();	    
	
	    while(! act.equals("A") && ! act.equals("S") && ! act.equals("G")){
		System.out.println("Invalid response received. Please try again.");
		act = s.nextLine();
	    }
	    if(act.equals("A")){
		player.attack(opponent);
	    }else if(act.equals("S")){
		player.specialAttack(opponent);
	    }else if(act.equals("G")){
		endmatch = false;
		loser = "player";
	    }
	    if(opponent.getHP() < 1){
		endmatch = false;
		loser = "opponent";
	    }
	}
	String act2 = "";
	while(endmatch){
	    System.out.println("********** OPPONENT'S TURN **********\n");
	    if(n == 2){
		System.out.println("Current stats:\n" + player.getStats() + "\n" + opponent.getStats() + "\n");
	    }
	    double chance = r.nextDouble();
	    if(chance < 0.6){
		opponent.attack(player);
	    }else{
		if(opponent.getSC() > 5){
		    opponent.specialAttack(player);
		}else{
		    opponent.attack(player);
		} 
	    }
	    if(player.getHP() < 1){
		endmatch = false;
		loser = "player";
		break;
	    }
	    System.out.println("********** YOUR TURN **********\n");
	    if(n==1){
		System.out.println("Current stats:\n" + player.getStats() + "\n" + opponent.getStats() + "\n");
	    }
	    System.out.println("Choose an action:\nA : attack\nS : special attack\nG : give up");
	    act2 = s.nextLine();	    
	    
	    while(! act2.equals("A") && ! act2.equals("S") && ! act2.equals("G")){
		System.out.println("Invalid response received. Please try again.");
		act2 = s.nextLine();
	    }
	    if(act2.equals("A")){
		player.attack(opponent);
	    }else if(act2.equals("S")){
		player.specialAttack(opponent);
	    }else if(act2.equals("G")){
		endmatch = false;
		loser = "player";
	    }
	    if(opponent.getHP() < 1){
		endmatch = false;
		loser = "opponent";
		break;
	    }
      	}
	if(loser.equals("player")){
	    System.out.println(player.getName() + " has lost!\n" + "Malevolence has won!!");
	}else if(loser.equals("opponent")){
	    System.out.println("Malevolence has lost!\n" + player.getName() + " has won!!");
	}
    }
    
    public static void heal(Adventurer[] players){
	int count = 0;
	for(int i = 0; i < 4; i++){
	    players[i].heal(50,stats[count],stats[count+1],stats[count+2]);
	    count += 3;
	}
    }
    public static void main(String[] args){
	
	// initialize the players
	//Adventurer player = new Warrior();// = new Adventurer();
	//Adventurer opponent = new Warrior();// = new Adventurer();
	
	Adventurer[] players = setupPlayers();
	Adventurer opponent = setupOpp();
	Random r = new Random();
	Scanner s = new Scanner(System.in);
	
	System.out.println("Now you shall face against the fierce opponent, " + opponent.getName() + ".");
	System.out.println("We need to decide who goes first. Allow me to randomly pick with a coin...\n.\n.\n.");
	
	n = r.nextInt(2) + 1;
	int decision = 0;
	if(n == 1){
	    System.out.println("You go first!");
	}else{
	    System.out.println("Opponent goes first!");
	}

	combat(players,opponent);
	while(true){
	    System.out.println("Hopefully you didn't break a sweat! Would you like to battle the opponent again? Your players and your opponent will be healed. Enter yes or no.");
	    String choice = s.nextLine();
	    if(choice.equalsIgnoreCase("yes")){
		heal(players);
		Adventurer newOpponent = setupOpp();
		combat(players,newOpponent);
	    }else if(choice.equalsIgnoreCase("no")){
		System.out.println("Thank you for playing! We hope to see you on another adventure!");
		break;
	    }else{
		System.out.println("Invalid response received. Please try again.");
	    }
	}
	    
	

    }
	
}
    

