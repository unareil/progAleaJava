package eu.unareil.progAleatoire.IHM;
import java.util.Scanner;

import eu.unareil.progAleatoire.bll.ProgAleatoire;
import eu.unareil.progAleatoire.bo.Stagiaire;
public class IHMConsoleProgAleatoire
{
public static void main(String args[])
{
  Stagiaire stagiaires[]= new Stagiaire[10];
  stagiaires[0]=new Stagiaire("Angélique","GEMARIN");
  stagiaires[1]=new Stagiaire("Mickaël","LECLERC");
  stagiaires[2]=new Stagiaire("Noémie","LECLEVE");
  stagiaires[3]=new Stagiaire("Andréa","LUTTON");
  stagiaires[4]=new Stagiaire("Yann","MAILLOT");
  stagiaires[5]=new Stagiaire("Ronan","PENDU");
  stagiaires[6]=new Stagiaire("Islem","SLAMA");
  stagiaires[7]=new Stagiaire("Loubna","TANI");
  stagiaires[8]=new Stagiaire("Florent","VESLIN");
  stagiaires[9]=new Stagiaire("Alexandre","VIGNERON");
ProgAleatoire pr = ProgAleatoire.getInstance();
pr.ajouteTous(stagiaires);
  int nbreTour;
  Scanner s=new Scanner(System.in);
  System.out.println("Combien de tours ?");
  nbreTour= s.nextInt();
  s.close();
//  String maChaine;
  Stagiaire st;
  for (int i=1;i<=nbreTour;i++)
  {
	  st=null;
	  st=pr.selectionHasard(i);
	  
	  System.out.println("Tour n°"+i+" : "+st.getMini());
	  for (Stagiaire sta : pr.afficheTous())
	  {
		  System.out.print(sta.getMini());
	  }
	  System.out.println("");
	  try
    {
      Thread.sleep(500);
    }
    catch(Exception e)
    {
      System.out.println("Pb :"+e.getMessage());
    }
  }
  st =pr.gagnant();
  try
  {
    Thread.sleep(800);
  }
  catch(Exception e)
  {
    System.out.println("Pb :"+e.getMessage());
  }
  System.out.println("****************************************************");
  System.out.println("Le stagaire sélectionné est :");
  System.out.println(st);
  System.out.println("****************************************************");
  
//    Random rnd = new Random();
//    nbreAlea= rnd.nextInt(10);
//    stagiaires[nbreAlea].setDernierTirage(nbreTour-i);
//    maChaine= i+" :";
//    for(int j=1;j<10;j++)
//    {
//      maChaine=maChaine+" "+stagiaires[j].getMini();
//    }
//    System.out.print("\r"+maChaine);
//    try
//    {
//      Thread.sleep(500);
//    }
//    catch(Exception e)
//    {
//      System.out.println("Pb :"+e.getMessage());
//    }
//  }
//      // Nous recherchons le stagiaire qui a été le plus sélectionné
//      // et en cas d'égalité le dernier selectionné
//  int nbreGrand=0;
//  int tourSortie=0;
//  for(int i=1;i<10;i++)
//  {
//    if (stagiaires[i].getScore()>=nbreGrand)
//    {
//      nbreGrand=stagiaires[i].getScore();
//    }
//  }
//  for(int i=1;i<10;i++)
//  {  
//  if ((stagiaires[i].getScore()==nbreGrand) && (stagiaires[i].getDernierTirage()>=tourSortie))
//  {
//      tourSortie=stagiaires[i].getDernierTirage();
//  }
//  }
//  int j=-1;
//  do
//  {
//    j++;
//  }while(tourSortie!=stagiaires[j].getDernierTirage());
//  System.out.println("\nLe stagiaire sélectionné est :"+stagiaires[j].toString());
}
}
