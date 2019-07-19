package eu.unareil.progAleatoire.dal;

import java.util.ArrayList;
import java.util.List;

import eu.unareil.progAleatoire.bo.Stagiaire;

public class TestDal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO<Stagiaire> maDAO = DAOFactory.getStagairesDAO();
		List<Stagiaire> stagiaires = new ArrayList<>();

	
		try {
//			Stagiaire unSta= new Stagiaire("Aurélien","Martineau");
//			unSta.setJoue(true);
//			maDAO.insert(unSta);
//			unSta= new Stagiaire("Noémie","Martineau");
//			unSta.setJoue(true);
//			maDAO.insert(unSta);
//			unSta= new Stagiaire("Noéline","Martineau");
//			unSta.setJoue(true);
//			maDAO.insert(unSta);
			stagiaires=maDAO.selectAll();
			for(Stagiaire st : stagiaires)
			{
				System.out.println(st.getId()+"-"+st.getPrenom()+"-"+st.getNom());
			}
		//	maDAO.delete(5);
//			System.out.println("-----------------------------------");
//			Stagiaire sta = maDAO.selectById(3);
//			System.out.println(sta.getId()+"-"+sta.getPrenom()+"-"+sta.getNom());
//			sta.setPrenom("Noéline");
//			maDAO.update(sta);
//			stagiaires=maDAO.selectAll();
//			for(Stagiaire st : stagiaires)
//			{
//				System.out.println(st.getId()+"-"+st.getPrenom()+"-"+st.getNom());
//			}
//			maDAO.update(sta);
//			System.out.println("-----------------------------------");
//			sta = maDAO.selectById(3);
//			System.out.println(sta.getId()+"-"+sta.getPrenom()+"-"+sta.getNom());
//			System.out.println("-----------------------------------");
//			sta = new Stagiaire("Toto","Titi");
//			sta.setJoue(true);
//			maDAO.insert(sta);
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
