import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends JFrame implements ActionListener {
    JButton jButton1;
    JTextField jText1, jText2;
    JLabel lbl, lbl1;

    Timer() {

        jText1 = new JTextField("0");
        jText1.setBounds(90, 60, 40, 40);
        add(jText1);

        jText2 = new JTextField("0");
        jText2.setBounds(150, 60, 40, 40);
        add(jText2);

        lbl = new JLabel("Time:  ");
        lbl.setBounds(90, 105, 150, 30);
        add(lbl);

        lbl1 = new JLabel(":");
        lbl1.setBounds(138, 60, 40, 40);
        add(lbl1);

        jButton1 = new JButton("Start");
        jButton1.setBounds(90, 150, 100, 30);
        add(jButton1);
        jButton1.addActionListener(this);

        setLayout(null);
        setSize(300, 300);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        final int m = Integer.parseInt(jText1.getText()) + (Integer.parseInt(jText2.getText())) / 60;
        final int s = Integer.parseInt(jText2.getText()) % 60;

        if (e.getSource().equals(jButton1)) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    jButton1.setEnabled(false);
                    int min = m;
                    int sec = s;
                    while ((min != 0 || sec != 0)) {
                        StringBuilder str = new StringBuilder("Time:  ")
                                .append(min < 10 ? "0" + min : min)
                                .append("m:")
                                .append(sec < 10 ? "0" + sec : sec)
                                .append("s");
                        lbl.setText(str.toString());

                        if (sec != 0) --sec;
                        else {
                            --min;
                            sec = 59;
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                    lbl.setText("Time:  00m:00s");
                    jButton1.setEnabled(true);
                }
            });

            t.start();

        }
    }

    public static void main(String args[]) {
        Timer t = new Timer();
    }

}
