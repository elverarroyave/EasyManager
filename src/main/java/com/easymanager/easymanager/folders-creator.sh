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
			mkdir repository web gateway
			echo "$packageName/io/repository created"
			echo "$packageName/io/web created"
			echo "$packageName/io/gateway created"
			cd web 
				mkdir v1
				echo "$packageName/io/web/v1 created"
				cd v1
					mkdir model
					echo "$packageName/io/web/v1/model created"
				cd ..		
			cd ..
		cd ..
	mkdir model
	echo "$packageName/model created"
	mkdir service
	echo "$packageName/service created"	
		cd service
			mkdir model
			echo "$packageName/service/model created"
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

