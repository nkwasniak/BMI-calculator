package com.company;

import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.FormatFlagsConversionMismatchException;

public class BMI extends JFrame implements ActionListener {
    private ButtonGroup sex;
    private JRadioButton male,female;
    private JLabel ls, score; // label sex
    private JTextField weight, height;
    private JLabel lWeight, lHeight,scoreText;
    private JButton calc;
    private JMenu About,ScoreBMI;
    private JMenuItem author,aboutBMI,aboutCalc,forW,forM;
    private JMenuBar Menu;

    boolean EnableWeight = false;
    boolean EnableHeight = false;

    BMI(){
    setTitle("Kalkulator BMI");
    setSize(300,250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setResizable(false);
    setLocationRelativeTo(null);


    Menu = new JMenuBar();
    About = new JMenu("Pomoc");
    author = new JMenuItem("Autor");
    aboutBMI = new JMenuItem("Czym jest BMI?");
    aboutCalc = new JMenuItem("Kalkulator BMI");

    forW = new JMenuItem("Dla kobiet");
    forM = new JMenuItem("Dla mężczyzn");



    ScoreBMI = new JMenu("Wynik BMI");

    About.add(aboutCalc);
    About.add(aboutBMI);
    About.addSeparator();
    About.add(author);


    ScoreBMI.add(forW);
    ScoreBMI.add(forM);
    forW.addActionListener(this);
    forM.addActionListener(this);


    aboutCalc.addActionListener(this);
    aboutBMI.addActionListener(this);
    author.addActionListener(this);


    setJMenuBar(Menu);
    Menu.add(About);
    Menu.add(ScoreBMI);
    Menu.add(Box.createHorizontalGlue());



    sex = new ButtonGroup();
    male = new JRadioButton("mężczyzna",false);
    female = new JRadioButton("kobieta",true);
    female.setBounds(50,30,70,20);
    male.setBounds(135,30,90,20);
    sex.add(male);
    sex.add(female);


    ls = new JLabel("Wybierz płeć:");
    ls.setFont(new Font("SansSerif",Font.BOLD,13));
    ls.setBounds(90,5,100,20);
    add(ls);

    lWeight = new JLabel("Waga:            kg");
    lWeight.setBounds(30,60,90,20);
    add(lWeight);

    weight = new KTextField(3);
    weight.setBounds(70,60,30,23);

    JLabel showWarning;
    showWarning = new JLabel();
    showWarning.setBounds(109,80,60,20);
    showWarning.setFont(new Font("SansSerif",Font.PLAIN,9));
    showWarning.setForeground(Color.red);

    add(showWarning);
    add(weight);

    calc = new JButton("Oblicz!");
    calc.setBounds(85,100,100,20);
    calc.addActionListener(this);
    calc.setEnabled(false);
    add(calc);





    weight.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (weight.getText().length() < weight.getColumns()) {
                    showWarning.setText("");

                    if (!weight.getText().isEmpty() && !checkString(weight.getText())) {
                        showWarning.setText("Błędne dane!");
                        EnableWeight = false;
                    } else
                        EnableWeight = true;


                    if (!Character.isDigit(c) && ((c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) || (weight.getText().isEmpty()))) {
                        showWarning.setText("Błędne dane!");
                        EnableWeight = false;

                    }
                    if (EnableWeight && EnableHeight) calc.setEnabled(true);
                    else calc.setEnabled(false);
                }
            }
        });


        lHeight = new JLabel("Wzrost:            cm");
        lHeight.setBounds(140,60,100,20);
        add(lHeight);

        height = new KTextField(3);
        height.setBounds(188,60,30,23);


        add(height);

        height.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (height.getText().length() < height.getColumns()) {
                    showWarning.setText("");

                    if (!height.getText().isEmpty() && !checkString(height.getText())) {
                        showWarning.setText("Błędne dane!");
                        EnableHeight = false;
                    } else
                        EnableHeight = true;


                    if (!Character.isDigit(c) && ((c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) || (height.getText().isEmpty()))) {
                        showWarning.setText("Błędne dane!");
                        EnableHeight = false;

                    }
                    if (EnableWeight && EnableHeight) calc.setEnabled(true);
                    else calc.setEnabled(false);
                }
            }
        });




    height.setToolTipText("Wprowadz wagę w kg");
    weight.setToolTipText("Wprowdz wzorst w cm");

    score = new JLabel();
    score.setBounds(15,130,170,20);

    scoreText = new JLabel();
    scoreText.setBounds(15,140,300,50);
    scoreText.setFont(new Font("SansSerif",Font.BOLD,15));


    add(scoreText);
    add(male);
    add(female);
    add(score);

    }



    private boolean checkString (String a){
            for (int i = 0; i < a.length(); i++){
                if (a.charAt(i) < '0' || a.charAt(i) > '9' ) return false;
            }
            return true;
    }


    public void setScoreText(int number_case){
        switch (number_case){
            case 1:
                scoreText.setText("Wygłodzenie");
                scoreText.setForeground(new Color(51,51,255));
                break;
            case 2:
                scoreText.setText("Wychudzenie");
                scoreText.setForeground(new Color(51,0,120));
                break;
            case 3:
                scoreText.setText("Niedowaga");
                scoreText.setForeground(new Color(0,51,25));
                break;
            case 4:
                scoreText.setText("Wartość prawidłowa");
                scoreText.setForeground(new Color(0,204,0));
                break;
            case 5:
                scoreText.setText("Okres przed otyłością");
                scoreText.setForeground(new Color(255, 195, 67));
                break;
            case 6:
                scoreText.setText("I stopień otyłości");
                scoreText.setForeground(new Color(255,128,0));
                break;
            case 7:
                scoreText.setText("II stopień otyłości");
                scoreText.setForeground(Color.red);
                break;
            case 8:
                scoreText.setText("III stopień (otyłość kliniczna)");
                scoreText.setForeground(new Color(102,0,0));
                break;
        }
    }

    public void checkScore(double BMI){
        if (BMI <= 16) setScoreText(1);
            else if (BMI <= 16.9) setScoreText(2);
                else if (BMI <= 18.4) setScoreText(3);
                    else if (BMI <= 24.9) setScoreText(4);
                        else if (BMI <= 29.9) setScoreText(5);
                            else if (BMI <= 34.9) setScoreText(6);
                                else if (BMI <= 39.9) setScoreText(7);
                                    else setScoreText(8);
    }

    public static void main (String[] args){
        BMI calc = new BMI();
        calc.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object G = e.getSource();

        if (G == calc) {
                double W = Integer.parseInt(weight.getText());
                double H = Double.parseDouble(height.getText());
                H/=100;
                double BMI = W/(H*H);
                BMI = Math.round(BMI*100);
                BMI/=100;
                score.setText("Wskaźnik BMI wynosi: " + String.valueOf(BMI));

                if(male.isSelected()){
                   checkScore(BMI);
                }
                else{
                  checkScore(BMI-1);
                }
        } else {

            if(G == forW){
                JOptionPane.showMessageDialog(this," Wygłodzenie: < 15 \n Wychudzenie: 15.0-15.9 \n Niedowaga: 16.0-17.4 \n Wartość prawidłowa: 17.5-23.9 \n Okres przed otyłością: 24.0-28.9 \n I stopień otyłości: 29.0-33.9 \n II stopień otyłości: 34.0-38.9 \n III stopień otyłości: >39","Wynik dla kobiet",JOptionPane.INFORMATION_MESSAGE);
            }

            if (G == forM){
                JOptionPane.showMessageDialog(this," Wygłodzenie: < 16 \n Wychudzenie: 16.0-16.9 \n Niedowaga: 17.0-18.4 \n Wartość prawidłowa: 18.5-24.9 \n Okres przed otyłością: 25.0-29.9 \n I stopień otyłości: 30.0-34.9 \n II stopień otyłości: 35.0-39.9 \n III stopień otyłości: >40","Wynik dla mężczyzn",JOptionPane.INFORMATION_MESSAGE);
            }

            if (G == author)
                JOptionPane.showMessageDialog(this, " Wersja: 1.00 \n © Copyright by Nikodem Kwaśniak \n nkwasniak.github.io","Autor",JOptionPane.INFORMATION_MESSAGE);

            if (G == aboutCalc)
                JOptionPane.showMessageDialog(this, "Kalkulator BMI (Body Mass Index) daje każdemu możliwość szybkiego i wygodego obliczenia własnego wskaźnika masy ciała. \n BMI obliczamy dzieląc masę ciała (w kilogramach) przez wzrost do kwadratu (w metrach).  Wskaźnik ten wykorzystywany \n jest przede wszystkim do oceny ryzyka pojawienia się groźnych chorób: miażdżycy, choroby niedokrwiennej serca, \n udaru mózgu, czy nawet nowotworów. \n Większość tych chorób jest związana z otyłością i dlatego kalkulator BMI to tak przydatne narzędzie.","Kalkulator BMI",JOptionPane.INFORMATION_MESSAGE);

            if (G == aboutBMI)
                JOptionPane.showMessageDialog(this, "BMI jest jednym z ważnych wskaźniów określających nasz stan fizyczny, ale niestety nie wystarczającym. Bardzo ważnym  \n uzupełnieniem BMI jest wskaźnik ilości tłuszczu brzusznego - zbyt duży może oznaczać niebezpieczną otyłość brzuszną \n i to nawet przy prawidłowym BMI! Ponadto, paradoksalnie, badania naukowe wskazują, że osoby z lekką nadwagą zwykle \n są zdrowsze i żyją dłużej od osób z tzw. prawidłową wagą. Pojawiają się nawet głosy, że ustalony arbitralnie przez WHO próg nadwagi (25) jest zbyt niski.","Czym jest BMI?",JOptionPane.INFORMATION_MESSAGE);
        }
    }


    class KTextField extends JTextField {

        private int length = 0;

        // Creates a TextField with a fixed length of string input.
        public KTextField(int length) {
            super(new BMI.FixedLengthPlainDocument(length), "", length);
        }
    }

    class FixedLengthPlainDocument extends PlainDocument {

        private int maxlength;

        // This creates a Plain Document with a maximum length
        // called maxlength.
        FixedLengthPlainDocument(int maxlength) {
            this.maxlength = maxlength;
        }

        // This is the method used to insert a string to a Plain Document.
        public void insertString(int offset, String str, AttributeSet a) throws
                BadLocationException {

            // If the current length of the string
            // + the length of the string about to be entered
            // (through typing or copy and paste)
            // is less than the maximum length passed as an argument..
            // We call the Plain Document method insertString.
            // If it isn't, the string is not entered.

            if (!((getLength() + str.length()) > maxlength)) {
                super.insertString(offset, str, a);
            }
        }
    }


}
