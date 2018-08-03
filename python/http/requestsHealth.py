import requests

url = "https://ps-docker.incubator.aws-dqs.rentalcars.com:9521/health"

headers = {
	'Content-Type': "application/json",
	'X-RC-Application': "inventory-admin",
	'Secret': "LypQNHXM37",
	'X-RC-UserId': "999",
	'X-RC-UserName': "BLD",
	'Cache-Control': "no-cache",
	'Postman-Token': "353df931-11e1-43b6-9af4-f20fa9bae4af"
}

response = requests.get(url, headers=headers, verify=False)

print(response.text)