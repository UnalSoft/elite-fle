package com.unalsoft.elitefle.entity.xml;

/**
 *
 * @author Edward
 */
public class ElementXML {
    
    protected String tag;
    protected boolean prop;
    protected boolean elem;
    protected boolean referent;
    protected boolean coreferent;    
    protected boolean sousTitre;
    protected boolean paragraphe;    
    
    public String getTag(){
        return tag;
    }

    public boolean isProp() {
        return prop;
    }

    public boolean isElem() {
        return elem;
    }

    public boolean isReferent() {
        return referent;
    }

    public boolean isCoreferent() {
        return coreferent;
    }

    public boolean isSousTitre() {
        return sousTitre;
    }

    public boolean isParagraphe() {
        return paragraphe;
    }
    
}
