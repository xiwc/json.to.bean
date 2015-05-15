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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JCheckBox ckComment;

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

				Config config = new Config();
				config.setAddValueAsComment(ckComment.isSelected());
				config.setDate(Utils.formatDate(new Date()));

				if (StringUtils.isEmpty(json)) {
					getTextAreaOutput().append("input json string is empty!");
					return;
				}

				try {

					MainHandler.handle(json, config, new ILogger() {

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

		JButton btnOpen = new JButton("Open Dir");
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

		ckComment = new JCheckBox("Add value as comment");
		ckComment.setSelected(true);
		toolBar.add(ckComment);

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
		textAreaInput
				.setText("{\r\n  \"servicePlan\": {\r\n    \"id\": \"7a696f98-b870-4a73-ad6a-283105e9c45c\",\r\n    \"planName\": \"eric_bronze_plan\",\r\n    \"planDisplayName\": \"eric_bronze_plan\",\r\n    \"description\": \"modified description on one plan\",\r\n    \"version\": \"2\",\r\n    \"enabled\": \"true\",\r\n    \"isBuiltIn\": \"false\",\r\n    \"planType\": \"Bronze\",\r\n    \"discoveryPhaseSpec\": {\r\n      \"enabled\": \"true/false\",\r\n      \"schedule\": {\r\n        \"scheduleExpression\": \"*:*:*:*:0:5:0\",\r\n        \"options\": {\r\n          \"rpoMonitor\": \"true/false\",\r\n          \"rpoValueInMinute\": \"1440\"\r\n        }\r\n      }\r\n    },\r\n    \"replicationPhaseSpec\": {\r\n      \"enabled\": \"true/false\",\r\n      \"options\": {\r\n        \"numberOfCopiesForExpire\": \"2\",\r\n        \"appConsistency\": \"VM/CRASH\",\r\n        \"isIncludeVMDisksForCopy\": \"true/false\",\r\n        \"maxSimultaneousVmSnapshots\": \"4\"\r\n      }\r\n    },\r\n    \"mountPhase\": {\r\n      \"enabled\": \"true\",\r\n      \"options\": {\r\n        \"site\": \"local/remote\",\r\n        \"accessMode\": \"physical/virtual/virtual with roll\",\r\n        \"clusterMount\": \"true/false\",\r\n        \"resignature\": \"true/false\",\r\n        \"vCenterUuid\": \"844BC0C8-2252-49E5-8663-26473257D993\",\r\n        \"mountHost\": \"10.110.43.145\",\r\n        \"accessType\": \"read-only/read-write\"\r\n      }\r\n    },\r\n    \"unmountPreviousCopyPhase\": {\r\n      \"enabled\": \"true\"\r\n    },\r\n    \"datasets\": [\r\n      {\r\n        \"id\": \"e07fb9f4-0c04-4d52-ac8f-f1ce4aab9da8\",\r\n        \"type\": \"datastore\",\r\n        \"options\": {\r\n          \"vCenterServer\": \"dd7096ff-1417-49af-acff-d9eb68312617\",\r\n          \"datastore\": \"ds:///vmfs/volumes/545aa3db-ffb16c39-38e1-d072dc5a4584/\"\r\n        },\r\n        \"override\": {\r\n          \"id\": \"e89ddb6a-d905-4c42-8bc1-b963ed359376\",\r\n          \"enabled\": \"true\",\r\n          \"name\": \"Mount Copy\",\r\n          \"options\": {\r\n            \"vCenterUuid\": \"844BC0C8-2252-49E5-8663-26473257D993\",\r\n            \"resignature\": \"true\",\r\n            \"clusterMount\": \"false\",\r\n            \"accessMode\": \"physical\",\r\n            \"accessType\": \"read-only\"\r\n          }\r\n        },\r\n        \"datastore\": {\r\n          \"id\": \"b7459be9-1852-404d-94fc-bfe35989b937\",\r\n          \"name\": \"Natasha_CG_CLR_2\",\r\n          \"datastoreVcenterId\": \"datastore-2162\",\r\n          \"datastoreUrl\": \"ds:///vmfs/volumes/545aa3db-ffb16c39-38e1-d072dc5a4584/\",\r\n          \"vcenterName\": \"10.102.7.17\",\r\n          \"datacenterName\": \"vsi_sh_dev_dc\"\r\n        }\r\n      },\r\n      {\r\n        \"id\": \"24936f58-64f6-4dc4-b747-3588660c952d\",\r\n        \"type\": \"datastore\",\r\n        \"options\": {\r\n          \"vCenterServer\": \"dd7096ff-1417-49af-acff-d9eb68312617\",\r\n          \"datastore\": \"ds:///vmfs/volumes/5451fed2-397602f4-9859-e8b748d5d700/\"\r\n        },\r\n        \"override\": null,\r\n        \"datastore\": {\r\n          \"id\": \"9a42a68d-a2e9-4beb-9b7d-bfed2d676a55\",\r\n          \"name\": \"Natasha_CG_NewRP_2\",\r\n          \"datastoreVcenterId\": \"datastore-2106\",\r\n          \"datastoreUrl\": \"ds:///vmfs/volumes/5451fed2-397602f4-9859-e8b748d5d700/\",\r\n          \"vcenterName\": \"10.102.7.17\",\r\n          \"datacenterName\": \"vsi_sh_dev_dc\"\r\n        }\r\n      }\r\n    ]\r\n  }\r\n}");
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

	public JCheckBox getCkComment() {
		return ckComment;
	}
}
