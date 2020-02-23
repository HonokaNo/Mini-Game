package game;

import java.io.IOException;

import game.ConsEsc;
import game.character.Mob;
import game.character.Status;
import game.character.player.Player;

import static game.Battle.*;
import static game.Console.*;

/**
 * ゲームの根幹となるクラスです。
 *
 * @version 0.0.1p
 */
public final class Game
{
	/* 現在ログインしているプレイヤー */
	private static Player loginedPlayer;

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
			write("名前を入れてください!");
			loginedPlayer = new Player(read());
			putfn("%s lv.%dがログインしたよ!" + NEWLINE + NEWLINE, loginedPlayer.getName(), loginedPlayer.getStatus().lv);
			maintown();
		/* System.exitだとファイナライザが呼ばれない(らしい)ので */
		/* あえてreturnで終わらせてみる(意味があるかは知らない) */
		}else if(c == 'x') return;
	}

	private static void maintown()
	{
		write("---初めの町 住宅街---");
		putll(loginedPlayer.getName() + "はどうしようか?");
		write("1.冒険に外へ行く");
		write("2.市場に散歩に行く");
		write("3.自分自身を見つめる");
		write("x.ゲーム終了");
		char c = input();

		if(c == '1'){
//			write("今行くのはさすがに危険すぎるからやめておこう。");
			battleInit(new Mob[]{loginedPlayer}, new Mob[]{new game.character.enemy.slime.Slime()});
		}else if(c == '2'){
			/* 市場に散歩に行く */
			/* アイテムの購入やイベントの発生など */
			write("どこに行こう?");
			write("1.武器屋さん");
			write("x.街に戻る");
			c = input();

			if(c == '1'){
			}else if(c == 'x') write("町に戻ろう。");
		}else if(c == '3'){
			Status st = loginedPlayer.getStatus();
			/* 自分自身を見つめる */
			/* ステータスの表示 */
			write("-------------------------");
			putfn("%s lv.%d", loginedPlayer.getName(), st.lv);
			write("-------------------------");
			putfn("HP:%d/%d", st.hp, st.mhp);
			putfn("AP:%d", st.ap);
			putfn("BP:%d", st.bp);
			putfn("SP:%d", st.sp);
			putfn("MAP:%d", st.map);
			putfn("MBP:%d", st.mbp);
			putfn("MP:%d/%d", st.mp, st.mmp);
			putfn("money:$%8d", loginedPlayer.getMoney());
			write("-------------------------");
			write("");
			/* もし武器をつけてないなら"つけてないよ"と表示 */
			putfn("weapon:%s", loginedPlayer.getWeapon() == null ? "つけてないよ" : loginedPlayer.getWeapon().getName());
		}else if(c == 'x'){
			/* ゲーム終了 */
			write("お疲れ様!ゲームを終了します!");
			write(loginedPlayer.getName() + "さんがログアウトしました!");
			return;
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
	public static boolean getAnsi()
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
			else{
				error("文字が入力されていません。");
				return read();
			}
		}catch(IOException ie){
			error("入出力でエラーが発生しました!");
			error("もう一度入力してみてください。");
			error("繰り返される場合いったんゲームを終了しましょう。");
			return read();
		}
	}
}
