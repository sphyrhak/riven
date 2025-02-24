
package com.raven.form;

import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import com.raven.event.EventColorChange;
import com.raven.theme.ThemeColorChange;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Sale_Form extends Form {
    DefaultTableModel modelo;

    public Sale_Form() {
        initComponents();
        mostrarTabla();
        
        //metodo para la barra de busqueda
    
    }
    private void filtrarProductos(String textoBusqueda) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    jTable1.setRowSorter(sorter);

    if (textoBusqueda.isEmpty()) {
        // Si el campo de búsqueda está vacío, muestra todas las filas
        sorter.setRowFilter(null);
    } else {
        // Aplica un filtro que ignore mayúsculas y minúsculas y busque coincidencias parciales en cualquier columna
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
    }
}
    

//---------------------------------------------------
    //METODO PARA TRAER LA BASE DE DATOS A NUESTRO JPANEL
    //---------------------------------------------------
    void mostrarTabla(){
    
       String[] titulos = {"Id_Ventas", "NombreApellido","Teléfono", "Documento", "Fecha", "Subtotal", "IGV", "Total"};
    modelo = new DefaultTableModel(null, titulos);
    jTable1.setModel(modelo);

    String sql = "SELECT ventas.Id_Ventas, ventas.Fecha, ventas.Subtotal, ventas.IGV, ventas.Total, " +
             "cliente.NombreApellido, cliente.Teléfono, cliente.Documento " +
             "FROM ventas " +
             "JOIN cliente ON ventas.Id_Cliente = cliente.Id_Cliente";

    try (Connection cn = ConnectionDB.conectar();
         Statement st = cn.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Object[] fila = new Object[9];
            fila[0] = rs.getInt("Id_Ventas");
            fila[1] = rs.getString("NombreApellido");
            fila[2] = rs.getInt("Teléfono");
            fila[3] = rs.getInt("Documento");
            fila[4] = rs.getString("Fecha");
            fila[5] = rs.getDouble("Subtotal");
            fila[6] = rs.getDouble("IGV");
            fila[7] = rs.getDouble("Total");
            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage());
        System.out.println("Error al mostrar los datos: " + e.getMessage());
    }
    //metodo para la barra de busqueda
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitleSale = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lbTexDNI = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setOpaque(false);

        lbTitleSale.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitleSale.setForeground(new java.awt.Color(255, 204, 0));
        lbTitleSale.setText("ADMINISTRAR VENTA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addComponent(lbTitleSale)
                .addContainerGap(381, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitleSale)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbTexDNI.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTexDNI.setForeground(new java.awt.Color(255, 204, 0));
        lbTexDNI.setText("Ingresar DNI del cliente");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTexDNI)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(lbTexDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        // Obtén el texto ingresado en el campo de búsqueda
    String textoBusqueda = txtSearch.getText().trim();

    // Filtra la tabla de productos según el texto de búsqueda
    filtrarProductos(textoBusqueda);
        
    }//GEN-LAST:event_txtSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbTexDNI;
    private javax.swing.JLabel lbTitleSale;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
