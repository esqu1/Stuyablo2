import java.util.Random;
public class Wizard extends Adventurer{
    private int mana;

    public Wizard(){
	super("Matthew");
	setHP(50);
	setMana(50);
	setSTR(6);
	setDEX(8);
	setINT(18);
    }
    
    public Wizard(String n){
	super(n);
	setHP(50);
	setMana(50);
	setSTR(6);
	setDEX(8);
	setINT(18);
    }

    public void setMana(int man){
	mana = man;
    }

    public int getMana(){
	return mana;
    }

    public String getStats(){
	return super.getStats() + getMana() + "MANA ";
    }

    public int getSC(){
	return getMana();
    }
    
    public void attack(Adventurer other){
	Random r = new Random();
	int n = r.nextInt(getSTR() / 2);
	if(hit(other) || hit(other)){
	    System.out.println(this + " pokes " + other + " with a wooden stick for " + n + " points of damage!!");
	    other.setHP(other.getHP() - n);
	}else{
	    System.out.println(this + " misses " + other + "!");
	}
    }

    public void specialAttack(Adventurer other){
	Random r = new Random();
	int n = r.nextInt(getSTR() * 2);
	if(mana < 5){
	    System.out.println("Not enough mana. Using regular attack.");
	    attack(other);
	}else{
	    if(hitMagic(other)){
		System.out.println(this + " shoots fireballs at " + other + " for " + n + " points of damage!!");
		other.setHP(other.getHP() - n);
		this.setMana(getMana() - 5);
	    }else{
		System.out.println(this + " misses " + other + "!");
		this.setMana(getMana() - 5);
	    }
	}
    }
    
	    
}
