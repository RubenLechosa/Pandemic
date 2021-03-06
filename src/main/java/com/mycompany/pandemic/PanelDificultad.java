/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pandemic;

import com.mycompany.pandemic.Controlador.Partida;
import com.mycompany.pandemic.Modelo.Ciudad;
import com.mycompany.pandemic.Modelo.Enfermedad;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ruben
 */
public class PanelDificultad extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form PanelJugar
     */
    public PanelDificultad() {
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_normal.png")); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 520, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_dificil.png")); // NOI18N
        jButton3.setBorder(null);
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 520, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_facil.png")); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 520, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\paneles\\seleccdiff.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 21, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\pastel.png")); // NOI18N
        jLabel6.setToolTipText("");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\dificil.png")); // NOI18N
        jLabel8.setToolTipText("");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 190, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\marihuana.ong.png")); // NOI18N
        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\paneles\\panelDif.png")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1267, 139, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\paneles\\panelDif.png")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 139, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\paneles\\panelDif.png")); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 139, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_atras.png")); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 1830, 890));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            PanelJugar.partida = new Partida(0, 0);
        } catch (IOException ex) {
            Logger.getLogger(PanelDificultad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PanelJugar.ciudades = Partida.ciudadList;

        JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this);
            marco.remove(this);
            marco.add(new PanelJugar());
            marco.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_facil2.png"));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_facil.png"));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            PanelJugar.partida = new Partida(0, 1);
        } catch (IOException ex) {
            Logger.getLogger(PanelDificultad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PanelJugar.ciudades = Partida.ciudadList;

        JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this);
            marco.remove(this);
            marco.add(new PanelJugar());
            marco.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_normal.png"));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
       jButton1.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_normal2.png"));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
       jButton3.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_dificil2.png"));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_dificil.png"));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this);
            marco.remove(this);
            marco.add(new PanelNuevaPartida());
            marco.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            PanelJugar.partida = new Partida(0, 2);
        } catch (IOException ex) {
            Logger.getLogger(PanelDificultad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PanelJugar.ciudades = Partida.ciudadList;

        JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this);
            marco.remove(this);
            marco.add(new PanelJugar());
            marco.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        jButton4.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_atras2.png"));
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        jButton4.setIcon(new javax.swing.ImageIcon("src\\main\\java\\Assets\\botones\\button_atras.png"));
    }//GEN-LAST:event_jButton4MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
