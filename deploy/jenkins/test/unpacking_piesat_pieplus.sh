echo $1 $2
timeStamp=`date +%Y-%m-%d`
echo $timeStamp@$2

#cd /home/ec2-user/school
cd /apps/piesat-school/server

tmpDir=$timeStamp"@"$2

if [ ! -d $tmpDir  ];then
  mkdir $tmpDir
else
  echo dir $tmpDir exist
fi

sudo wget -P /apps/piesat-school/server/$tmpDir -N http://jkdevplus.piesat.cn/deploy/docs/build/$1/$timeStamp@$2/piesat-school-rpc.jar
sudo wget -P /apps/piesat-school/server/$tmpDir -N http://jkdevplus.piesat.cn/deploy/docs/build/$1/$timeStamp@$2/piesat-school-rest.jar

echo "start move jar"

#sudo -i

sudo mv -f /apps/piesat-school/server/$tmpDir/piesat-school-rpc.jar /home/pie+/logs/piesat-school（pie+）/piesat-school-rpc/
sudo mv -f /apps/piesat-school/server/$tmpDir/piesat-school-rest.jar /home/pie+/logs/piesat-school（pie+）/piesat-school-rest/

sleep 5

sudo rm -rf /apps/piesat-school/server/$tmpDir/

echo "completed move jar"
echo "start rpc run.sh"

cd /apps/pie+/logs/piesat-school（pie+）/piesat-school-rpc/
sh run.sh >/dev/null 2>&1 &

sleep 10

echo "start rest run.sh"

cd /home/pie+/logs/piesat-school（pie+）/piesat-school-rest/
sh run.sh >/dev/null 2>&1 &
