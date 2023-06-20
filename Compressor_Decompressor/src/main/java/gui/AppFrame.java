package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import compdecomp.Compressor;
import compdecomp.DeCompressor;

public class AppFrame extends JFrame implements ActionListener {
	
	JButton compressButton,decompressButton;
	
	public AppFrame() {
//		//setting frame properties;
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setSize(1000, 500);
//		this.getContentPane().setBackground(Color.CYAN);
//		this.setVisible(true);
//		
//		//compressed Button
//		compressButton=new JButton("compress");
//		compressButton.setBounds(0, 100, 200, 30);
//		compressButton.addActionListener(this);
//		//decompressed button
//		decompressButton=new JButton("decompress");
//		decompressButton.setBounds(20, 100, 200, 30);
//		decompressButton.addActionListener(this);
//		
//		//adding buttons to the frame
//		this.add(compressButton);
//		this.add(decompressButton);
		//-------------------------------------//
		 // Set the title of the frame
        setTitle("Compresser && Decopresser");

        // Set the size of the frame
        setSize(1000, 500);
        
        // Set the background color of the JFrame to cyan
        this.getContentPane().setBackground(Color.CYAN);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // Set the layout manager for the panel
        buttonPanel.setLayout(new GridBagLayout());

        // Create the first button
        compressButton = new RoundButton("Compress");
        compressButton.setPreferredSize(new Dimension(150, 50));
        compressButton.setFont(new Font("Arial", Font.BOLD, 16));
        compressButton.setBackground(Color.blue);
        compressButton.setForeground(Color.WHITE);
        compressButton.addActionListener(this);

        // Create the second button
        decompressButton = new RoundButton("Decompress");
        decompressButton.setPreferredSize(new Dimension(150, 50));
        decompressButton.setFont(new Font("Arial", Font.BOLD, 16));
        decompressButton.setBackground(Color.GREEN);
        decompressButton.setForeground(Color.WHITE);
        decompressButton.addActionListener(this);

        // Add the buttons to the panel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some spacing around the buttons
        buttonPanel.add(compressButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(decompressButton, gbc);

        // Set the layout manager for the frame's content pane
        getContentPane().setLayout(new BorderLayout());

        // Add the button panel to the center of the frame's content pane
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.CYAN);

        // Display the frame
        setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//compressed Button 
		if(e.getSource()==compressButton) {
			JFileChooser chooser=new JFileChooser();
			int response=chooser.showSaveDialog(null);
			if(response==chooser.APPROVE_OPTION) {
				File file=new File(chooser.getSelectedFile().getAbsolutePath());
				try {
					Compressor.method(file);
					 JOptionPane.showMessageDialog(null, "File is compressed successfully!\n Please check in this path "+file.getParent());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		}
		if(e.getSource()==decompressButton) {
			JFileChooser chooser=new JFileChooser();
			int response=chooser.showSaveDialog(null);
			if(response==chooser.APPROVE_OPTION) {
				File file=new File(chooser.getSelectedFile().getAbsolutePath());
				try {
					DeCompressor.method(file);
					 JOptionPane.showMessageDialog(null, "File is decompressed successfully!\n Please check in this path "+file.getParent());
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		}
	}

}

// Custom RoundButton class
class RoundButton extends JButton {
    public RoundButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 60, 60));
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 60, 60));
        g2.dispose();
    }
}
