import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class STAGE_1 extends JFrame implements KeyListener{

    int WALK_COUNTER;
    JLabel JEDISAUR;
    ImageIcon SPRITE_MODEL,BACKGROUND;

    STAGE_1(){
        // FRAME
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setResizable(false);

        // BACKGROUND IMAGE
        BACKGROUND = new ImageIcon("src\\com\\codex\\learning\\states\\stage_1\\house.png"); // FILE PATH
        Image TEMP = BACKGROUND.getImage();
        Image TEMP_BACKGROUND = TEMP.getScaledInstance(1200,660,Image.SCALE_SMOOTH);
        BACKGROUND = new ImageIcon(TEMP_BACKGROUND);
        JLabel mainBackground = new JLabel(BACKGROUND,JLabel.CENTER);
        mainBackground.setIcon(BACKGROUND);
        mainBackground.setBounds(0,-17,1200,700);


        // JEDISAUR PLAYER
        JEDISAUR = new JLabel();
        JEDISAUR.setBounds(500, 300, 50, 50);
        // JEDISAUR.setIcon(SPRITE_MODEL); // FOR JEDISAUR SPRITE
        JEDISAUR.setBackground(Color.red);
        JEDISAUR.setOpaque(true);
        this.add(JEDISAUR);
        this.setVisible(true);
        this.add(mainBackground);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //WASD KEYS
        if(e.getKeyChar() == 'a' ||e.getKeyChar() == 'A' ||e.getKeyChar() == 'w' ||e.getKeyChar() == 'W' ||
                e.getKeyChar() == 's' ||e.getKeyChar() == 'S' ||e.getKeyChar() == 'd' ||e.getKeyChar() == 'D') {
            switch (e.getKeyChar()) {
                case 'a':
                case 'A':
                    JEDISAUR.setLocation(JEDISAUR.getX() - 10, JEDISAUR.getY());
                    if (WALK_COUNTER == 1) {
                        //JEDISAUR.setBackground(Color.blue); EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //label.setBackground(Color.green);   EXAMPLE FOR WALK ANIMATIONS
                    }

                    break;
                case 'w':
                case 'W':
                    JEDISAUR.setLocation(JEDISAUR.getX(), JEDISAUR.getY() - 10);
                    if (WALK_COUNTER == 1) {
                        //JEDISAUR.setBackground(Color.black);  EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //JEDISAUR.setBackground(Color.yellow); EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
                case 's':
                case 'S':
                    JEDISAUR.setLocation(JEDISAUR.getX(), JEDISAUR.getY() + 10);
                    if (WALK_COUNTER == 1) {
                        // JEDISAUR.setBackground(Color.white);  EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //JEDISAUR.setBackground(Color.orange); EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
                case 'd':
                case 'D':
                    JEDISAUR.setLocation(JEDISAUR.getX() + 10, JEDISAUR.getY());
                    if (WALK_COUNTER == 1) {
                        //  JEDISAUR.setBackground(Color.pink);  EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //  JEDISAUR.setBackground(Color.gray);  EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
            }
            WALK_COUNTER++;
            if (WALK_COUNTER > 2) {
                if (WALK_COUNTER == 1) {
                    WALK_COUNTER = 2;
                } else if (WALK_COUNTER == 2) {
                    WALK_COUNTER = 1;
                }
                WALK_COUNTER = 0;

            }
        }else{
            WALK_COUNTER = 1;

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // ARROW KEYS
        if(e.getKeyCode() == 37 ||e.getKeyCode() == 38 ||e.getKeyCode() == 39 ||e.getKeyCode() == 40 ) {
            switch (e.getKeyCode()) {
                case 37:
                    JEDISAUR.setLocation(JEDISAUR.getX() - 10, JEDISAUR.getY());
                    if (WALK_COUNTER == 1) {
                        //  JEDISAUR.setBackground(Color.pink); EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        // JEDISAUR.setBackground(Color.gray);  EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
                case 38:
                    JEDISAUR.setLocation(JEDISAUR.getX(), JEDISAUR.getY() - 10);
                    if (WALK_COUNTER == 1) {
                        //  JEDISAUR.setBackground(Color.pink);  EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //  JEDISAUR.setBackground(Color.gray);  EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
                case 39:
                    JEDISAUR.setLocation(JEDISAUR.getX() + 10, JEDISAUR.getY());
                    if (WALK_COUNTER == 1) {
                        //   JEDISAUR.setBackground(Color.pink);  EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //   JEDISAUR.setBackground(Color.gray);  EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
                case 40:
                    JEDISAUR.setLocation(JEDISAUR.getX(), JEDISAUR.getY() + 10);
                    if (WALK_COUNTER == 1) {
                        //   JEDISAUR.setBackground(Color.pink);  EXAMPLE FOR WALK ANIMATIONS
                    } else if (WALK_COUNTER == 2) {
                        //   JEDISAUR.setBackground(Color.gray);  EXAMPLE FOR WALK ANIMATIONS
                    }
                    break;
            }
            WALK_COUNTER++;
            if (WALK_COUNTER > 2) {
                if (WALK_COUNTER == 1) {
                    WALK_COUNTER = 2;
                } else if (WALK_COUNTER == 2) {
                    WALK_COUNTER = 1;
                }
                WALK_COUNTER = 0;
            }
        }else{
            WALK_COUNTER = 1;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public static void main(String[] args) {
        new STAGE_1();
    }
}
