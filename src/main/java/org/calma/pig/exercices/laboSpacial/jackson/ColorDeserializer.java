package org.calma.pig.exercices.laboSpacial.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ColorDeserializer extends JsonDeserializer<Color> {
    @Override
    public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode root = p.getCodec().readTree(p);

        double red      = (Double) ((DoubleNode) root.get("red")).numberValue();
        double green    = (Double) ((DoubleNode) root.get("green")).numberValue();
        double blue     = (Double) ((DoubleNode) root.get("blue")).numberValue();
        double opacity  = (Double) ((DoubleNode) root.get("opacity")).numberValue();

        return new Color(red, green, blue, opacity);
    }
}