package eu.unareil.progAleatoire.dal;

import java.io.IOException;

import eu.unareil.progAleatoire.bo.Stagiaire;
import eu.unareil.progAleatoire.dal.xmlImpl.DAOXmlImpl;
import eu.unareil.progAleatoire.dal.xmlImpl.Settings;

public class DAOFactory {
public static DAO<Stagiaire> getStagairesDAO(){
	DAO<Stagiaire> maDAO = new DAOXmlImpl();
	return maDAO;
	
}
public static String getURL(){
	
	return Settings.getProperty("urlxml");
	
}
public static void setURL(String value) throws DALException{
	
	try {
		Settings.setProperty(value);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		throw new DALException("Erreur d'écriture du xml",e);
	}
	
}
}
