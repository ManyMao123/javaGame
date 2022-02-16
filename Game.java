//package Practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game{

	public static void main(String[] args) throws IOException, InterruptedException {
		Game gm = new Game();
		gm.Story();
	}
	
	Scanner sc = new Scanner(System.in);
	//事件操控變數
	boolean event= true;
	boolean control = false;//XX
	String name;
	//口袋物品
	String []item = {"筆電", "螺絲起子", "Java SE-Java物件導向程式設計 課本《作者:吳冠宏》", "（空）"};
	int money = 0 ;
	//讀文字檔(欲讀取章節編號)
	public void Read(int paragraph) throws IOException, InterruptedException{
		FileReader fr = new FileReader("C:\\Users\\Tibame_T14\\Desktop\\All.txt");
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("C:\\Users\\Tibame_T14\\Desktop\\test.txt");
		FileReader fr2 = new FileReader("C:\\Users\\Tibame_T14\\Desktop\\test.txt");
		BufferedReader br2 = new BufferedReader(fr2);
		String line = "";
		String []p = new String[10];
		Arrays.fill(p, "");
		int i = 0;
		while((line=br.readLine())!=null) {
				if(line.equals("p")) {
					i++;
					continue;
				}
				p[i] += (line + "\n");
		}
		br.close();
		fr.close();
		fw.write(p[paragraph]);
		fw.flush();
		fw.close();
		while((line=br2.readLine())!=null) {
			System.out.println(line);
			TimeUnit.SECONDS.sleep(2);
		}
		br2.close();
		fr2.close();
	}
	
	//寫文字檔
	public void Write() throws IOException {
		FileWriter fw = new FileWriter("C:\\Users\\Tibame_T14\\Desktop\\test.txt");
		String write = sc.nextLine();
		fw.write(write);
		fw.flush();
		fw.close();
	}
	//完整劇情
	public void Story() throws IOException, InterruptedException {
		Read(0);
		name = setName(); //取ID
		System.out.println("鬍子大叔：「喂喂喂！別開玩笑了！少在那邊給我裝傻阿！" + name +"！」");
		Read(1);
		showBill();
		Read(2);
		event1();//事件一
		Read(4);
		System.out.println("（忽然間，拳頭從"+name+"的頭上打下去）");
		Read(5);
		event2();//事件二
	}
	
	//賭博
	public void gambling() {
		Gambling[] player = new Gambling[3];
		Gambling p1 = new Gambling("小石頭");
		Gambling p2 = new Gambling("小蝸");
		Gambling p3 = new Gambling("小莉莉");
		
		p1.start();
		p2.start();
		p3.start();
	}
	//帳單
	public void showBill() {
		System.out.println("┌──────────────┐\r\n"
						 + "│Δζαναιδτηεβεδτ│\r\n"
						 + "├────────┬─────┤\r\n"
						 + "│××× RοΠ │§1487│\r\n"
						 + "├────────┼─────┤\r\n"
						 + "│×× ζμισε│ §995│\r\n"
						 + "├────────┼─────┤\r\n"
						 + "│×××  ισε│   §0│\r\n"
						 + "├────────┼─────┤\r\n"
						 + "│Τοταξ   │§6451│\r\n"
						 + "╞════════╧═════╡\r\n"
						 + "└──────────────┘");
	}
	//事件一
 	public void event1() throws IOException, InterruptedException {
		do {
			System.out.print("〈查看物品欄，請按(I)〉");
			String take = getItem();
			switch(take) {
				case "1":
					event = false;
					break;
				case "2":
					System.out.println("鬍子大叔：(╬ﾟдﾟ)「別想拿這破爛東西敷衍我！！！」");
					break;
				case "3":
					if (item[Integer.parseInt(take)-1]!="（空）") {
						Read(3);
						giveItem(Integer.parseInt(take));
					}else {
						System.out.println("鬍子大叔：(╬ﾟдﾟ)「什麼都沒有阿！！！」");
					}
					break;
				case "4":
					System.out.println("鬍子大叔：(╬ﾟдﾟ)「什麼都沒有阿！！！」");
					break;
				default:
					System.out.println("〈口袋內沒有此選項〉");
					break;
			}
		}while(event);
	}
	//事件二
	public void event2() {
		do {
			String quest = sc.next();
			switch(quest.toLowerCase()) {
				case "q":
					callQuest();
					event = true;
					break;
				default:
					System.out.println("〈請按下Q鍵開啟任務佈告欄〉");
					break;
			}
		}while(event==false);
	}
	//遊戲ID設定
	public String setName() { 
		String myName, sure;
		boolean loop = true;
		do {
			myName = sc.next();
			if(myName.length()>0) {
				System.out.print("〈你確定要叫這個名字嗎(請輸入y/n)?〉");
				sure = sc.next();
				if(sure.equals("y")) {
					System.out.print("〈"+myName+"已經有人取了！");
					myName += "123";
					System.out.println("不如就叫"+myName+"吧！〉");
					System.out.println();
					loop=false;
				}else if (sure.equals("n")) {
					System.out.println("〈等你決定好，隨時再告訴我！〉");
				}else {
					System.out.println("〈什麼?真是個好名字啊！〉");
					myName = "乂Java神o"+myName+"乂";
					System.out.println("〈那麼就回到我們的冒險吧！"+myName+"〉");
					System.out.println();
					loop=false;
				}
			}else {
				System.out.println("好好取個名字吧！");
			}
		}while(loop);
		return myName;
	}

	//拿取物品
	public String getItem() {
		boolean loop = true;
		String take;
		do {
			take = sc.next();
			switch(take.toLowerCase()) {
				case "i":
					for(int i = 0 ; i < item.length; i++) {
						System.out.println((i+1)+". "+item[i]);
					}
					System.out.println("☆ 持有金額：§"+money);
					System.out.print("〈欲拿取的物品:〉");
					do {
						take = sc.next();
						switch(take) {
							default:
								System.out.println("請輸入物品編號1~4");
								break;
							case "1":
								System.out.println("（拿出了"+item[0]+"）");
								loop = false;
								break;
							case "2":
								System.out.println("（拿出了"+item[1]+"）");
								loop = false;
								break;
							case "3":
								System.out.println("（拿出了"+item[2]+"）");
								loop = false;
								break;
							case "4":
								System.out.println("（拿出了"+item[3]+"）");
								loop = false;
								break;
						}
					}while(loop);
					break;
				default:
					System.out.println("請按下I鍵開啟物品欄");
					break;
			}

		}while(loop);
		return take;
	}
	//給出物品
	public void giveItem(int itemNo) {
		System.out.println("〈已失去 "+ item[itemNo-1] + "〉");
		item[itemNo-1] = "（空）" ;
	}
		
	//工會任務選單
	public void callQuest() {
		boolean loop = true;
		System.out.println("╔═══════╦══════╗");
		System.out.println("║Θυεδτ01║ §220 ║");
		System.out.println("╠═══════╬══════╣");
		System.out.println("║Θυεδτ02║ §240 ║");
		System.out.println("╠═══════╬══════╣");
		System.out.println("║Θυεδτ03║ §350 ║");
		System.out.println("╠═══════╬══════╣");
		System.out.println("║Θυεδτ04║ §470 ║");
		System.out.println("╠═══════╬══════╣");
		System.out.println("║Θυεδτ05║ §990 ║");
		System.out.println("╠═══════╬══════╣");
		System.out.println("║Θυεδτ06║ §580 ║");
		System.out.println("╚═══════╩══════╝");
		System.out.println("我：（總之...先來看看有什麼任務吧！）");
		System.out.print("請輸入欲查看的任務編號(離開輸入0)：");
		do {
			String q = sc.next();
			switch(q) {
				case "1":
					System.out.println("開發中，敬請期待");
					System.out.println("輸入其他欲查看的任務編號(離開輸入0)：");
					break;
				case "2":
					System.out.println("開發中，敬請期待");
					System.out.println("還想再接取什麼任務呢(離開請輸入0)？");
					break;
				case "3":
					System.out.println("開發中，敬請期待");
					System.out.println("還想再接取什麼任務呢(離開請輸入0)？");
					break;
				case "4":
					System.out.println("開發中，敬請期待");
					System.out.println("還想再接取什麼任務呢(離開請輸入0)？");
					break;
				case "5":
					System.out.println("開發中，敬請期待");
					System.out.println("還想再接取什麼任務呢(離開請輸入0)？");
					break;
				case "6":
					System.out.println("開發中，敬請期待");
					System.out.println("還想再接取什麼任務呢(離開請輸入0)？");
					break;
				case "0":
					System.out.println("（故事未完待續...）");
					loop = false;
					break;
				default:
					System.out.println("輸入錯誤，請重新輸入！");
					break;
			}
			
		}while(loop);
	}


}
