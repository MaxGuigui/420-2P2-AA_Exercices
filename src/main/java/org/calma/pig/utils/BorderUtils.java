package org.calma.pig.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class BorderUtils {

    private Paint topP;
    private Paint rigP;
    private Paint botP;
    private Paint lefP;
    private BorderStrokeStyle topBSS;
    private BorderStrokeStyle rigBSS;
    private BorderStrokeStyle botBSS;
    private BorderStrokeStyle lefBSS;
    private CornerRadii cR;
    private BorderWidths bW;
    private Insets i;

    public BorderUtils() {
        this.topP = Color.BLACK;
        this.rigP = Color.BLACK;
        this.botP = Color.BLACK;
        this.lefP = Color.BLACK;

        this.topBSS = BorderStrokeStyle.SOLID;
        this.rigBSS = BorderStrokeStyle.SOLID;
        this.botBSS = BorderStrokeStyle.SOLID;
        this.lefBSS = BorderStrokeStyle.SOLID;

        this.cR = CornerRadii.EMPTY;
//        Same as : this.cR = new CornerRadii(0.0, 0.0, 0.0, 0.0, false);
        this.bW = BorderWidths.DEFAULT;
//        Same as : this.bW = new BorderWidths(1.0, 1.0, 1.0, 1.0);
        this.i = Insets.EMPTY;
//        Same as : this.i = new Insets(0.0, 0.0, 0.0, 0.0);
    }

    public Border createBorder(){
        return new Border(
            new BorderStroke(
                topP,
                rigP,
                botP,
                lefP,
                topBSS,
                rigBSS,
                botBSS,
                lefBSS,
                cR,
                bW,
                i
            )
        );
    }

    public void setColor(Paint paint) {
        this.topP = paint;
        this.rigP = paint;
        this.botP = paint;
        this.lefP = paint;
    }
    public void setColor(Paint top, Paint right, Paint bottom, Paint left) {
        this.topP = top;
        this.rigP = right;
        this.botP = bottom;
        this.lefP = left;
    }

    public void setBorderStrokeStyle(BorderStrokeStyle borderStrokeStyle) {
        this.topBSS = borderStrokeStyle;
        this.rigBSS = borderStrokeStyle;
        this.botBSS = borderStrokeStyle;
        this.lefBSS = borderStrokeStyle;
    }
    public void setBorderStrokeStyle(
            BorderStrokeStyle top,
            BorderStrokeStyle right,
            BorderStrokeStyle bottom,
            BorderStrokeStyle left) {

        this.topBSS = top;
        this.rigBSS = right;
        this.botBSS = bottom;
        this.lefBSS = left;
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

    public void setBorderWidths(BorderWidths borderWidths) {
        this.bW = borderWidths;
    }
    public void setBorderWidths(Double borderWidths) {
        this.bW = new BorderWidths(borderWidths);
    }
    public void setBorderWidths(Double top, Double right, Double bottom, Double left) {
        this.bW = new BorderWidths(top, right, bottom, left);
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
