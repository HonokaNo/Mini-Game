package game;

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
	 */
	public static void write(String str, ConsEsc... escapes)
	{
		/* puteでは改行をしないので改行を一緒に出力する */
		if(useAnsi) pute(str + NEWLINE, escapes);
		else putl(str);
	}
}
