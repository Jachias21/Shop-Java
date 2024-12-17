/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import main.Shop;



/**
 *
 * @author jachias
 */
public class ShopView extends javax.swing.JFrame implements ActionListener, KeyListener {

    public Shop tienda;
    public int option = 0;
    
    /**
     * Creates new form ShopView
     * @throws SQLException 
     * @throws IOException 
     */
    public ShopView() throws SQLException, IOException {
    	
        initComponents();
        Shop shop = new Shop();
        tienda = shop;
    }
    
    public void openCashView() throws IOException, SQLException {
        Shop shop = new Shop();
        CashView cashView = new CashView(shop);
        cashView.setVisible(true);
    }
    
    public void openProductView() throws IOException, SQLException {
        Shop shop = new Shop();
        ProductView productView = new ProductView(shop, option);
        productView.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBAddProd = new javax.swing.JButton();
        jBCount = new javax.swing.JButton();
        jBAddStock = new javax.swing.JButton();
        jBDeleteProd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jBImportarInv1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBAddProd.setText("2. Añadir producto");
        jBAddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jBAddProdActionPerformed(evt);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jBCount.setText("1. Contar caja");
        jBCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jBCountActionPerformed(evt);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jBAddStock.setText("3. Añadir stock");
        jBAddStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jBAddStockActionPerformed(evt);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jBDeleteProd.setText("9. Eliminar producto");
        jBDeleteProd.setToolTipText("");
        jBDeleteProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jBDeleteProdActionPerformed(evt);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jLabel1.setText("Seleccione una opción:");

        jBImportarInv1.setText("10. Exportar archivo");
        jBImportarInv1.setToolTipText("");
        jBImportarInv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jBImportarInv1ActionPerformed(evt);
					JOptionPane.showMessageDialog(null, "Inventario exportado en la base de datos", "Info", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error al exportar inventario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
               
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jBCount, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBDeleteProd, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBAddStock, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBImportarInv1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCount, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAddStock, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBDeleteProd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBImportarInv1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBAddProdActionPerformed(java.awt.event.ActionEvent evt) throws IOException, SQLException {//GEN-FIRST:event_jBAddProdActionPerformed
         option = 1;
        openProductView();
    }//GEN-LAST:event_jBAddProdActionPerformed

    private void jBCountActionPerformed(java.awt.event.ActionEvent evt) throws IOException, SQLException {//GEN-FIRST:event_jBCountActionPerformed
        openCashView();
    }//GEN-LAST:event_jBCountActionPerformed

    private void jBAddStockActionPerformed(java.awt.event.ActionEvent evt) throws IOException, SQLException {//GEN-FIRST:event_jBAddStockActionPerformed
        // TODO add your handling code here:
        option = 2;
        openProductView();
    }//GEN-LAST:event_jBAddStockActionPerformed

    private void jBDeleteProdActionPerformed(java.awt.event.ActionEvent evt) throws IOException, SQLException {//GEN-FIRST:event_jBDeleteProdActionPerformed
        // TODO add your handling code here:
        option = 9;
        openProductView();
    }//GEN-LAST:event_jBDeleteProdActionPerformed

    private void jBImportarInv1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_jBImportarInv1ActionPerformed  
            // TODO add your handling code here:
            tienda.writeInventory();
      
           
    }//GEN-LAST:event_jBImportarInv1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAddProd;
    private javax.swing.JButton jBAddStock;
    private javax.swing.JButton jBCount;
    private javax.swing.JButton jBDeleteProd;
    private javax.swing.JButton jBImportarInv1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
