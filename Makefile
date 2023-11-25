all: menu

menu: Menu.java
	javac Menu.java

run: clean all
	java Menu

clean:
	rm -rf *.class