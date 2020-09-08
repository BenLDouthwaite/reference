# Create an s3 bucket
aws s3 mb s3://bld-code-sam

# package cloudformation
aws cloudformation package --s3-bucket bld-code-sam --template-file template.yaml --output-template-file gen/tempalte-generated.yaml

# deploy
aws cloudformation deploy --template-file /Users/DouthwaiteBe/dev/personal/reference/aws/serverless/microservicePython/gen/tempalte-generated.yaml --stack-name hello-world-sam --capabilities CAPABILITY_IAM
