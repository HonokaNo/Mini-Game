package game;

import java.util.Locale;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * コンソールへの出力を行うユーティリティクラスです。
 *
 * @since 0.0.1p
 */
public final class Console
{
	/* 特定の地域を表す 開発地域が日本なので(笑)日本にしておく */
	private static Locale defaultLocale = Locale.JAPAN;

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
}
