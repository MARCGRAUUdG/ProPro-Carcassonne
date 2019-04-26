public class Joc {
    public Joc(){
        Gui.posaFitxa(5,5, 3);
        Gui.posaFitxa(4,5, 10);
        Gui.posaFitxa(4,6, 15);
        Gui.posaFitxa(5,4, 17);
        Gui.posaFitxa(3,5, 6);

        Gui.setScore(1,24);
        Gui.setScore(2,15);
        Gui.setScore(4,3);
    }

    public static void repNomFitxer(String text) {
        LlegirFitxer.nomFitxer(text);
    }
}
