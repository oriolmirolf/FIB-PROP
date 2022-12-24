CLASS_INPUT = ./FONTS/src/main/domain/controllers/*.java \
./FONTS/src/main/domain/classes/resultats/*.java \
./FONTS/src/main/domain/classes/indexes/*java \
./FONTS/src/main/domain/classes/individual_classes/*java \
./FONTS/src/main/domain/classes/exceptions/*java \
./FONTS/src/main/persistencia/*.java \
./FONTS/src/main/persistencia/excepcions/*.java \
./FONTS/src/drivers/DriverDomini.java

CLASS_INPUT_PRESENTACIO = ./FONTS/src/main/presentation/views/*.java \
./FONTS/src/main/presentation/controllers/*.java \
./FONTS/src/main/persistencia/*.java \
./FONTS/src/main/persistencia/excepcions/*.java\
./FONTS/src/main/domain/controllers/*.java \
./FONTS/src/main/domain/classes/resultats/*.java \
./FONTS/src/main/domain/classes/indexes/*java \
./FONTS/src/main/domain/classes/individual_classes/*java \
./FONTS/src/main/domain/classes/exceptions/*java \
./FONTS/src/drivers/DriverPresentacio.java

CLASS_INPUT_ALL = ./:./lib/gson-2.8.2.jar:./lib/itextpdf-5.5.13.2.jar
CLASS_OUTPUT =./EXE/out:./lib/gson-2.8.2.jar:./lib/itextpdf-5.5.13.2.jar






make_out_dir:
	if [! -d "./EXE/out"]; then mkdir EXE/out; fi
	
compile_driverDomini: make_out_dir
	javac -cp $(CLASS_INPUT_ALL) -d ./out/ $(CLASS_INPUT)

run_driverDomini: 
	java -cp $(CLASS_OUTPUT) FONTS.src.drivers.DriverDomini
	
compile_run_driverDomini: compile_driverDomini
	java -cp $(CLASS_OUTPUT) FONTS.src.drivers.DriverDomini
	
compileDriverPresentacio: make_out_dir
	javac -cp $(CLASS_INPUT_ALL) -d ./EXE/out/ $(CLASS_INPUT_PRESENTACIO)
	
run_App: 
	java -cp $(CLASS_OUTPUT) FONTS.src.drivers.DriverPresentacio
		
compile_run_App: compileDriverPresentacio
	java -cp $(CLASS_OUTPUT) FONTS.src.drivers.DriverPresentacio
	
compile_all: compile_driverDomini compileDriverPresentacio

clean_out:
	rm -r ./EXE/out/*
