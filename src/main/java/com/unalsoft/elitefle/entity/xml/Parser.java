package com.unalsoft.elitefle.entity.xml;

import com.unalsoft.elitefle.presentation.controller.SpottingActivityBean;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Edward
 */
public class Parser {

    /**
     * Parse a XML File with a valid format into a DocumentTexte Object
     *
     * @param url XML file url
     * @return doc Object representation of the XML File
     */
    public static DocumentTexte parseXML(String url) {
        DocumentTexte doc = null;
        try {

            //Get the DOM Builder Factory
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            //Get the DOM Builder
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            //Load and Parse the XML document            
            Document document = builder.parse(new File(url));

            //Document Text
            ObjectFactory factory = new ObjectFactory();
            doc = factory.createDocumentTexte();

            org.w3c.dom.Element documentElement = document.getDocumentElement();
            doc.setIdDoc(documentElement.getAttribute("id_doc"));

            //Entete
            org.w3c.dom.Element enteteNode = (org.w3c.dom.Element) documentElement.getElementsByTagName("ENTETE").item(0);
            doc.setEntete(factory.createEntete());
            doc.getEntete().setTitreDoc(getTextValue(enteteNode, "titre_doc"));
            doc.getEntete().setAuteur(getTextValue(enteteNode, "auteur"));

            //Entete - source
            org.w3c.dom.Element sourceNode = (org.w3c.dom.Element) enteteNode.getElementsByTagName("source").item(0);
            Source source = factory.createSource();
            source.setType(sourceNode.getAttribute("type"));
            source.setPeriodicite(sourceNode.getAttribute("periodicite"));
            source.setvalue(getTextValue(enteteNode, "source"));
            doc.getEntete().setSource(source);

            doc.getEntete().setDate(getTextValue(enteteNode, "date"));
            doc.getEntete().setType(getTextValue(enteteNode, "type"));
            doc.getEntete().setNiveau(getTextValue(enteteNode, "niveau"));

            //Contenu
            org.w3c.dom.Element contenuNode = (org.w3c.dom.Element) documentElement.getElementsByTagName("CONTENU").item(0);
            doc.setContenu(factory.createContenu());

            //Contenu - titre (Optional)
            org.w3c.dom.Element titreNode = (org.w3c.dom.Element) contenuNode.getElementsByTagName("titre").item(0);
            if (titreNode != null) {
                Titre titre = parseTitre(factory, titreNode);
                doc.getContenu().setTitre(titre);
            }
            
            //Contenu - (subtitre?, pragraphe)*
            if(contenuNode.hasChildNodes()) {
                NodeList sousTOrParagList = contenuNode.getChildNodes();                
                for (int i = 0; i < sousTOrParagList.getLength(); i++) {
                    try {
                        org.w3c.dom.Element sousTOrParag = (org.w3c.dom.Element) sousTOrParagList.item(i);
                        if (sousTOrParag.getTagName().equals("sous-titre")) {
                            SousTitre sousTitre = parseSousTitre(sousTOrParag, factory);
                            doc.getContenu().getSousTitreOrParagraphe().add(sousTitre);
                        } else if (sousTOrParag.getTagName().equals("paragraphe")) {
                            Paragraphe paragraphe = parseParagraphe(sousTOrParag, factory);
                            doc.getContenu().getSousTitreOrParagraphe().add(paragraphe);
                        }
                    } catch (Exception e) {
                    }
                    
                }
            }
            
        } catch (Exception e) {
            Logger.getLogger(SpottingActivityBean.class.getName()).log(Level.SEVERE, null, e);
        }

        return doc;
    }

