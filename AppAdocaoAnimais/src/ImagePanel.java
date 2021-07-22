import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 *
 * @author Sachi
 */
class ImagePainel extends JPanel {

  /**
	 * 
	 */
	private static final long serialVersionUID = 7441642089495026890L;
private Image img;
  public ImagePainel(String img) {
    this(new ImageIcon(img).getImage());
  }
  public ImagePainel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
	public JButton addButtonCenterUp(String s) {
		JButton button=new JButton(s);    
		button.setLocation(95,190);
        button.setSize(150, 50);

		add(button);
		return button;
	}
	public JButton addButtonCenterDown(String s) {
		JButton button=new JButton(s);    
		button.setLocation(95,275);
        button.setSize(150, 50);

		add(button);
		return button;
	}
  @Override
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }
}