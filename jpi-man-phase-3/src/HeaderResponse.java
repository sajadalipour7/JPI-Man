import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is a class for adding all the header elements in response
 * Also this class extends from JPanel
 */
public class HeaderResponse extends JPanel {
    //copy to clipboard button
    private JButton copyToClipBoard = new JButton("Copy to Clipboard");
    //panel for elements
    private JPanel elements = new JPanel();

    public HeaderResponse() {
        this.setLayout(new BorderLayout());
        elements.setLayout(new BoxLayout(elements, BoxLayout.PAGE_AXIS));
        copyToClipBoard.setFont(new Font("Arial", Font.BOLD, 18));
        copyToClipBoard.setBackground(new Color(245, 135, 255));
        copyToClipBoard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //MouseListener for hover effect for copy button
        copyToClipBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                copyToClipBoard.setBackground(new Color(254, 84, 231));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                copyToClipBoard.setBackground(new Color(245, 135, 255));
            }
        });
        this.add(elements, BorderLayout.NORTH);
        this.add(copyToClipBoard, BorderLayout.SOUTH);
        this.setBackground(new Color(143, 161, 255));
        elements.setBackground(new Color(143, 161, 255));
    }

    public JButton getCopyToClipBoard() {
        return copyToClipBoard;
    }

    public JPanel getElements() {
        return elements;
    }
}
