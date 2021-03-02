#!/bin/bash
clear
echo "*************************************"
echo "*FOLDERS CREATOR WHIT BASE STRUCTURE*"
echo "*************************************"
echo "-----------------------------------------"
continue="y" 
while [ $continue == "y" ]; do
echo "What is the name of the package?"
read packageName
mkdir $packageName
cd $packageName
	mkdir io
	echo "$packageName/io created"
		cd io
			mkdir repository web
			echo "$packageName/io/repository created"
			echo "$packageName/io/web created"
			cd web 
				mkdir v1 request
				echo "$packageName/io/web/v1 created"
				echo "$packageName/io/web/request created"
			cd ..
		cd ..
	mkdir model
	echo "$packageName/model created"
	mkdir service
	echo "$packageName/service created"	
		cd service
			mkdir impl mapper
			echo "$packageName/service/impl created"
			echo "$packageName/service/mapper created"
		cd ..
	cd ..
echo "***************All right!****************"
echo "-----------------------------------------"
echo "Do you need other package? y/n"
read continue
if [ $continue != "y" ];
then
	exit
fi
done

