/**
 * 
 */
package ressourceAPIXml;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;


/**
 * @author Live Soccer
 *
 */
public class XMLParser {
		
	

	/**
	 * Méthode qui établi la connexion HTTP et revoi la String xml de la page 
	 * @param url
	 * @return xmlString <code>String</code> Object
	 */
	public String getXmlFromUrl(String url) {
		String xmlString = "" ;
			try {
				 HttpEntity page = getHttp(url);
				 xmlString = EntityUtils.toString(page,HTTP.UTF_8);
				 
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}
       return xmlString;
    }
	 

	
	/**
	 * Cette méthode parcse la String xml et revoi un Objet <code>Document</code> pour exploitable par la DOM
	 * @param xml
	 * @return doc <code>Document</code> Object
	 */
	public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder dbuider = factory.newDocumentBuilder();
 
            InputSource input = new InputSource();
                input.setCharacterStream(new StringReader(xml));
                doc = dbuider.parse(input); 
 
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
               
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                
            }
                // return DOM
            return doc;
    }
	
	/**
	 * @param item
	 * @param str
	 * @return la valeur de l'élément ayant un seul noeud 
	 */
	public String getValue(Element item, String str) {
		
	    NodeList n = item.getElementsByTagName(str);        
	    return this.getElementValue(n.item(0));
	}
	 
	/**
	 * Cette méthode retourne la valeur de l'élément 
	 * @param elem
	 * @return la valeur de l'élément 
	 */
	public final String getElementValue( Node elem ) {
	         Node child;
	         if( elem != null && elem.hasChildNodes()){
	             
	             for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                 if( child.getNodeType() == Node.TEXT_NODE  ){
	                     return child.getNodeValue();
	                 }
	             }
	           } 
	         return "";
	  } 
	/**
	 * Méthode qui établie la connexion HTTP
	 * @param url
	 * @return <code>HttpEntity</code> Objet
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpEntity getHttp(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet http = new HttpGet(url);
		HttpResponse response = httpClient.execute(http);
		return response.getEntity();    		
	}
}
