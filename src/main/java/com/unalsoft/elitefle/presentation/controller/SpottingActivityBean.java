package com.unalsoft.elitefle.presentation.controller;

import com.unalsoft.elitefle.businesslogic.facade.FacadeFactory;
import com.unalsoft.elitefle.entity.xml.*;
import com.unalsoft.elitefle.vo.ActivityVo;
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
public class SpottingActivityBean {

    private ActivityVo activity;
    private Integer idActivity;
    private DocumentTexte text;
    private final String[] colorRef = {"red", "darkblue", "green", "purple", "deeppink", "goldenrod"};
    private final String[] colorCoRef = {"orange", "blue", "greenyellow", "orchid", "hotpink", "gold"};

    public void preRenderView() {
        if (getIdActivity() != null) {
            activity = FacadeFactory.getInstance().getActivityFacade().find(getIdActivity());
            text = Parser.parseXML(activity.getUrl());
        } else {
            //@TODO Error page
        }
    }
    
    public List<ElementXML> getTitleElems() {
        List<ElementXML> elems =  new ArrayList<ElementXML>();
        List<Phrase> phrases = text.getContenu().getTitre().getPhrase();
        for (Phrase phrase : phrases) {
            addPhraseElems(phrase, elems);
        }
        return elems;
    }

    private void addPhraseElems(Phrase phrase, List<ElementXML> elems) {
        List<ElementXML> propOrElement = phrase.getPropOrElement();
        for (ElementXML elementXML : propOrElement) {
            if (elementXML.isProp()){
                List<ElementXML> propElems = getPropElems(elementXML);
                elems.addAll(propElems);
            } else if (elementXML.isElem()) {
                elems.add(elementXML);
            }
        }
    }
    
    private List<ElementXML> getPropElems(ElementXML prop) {
        List<ElementXML> elems =  new ArrayList<ElementXML>();
        List<ElementXML> propElems = ((Prop)prop).getReferentOrCoreferentOrElementOrProp();
        for (ElementXML elementXML : propElems) {
            if (elementXML.isProp()) {
                List<ElementXML> subPropElems = getPropElems(elementXML);
                elems.addAll(subPropElems);
            } else {
                elems.add(elementXML);
            }
        }
        return elems;
    }
    
    public List<ElementXML> getSubtitleElems(ElementXML subtitle) {
        List<ElementXML> elems =  new ArrayList<ElementXML>();
        List<Phrase> phrases = ((SousTitre)subtitle).getPhrase();
        for (Phrase phrase : phrases) {
            addPhraseElems(phrase, elems);
        }
        return elems;
    }
    
    public List<ElementXML> getParagraphElems(ElementXML paragraph) {
        List<ElementXML> elems =  new ArrayList<ElementXML>();
        List<ElementXML> phraseOrElement = ((Paragraphe)paragraph).getPhraseOrElement();
        for (ElementXML elementXML : phraseOrElement) {
            if (elementXML.isElem()) {
                elems.add(elementXML);
            } else {
                addPhraseElems((Phrase) elementXML, elems);
            }
        }
        return elems;
    }
    
    public String getRefColor(String idn) {
        String[] split = idn.split("r");
        int id = Integer.parseInt(split[1]) - 1;
        return colorRef[id];
    }
    
    public String getCoRefColor(String ref) {
        String[] split = ref.split("r");
        int id = Integer.parseInt(split[1]) - 1;
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

}
