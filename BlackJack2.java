package Yen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BlackJack2 {
	String[] suits = { "黑桃", "紅心", "方塊", "梅花" };
	String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	int myPoker, bookmaker, myA, BA;// 點數/A幾張
	int index, x;
	JButton bt1, bt2, bt3;// 下方按鈕
	int[] poker = new int[52];
	JFrame jwin, jlose;// 輸贏畫面
	JFrame jf = new JFrame("BlackJack");
	JPanel jpm, jps, jpc, jBookmaker, jmy, js, jm, jcenter;
	JTextArea js1, js2, js3, js4, js5, js6, jm1, jm2, jm3, jm4, jm5, jm6;// 要牌畫面

	public BlackJack2() {
		jf.setLayout(new BorderLayout());
		JPanel jp = new JPanel(new GridLayout(1, 3));

		// 下方按鈕
		bt1 = new JButton("開新局");
		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();

			}
		});
		bt2 = new JButton("要牌");
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				send();

			}
		});
		bt3 = new JButton("停牌");
		bt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendBookmaker();

			}
		});
		jp.add(bt1);
		jp.add(bt2);
		jp.add(bt3);

//		//莊家視窗
		jcenter = new JPanel(new GridLayout(2, 1));
		js = new JPanel(new GridLayout(6, 1));
		js1 = new JTextArea("莊家點數 : ");
		js.add(js1);
		jcenter.add(js);
