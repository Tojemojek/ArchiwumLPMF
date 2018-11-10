sudo docker cp 01CreationScript.sql mysql-janki:/
sudo docker cp 02CreationViews.sql mysql-janki:/
sudo docker cp 03InsertsIntoCleanUpMaps.sql mysql-janki:/
sudo docker cp 04InsertsIntoRawData.sql mysql-janki:/
sudo docker cp 00insideDocker.sh mysql-janki:/

sudo docker exec mysql-janki /bin/bash /00insideDocker.sh