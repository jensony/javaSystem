package com.test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

class FileName implements FilenameFilter {
	String str = null;

	FileName(String s) {
		str = "." + s;
	}

	public boolean accept(File dir, String name) {
		return name.endsWith(str);
	}
}

public class TestArea extends JPanel implements ActionListener, ItemListener,
		Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8644817439426152872L;
	Choice list = null;
	JTextArea ������ʾ�� = null, ��Ϣ�� = null;
	JCheckBox box[];
	JButton �ύ�����, ��ȡ��һ��, �鿴����;
	ReadTestquestion ��ȡ���� = null;
	JLabel welcomeLabel = null;
	Thread countTime = null;
	long time = 0;
	JTextField timeShow = null;
	boolean �Ƿ�رռ�ʱ�� = false, �Ƿ���ͣ��ʱ = false;
	JButton ��ͣ�������ʱ = null;

	public TestArea() {
		list = new Choice();
		String ��ǰĿ¼ = System.getProperty("user.dir");
		File dir = new File(��ǰĿ¼);
		System.out.println(��ǰĿ¼);
		FileName fileTxt = new FileName("txt");
		String fileName[] = dir.list(fileTxt);
		for (int i = 0; i < fileName.length; i++) {
			list.add(fileName[i]);
		}

		������ʾ�� = new JTextArea(15, 12);
		������ʾ��.setLineWrap(true);
		������ʾ��.setWrapStyleWord(true);
		������ʾ��.setFont(new Font("TimesRoman", Font.PLAIN, 14));
		������ʾ��.setForeground(Color.blue);
		��Ϣ�� = new JTextArea(8, 8);
		��Ϣ��.setForeground(Color.blue);
		��Ϣ��.setLineWrap(true);
		��Ϣ��.setWrapStyleWord(true);

		countTime = new Thread(this);
		String s[] = { "A", "B", "C", "D" };
		box = new JCheckBox[4];
		for (int i = 0; i < 4; i++) {
			box[i] = new JCheckBox(s[i]);
		}
		��ͣ�������ʱ = new JButton("��ͣ��ʱ");
		��ͣ�������ʱ.addActionListener(this);
		�ύ����� = new JButton("�ύ�����");
		��ȡ��һ�� = new JButton("��ȡ��һ��");
		��ȡ��һ��.setForeground(Color.blue);
		�ύ�����.setForeground(Color.blue);
		�鿴���� = new JButton("�鿴����");
		�鿴����.setForeground(Color.blue);
		�ύ�����.setEnabled(false);
		�ύ�����.addActionListener(this);
		��ȡ��һ��.addActionListener(this);
		�鿴����.addActionListener(this);
		list.addItemListener(this);
		��ȡ���� = new ReadTestquestion();
		JPanel pAddbox = new JPanel();
		for (int i = 0; i < 4; i++) {
			pAddbox.add(box[i]);
		}
		Box boxH1 = Box.createVerticalBox(), boxH2 = Box.createVerticalBox(), baseBox = Box
				.createHorizontalBox();
		boxH1.add(new JLabel("ѡ�������ļ�"));
		boxH1.add(list);
		boxH1.add(new JScrollPane(��Ϣ��));
		boxH1.add(�鿴����);
		timeShow = new JTextField(20);
		timeShow.setHorizontalAlignment(SwingConstants.RIGHT);
		timeShow.setEditable(false);
		JPanel p1 = new JPanel();
		p1.add(new JLabel("ʣ��ʱ�䣺"));
		p1.add(timeShow);
		p1.add(��ͣ�������ʱ);
		boxH1.add(p1);
		boxH2.add(new JLabel("��������:"));
		boxH2.add(new JScrollPane(������ʾ��));
		JPanel p2 = new JPanel();
		p2.add(pAddbox);
		p2.add(�ύ�����);
		p2.add(��ȡ��һ��);
		boxH2.add(p2);
		baseBox.add(boxH1);
		baseBox.add(boxH2);
		setLayout(new BorderLayout());
		add(baseBox, BorderLayout.CENTER);
		welcomeLabel = new JLabel("��ӭ����", JLabel.CENTER);
		welcomeLabel.setFont(new Font("����", Font.PLAIN, 24));
		welcomeLabel.setForeground(Color.blue);
		add(welcomeLabel, BorderLayout.NORTH);

	}

	public void itemStateChanged(ItemEvent e) {
		timeShow.setText(null);
		�Ƿ�رռ�ʱ�� = false;
		�Ƿ���ͣ��ʱ = false;
		��ͣ�������ʱ.setText("��ͣ��ʱ");
		String name = (String) list.getSelectedItem();
		��ȡ����.setFilename(name);
		��ȡ����.set��ɿ���(false);
		time = ��ȡ����.getTime();
		if (countTime.isAlive()) {
			�Ƿ�رռ�ʱ�� = true;
			countTime.interrupt();
		}
		countTime = new Thread(this);

		��Ϣ��.setText(null);
		������ʾ��.setText(null);
		��ȡ��һ��.setText("��ȡ��һ��");
		�ύ�����.setEnabled(false);
		��ȡ��һ��.setEnabled(true);
		welcomeLabel.setText("��ӭ����,��ѡ�������:" + ��ȡ����.getFilename());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ��ȡ��һ��) {
			��ȡ��һ��.setText("��ȡ��һ��");
			�ύ�����.setEnabled(true);
			String contentTest = ��ȡ����.getTestContent();
			������ʾ��.setText(contentTest);
			��Ϣ��.setText(null);
			��ȡ��һ��.setEnabled(false);
			try {
				countTime.start();
			} catch (Exception event) {

			}
		}
		if (e.getSource() == �ύ�����) {
			��ȡ��һ��.setEnabled(true);
			�ύ�����.setEnabled(false);
			String answer = "?";
			for (int i = 0; i < 4; i++) {
				if (box[i].isSelected()) {
					answer = box[i].getText();
					box[i].setSelected(false);
					break;
				}
			}
			��ȡ����.setSelection(answer);
		}
		if (e.getSource() == �鿴����) {
			int score = ��ȡ����.getScore();
			String messages = ��ȡ����.getMessages();
			��Ϣ��.setText("����:" + score + "\n" + messages);
		}
		if (e.getSource() == ��ͣ�������ʱ) {
			if (�Ƿ���ͣ��ʱ == false) {
				��ͣ�������ʱ.setText("������ʱ");
				�Ƿ���ͣ��ʱ = true;
			} else if (�Ƿ���ͣ��ʱ == true) {
				��ͣ�������ʱ.setText("��ͣ��ʱ");
				�Ƿ���ͣ��ʱ = false;
				countTime.interrupt();
			}
		}
	}

	public synchronized void run() {
		while (true) {
			if (time <= 0) {
				�Ƿ�رռ�ʱ�� = true;
				countTime.interrupt();
				�ύ�����.setEnabled(false);
				��ȡ��һ��.setEnabled(false);
				timeShow.setText("��ʱ��,���Խ���");
			} else if (��ȡ����.get��ɿ���()) {
				�Ƿ�رռ�ʱ�� = true;
				timeShow.setText("����Ч��:����*ʣ��ʱ��(��)=" + 1.0 * ��ȡ����.getScore()
						* (time / 1000));
				countTime.interrupt();
				�ύ�����.setEnabled(false);
				��ȡ��һ��.setEnabled(false);

			} else if (time >= 1) {
				time = time - 1000;
				long leftTime = time / 1000;
				long leftHour = leftTime / 3600;
				long leftMinute = (leftTime - leftHour * 3600) / 60;
				long leftSecond = leftTime % 60;
				timeShow.setText("" + leftHour + "Сʱ" + leftMinute + "��"
						+ leftSecond + "��");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ee) {
				if (�Ƿ�رռ�ʱ�� == true)
					return;
			}
			while (�Ƿ���ͣ��ʱ == true) {
				try {
					wait();
				} catch (InterruptedException ee) {
					if (�Ƿ���ͣ��ʱ == false) {
						notifyAll();
					}
				}
			}
		}
	}

}
