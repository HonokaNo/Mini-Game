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
	 * セーブするときに使用する番号
	 * 同じアイテムは同じ番号、違うアイテムは違う番号でないといけない
	 */
	protected int num;

	/**
	 * アイテムの名前 この名前はほかのアイテムと被ってはいけません。
	 */
	protected String name;

	/**
	 * アイテムの説明
	 */
	protected String[] description = new String[3];

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
	public final String[] getDescription()
	{
		return description;
	}

	/**
	 * このアイテムの番号を返します。
	 *
	 * @return このアイテム固有の番号
	 */
	public final int getNumber()
	{
		return num;
	}

	/**
	 * アイテムが使われた際の処理を定義します。
	 *
	 * @param p そのアイテムを使用したプレイヤー
	 */
	public abstract void use(Player p);

	@Override public boolean equals(Object o)
	{
		if(o instanceof Item){
			Item item = (Item)o;
			if(item.getNumber() == getNumber()){
				if(item.getName().equals(getName())){
					if(item.getDescription().length == getDescription().length){
						int l = 0;
						for(; l < item.getDescription().length; l++){
							if(!item.getDescription()[l].equals(getDescription()[l])) break;
						}
						/* 固有番号と名前と説明完全一致 */
						if(l == item.getDescription().length) return true;
					}
				}
			}
		}

		return false;
	}
}
