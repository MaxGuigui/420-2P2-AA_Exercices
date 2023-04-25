package org.calma.pig.exercices.laboCollegeGrid.models.cell;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.calma.pig.exercices.laboCollegeGrid.Grid;

public class CellListener {
    public static class CellClickListener implements EventHandler<MouseEvent> {
        private Grid grid;

        public CellClickListener(Grid grid)
        {
            this.grid = grid;
        }
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                int gridX = (int) (x / grid.getCellSize());
                int gridY = (int) (y / grid.getCellSize());

                // ignore out of bounds
                if (gridX < 0 || gridX > grid.getColumns() - 1 || gridY < 0 || gridY > grid.getRows() - 1) {
                    return;
                }

                Cell cell = grid.getCells()[gridX][gridY];
                if (cell != null) {
                    grid.onCellClick(event, cell, gridX, gridY);
                }
        }
    }

    public static class CellHoverListener implements EventHandler<MouseEvent> {
        private Grid grid;

        public CellHoverListener(Grid grid) {
            this.grid = grid;
        }

        @Override
        public void handle(MouseEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (x < 0 || y < 0) {
                return;
            }

            int gridX = (int) (x / grid.getCellSize());
            int gridY = (int) (y / grid.getCellSize());

            // ignore out of bounds
            if (gridX < 0 || gridX > grid.getColumns() || gridY < 0 || gridY > grid.getRows()) {
                return;
            }

            Cell cell = grid.getCells()[gridX][gridY];
            if (cell != null) {
                grid.onCellHover(cell, gridX, gridY);
            }
        }
    }
}