//		//玩家視窗
		jm = new JPanel(new GridLayout(6, 1));
		jm1 = new JTextArea("玩家點數 : ");
		jm.add(jm1);
		jcenter.add(jm);

		newGame();
		// 主視窗
		jf.setLocation(300, 150);
		jf.add(jcenter, BorderLayout.CENTER);
		jf.add(jp, BorderLayout.SOUTH);
		jf.setSize(600, 400);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new BlackJack2();

	}

	// 開新局
	public void newGame() {
		myA = myPoker = bookmaker = BA = 0;
		index = 3;
		// 洗牌
		for (int i = 0; i < poker.length; i++)
			poker[i] = i;
		for (int i = poker.length - 1; i > 0; i--) {
			int rand = (int) (Math.random() * (i + 1));
			// poker[rand] <==> poker[i]
			int temp = poker[rand];
			poker[rand] = poker[i];
			poker[i] = temp;
		}
		for (int i : poker)
			System.out.print(poker[i] + " : ");
		System.out.println();

		// 給玩家發牌
		jm.removeAll();
		jm.repaint();
		jm2 = new JTextArea(suits[poker[0] / 13] + values[poker[0] % 13]);
		jm3 = new JTextArea(suits[poker[1] / 13] + values[poker[1] % 13]);
		for (int i = 0; i < 2; i++) {
			System.out.println(suits[poker[i] / 13] + values[poker[i] % 13]);
			if (poker[i] % 13 >= 10)
				poker[i] = 9;
			if (poker[i] % 13 == 0) {
				poker[i] = 9;
				myA++;
			}
			myPoker = myPoker + poker[i] % 13 + 1;
		}
		jm1.setText("玩家點數 :");
		jm1.append(String.valueOf(myPoker));// 顯示點數
		jm.add(jm1);
		jm.add(jm2);
		jm.add(jm3);
		jm.validate();
		System.out.println("玩家點數:" + myPoker);
		// 2張A贏了
		if (myA == 2)
			myWin();

		// 給莊家發牌
		js.removeAll();
		js.repaint();
		js2 = new JTextArea(suits[poker[2] / 13] + values[poker[2] % 13]);
		js3 = new JTextArea(suits[poker[3] / 13] + values[poker[3] % 13]);
		for (int i = 2; i < 4; i++) {
			System.out.println(suits[poker[i] / 13] + values[poker[i] % 13]);
			if (poker[i] % 13 >= 10)
				poker[i] = 9;
			if (poker[i] % 13 == 0) {
				poker[i] = 9;
				BA++;
			}
			bookmaker = bookmaker + poker[i] % 13 + 1;
		}
		js1.setText("莊家點數 :");
		js1.append(String.valueOf(bookmaker));
		js.add(js1);
		js.add(js2);
		js.add(js3);
		js.validate();
		System.out.println("莊家點數:" + bookmaker);
	}

	// 要牌
	public void send() {
		index++;
		System.out.println(suits[poker[index] / 13] + values[poker[index] % 13]);
		System.out.println(index);
		switch (index) {
		case 4:
			jm4 = new JTextArea(suits[poker[index] / 13] + values[poker[index] % 13]);
			jm.add(jm4);
			break;
		case 5:
			jm5 = new JTextArea(suits[poker[index] / 13] + values[poker[index] % 13]);
			jm.add(jm5);
			break;
		case 6:
			jm6 = new JTextArea(suits[poker[index] / 13] + values[poker[index] % 13]);
			jm.add(jm6);
		}
		// 發完牌點數判斷
		if (poker[index] % 13 >= 10)
			poker[index] = 9;
		if (poker[index] % 13 == 0) {
			poker[index] = 9;
			myA++;
		}
		myPoker = myPoker + poker[index] % 13 + 1;

		if (myA == 2)
			myLose();
		if (myPoker > 21) {
			if (myA > 0)
				myPoker -= 9;
			myA--;
			System.out.println("myA:" + myA);
			// System.out.println("玩家點數:"+myPoker);
		}
		if (myPoker == 21) {
			System.out.println("你21點");
			myWin();
		}
		if (myPoker > 21) {
			System.out.println("你爆了");
			myLose();
		}
		if (myPoker < 21)
			if (index == 6) {
				System.out.println("收集5張 贏了");
				myWin();
				System.out.println("收集5張 贏了:");
			}
		jm1.setText("玩家點數 :" + myPoker);
		jm.validate();
	}

	// 莊家要牌
	public void sendBookmaker() {
		index = 9;
		while (bookmaker < 17) {
			index++;
			System.out.println(suits[poker[index] / 13] + values[poker[index] % 13]);

			switch (index) {
			case 10:
				js4 = new JTextArea(suits[poker[index] / 13] + values[poker[index] % 13]);
				js.add(js4);
				break;
			case 11:
				js5 = new JTextArea(suits[poker[index] / 13] + values[poker[index] % 13]);
				js.add(js5);
				break;
			case 12:
				js6 = new JTextArea(suits[poker[index] / 13] + values[poker[index] % 13]);
				js.add(js6);
			}
			// 發完牌點數判斷
			if (poker[index] % 13 >= 10)
				poker[index] = 9;
			if (poker[index] % 13 == 0) {
				poker[index] = 9;
				BA++;
			}
			bookmaker = bookmaker + poker[index] % 13 + 1;

			if (myA == 2)
				myLose();
			if (bookmaker > 21) {
				if (myA > 0)
					bookmaker -= 9;
				myA--;
				System.out.println("BA:" + BA);
				// System.out.println("莊家點數:"+bookmaker);
			}
			System.out.println("莊家點數:" + bookmaker);
		}
		if (bookmaker <= 21) {
			if (bookmaker > myPoker) {
				if (bookmaker == 21)
					System.out.println("莊家21點");
				myLose();
			} else {
				myWin();
			}
		} else {
			System.out.println("莊家爆了");
			myWin();
		}

		js1.setText("玩家點數 :" + bookmaker);
		js.validate();

	}

	// 玩家贏畫面
	public void myWin() {
		System.out.println("你贏了:");
		jwin = new JFrame();
		jwin.setLocation(390, 250);
		JButton jwb = new JButton("開新局\n");
		jwb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jwin.dispose();
				newGame();
			}
		});
		JTextArea jwt = new JTextArea("你贏了\n");
		jwt.append("莊家點數 :" + bookmaker + "\n");
		jwt.append("你的點數 :" + myPoker + "\n");
		jwin.setLayout(new BorderLayout());
		jwin.add(jwb, BorderLayout.SOUTH);
		jwin.add(jwt, BorderLayout.CENTER);
		jwin.setVisible(true);
		jwin.setSize(300, 200);
		jwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 莊家贏畫面
	public void myLose() {
		System.out.println("莊家贏了:");
		jlose = new JFrame();
		jlose.setLocation(390, 250);
		JButton jwb = new JButton("開新局");
		jwb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jlose.dispose();
				newGame();
			}
		});

		JTextArea jwt = new JTextArea("莊家贏了\n");
		jwt.append("莊家點數 :" + bookmaker + "\n");
		jwt.append("你的點數 :" + myPoker + "\n");
		jlose.setLayout(new BorderLayout());
		jlose.add(jwb, BorderLayout.SOUTH);
		jlose.add(jwt, BorderLayout.CENTER);
		jlose.setVisible(true);
		jlose.setSize(300, 200);
		jlose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
