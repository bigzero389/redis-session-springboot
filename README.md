# Redis Session Test

## Runtime
* springboot
* redis (localhost)

## Test
### set session info
* request send  (save session id in cookie.txt)
` curl -v -I  -c cookie.txt "http://localhost:8080/hello?helloCaller=bigzero&greetingText=GREETING" `
* response (json, inserted data)  
` {"helloCaller":"bigzero","greetingText":"GREETING"} `

### get session info string
* request send  (use session id in cookie.txt)
` curl -b cookie.txt "http://localhost:8080/getString" `
* response (string)  
` bigzero:GREETING `

### get session info json 
* request send  (use session id in cookie.txt)
` curl -b cookie.txt "http://localhost:8080/getObject" `
* response (json)  
` {"helloCaller":"bigzero","greetingText":"GREETING"} `
