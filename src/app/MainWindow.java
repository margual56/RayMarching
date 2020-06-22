package app;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
    private Canvas paintPanel;                 
    private javax.swing.JPanel PPaint;

	public MainWindow() {
		initComponents();

		this.setSize(Canvas.WIDTH, Canvas.HEIGHT);
		paintPanel = (Canvas) PPaint;
		paintPanel.init();
	}

	private void initComponents() {
        PPaint = new Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout());

        javax.swing.GroupLayout PPaintLayout = new javax.swing.GroupLayout(PPaint);
        PPaint.setLayout(PPaintLayout);
        PPaintLayout.setHorizontalGroup(
            PPaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PPaintLayout.setVerticalGroup(
            PPaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        getContentPane().add(PPaint);

        pack();
	}// </editor-fold>

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow().setVisible(true);
			}
		});
	}

}
