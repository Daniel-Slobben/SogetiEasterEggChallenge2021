package nl.sogeti.com;

public class CellularGenerator {

    private final EggMetrics eggMetrics;
    private final boolean[][] matrix;
    private final Colors[][] colorMatrix;

    public CellularGenerator(EggMetrics eggMetrics) {
        this.eggMetrics = eggMetrics;
        matrix = new boolean[eggMetrics.getFrameHeight() + 1][eggMetrics.getFrameWidth() + 1];
        colorMatrix = new Colors[eggMetrics.getFrameHeight() + 1][eggMetrics.getFrameWidth() + 1];

        // Set our initial pixel to true
        matrix[0][eggMetrics.getFrameWidth() / 2] = true;
    }

    /**
     * Generates a colorMatrix based on Cellular Automata
     *
     * @return returns the colormatrix
     */
    public Colors[][] generate(boolean[] ruleSet) {
        for (int i = 0; i < eggMetrics.getFrameHeight(); i++) {
            for (int j = 1; j < eggMetrics.getFrameWidth(); j++) {

                boolean[] cellValues = {matrix[i][j - 1], matrix[i][j], matrix[i][j + 1]};
                int cellValueInteger = booleanArrayToInteger(cellValues);

                // update the matrices with our cellValueInteger
                matrix[i + 1][j] = ruleSet[cellValueInteger];
                colorMatrix[i + 1][j] = getColor(cellValueInteger);
            }
        }
        return colorMatrix;
    }

    /**
     * Converts boolean[] to integer
     *
     * @return the integer
     */
    private int booleanArrayToInteger(boolean[] cellValues) {
        StringBuilder binaryCellValues = new StringBuilder();
        for (boolean cellValue : cellValues) {
            if (cellValue) {
                binaryCellValues.append("1");
            } else {
                binaryCellValues.append("0");
            }
        }
        return Integer.parseInt(binaryCellValues.toString(), 2);
    }

    /**
     * Returns color based on an integer
     * Only compatible with rule 30!
     * Rule 30 = false, true, true, true, true, false, false, false
     * So only integers 1, 2, 3, 4 and default have to return a color.
     */
    private Colors getColor(int decimal) {
        return switch (decimal) {
            case 1 -> Colors.LIGHTBLUE;
            case 2 -> Colors.BLUE;
            case 3 -> Colors.DARKBLUE;
            case 4 -> Colors.DARKERBLUE;
            default -> Colors.BACKGROUND;
        };
    }
}
