package game.item;

/**
 * アイテム管理に使用するクラスです。
 */
public class Items
{
	/**
	 * 所有しているアイテム
	 */
	private Item item;

	/**
	 * 所有しているアイテムの個数
	 */
	private long num;

	/**
	 * 一つだけアイテムを持ったItemsを生成します。
	 *
	 * @param i 所持しているアイテム
	 */
	public Items(Item i)
	{
		item = i;
		num = 1;
	}

	/**
	 * 複数同一アイテムを持ったItemsを生成します。
	 *
	 * @param i 所持しているアイテム
	 * @param n 所持しているアイテムの個数
	 */
	public Items(Item i, long n)
	{
		item = i;
		num = n;
	}

	public Item getItem()
	{
		return item;
	}

	public long getNumber()
	{
		return num;
	}

	public void addNumber(long num)
	{
		this.num += num;
	}
}
