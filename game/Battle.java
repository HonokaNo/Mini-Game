package game;

import game.character.Mob;
import game.character.player.Player;

import static game.Game.*;
import static game.Console.*;

/**
 * バトル時の処理に関するクラスです。
 *
 * @version 0.0.1p
 */
public final class Battle
{
	public static void battleInit(Mob[] m, Mob[] n)
	{
		if(m != null && n != null){
			/* どのキャラも逃げていない状態にする */
			for(Mob mob : m) mob.battleInit();
			for(Mob mob : n) mob.battleInit();

			putll(n[0].getName() + (n.length > 1 ? "ら" : "") + "が現れた!");

			battleLoop(m, n);

			putl("戦いが終了した!");
		}
	}

	private static void battleLoop(Mob[] p, Mob[] e)
	{
		putl("---------------------------------");
		for(int l = 0; l < p.length; l++) if(!p[l].isFlee() && !p[l].isDeath()) putf("%s   HP:%d/%d MP:%d/%d%s", p[l].getName(), p[l].getStatus().hp, p[l].getStatus().mhp, p[l].getStatus().mp, p[l].getStatus().mmp, NEWLINE);
		putl("---------------------------------");
		for(int l = 0; l < e.length; l++) if(!e[l].isFlee() && !e[l].isDeath()) putf("%s   HP:%d/%d MP:%d/%d%s", e[l].getName(), e[l].getStatus().hp, e[l].getStatus().mhp, e[l].getStatus().mp, e[l].getStatus().mmp, NEWLINE);
		putll("---------------------------------");

		Mob[] mobs = new Mob[p.length + e.length];
		System.arraycopy(p, 0, mobs, 0, p.length);
		System.arraycopy(e, 0, mobs, p.length, e.length);
		int left = 0, right = mobs.length - 1;
		mobsort(mobs, left, right);

		for(int l = mobs.length - 1; l >= 0; l--){
			if(!mobs[l].isFlee() && !mobs[l].isDeath()){
				mobs[l].command(p, e);
				putl("");
			}

			/* 逃げられたので関数から抜ける */
			if(mobs[l] instanceof Player && mobs[l].isFlee()) return;
		}

		int death = 0;
		for(; death < e.length; death++){
			if(!e[death].isFlee() && !e[death].isDeath()) break;
		}
		/* 全ての敵を倒した */
		if(death == e.length){
			putl("すべての敵を倒した!");

			return;
		}

		battleLoop(p, e);
	}

	/* 敵の配列を素早さ順に並びかえる */
	/* 参照:Wikipedia:クイックソート */
	private static void mobsort(Mob[] m, int l, int r)
	{
		if(l < r){
			int i = l, j = r;

			Mob tmp, piv = med(m[i], m[i + (j - i) / 2], m[j]);
			while(true){
				/* 後半の条件は配列のサイズを超えないように */
				while(m[i].getStatus().sp < piv.getStatus().sp && i < m.length - 2) i++;
				while(piv.getStatus().sp < m[j].getStatus().sp && j > 0) j--;
				if(i >= j) break;
				/* m[i] と m[j] を交換 */
				tmp = m[i]; m[i] = m[j]; m[j] = tmp;
				i++; j--;
			}
			mobsort(m, l, i - 1);	/* 分割した左を再帰的にソート */
			mobsort(m, j + 1, r);	/* 分割した右を再帰的にソート */
		}
	}

	/* x, y, z のspの中央値を取る */
	/* 参照:Wikipedia:クイックソート */
	private static Mob med(Mob x, Mob y, Mob z)
	{
		if(x.getStatus().sp < y.getStatus().sp){
			if(y.getStatus().sp < z.getStatus().sp) return y;
			else if(z.getStatus().sp < x.getStatus().sp) return x;
			else return z;
		}else{
			if(z.getStatus().sp < y.getStatus().sp) return y;
			else if(x.getStatus().sp < z.getStatus().sp) return x;
			else return z;
		}
	}
}
