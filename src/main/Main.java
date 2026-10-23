package main;

import vista.FrmVideojuegos;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al aplicar estilo: "
                    + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FrmVideojuegos().setVisible(true);
        });
    }
}
