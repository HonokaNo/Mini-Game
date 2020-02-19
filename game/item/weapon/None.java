package game.item.weapon;

import game.character.Status;

/**
 * 何も武器を装備していないことを示すクラスです。
 *
 * @version 0.0.1p
 */
public class None extends Weapon
{
	/**
	 * コンストラクタ
	 */
	public None()
	{
		name = "何も装備してないよ";
		description = "何もつけてない";
		st = new Status();
	}
}
