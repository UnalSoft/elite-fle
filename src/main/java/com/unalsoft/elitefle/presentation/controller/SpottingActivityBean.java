package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.entity.xml.*;
import com.unalsoft.elitefle.vo.ActivityVo;
import com.unalsoft.elitefle.vo.SequenceVo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edward
 */
@ManagedBean(name = "spottingActivityBean")
@ViewScoped
public class SpottingActivityBean implements Serializable {

    private ActivityVo activity;
    private Integer idSequence;
    private DocumentTexte text;
    private String mainReferent;
    private final String MAIN_REF = "r1";
    private String elemToFind;
    private String typeToFind;
    private String subTypeToFind;

    private final String[] colorRef = {"black", "red", "darkblue", "green", "purple", "deeppink", "goldenrod"};
    private final String[] colorCoRef = {"black", "orange", "blue", "greenyellow", "orchid", "hotpink", "gold"};

    private List<ElementXML> selectedElements;
    private List<ElementXML> elementsToDrag;

    public void preRenderView() throws Exception {
        if (getIdSequence() != null) {
            SequenceVo sequence = FacadeFactory.getInstance().getSequenceFacade().find(getIdSequence());
            activity = FacadeFactory.getInstance().getActivityFacade().find(sequence.getIdSpottingActivity());
            if (text == null) {
                text = Parser.parseXML(activity.getUrl());
                if (text != null) {
                    initElements();
                }else {
                    throw new Exception("File not found : " + activity.getUrl());
                }
            }
        } else {
            throw new Exception("ActivityId is required");
        }
    }

    private void initElements() {
        elementsToDrag = new ArrayList<ElementXML>();
        selectedElements = new ArrayList<ElementXML>();
        List<ElementXML> elements = getTitleElems();
        List<ElementXML> sousTitreOrParagraphe = text.getContenu().getSousTitreOrParagraphe();
        for (ElementXML elementXML : sousTitreOrParagraphe) {
            if (elementXML.isSousTitre()) {
                elements.addAll(getSubtitleElems(elementXML));
            }
            if (elementXML.isParagraphe()) {
                elements.addAll(getParagraphElems(elementXML));
            }
        }
        for (ElementXML elementXML : elements) {
            if (elementXML.isReferent()) {
                String idn = ((Referent) elementXML).getIdn();
                if (idn.equals(MAIN_REF)) {
                    setMainReferent(getElementsFromReferent(elementXML));
                }
            }
            if (elementXML.isCoreferent()) {
                String chaine = (String) ((Coreferent) elementXML).getChaine();
                if (chaine.equals(MAIN_REF)) {
                    selectedElements.add(elementXML);
                } else {
                    elementsToDrag.add(elementXML);
                }
            }
        }
        int random = (int) Math.floor(Math.random() * (selectedElements.size() - 1));
        ElementXML randomElem = selectedElements.remove(random);
        setElemToFind(((Coreferent) randomElem).getIdn());
        setTypeToFind(((Coreferent) randomElem).getType());
        setSubTypeToFind(((Coreferent) randomElem).getSousType());
        elementsToDrag.add(randomElem);

    }

    public List<ElementXML> getTitleElems() {
        List<ElementXML> elems = new ArrayList<ElementXML>();
        if (text != null && text.getContenu().getTitre() != null) {
            List<Phrase> phrases = text.getContenu().getTitre().getPhrase();
            for (Phrase phrase : phrases) {
                addPhraseElems(phrase, elems);
            }
        }
        return elems;
    }

    private void addPhraseElems(Phrase phrase, List<ElementXML> elems) {
        List<ElementXML> propOrElement = phrase.getPropOrElement();
        for (ElementXML elementXML : propOrElement) {
            if (elementXML.isProp()) {
                List<ElementXML> propElems = getPropElems(elementXML);
                elems.addAll(propElems);
            } else if (elementXML.isElem()) {
                elems.add(elementXML);
            }
        }
    }

