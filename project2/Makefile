all:
	mkdir -p class
	cp -R images class
	javac -cp ../libs/image4j.jar:. src/* -d class
	cd class; java -cp ../../libs/image4j.jar:. Invariants | tee report.tex
	cd class; pdflatex report.tex
	cp class/report.pdf .

clean:
	rm -rf class

