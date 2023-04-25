package org.calma.pig.exercices.laboSpacial.jackson;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ColorSerializer extends JsonSerializer<Color> {
    @Override
    public void serialize(Color value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("color");
        String HexColor = String.format( "#%02X%02X%02X",
                (int)( value.getRed() * 255 ),
                (int)( value.getGreen() * 255 ),
                (int)( value.getBlue() * 255 ) );

        gen.writeString(HexColor);
        gen.writeEndObject();
    }
}
