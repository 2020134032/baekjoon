
if [ $# -gt 0 ]; then
    echo writing to current directory
else
    echo making a directory besides this
    cd ..
fi

echo Type directory name

read DIRNAME

mkdir $DIRNAME

cd $DIRNAME
touch Main.java
touch i.txt
