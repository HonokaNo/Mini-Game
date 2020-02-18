package game;

import java.io.IOException;

import game.ConsEsc;
import game.character.player.Player;

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
			putfn("%s lv.xxがログインしたよ!" + NEWLINE + NEWLINE, loginedPlayer.getName());
			maintown();
		/* System.exitだとファイナライザが呼ばれない(らしい)ので */
		/* あえてreturnで終わらせてみる(意味があるかは知らない) */
		}else if(c == 'x') return;
	}

	private static void maintown()
	{
		write("---------------");
		write("初めの町 住宅街");
		putll(loginedPlayer.getName() + "はどうしようか?");
		write("1.冒険に外へ行く");
		write("2.市場に散歩に行く");
		write("3.自分自身を見つめる");
		write("x.ゲーム終了");
		char c = input();

		if(c == '1'){
			write("今行くのはさすがに危険すぎるからやめておこう。");
		}else if(c == '2'){
			/* 市場に散歩に行く */
			/* アイテムの購入やイベントの発生など */
			write("しかし自分はお金を持っていなかった。");
			write("だからどこにも行かず戻ってきた。");
		}else if(c == '3'){
			/* 自分自身を見つめる */
			/* ステータスの表示 */
			putfn("%s lv.xxx", loginedPlayer.getName());
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
			return c;
		}catch(IOException ie){
			error("入出力でエラーが発生しました!");
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
			error("入出力でエラーが発生しました!");
			return null;
		}
	}
}
