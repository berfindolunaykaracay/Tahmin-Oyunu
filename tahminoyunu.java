/*
 OYUN KILAVUZU
-Yeni oyun butonuna basarak oyuna başlanır
-İlk olarak bilgisayar oyuncunun tuttuğu sayıyı tahmin etmeye çalışır
-Tahmin ettiği sayıya göre oyuncu ipucu verir (Rakamın bulunduğu basamak değeri doğruysa + kısmına,eğer o rakamı içeriyor ama basamak değeri yanlışsa - kısmına kaç adet sayı olduğu girilir)
-Eğer sayıyı doğru bilemediyse sıra oyuncuya geçer ve bilgisayarın tuttuğu sayıyı tahmin etmeye çalışır
-Tahmin edilen sayıya göre bilgisayar oyuncuya ipucu veriyor (Rakamın bulunduğu basamak değeri doğruysa + kısmına,eğer o rakamı içeriyor ama basamak değeri yanlışsa - kısmına kaç adet sayı olduğu verilir)
-İki taraftan biri doğru tahmin yapana kadar oyun devam eder 
*/

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

public class tahminoyunu {

	private JFrame frmTahminOyunu;
	private JTextField textField_pc;
	private JTextField textField_player;
	static ArrayList<String> possibpc = new ArrayList<String>();
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	String guess;
	int sira = 1;
	String target;
	String pguess;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel label_sira;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tahminoyunu window = new tahminoyunu();

					window.frmTahminOyunu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public tahminoyunu() {
		initialize();

	}

