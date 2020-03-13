package game.character.enemy.slime;

import game.character.Effect;
import game.character.Mob;
import game.character.Status;
import game.character.enemy.Enemy;

import static game.Console.*;
import static game.Random.*;

public class HopSlime extends Enemy
{
	@Override protected void statusInit()
	{
		name = "ラビットスライム";

		status.lv = 1;
		status.mhp = 6;
		status.ap = 4;
		status.bp = 4;
		status.map = 3;
		status.mbp = 3;
		status.sp = 5;
		status.mmp = 2;

		sendexp = 2;
		send = 2;
	}

	@Override public void command(Mob[] m, Mob[] n)
	{
		long rand = _rand();
		if(rand <= 20 && status.mp >= 2){
			putl(name + "のホッピング!");
			Status st = new Status();
			st.sp = 2;
			Effect e = new Effect(5, st, "ホッピング");
			addEffect(e);
		}else if(rand <= 80) powerAttack(this, m[(int)rand(m.length)], 1);
	}
}
