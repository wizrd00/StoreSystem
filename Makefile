SRC_DIR := src
BIN_DIR := bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

all :
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES)

clean :
	rm -rf $(BIN_DIR)/*

run : all
	java -cp $(BIN_DIR) Main

test : all
	java -ea -cp $(BIN_DIR) test.TestReadCSV.CSVReaderTest
	java -ea -cp $(BIN_DIR) test.TestWriteCSV.CSVWriterTest
	java -ea -cp $(BIN_DIR) test.TestStoreSystem.StoreSystemTest

debug : all
	jdb -classpath bin Main
