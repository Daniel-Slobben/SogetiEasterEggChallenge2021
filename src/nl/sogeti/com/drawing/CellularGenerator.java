package nl.sogeti.com.drawing;

import nl.sogeti.com.Colors;
import nl.sogeti.com.EggMetrics;

public class CellularGenerator {

   private EggMetrics eggMetrics;
   private boolean[] ruleSet;
   private boolean[][] matrix;
   private Colors[][] colorMatrix;

   public CellularGenerator(EggMetrics eggMetrics, boolean[] ruleSet) {
      this.eggMetrics = eggMetrics;
      matrix = new boolean[eggMetrics.getFrameHeight()+1][eggMetrics.getFrameWidth()+1];
      this.ruleSet = ruleSet;
      colorMatrix = new Colors[eggMetrics.getFrameHeight()+1][eggMetrics.getFrameWidth()+1];

      // Set our initial pixel to true
      matrix[0][eggMetrics.getFrameWidth()/2] = true;
   }

   public Colors[][] generate() {
      System.out.println(eggMetrics.getFrameWidth());
      for (int i = 0; i < eggMetrics.getFrameHeight(); i++) {
         for (int j = 1; j < eggMetrics.getFrameWidth(); j++) {

            // matrix i+1 j is the target we will decide for
            boolean[] cellValues =  {matrix[i][j - 1], matrix[i][j], matrix[i][j+1]};

            int cellValueInteger = booleanArrayToInteger(cellValues);
            matrix[i+1][j] = ruleSet[cellValueInteger];
            if (ruleSet[cellValueInteger]) {
               colorMatrix[i+1][j] = getColor(cellValueInteger);
            } else {
               colorMatrix[i+1][j] = Colors.CURSED;
            }
         }
      }
      return colorMatrix;
   }

   /**
    *
    * @return
    */
   private int booleanArrayToInteger(boolean[] cellValues) {



      StringBuilder binaryCellValues = new StringBuilder();
      for (boolean cellValue: cellValues) {
         if (cellValue) {
            binaryCellValues.append("1");
         } else {
            binaryCellValues.append("0");
         }
      }
      return Integer.parseInt(binaryCellValues.toString(),2);
   }

   /**
    * get corresponding color
    *  rule 30 boolean[] ruleSet = {false, true, true, true, true, false, false, false};
    */
   private Colors getColor(int decimal) {
      return switch (decimal) {
         case 1 -> Colors.LIGHTPURPLE;
         case 2 -> Colors.PURPLE;
         case 3 -> Colors.DARKERPURPLE;
         case 4 -> Colors.DARKESTPURPLE;
         default -> Colors.BLACK;
      };
   }


}
