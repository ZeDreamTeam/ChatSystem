package perso;

/**
* Class Enseignant
**/
public class Enseignant extends Personne
{
	private int heures;
	public Enseignant(String nom, int age, int heures)
	{
		super(nom,age);
		this.heures = heures;
	}
	public int getHeures()
	{
		return this.heures;
	}
	public void setHeures(int val)
	{
		this.heures = val;
	}
	public String toString()
	{
		return "\nAge : " + super.getAge() + "\nNom : "+ super.getNom() + "\nHeures : "+this.getHeures();
	}
}