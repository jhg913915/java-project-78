.DEFAULT_GOAL := build-run
clean:
	./gradlew clean
build:
	./gradlew clean build
install-dist:
	./gradlew clean installDist
run:
	./gradlew run
test:
	./gradlew test
report:
	./gradlew jacocoTestReport
lint:
	./gradlew checkstyleMain
checkstyle:
	./gradlew checkstyleMain
run-dist:
	./build/install/app/bin/app