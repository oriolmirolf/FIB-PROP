package FONTS.src.main.persistencia;

import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;

public class TransformadorXML implements TransformadorArxius {

    /**
     *	Mètode per importar un arxiu a format XML
     *	@param path Path de l'arxiu a importar
     *	@return Un Container amb els elements del container "setejats" amb els de l'arxiu.
     * */
    public Container importar(String path) throws ErrorIntern {
        File file = new File(path);
        String autor = "";
        String titol = "";
        String contingut = "";
        LocalDateTime dataCreacio = LocalDateTime.now();
        LocalDateTime dataDarreraModificacio = LocalDateTime.now();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList autorDOM = document.getElementsByTagName("autor");
            for (int i = 0; i < autorDOM.getLength(); ++i) {
                Node node = autorDOM.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    autor = e.getTextContent();
                }
            }

            NodeList titolDOM = document.getElementsByTagName("títol");
            for (int i = 0; i < titolDOM.getLength(); ++i) {
                Node node = titolDOM.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    titol = e.getTextContent();
                }
            }

            NodeList contingutDOM = document.getElementsByTagName("contingut");
            for (int i = 0; i < contingutDOM.getLength(); ++i) {
                Node node = contingutDOM.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    contingut = e.getTextContent();
                }
            }
        } catch (Exception err) {
            throw new ErrorIntern();
        }
        return new Container(titol, autor, path, dataCreacio, dataDarreraModificacio, contingut);
    }

    /**
     *	Mètode per exportar un arxiu a format XML
     *	@param destinacio Path de destinació de l'arxiu un cop exportat.
     *	@param c Container que conté l'informació a exportar.
     *	@throws ErrorIntern
     * */
    public void exportar(String destinacio, Container c) throws ErrorIntern {
        File file = new File(destinacio);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, "Document", null);
            document.setXmlVersion("1.0");

            Element autor = document.createElement("autor");
            Text textAutor = document.createTextNode(c.getAutor());
            autor.appendChild(textAutor);

            Element titol = document.createElement("títol");
            Text textTitol = document.createTextNode(c.getTitol());
            titol.appendChild(textTitol);

            Element contingut = document.createElement("contingut");
            Text textContingut = document.createTextNode(c.getContingut());
            contingut.appendChild(textContingut);

            document.getDocumentElement().appendChild(autor);
            document.getDocumentElement().appendChild(titol);
            document.getDocumentElement().appendChild(contingut);

            Source source = new DOMSource(document);
            Result result = new StreamResult(file);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (Exception err) {
            throw new ErrorIntern();
        }
    }
}
