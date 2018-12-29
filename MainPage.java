/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author REBEL
 */
public class MainPage extends javax.swing.JFrame {
    public Connection cn;
    public Statement st;
    /**
     * Creates new form MainPage
     */
    public MainPage() throws SQLException, ClassNotFoundException {
        initComponents();
        getConnected();
        Show_Products_In_JTable();
    }
    
    
    String ImgPath = null;
    int pos = 0;
    
public Connection getConnected() throws ClassNotFoundException 
{
    
    try{
            Class.forName("com.mysql.jdbc.Driver");
            cn= (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","");
            JOptionPane.showMessageDialog(null, "Connected!");
            return cn;
        }
    catch( SQLException e)
        {
           JOptionPane.showMessageDialog(null, "YourDBisNotConnected!");
        return null;      

}

}

 public boolean checkInputs()
    {
        if(
              txt_name.getText() == null
           || txt_price.getText() == null
           || txt_AddDate.getText() == null
          ){
            return false;
        }
        else{
            try{
                Float.parseFloat(txt_price.getText());
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
    }

   // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Product> getProductList()
    {
            ArrayList<Product> productList  = new ArrayList<Product>();
            String query = "SELECT * FROM product";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = cn.createStatement();
            rs = st.executeQuery(query);
            Product product;
            
            while(rs.next())
            {
                product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
                productList.add(product);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList; 
                
    }
    
   
 //2. tABLE SHO
 //      2 - Populate The JTable
    
    public void Show_Products_In_JTable()
    {
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
        }
    
    }
    
    // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_name.setText(getProductList().get(index).getName());
            txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
            txt_AddDate.setText(getProductList().get(index).getAddDate());
       // try {
           //Date addDate = null;
            //addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
            //txt_AddDate.setDate(txt_AddDate);
      //  } catch (ParseException ex) {
         //   Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
       // }
        
        imag.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
    }
    
 

 
 
 
 
public ImageIcon ResizeImage(String imagePath, byte[] pic)
{
    ImageIcon myImage = null;
    if(imagePath!=null)
    {
        myImage=new ImageIcon(imagePath);       
    }else{
        myImage=new ImageIcon(pic);
    }
    Image im = myImage.getImage();
    Image img2=im.getScaledInstance(imag.getWidth(),imag.getHeight(),Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(img2);
    return image;

}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        imag = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_Insert = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        txt_AddDate = new javax.swing.JTextField();
        img = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("NAme");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Date:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Price");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Image:");

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        imag.setBackground(new java.awt.Color(0, 255, 153));
        imag.setForeground(new java.awt.Color(255, 153, 153));
        imag.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        Btn_Insert.setText("Insert");
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        jButton3.setText("Next");

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("First");

        jButton7.setText("Previous");

        jButton8.setText("Last");

        img.setText("Choose Image");
        img.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imag, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(Btn_Insert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(imag, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(img))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Insert)
                    .addComponent(delete)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton3)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(356, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        // TODO add your handling code here:
        if(checkInputs() && ImgPath != null)
        {
            System.out.println("jsjj");
            try {
                 //Connection con = getConnection();
                PreparedStatement ps = cn.prepareStatement("INSERT INTO product (name,price,add_date,image)"
                        + "values(?,?,?,?) ");
                //ps.setString(1,"");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
               
               // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //String addDate = dateFormat.format(txt_AddDate.getDate());
                ps.setString(3, txt_AddDate.getText());
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                Show_Products_In_JTable();
               
                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Oneslj Or More Field Are Empty");
        }
       
        // only for test
        System.out.println("Name => "+txt_name.getText());
        System.out.println("Price => "+txt_price.getText());
        System.out.println("Image => "+ImgPath);
      
        
        
        
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void imgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgActionPerformed
        // TODO add your handling code here:
           JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
             System.out.println(path);
            imag.setIcon(ResizeImage(path, null));
            ImgPath = path;
        }
        else{
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_imgActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     
        if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
        
           
            // update without image
            if(ImgPath == null)
            {
                try {
                    UpdateQuery = "UPDATE product SET name = ?, price = ?"
                            + ", add_date = ? WHERE id = ?";
                    ps = cn.prepareStatement(UpdateQuery);
                   
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                   
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    //String addDate = dateFormat.format(txt_AddDate.getDate());
                   
                    ps.setString(3, txt_AddDate.getText());
                   
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                   
                    ps.executeUpdate();
                    //Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");
                   
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Product Updatedk,jhgfdvs");
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            // update With Image
            else{
                try{
                InputStream img = new FileInputStream(new File(ImgPath));
               
                 UpdateQuery = "UPDATE product SET name = ?, price = ?"
                            + ", add_date = ?, image = ? WHERE id = ?";
               
                  ps = cn.prepareStatement(UpdateQuery);
                   
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                   
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    //String addDate = dateFormat.format(txt_AddDate.getDate());
                   
                    ps.setString(3, txt_AddDate.getText());
                   
                    ps.setBlob(4, img);
                   
                    ps.setInt(5, Integer.parseInt(txt_id.getText()));
                   
                    ps.executeUpdate();
                    //Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");
               
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
       

        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if(!txt_id.getText().equals(""))
        {
            try {
                //Connection con = getConnection();
                PreparedStatement ps = cn.prepareStatement("DELETE FROM product WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                JOptionPane.showMessageDialog(null, id);
                ps.setInt(1, id);
                ps.executeUpdate();
                //Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Product Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
         
        }else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id To Delete");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
     
        int index = JTable_Products.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainPage().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton delete;
    private javax.swing.JLabel imag;
    private javax.swing.JButton img;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_AddDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables

    

    
}
