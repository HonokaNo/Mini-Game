package game;

import java.io.*;
import java.util.Locale;

import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * コンソール関連の処理を行うユーティリティクラスです。
 *
 * @since 0.0.1p
 */
public final class Console
{
	/* 一文字入力に使用するInputStream */
	private static BufferedInputStream charInput;
	/* 文字列取得に使用するReader */
	private static BufferedReader stringInput;

	/* 特定の地域を表す 開発地域が日本なので(笑)日本にしておく */
	private static Locale defaultLocale = Locale.JAPAN;

	/**
	 * このコンソール環境で使用されるロケールを設定します。
	 *
	 * @param locale 設定する地域を示すロケール
	 */
	public static void setLocale(Locale locale)
	{
		defaultLocale = locale;
	}

	/**
	 * コンソール出力で使用される改行コード
	 */
	public static final String NEWLINE = "\n";

	/**
	 * ただ文字列を出力して終了します。改行も何も行いません。
	 *
	 * @param str 出力する文字列
	 */
	public static void put(String str)
	{
		out.print(str);
	}

	/**
	 * 文字列を出力し、改行して終了します。
	 *
	 * @param str 出力する文字列
	 */
	public static void putl(String str)
	{
		put(str + NEWLINE);
	}

	/**
	 * 文字列を出力し、2回改行して終了します。
	 * 文字列の表示で区切りをつけるときに使用します。
	 *
	 * @param str 出力する文字列
	 */
	public static void putll(String str)
	{
		put(str + NEWLINE + NEWLINE);
	}

	/**
	 * 文字列をフォーマットに合わせて出力します。
	 * 変数の出力等はこちらを使う予定です。
	 *
	 * @param str 出力する文字列とそのフォーマット
	 * @param format 文字列のフォーマットに合わせた引数
	 */
	public static void putf(String str, Object... format)
	{
		out.format(defaultLocale, str, format);
	}

	/**
	 * エラー出力にメッセージを出力します。
	 *
	 * @param str 出力するエラーメッセージとそのフォーマット
	 * @param format 文字列のフォーマットに合わせた引数
	 */
	public static void error(String str, Object... format)
	{
		err.format(defaultLocale, str, format);
	}

	/**
	 * 引数で指定されたエスケープを適用した文字列を返します。
	 * なお、エスケープが実際に適用されるかは環境依存になります。
	 * memo:この関数ではANSIエスケープシーケンスを使用
	 * Linux系列だとうまくいくことが多くWindowsだとうまくいきにくい
	 *
	 * @param str 基本となるメッセージ
	 * @param escapes メッセージに適用するエスケープ
	 *
	 * @return 環境にかかわらずエスケープされた文字列
	 */
	public static String getEscapeString(String str, ConsEsc... escapes)
	{
		/* 場合によっては5回くらい書き換わるので */
		/* 変更可能なStringBufferを */
		StringBuffer sbuf = new StringBuffer("");

		/* enumは結局数値なのでenumと同じ順で配列を作ることで */
		/* 配列[enumの値]としたとき対応した配列の値を取り出せる */
		String[] esc = {"\\u001B[m", "\\u001B[1m",
						"\\u001B[2m", "\\u001B[3m",
						"\\u001B[4m", "\\u001B[5m",
						"\\u001B[6m", "\\u001B[7m",
						"\\u001B[8m", "\\u001B[9m",
						"\\u001B[30m", "\\u001B[31m",
						"\\u001B[32m", "\\u001B[33m",
						"\\u001B[34m", "\\u001B[35m",
						"\\u001B[36m", "\\u001B[37m",
						"\\u001B[40m", "\\u001B[41m",
						"\\u001B[42m", "\\u001B[43m",
						"\\u001B[44m", "\\u001B[45m",
						"\\u001B[46m", "\\u001B[47m",
		};
		/* 全てのエスケープを追加 */
		for(ConsEsc e : escapes) sbuf.append(esc[e.getNumber()]);
		sbuf.append(str);
		/* 最後にリセットを代入 */
		/* 実はappendはStringBufferを返すのでそのまま戻り値に変換して戻す */
		return sbuf.append(esc[0]).toString();
	}

	/**
	 * 引数で指定されたエスケープを適用してメッセージを出力します。
	 * なお、エスケープの処理は環境依存になります。
	 * memo:この関数ではANSIエスケープシーケンスを使用
	 * Linux系列だとうまくいくことが多くWindowsだとうまくいきにくいっぽい
	 *
	 * @param str 出力するメッセージ
	 * @param escapes 出力するメッセージに適用するエスケープ
	 */
	public static void pute(String str, ConsEsc... escapes)
	{
		put(getEscapeString(str, escapes));
	}

	/**
	 * 引数で指定されたカラーコードでメッセージを出力します。
	 * memo:この関数ではANSIエスケープシーケンスを使用
	 * Linux系列だとうまくいくことが多くWindowsだとうまくいきにくい
	 *
	 * @param str 出力するメッセージ
	 * @param color 0-255のカラーコード それ以外の値の場合動作未保障
	 */
	public static void putcolc(String str, int color)
	{
		put("\\u001B[38;5;" + color + "m" + str);
	}

	/**
	 * 引数で指定されたRGBでメッセージを出力します。
	 * memo:この関数ではANSIエスケープシーケンスを使用
	 * Linux系列だとうまくいくことが多くWindowsだとうまくいきにくい
	 *
	 * @param str 出力するメッセージ
	 * @param r 0-255のRGBのR
	 * @param g 0-255のRGBのG
	 * @param b 0-255のRGBのB
	 */
	public static void putcolrgb(String str, int r, int g, int b)
	{
		put("\\u001B[38;2;" + r + ";" + g + ";" + b + "m" + str);
	}

	/**
	 * 一文字だけ入力された値を取得します。
	 *
	 * @return 入力された文字 エラーが発生した場合0(0x30ではなく0x00)
	 *
	 * @throws IOException 入出力例外が発生した場合
	 */
	public static char getInputChar() throws IOException
	{
		/* 入力させる */
		charInput = new BufferedInputStream(in, 1);

		/* 読み込み */
		int read = charInput.read();

		/* エラーでない */
		if(read != -1){
			/* 読み込めるバイトをすべて読み飛ばす */
			charInput.skip(charInput.available());
			return (char)read;
		}else return (char)0;
	}

	/**
	 * 文字列の入力された値を取得します。
	 * もしかしたら200バイト以上の入力で例外が発生するかもしれません。
	 *
	 * @return 入力された文字列 エラーが発生した場合null
	 *
	 * @throws IOException 入出力例外が発生した場合
	 */
	public static String getInputString() throws IOException
	{
		/* 入力させる 基本的にこれ以上入れる人は少ないと思うから200バイト(全角100文字分) */
		stringInput = new BufferedReader(new InputStreamReader(in), 200);

		/* 読み込み */
		String read = stringInput.readLine();

		/* エラーでない */
		if(read != null){
			/* 読み飛ばしは改行(読み込み開始)までを上で取得しているのでいらない */
			return read;
		}else return null;
	}
}