	public void pc() {
		try {

			int bulls = Integer.parseInt(textField.getText());
			int cows = Integer.parseInt(textField_1.getText());

			for (int j = 0; j < possibpc.size(); j++)
				if (!check_pc(possibpc.get(j), guess, cows, bulls)) {

					possibpc.remove(j);
					j--;
				}

			guess = possibpc.get((int) (Math.random() * possibpc.size()));

			textField_pc.setText(guess);

			if (possibpc.size() == 1)
				System.out.println("kazandım:::: " + possibpc.get(0));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void firsttime() {

		target = getNum();

		guess = possibpc.get((int) (Math.random() * possibpc.size()));

		textField_pc.setText(guess);

	}

	static void init_pc() {
		for (int a = 1; a <= 9; a++) {
			for (int b = 0; b <= 9; b++) {
				if (b == a)
					continue;
				for (int c = 0; c <= 9; c++) {
					if (c == b || c == a)
						continue;
					for (int d = 0; d <= 9; d++) {
						if (d == a || d == b || d == c)
							continue;
						String cnt = "" + a + b + c + d;
						possibpc.add(cnt);

					}
				}
			}
		}

	}

	static boolean check_pc(String ans, String guess, int cows, int bulls) {

		boolean val = true;
		for (int i = 0; i < guess.length(); i++) {
			int ind = ans.indexOf(guess.charAt(i));
			if (ind == i)
				bulls--;
			else if (ind >= 0)
				cows--;
		}

		if (cows == 0 && bulls == 0) {
			val = true;

		} else
			val = false;
		// return (cows == 0) && (bulls == 0);

		return val;

	}

////////

	public void player() {

		int check_player = check_player(target, pguess);

		textField_2.setText(String.valueOf(check_player / 10));
		textField_3.setText(String.valueOf(check_player % 10));

		if (check_player == 40) {
			System.out.println("CONGRATULATIONS! YOU WIN!");

		} else {

			pc();
		}

	}

	static String getNum() {
		ArrayList<String> possib = new ArrayList<String>();
		for (int a = 1; a <= 9; a++) {
			for (int b = 0; b <= 9; b++) {
				if (b == a)
					continue;
				for (int c = 0; c <= 9; c++) {
					if (c == b || c == a)
						continue;
					for (int d = 0; d <= 9; d++) {
						if (d == a || d == b || d == c)
							continue;
						String cnt = "" + a + b + c + d;
						possib.add(cnt);
					}
				}
			}
		}
		String guess = possib.get((int) (Math.random() * possib.size()));
		return guess;
	}

	static int check_player(String ans, String guess) {
		int bulls = 0, cows = 0;
		for (int i = 0; i < guess.length(); i++) {
			int ind = ans.indexOf(guess.charAt(i));
			if (ind == i)
				bulls++;
			else if (ind >= 0)
				cows++;
		}
		return bulls * 10 + cows;
	}

///////

	private void makeinactive() {

		textField.setEditable(false);
		textField_1.setEditable(false);
		btnNewButton_1.setEnabled(false);

	}

	private void makeactive() {

		textField.setEditable(true);
		textField_1.setEditable(true);

		textField.setText(null);
		textField_1.setText(null);
		btnNewButton_1.setEnabled(true);
	}

	private void pmakeinactive() {

		textField_2.setEnabled(false);
		textField_3.setEnabled(false);
		btnNewButton_2.setEnabled(false);
		textField_player.setEnabled(false);
	}

	private void pmakeactive() {

		textField_2.setEnabled(true);
		textField_3.setEnabled(true);
		btnNewButton_2.setEnabled(true);
		textField_player.setEnabled(true);
	}

	private void reset() {
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		textField_player.setText(null);
		textField_pc.setText(null);
		label_sira.setText(null);
	}

	private void initialize() {
		frmTahminOyunu = new JFrame();
		frmTahminOyunu.setType(Type.UTILITY);
		frmTahminOyunu.setResizable(false);
		frmTahminOyunu.setTitle("TAHMİN OYUNU");
		frmTahminOyunu.getContentPane().setBackground(Color.GRAY);
		frmTahminOyunu.getContentPane().setForeground(Color.BLACK);
		frmTahminOyunu.setBounds(100, 100, 466, 327);
		frmTahminOyunu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTahminOyunu.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("PC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(24, 65, 117, 16);
		frmTahminOyunu.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("OYUNCU");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(316, 65, 117, 16);
		frmTahminOyunu.getContentPane().add(lblNewLabel_1);

		textField_pc = new JTextField();
		textField_pc.setHorizontalAlignment(SwingConstants.CENTER);
		textField_pc.setBackground(Color.WHITE);
		textField_pc.setEnabled(false);
		textField_pc.setEditable(false);
		textField_pc.setBounds(24, 91, 117, 29);
		frmTahminOyunu.getContentPane().add(textField_pc);
		textField_pc.setColumns(10);

		textField_player = new JTextField();
		textField_player.setEnabled(false);
		textField_player.setHorizontalAlignment(SwingConstants.CENTER);
		textField_player.setBounds(316, 91, 117, 29);
		frmTahminOyunu.getContentPane().add(textField_player);
		textField_player.setColumns(10);

		label_sira = new JLabel("");
		label_sira.setForeground(Color.WHITE);
		label_sira.setHorizontalAlignment(SwingConstants.CENTER);
		label_sira.setBounds(145, 136, 164, 38);
		frmTahminOyunu.getContentPane().add(label_sira);

		btnNewButton = new JButton("Yeni Oyun");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				btnNewButton_1.setEnabled(true);
				pmakeinactive();
				label_sira.setText("Tahmin Sırası: \nPC");
				init_pc();

				firsttime();

			}
		});

		btnNewButton.setBounds(169, 40, 117, 29);
		frmTahminOyunu.getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("İpucu");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(null);
				textField_3.setText(null);
				textField_player.setEnabled(true);
				textField_player.setText(null);

				if (Integer.parseInt(textField.getText()) != 4) {

					if (sira == 1)
						sira = 2;
					else
						sira = 1;

					if (sira == 1) {

						if (possibpc.size() > 1 && Integer.parseInt(textField.getText()) != 4) {
							pmakeactive();

							sira = 2;
							pc();

						} else
							label_sira.setText("PC Kazandı!");

					} else {

						label_sira.setText("Tahmin Sırası: Oyuncu");
						makeinactive();
						pmakeactive();

					}

				} else {
					label_sira.setText("PC Kazandı!");

				}

			}

		});

		btnNewButton_1.setBounds(24, 201, 117, 29);
		frmTahminOyunu.getContentPane().add(btnNewButton_1);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(24, 165, 48, 26);
		frmTahminOyunu.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(93, 165, 48, 26);
		frmTahminOyunu.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setForeground(Color.WHITE);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBackground(Color.DARK_GRAY);
		textField_2.setEnabled(false);
		textField_2.setBounds(316, 165, 48, 26);
		frmTahminOyunu.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setForeground(Color.WHITE);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBackground(Color.DARK_GRAY);
		textField_3.setEnabled(false);
		textField_3.setBounds(385, 165, 48, 26);
		frmTahminOyunu.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		btnNewButton_2 = new JButton("Tahmin Et");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pguess = textField_player.getText();

				textField_player.setEnabled(false);

				player();

				if (Integer.parseInt(textField_2.getText()) != 4) {

					if (sira == 2)
						sira = 1;
					else
						sira = 2;
					if (sira == 2) {

						if (Integer.parseInt(textField_2.getText()) != 4) {
							makeactive();

							sira = 1;

						} else
							label_sira.setText("Oyuncu Kazandı!");

					} else {
						label_sira.setText("Tahmin Sırası: \nPC");
						pmakeinactive();
						makeactive();
					}
				} else {
					label_sira.setText("Oyuncu Kazandı!");
				}

			}
		});
		btnNewButton_2.setBounds(316, 201, 117, 29);
		frmTahminOyunu.getContentPane().add(btnNewButton_2);

		lblNewLabel_2 = new JLabel("+");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(24, 148, 48, 13);
		frmTahminOyunu.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(93, 149, 48, 13);
		frmTahminOyunu.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("+");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(316, 149, 48, 13);
		frmTahminOyunu.getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("-");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(385, 149, 48, 13);
		frmTahminOyunu.getContentPane().add(lblNewLabel_5);
	}
}
