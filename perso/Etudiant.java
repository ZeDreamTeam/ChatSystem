package perso;

/**
* Class Etudiant
**/

public class Etudiant extends Personne
{
	private float note;

	public Etudiant(String nom, int age, float note)
	{
		super(nom, age);
		this.note = note;
	}
	public float getNote()
	{
		return this.note;
	}
	public void setNote(float val)
	{
		this.note = val;
	}
	public String toString()
	{
		return super.toString()+"\nNote : " +this.getNote();
	}
}