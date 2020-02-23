package game.character.enemy.slime;

import game.character.Mob;
import game.character.enemy.Enemy;

public class Slime extends Enemy
{
	@Override protected void statusInit()
	{
		name = "スライム";

		status.lv = 1;
		status.mhp = 3;
		status.ap = status.map = 2;
		status.bp = status.mbp = 1;
		status.sp = 1;
		status.mmp = 0;
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		System.out.println("スライムのコマンド");
	}
}
