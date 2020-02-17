package game;

import java.io.IOException;

import game.ConsEsc;

import static game.Console.*;

/**
 * ゲームの根幹となるクラスです。
 *
 * @version 0.0.1p
 */
public final class Game
{
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
			String name = read();
			putf("%s lv.xxがログインしたよ!" + NEWLINE + NEWLINE, name);
			maintown(name);
		/* System.exitだとファイナライザが呼ばれない(らしい)ので */
		/* あえてreturnで終わらせてみる(意味があるかは知らない) */
		}else if(c == 'x') return;
	}

	private static void maintown(String name)
	{
		write("初めの町 住宅街");
		putll(name + "はどうしようか?");
		write("x.ゲーム終了");
		char c = input();

		if(c == 'x'){
			write("お疲れ様!ゲームを終了します!");
			write(name + "さんがログアウトしました!");
			return;
		}else{
			write("関係ない入力がされました。", ConsEsc.ESC_TRED);
			maintown(name);
		}
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
			return c;
		}catch(IOException ie){
			write("入出力でエラーが発生しました!", ConsEsc.ESC_TRED);
			return (char)0;
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
			return s;
		}catch(IOException ie){
			write("入出力でエラーが発生しました!", ConsEsc.ESC_TRED);
			return null;
		}
	}
}
