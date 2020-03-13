package game.item;

public final class ItemManager
{
	/* アイテムと個数のリスト */
	private static Items[] items = new Items[1000];
	/* 配列の最終位置 */
	private static int pos = 0;

	public static Item getItem(int num)
	{
		if(ItemList.itemlist.length > num) return ItemList.itemlist[num];
		return null;
	}

	/**
	 * 全てのアイテムを格納した配列を返します。
	 * なお、新たな配列を生成して返すので変更が自由に行えます。
	 *
	 * @return 全ての所持しているアイテムのある配列 もしアイテムを所持していない場合nullが返る
	 */
	public static Items[] getItems()
	{
		if(pos > 0){
			Items[] item = new Items[pos];
			System.arraycopy(items, 0, item, 0, pos);
			return item;
		}
		return null;
	}

	/**
	 * アイテムを追加します。
	 * 万が一アイテムがあふれた場合の処理はしていないので注意!
	 *
	 * @param i 追加するアイテム
	 * @param num 追加する個数
	 */
	public static void addItem(Item i, long num)
	{
		Items item = new Items(i, num);
		for(int l = 0; l < pos; l++){
			if(items[l].getItem().equals(i)){
				items[l].addNumber(item.getNumber());
				return;
			}
		}
		/* 見つからなかった */
		items[pos] = item;
		pos++;
	}
}
