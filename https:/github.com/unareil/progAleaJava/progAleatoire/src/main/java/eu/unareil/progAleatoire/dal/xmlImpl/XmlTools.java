package eu.unareil.progAleatoire.dal.xmlImpl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlTools {
private static String urlxml;
static {
	urlxml=Settings.getProperty("urlxml");
}
public static Document getConnection() throws Exception {
	Document domXml=null;
	DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
    // Ce dernier représente la hiérarchie d'objet créée pendant le parsing
	DocumentBuilder parseur;
	try {
		File fileXML = new File(urlxml);
		parseur = fabrique.newDocumentBuilder();
		 domXml = parseur.parse(fileXML);
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		throw e;
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		throw e;
	} catch (IOException e) {
		// TODO Auto-generated catch block	
		try {
			parseur = fabrique.newDocumentBuilder();
			domXml = parseur.newDocument();
			//Création de notre élément racine
			Element root =domXml.createElement("stagiaires");
			root.setAttribute("xmlns","http://www.example.org/stagiaires");
			root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", "http://www.example.org/individus individus.xsd");	 
//			//On crée un fichier xml correspondant au résultat
//			 Element stagiaire = domXml.createElement("stagiaire");
//			 Element id = domXml.createElement("id");
//		     id.setTextContent("1");
//		     stagiaire.appendChild(id);
//	         Element prenom = domXml.createElement("prenom");
//	         prenom.setTextContent(" ");
//	         stagiaire.appendChild(prenom);
//	         Element nom = domXml.createElement("nom");
//	         nom.setTextContent(" ");
//	         stagiaire.appendChild(nom);
//	         Element joue = domXml.createElement("joue");
//	         joue.setTextContent("true");
//	         stagiaire.appendChild(joue);
//	         root.appendChild(stagiaire);
	         //construire la transformation inactive
	         Transformer t = TransformerFactory.newInstance().newTransformer();
	         //définir les propriétés de sortie pour obtenir un nœud XSD
	              //permet d'éviter d'afficher les balises sur la même ligne
	         t.setOutputProperty(OutputKeys.INDENT, "yes");
	         // appliquer la transformation 
			 // chemin StreamResult sr = new StreamResult(new File("/my/file.xml"));
	         StreamResult xmlStream = new StreamResult(urlxml);    
	         t.transform(new DOMSource(root), xmlStream);    
	         System.out.println("Création du fichier xml faite");
		} catch (ParserConfigurationException | TransformerException e1) {
			// TODO Auto-generated catch block
			throw e1;
			}
		}
   
	return domXml;
}
}
