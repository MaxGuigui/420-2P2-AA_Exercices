package org.calma.pig.exercices.laboSpacial;

import javafx.scene.input.MouseEvent;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;

public interface IGridEvents {
    void onCellClick(MouseEvent event, Cell cell, int gridPosX, int gridPosY);
    void onCellHover(Cell cell, int gridX, int gridY);
}
