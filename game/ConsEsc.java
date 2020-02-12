package game;

/**
 * コンソールで出力する文字列に使用する
 * エスケープの列挙です。
 * memo:エスケープにはANSIエスケープを使用
 * Linux系列だとうまくいくことが多くWindowsだとうまくいきにくい
 *
 * @since 0.0.1p
 */
public enum ConsEsc
{
	/**
	 * エスケープをリセットします。
	 */
	ESC_RESET(0),
	/**
	 * 文字を太字で出力します。
	 */
	ESC_BOLD(1),
	/**
	 * 文字を薄い色で出力します。
	 */
	ESC_LIGHT(2),
	/**
	 * 文字を斜体で出力します。
	 */
	ESC_ITALIC(3),
	/**
	 * 文字に下線をつけて出力します。
	 */
	ESC_ULINE(4),
	/**
	 * 文字を点滅させます。
	 */
	ESC_BLINK(5),
	/**
	 * 文字を高速で点滅させます。
	 */
	ESC_FBLINK(6),
	/**
	 * 文字色と背景色を反転させます。
	 */
	ESC_TERN(7),
	/**
	 * 文字を隠します。
	 */
	ESC_HIDE(8),
	/**
	 * 取り消しを行います。
	 */
	ESC_CANCEL(9),
	/**
	 * 文字色を黒にして描画します。
	 */
	ESC_TBLACK(10),
	/**
	 * 文字列を赤にして描画します。
	 */
	ESC_TRED(11),
	/**
	 * 文字列を緑にして描画します。
	 */
	ESC_TGREEN(12),
	/**
	 * 文字列を黄色にして描画します。
	 */
	ESC_TYELLOW(13),
	/**
	 * 文字列を青にして描画します。
	 */
	ESC_TBLUE(14),
	/**
	 * 文字列をマゼンタにして描画します。
	 */
	ESC_TMAGENTA(15),
	/**
	 * 文字列を思案にして描画します。
	 */
	ESC_TCYAN(16),
	/**
	 * 文字列を白にして描画します。
	 */
	ESC_TWHITE(17),
	/**
	 * 背景を黒にして描画します。
	 */
	ESC_BBLACK(18),
	/**
	 * 背景を赤にして描画します。
	 */
	ESC_BRED(19),
	/**
	 * 背景を緑にして描画します。
	 */
	ESC_BGREEN(20),
	/**
	 * 背景を黄色にして描画します。
	 */
	ESC_BYELLOW(21),
	/**
	 * 背景を青にして描画します。
	 */
	ESC_BBLUE(22),
	/**
	 * 背景をマゼンタにして描画します。
	 */
	ESC_BMAGENTA(23),
	/**
	 * 背景をシアンにして描画します。
	 */
	ESC_BCYAN(24),
	/**
	 * 背景を白にして描画します。
	 */
	ESC_BWHITE(25);

	/* enumを数値とした場合の値 */
	private int number;
	/* enumもクラスなのでコンストラクタ */
	/* 引数に渡す数値が上のnumber */
	private ConsEsc(int num)
	{
		number = num;
	}

	/**
	 * enumを数値とした場合の値を返します。
	 *
	 * @return enumが列挙された順に0から振られた番号
	 */
	public int getNumber()
	{
		return number;
	}
};
