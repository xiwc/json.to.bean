package com.emc.vsi.json2Bean;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang.StringUtils;

public class MainFrame extends JFrame {

	/** serialVersionUID long */
	private static final long serialVersionUID = -8469176333936546093L;
	private JPanel contentPane;
	private JTextArea textAreaOutput;
	private JTextArea textAreaInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Json To JavaBean Tool - Author : xiweicheng@yeah.net");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, BorderLayout.NORTH);

		JButton btnHandle = new JButton("Hadle");
		btnHandle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String json = getTextAreaInput().getText();

				if (StringUtils.isEmpty(json)) {
					getTextAreaOutput().append("input json string is empty!");
					return;
				}

				try {
					MainHandler.handle(json, new ILogger() {

						public void info(String msg, Object... params) {
							getTextAreaOutput().append(String.format("[INFO] - %s\r\n", String.format(msg, params)));
						}

						public void error(String msg, Object... params) {
							getTextAreaOutput().append(String.format("[ERROR] - %s\r\n", String.format(msg, params)));
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
					getTextAreaOutput().append(String.format("[EX] - %s\r\n", e1.getMessage()));
				}
			}
		});

		JButton btnPaste = new JButton("Paste");
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getTextAreaInput().setText(
							Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor)
									.toString());
					getTextAreaOutput().append("paste input json string.\r\n");
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedFlavorException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnPaste);
		toolBar.add(btnHandle);

		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] cmd = new String[5];

					String url = MainHandler.FILE_PATH;
					cmd[0] = "cmd";
					cmd[1] = "/c";
					cmd[2] = "start";
					cmd[3] = " ";
					cmd[4] = url;

					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnOpen);

		JButton btnClear = new JButton("Clear Output");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTextAreaOutput().setText("");
			}
		});
		toolBar.add(btnClear);

		JButton btnClearInput = new JButton("Clear Input");
		btnClearInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTextAreaInput().setText("");
			}
		});
		toolBar.add(btnClearInput);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		textAreaInput = new JTextArea();
		scrollPane_1.setViewportView(textAreaInput);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2, BorderLayout.CENTER);

		textAreaOutput = new JTextArea();
		scrollPane_2.setViewportView(textAreaOutput);
		splitPane.setDividerLocation(200);
	}

	public JTextArea getTextAreaOutput() {
		return textAreaOutput;
	}

	public JTextArea getTextAreaInput() {
		return textAreaInput;
	}
}
