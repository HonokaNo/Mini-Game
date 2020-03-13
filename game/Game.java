package game;

import java.io.IOException;

import game.ConsEsc;
import game.character.*;
import game.character.enemy.slime.*;
import game.character.player.Player;
import game.item.*;
import game.item.weapon.Weapon;
import game.item.weapon.axe.Axe;
import game.item.weapon.gloves.Glove;
import game.item.weapon.jsword.JSword;
import game.item.weapon.knife.Knife;
import game.item.weapon.longsword.LongSword;
import game.item.weapon.scythe.Scythe;
import game.item.weapon.spear.Spear;
import game.item.weapon.sword.Sword;
import game.magic.Magic;
import game.magic.MagicManager;
import game.magic.light.Heal;

import static game.Battle.*;
import static game.Console.*;

/**
 * ゲームの根幹となるクラスです。
 *
 * @version 0.0.1p
 */
public final class Game
{
	/**
	 * この環境で使用する矢印マーク
	 * 前に要望があったので設定
	 */
	public static final String ARROW = "->";

	/* 現在ログインしているプレイヤー */
	static Player loginedPlayer;

	/**
	 * このゲームのメイン関数 もちろんここから動作を開始します。
	 *
	 * @param args コマンドラインから渡された引数
	 */
	public static void main(String[] args)
	{
		write("---------------");
		write("  Mini Game++  ", ConsEsc.ESC_ULINE);
		write("   ver:0.0.1   ");
		putll("---------------");

		write("1.セーブデータを作る/開く");
		write("x.終了する");

		char c = input();

		if(c == '1'){
			if(!SaveData.read()){
				write("名前を入れてください!");
				loginedPlayer = new Player(read());
				putfn("まずはステータスを振ってみよう!");
				loginedPlayer.pointed();
			}
			putfn("%s lv.%dがログインしたよ!", loginedPlayer.getName(), loginedPlayer.getStatus().lv);
			write("");
			maintown();
		/* System.exitだとファイナライザが呼ばれない(らしい)ので */
		/* あえてreturnで終わらせてみる(意味があるかは知らない) */
		/* 結局exitを別の場所で呼び出したのでたぶん意味はない(笑) */
		}else if(c == 'x') return;
	}

	static int pos = 0;

