package ejercicioAOP.model;

import ejercicioAOP.UI.RadioCompetition;
import ejercicioAOP.persistencia.PersistenciaEnBase;
import ejercicioAOP.persistencia.PersistenciaLocal;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().start();
                } catch (Exception e) {
// log exception...
                    System.out.println(e);
                }
            }
        });
    }

    private void start() {
        var persistencia = new PersistenciaLocal();
        new RadioCompetition(persistencia);
    }
}
