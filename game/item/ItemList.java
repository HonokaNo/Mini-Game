package game.item;

import game.item.weapon.None;
import game.item.weapon.axe.*;
import game.item.weapon.gloves.*;
import game.item.weapon.knife.*;
import game.item.weapon.longsword.*;
import game.item.weapon.scythe.*;
import game.item.weapon.spear.*;
import game.item.weapon.sword.*;

/* セーブデータから読み込むときに使用する */
/* 番号とアイテムの対応表 */
final class ItemList
{
	/**
	 * 初めの斧:1
	 * 初めの籠手:2
	 * 初めの小刀:3
	 * 初めの大剣:4
	 * 初めの鎌:5
	 * 初めの槍:6
	 * 初めの剣:7
	 */
	static Item[] itemlist = {
		new None(),
		new Axe(),
		new Glove(),
		new Knife(),
		new LongSword(),
		new Scythe(),
		new Spear(),
		new Sword(),
	};
}