    private List<ElementXML> getPropElems(ElementXML prop) {
        List<ElementXML> elems = new ArrayList<ElementXML>();
        if (prop != null) {
            List<ElementXML> propElems = ((Prop) prop).getReferentOrCoreferentOrElementOrProp();
            for (ElementXML elementXML : propElems) {
                if (elementXML.isProp()) {
                    List<ElementXML> subPropElems = getPropElems(elementXML);
                    elems.addAll(subPropElems);
                } else {
                    elems.add(elementXML);
                }
            }
        }
        return elems;
    }

    public List<ElementXML> getSubtitleElems(ElementXML subtitle) {
        List<ElementXML> elems = new ArrayList<ElementXML>();
        if (subtitle != null) {
            List<Phrase> phrases = ((SousTitre) subtitle).getPhrase();
            for (Phrase phrase : phrases) {
                addPhraseElems(phrase, elems);
            }
        }
        return elems;
    }

    public List<ElementXML> getParagraphElems(ElementXML paragraph) {
        List<ElementXML> elems = new ArrayList<ElementXML>();
        if (paragraph != null) {
            List<ElementXML> phraseOrElement = ((Paragraphe) paragraph).getPhraseOrElement();
            for (ElementXML elementXML : phraseOrElement) {
                if (elementXML.isElem()) {
                    elems.add(elementXML);
                } else {
                    addPhraseElems((Phrase) elementXML, elems);
                }
            }
        }
        return elems;
    }

    public String getElementsFromReferent(ElementXML element) {
        String elements = new String();
        List<Element> elems = null;
        if (element.isReferent()) {
            elems = ((Referent) element).getElement();
        } else if (element.isCoreferent()) {
            elems = ((Coreferent) element).getElement();
        }
        for (Element el : elems) {
            elements = elements.concat(el.getvalue() + " ");
        }
        return elements;
    }

    public String getRefColor(String idn) {
        String[] split = idn.split("r");
        int id = Integer.parseInt(split[1]);
        if (id != 1) {
            id = 0;
        }
        return colorRef[id];
    }

    public String getCoRefColor(String ref, String idn) {
        String[] split = ref.split("r");
        int id = Integer.parseInt(split[1]);
        if (id != 1 || idn.equals(elemToFind)) {
            id = 0;
        }
        return colorCoRef[id];
    }

    public ActivityVo getActivity() {
        return activity;
    }

    public void setActivity(ActivityVo activity) {
        this.activity = activity;
    }

    public Integer getIdSequence() {
        return idSequence;
    }

    public void setIdSequence(Integer idSequence) {
        this.idSequence = idSequence;
    }

    public DocumentTexte getText() {
        return text;
    }

    public void setText(DocumentTexte text) {
        this.text = text;
    }

    public String getMainReferent() {
        return mainReferent;
    }

    public void setMainReferent(String mainReferent) {
        this.mainReferent = mainReferent;
    }

    public List<ElementXML> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(List<ElementXML> selectedElements) {
        this.selectedElements = selectedElements;
    }

    public List<ElementXML> getElementsToDrag() {
        return elementsToDrag;
    }

    public void setElementsToDrag(List<ElementXML> elementsToDrag) {
        this.elementsToDrag = elementsToDrag;
    }

    public String getMAIN_REF() {
        return MAIN_REF;
    }

    public String getElemToFind() {
        return elemToFind;
    }

    public void setElemToFind(String elemToFInd) {
        this.elemToFind = elemToFInd;
    }

    public String getTypeToFind() {
        return typeToFind;
    }

    public void setTypeToFind(String typeToFind) {
        this.typeToFind = typeToFind;
    }

    public String getSubTypeToFind() {
        return subTypeToFind;
    }

    public void setSubTypeToFind(String subTypeToFind) {
        this.subTypeToFind = subTypeToFind;
    }

}
