package org.calma.pig.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public class BackgroundUtils {

    private Paint p;
    private CornerRadii cR;
    private Insets i;

    public BackgroundUtils() {
        this.p = Color.BLACK;
        this.cR = CornerRadii.EMPTY;
//        Same as : this.cR = new CornerRadii(0.0, 0.0, 0.0, 0.0, false);
        this.i = Insets.EMPTY;
//        Same as : this.i = new Insets(0.0, 0.0, 0.0, 0.0);
    }

    public Background createBackground(){
        return new Background(new BackgroundFill(p, cR, i));
    }

    public void setPaint(Paint paint) {
        this.p = paint;
    }

    public void setCornerRadii(CornerRadii cornerRadii) {
        this.cR = cornerRadii;
    }
    public void setCornerRadii(Double cornerRadii) {
        this.cR = new CornerRadii(cornerRadii, false);
    }
    public void setCornerRadii(Double top, Double right, Double bottom, Double left) {
        this.cR = new CornerRadii(top, right, bottom, left, false);
    }
    public void setCornerRadiiAsPercent(Double cornerRadii) {
        this.cR = new CornerRadii(cornerRadii, true);
    }
    public void setCornerRadiiAsPercent(Double top, Double right, Double bottom, Double left) {
        this.cR = new CornerRadii(top, right, bottom, left, true);
    }

    public void setInsets(Insets insets) {
        this.i = insets;
    }
    public void setInsets(Double insets) {
        this.i = new Insets(insets);
    }
    public void setInsets(Double top, Double right, Double bottom, Double left) {
        this.i = new Insets(top, right, bottom, left);
    }
}
