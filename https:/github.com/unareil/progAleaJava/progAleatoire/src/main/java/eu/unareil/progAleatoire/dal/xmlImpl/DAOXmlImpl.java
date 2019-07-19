package eu.unareil.progAleatoire.dal.xmlImpl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eu.unareil.progAleatoire.bo.Stagiaire;
import eu.unareil.progAleatoire.dal.DALException;
import eu.unareil.progAleatoire.dal.DAO;

public class DAOXmlImpl implements DAO<Stagiaire> {


	@Override
	public Stagiaire selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		 Node noeudEnfant;
		 Stagiaire sta=null;
		 Document domXml;
		 int idT=0;
		try {
			domXml = XmlTools.getConnection();
		 Element root = domXml.getDocumentElement();
		 NodeList enfantsRoot = root.getChildNodes();
		 for(int i=0;i<=enfantsRoot.getLength();i++)
		 {
			 noeudEnfant=enfantsRoot.item(i);
			 if(noeudEnfant instanceof Element){
				 if (((Element)noeudEnfant).getTagName().toUpperCase().equalsIgnoreCase("stagiaire"))
				 {
					
					 Element elementIndividu = (Element)noeudEnfant;
			    	 NodeList enfantsIndividu = elementIndividu.getChildNodes();
			    	  for(int j=0;j<=enfantsIndividu.getLength();j++)
				         {
			    		  Node noeudEnfantIndiv = enfantsIndividu.item(j);
				        	 if(noeudEnfantIndiv instanceof Element){
					        	 Element element = (Element)noeudEnfantIndiv;
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("id"))
					        	 {
					        		 idT= Integer.valueOf(element.getTextContent());
					        		 if (idT==id)
					        		 {
					        		 sta = new Stagiaire();
					        		 sta.setId(id);
					        		 }
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("prenom"))
					        	 {
					        		 if (idT==id)
					        		 {
					        		 sta.setPrenom(element.getTextContent());
					        		 }
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("nom"))
					        	 {
					        		 if (idT==id)
					        		 {
					        		 sta.setNom(element.getTextContent());
					        		 }
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("joue"))
					        	 {
					        		 if (idT==id)
					        		 {
					        		 boolean joue= Boolean.valueOf(element.getTextContent());
					        		 sta.setJoue(joue);
					        		 }
					        	 }
					        	
				        	 }
				         }
				 }
			 }
		 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DALException("Erreur SELECT_ALL",e);
		}
		return sta;
	}

	@Override
	public List<Stagiaire> selectAll() throws DALException {
		// TODO Auto-generated method stub
		 Node noeudEnfant;
		List<Stagiaire> stagiaires = new ArrayList<>();
		 Document domXml;
		 System.out.println("ici");
		try {
			domXml = XmlTools.getConnection();
		 Element root = domXml.getDocumentElement();
		 NodeList enfantsRoot = root.getChildNodes();
		 for(int i=0;i<=enfantsRoot.getLength();i++)
		 {
			 noeudEnfant=enfantsRoot.item(i);
			 if(noeudEnfant instanceof Element){
				 if (((Element)noeudEnfant).getTagName().toUpperCase().equalsIgnoreCase("stagiaire"))
				 {
					 Stagiaire sta = new Stagiaire();
					 Element elementIndividu = (Element)noeudEnfant;
			    	 NodeList enfantsIndividu = elementIndividu.getChildNodes();
			    	  for(int j=0;j<=enfantsIndividu.getLength();j++)
				         {
			    		  Node noeudEnfantIndiv = enfantsIndividu.item(j);
				        	 if(noeudEnfantIndiv instanceof Element){
					        	 Element element = (Element)noeudEnfantIndiv;
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("id"))
					        	 {
					        		 int id= Integer.valueOf(element.getTextContent());
					        		 sta.setId(id);
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("prenom"))
					        	 {
					        		 sta.setPrenom(element.getTextContent());
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("nom"))
					        	 {
					        		 sta.setNom(element.getTextContent());
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("joue"))
					        	 {
					        		 boolean joue= Boolean.valueOf(element.getTextContent());
					        		 sta.setJoue(joue);
					        	 }
					        	
				        	 }
				         }
			    	  stagiaires.add(sta); 
				 }
			 }
		 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DALException("Erreur SELECT_ALL",e);
		}
		return stagiaires;
	}

	@Override
	public void update(Stagiaire data) throws DALException {
		
		
		// TODO Auto-generated method stub
		 Node noeudEnfant;
		 @SuppressWarnings("unused")
		Stagiaire sta=null;
		 Document domXml;
		 int idT=0;
		 int id=data.getId();
		try {
			domXml = XmlTools.getConnection();
		 Element root = domXml.getDocumentElement();
		 NodeList enfantsRoot = root.getChildNodes();
		 for(int i=0;i<=enfantsRoot.getLength();i++)
		 {
			 noeudEnfant=enfantsRoot.item(i);
			 if(noeudEnfant instanceof Element){
				 if (((Element)noeudEnfant).getTagName().toUpperCase().equalsIgnoreCase("stagiaire"))
				 {
					
					 Element elementIndividu = (Element)noeudEnfant;
			    	 NodeList enfantsIndividu = elementIndividu.getChildNodes();
			    	  for(int j=0;j<=enfantsIndividu.getLength();j++)
				         {
			    		  Node noeudEnfantIndiv = enfantsIndividu.item(j);
				        	 if(noeudEnfantIndiv instanceof Element){
					        	 Element element = (Element)noeudEnfantIndiv;
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("id"))
					        	 {
					        		 idT= Integer.valueOf(element.getTextContent());
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("prenom"))
					        	 {
					        		 if (idT==id)
					        		 {
					        		 element.setTextContent(data.getPrenom());
					        		 }
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("nom"))
					        	 {
					        		 if (idT==id)
					        		 {
					        			 element.setTextContent(data.getNom());
					        		 }
					        	 }
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("joue"))
					        	 {
					        		 if (idT==id)
					        		 {
					        		 @SuppressWarnings("unused")
									boolean joue= Boolean.valueOf(element.getTextContent());
					        		 element.setTextContent(String.valueOf(data.isJoue()));
					        		 }
					        	 }
					        	
				        	 }
				         }
				 }
			 }
		 }
		 //construire la transformation inactive
         Transformer t = TransformerFactory.newInstance().newTransformer();
         //définir les propriétés de sortie pour obtenir un nœud XSD
              //permet d'éviter d'afficher les balises sur la même ligne
         t.setOutputProperty(OutputKeys.INDENT, "yes");
         // appliquer la transformation 
		 // chemin StreamResult sr = new StreamResult(new File("/my/file.xml"));
         StreamResult xmlStream = new StreamResult(Settings.getProperty("urlxml"));    
         t.transform(new DOMSource(root), xmlStream);    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DALException("Erreur Update",e);
		}


		// TODO Auto-generated method stub

	}

	@Override
	public int insert(Stagiaire data) throws DALException {
		// TODO Auto-generated method stub
		 Node noeudEnfant;
		 int nb=0;
			@SuppressWarnings("unused")
			List<Stagiaire> stagiaires = new ArrayList<>();
			 Document domXml;
			try {
				domXml = XmlTools.getConnection();
			 Element root = domXml.getDocumentElement();
			 NodeList enfantsRoot = root.getChildNodes();
			 for(int i=0;i<=enfantsRoot.getLength();i++)
			 {
				 noeudEnfant=enfantsRoot.item(i);
				 if(noeudEnfant instanceof Element){
					 if (((Element)noeudEnfant).getTagName().toUpperCase().equalsIgnoreCase("stagiaire"))
					 {
						 nb++;
					 }
				 }
			 }
			 nb++;
			 //On crée un fichier xml correspondant au résultat
			 Element stagiaire = domXml.createElement("stagiaire");
			 Element id = domXml.createElement("id");
		     id.setTextContent(String.valueOf(nb));
		     stagiaire.appendChild(id);
	         Element prenom = domXml.createElement("prenom");
	         prenom.setTextContent(data.getPrenom());
	         stagiaire.appendChild(prenom);
	         Element nom = domXml.createElement("nom");
	         nom.setTextContent(data.getNom());
	         stagiaire.appendChild(nom);
	         Element joue = domXml.createElement("joue");
	         joue.setTextContent(String.valueOf(data.isJoue()));
	         stagiaire.appendChild(joue);
	         root.appendChild(stagiaire);
	         //construire la transformation inactive
	         Transformer t = TransformerFactory.newInstance().newTransformer();
	         //définir les propriétés de sortie pour obtenir un nœud XSD
	              //permet d'éviter d'afficher les balises sur la même ligne
	         t.setOutputProperty(OutputKeys.INDENT, "yes");
	         // appliquer la transformation 
			 // chemin StreamResult sr = new StreamResult(new File("/my/file.xml"));
	         StreamResult xmlStream = new StreamResult(Settings.getProperty("urlxml"));    
	         t.transform(new DOMSource(root), xmlStream);    
			}catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DALException("Erreur UPDATE",e);
		}
			return nb;
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		 Node noeudEnfant;
		 @SuppressWarnings("unused")
		Stagiaire sta=null;
		 Document domXml;
		 int idT=0;
		try {
			domXml = XmlTools.getConnection();
		 Element root = domXml.getDocumentElement();
		 NodeList enfantsRoot = root.getChildNodes();
		 for(int i=0;i<=enfantsRoot.getLength();i++)
		 {
			 noeudEnfant=enfantsRoot.item(i);
			 if(noeudEnfant instanceof Element){
				 if (((Element)noeudEnfant).getTagName().toUpperCase().equalsIgnoreCase("stagiaire"))
				 {
					
					 Element elementIndividu = (Element)noeudEnfant;
			    	 NodeList enfantsIndividu = elementIndividu.getChildNodes();
			    	  for(int j=0;j<=enfantsIndividu.getLength();j++)
				         {
			    		  Node noeudEnfantIndiv = enfantsIndividu.item(j);
				        	 if(noeudEnfantIndiv instanceof Element){
					        	 Element element = (Element)noeudEnfantIndiv;
					        	 if (element.getTagName().toUpperCase().equalsIgnoreCase("id"))
					        	 {
					        		 idT= Integer.valueOf(element.getTextContent());
					        		 
					        	 }		        	
				        	 }
				         }
			    	  if (id==idT)
			    	  {
			    		  root.removeChild(noeudEnfant);
			    	  }
				 }
			 }
		 }
		 //construire la transformation inactive
         Transformer t = TransformerFactory.newInstance().newTransformer();
         //définir les propriétés de sortie pour obtenir un nœud XSD
              //permet d'éviter d'afficher les balises sur la même ligne
         t.setOutputProperty(OutputKeys.INDENT, "yes");
         // appliquer la transformation 
		 // chemin StreamResult sr = new StreamResult(new File("/my/file.xml"));
         StreamResult xmlStream = new StreamResult(Settings.getProperty("urlxml"));    
         t.transform(new DOMSource(root), xmlStream);    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DALException("Erreur Update",e);
		}



	}

}
