JFLAGS = -g
J = java
JC = javac
JR = jar -cvfm
RJ = java -jar
.SUFFIXES:
	.java .class
CLASSES = ChristmasPresent.java
default:
	$(JC) $(JFLAGS) ChristmasPresent.java
run:
	find . -type f -name "*.class" -exec rm {} \; && $(JC) $(JFLAGS) ChristmasPresent.java && $(J) ChristmasPresent 

jar:
	$(JR) MerryChristmas.jar Manifest.txt *

jarrun:
	$(RJ) MerryChristmas.jar

clean:
	find . -type f -name "*.class" -exec rm {} \;