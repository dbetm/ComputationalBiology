package sat_3;

/**
 *
 * @author david
 */
public class Intercambio extends javax.swing.JDialog {
    private Manager mg;
    private String hashOrigen, hashDestino;
    /**
     * Creates new form Intercambio
     */
    public Intercambio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Intercambio");
    }
    
    public Intercambio(java.awt.Frame parent, boolean modal, String hashOrigen, 
            String hashDestino, Manager man) {
        super(parent, modal);
        initComponents();
        this.setTitle("Intercambio");
        this.lblHashDestino.setText(hashDestino);
        this.lblHashOrigen.setText(hashOrigen);
        this.mg = man;
        this.hashDestino = hashDestino;
        this.hashOrigen = hashOrigen;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblHashOrigen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblHashDestino = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnMejor = new javax.swing.JButton();
        btnMascara = new javax.swing.JButton();
        btnNAleatorios = new javax.swing.JButton();
        txtNAleatorios = new javax.swing.JTextField();
        txtNMejores = new javax.swing.JTextField();
        btnNMejores = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Origen:");

        lblHashOrigen.setText("hashCode");

        jLabel2.setText("Destino:");

        lblHashDestino.setText("hashCode");

        jLabel3.setText("Intercambiar");

        btnMejor.setText("Mejor");
        btnMejor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMejorActionPerformed(evt);
            }
        });

        btnMascara.setText("Máscara");
        btnMascara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMascaraActionPerformed(evt);
            }
        });

        btnNAleatorios.setText("N-Aleatorios");
        btnNAleatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNAleatoriosActionPerformed(evt);
            }
        });

        btnNMejores.setText("N-Mejores");
        btnNMejores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNMejoresActionPerformed(evt);
            }
        });

        jLabel4.setText("N");

        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtLog.setText("log");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNMejores, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(txtLog))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNAleatorios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNMejores, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(txtNAleatorios))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHashDestino)
                            .addComponent(lblHashOrigen)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMascara, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(btnMejor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblHashOrigen))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblHashDestino))
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMejor)
                .addGap(18, 18, 18)
                .addComponent(btnMascara)
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNAleatorios)
                    .addComponent(txtNAleatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNMejores)
                    .addComponent(txtNMejores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(txtLog))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMejorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMejorActionPerformed
        this.mg.intercambiarMejorIndividuo(
            Integer.valueOf(hashOrigen), Integer.valueOf(hashDestino));
    }//GEN-LAST:event_btnMejorActionPerformed

    private void btnMascaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMascaraActionPerformed
        this.mg.intercambiarMascara(Integer.valueOf(hashOrigen), 
            Integer.valueOf(hashDestino));
    }//GEN-LAST:event_btnMascaraActionPerformed

    private void btnNAleatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNAleatoriosActionPerformed
        int n = Integer.parseInt(this.txtNAleatorios.getText());
        this.mg.intercambiarNAleatorios(Integer.valueOf(hashOrigen), 
            Integer.valueOf(hashDestino), n);
    }//GEN-LAST:event_btnNAleatoriosActionPerformed

    private void btnNMejoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNMejoresActionPerformed
        int n = Integer.parseInt(this.txtNMejores.getText());
        this.mg.intercambiarNMejores(Integer.valueOf(hashOrigen), 
            Integer.valueOf(hashDestino), n);
    }//GEN-LAST:event_btnNMejoresActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Intercambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Intercambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Intercambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Intercambio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Intercambio dialog = new Intercambio(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMascara;
    private javax.swing.JButton btnMejor;
    private javax.swing.JButton btnNAleatorios;
    private javax.swing.JButton btnNMejores;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblHashDestino;
    private javax.swing.JLabel lblHashOrigen;
    private javax.swing.JLabel txtLog;
    private javax.swing.JTextField txtNAleatorios;
    private javax.swing.JTextField txtNMejores;
    // End of variables declaration//GEN-END:variables
}