SRCFILES := $(shell find src -name \*.java)
RESFILES := $(shell (find src -name \*.png ; find src -name \*.ttf ; find src -name \*.txt) | sed s/^src\\///)
PACKAGES := $(shell find src/com/kreative/bitsnpicas -type d \! -path \*/.\* | sed s/^src\\/// | tr / .)

all: clean bin doc BitsNPicas.jar BitsNPicas.app BitsNPicas-src.tgz

eclipseall: eclipseclean BitsNPicas.jar BitsNPicas.app BitsNPicas-src.tgz

clean:
	rm -rf bin
	rm -rf doc
	rm -rf BitsNPicas*.jar
	rm -rf BitsNPicas*.tgz
	rm -rf BitsNPicas*.app

eclipseclean:
	rm -rf BitsNPicas*.jar
	rm -rf BitsNPicas*.tgz
	rm -rf BitsNPicas*.app

bin: src
	mkdir -p bin
	javac -classpath dep/KSFL.jar:dep/orange-extensions.jar -sourcepath src $(SRCFILES) -d bin
	$(foreach res,$(RESFILES),cp src/$(res) bin/$(res);)

doc: src
	mkdir -p doc
	javadoc -classpath dep/KSFL.jar:dep/orange-extensions.jar -sourcepath src $(PACKAGES) -d doc

BitsNPicas.jar: bin
	export COPYFILE_DISABLE=true
	find bin -name .DS_Store -delete
	jar cmf dep/MANIFEST.MF BitsNPicas.jar -C bin com/kreative/bitsnpicas

BitsNPicas.app: dep BitsNPicas.jar
	mkdir -p BitsNPicas.app/Contents/MacOS
	mkdir -p BitsNPicas.app/Contents/Resources/Java
	cp -f dep/PkgInfo BitsNPicas.app/Contents
	cp -f dep/Info.plist BitsNPicas.app/Contents
	cp -f dep/universalJavaApplicationStub BitsNPicas.app/Contents/MacOS/BitsNPicas
	cp -f dep/*.icns BitsNPicas.app/Contents/Resources
	cp -f dep/*.jar BitsNPicas.app/Contents/Resources/Java
	cp -f BitsNPicas.jar BitsNPicas.app/Contents/Resources/Java

BitsNPicas.dmg: BitsNPicas.app
	mkdir -p dmgtmp
	cp -R BitsNPicas.app dmgtmp
	cp BitsNPicas.jar dmgtmp
	hdiutil create -fs HFS+ -volname "Bits'n'Picas" -srcfolder dmgtmp BitsNPicas.dmg
	rm -rf dmgtmp

BitsNPicas-src.tgz: src
	export COPYFILE_DISABLE=true
	find src -name .DS_Store -delete
	tar -czf BitsNPicas-src.tgz src/com/kreative/bitsnpicas LICENSE

install: BitsNPicas.jar
	sudo mkdir -p /usr/local/lib/
	sudo cp -f BitsNPicas.jar /usr/local/lib/bitsnpicas.jar
	sudo chmod -f 644 /usr/local/lib/bitsnpicas.jar
	sudo mkdir -p /usr/local/bin/
	sudo cp -f dep/bitsnpicas /usr/local/bin/bitsnpicas
	sudo chmod -f 755 /usr/local/bin/bitsnpicas
	sudo mkdir -p /usr/share/icons/hicolor/128x128/apps/
	sudo cp -f dep/bitsnpicas.png /usr/share/icons/hicolor/128x128/apps/bitsnpicas.png
	sudo chmod -f 644 /usr/share/icons/hicolor/128x128/apps/bitsnpicas.png
	sudo mkdir -p /usr/share/applications/
	sudo cp -f dep/bitsnpicas.desktop /usr/share/applications/bitsnpicas.desktop
	sudo chmod -f 644 /usr/share/applications/bitsnpicas.desktop

.PHONY: all eclipseall clean eclipseclean install
