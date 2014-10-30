import java.util.Random;
public class Rogue extends Adventurer{
    private int stamina;

    public Rogue(){
	super("Max");
	setHP(50);
	setStamina(50);
	setSTR(8);
	setDEX(14);
	setINT(10);
    }
    
    public Rogue(String n){
	super(n);
	setHP(50);
	setStamina(50);
	setSTR(8);
	setDEX(14);
	setINT(10);
    }

    public void setStamina(int stam){
    	stamina = stam;
    }

    public int getStamina(){
    	return stamina;
    }

    public String getStats(){
    	return super.getStats() + getStamina() + "STAMINA ";
    }

    public int getSC(){
	return getStamina();
    }

    public void heal(int HP, int STR, int DEX, int INT){
	setStats(HP,STR,DEX,INT);
	setStamina(50);
    }
    
    public void attack(Adventurer other){
	Random r = new Random();
	int n = r.nextInt(getSTR() + 2);
	if(hit(other) || hit(other)){
	    System.out.println(this + " attacks " + other + " sneakily for " + n + " points of damage!!");
	    other.setHP(other.getHP() - n);
	}else{
	    System.out.println(this + " misses " + other + "!");
	}
    }

    public void specialAttack(Adventurer other){
    	Random r = new Random();
    	int n = r.nextInt(getSTR() + 8);
    	if(stamina < 5){
	    System.out.println("Not enough mana, using regular attack.");
	    attack(other);
    	}else{
	    if(hitMagic(other)){
		System.out.println(this + " swindles " + other + " for " + n + " points of damage!!");
		other.setHP(other.getHP() - n);
		this.setStamina(getStamina() - 5);
	    }else{
		System.out.println(this + " misses " + other + "!");
		this.setStamina(getStamina() - 5);
	    }
    	}
    }
    
	    
}
