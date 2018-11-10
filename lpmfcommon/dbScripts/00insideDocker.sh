mysql --default-character-set=utf8 -u root -ppassword < 01CreationScript.sql
mysql --default-character-set=utf8 -u root -ppassword < 02CreationViews.sql
mysql --default-character-set=utf8 -u root -ppassword < 03InsertsIntoCleanUpMaps.sql
mysql --default-character-set=utf8 -u root -ppassword < 04InsertsIntoRawData.sql


