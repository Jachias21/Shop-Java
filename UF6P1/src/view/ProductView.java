/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import static java.lang.Double.parseDouble;
import main.ActionListener;
import main.Shop;
import model.Amount;
import model.Product;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author jachias
 */
public class ProductView extends javax.swing.JFrame implements ActionListener, KeyListener {

    private int opcion;
    private Shop shop;

    /**
     * Creates new form AddView
     */
    public ProductView(Shop tienda, int option) {
        initComponents();
        opcion = option;
        shop = tienda;
        switch (option) {
            case 2:
                jTPrice.setVisible(false);
                jLPrice.setVisible(false);
                break;
            case 9:
                jLStock.setVisible(false);
                jTStock.setVisible(false);
                jTPrice.setVisible(false);
                jLPrice.setVisible(false);
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLName = new javax.swing.JLabel();
        jLStock = new javax.swing.JLabel();
        jLPrice = new javax.swing.JLabel();
        jTStock = new javax.swing.JTextField();
        jTPrice = new javax.swing.JTextField();
        jTName = new javax.swing.JTextField();
        jBCancel = new javax.swing.JButton();
        jBOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLName.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLName.setText("Nombre producto:");

        jLStock.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLStock.setText("Stock producto:");

        jLPrice.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLPrice.setText("Precio producto:");

        jTStock.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTStockActionPerformed(evt);
            }
        });

        jTPrice.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTPriceActionPerformed(evt);
            }
        });

        jTName.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNameActionPerformed(evt);
            }
        });

        jBCancel.setText("CANCEL");
        jBCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelActionPerformed(evt);
            }
        });

        jBOk.setText("OK");
        jBOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLStock)
                            .addComponent(jLPrice)
                            .addComponent(jLName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTStock, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTName, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jBOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCancel)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLName)
                    .addComponent(jTName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLStock)
                    .addComponent(jTStock, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPrice)
                    .addComponent(jTPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCancel)
                    .addComponent(jBOk))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNameActionPerformed

    private void jTPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTPriceActionPerformed

    }//GEN-LAST:event_jTPriceActionPerformed

    private void jBCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelActionPerformed
        //Cerrar ventana
        dispose();
    }//GEN-LAST:event_jBCancelActionPerformed

    private void jBOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBOkActionPerformed
        // TODO add your handling code here:
        switch (opcion) {
            case 1:
                addProduct(shop);
                break;
            case 2:
                addStock(shop);
                break;
            case 9:
                deleteProduct(shop);
                break;
        }
    }//GEN-LAST:event_jBOkActionPerformed

    private void jTStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTStockActionPerformed
    
    public void addProduct(Shop shopDao) {
        if (shop.isInventoryFull()) {
            JOptionPane.showMessageDialog(null, "Inventario lleno", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = jTName.getText();

        boolean productExists = shop.productExists(name);

        if (!productExists) {
            try {
                double wholesalerPrice = parseDouble(jTPrice.getText());
                int stock = Integer.parseInt(jTStock.getText());

                Product newProduct = new Product(name, new Amount(wholesalerPrice, "€"), true, stock);

                shop.inventory.add(newProduct);
                shop.numberProducts++;

                shopDao.addProduct(newProduct);

                JOptionPane.showMessageDialog(null, "El producto ha sido añadido y registrado en la base de datos", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce valores numéricos válidos para el precio y el stock", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al registrar el producto en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "El producto ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void addStock(Shop shopDao) {
        String name = jTName.getText();
        Product product = shop.findProduct(name);

        if (product != null) {
            try {
                int stock = Integer.parseInt(jTStock.getText());
                int newStock = product.getStock() + stock;

                product.setStock(newStock);

                // Actualizar el producto en la base de datos
                shopDao.updateProduct(product);

                JOptionPane.showMessageDialog(null, "La cantidad de stock ha sido añadida y actualizada en la base de datos", "Info", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido para el stock", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el producto en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if (shop.isInventoryFull()) {
            JOptionPane.showMessageDialog(null, "El inventario está lleno", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encuentra el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void deleteProduct(Shop shopDao) {
        String name = jTName.getText();

        // Encuentra el producto en la lista local
        Product product = shop.findProduct(name);

        if (product != null) {
            try {
                // Elimina el producto de la base de datos
                shopDao.deleteProduct(product.getName());

                // Elimina el producto de la lista de productos en la instancia de la tienda
                if (shop.inventory.remove(product)) {
                    JOptionPane.showMessageDialog(null, "Producto eliminado con éxito", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "El producto no se ha podido eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                // Manejo de errores de SQL
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto de la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encuentra producto con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancel;
    private javax.swing.JButton jBOk;
    private javax.swing.JLabel jLName;
    private javax.swing.JLabel jLPrice;
    private javax.swing.JLabel jLStock;
    private javax.swing.JTextField jTName;
    private javax.swing.JTextField jTPrice;
    private javax.swing.JTextField jTStock;
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
