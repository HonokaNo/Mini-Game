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
	make clean
	make game/ConsEsc.class
	make game/Console.class
	make game/Game.class

run :
	make build
	$(JAVA) game.Game

javadoc : 
	$(JAVADOC) -encoding $(ENCODING) -d ..\docs -use -version -nohelp -charset $(ENCODING) -sourcetab 4 -docencoding $(ENCODING) \
		game

%.class : %.java
	$(JAVAC) -encoding $(ENCODING) $*.java

clean : 
	cd game
	$(REMOVE) $(REMOPT) *.class
	cd ..

