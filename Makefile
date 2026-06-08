SRC_DIR := src
BIN_DIR := bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

all :
	mkdir -p $(BIN_DIR)
	javac -g -d $(BIN_DIR) $(SOURCES)

clean :
	rm -rf $(BIN_DIR)/*

run : all
	java -cp $(BIN_DIR) Main

debug : all
	jdb -classpath bin Main
