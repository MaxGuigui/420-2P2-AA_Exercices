package org.calma.pig.exercices.laboSpacial.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import javafx.geometry.Point2D;

import java.io.IOException;

public class Point2DDeserializer extends JsonDeserializer<Point2D> {
    @Override
    public Point2D deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode root = p.getCodec().readTree(p);

        System.out.println(root);

        int x = (int) ((DoubleNode) root.get("x")).numberValue();
        int y = (int) ((DoubleNode) root.get("y")).numberValue();

        return new Point2D(x, y);
    }
}