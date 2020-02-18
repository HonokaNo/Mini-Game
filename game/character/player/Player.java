package game.character.player;

/**
 * プレイヤーの分身として、ゲーム内で活動する
 * キャラクターのクラスです。
 */
public class Player
{
	/* プレイヤーの名前 */
	private String name;

	/**
	 * 新しいPlayerオブジェクトを生成します。
	 *
	 * @param name プレイヤーを識別する名前
	 */
	public Player(String name)
	{
		this.name = name;
	}

	/**
	 * プレイヤーの名前を返します。
	 *
	 * @return プレイヤーを識別する名前
	 */
	public String getName()
	{
		return name;
	}
}