	private static void maintown()
	{
		Heal heal = new Heal();
		if(MagicManager.addMagic(heal)){
			putfn("魔法実装記念! 新たな力を先行体験しよう!!!");
			putfn("%sは%sを覚えた!", loginedPlayer.getName(), heal.getName());
		}

		write("---初めの町 住宅街---");
		putll(loginedPlayer.getName() + "はどうしようか?");
		write("1.冒険に外へ行く");
		write("2.市場に散歩に行く");
		write("3.自分自身を見つめる");
		write("4.ステータスを振る");
		write("5.アイテムの確認/使用");
		write("6.セーブしてみる");
		write("x.ゲーム終了");
		char c = input();

		if(c == '1'){
			write("どちらへ行こう?");
			write("1.小さな森の中");
			write("2.迷い(そう)な竹林の中");
			write("x.戻る");
			c = input();

			if(c == '1'){
				write("--- スライムの森 ---");
				long rand = Random._rand();
				Mob[] enemy;
				if(rand <= 20) enemy = new Mob[]{new HopSlime(), new Slime(), new SlowSlime()};
				if(rand <= 30) enemy = new Mob[]{new HopSlime(), new Slime()};
				else if(rand <= 75) enemy = new Mob[]{new Slime()};
				else if(rand <= 95) enemy = new Mob[]{new SlowSlime(), new Slime()};
				else enemy = new Mob[]{new Slime(), new Slime()};
				battleInit(new Mob[]{loginedPlayer}, enemy);
			}else if(c == '2'){
				write("おや、あんたは誰だ?");
				write("まあいいか。ここに来るってことは、それなりな手練れだろ?");
				write("少し見て行ってや。");
				putfn("現在のお金:$%d", loginedPlayer.getMoney());
				JSword jsword = new JSword();
				putfn("1.%s $100", jsword.getName());
				putfn("%s", jsword.getDescription()[0]);
				write("x.帰る");
				c = input();

				String msg = "お金がねぇぞ";
				if(c == '1') buyItem(100, jsword, msg);
				else write("また来てくれな。");
			}else maintown();
		}else if(c == '2'){
			/* 市場に散歩に行く */
			/* アイテムの購入やイベントの発生など */
			write("どこに行こう?");
			write("1.武器屋さん");
			write("2.宿屋");
			write("x.街に戻る");
			c = input();

			if(c == '1'){
				write("いらっしゃいなのだ。");
				putfn("現在のお金:$%d", loginedPlayer.getMoney());
				Sword sword = new Sword();
				Knife knife = new Knife();
				LongSword longsword = new LongSword();
				Spear spear = new Spear();
				Axe axe = new Axe();
				Glove glove = new Glove();
				Scythe scythe = new Scythe();
				putfn("1.%s $6", sword.getName());
				putfn("%s", sword.getDescription()[0]);
				putfn("2.%s $6", knife.getName());
				putfn("%s", knife.getDescription()[0]);
				putfn("3.%s $6", longsword.getName());
				putfn("%s", longsword.getDescription()[0]);
				putfn("4.%s $6", spear.getName());
				putfn("%s", spear.getDescription()[0]);
				putfn("5.%s $6", axe.getName());
				putfn("%s", axe.getDescription()[0]);
				putfn("6.%s $6", glove.getName());
				putfn("%s", glove.getDescription()[0]);
				putfn("7.%s $6", scythe.getName());
				putfn("%s", scythe.getDescription()[0]);
				write("x.帰る");
				c = input();

				String msg = "お金が足りないみてぇだぞ";
				if(c == '1') buyItem(5, sword, msg);
				else if(c == '2') buyItem(6, knife, msg);
				else if(c == '3') buyItem(6, longsword, msg);
				else if(c == '4') buyItem(6, spear, msg);
				else if(c == '5') buyItem(6, axe, msg);
				else if(c == '6') buyItem(6, glove, msg);
				else if(c == '7') buyItem(6, scythe, msg);
				else write("また来てなのだ。");
			}else if(c == '2'){
				write("ここは宿屋です。");
				write("回復するには$8必要です。");
				write("回復しますか?");
				write("1.する");
				write("x.しない");
				c = input();

				if(c == '1'){
					if(loginedPlayer.getMoney() >= 8){
						write("体力が完全回復した!");
						loginedPlayer.maxheal();
						loginedPlayer.subMoney(8);
					}else write("無銭宿泊はだめですよ!");
				}else write("やっぱりやめよう。");
			}else if(c == 'x') putfn("町に戻ろう。%s", NEWLINE);
		}else if(c == '3'){
			Status st = loginedPlayer.getStatus();
			/* 自分自身を見つめる */
			/* ステータスの表示 */
			write("-------------------------");
			putfn("%s lv.%d%s", loginedPlayer.getName(), st.lv, st.lv == Player.MAX ? "(MAX)" : "");
			putfn("status point:%d", loginedPlayer.getPoint());
			write("-------------------------");
			putfn("HP:%d/%d", st.hp, st.mhp);
			putfn("AP:%d", st.ap);
			putfn("BP:%d", st.bp);
			putfn("SP:%d", st.sp);
			putfn("MAP:%d", st.map);
			putfn("MBP:%d", st.mbp);
			putfn("MP:%d/%d", st.mp, st.mmp);
			putfn("AVOID:%d%%", st.avoid);
			putfn("EXP:%d/%d", loginedPlayer.getNowExp(), loginedPlayer.getNextExp());
			putfn("money:$%8d", loginedPlayer.getMoney());
			write("-------------------------");
			putfn("weapon:%s", loginedPlayer.getWeapon() == null ? "つけてないよ" : loginedPlayer.getWeapon().getName());
			write("-------------------------");
			write("");
			write("1.魔法を見る");
			write("x.戻る");
			c = input();

			if(c == '1'){
				Magic[] magic = MagicManager.getMagics();
				if(magic != null){
					for(int l = 0; l < magic.length; l++){
						write("・" + magic[l].getName());
						for(int m = 0; m < magic[l].getDescription().length; m++){
							write(magic[l].getDescription()[m]);
						}
					}
				}else write("魔法を覚えていないようだ!");
			}else maintown();
		}else if(c == '4') loginedPlayer.pointed();
		else if(c == '5'){
			Items[] item = ItemManager.getItems();
			if(item != null){
				clear(useAnsi);
				int spos = 1;
				int end = (pos + 10) > item.length ? item.length : (pos + 10);
				for(int l = pos; l < end; l++, spos++){
					putfn("%d.%s x%d", spos, item[l].getItem().getName(), item[l].getNumber());
					putfn("%s", item[l].getItem().getDescription()[0]);
				}
				if(end != item.length) write("n.次へ");
				if(pos != 0) write("b.戻る");
				write("x.やめる");
				c = input();

				if(c >= '1' && c <= '9'){
					/* どれを押したかの番号を取得 0-8 */
					int input = (int)(c - '1');
					int ipos = pos + input;
					if(ipos >= item.length) write("そんなアイテムは存在しません。");
					else{
						putfn("%s x%d", item[ipos].getItem().getName(), item[ipos].getNumber());
						for(String s : item[ipos].getItem().getDescription()) putfn("%s", s);
						putfn("%s1.使う", NEWLINE);
						putfn("x.戻る");
						c = input();
						if(c == '1') item[ipos].getItem().use(loginedPlayer);

						maintown();
					}
				}else if(c == 'n') if(end != item.length) pos += 6;
				else if(c == 'b') if(pos != 0) pos -= 6;
				else write("アイテムを使うのをやめた。");
			}else write("アイテムを持っていなかった。");
		}else if(c == '6'){
			write("セーブします!");
			SaveData.write();
			write("セーブ完了!");
		}else if(c == 'x'){
			/* ゲーム終了 */
			SaveData.write();
			write("お疲れ様!ゲームを終了します!");
			write(loginedPlayer.getName() + "さんがログアウトしました!");
			System.exit(0);
		}else error("関係ない入力がされました。");

		write("");
		maintown();
	}

