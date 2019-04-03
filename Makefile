#
JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
jDoc= doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=LinearProbingHashTable.java QuadraticProbingHashTable.java ChainingHashTable.java HashTables.java

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
run:
	java -cp bin HashTables
doc:
	javadoc  -d doc src/HashTables.java
	
cleandoc:
	rm -r doc

	



