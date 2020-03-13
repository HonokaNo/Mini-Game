package game.magic;

import game.character.Mob;

public abstract class Magic
{
	/**
	 * セーブするときに使用する番号
	 */
	protected int num;

	/**
	 * 魔法名
	 */
	protected String name;

	/**
	 * 説明
	 */
	protected String[] description = new String[3];

	/**
	 * 使用魔力
	 */
	protected int use;

	/**
	 * 魔法名を返す
	 *
	 * @return 魔法名
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * 説明文を返す
	 *
	 * @return 説明
	 */
	public final String[] getDescription()
	{
		return description;
	}

	/**
	 * 魔法の番号
	 *
	 * @return 固有の番号
	 */
	public final int getNumber()
	{
		return num;
	}

	/**
	 * 使用するMPを返す
	 *
	 * @return 消費MP
	 */
	public final int getUse()
	{
		return use;
	}

	/**
	 * 使用時処理
	 *
	 * @param m 使用するMob
	 * @param e 対象のMob
	 */
	public abstract void use(Mob m, Mob[] e);

	@Override public boolean equals(Object o)
	{
		if(o instanceof Magic){
			Magic magic = (Magic)o;
			if(magic.getNumber() == getNumber()){
				if(magic.getName().equals(getName())){
					if(magic.getDescription().length == getDescription().length){
						int l = 0;
						for(; l < magic.getDescription().length; l++){
							if(!magic.getDescription()[l].equals(getDescription()[l])) break;
						}
						/* 固有番号と名前と説明完全一致 */
						if(l == magic.getDescription().length) return true;
					}
				}
			}
		}

		return false;
	}
}