	/* ANSIエスケープシーケンスを使用するか */
	private static boolean useAnsi = false;

	/**
	 * ANSIエスケープを使用するか設定します。
	 *
	 * @param ansi ANSIエスケープを使用するならtrue
	 */
	public static void setAnsi(boolean ansi)
	{
		useAnsi = ansi;
	}

	/**
	 * ANSIエスケープを使用するかの設定を返します。
	 *
	 * @return ANSIエスケープを使用する場合はtrue
	 */
	public static boolean isAnsi()
	{
		return useAnsi;
	}

	/**
	 * 文字列をANSIエスケープを使用するかの設定に基づいて出力します。
	 * さらに改行を自動で行います。
	 *
	 * @param str 出力する文字列
	 * @param escapes 出力時に使用するエスケープ
	 *
	 */
	public static void write(String str, ConsEsc... escapes)
	{
		/* puteでは改行をしないので改行を一緒に出力する */
		if(useAnsi) pute(str + NEWLINE, escapes);
		else putl(str);
	}

	/**
	 * 一文字取得して返します。
	 * Console.getInputChar に例外処理を追加しただけです。
	 *
	 * @return 入力された文字 エラーの場合0が戻る(0x30でなく0x00)
	 *
	 * @see game.Console#getInputChar()
	 */
	public static char input()
	{
		try{
			char c = getInputChar();
			if(c == (char)0){
				error("エラーが出たのでもう一度入力してください...");
				return input();
			}
			return c;
		}catch(IOException ie){
			error("入出力でエラーが発生しました!");
			error("もう一度入力してください。");
			return input();
		}
	}

	/**
	 * 文字列を取得して返します。
	 * Console.getInputString に例外処理を追加しただけです。
	 *
	 * @return 入力された文字列 エラーの場合nullが戻る
	 *
	 * @see game.Console#getInputString()
	 */
	public static String read()
	{
		try{
			String s = getInputString();
			if(s.length() > 0) return s;
			else return read();
		}catch(IOException ie){
			error("入出力でエラーが発生しました!");
			error("もう一度入力してみてください。");
			error("繰り返される場合いったんゲームを終了しましょう。");
			return read();
		}
	}

	public static void buyItem(long money, Item item, String msg)
	{
		write("いくつ買おう?");
		long num = Long.parseLong(read());
		if(num > 0){
			if(loginedPlayer.getMoney() >= money * num){
				loginedPlayer.giveItem(item, num);
				loginedPlayer.subMoney(money);
				if(item instanceof Weapon){
					write("装備しちゃう?(y/n)");
					char c = input();

					if(c == 'y') item.use(loginedPlayer);
				}
			}else write(msg);
		}
	}

	public static Player getLoginedPlayer()
	{
		return loginedPlayer;
	}
}