    private static Titre parseTitre(ObjectFactory factory, org.w3c.dom.Element titreNode) {
        Titre titre = factory.createTitre();
        NodeList nodeList = titreNode.getElementsByTagName("phrase");
        List<Phrase> phrases = titre.getPhrase();
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Element phraseNode = (org.w3c.dom.Element) nodeList.item(i);
                Phrase phrase = parsePhrase(phraseNode, factory);
                phrases.add(phrase);
            }
        }
        return titre;
    }

    private static String getTextValue(org.w3c.dom.Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }

    private static int getIntValue(org.w3c.dom.Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    private static Phrase parsePhrase(org.w3c.dom.Element phraseNode, ObjectFactory factory) {
        Phrase phrase = factory.createPhrase();
        phrase.setType(phraseNode.getAttribute("type"));
        if (phraseNode.hasChildNodes()) {
            NodeList propOrElementList = phraseNode.getChildNodes();
            for (int i = 0; i < propOrElementList.getLength(); i++) {
                try {
                    org.w3c.dom.Element propOrElementNode = (org.w3c.dom.Element) propOrElementList.item(i);
                    if (propOrElementNode.getTagName().equals("prop")) {
                        Prop prop = parseProp(propOrElementNode, factory);
                        phrase.getPropOrElement().add(prop);
                    } else if (propOrElementNode.getTagName().equals("element")) {
                        Element element = parseElement(propOrElementNode, factory);
                        phrase.getPropOrElement().add(element);                        
                    }
                } catch (Exception e) {
                }
            }
        }
       
        return phrase;
    }

    private static Prop parseProp(org.w3c.dom.Element propNode, ObjectFactory factory) {
        Prop prop = factory.createProp();
        prop.setType(propNode.getAttribute("type"));
        prop.setSousType(propNode.getAttribute("sous-type"));
        if (propNode.hasChildNodes()) {
            NodeList refOrCorefOrElemOrPropList = propNode.getChildNodes();
            for (int i = 0; i < refOrCorefOrElemOrPropList.getLength(); i++) {
                try {
                    org.w3c.dom.Element refOrCorefOrElemOrPropNode = (org.w3c.dom.Element) refOrCorefOrElemOrPropList.item(i);
                    if (refOrCorefOrElemOrPropNode.getTagName().equals("referent")) {
                        Referent referent = parseReferent(refOrCorefOrElemOrPropNode, factory);
                        prop.getReferentOrCoreferentOrElementOrProp().add(referent);
                    } else if (refOrCorefOrElemOrPropNode.getTagName().equals("coreferent")) {
                        Coreferent coreferent = parseCoreferent(refOrCorefOrElemOrPropNode, factory);
                        prop.getReferentOrCoreferentOrElementOrProp().add(coreferent);
                    } else if (refOrCorefOrElemOrPropNode.getTagName().equals("element")) {
                        Element element = parseElement(refOrCorefOrElemOrPropNode, factory);
                        prop.getReferentOrCoreferentOrElementOrProp().add(element);
                    } else if (refOrCorefOrElemOrPropNode.getTagName().equals("prop")) {
                        Prop propCh = parseProp(refOrCorefOrElemOrPropNode, factory);
                        prop.getReferentOrCoreferentOrElementOrProp().add(propCh);
                    }
                } catch (Exception e) {
                }
            }
        }
        return prop;
    }

    private static Element parseElement(org.w3c.dom.Element elementNode, ObjectFactory factory) {
        Element element = factory.createElement();
        element.setType(elementNode.getAttribute("type"));
        element.setCat(elementNode.getAttribute("cat"));
        element.setMode(elementNode.getAttribute("mode"));
        element.setTemps(elementNode.getAttribute("temps"));
        element.setPers(elementNode.getAttribute("pers"));
        element.setGenre(elementNode.getAttribute("genre"));
        element.setNombre(elementNode.getAttribute("nombre"));
        element.setvalue(elementNode.getFirstChild().getNodeValue());        
        return element;
    }

    private static Referent parseReferent(org.w3c.dom.Element referentNode, ObjectFactory factory) {
        Referent referent = factory.createReferent();
        referent.setType(referentNode.getAttribute("type"));
        referent.setSousType(referentNode.getAttribute("sous-type"));
        referent.setIdn(referentNode.getAttribute("idn"));
        if (referentNode.hasChildNodes()) {
            NodeList elemList = referentNode.getElementsByTagName("element");
            for (int i = 0; i < elemList.getLength(); i++) {
                org.w3c.dom.Element elemNode = (org.w3c.dom.Element) elemList.item(i);
                Element element = parseElement(elemNode, factory);
                referent.getElement().add(element);
            }
        }
        return referent;
    }

    private static Coreferent parseCoreferent(org.w3c.dom.Element corefNode, ObjectFactory factory) {
        Coreferent coreferent = factory.createCoreferent();
        coreferent.setType(corefNode.getAttribute("type"));
        coreferent.setSousType(corefNode.getAttribute("sous-type"));
        coreferent.setClasse(corefNode.getAttribute("classe"));
        coreferent.setIdn(corefNode.getAttribute("idn"));
        coreferent.setChaine(corefNode.getAttribute("chaine"));
        coreferent.setPosition(corefNode.getAttribute("position"));
        coreferent.setReference(corefNode.getAttribute("reference"));
        if (corefNode.hasChildNodes()) {
            NodeList elemList = corefNode.getElementsByTagName("element");
            for (int i = 0; i < elemList.getLength(); i++) {
                org.w3c.dom.Element elemNode = (org.w3c.dom.Element) elemList.item(i);
                Element element = parseElement(elemNode, factory);
                coreferent.getElement().add(element);
            }
        }
        return coreferent;
    }

    private static SousTitre parseSousTitre(org.w3c.dom.Element sousTitreNode, ObjectFactory factory) {
        SousTitre sousTitre = factory.createSousTitre();
        NodeList nodeList = sousTitreNode.getElementsByTagName("phrase");
        List<Phrase> phrases = sousTitre.getPhrase();
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Element phraseNode = (org.w3c.dom.Element) nodeList.item(i);
                Phrase phrase = parsePhrase(phraseNode, factory);
                phrases.add(phrase);
            }
        }
        return sousTitre;
    }

    private static Paragraphe parseParagraphe(org.w3c.dom.Element paragrapheNode, ObjectFactory factory) {
        Paragraphe paragraphe = factory.createParagraphe();
        if (paragrapheNode.hasChildNodes()) {
            NodeList phraseOrElementList = paragrapheNode.getChildNodes();
            for (int i = 0; i < phraseOrElementList.getLength(); i++) {
                try {
                    org.w3c.dom.Element phraseOrElementNode = (org.w3c.dom.Element) phraseOrElementList.item(i);
                    if (phraseOrElementNode.getTagName().equals("phrase")) {
                        Phrase phrase = parsePhrase(phraseOrElementNode, factory);
                        paragraphe.getPhraseOrElement().add(phrase);
                    } else if (phraseOrElementNode.getTagName().equals("element")) {
                        Element element = parseElement(phraseOrElementNode, factory);
                        paragraphe.getPhraseOrElement().add(element);
                    }
                } catch (Exception e) {
                }
            }
        }
        return paragraphe;
    }

}
