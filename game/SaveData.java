package game;

import java.io.*;

import game.Game;
import game.io.*;

import static game.Console.*;

public final class SaveData
{
	public static void write()
	{
		try{
			File f = new File("save.dat");
			LockedOutputStream fos = new LockedOutputStream(new FileOutputStream(f));

			Game.loginedPlayer.s_write(fos);

			fos.close();

			f = new File("save.dat2");
			LockedWriter fw = new LockedWriter(new FileWriter(f));

			Game.loginedPlayer.s_writename(fw);

			fw.close();
		}catch(IOException ie){
			error("セーブ時にエラーが発生しました");
		}
	}

	public static boolean read()
	{
		try{
			File f = new File("save.dat");
			LockedInputStream fis = new LockedInputStream(new FileInputStream(f));

			Game.loginedPlayer = new game.character.player.Player("");
			Game.loginedPlayer.s_read(fis);

			fis.close();
			return true;
		}catch(IOException ie){
			error("セーブデータを読み込むときにエラーが発生しました");
			return false;
		}
	}
}
