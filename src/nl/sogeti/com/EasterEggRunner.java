package nl.sogeti.com;

import nl.sogeti.logo.SogetiLogoDrawer;

public class EasterEggRunner {

    public static void main(String[] args) {
        EggMetrics eggMetrics = createEggMetrics();

        CellularGenerator cellularGenerator = new CellularGenerator(eggMetrics);
        // rule 30
        boolean[] ruleSet = {false, true, true, true, true, false, false, false};
        EasterEgg.drawEgg(eggMetrics, cellularGenerator.generate(ruleSet));

        // Please don't change the following code:
        new SogetiLogoDrawer().printSogetiLogo();
    }

    private static EggMetrics createEggMetrics() {
        return new EggMetrics(30, 22, 50, 20, Colors.BLACK.getColor(), Colors.GREEN.getColor());
    }
}
