# Google Cloud

## Commands

`gcloud projects list`
View the projects associated with the logged in google cloud account

`gcloud projects delete [PROJECT_ID]`
Delete the project

`gcloud config set project [YOUR_PROJECT_ID]`
Set the currently selected project on local machine to control deployments

`gcloud config set compute/zone [ZONE_ID]`
example 'asia-northeast1'

`gcloud container clusters create personal-website --num-nodes=2`
Create clusters for 'personal-website' with 2 nodes



`gcloud app browse`
Load the app in browser



## Java Bookshelf

To run locally
`mvn -Plocal clean jetty:run-exploded -DprojectID=bookshelfjava-255411`

To deploy new version to google app engine
`mvn appengine:deploy -DprojectID=bookshelfjava-255411`

