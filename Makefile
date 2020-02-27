# jdk path
JDK = 
# java runtime path
JAVA  = $(JDK)java
# java compiler path
JAVAC = $(JDK)javac
# javadoc path
JAVADOC = $(JDK)javadoc
REMOVE = del
REMDIR = del
REMOPT = /f /s
# gnu make tool
MAKE  = make
# encoding mode
ENCODING = UTF8

build : 
#	make clean
	make game/Random.class
	make game/io/LockedInputStream.class
	make game/io/LockedOutputStream.class
	make game/io/LockedReader.class
	make game/io/LockedWriter.class
	make game/item/Item.class
	make game/item/Items.class
	make game/item/weapon/WeaponType.class
	make game/item/weapon/Weapon.class
	make game/item/weapon/None.class
	make game/item/weapon/axe/Axe.class
	make game/item/weapon/gloves/Glove.class
	make game/item/weapon/knife/Knife.class
	make game/item/weapon/longsword/LongSword.class
	make game/item/weapon/scythe/Scythe.class
	make game/item/weapon/spear/Spear.class
	make game/item/weapon/sword/Sword.class
	make game/item/ItemList.class
	make game/character/Status.class
	make game/character/Mob.class
	make game/character/enemy/Enemy.class
	make game/character/enemy/slime/Slime.class
	make game/character/player/Player.class
	make game/ConsEsc.class
	make game/Console.class
	make game/SaveData.class
	make game/Game.class

run :
#	make build
	$(JAVA) game.Game

javadoc : 
	$(JAVADOC) -encoding $(ENCODING) -d ..\docs -use -version -nohelp -charset $(ENCODING) -sourcetab 4 -docencoding $(ENCODING) \
		game game.character game.character.player game.item game.item.weapon \
		game.item.weapon.axe game.item.weapon.gloves game.item.weapon.knife game.item.weapon.longsword \
		game.item.weapon.scythe game.item.weapon.spear game.item.weapon.sword game.io

%.class : %.java
	$(JAVAC) -encoding $(ENCODING) $*.java

clean : 
	cd game
	$(REMOVE) $(REMOPT) *.class
	cd ..

