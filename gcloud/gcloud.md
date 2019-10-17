# Google Cloud

## Commands

`gcloud projects list`
View the projects associated with the logged in google cloud account

`gcloud config set project [YOUR_PROJECT_ID]`
Set the currently selected project on local machine to control deployments

`gcloud app browse`
Load the app in browser

## Java Bookshelf

To run locally
`mvn -Plocal clean jetty:run-exploded -DprojectID=bookshelfjava-255411`

To deploy new version to google app engine
`mvn appengine:deploy -DprojectID=bookshelfjava-255411`

