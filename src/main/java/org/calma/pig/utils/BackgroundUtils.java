package org.calma.pig.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public class BackgroundUtils {

    private Paint paint;
    private CornerRadii cornerRadii;
    private Insets insets;

    public BackgroundUtils() {
        this.paint = Color.BLACK;
        this.cornerRadii = new CornerRadii(0.0);
        this.insets = new Insets(0.0,0.0,0.0,0.0);
    }

    public Background createBgWithColor(Paint color){
        this.paint = color;
        return new Background(new BackgroundFill(this.paint, this.cornerRadii, this.insets));
    }
    public Background createBgWithCornerRadius(Double v){
        this.cornerRadii = new CornerRadii(v);
        return new Background(new BackgroundFill(this.paint, this.cornerRadii, this.insets));
    }
    public Background createBgWithInsets(Double v){
        this.insets = new Insets(v);
        return new Background(new BackgroundFill(this.paint, this.cornerRadii, this.insets));
    }
    public Background createBgWithInsets(Double v, Double v1, Double v2, Double v3){
        return new Background(new BackgroundFill(this.paint, this.cornerRadii, new Insets(v,v2,v2,v3)));
    }

    public Background changeBgColorBy(Background bg, Paint color){
        CornerRadii cornerR = bg.getFills().get(0).getRadii();

        return new Background(new BackgroundFill(color, cornerR, bg.getOutsets()));
    }
    public Background changeBgCornerRadiusBy(Background bg, Double v){
        Paint paint = bg.getFills().get(0).getFill();

        return new Background(new BackgroundFill(paint, new CornerRadii(v), bg.getOutsets()));
    }
    public Background changeBgInsetsBy(Background bg, Double v){
        Paint paint = bg.getFills().get(0).getFill();
        CornerRadii cornerR = bg.getFills().get(0).getRadii();

        return new Background(new BackgroundFill(paint, cornerR, new Insets(v)));
    }
    public Background changeBgInsetsBy(Background bg, Double v, Double v1, Double v2, Double v3){
        Paint paint = bg.getFills().get(0).getFill();
        CornerRadii cornerR = bg.getFills().get(0).getRadii();

        return new Background(new BackgroundFill(paint, cornerR, new Insets(v,v1,v2,v3)));
    }

    public Background createBgBlack(){
        return this.createBgWithColor(Color.BLACK);
    }
}
