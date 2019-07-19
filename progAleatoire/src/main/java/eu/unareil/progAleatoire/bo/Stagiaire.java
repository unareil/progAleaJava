package eu.unareil.progAleatoire.bo;
public class Stagiaire extends Individu
{
  private int nbreFois;
  private int id;
  public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
private int dernierTirage;
  private boolean joue=true;
public boolean isJoue() {
	return joue;
}
public Stagiaire() {
	super("", "");
}
public void setJoue(boolean joue) {
	this.joue = joue;
}
public Stagiaire(String prenom,String nom)
{
  super(prenom,nom);
  nbreFois=0;
}
private void incremente()
{
    nbreFois++;
}
public int getScore()
{
    return this.nbreFois;
}
public void reset()
{
    this.nbreFois=0;
}
public void setDernierTirage(int tour)
{
  this.dernierTirage=tour;
  this.incremente();
}
public int getDernierTirage()
{
  return this.dernierTirage;
}
public String toString()
{
  @SuppressWarnings("unused")
String selection="sélection";
  if (this.getScore()>1)
  {
    selection+="s";
  }
  return this.getPrenom()+" "+this.getNom()+"\navec "+this.getScore()+" selection(s) \net une dernière selection au tour n°"+this.getDernierTirage();
}
public String getMini()
{
  return (this.getPrenom()).substring(0,1)+""+(this.getNom()).substring(0,1)+"=>"+this.getScore()+"  ";
}
}
