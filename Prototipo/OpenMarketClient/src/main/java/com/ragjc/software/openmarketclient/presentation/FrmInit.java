/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package com.ragjc.software.openmarketclient.presentation;

import com.ragjc.software.openmarketclient.access.Factory;
import com.ragjc.software.openmarketclient.access.ICategoryRepository;
import com.ragjc.software.openmarketclient.access.IProductRepository;
import com.ragjc.software.openmarketclient.domain.service.CategoryService;
import com.ragjc.software.openmarketclient.domain.service.ProductService;
import javax.swing.JFrame;

/**
 *
 * @author RodAlejo
 */
public class FrmInit extends javax.swing.JFrame {

    /**
     * Creates new form FrmInit
     */
    
    private String mode = "anon";
    public FrmInit(String mode) {
        initComponents();
        this.mode  = mode;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(FrmInit.MAXIMIZED_BOTH);
        this.setTitle("OpenMarket - Compra y venta de productos en línea.");
        
        initializeMode();
    }
    
    public void initSearchProduct(ProductService productService){
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnuProduct = new javax.swing.JMenu();
        mnuCategory = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setBackground(new java.awt.Color(220, 230, 240));

        mnuProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ragjc/software/openmarketclient/icons/inventory.png"))); // NOI18N
        mnuProduct.setMnemonic('f');
        mnuProduct.setText("Producto");
        mnuProduct.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuProductMenuSelected(evt);
            }
        });
        mnuProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuProductMouseClicked(evt);
            }
        });
        menuBar.add(mnuProduct);

        mnuCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ragjc/software/openmarketclient/icons/category.png"))); // NOI18N
        mnuCategory.setMnemonic('e');
        mnuCategory.setText("Categoria");
        mnuCategory.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuCategoryMenuSelected(evt);
            }
        });
        mnuCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuCategoryMouseClicked(evt);
            }
        });
        menuBar.add(mnuCategory);

        mnuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ragjc/software/openmarketclient/icons/logout.png"))); // NOI18N
        mnuExit.setText("Salir");
        mnuExit.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                mnuExitMenuSelected(evt);
            }
        });
        mnuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuExitMouseClicked(evt);
            }
        });
        menuBar.add(mnuExit);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuProductMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuProductMenuSelected
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnuProductMenuSelected

    private void mnuCategoryMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuCategoryMenuSelected
      
    }//GEN-LAST:event_mnuCategoryMenuSelected

    private void mnuExitMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_mnuExitMenuSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuExitMenuSelected

    private void mnuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuExitMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_mnuExitMouseClicked

    private void mnuCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuCategoryMouseClicked
        System.out.println("Clicked");
        ICategoryRepository categoryRep = Factory.getInstance().getCategoryRepository("remote");
        
        CategoryService categoryService = new CategoryService(categoryRep);
        
        GUICategories categoryInstance = new GUICategories(this, categoryService);
        desktopPane.add(categoryInstance);
        categoryInstance.toFront();
        categoryInstance.setVisible(true);
    }//GEN-LAST:event_mnuCategoryMouseClicked

    private void mnuProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuProductMouseClicked
        IProductRepository repository = Factory.getInstance().getRepository("remote");
        ProductService productService = new ProductService(repository);
        
        showSearchProducts(productService);
        switch (mode){
            case "seller":
                showCRUDProduct(productService);
                return;
        }
        
    }//GEN-LAST:event_mnuProductMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuCategory;
    private javax.swing.JMenu mnuExit;
    private javax.swing.JMenu mnuProduct;
    // End of variables declaration//GEN-END:variables

    private void initializeMode() {
        switch (mode){
            case "anon":
                mnuCategory.setVisible(false);
                break;
            
        }
    }

    private void showSearchProducts(ProductService productService) {
        GUIProductsFind instance2 = new GUIProductsFind(false,productService, mode);
        desktopPane.add(instance2);
        productService.addObservador(instance2);
        instance2.toFront();
        instance2.setVisible(true);
        
    }
    
    private void showCRUDProduct(ProductService productService){
        GUIProducts instance = new GUIProducts(this,  productService);
        desktopPane.add(instance);
        instance.toFront();
        instance.setVisible(true);
    }

}
