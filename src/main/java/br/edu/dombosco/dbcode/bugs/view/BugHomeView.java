package br.edu.dombosco.dbcode.bugs.view;

import br.edu.dombosco.dbcode.accessManagment.controller.UserController;
import br.edu.dombosco.dbcode.accessManagment.model.User;
import br.edu.dombosco.dbcode.bugs.controller.BugController;
import br.edu.dombosco.dbcode.bugs.repository.BugRepository;
import com.sun.mail.imap.protocol.UIDSet;

import javax.swing.*;

public class BugHomeView extends JPanel {

    private BugRegisterView bugRegisterView;
    private BugQueryView bugQueryView;
    private BugRelatoryView bugRelatoryView;
    private BugController bugController;
    private User user;

    public BugHomeView(BugController bugController, User user){
        this.bugController = bugController;
        this.user = user;

        // Cadastro de bugs
        JButton addBug = new JButton("Cadastro de Bug");
        addBug.addActionListener(e -> {
            this.bugRegisterView = new BugRegisterView(bugController);
            this.bugRegisterView.setVisible(true);
        });
        add(addBug);

        // Consulta de Bug
        JButton queryBug = new JButton("Consulta de Bug");
        queryBug.addActionListener(e -> {
            this.bugQueryView = new BugQueryView(bugController, user);
            this.bugQueryView.setVisible(true);
        });
        add(queryBug);

        // Relatório de Bugs
        JButton relBug = new JButton("Relatório de Bugs");
        relBug.addActionListener(e -> {
            this.bugRelatoryView = new BugRelatoryView(bugController);
            this.bugRelatoryView.setVisible(true);
        });
        add(relBug);
    }
}
