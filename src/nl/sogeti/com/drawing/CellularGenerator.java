package nl.sogeti.com.drawing;

import nl.sogeti.com.EggMetrics;

public class CellularGenerator {

   private EggMetrics eggMetrics;
   private boolean[] ruleSet;
   private boolean[][] matrix;

   public CellularGenerator(EggMetrics eggMetrics, boolean[] ruleSet) {
      this.eggMetrics = eggMetrics;
      matrix = new boolean[eggMetrics.getFrameHeight()+1][eggMetrics.getFrameWidth()+1];
      this.ruleSet = ruleSet;

      // Set our initial pixel to true
      matrix[1][eggMetrics.getFrameWidth()/2] = true;
   }

   public boolean[][] generate() {
      System.out.println(eggMetrics.getFrameWidth());
      for (int i = 1; i < eggMetrics.getFrameHeight(); i++) {
         for (int j = 1; j < eggMetrics.getFrameWidth(); j++) {

            // matrix i+1 j is the target we will decide for
            boolean[] cellValues =  {matrix[i][j - 1], matrix[i][j], matrix[i][j+1]};
            matrix[i+1][j] = decide(cellValues);
         }
      }
      return matrix;
   }

   /**
    *
    * @return
    */
   private boolean decide(boolean[] cellValues) {
      StringBuilder binaryCellValues = new StringBuilder();
      for (boolean cellValue: cellValues) {
         if (cellValue) {
            binaryCellValues.append("1");
         } else {
            binaryCellValues.append("0");
         }
      }
      int decimal = Integer.parseInt(binaryCellValues.toString(),2);
      return ruleSet[decimal];
   }


}
