#!/bin/bash

# Configuration
PROJECT_ROOT=$(pwd)
WEBAPP_NAME="ETU003209"
TOMCAT_HOME="/usr/local/tomcat"  # Typical Tomcat installation path on macOS
TOMCAT_WEBAPPS="$TOMCAT_HOME/webapps"

# Create temporary directories
BUILD_DIR="$PROJECT_ROOT/build"
CLASSES_DIR="$BUILD_DIR/WEB-INF/classes"
mkdir -p "$CLASSES_DIR"

echo "=== Cleaning old builds ==="
[ -d "$BUILD_DIR" ] && rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"
mkdir -p "$CLASSES_DIR"

echo "=== Compiling Java files ==="
# Create CLASSPATH
CLASSPATH=""
for jar in "$PROJECT_ROOT"/lib/*.jar; do
    if [ -z "$CLASSPATH" ]; then
        CLASSPATH="$jar"
    else
        CLASSPATH="$CLASSPATH:$jar"
    fi
done

# Compile with new structure
find "$PROJECT_ROOT/src/main/java" -name "*.java" > sources.txt
javac -cp "$CLASSPATH" -d "$CLASSES_DIR" @sources.txt
if [ $? -ne 0 ]; then
    echo "Compilation error"
    rm sources.txt
    exit 1
fi
rm sources.txt

echo "=== Copying web resources ==="
# Copy webapp content with new structure
cp -R "$PROJECT_ROOT/src/main/webapp/"* "$BUILD_DIR"

# Copy libraries
mkdir -p "$BUILD_DIR/WEB-INF/lib"
cp "$PROJECT_ROOT/lib/"*.jar "$BUILD_DIR/WEB-INF/lib/"
# Remove servlet-api.jar from WAR
[ -f "$BUILD_DIR/WEB-INF/lib/servlet-api.jar" ] && rm "$BUILD_DIR/WEB-INF/lib/servlet-api.jar"

echo "=== Creating WAR ==="
cd "$BUILD_DIR"
jar -cvf "$WEBAPP_NAME.war" *

echo "=== Deploying to Tomcat ==="
# Remove old version if it exists
[ -f "$TOMCAT_WEBAPPS/$WEBAPP_NAME.war" ] && rm "$TOMCAT_WEBAPPS/$WEBAPP_NAME.war"
[ -d "$TOMCAT_WEBAPPS/$WEBAPP_NAME" ] && rm -rf "$TOMCAT_WEBAPPS/$WEBAPP_NAME"

# Copy new WAR
cp "$WEBAPP_NAME.war" "$TOMCAT_WEBAPPS"

echo "=== Deployment complete ==="
echo
echo "Access your application at: http://localhost:8080/$WEBAPP_NAME"
echo
cd "$PROJECT_ROOT"