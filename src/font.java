import java.awt.*;
import javax.swing.*;
public class font {


	    public static void main(String[] args) {
	        SwingUtilities.invokeLater( new Runnable() {
	            public void run() {
	                GraphicsEnvironment ge = GraphicsEnvironment.
	                    getLocalGraphicsEnvironment();
	                String[] fonts = ge.getAvailableFontFamilyNames();
	                JComboBox fontChooser = new JComboBox(fonts);
	                fontChooser.setRenderer(new FontCellRenderer());
	                JOptionPane.showMessageDialog(null, fontChooser);
	            }
	        });
	    }
	}

	class FontCellRenderer extends DefaultListCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(
	        JList list,
	        Object value,
	        int index,
	        boolean isSelected,
	        boolean cellHasFocus) {
	        JLabel label = (JLabel)super.getListCellRendererComponent(
	            list,value,index,isSelected,cellHasFocus);
	        Font font = new Font((String)value, Font.PLAIN, 20);
	        label.setFont(font);
	        return label;
	    }
	}
