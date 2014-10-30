import java.util.Random;
public class MartialArtist extends Adventurer{
    private int qi;

    public MartialArtist(){
	super("Young Sensei");
	setQi(50);
    }

    public MartialArtist(String n){
	super(n);
	setQi(50);
    }
	

    public void setQi(int n){
	qi = n;
    }

    public int getQi(){
	return qi;
    }
    
    public String getStats(){
	return super.getStats() + getQi() + "QI";
    }
   
    public void heal(int HP, int STR, int DEX, int INT){
	setStats(HP,STR,DEX,INT);
	setQi(50);
    }

    public void attack(Adventurer other){
	Random r = new Random();
	int n = r.nextInt(getSTR()+3);
	if(hit(other) || hit(other)){
	    System.out.println(this + " attacks " + other +  " with his katanas for " + n + " points of damage!!");
	    other.setHP(other.getHP() - n);
	}else{
	    System.out.println(this + " misses " + other + "!");
	}
    }
    
    public void specialAttack(Adventurer other){
	Random r = new Random();
	int n = r.nextInt(getSTR() * 2);
	if(hitMagic(other)){
	    System.out.println(this + " attacks " + other  + " with a fierce uppercut for " + n + " points of damage!!");
	    other.setHP(other.getHP() - n);
	    setQi(getQi() - 5);	    
	}else{
	    System.out.println(this + " misses " + other + "!");
	    setQi(getQi() - 5);
	}
	}

}
