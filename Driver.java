public class Driver{
    public static void main(String[]args){
	MartialArtist p1 = new MartialArtist("Young Sensei");
	Warrior p2 = new Warrior("Grobnar");
	p2.setRage(20);
	Wizard p3 = new Wizard("Merlin");
	Rogue p4 = new Rogue("Inigo Montoya");
	
	System.out.println(p1.getStats());
	System.out.println(p2.getStats());
	System.out.println(p3.getStats());
	System.out.println(p4.getStats());
	System.out.println();
	
	p1.attack(p2);
	p1.specialAttack(p2);
	System.out.println();
	
	p2.attack(p3);
	p2.specialAttack(p3);
	System.out.println();
	
	p3.attack(p4);
	p3.specialAttack(p4);
	System.out.println();
	
	p4.attack(p1);
	p4.specialAttack(p1);
	System.out.println();
	
	System.out.println(p1.getStats());
	System.out.println(p2.getStats());
	System.out.println(p3.getStats());
	System.out.println(p4.getStats());
	System.out.println();
    }
}
