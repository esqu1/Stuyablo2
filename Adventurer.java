import java.util.Random;
public abstract class Adventurer{ 
    private String name;
    private int HP, STR, DEX, INT;

    public Adventurer(){
	setName("Bob");
	setHP(50);
	setSTR(10);
	setDEX(10);
	setINT(10);
    }
    
    public Adventurer(String n){
	setName(n);
	setHP(50);
	setSTR(10);
	setDEX(10);
	setINT(10);
    }
    
    public void setName(String n){
	name = n;
    }

    public void setHP(int health){
	HP = health;
    }

    public void setSTR(int strength){
	STR = strength;
    }

    public void setDEX(int dexterity){
	DEX = dexterity;
    }

    public void setINT(int integrity){
	INT = integrity;
    }

    public String getStats(){
	return name + "\n" + getHP() + "HP " + getSTR() + "STR " + getDEX() + "DEX " + getINT() + "INT ";
    }
    
    
    public String getName(){
	return name;
    }

    public int getSC(){
	return 1234567890;
    }
    
    public int getHP(){
	return HP;
    }

    public int getSTR(){
	return STR;
    }

    public int getDEX(){
	return DEX;
    }
    
    public int getINT(){
	return INT;
    }
    
    public String toString(){
	return getName();
    }

    public void setStats(int HP, int STR, int DEX, int INT){
	setHP(HP);
	setSTR(STR);
	setDEX(DEX);
	setINT(INT);
    }
    
    public int protect(int n, Adventurer other){
	return n / ((other.getINT() / 8) + 1);
    }
    
    public boolean hit(Adventurer other){
	int m = (int)((this.getDEX() - other.getDEX()) * 1.25) + 50;
	Random rand = new Random();
	int n = rand.nextInt(100);
	return n < m;
    }

    public boolean hitMagic(Adventurer other){
	int m = (int)((this.getINT() - other.getINT()) * 1.25) + 50;
	Random rand = new Random();
	int n = rand.nextInt(100);
	return n < m;
    }

    public abstract void heal(int HP, int STR, int DEX, int INT);
	
    public abstract void attack(Adventurer other);
	
    public abstract void specialAttack(Adventurer other);

    

    
}
