package eu.unareil.progAleatoire.bll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import eu.unareil.progAleatoire.bo.Stagiaire;
import eu.unareil.progAleatoire.dal.DALException;
import eu.unareil.progAleatoire.dal.DAO;
import eu.unareil.progAleatoire.dal.DAOFactory;
public class ProgAleatoire
{
	
	private List<Stagiaire> lesStagiaires = new ArrayList<>();
    private static ProgAleatoire instance=null;
    
    private ProgAleatoire()
    {
    	super();
    }
    public static synchronized ProgAleatoire getInstance()
    {
    	
    	if (instance==null)
    	{
    		instance = new ProgAleatoire();
    	}
    	return instance;
    }
	public int ajoute(Stagiaire st) throws BLLException
	{
		int id=0;
		DAO<Stagiaire> maDAO = DAOFactory.getStagairesDAO();
		try {
			id=maDAO.insert(st);
			lesStagiaires=maDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException("Erreur insertion "+st.getPrenom()+" "+st.getNom(),e);
		}
		return id;
		
	}
	public void suppr(Stagiaire candidat) throws BLLException
	{
		DAO<Stagiaire> maDAO = DAOFactory.getStagairesDAO();
		try {
			maDAO.delete(candidat.getId());
			lesStagiaires=maDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException("Erreur suppression "+candidat.getPrenom()+" "+candidat.getNom(),e);
		}
	}
	public void ajouteTous(List <Stagiaire>lesSta)
	{
		lesStagiaires=lesSta;
	}
	public void chargeXML() throws BLLException
	{
		DAO<Stagiaire> maDAO = DAOFactory.getStagairesDAO();
		try {
			lesStagiaires=maDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException("Erreur chargement fichier xml",e);
		}
		
	}
	public void modifieStagiaire(Stagiaire leSta) throws BLLException
	{
		DAO<Stagiaire> maDAO = DAOFactory.getStagairesDAO();
		try {
			maDAO.update(leSta);
			lesStagiaires=maDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException("Erreur modification "+leSta.getPrenom()+" "+leSta.getNom(),e);
		}
	}
	public void ajouteTous(Stagiaire[] lesSta)
	{
		lesStagiaires=Arrays.asList(lesSta);
	}
	public List<Stagiaire> afficheTous(){
		return lesStagiaires;
	}
	public Stagiaire affiche(int index){
		return lesStagiaires.get(index);
	}
	public Stagiaire selectionHasard(int tour)
	{
		int nbreAlea=0;
		do {
		Random rnd = new Random();
		nbreAlea= rnd.nextInt(lesStagiaires.size());
		selectionne(nbreAlea, tour);	
		}while(!(lesStagiaires.get(nbreAlea)).isJoue());
		
		return lesStagiaires.get(nbreAlea);
	}
	private void selectionne(int index, int tour){
		if (lesStagiaires.get(index).isJoue())
		{
		lesStagiaires.get(index).setDernierTirage(tour);
		}
	}
	public void reset()
	{
		for (Stagiaire st : lesStagiaires)
		{
			st.setDernierTirage(0);
			st.reset();
		}
		
	}
	public Stagiaire gagnant()
	{
		int nbreGrand=0;
		  int tourSortie=0;
		  for(Stagiaire st : lesStagiaires)
		  {
		    if (st.getScore()>=nbreGrand)
		    {
		      nbreGrand=st.getScore();
		    }
		  }
		  for(Stagiaire st : lesStagiaires)
		  {  
		  if ((st.getScore()==nbreGrand) && (st.getDernierTirage()>=tourSortie))
		  {
		      tourSortie=st.getDernierTirage();
		  }
		  }
		  for(Stagiaire st : lesStagiaires)
		  { 
			  if (tourSortie==st.getDernierTirage())
			  {
				  return st;
			  }
		  }
		return null;
	}
	
public static void main(String args[])
{
//  Stagiaire stagiaires[]= new Stagiaire[10];
//  stagiaires[0]=new Stagiaire("Angélique","GEMARIN");
//  stagiaires[1]=new Stagiaire("Mickaël","LECLERC");
//  stagiaires[2]=new Stagiaire("Noémie","LECLEVE");
//  stagiaires[3]=new Stagiaire("Andréa","LUTTON");
//  stagiaires[4]=new Stagiaire("Yann","MAILLOT");
//  stagiaires[5]=new Stagiaire("Ronan","PENDU");
//  stagiaires[6]=new Stagiaire("Islem","SLAMA");
//  stagiaires[7]=new Stagiaire("Loubna","TANI");
//  stagiaires[8]=new Stagiaire("Florent","VESLIN");
//  stagiaires[9]=new Stagiaire("Alexandre","VIGNERON");
  
//  int nbreAlea;
//  int nbreTour;
//  Scanner s=new Scanner(System.in);
//  System.out.println("Combien de tours ?");
//  nbreTour= s.nextInt();
//  s.close();
//  String maChaine;
//  for (int i=nbreTour;i>=0;i--)
//  {
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
