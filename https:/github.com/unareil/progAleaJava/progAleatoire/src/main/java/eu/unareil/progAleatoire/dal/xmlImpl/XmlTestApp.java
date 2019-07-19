package eu.unareil.progAleatoire.dal.xmlImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlTestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 Document domXml;
try {
	domXml = XmlTools.getConnection();


 Element root = domXml.getDocumentElement();
 NodeList enfantsRoot = root.getChildNodes();
 NodeList enfantsIndividu;
 Node noeudEnfant;
 Node noeudEnfantIndiv;
 @SuppressWarnings("unused")
Element elementEnfant;
 Element elementIndividu;
 Element element;
 int nbIndiv=1;
 System.out.println("INDIVIDUS :\n");
 for(int i=0;i<=enfantsRoot.getLength();i++)
 {
	 noeudEnfant=enfantsRoot.item(i);
	 if(noeudEnfant instanceof Element){
    	 System.out.println("Individu "+nbIndiv+" :");
    	 nbIndiv++;
    	 elementIndividu=(Element)noeudEnfant;
    	 enfantsIndividu=elementIndividu.getChildNodes();
    	  for(int j=0;j<=enfantsIndividu.getLength();j++)
	         {
    		  noeudEnfantIndiv=enfantsIndividu.item(j);
	        	 if(noeudEnfantIndiv instanceof Element){
		        	 element=(Element)noeudEnfantIndiv;
		        	 StringBuilder sb=new StringBuilder();
		        	 sb.append(element.getTagName().toUpperCase());
		        	 sb.append(" : ");
		        	 sb.append(element.getTextContent());
		        	 System.out.println(sb.toString());
	         }
	         }
    	  System.out.println("");
        	 }
 }
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}


}
