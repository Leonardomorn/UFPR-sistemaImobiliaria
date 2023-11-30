all: menu

menu: Menu.java
	javac Menu.java

run: clean all
	java Menu

jar: menu
	jar cvmf META-INF/MANIFEST.MF app.jar *.class

clean:
	rm -rf *.class