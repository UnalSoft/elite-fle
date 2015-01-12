package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.entity.xml.*;
import com.unalsoft.elitefle.vo.ActivityVo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Edward
 */
@ManagedBean(name = "spottingActivityBean3")
@ViewScoped
public class SpottingActivityBean3 implements Serializable {

    private ActivityVo activity;
    private Integer idActivity;
    private DocumentTexte text;

    private final String[] colorRef = {"black", "red", "lime ", "blue", "purple", "deeppink", "goldenrod"};
    private final String[] colorCoRef = {"black", "orange", "greenyellow", "SkyBlue", "orchid", "hotpink", "gold"};

    private Referent referent1;
    private Referent referent2;
    private Referent referent3;
    private List<Coreferent> coreferent1;
    private List<Coreferent> coreferent2;
    private List<Coreferent> coreferent3;
    private int r1GN;
    private int r1Pron;
    private int r2GN;
    private int r2Pron;
    private int r3GN;
    private int r3Pron;
    
    private final String R3 = "r3";
    private final String R2 = "r2";
    private final String R1 = "r1";
    private final String GN = "GN";
    private final String PRON = "Pron";

    public void preRenderView() throws Exception {
        if (getIdActivity() != null) {
            activity = FacadeFactory.getInstance().getActivityFacade().find(getIdActivity());
            if (text == null) {
                text = Parser.parseXML(activity.getUrl());
                if (text != null) {
                    initElements();
                } else {
                    throw new Exception("File not found");
                }
            }
        } else {
            throw new Exception("ActivityId is required");
        }
    }

    private void initElements() {
        coreferent1 = new ArrayList<Coreferent>();
        coreferent2 = new ArrayList<Coreferent>();
        coreferent3 = new ArrayList<Coreferent>();        
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
                if (idn.equals(R1)) {
                    setReferent1((Referent) elementXML);
                } else if (idn.equals(R2)) {
                    setReferent2((Referent) elementXML);
                } else if (idn.equals(R3)) {
                    setReferent3((Referent) elementXML);
                }
            } else if (elementXML.isCoreferent()) {
                Coreferent coref = (Coreferent) elementXML;
                String chaine = (String) (coref).getChaine();
                if (chaine.equals(R1)) {
                    coreferent1.add(coref);
                    if (coref.getSousType().equals(GN)) {
                        r1GN +=1;
                    } else if (coref.getType().equals(PRON)) {
                        r1Pron +=1;
                    }
                } else if (chaine.equals(R2)) {
                    coreferent2.add(coref);
                    if (coref.getSousType().equals(GN)) {
                        r2GN +=1;
                    } else if (coref.getType().equals(PRON)) {
                        r2Pron +=1;
                    }
                } else if (chaine.equals(R3)) {
                    coreferent3.add(coref);
                    if (coref.getSousType().equals(GN)) {
                        r3GN +=1;
                    } else if (coref.getType().equals(PRON)) {
                        r3Pron +=1;
                    }
                }
            }
        }
    }

    public List<ElementXML> getTitleElems() {
        List<ElementXML> elems = new ArrayList<ElementXML>();
        if (text != null) {
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
        if (id > 3) {
            id = 0;
        }
        return colorRef[id];
    }

    public String getCoRefColor(String ref, String idn) {
        String[] split = ref.split("r");
        int id = Integer.parseInt(split[1]);
        if (id > 3) {
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

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public DocumentTexte getText() {
        return text;
    }

    public void setText(DocumentTexte text) {
        this.text = text;
    }

    public Referent getReferent1() {
        return referent1;
    }

    public void setReferent1(Referent referent1) {
        this.referent1 = referent1;
    }

    public Referent getReferent2() {
        return referent2;
    }

    public void setReferent2(Referent referent2) {
        this.referent2 = referent2;
    }

    public List<Coreferent> getCoreferent1() {
        return coreferent1;
    }

    public void setCoreferent1(List<Coreferent> coreferent1) {
        this.coreferent1 = coreferent1;
    }

    public List<Coreferent> getCoreferent3() {
        return coreferent3;
    }

    public void setCoreferent3(List<Coreferent> coreferent3) {
        this.coreferent3 = coreferent3;
    }

    public String getR3() {
        return R3;
    }

    public String getR2() {
        return R2;
    }

    public String getR1() {
        return R1;
    }

    public Referent getReferent3() {
        return referent3;
    }

    public void setReferent3(Referent referent3) {
        this.referent3 = referent3;
    }

    public List<Coreferent> getCoreferent2() {
        return coreferent2;
    }

    public void setCoreferent2(List<Coreferent> coreferent2) {
        this.coreferent2 = coreferent2;
    }

    public int getR1GN() {
        return r1GN;
    }

    public int getR1Pron() {
        return r1Pron;
    }

    public int getR2GN() {
        return r2GN;
    }

    public int getR2Pron() {
        return r2Pron;
    }

    public int getR3GN() {
        return r3GN;
    }

    public int getR3Pron() {
        return r3Pron;
    }

}
