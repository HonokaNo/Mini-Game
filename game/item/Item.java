package game.item;

import game.character.player.Player;

/**
 * プレイヤーが自由に使用できるアイテムの処理を実装します。
 *
 * @version 0.0.1p
 */
public abstract class Item
{
	/**
	 * アイテムの名前 この名前はほかのアイテムと被ってはいけません。
	 */
	protected String name;

	/**
	 * アイテムの説明
	 */
	protected String description;

	/**
	 * 使用した際アイテムは消えてなくなるか
	 */
	protected boolean lost;

	/**
	 * この武器の名前を返します。
	 *
	 * @return このアイテムの一意な名前
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * このアイテムの説明を返します。
	 *
	 * @return このアイテムの説明文
	 */
	public final String getDescription()
	{
		return description;
	}

	/**
	 * アイテムが使われた際の処理を定義します。
	 *
	 * @param p そのアイテムを使用したプレイヤー
	 */
	public abstract void use(Player p);
}
