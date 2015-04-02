#! /bin/bash

while read line
do
    key="${line/API_KEY=/}"
done < ../.env

# echo "Showing everything in ROOM collection"
# curl https://api.mongolab.com/api/1/databases/icuem/collections/ROOM\?apiKey\=$key
# echo;echo

# echo "Showing everything in USER collection"
# curl https://api.mongolab.com/api/1/databases/icuem/collections/USER\?apiKey\=$key
# echo;echo

# echo "Showing everything in CLASS-EVENT collection"
# curl https://api.mongolab.com/api/1/databases/icuem/collections/CLASS-EVENT\?apiKey\=$key
# echo;echo


echo "Insert into database from json file"
curl -X POST https://api.mongolab.com/api/1/databases/icuem/collections/ROOM\?apiKey\=$key
echo;echo