import perso.*;

/**
* Class HelloPersonnes
**/
public class HelloPersonnes
{
	/**
	* Method main, entry point tp the class
	* @param args input parameters for command line
	**/
	public static void main(String[] args)
	{
		Personne etudiant1 = new Etudiant("toto", 12, 2);
		Personne prof1 = new Enseignant("Mr. Toto", 77, 192);
		System.out.println(etudiant1);
		System.out.println(prof1);
	}
}