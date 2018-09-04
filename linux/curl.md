## Curl

# Tracking the time a request takes

curl -o /dev/null -s -w 'Total: %{time_total}\n'  https://www.google.com
