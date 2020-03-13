package game.magic;

public final class MagicManager
{
	private static Magic[] magics = new Magic[300];
	private static int pos = 0;

	public static Magic getMagic(int num)
	{
		if(MagicList.magiclist.length > num) return MagicList.magiclist[num];
		return null;
	}

	public static Magic[] getMagics()
	{
		if(pos > 0){
			Magic[] magic = new Magic[pos];
			System.arraycopy(magics, 0, magic, 0, pos);
			return magic;
		}
		return null;
	}

	public static boolean addMagic(Magic m)
	{
		for(int l = 0; l < pos; l++){
			if(magics[l].equals(m)) return false;
		}
		/* 見つからなかった */
		magics[pos] = m;
		pos++;
		return true;
	}
}
