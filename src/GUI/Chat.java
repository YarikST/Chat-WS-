package GUI;

import WS.Klient;
import WS.Massages;
import WS.MyClass.ConnectEvent;
import WS.MyClass.ConnectListener;
import WSKConfiguration.Configuration;
import WSKConfiguration.Person;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Chat extends JFrame {

    private String nik;

    private String nameAuthorizable;
    private String nameRegistori;
    private String nameChat;
    private CardLayout cardLayout;

    private JPanel panelAut, panelReg;
    private JTabbedPane paneChat;

    private ImageIcon iconAut,iconReg, iconChat,iconFrame;

    private JMenuBar menuBar;
    private JMenu menu = new JMenu("Menu");
    private JPopupMenu popupMenu;

    private Font font = new Font("Garamond", Font.BOLD | Font.ITALIC, 11), fontMassages = new Font("Garamond", Font.PLAIN | Font.TRUETYPE_FONT, 10);

    private Klient klient;
    private ExecutorService executorServiceRead;

    ButtonAstionFunctions buttonAstionConnect;
    ButtonAstionConstekst buttonAstionCopy;
    ButtonAstionConstekst buttonAstionPset;
    ButtonAstionFunctions buttonAstionInf;

    public Chat() throws HeadlessException {
        super("Chat");


        //icon

        nameAuthorizable = "Authorizable";
        nameRegistori = "Registori";
        nameChat = "Chat";

        cardLayout = new CardLayout();
        panelAut = new JPanel();
        panelReg = new JPanel();
        paneChat = new JTabbedPane();
        Container contentPane = getContentPane();
        contentPane.setLayout(cardLayout);
        contentPane.add(panelAut, nameAuthorizable);
        contentPane.add(panelReg, nameRegistori);
        contentPane.add(paneChat, nameChat);

        menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        executorServiceRead = Executors.newCachedThreadPool();

        klient = new Klient();

        menu();
        autGU();
        regGU();
        chatGU();
        cardLayout.show(contentPane,nameAuthorizable);
        setLocation(500, 250);
        setSize(300,160);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                klient.close();
            }
        });
    }

    private void autGU() {
        final JTextField loginField = fieldLogin();
        final JPasswordField passwordField =passwordField();
        JLabel labelLogin = new JLabel("Login"), labelPass = new JLabel("Password");
        JLabel labelNumberLogin = labelNumberLogin(), labelNumberPass = labelNumberPass();
        final JButton buttonAut = new JButton("Go"), buttonReg = new JButton("New Profile");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1, 2));
        panelButton.add(buttonAut);
        panelButton.add(buttonReg);

        panelAut.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Insets insets = constraints.insets;
        int l = insets.left;
        int r = insets.right;
        int t = insets.top;
        int b = insets.bottom;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panelAut.add(labelLogin, constraints);
        constraints.gridx = 1;
        insets.set(t,l+30,b,r);
        panelAut.add(loginField, constraints);
        insets.set(t,l,b,r);
        constraints.gridx = 2;
        panelAut.add(labelNumberLogin, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panelAut.add(labelPass, constraints);
        constraints.gridx = 1;
        insets.set(t,l+30,b,r);
        panelAut.add(passwordField, constraints);
        insets.set(t,l,b,r);
        constraints.gridx = 2;
        panelAut.add(labelNumberPass, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        insets.set(t+20,l,b,r);
        panelAut.add(panelButton, constraints);

        buttonAut.setEnabled(false);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration configuration = klient.configuration();

                boolean aut = configuration.aut(loginField.getText(), passChar(passwordField.getPassword()));
                if (aut) {
                    nik = loginField.getText();
                    klient.setLogin(nik);
                    cardLayout.show(getContentPane(), nameChat);
                    setSize(800, 340);
                } else {

                    loginField.setText("");
                    passwordField.setText("");

                    JOptionPane.showMessageDialog(null, "Eroor login end password", "authorizable", JOptionPane.WARNING_MESSAGE);

                }


            }
        };
        buttonAut.addActionListener(listener);
        passwordField.addActionListener(listener);

        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(getContentPane(),nameRegistori);
               setSize(320,180);
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!loginField.getText().equals("") && loginField.getText() != null) {
                    buttonAut.setEnabled(true);
                }
            }
        });

    }

    private void regGU() {
        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(-120);
        gridLayout.setVgap(5);
        panelReg.setLayout(gridLayout);

        JLabel labelNik = new JLabel("Nik"), labelStatus = new JLabel("Status"), labelPass = new JLabel("Password"),
                labelAge = new JLabel("Age");

        final JTextField fieldNik = fieldLogin(), fieldStatus = fieldLogin();
        final JPasswordField passwordField = passwordField();
        final JSpinner spinnerHour, spinnerMouth, spinnerDay;
        final JButton buttonReg = new JButton("Registori");

        final SpinnerNumberModel spinnerNumberModelDay = new SpinnerNumberModel(1, 1, 31, 1);
        final SpinnerListModel spinnerListModelMouth = new SpinnerListModel(new String[]{"Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
                "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"});
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.add(Calendar.YEAR,-17);
        Calendar calendarLefr = (Calendar) calendar.clone();
        calendarLefr.add(Calendar.YEAR, -4);
        Calendar calendarRegth = (Calendar) calendar.clone();
        calendarRegth.add(Calendar.YEAR,1);
        final SpinnerDateModel spinnerDateModelYear = new SpinnerDateModel(calendar.getTime(), calendarLefr.getTime(), calendarRegth.getTime(), Calendar.YEAR);

        spinnerHour = new JSpinner(spinnerDateModelYear);
        spinnerMouth = new JSpinner(spinnerListModelMouth);
        spinnerDay = new JSpinner(spinnerNumberModelDay);

        JPanel panelSpiner = new JPanel();
        panelSpiner.add(spinnerDay);
        panelSpiner.add(spinnerMouth);
        panelSpiner.add(spinnerHour);

        panelReg.add(labelNik);
        panelReg.add(fieldNik);
        panelReg.add(labelStatus);
        panelReg.add(fieldStatus);
        panelReg.add(labelPass);
        panelReg.add(passwordField);
        panelReg.add(labelAge);
        panelReg.add(panelSpiner);
        panelReg.add(buttonReg);

        panelReg.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.GREEN));

        spinnerHour.setEditor(new JSpinner.DefaultEditor(spinnerHour));
        spinnerMouth.setEditor(new JSpinner.ListEditor(spinnerMouth));
        spinnerDay.setEditor(new JSpinner.NumberEditor(spinnerDay));

        buttonReg.setEnabled(false);

        fieldStatus.setFont(font);
        fieldNik.setFont(font);
        passwordField.setFont(font);

        spinnerDay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                SpinnerDateModel dateModel = (SpinnerDateModel) spinnerHour.getModel();
                SpinnerNumberModel numberModel = (SpinnerNumberModel) spinnerDay.getModel();
                Date dateTime = dateModel.getDate();
                Calendar date = Calendar.getInstance();
                date.setTime(dateTime);
                date.set(Calendar.DAY_OF_MONTH, (Integer) numberModel.getNumber());
                dateModel.setValue(date.getTime());
            }
        });
        spinnerMouth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                SpinnerDateModel dateModel = (SpinnerDateModel) spinnerHour.getModel();
                SpinnerListModel listModel = (SpinnerListModel) spinnerMouth.getModel();
                Date dateTime = dateModel.getDate();
                Calendar date = Calendar.getInstance();
                date.setTime(dateTime);
                java.util.List<String> list = (List<String>) listModel.getList();
                int i = list.indexOf(listModel.getValue());
                date.set(Calendar.MONTH, i);
                dateModel.setValue(date.getTime());

            }
        });

        spinnerHour.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                buttonReg.setEnabled(true);
            }
        });

        buttonReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Configuration configuration = klient.configuration();

                Calendar instance = Calendar.getInstance();
                instance.setTime(spinnerDateModelYear.getDate());
                int year = instance.get(Calendar.YEAR);
                URL whatismyip = null;
                String ip = "";
                try {
                    whatismyip = new URL("http://checkip.amazonaws.com");
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            whatismyip.openStream()));
                    ip = in.readLine();
                    in.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Person person = new Person(fieldNik.getText(), fieldStatus.getText(), passwordField.getPassword(), (int) spinnerNumberModelDay.getNumber(), spinnerListModelMouth.getList().indexOf(spinnerListModelMouth.getValue()), year, ip);
                boolean add = configuration.add(person);
                if (add) {
                    nik = fieldNik.getText();
                    klient.setLogin(nik);
                    cardLayout.show(getContentPane(), nameChat);
                    setSize(800, 340);
                } else {
                    JOptionPane.showMessageDialog(null,"err","registrations",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

    private void chatGU() {
        klient.addConnectListener(new ConnectListener() {
            @Override
            public void connect(ConnectEvent connectEvent) {
                newConnect(connectEvent.getName(), connectEvent.getKlientKey());
            }
        });
    }

    private void newConnect(String nameChat, final Klient.KlientKey key) {
        final JTextPane textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        final JTextArea textField = new JTextArea(3,60);
        JButton button = new JButton("sumbit");
        JPanel panel = new JPanel();
        JPanel panelF = new JPanel();


        StyleConstants.setForeground(attributeSet, Color.GREEN);
        textPane.setFont(fontMassages);
        textPane.setCharacterAttributes(attributeSet,true);
        textPane.setPreferredSize(new Dimension(780,200));
        textPane.setMinimumSize(textPane.getPreferredSize());


        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setColumnHeaderView(new JLabel("connect:" + nameChat));
        scrollPane.setPreferredSize(textPane.getPreferredSize());

        scrollPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        textField.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        panel.add(scrollPane);
        panelF.add(new JScrollPane(textField));
        panelF.add(button);
        panel.add(panelF);

        button.setToolTipText("<html>Enter new ln <br> Ctr+Enter sumbit");

        textField.setLineWrap(true);

        final ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                key.write(textField.getText());
                textField.setText("");
            }
        };

        executorServiceRead.execute(new Runnable() {
            @Override
            public void run() {
                Document document = textPane.getDocument();
                try {
                    while (true) {
                        String read = key.read();
                        try {
                            document.insertString(read.length(), read, null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        button.addActionListener(actionListener);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                //null
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                //null
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (keyEvent.getModifiers() == KeyEvent.CTRL_MASK) {
                       actionListener.actionPerformed(null);
                    } else {
                        textField.append("\n");
                    }

                }
            }
        });

        buttonAstionCopy = new ButtonAstionConstekst(textField,"copy", KeyEvent.VK_C, KeyEvent.VK_C);
        buttonAstionPset = new ButtonAstionConstekst(textField,"pset", KeyEvent.VK_P, KeyEvent.VK_V);

        JPopupMenu popupMenu = new JPopupMenu("menu");
        popupMenu.add(buttonAstionConnect);
        popupMenu.add(buttonAstionInf);
        popupMenu.addSeparator();
        popupMenu.add(buttonAstionCopy);
        popupMenu.add(buttonAstionPset);

        paneChat.setComponentPopupMenu(popupMenu);
        paneChat.addTab(nameChat, panel);
        paneChat.setTabComponentAt(paneChat.getTabCount()-1,new JCoseButton());

    }

    private void menu() {
        JMenuItem menuItemConnect, menuItemCopy, menuItemPset, menuItemInf;

         buttonAstionConnect = new ButtonAstionFunctions("connect", KeyEvent.VK_T, KeyEvent.VK_D);
         buttonAstionInf = new ButtonAstionFunctions("informations", KeyEvent.VK_I, KeyEvent.VK_I);

        menuItemConnect = new JMenuItem(buttonAstionConnect);
        menuItemInf = new JMenuItem(buttonAstionInf);

        menu.add(menuItemConnect);
        menu.addSeparator();
        menu.add(menuItemInf);



    }

    private JTextField fieldLogin() {
        JTextField field = new JTextField(10);
        field.setFont(font);
        ((AbstractDocument) field.getDocument()).setDocumentFilter(documentFilterLogin(field));
        field.setSelectionColor(Color.RED);
        return field;
    }
    private JPasswordField passwordField() {
        final JPasswordField passwordField = new JPasswordField(10);
        passwordField.setFont(font);
        ((AbstractDocument) passwordField.getDocument()).setDocumentFilter(documentFilterPass(passwordField));
        passwordField.setForeground(Color.GREEN);
        passwordField.setSelectionColor(Color.RED);
        return passwordField;
    }

    private JLabel labelNumberLogin() {
        JLabel labelNumberLogin = new JLabel("A-z");
        Font fontLabel = new Font("Garamond",0,9);
        labelNumberLogin.setFont(fontLabel);
        return labelNumberLogin;
    }
    private JLabel labelNumberPass() {
        JLabel  labelNumberPass = new JLabel(labelNumberLogin().getText()+","+"0-9");
        Font fontLabel = new Font("Garamond",0,9);
        labelNumberPass.setFont(fontLabel);
        return labelNumberPass;
    }

    private ArrayList<Integer> passChar(char mas[]) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < mas.length; i++) {
            list.add((int)mas[i]);
        }
        return list;
    }

    private DocumentFilter documentFilterLogin(final JTextField loginField) {
       return  new DocumentFilter() {
            private int A = 65, Z = 90, a = 97, z = 122;
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                byte mas[] = string.getBytes();
                boolean boo = true;
                for (int i = 0; i < mas.length; i++) {
                    byte b = mas[i];
                    if (b < A || b > Z && b < a || b > z) {
                        boo = false;
                        break;
                    }

                }
                fb.insertString(offset, string, attr);
                if (!boo) {
                    loginField.selectAll();
                    loginField.getToolkit().beep();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                boolean boo = true;
                byte b = 0;
                try {
                  b = text.getBytes()[0];
                } catch (Throwable b2) {

                }
                if (b < A || b > Z && b < a || b > z) {
                    boo = false;
                }

                fb.replace(offset, length, text, attrs);

                if (!boo) {
                    loginField.select(offset,offset+1);
                    loginField.getToolkit().beep();

                }

            }

        };

    }
    private DocumentFilter documentFilterPass(final JPasswordField passwordField) {
      return   new DocumentFilter() {
            private int i = 48, j = 57;
            private int A = 65, Z = 90, a = 97, z = 122;

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                byte mas[] = string.getBytes();
                boolean boo = true;
                for (int i = 0; i < mas.length; i++) {
                    byte b = mas[i];
                    if (b < A &&b<i||b<A&&b>j || b > Z && b < a || b > z) {
                        boo = false;
                        break;
                    }

                }
                fb.insertString(offset, string, attr);
                if (!boo) {
                    passwordField.getToolkit().beep();
                    passwordField.selectAll();
                }

            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                boolean boo = true;
                byte b = 0;
                try {
                    b = text.getBytes()[0];
                } catch (Throwable throwable) {

                }
                if (b < A &&b<i||b<A&&b>j || b > Z && b < a || b > z) {
                    boo = false;
                }
                fb.replace(offset, length, text, attrs);
                if (!boo) {
                    passwordField.getToolkit().beep();
                    passwordField.select(offset,offset+1);
                }

            }

        };
    }

    private class ButtonAstionConstekst extends AbstractAction {
        private JTextComponent textComponent;

        public ButtonAstionConstekst(JTextComponent jTextComponent,String name,int m,int k) {
            super(name);
            textComponent = jTextComponent;
            putValue(Action.MNEMONIC_KEY, m);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(k, InputEvent.CTRL_MASK));
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String smd = actionEvent.getActionCommand();

            switch (smd) {

                case "copy":
                   textComponent.copy();
                    break;
                case "pset":
                    textComponent.paste();
                    break;

            }

        }
    }
    private class ButtonAstionFunctions extends AbstractAction {

        public ButtonAstionFunctions(String name,int m,int k) {
            super(name);
            putValue(Action.MNEMONIC_KEY, m);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(k, InputEvent.CTRL_MASK));
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String smd = actionEvent.getActionCommand();

            switch (smd) {
                case "connect":
                    String s = JOptionPane.showInputDialog(null, "nik connect", "Connect", JOptionPane.QUESTION_MESSAGE);
                    klient.getMassages().conenct(nik,s);
                    break;
                case "informations":
                    System.out.println("inf");
                    break;


            }

        }
    }

    private class JCoseButton extends JButton {
        private int w;
        private int h;
        int posX, posY;
        int i;
        public JCoseButton() {
            setForeground(Color.RED);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            w = h = 12;
            i = 4;

            Insets insets = getBorder().getBorderInsets(this);
            w = w-insets.right-i;
            h = h-insets.bottom-i;
            posX = getX()+insets.left+i;
            posY = getY() + insets.top+i;

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(w *2, h *2);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(new BasicStroke(i));
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            graphics2D.setRenderingHints(hints);
            if (!getModel().isRollover()) {
                graphics2D.setPaint(new GradientPaint(0, 0, Color.BLUE, posX + w, posY + h, Color.YELLOW));
            } else {
                graphics2D.setPaint(Color.RED);
            }

            graphics2D.drawLine(posX, posY, posX + w, posY + h);
            graphics2D.drawLine(posX, posY + h, posX + w, posY);
        }
    }

}
