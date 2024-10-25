package ensa.examgi3.DesktopClient;

import ensa.exam1.model.Emprunt;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminClient extends JFrame {

    private AdminService adminService;
    private JTextArea displayArea;
    private JTextField idField, userIdField, bookIdField;

    public AdminClient() {
        // Configuration de la fenêtre principale
        setTitle("Gestion des Emprunts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panneau de saisie
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID Emprunt:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("ID Utilisateur:"));
        userIdField = new JTextField();
        inputPanel.add(userIdField);
        inputPanel.add(new JLabel("ID Livre:"));
        bookIdField = new JTextField();
        inputPanel.add(bookIdField);
        add(inputPanel, BorderLayout.NORTH);

        // Zone d'affichage des emprunts
        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Panneau de boutons d'action
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Ajouter Emprunt");
        JButton updateButton = new JButton("Modifier Emprunt");
        JButton deleteButton = new JButton("Supprimer Emprunt");
        JButton viewButton = new JButton("Consulter Emprunts");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        addButton.addActionListener(e -> ajouterEmprunt());
        updateButton.addActionListener(e -> modifierEmprunt());
        deleteButton.addActionListener(e -> supprimerEmprunt());
        viewButton.addActionListener(e -> consulterEmprunts());

        // Connexion à l'EJB
        try {
            Context context = new InitialContext();
            adminService = (AdminService) context.lookup("java:global/Exam1/AdminService");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    private void ajouterEmprunt() {
        Emprunt emprunt = new Emprunt();
        emprunt.setUserId(Long.parseLong(userIdField.getText()));
        emprunt.setBookId(Long.parseLong(bookIdField.getText()));
        adminService.ajouterEmprunt(emprunt);
        displayArea.setText("Emprunt ajouté avec succès!\n");
    }

    private void modifierEmprunt() {
        Emprunt emprunt = new Emprunt();
        emprunt.setId(Long.parseLong(idField.getText()));
        emprunt.setUserId(Long.parseLong(userIdField.getText()));
        emprunt.setBookId(Long.parseLong(bookIdField.getText()));
        adminService.modifierEmprunt(emprunt);
        displayArea.setText("Emprunt modifié avec succès!\n");
    }

    private void supprimerEmprunt() {
        Long empruntId = Long.parseLong(idField.getText());
        adminService.supprimerEmprunt(empruntId);
        displayArea.setText("Emprunt supprimé avec succès!\n");
    }

    private void consulterEmprunts() {
        List<Emprunt> emprunts = adminService.consulterEmprunts();
        displayArea.setText("Liste des emprunts:\n");
        for (Emprunt e : emprunts) {
            displayArea.append("ID: " + e.getId() + ", Utilisateur: " + e.getUserId() + ", Livre: " + e.getBookId() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminClient client = new AdminClient();
            client.setVisible(true);
        });
    }
}

