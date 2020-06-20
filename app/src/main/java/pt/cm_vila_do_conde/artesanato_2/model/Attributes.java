package pt.cm_vila_do_conde.artesanato_2.model;

import java.io.Serializable;

public class Attributes implements Serializable {
    private boolean acceptsCC;
    private boolean acceptsMBWay;

    public Attributes() {
    }

    public Attributes(boolean acceptsCC, boolean acceptsMBWay) {
        this.acceptsCC = acceptsCC;
        this.acceptsMBWay = acceptsMBWay;
    }

    public boolean isAcceptsCC() {
        return acceptsCC;
    }

    public void setAcceptsCC(boolean acceptsCC) {
        this.acceptsCC = acceptsCC;
    }

    public boolean isAcceptsMBWay() {
        return acceptsMBWay;
    }

    public void setAcceptsMBWay(boolean acceptsMBWay) {
        this.acceptsMBWay = acceptsMBWay;
    }
}
