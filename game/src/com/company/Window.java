package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//frame.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));
public class Window { //graphical stuff
    JFrame frame;
    JButton play,quit,QG,Cod,BK,RT;

    JLabel label,L,L1,L2,L3,L4,i1,i2,i3,i4,WL,mi,CT;//title and Loss
    ArrayList<JLabel> lc = new ArrayList<JLabel>();//list of codex labels
    ArrayList<JLabel> ic = new ArrayList<JLabel>();//list of images
    public Window(int width,int height,String title,Game game){

         frame = new JFrame(title);
        menu(game);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
       frame.add(game);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// box is in center
        frame.setVisible(true);

    }
    public void restart(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void menu(Game game) {
        L = new JLabel("You deaded",SwingConstants.CENTER);
        L.setBounds(440, 100, 100, 20);
        frame.add(L);
        L.setVisible(false);
        label = new JLabel("Mafia Mania",SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setBounds(440, 100, 100, 100);
        frame.add(label);
        play = new JButton("Play");
        play.setBounds(440,200,140, 40);
        frame.add(play);
        Cod = new JButton("Codex");
        Cod.setBounds(440,250,140, 40);
        frame.add(Cod);
        quit = new JButton("Quit to Desktop");
        quit.setBounds(440,300,140, 40);
        frame.add(quit);

        //////////////////////////////////////////////////////the pause menu below
        QG = new JButton("Quit Game");
        QG.setBounds(440,250,140, 40);
        QG.setVisible(false);
        frame.add(QG);
        frame.validate();
        /////////////////////////////////////////////////////Codex below
        BK = new JButton("Back");
        BK.setBounds(0,0,140, 40);
        BK.setVisible(false);
        frame.add(BK);
        RT = new JButton("Reset");
        RT.setBounds(140,0,140, 40);
        RT.setVisible(false);
        frame.add(RT);
        CT = new JLabel("<html><p>Collect pages to unlock entries</p><html>");
        CT.setBounds(440, 0, 200, 50);
        frame.add(CT);
        CT.setVisible(false);
        L1 = new JLabel("<html><p>On January 16, 1919, Prohibition began in the United States with the 18th Amendment to the United States Constitution making it illegal to manufacture, transport, " +
                "or sell alcohol. Despite these bans, there was still a very high demand for it from the public. " +
                "This created an atmosphere that tolerated crime as a means to provide liquor to the public, " +
                "even among the police and city politicians. " +
                "Within the first three months proceeding the Eighteenth Amendment, a half million dollars in bonded whiskey was stolen from government warehouses. The profits that could be made from selling and distributing alcohol were worth the risk of punishment. </p><html>");
        L1.setBounds(12,200,250,300);
        L2 = new JLabel("<html><p> Lucky Luciano created The Commission in 1931, where the bosses of the most powerful families would " +
                "vote on important matters and solve disputes between families. This group ruled over the National Crime Syndicate and brought in an era of peace and prosperity for the American Mafia. Each family operated independently from the others and generally had exclusive territory it controlled. The Mafia thrived by following a " +
                "strict set of rules that called for an organized hierarchical structure and a code of silence that forbade its members from cooperating with the police (Omertà).</p><html>");
        L2.setBounds(264,200,250,300);
        L3 = new JLabel("<html><p>" +
                  "One of the most lucrative gains for the Mafia was through gas-tax fraud. They created schemes to keep the money that they owed in taxes after the sale of millions of dollars' worth of wholesale petroleum. " +
                "This allowed them to sell more gasoline at even lower prices. Michael Franzese, also known as the Yuppie Don, ran and organized a gas scandal and stole over $290 million in gasoline taxes by evading the Internal Revenue Service (IRS) and shutting down the " +
                "gas station before government officials could make him pay what he owed. Franzese was caught in 1985.</p><html>");
        L3.setBounds(516,200,250,300);
        L4 = new JLabel("<html><p>When the Racketeer Influenced and Corrupt Organizations Act (RICO Act) became federal law in 1970, it became a highly effective tool in prosecuting mobsters. It provides for extended criminal penalties for acts performed as part of an ongoing criminal " +
                "organization. Violation of the act is punishable by up to 20 years in prison per count, up to $25,000 in fines, and the violator must forfeit all properties attained while violating the RICO Act. The RICO Act has proven to be a very powerful weapon, because " +
                "it attacks the entire corrupt entity instead of individuals who can easily be replaced with other organized crime members. Between 1981 and 1992, 23 bosses from around the country were convicted under the law while between 1981 and 1988, " +
                "13 underbosses and 43 captains were convicted. Over 1,000 crime family figures were convicted by 1990. </p><html>");
        L4.setBounds(768,200,250,300);
        lc.add(L1);
        lc.add(L2);
        lc.add(L3);
        lc.add(L4);
        //label.setIcon(new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        i1 = new JLabel();
        i1.setIcon(new ImageIcon(new ImageIcon("res/prohibition.png").getImage().getScaledInstance(240, 140, Image.SCALE_DEFAULT)));
        i1.setBounds(12,50,240,140);
        i2 = new JLabel();
        i2.setIcon(new ImageIcon(new ImageIcon("res/com.png").getImage().getScaledInstance(240, 140, Image.SCALE_DEFAULT)));
        i2.setBounds(264,50,240,140);
        i3 = new JLabel();
        i3.setIcon(new ImageIcon(new ImageIcon("res/money.png").getImage().getScaledInstance(240, 140, Image.SCALE_DEFAULT)));
        i3.setBounds(516,50,240,140);
        i4 = new JLabel();
        i4.setIcon(new ImageIcon(new ImageIcon("res/rico.png").getImage().getScaledInstance(240, 140, Image.SCALE_DEFAULT)));
        i4.setBounds(768,50,240,140);
        ic.add(i1);
        ic.add(i2);
        ic.add(i3);
        ic.add(i4);
        //////////////////////////////////////////////////win menu below
        WL =new JLabel("You Win!!!");
        WL.setBounds(440,100,100, 30);
        WL.setVisible(false);
        frame.add(WL);
        frame.validate();

        for(int i =0;i<4;i++){
            lc.get(i).setVisible(false);
            frame.add(lc.get(i));
            ic.get(i).setVisible(false);
            frame.add(ic.get(i));
        }
        mi = new JLabel(new ImageIcon("res/menu.png"));
        mi.setBounds(0,0,1020,580);
        frame.add(mi);
        mi.setVisible(true);
        frame.validate();
        /////////////////////////////////////////////////
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                   game.start();
                   boolean done = false;

                   quit.setVisible(false);
                   play.setVisible(false);
                   label.setVisible(false);
                   Cod.setVisible(false);
                   mi.setVisible(false);
                   frame.validate();
            }
        });
        quit.addActionListener(new ActionListener() {// quit the program
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        QG.addActionListener(new ActionListener() {// quit the game
            @Override
            public void actionPerformed(ActionEvent arg0) {
                restart();
                try {
                    new Game();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                game.stop();

            }
        });
        Cod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                quit.setVisible(false);
                play.setVisible(false);
                label.setVisible(false);
                Cod.setVisible(false);
                mi.setVisible(false);
                for(int i =0;i<game.PG;i++){
                    lc.get(i).setVisible(true);
                    ic.get(i).setVisible(true);
                }
                CT.setVisible(true);
                BK.setVisible(true);
                RT.setVisible(true);
                frame.validate();
            }
        });
        BK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                quit.setVisible(true);
                play.setVisible(true);
                label.setVisible(true);
                Cod.setVisible(true);
                mi.setVisible(true);
                for(int i =0;i<4;i++){
                    lc.get(i).setVisible(false);
                    ic.get(i).setVisible(false);
                }
                CT.setVisible(false);
                BK.setVisible(false);
                RT.setVisible(false);
            }
        });
        RT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    List<String> lines = Arrays.asList("PF="+0);
                    Path file = Paths.get("res/text.txt");
                    Files.write(file, lines, StandardCharsets.UTF_8);
                    game.PG = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void death(Game game){
        L.setVisible(true);
        QG.setVisible(true);
        quit.setVisible(true);
        frame.validate();
    }
    public void win(Game game){
        WL.setVisible(true);
        QG.setVisible(true);
        quit.setVisible(true);
        frame.validate();
    }
    public void psmenu(Game game){

        QG.setVisible(!QG.isVisible());
        frame.validate();
    }

}

