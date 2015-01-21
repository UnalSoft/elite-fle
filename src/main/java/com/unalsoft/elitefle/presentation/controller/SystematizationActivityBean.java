package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.entity.xml.*;
import com.unalsoft.elitefle.vo.ActivityVo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Juan
 */
@ManagedBean(name = "systematizationActivityBean")
@ViewScoped
public class SystematizationActivityBean implements Serializable {

    private final String[] colorRef = {"black", "red", "lime ", "blue", "purple", "deeppink", "goldenrod"};
    private final String[] colorCoRef = {"black", "orange", "greenyellow", "SkyBlue", "orchid", "hotpink", "gold"};
    private final String R3 = "r3";
    private final String R2 = "r2";
    private final String R1 = "r1";
    private ActivityVo activity;
    private Integer idActivity;
    private DocumentTexte text;
    private Referent referent1;
    private Referent referent2;
    private List<Coreferent> coreferent1;
    private List<Coreferent> coreferent2;
    private List<Coreferent> coreferent3;
    private Set<String> coreferentColors;
    private String lastColor = "black";
    private int rightAnswers;

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
        lastColor = "black";
        HashSet<String> coreferentColorsSet = new HashSet<String>(colorCoRef.length);
        coreferentColors = new HashSet<String>(colorCoRef.length);
        this.rightAnswers = 0;
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
                    rightAnswers += 1;
                }
            } else if (elementXML.isCoreferent()) {
                String chaine = (String) ((Coreferent) elementXML).getChaine();
                if (chaine.equals(R1)) {
                    coreferent1.add((Coreferent) elementXML);
                    coreferentColorsSet.add(getCoRefColor(chaine, ((Coreferent) elementXML).getIdn()));
                } else if (chaine.equals(R2)) {
                    coreferent2.add((Coreferent) elementXML);
                    coreferentColorsSet.add(getCoRefColor(chaine, ((Coreferent) elementXML).getIdn()));
                } else if (chaine.equals(R3)) {
                    coreferent3.add((Coreferent) elementXML);
                    coreferentColorsSet.add(getCoRefColor(chaine, ((Coreferent) elementXML).getIdn()));
                }
            }
        }
        for (String color : coreferentColorsSet) {
            coreferentColors.add(color);
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

    /**
     * Look if the chaine is R1, R2 or R3
     *
     * @param chaine
     * @return
     */
    public boolean isRightCoreferent(String chaine) {
        return chaine.equals(R1) || chaine.equals(R2) || chaine.equals(R3);
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

    public List<Coreferent> getCoreferent2() {
        return coreferent2;
    }

    public void setCoreferent2(List<Coreferent> coreferent2) {
        this.coreferent2 = coreferent2;
    }

    public List<Coreferent> getCoreferent3() {
        return coreferent3;
    }

    public void setCoreferent3(List<Coreferent> coreferent3) {
        this.coreferent3 = coreferent3;
    }

    public Set<String> getCoreferentColors() {
        return coreferentColors;
    }

    public void setCoreferentColors(Set<String> coreferentColors) {
        this.coreferentColors = coreferentColors;
    }

    public String getLastColor() {
        return lastColor;
    }

    public void setLastColor(String lastColor) {
        this.lastColor = lastColor;
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

    public int getRightAnswers() {
        return rightAnswers;
    }

    public String lastColor() {
        return lastColor;
    }
}
