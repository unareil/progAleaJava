package eu.unareil.progAleatoire.bo;
public class Individu
{
  protected String nom;
  protected String prenom;
public Individu(String prenom,String nom)
{
this.setNom(nom);
this.setPrenom(prenom);
}
public Individu(String prenom)
{
this.setNom("");
this.setPrenom(prenom);
}
public Individu()
{
this.setNom("");
this.setPrenom("");
}
public void setNom(String nom)
{
this.nom=nom;
}
public void setPrenom(String prenom)
{
this.prenom=prenom;
}
public String getNom()
{
return this.nom;
}
public String getPrenom()
{
return this.prenom;
}
public String toString()
{
return this.prenom+" "+this.nom;
}
}
